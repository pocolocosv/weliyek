/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.bitcoin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import weliyek.ketza.util.ByteSequence;
import weliyek.ketza.util.ByteSequenceWrapper;

public class BitcoinScriptOp
{

    private final int opcode;
    private final Optional<BitcoinScriptOpName> name;
    private final ByteSequence data;

    static BitcoinScriptOp fromCode(int opcode) {
        BitcoinScriptOp op = OP_BY_CODE.get(opcode);
        if (null == op) {
            op = new BitcoinScriptOp(opcode);
        }
        return op;
    }

    static BitcoinScriptOp fromData(ByteSequence barray) {
        return new BitcoinScriptOp(barray);
    }

    static BitcoinScriptOp fromData(byte[] array) {
        return new BitcoinScriptOp(ByteSequenceWrapper.build(array));
    }

    static BitcoinScriptOp fromCodeAndData(int opcode, ByteSequence barray) {
        if (null != barray) {
            if (isDataOpCode(opcode))
                return new BitcoinScriptOp(barray);
            else
                throw new IllegalArgumentException();
        } else {
            return new BitcoinScriptOp(opcode);
        }
    }

    private BitcoinScriptOp(int opcode) {
        if (isDataOpCode(opcode)) {
            throw new IllegalArgumentException("Cannot creat Script data object without data");
        }
        final BitcoinScriptOpName n = BitcoinScriptOpName.fromInt(opcode);
        this.name = Optional.ofNullable(n);
        this.opcode = opcode;
        this.data = ByteSequenceWrapper.EMPTY;
    }

    private BitcoinScriptOp(BitcoinScriptOpName name) {
        this.name = Optional.of(name);
        this.opcode = name.code();
        this.data = ByteSequenceWrapper.EMPTY;
    }

    public static boolean isDataOpCode(int code) {
        return code <= BitcoinScriptOpName.OP_PUSHDATA4.code();
    }

    private BitcoinScriptOp(ByteSequence byteArray) {
        final long size = byteArray.size();
        if (size < BitcoinScriptOpName.OP_PUSHDATA1.code()) {
            this.opcode = (int) size;
            this.name = Optional.empty();
        } else if (size <= 0xFF) {
            this.name = Optional.of(BitcoinScriptOpName.OP_PUSHDATA1);
            this.opcode = this.name.get().code();
        } else if (size <= 0xFFFF) {
            this.name = Optional.of(BitcoinScriptOpName.OP_PUSHDATA2);
            this.opcode = this.name.get().code();
        } else if (size <= 0xFFFF_FFFFL) {
            if (size > (Integer.MAX_VALUE - 8)) // Limit copied from ArrayList
                throw new OutOfMemoryError("Unsupported size");
            this.name = Optional.of(BitcoinScriptOpName.OP_PUSHDATA4);
            this.opcode = this.name.get().code();
        } else {
            throw new IllegalArgumentException("Cannot add data with length longer than usngined int");
        }
        this.data = byteArray;
    }

    public int opcode() {
        return this.opcode;
    }

    public Optional<BitcoinScriptOpName> name() {
        return this.name;
    }

    public ByteSequence data() {
        return this.data;
    }

    public long sizeInBytes() {
        long size = 1;    // opcode byte
        if (isDataOpCode(opcode)) {
            if (opcode == BitcoinScriptOpName.OP_PUSHDATA1.code())
                size += 1;
            else if (opcode == BitcoinScriptOpName.OP_PUSHDATA2.code())
                size += 2;
            else
                size += 4;
            size += data.size();
        }
        return size;
    }

