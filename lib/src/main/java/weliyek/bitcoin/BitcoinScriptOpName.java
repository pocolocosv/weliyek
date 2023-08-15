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

public enum BitcoinScriptOpName {
    // push value
    OP_0        (0x00),
    OP_FALSE    (0x00),

    OP_PUSHDATA1(0x4c),
    OP_PUSHDATA2(0x4d),
    OP_PUSHDATA4(0x4e),
    OP_1NEGATE  (0x4f),
    OP_RESERVED (0x50),

    OP_1   (0x51),
    OP_TRUE(OP_1),

    OP_2 (0x52),
    OP_3 (0x53),
    OP_4 (0x54),
    OP_5 (0x55),
    OP_6 (0x56),
    OP_7 (0x57),
    OP_8 (0x58),
    OP_9 (0x59),
    OP_10(0x5a),
    OP_11(0x5b),
    OP_12(0x5c),
    OP_13(0x5d),
    OP_14(0x5e),
    OP_15(0x5f),
    OP_16(0x60),

    // control
    OP_NOP     (0x61),
    OP_VER     (0x62),
    OP_IF      (0x63),
    OP_NOTIF   (0x64),
    OP_VERIF   (0x65),
    OP_VERNOTIF(0x66),
    OP_ELSE    (0x67),
    OP_ENDIF   (0x68),
    OP_VERIFY  (0x69),
    OP_RETURN  (0x6a),

    // stack ops
    OP_TOALTSTACK(0x6b),
    OP_FROMALTSTACK(0x6c),
    OP_2DROP(0x6d),
    OP_2DUP(0x6e),
    OP_3DUP(0x6f),
    OP_2OVER(0x70),
    OP_2ROT(0x71),
    OP_2SWAP(0x72),
    OP_IFDUP(0x73),
    OP_DEPTH(0x74),
    OP_DROP(0x75),
    OP_DUP(0x76),
    OP_NIP(0x77),
    OP_OVER(0x78),
    OP_PICK(0x79),
    OP_ROLL(0x7a),
    OP_ROT(0x7b),
    OP_SWAP(0x7c),
    OP_TUCK(0x7d),

    // splice ops
    OP_CAT(0x7e),
    OP_SUBSTR(0x7f),
    OP_LEFT(0x80),
    OP_RIGHT(0x81),
    OP_SIZE(0x82),

    // bit logic
    OP_INVERT(0x83),
    OP_AND(0x84),
    OP_OR(0x85),
    OP_XOR(0x86),
    OP_EQUAL(0x87),
    OP_EQUALVERIFY(0x88),
    OP_RESERVED1(0x89),
    OP_RESERVED2(0x8a),

    // numeric
    OP_1ADD(0x8b),
    OP_1SUB(0x8c),
    OP_2MUL(0x8d),
    OP_2DIV(0x8e),
    OP_NEGATE(0x8f),
    OP_ABS(0x90),
    OP_NOT(0x91),
    OP_0NOTEQUAL(0x92),

    OP_ADD(0x93),
    OP_SUB(0x94),
    OP_MUL(0x95),
    OP_DIV(0x96),
    OP_MOD(0x97),
    OP_LSHIFT(0x98),
    OP_RSHIFT(0x99),

    OP_BOOLAND(0x9a),
    OP_BOOLOR(0x9b),
    OP_NUMEQUAL(0x9c),
    OP_NUMEQUALVERIFY(0x9d),
    OP_NUMNOTEQUAL(0x9e),
    OP_LESSTHAN(0x9f),
    OP_GREATERTHAN(0xa0),
    OP_LESSTHANOREQUAL(0xa1),
    OP_GREATERTHANOREQUAL(0xa2),
    OP_MIN(0xa3),
    OP_MAX(0xa4),

    OP_WITHIN(0xa5),

