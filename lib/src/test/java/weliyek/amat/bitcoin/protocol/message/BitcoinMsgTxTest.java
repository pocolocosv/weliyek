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
package weliyek.amat.bitcoin.protocol.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import weliyek.amat.base.protocol.TestAmatInfo;
import weliyek.amat.bitcoin.protocol.BitcoinConfig;
import weliyek.amat.bitcoin.protocol.BitcoinMessageMagicName;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolScriptAbstract;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgTxRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgTxRW;
import weliyek.bitcoin.BitcoinCommandName;
import weliyek.bitcoin.BitcoinHash;
import weliyek.bitcoin.protocol.tx.BitcoinTxInRO;
import weliyek.bitcoin.protocol.tx.BitcoinTxOutRO;
import weliyek.ketza.base.composite.lineage.Prospective;

public class BitcoinMsgTxTest
{

    /*
     * The data used to create these arrays was obtained from
     * https://en.bitcoin.it/wiki/Protocol_documentation
     */

    static final byte[] ARRAY_MSG_MAGIC = new byte[] {
            (byte)0xF9, (byte)0xBE, (byte)0xB4, (byte)0xD9
    };

    static final byte[] ARRAY_MSG_COMMAND = new byte[] {
            (byte)0x74, (byte)0x78, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    static final byte[] ARRAY_MSG_PAYLOAD_SIZE = new byte[] {
            (byte)0x02, (byte)0x01, (byte)0x00, (byte)0x00
    };

    static final byte[] ARRAY_MSG_PAYLOAD_CHECKSUM = new byte[] {
            (byte)0xE2, (byte)0x93, (byte)0xCD, (byte)0xBE
    };

    static final byte[] ARRAY_MSG_TX_VERSION = new byte[] {
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00 // tx version
    };

    static final byte[] ARRAY_MSG_TX_TXIN_NUM = new byte[] {
            (byte)0x01 // number of transaction inputs
    };

    static final byte[] ARRAY_MSG_TX_TXIN1_OUTPOINT_HASH = new byte[] {
            (byte)0x6D, (byte)0xBD, (byte)0xDB, (byte)0x08, (byte)0x5B, (byte)0x1D, (byte)0x8A, (byte)0xF7,
            (byte)0x51, (byte)0x84, (byte)0xF0, (byte)0xBC, (byte)0x01, (byte)0xFA, (byte)0xD5, (byte)0x8D,
            (byte)0x12, (byte)0x66, (byte)0xE9, (byte)0xB6, (byte)0x3B, (byte)0x50, (byte)0x88, (byte)0x19,
            (byte)0x90, (byte)0xE4, (byte)0xB4, (byte)0x0D, (byte)0x6A, (byte)0xEE, (byte)0x36, (byte)0x29
    };

    static final byte[] ARRAY_MSG_TX_TXIN1_OUTPOINT_INDEX = new byte[] {
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    static final byte[] ARRAY_MSG_TX_TXIN1_SCRIPT_SIZE = new byte[] {
            (byte)0x8B, // input 1: script size
    };

    static final byte[] ARRAY_MSG_TX_TXIN1_SCRIPT = new byte[] {
            (byte)0x48, (byte)0x30, (byte)0x45, (byte)0x02, (byte)0x21, (byte)0x00, (byte)0xF3, (byte)0x58,
            (byte)0x1E, (byte)0x19, (byte)0x72, (byte)0xAE, (byte)0x8A, (byte)0xC7, (byte)0xC7, (byte)0x36,
            (byte)0x7A, (byte)0x7A, (byte)0x25, (byte)0x3B, (byte)0xC1, (byte)0x13, (byte)0x52, (byte)0x23,
            (byte)0xAD, (byte)0xB9, (byte)0xA4, (byte)0x68, (byte)0xBB, (byte)0x3A, (byte)0x59, (byte)0x23,
            (byte)0x3F, (byte)0x45, (byte)0xBC, (byte)0x57, (byte)0x83, (byte)0x80, (byte)0x02, (byte)0x20,
            (byte)0x59, (byte)0xAF, (byte)0x01, (byte)0xCA, (byte)0x17, (byte)0xD0, (byte)0x0E, (byte)0x41,
            (byte)0x83, (byte)0x7A, (byte)0x1D, (byte)0x58, (byte)0xE9, (byte)0x7A, (byte)0xA3, (byte)0x1B,
            (byte)0xAE, (byte)0x58, (byte)0x4E, (byte)0xDE, (byte)0xC2, (byte)0x8D, (byte)0x35, (byte)0xBD,
            (byte)0x96, (byte)0x92, (byte)0x36, (byte)0x90, (byte)0x91, (byte)0x3B, (byte)0xAE, (byte)0x9A,
            (byte)0x01, (byte)0x41, (byte)0x04, (byte)0x9C, (byte)0x02, (byte)0xBF, (byte)0xC9, (byte)0x7E,
            (byte)0xF2, (byte)0x36, (byte)0xCE, (byte)0x6D, (byte)0x8F, (byte)0xE5, (byte)0xD9, (byte)0x40,
            (byte)0x13, (byte)0xC7, (byte)0x21, (byte)0xE9, (byte)0x15, (byte)0x98, (byte)0x2A, (byte)0xCD,
            (byte)0x2B, (byte)0x12, (byte)0xB6, (byte)0x5D, (byte)0x9B, (byte)0x7D, (byte)0x59, (byte)0xE2,
            (byte)0x0A, (byte)0x84, (byte)0x20, (byte)0x05, (byte)0xF8, (byte)0xFC, (byte)0x4E, (byte)0x02,
            (byte)0x53, (byte)0x2E, (byte)0x87, (byte)0x3D, (byte)0x37, (byte)0xB9, (byte)0x6F, (byte)0x09,
            (byte)0xD6, (byte)0xD4, (byte)0x51, (byte)0x1A, (byte)0xDA, (byte)0x8F, (byte)0x14, (byte)0x04,
            (byte)0x2F, (byte)0x46, (byte)0x61, (byte)0x4A, (byte)0x4C, (byte)0x70, (byte)0xC0, (byte)0xF1,
            (byte)0x4B, (byte)0xEF, (byte)0xF5 // input 1: signature script
    };

    static final byte[] ARRAY_MSG_TX_TXIN1_SEQUENCE = new byte[] {
            (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF, // input 1 sequence
    };

    static final byte[] ARRAY_MSG_TX_TXOUT_NUM = new byte[] {
            (byte)0x02, // number of output transactions
    };

    static final byte[] ARRAY_MSG_TX_TXOUT1_VALUE = new byte[] {
            // output 1: value 0.05 BTC (5000000)
            (byte)0x40, (byte)0x4B, (byte)0x4C, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
    };

    static final byte[] ARRAY_MSG_TX_TXOUT1_SCRIPT_SIZE = new byte[] {
            (byte)0x19, // output 1: script size
    };

    static final byte[] ARRAY_MSG_TX_TXOUT1_SCRIPT = new byte[] {
            (byte)0x76, (byte)0xA9, (byte)0x14, (byte)0x1A, (byte)0xA0, (byte)0xCD, (byte)0x1C, (byte)0xBE,
            (byte)0xA6, (byte)0xE7, (byte)0x45, (byte)0x8A, (byte)0x7A, (byte)0xBA, (byte)0xD5, (byte)0x12,
            (byte)0xA9, (byte)0xD9, (byte)0xEA, (byte)0x1A, (byte)0xFB, (byte)0x22, (byte)0x5E, (byte)0x88,
            (byte)0xAC, // output 1: script
    };

    static final byte[] ARRAY_MSG_TX_TXOUT2_VALUE = new byte[] {
            // output 2: value 33.54 BTC (3354000000)
            (byte)0x80, (byte)0xFA, (byte)0xE9, (byte)0xC7, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
    };

    static final byte[] ARRAY_MSG_TX_TXOUT2_SCRIPT_SIZE = new byte[] {
            (byte)0x19, // output2 : script size
    };

    static final byte[] ARRAY_MSG_TX_TXOUT2_SCRIPT = new byte[] {
            (byte)0x76, (byte)0xA9, (byte)0x14, (byte)0x0E, (byte)0xAB, (byte)0x5B, (byte)0xEA, (byte)0x43,
            (byte)0x6A, (byte)0x04, (byte)0x84, (byte)0xCF, (byte)0xAB, (byte)0x12, (byte)0x48, (byte)0x5E,
            (byte)0xFD, (byte)0xA0, (byte)0xB7, (byte)0x8B, (byte)0x4E, (byte)0xCC, (byte)0x52, (byte)0x88,
            (byte)0xAC, // output 2: script
    };

    static final byte[] ARRAY_MSG_TX_LOCKTIME = new byte[] {
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00 // locktime
    };

    static final byte[] ARRAY_MSG;

    public static final BitcoinMessageMagicName TX_MAGIC;
    public static final BitcoinCommandName TX_COMMAND;
    public static final    int TX_PAYLOAD_CHECKSUM;
    public static final    int TX_TXVERSION;
    public static final    int TX_TXIN_NUM;
    public static final BitcoinHash TX_TXIN_OUTPOINT_HASH;
    public static final    int TX_TXIN_OUTPOINT_INDEX;
    public static final    int TX_TXIN_SCRIPT_SIZE;
    public static final BitcoinProtocolScriptAbstract TX_TXIN_SCRIPT;
    public static final   long TX_TXIN_SEQUENCE;
    public static final    int TX_TXOUT_NUM;
    public static final   long TX_TXOUT1_VALUE;
    public static final BitcoinProtocolScriptAbstract TX_TXOUT1_SCRIPT;
    public static final   long TX_TXOUT2_VALUE;
    public static final BitcoinProtocolScriptAbstract TX_TXOUT2_SCRIPT;
    public static final   long TX_LOCKTIME;

    static {
        ARRAY_MSG = TestUtils.joinArrays(ARRAY_MSG_MAGIC,
                                         ARRAY_MSG_COMMAND,
                                         ARRAY_MSG_PAYLOAD_SIZE, ARRAY_MSG_PAYLOAD_CHECKSUM,
                                         ARRAY_MSG_TX_VERSION,
                                         ARRAY_MSG_TX_TXIN_NUM,
                                         ARRAY_MSG_TX_TXIN1_OUTPOINT_HASH,
                                         ARRAY_MSG_TX_TXIN1_OUTPOINT_INDEX,
                                         ARRAY_MSG_TX_TXIN1_SCRIPT_SIZE,
                                         ARRAY_MSG_TX_TXIN1_SCRIPT,
                                         ARRAY_MSG_TX_TXIN1_SEQUENCE,
                                         ARRAY_MSG_TX_TXOUT_NUM,
                                         ARRAY_MSG_TX_TXOUT1_VALUE,
                                         ARRAY_MSG_TX_TXOUT1_SCRIPT_SIZE,
                                         ARRAY_MSG_TX_TXOUT1_SCRIPT,
                                         ARRAY_MSG_TX_TXOUT2_VALUE,
                                         ARRAY_MSG_TX_TXOUT2_SCRIPT_SIZE,
                                         ARRAY_MSG_TX_TXOUT2_SCRIPT,
                                         ARRAY_MSG_TX_LOCKTIME);

        TX_MAGIC = TestUtils.toMagic(ARRAY_MSG_MAGIC);
        TX_COMMAND = TestUtils.toCommand(ARRAY_MSG_COMMAND);
        TX_PAYLOAD_CHECKSUM = TestUtils.toSignedInt(ARRAY_MSG_PAYLOAD_CHECKSUM);
        TX_TXVERSION = TestUtils.toSignedInt(ARRAY_MSG_TX_VERSION);
        TX_TXIN_NUM = TestUtils.toVarInt(ARRAY_MSG_TX_TXIN_NUM, TestAmatInfo.newNamespace()).intValue();
        TX_TXIN_OUTPOINT_HASH = TestUtils.toBitcoinHash(ARRAY_MSG_TX_TXIN1_OUTPOINT_HASH, TestAmatInfo.newNamespace());
        TX_TXIN_OUTPOINT_INDEX = TestUtils.toSignedInt(ARRAY_MSG_TX_TXIN1_OUTPOINT_INDEX);
        TX_TXIN_SCRIPT_SIZE = TestUtils.toVarInt(ARRAY_MSG_TX_TXIN1_SCRIPT_SIZE, TestAmatInfo.newNamespace()).intValue();
        TX_TXIN_SCRIPT = TestUtils.toScript(ARRAY_MSG_TX_TXIN1_SCRIPT_SIZE, ARRAY_MSG_TX_TXIN1_SCRIPT, TestAmatInfo.newNamespace());
        TX_TXIN_SEQUENCE = TestUtils.toUnsignedInt(ARRAY_MSG_TX_TXIN1_SEQUENCE);
        TX_TXOUT_NUM = TestUtils.toVarInt(ARRAY_MSG_TX_TXOUT_NUM, TestAmatInfo.newNamespace()).intValue();
        TX_TXOUT1_VALUE = TestUtils.toLong(ARRAY_MSG_TX_TXOUT1_VALUE);
        TX_TXOUT1_SCRIPT = TestUtils.toScript(ARRAY_MSG_TX_TXOUT1_SCRIPT_SIZE, ARRAY_MSG_TX_TXOUT1_SCRIPT, TestAmatInfo.newNamespace());
        TX_TXOUT2_VALUE = TestUtils.toLong(ARRAY_MSG_TX_TXOUT2_VALUE);
        TX_TXOUT2_SCRIPT = TestUtils.toScript(ARRAY_MSG_TX_TXOUT2_SCRIPT_SIZE, ARRAY_MSG_TX_TXOUT2_SCRIPT, TestAmatInfo.newNamespace());
        TX_LOCKTIME = TestUtils.toUnsignedInt(ARRAY_MSG_TX_LOCKTIME);
    }

    InputStream msgInputStream;
    BitcoinConfig unknownVersionConfig;

    static BitcoinProtocolScriptAbstract arraysToScript(byte[] scriptSize, byte[] script) throws IOException {
        byte[] array = new byte[ scriptSize.length + script.length];
        System.arraycopy(scriptSize, 0, array, 0, scriptSize.length);
        System.arraycopy(script, 0, array, scriptSize.length, script.length);
        return BitcoinProtocolScriptAbstract.readFrom(TestUtils.toLittleEndian(array), TestAmatInfo.newNamespace());
    }

    @Before
    public void setUp() throws Exception {
        msgInputStream = TestUtils.toLittleEndian(ARRAY_MSG);
    }

    @Test
    public void testTx1RO() throws NoSuchAlgorithmException {
        unknownVersionConfig = BitcoinConfig.newConfigWithUnknonwVersion();
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());

        // BitcoinMsgStream
        Prospective<BitcoinMsgStream> msgStream = creator.newSealed(msgInputStream, unknownVersionConfig);
        assertTrue(msgStream.isPresent());

        // BitcoinMsgRO
        Prospective<BitcoinMsgRO> msgRO = msgStream.get().msgRO();
        assertTrue(msgRO.isPresent());
        assertEquals(TX_MAGIC, msgRO.get().asMagicName().get());
        assertEquals(TX_COMMAND, msgRO.get().asCommandName().get());
        assertEquals(TX_PAYLOAD_CHECKSUM, msgRO.get().checksum());
        assertTrue(msgRO.get().isTx());

        // BitcoinMsgTxRO
        Optional<BitcoinMsgTxRO> txRO = msgRO.get().asTx();
        assertTrue(txRO.isPresent());
        assertEquals(TX_TXVERSION, txRO.get().txVersion());
        assertEquals(TX_TXIN_NUM, txRO.get().txInList().size());
        BitcoinTxInRO txIn = txRO.get().txInList().get(0);
        assertEquals(TX_TXIN_OUTPOINT_HASH, txIn.outpoint.hash);
        assertEquals(TX_TXIN_SCRIPT_SIZE, txIn.script.size());
        assertEquals(TX_TXIN_SCRIPT, txIn.script);
        assertEquals(TX_TXIN_SEQUENCE, txIn.sequence);
        assertEquals(TX_TXOUT_NUM, txRO.get().txOutList().size());
        BitcoinTxOutRO txOut1 = txRO.get().txOutList().get(0);
        assertEquals(TX_TXOUT1_VALUE, txOut1.value);
        assertEquals(TX_TXOUT1_SCRIPT, txOut1.script);
        BitcoinTxOutRO txOut2 = txRO.get().txOutList().get(1);
        assertEquals(TX_TXOUT2_VALUE, txOut2.value);
        assertEquals(TX_TXOUT2_SCRIPT, txOut2.script);
        assertEquals(TX_LOCKTIME, txRO.get().lockTime());

        // BitcoinMsgRW
        Prospective<BitcoinMsgRW> msgRW = msgRO.get().msgRW();
        assertTrue(msgRW.isPresent());
        assertTrue(msgRW.get().isTx());
        assertEquals(msgRO.get().asMagicName().get(), msgRW.get().asMagicName().get());
        assertEquals(msgRO.get().asCommandName().get(), msgRW.get().asCommandName().get());

        // BitcoinMsgTxRW
        Optional<BitcoinMsgTxRW> txRW = msgRW.get().asTx();
        assertTrue(txRW.isPresent());
        assertEquals(txRO.get().txVersion(), txRW.get().txVersion());
        assertEquals(txRO.get().txInList(), txRW.get().txInList());
        assertEquals(txRO.get().txOutList(), txRW.get().txOutList());
        assertEquals(txRO.get().lockTime(), txRW.get().lockTime());

        // BitcoinMsgStream 2
        Prospective<BitcoinMsgStream> msgStream2 = msgRW.get().msgStream();
        //assertEquals(msgStream.get(), msgStream2.get());

        // BitcoinMsgRO
        Prospective<BitcoinMsgRO> msgRO2 = msgStream2.get().msgRO();
        assertTrue(msgRO2.isPresent());
        assertEquals(msgRO.get().asMagicName().get(), msgRO2.get().asMagicName().get());
        assertEquals(msgRO.get().asCommandName().get(), msgRO2.get().asCommandName().get());
        assertEquals(msgRO.get().checksum(), msgRO2.get().checksum());
    }

}