    /*
    public void writeTo(OutputStream                      out,
                        BitcoinProtocolScriptOpNamespaces opNamespace)
    {
        BitcoinProtocolIO.writeByte(out, this.opcode, opNamespace);
        if (this.data.isPresent()) {
            if (BitcoinScriptOpName.OP_PUSHDATA1.code == this.opcode) {
                BitcoinProtocolIO.writeByte(out, data.get().size(), opNamespace.dataSize);
            } else if (BitcoinScriptOpName.OP_PUSHDATA2.code == this.opcode) {
                BitcoinProtocolIO.writeLittleEndianShort(out, data.get().size(), opNamespace.dataSize);
            } else if (BitcoinScriptOpName.OP_PUSHDATA4.code == this.opcode) {
                BitcoinProtocolIO.writeLittleEndianInt(out, data.get().size(), opNamespace.dataSize);
            }
            BitcoinProtocolIO.writeByteArray(out, data.get(), opNamespace.dataBody);
        }
    }

    public static BitcoinScriptOp readFrom(InputStream                       in,
                                                   BitcoinProtocolScriptOpNamespaces opNamespace)
    {
        final int opCodeValue = BitcoinProtocolIO.readByte(in, opNamespace);
        if (pushDataExpected(opCodeValue)) {
            return readOpCodeWithData(opCodeValue, in, opNamespace.dataSize, opNamespace.dataBody);
        }
        BitcoinScriptOp op = OP_BY_CODE.get(opCodeValue);
        if (null == op) {
            op = new BitcoinScriptOp(opCodeValue);
        }
        return op;
    }
    */

    static boolean pushDataExpected(int op) {
        return    op <= BitcoinScriptOpName.OP_PUSHDATA4.code()
               && op != BitcoinScriptOpName.OP_0.code();
    }

    /*
    private static BitcoinScriptOp readOpCodeWithData(int           readOpCode,
                                                          InputStream   in,
                                                          AmatNamespace opCodeDataSizeNamespace,
                                                          AmatNamespace opCodeDataBodyNamespace)
    {
        final int opDataSize = readOpDataSize(readOpCode, in, opCodeDataSizeNamespace);
        final ByteArrayWrapper opData = readBody(opDataSize, in, opCodeDataBodyNamespace);
        return new BitcoinScriptOp(opData);
    }

    private static int readOpDataSize(int           readOpCode,
                                      InputStream   in,
                                      AmatNamespace opCodeDataSizeNamespace) {
        if (readOpCode < BitcoinScriptOpName.OP_PUSHDATA1.code) {
            // Opcode value also represents data size.
            return readOpCode;
        } else if (readOpCode == BitcoinScriptOpName.OP_PUSHDATA1.code) {
            return BitcoinProtocolIO.readByte(in, opCodeDataSizeNamespace);
        } else if (readOpCode == BitcoinScriptOpName.OP_PUSHDATA2.code) {
            return BitcoinProtocolIO.readLittleEndianShort(in, opCodeDataSizeNamespace);
        } else if (readOpCode == BitcoinScriptOpName.OP_PUSHDATA4.code) {
            return (int) BitcoinProtocolIO.readLittleEndianInt(in, opCodeDataSizeNamespace);
        }
        return 0;
    }

    private static ByteArrayWrapper readBody(int dataSize, InputStream in, AmatNamespace namespace) {
        return BitcoinProtocolIO.readByteArrayWrapper(dataSize, in, namespace);
    }
    */

    // Static values #########################################################

    public final static BitcoinScriptOp OP_INVALIDOP = new BitcoinScriptOp(BitcoinScriptOpName.OP_INVALIDOP);
    public final static BitcoinScriptOp OP_FALSE = new BitcoinScriptOp(BitcoinScriptOpName.OP_FALSE);
    public final static BitcoinScriptOp OP_1NEGATE = new BitcoinScriptOp(BitcoinScriptOpName.OP_1NEGATE);
    public final static BitcoinScriptOp OP_1 = new BitcoinScriptOp(BitcoinScriptOpName.OP_1);
    public final static BitcoinScriptOp OP_TRUE = new BitcoinScriptOp(BitcoinScriptOpName.OP_TRUE);