    // crypto
    OP_RIPEMD160(0xa6),
    OP_SHA1(0xa7),
    OP_SHA256(0xa8),
    OP_HASH160(0xa9),
    OP_HASH256(0xaa),
    OP_CODESEPARATOR(0xab),
    OP_CHECKSIG(0xac),
    OP_CHECKSIGVERIFY(0xad),
    OP_CHECKMULTISIG(0xae),
    OP_CHECKMULTISIGVERIFY(0xaf),

    // expansion
    OP_NOP1(0xb0),

    OP_CHECKLOCKTIMEVERIFY(0xb1),
    OP_NOP2               (0xb1),

    OP_CHECKSEQUENCEVERIFY(0xb2),
    OP_NOP3               (0xb2),

    OP_NOP4(0xb3),
    OP_NOP5(0xb4),
    OP_NOP6(0xb5),
    OP_NOP7(0xb6),
    OP_NOP8(0xb7),
    OP_NOP9(0xb8),
    OP_NOP10(0xb9),


    // template matching params
    OP_SMALLINTEGER(0xfa),
    OP_PUBKEYS(0xfb),
    OP_PUBKEYHASH(0xfd),
    OP_PUBKEY(0xfe),

    OP_INVALIDOP(0xff);

    private int code;

    private static final Map<Integer, BitcoinScriptOpName> NAME_BY_CODE;