    // Control
    public final static BitcoinScriptOp OP_NOP = new BitcoinScriptOp(BitcoinScriptOpName.OP_NOP);
    public final static BitcoinScriptOp OP_VER = new BitcoinScriptOp(BitcoinScriptOpName.OP_VER);
    public final static BitcoinScriptOp OP_IF = new BitcoinScriptOp(BitcoinScriptOpName.OP_IF);
    public final static BitcoinScriptOp OP_NOTIF = new BitcoinScriptOp(BitcoinScriptOpName.OP_NOTIF);
    public final static BitcoinScriptOp OP_VERIF = new BitcoinScriptOp(BitcoinScriptOpName.OP_VERIF);
    public final static BitcoinScriptOp OP_VERNOTIF = new BitcoinScriptOp(BitcoinScriptOpName.OP_VERNOTIF);
    public final static BitcoinScriptOp OP_ELSE = new BitcoinScriptOp(BitcoinScriptOpName.OP_ELSE);
    public final static BitcoinScriptOp OP_ENDIF = new BitcoinScriptOp(BitcoinScriptOpName.OP_ENDIF);
    public final static BitcoinScriptOp OP_VERIFY = new BitcoinScriptOp(BitcoinScriptOpName.OP_VERIFY);
    public final static BitcoinScriptOp OP_RETURN = new BitcoinScriptOp(BitcoinScriptOpName.OP_RETURN);

    // stack ops
    public final static BitcoinScriptOp OP_TOALTSTACK = new BitcoinScriptOp(BitcoinScriptOpName.OP_TOALTSTACK);
    public final static BitcoinScriptOp OP_FROMALTSTACK = new BitcoinScriptOp(BitcoinScriptOpName.OP_FROMALTSTACK);
    public final static BitcoinScriptOp OP_2DROP = new BitcoinScriptOp(BitcoinScriptOpName.OP_2DROP);
    public final static BitcoinScriptOp OP_2DUP = new BitcoinScriptOp(BitcoinScriptOpName.OP_2DUP);
    public final static BitcoinScriptOp OP_3DUP = new BitcoinScriptOp(BitcoinScriptOpName.OP_3DUP);
    public final static BitcoinScriptOp OP_2OVER = new BitcoinScriptOp(BitcoinScriptOpName.OP_2OVER);
    public final static BitcoinScriptOp OP_2ROT = new BitcoinScriptOp(BitcoinScriptOpName.OP_2ROT);
    public final static BitcoinScriptOp OP_2SWAP = new BitcoinScriptOp(BitcoinScriptOpName.OP_2SWAP);
    public final static BitcoinScriptOp OP_IFDUP = new BitcoinScriptOp(BitcoinScriptOpName.OP_IFDUP);
    public final static BitcoinScriptOp OP_DEPTH = new BitcoinScriptOp(BitcoinScriptOpName.OP_DEPTH);
    public final static BitcoinScriptOp OP_DROP = new BitcoinScriptOp(BitcoinScriptOpName.OP_DROP);
    public final static BitcoinScriptOp OP_DUP = new BitcoinScriptOp(BitcoinScriptOpName.OP_DUP);
    public final static BitcoinScriptOp OP_NIP = new BitcoinScriptOp(BitcoinScriptOpName.OP_NIP);
    public final static BitcoinScriptOp OP_OVER = new BitcoinScriptOp(BitcoinScriptOpName.OP_OVER);
    public final static BitcoinScriptOp OP_PICK = new BitcoinScriptOp(BitcoinScriptOpName.OP_PICK);
    public final static BitcoinScriptOp OP_ROLL = new BitcoinScriptOp(BitcoinScriptOpName.OP_ROLL);
    public final static BitcoinScriptOp OP_ROT = new BitcoinScriptOp(BitcoinScriptOpName.OP_ROT);
    public final static BitcoinScriptOp OP_SWAP = new BitcoinScriptOp(BitcoinScriptOpName.OP_SWAP);
    public final static BitcoinScriptOp OP_TUCK = new BitcoinScriptOp(BitcoinScriptOpName.OP_TUCK);

    // splice ops
    public final static BitcoinScriptOp OP_CAT = new BitcoinScriptOp(BitcoinScriptOpName.OP_CAT);
    public final static BitcoinScriptOp OP_SUBSTR = new BitcoinScriptOp(BitcoinScriptOpName.OP_SUBSTR);
    public final static BitcoinScriptOp OP_LEFT = new BitcoinScriptOp(BitcoinScriptOpName.OP_LEFT);
    public final static BitcoinScriptOp OP_RIGHT = new BitcoinScriptOp(BitcoinScriptOpName.OP_RIGHT);
    public final static BitcoinScriptOp OP_SIZE = new BitcoinScriptOp(BitcoinScriptOpName.OP_SIZE);