    static {
        NAME_BY_CODE = new HashMap<>();
        NAME_BY_CODE.put(OP_FALSE.code,        OP_FALSE);
        NAME_BY_CODE.put(OP_PUSHDATA1.code,    OP_PUSHDATA1);
        NAME_BY_CODE.put(OP_PUSHDATA2.code,    OP_PUSHDATA2);
        NAME_BY_CODE.put(OP_PUSHDATA4.code,    OP_PUSHDATA4);
        NAME_BY_CODE.put(OP_1NEGATE.code,      OP_1NEGATE);
        NAME_BY_CODE.put(OP_RESERVED.code,     OP_RESERVED);
        NAME_BY_CODE.put(OP_TRUE.code,         OP_TRUE);
        NAME_BY_CODE.put(OP_2.code,            OP_2);
        NAME_BY_CODE.put(OP_3.code,            OP_3);
        NAME_BY_CODE.put(OP_4.code,            OP_4);
        NAME_BY_CODE.put(OP_5.code,            OP_5);
        NAME_BY_CODE.put(OP_6.code,            OP_6);
        NAME_BY_CODE.put(OP_7.code,            OP_7);
        NAME_BY_CODE.put(OP_8.code,            OP_8);
        NAME_BY_CODE.put(OP_9.code,            OP_9);
        NAME_BY_CODE.put(OP_10.code,           OP_10);
        NAME_BY_CODE.put(OP_NOP.code,          OP_NOP);
        NAME_BY_CODE.put(OP_IF.code,           OP_IF);
        NAME_BY_CODE.put(OP_NOTIF.code,        OP_NOTIF);
        NAME_BY_CODE.put(OP_VERIF.code,        OP_VERIF);
        NAME_BY_CODE.put(OP_VERNOTIF.code,     OP_VERNOTIF);
        NAME_BY_CODE.put(OP_ELSE.code,         OP_ELSE);
        NAME_BY_CODE.put(OP_ENDIF.code,        OP_ENDIF);
        NAME_BY_CODE.put(OP_VERIFY.code,       OP_VERIFY);
        NAME_BY_CODE.put(OP_RETURN.code,       OP_RETURN);
        NAME_BY_CODE.put(OP_TOALTSTACK.code,   OP_TOALTSTACK);
        NAME_BY_CODE.put(OP_FROMALTSTACK.code, OP_FROMALTSTACK);
        NAME_BY_CODE.put(OP_2DROP.code,        OP_2DROP);
        NAME_BY_CODE.put(OP_2DUP.code,         OP_2DUP);
        NAME_BY_CODE.put(OP_3DUP.code,         OP_3DUP);
        NAME_BY_CODE.put(OP_2OVER.code,        OP_2OVER);
        NAME_BY_CODE.put(OP_2ROT.code,         OP_2ROT);
        NAME_BY_CODE.put(OP_2SWAP.code,        OP_2SWAP);
        NAME_BY_CODE.put(OP_IFDUP.code,        OP_IFDUP);
        NAME_BY_CODE.put(OP_DEPTH.code,        OP_DEPTH);
        NAME_BY_CODE.put(OP_DROP.code,         OP_DROP);
        NAME_BY_CODE.put(OP_DUP.code,          OP_DUP);
        NAME_BY_CODE.put(OP_NIP.code,          OP_NIP);
        NAME_BY_CODE.put(OP_OVER.code,         OP_OVER);
        NAME_BY_CODE.put(OP_PICK.code,         OP_PICK);
        NAME_BY_CODE.put(OP_ROLL.code,         OP_ROLL);
        NAME_BY_CODE.put(OP_ROT.code,          OP_ROT);
        NAME_BY_CODE.put(OP_SWAP.code,         OP_SWAP);
        NAME_BY_CODE.put(OP_TUCK.code,         OP_TUCK);
        NAME_BY_CODE.put(OP_CAT.code,          OP_CAT);
        NAME_BY_CODE.put(OP_SUBSTR.code,       OP_SUBSTR);
        NAME_BY_CODE.put(OP_LEFT.code,         OP_LEFT);
        NAME_BY_CODE.put(OP_RIGHT.code,        OP_RIGHT);
        NAME_BY_CODE.put(OP_SIZE.code,         OP_SIZE);
        NAME_BY_CODE.put(OP_INVERT.code,       OP_INVERT);
        NAME_BY_CODE.put(OP_AND.code,          OP_AND);
        NAME_BY_CODE.put(OP_OR.code,           OP_OR);
        NAME_BY_CODE.put(OP_XOR.code,          OP_XOR);
        NAME_BY_CODE.put(OP_EQUAL.code,        OP_EQUAL);
        NAME_BY_CODE.put(OP_EQUALVERIFY.code,  OP_EQUALVERIFY);
        NAME_BY_CODE.put(OP_RESERVED1.code,    OP_RESERVED1);
        NAME_BY_CODE.put(OP_RESERVED2.code,    OP_RESERVED2);
        NAME_BY_CODE.put(OP_1ADD.code,         OP_1ADD);
        NAME_BY_CODE.put(OP_1SUB.code,         OP_1SUB);
        NAME_BY_CODE.put(OP_2MUL.code,         OP_2MUL);
        NAME_BY_CODE.put(OP_2DIV.code,         OP_2DIV);
        NAME_BY_CODE.put(OP_NEGATE.code,       OP_NEGATE);
        NAME_BY_CODE.put(OP_ABS.code,          OP_ABS);
        NAME_BY_CODE.put(OP_NOT.code,          OP_NOT);
        NAME_BY_CODE.put(OP_0NOTEQUAL.code,    OP_0NOTEQUAL);
        NAME_BY_CODE.put(OP_ADD.code,          OP_ADD);
        NAME_BY_CODE.put(OP_SUB.code,          OP_SUB);
        NAME_BY_CODE.put(OP_MUL.code,          OP_MUL);
        NAME_BY_CODE.put(OP_DIV.code,          OP_DIV);
        NAME_BY_CODE.put(OP_MOD.code,          OP_MOD);
        NAME_BY_CODE.put(OP_LSHIFT.code,       OP_LSHIFT);
        NAME_BY_CODE.put(OP_RSHIFT.code,       OP_RSHIFT);
        NAME_BY_CODE.put(OP_BOOLAND.code,      OP_BOOLAND);
        NAME_BY_CODE.put(OP_BOOLOR.code,       OP_BOOLOR);
        NAME_BY_CODE.put(OP_NUMEQUAL.code,     OP_NUMEQUAL);
        NAME_BY_CODE.put(OP_NUMEQUALVERIFY.code,      OP_NUMEQUALVERIFY);
        NAME_BY_CODE.put(OP_NUMNOTEQUAL.code,         OP_NUMNOTEQUAL);
        NAME_BY_CODE.put(OP_LESSTHAN.code,            OP_LESSTHAN);
        NAME_BY_CODE.put(OP_GREATERTHAN.code,         OP_GREATERTHAN);
        NAME_BY_CODE.put(OP_LESSTHANOREQUAL.code,     OP_LESSTHANOREQUAL);
        NAME_BY_CODE.put(OP_GREATERTHANOREQUAL.code,  OP_GREATERTHANOREQUAL);
        NAME_BY_CODE.put(OP_MIN.code,                 OP_MIN);
        NAME_BY_CODE.put(OP_MAX.code,                 OP_MAX);
        NAME_BY_CODE.put(OP_WITHIN.code,              OP_WITHIN);
        NAME_BY_CODE.put(OP_RIPEMD160.code,           OP_RIPEMD160);
        NAME_BY_CODE.put(OP_SHA1.code,                OP_SHA1);
        NAME_BY_CODE.put(OP_SHA256.code,              OP_SHA256);
        NAME_BY_CODE.put(OP_HASH160.code,             OP_HASH160);
        NAME_BY_CODE.put(OP_HASH256.code,             OP_HASH256);
        NAME_BY_CODE.put(OP_CODESEPARATOR.code,       OP_CODESEPARATOR);
        NAME_BY_CODE.put(OP_CHECKSIG.code,            OP_CHECKSIG);
        NAME_BY_CODE.put(OP_CHECKSIGVERIFY.code,      OP_CHECKSIGVERIFY);
        NAME_BY_CODE.put(OP_CHECKMULTISIG.code,       OP_CHECKMULTISIG);
        NAME_BY_CODE.put(OP_CHECKMULTISIGVERIFY.code, OP_CHECKMULTISIGVERIFY);
        NAME_BY_CODE.put(OP_NOP1.code,                OP_NOP1);
        NAME_BY_CODE.put(OP_CHECKLOCKTIMEVERIFY.code, OP_CHECKLOCKTIMEVERIFY);
        NAME_BY_CODE.put(OP_CHECKSEQUENCEVERIFY.code, OP_CHECKSEQUENCEVERIFY);
        NAME_BY_CODE.put(OP_NOP4.code,                OP_NOP4);
        NAME_BY_CODE.put(OP_NOP5.code,                OP_NOP5);
        NAME_BY_CODE.put(OP_NOP6.code,                OP_NOP6);
        NAME_BY_CODE.put(OP_NOP7.code,                OP_NOP7);
        NAME_BY_CODE.put(OP_NOP8.code,                OP_NOP8);
        NAME_BY_CODE.put(OP_NOP9.code,                OP_NOP9);
        NAME_BY_CODE.put(OP_NOP10.code,               OP_NOP10);
        NAME_BY_CODE.put(OP_SMALLINTEGER.code,        OP_SMALLINTEGER);
        NAME_BY_CODE.put(OP_PUBKEYS.code,             OP_PUBKEYS);
        NAME_BY_CODE.put(OP_PUBKEYHASH.code,          OP_PUBKEYHASH);
        NAME_BY_CODE.put(OP_PUBKEY.code,              OP_PUBKEY);
        NAME_BY_CODE.put(OP_INVALIDOP.code,       OP_INVALIDOP);
    }

    private BitcoinScriptOpName(int b) {
        this.code = b;
    }

    private BitcoinScriptOpName(BitcoinScriptOpName o) {
        this.code = o.code;
    }

    public static BitcoinScriptOpName fromInt(Integer val) {
        return NAME_BY_CODE.get(val);
    }

    public int code() {
        return this.code;
    }

}