    // bit logic
    public final static BitcoinScriptOp OP_INVERT = new BitcoinScriptOp(BitcoinScriptOpName.OP_INVERT);
    public final static BitcoinScriptOp OP_AND = new BitcoinScriptOp(BitcoinScriptOpName.OP_AND);
    public final static BitcoinScriptOp OP_OR = new BitcoinScriptOp(BitcoinScriptOpName.OP_OR);
    public final static BitcoinScriptOp OP_XOR = new BitcoinScriptOp(BitcoinScriptOpName.OP_XOR);
    public final static BitcoinScriptOp OP_EQUAL = new BitcoinScriptOp(BitcoinScriptOpName.OP_EQUAL);
    public final static BitcoinScriptOp OP_EQUALVERIFY = new BitcoinScriptOp(BitcoinScriptOpName.OP_EQUALVERIFY);

    // numeric
    public final static BitcoinScriptOp OP_1ADD = new BitcoinScriptOp(BitcoinScriptOpName.OP_1ADD);
    public final static BitcoinScriptOp OP_1SUB = new BitcoinScriptOp(BitcoinScriptOpName.OP_1SUB);
    public final static BitcoinScriptOp OP_2MUL = new BitcoinScriptOp(BitcoinScriptOpName.OP_2MUL);
    public final static BitcoinScriptOp OP_2DIV = new BitcoinScriptOp(BitcoinScriptOpName.OP_2DIV);
    public final static BitcoinScriptOp OP_NEGATE = new BitcoinScriptOp(BitcoinScriptOpName.OP_NEGATE);
    public final static BitcoinScriptOp OP_ABS = new BitcoinScriptOp(BitcoinScriptOpName.OP_ABS);
    public final static BitcoinScriptOp OP_NOT = new BitcoinScriptOp(BitcoinScriptOpName.OP_NOT);
    public final static BitcoinScriptOp OP_0NOTEQUAL = new BitcoinScriptOp(BitcoinScriptOpName.OP_0NOTEQUAL);

    public final static BitcoinScriptOp OP_ADD = new BitcoinScriptOp(BitcoinScriptOpName.OP_ADD);
    public final static BitcoinScriptOp OP_SUB = new BitcoinScriptOp(BitcoinScriptOpName.OP_SUB);
    public final static BitcoinScriptOp OP_MUL = new BitcoinScriptOp(BitcoinScriptOpName.OP_MUL);
    public final static BitcoinScriptOp OP_DIV = new BitcoinScriptOp(BitcoinScriptOpName.OP_DIV);
    public final static BitcoinScriptOp OP_MOD = new BitcoinScriptOp(BitcoinScriptOpName.OP_MOD);
    public final static BitcoinScriptOp OP_LSHIFT = new BitcoinScriptOp(BitcoinScriptOpName.OP_LSHIFT);
    public final static BitcoinScriptOp OP_RSHIFT = new BitcoinScriptOp(BitcoinScriptOpName.OP_RSHIFT);

    public final static BitcoinScriptOp OP_BOOLAND = new BitcoinScriptOp(BitcoinScriptOpName.OP_BOOLAND);
    public final static BitcoinScriptOp OP_BOOLOR = new BitcoinScriptOp(BitcoinScriptOpName.OP_BOOLOR);
    public final static BitcoinScriptOp OP_NUMEQUAL = new BitcoinScriptOp(BitcoinScriptOpName.OP_NUMEQUAL);
    public final static BitcoinScriptOp OP_NUMEQUALVERIFY = new BitcoinScriptOp(BitcoinScriptOpName.OP_NUMEQUALVERIFY);
    public final static BitcoinScriptOp OP_NUMNOTEQUAL = new BitcoinScriptOp(BitcoinScriptOpName.OP_NUMNOTEQUAL);
    public final static BitcoinScriptOp OP_LESSTHAN = new BitcoinScriptOp(BitcoinScriptOpName.OP_LESSTHAN);
    public final static BitcoinScriptOp OP_GREATERTHAN = new BitcoinScriptOp(BitcoinScriptOpName.OP_GREATERTHAN);
    public final static BitcoinScriptOp OP_LESSTHANOREQUAL = new BitcoinScriptOp(BitcoinScriptOpName.OP_LESSTHANOREQUAL);
    public final static BitcoinScriptOp OP_GREATERTHANOREQUAL = new BitcoinScriptOp(BitcoinScriptOpName.OP_GREATERTHANOREQUAL);
    public final static BitcoinScriptOp OP_MIN = new BitcoinScriptOp(BitcoinScriptOpName.OP_MIN);
    public final static BitcoinScriptOp OP_MAX = new BitcoinScriptOp(BitcoinScriptOpName.OP_MAX);

    public final static BitcoinScriptOp OP_WITHIN = new BitcoinScriptOp(BitcoinScriptOpName.OP_WITHIN);

    // crypto
    public final static BitcoinScriptOp OP_RIPEMD160 = new BitcoinScriptOp(BitcoinScriptOpName.OP_RIPEMD160);
    public final static BitcoinScriptOp OP_SHA1 = new BitcoinScriptOp(BitcoinScriptOpName.OP_SHA1);
    public final static BitcoinScriptOp OP_SHA256 = new BitcoinScriptOp(BitcoinScriptOpName.OP_SHA256);
    public final static BitcoinScriptOp OP_HASH160 = new BitcoinScriptOp(BitcoinScriptOpName.OP_HASH160);
    public final static BitcoinScriptOp OP_HASH256 = new BitcoinScriptOp(BitcoinScriptOpName.OP_HASH256);
    public final static BitcoinScriptOp OP_CODESEPARATOR = new BitcoinScriptOp(BitcoinScriptOpName.OP_CODESEPARATOR);
    public final static BitcoinScriptOp OP_CHECKSIG = new BitcoinScriptOp(BitcoinScriptOpName.OP_CHECKSIG);
    public final static BitcoinScriptOp OP_CHECKSIGVERIFY = new BitcoinScriptOp(BitcoinScriptOpName.OP_CHECKSIGVERIFY);
    public final static BitcoinScriptOp OP_CHECKMULTISIG = new BitcoinScriptOp(BitcoinScriptOpName.OP_CHECKMULTISIG);
    public final static BitcoinScriptOp OP_CHECKMULTISIGVERIFY = new BitcoinScriptOp(BitcoinScriptOpName.OP_CHECKMULTISIGVERIFY);

    // expansion
    public final static BitcoinScriptOp OP_CHECKLOCKTIMEVERIFY = new BitcoinScriptOp(BitcoinScriptOpName.OP_CHECKLOCKTIMEVERIFY);

    public final static BitcoinScriptOp OP_CHECKSEQUENCEVERIFY = new BitcoinScriptOp(BitcoinScriptOpName.OP_CHECKSEQUENCEVERIFY);

    // template matching params
    public final static BitcoinScriptOp OP_SMALLINTEGER = new BitcoinScriptOp(BitcoinScriptOpName.OP_SMALLINTEGER);
    public final static BitcoinScriptOp OP_PUBKEYS = new BitcoinScriptOp(BitcoinScriptOpName.OP_PUBKEYS);
    public final static BitcoinScriptOp OP_PUBKEYHASH = new BitcoinScriptOp(BitcoinScriptOpName.OP_PUBKEYHASH);
    public final static BitcoinScriptOp OP_PUBKEY = new BitcoinScriptOp(BitcoinScriptOpName.OP_PUBKEY);

    private static final Map<Integer, BitcoinScriptOp> OP_BY_CODE;

    static {
        OP_BY_CODE = new HashMap<>();
        OP_BY_CODE.put(OP_INVALIDOP.opcode, OP_INVALIDOP);
        OP_BY_CODE.put(OP_FALSE.opcode, OP_FALSE);
        OP_BY_CODE.put(OP_1NEGATE.opcode, OP_1NEGATE);
        OP_BY_CODE.put(OP_1.opcode, OP_1);
        OP_BY_CODE.put(OP_TRUE.opcode, OP_TRUE);

        // Control
        OP_BY_CODE.put(OP_NOP.opcode, OP_NOP);
        OP_BY_CODE.put(OP_VER.opcode, OP_VER);
        OP_BY_CODE.put(OP_IF.opcode, OP_IF);
        OP_BY_CODE.put(OP_NOTIF.opcode, OP_NOTIF);
        OP_BY_CODE.put(OP_VERIF.opcode, OP_VERIF);
        OP_BY_CODE.put(OP_VERNOTIF.opcode, OP_VERNOTIF);
        OP_BY_CODE.put(OP_ELSE.opcode, OP_ELSE);
        OP_BY_CODE.put(OP_ENDIF.opcode, OP_ENDIF);
        OP_BY_CODE.put(OP_VERIFY.opcode, OP_VERIFY);
        OP_BY_CODE.put(OP_RETURN.opcode, OP_RETURN);

        // stack ops
        OP_BY_CODE.put(OP_TOALTSTACK.opcode, OP_TOALTSTACK);
        OP_BY_CODE.put(OP_FROMALTSTACK.opcode, OP_FROMALTSTACK);
        OP_BY_CODE.put(OP_2DROP.opcode, OP_2DROP);
        OP_BY_CODE.put(OP_2DUP.opcode, OP_2DUP);
        OP_BY_CODE.put(OP_3DUP.opcode, OP_3DUP);
        OP_BY_CODE.put(OP_2OVER.opcode, OP_2OVER);
        OP_BY_CODE.put(OP_2ROT.opcode, OP_2ROT);
        OP_BY_CODE.put(OP_2SWAP.opcode, OP_2SWAP);
        OP_BY_CODE.put(OP_IFDUP.opcode, OP_IFDUP);
        OP_BY_CODE.put(OP_DEPTH.opcode, OP_DEPTH);
        OP_BY_CODE.put(OP_DROP.opcode, OP_DROP);
        OP_BY_CODE.put(OP_DUP.opcode, OP_DUP);
        OP_BY_CODE.put(OP_NIP.opcode, OP_NIP);
        OP_BY_CODE.put(OP_OVER.opcode, OP_OVER);
        OP_BY_CODE.put(OP_PICK.opcode, OP_PICK);
        OP_BY_CODE.put(OP_ROLL.opcode, OP_ROLL);
        OP_BY_CODE.put(OP_ROT.opcode, OP_ROT);
        OP_BY_CODE.put(OP_SWAP.opcode, OP_SWAP);
        OP_BY_CODE.put(OP_TUCK.opcode, OP_TUCK);

        // splice ops
        OP_BY_CODE.put(OP_CAT.opcode, OP_CAT);
        OP_BY_CODE.put(OP_SUBSTR.opcode, OP_SUBSTR);
        OP_BY_CODE.put(OP_LEFT.opcode, OP_LEFT);
        OP_BY_CODE.put(OP_RIGHT.opcode, OP_RIGHT);
        OP_BY_CODE.put(OP_SIZE.opcode, OP_SIZE);

        // bit logic
        OP_BY_CODE.put(OP_INVERT.opcode, OP_INVERT);
        OP_BY_CODE.put(OP_AND.opcode, OP_AND);
        OP_BY_CODE.put(OP_OR.opcode, OP_OR);
        OP_BY_CODE.put(OP_XOR.opcode, OP_XOR);
        OP_BY_CODE.put(OP_EQUAL.opcode, OP_EQUAL);
        OP_BY_CODE.put(OP_EQUALVERIFY.opcode, OP_EQUALVERIFY);

        // numeric
        OP_BY_CODE.put(OP_1ADD.opcode, OP_1ADD);
        OP_BY_CODE.put(OP_1SUB.opcode, OP_1SUB);
        OP_BY_CODE.put(OP_2MUL.opcode, OP_2MUL);
        OP_BY_CODE.put(OP_2DIV.opcode, OP_2DIV);
        OP_BY_CODE.put(OP_NEGATE.opcode, OP_NEGATE);
        OP_BY_CODE.put(OP_ABS.opcode, OP_ABS);
        OP_BY_CODE.put(OP_NOT.opcode, OP_NOT);
        OP_BY_CODE.put(OP_0NOTEQUAL.opcode, OP_0NOTEQUAL);

        OP_BY_CODE.put(OP_ADD.opcode, OP_ADD);
        OP_BY_CODE.put(OP_SUB.opcode, OP_SUB);
        OP_BY_CODE.put(OP_MUL.opcode, OP_MUL);
        OP_BY_CODE.put(OP_DIV.opcode, OP_DIV);
        OP_BY_CODE.put(OP_MOD.opcode, OP_MOD);
        OP_BY_CODE.put(OP_LSHIFT.opcode, OP_LSHIFT);
        OP_BY_CODE.put(OP_RSHIFT.opcode, OP_RSHIFT);

        OP_BY_CODE.put(OP_BOOLAND.opcode, OP_BOOLAND);
        OP_BY_CODE.put(OP_BOOLOR.opcode, OP_BOOLOR);
        OP_BY_CODE.put(OP_NUMEQUAL.opcode, OP_NUMEQUAL);
        OP_BY_CODE.put(OP_NUMEQUALVERIFY.opcode, OP_NUMEQUALVERIFY);
        OP_BY_CODE.put(OP_NUMNOTEQUAL.opcode, OP_NUMNOTEQUAL);
        OP_BY_CODE.put(OP_LESSTHAN.opcode, OP_LESSTHAN);
        OP_BY_CODE.put(OP_GREATERTHAN.opcode, OP_GREATERTHAN);
        OP_BY_CODE.put(OP_LESSTHANOREQUAL.opcode, OP_LESSTHANOREQUAL);
        OP_BY_CODE.put(OP_GREATERTHANOREQUAL.opcode, OP_GREATERTHANOREQUAL);
        OP_BY_CODE.put(OP_MIN.opcode, OP_MIN);
        OP_BY_CODE.put(OP_MAX.opcode, OP_MAX);

        OP_BY_CODE.put(OP_WITHIN.opcode, OP_WITHIN);

        // crypto
        OP_BY_CODE.put(OP_RIPEMD160.opcode, OP_RIPEMD160);
        OP_BY_CODE.put(OP_SHA1.opcode, OP_SHA1);
        OP_BY_CODE.put(OP_SHA256.opcode, OP_SHA256);
        OP_BY_CODE.put(OP_HASH160.opcode, OP_HASH160);
        OP_BY_CODE.put(OP_HASH256.opcode, OP_HASH256);
        OP_BY_CODE.put(OP_CODESEPARATOR.opcode, OP_CODESEPARATOR);
        OP_BY_CODE.put(OP_CHECKSIG.opcode, OP_CHECKSIG);
        OP_BY_CODE.put(OP_CHECKSIGVERIFY.opcode, OP_CHECKSIGVERIFY);
        OP_BY_CODE.put(OP_CHECKMULTISIG.opcode, OP_CHECKMULTISIG);
        OP_BY_CODE.put(OP_CHECKMULTISIGVERIFY.opcode, OP_CHECKMULTISIGVERIFY);

        // expansion
        OP_BY_CODE.put(OP_CHECKLOCKTIMEVERIFY.opcode, OP_CHECKLOCKTIMEVERIFY);

        OP_BY_CODE.put(OP_CHECKSEQUENCEVERIFY.opcode, OP_CHECKSEQUENCEVERIFY);

        // template matching params
        OP_BY_CODE.put(OP_SMALLINTEGER.opcode, OP_SMALLINTEGER);
        OP_BY_CODE.put(OP_PUBKEYS.opcode, OP_PUBKEYS);
        OP_BY_CODE.put(OP_PUBKEYHASH.opcode, OP_PUBKEYHASH);
        OP_BY_CODE.put(OP_PUBKEY.opcode, OP_PUBKEY);
    }

}
