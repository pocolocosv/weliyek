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
package weliyek.amat.bitcoin.protocol.message.payload;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.commons.io.input.BoundedInputStream;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.LittleEndianDataInputStream;

import weliyek.amat.base.protocol.TestAmatInfo;
import weliyek.amat.bitcoin.protocol.BitcoinConfig;
import weliyek.amat.bitcoin.protocol.BitcoinMessageMagicName;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsg;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRO;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRW;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgStream;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgVersionTest;
import weliyek.amat.bitcoin.protocol.message.TestUtils;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgReject.CCodeName;
import weliyek.bitcoin.BitcoinCommandName;
import weliyek.bitcoin.BitcoinHash;
import weliyek.ketza.base.composite.lineage.Prospective;
import weliyek.ketza.util.ByteSequenceWrapper;

public class BitcoinMsgRejectTest
{

    static final byte[] TXID_ARRAY = new byte[] {
            (byte)0x6D, (byte)0xBD, (byte)0xDB, (byte)0x08, (byte)0x5B, (byte)0x1D, (byte)0x8A, (byte)0xF7,
            (byte)0x51, (byte)0x84, (byte)0xF0, (byte)0xBC, (byte)0x01, (byte)0xFA, (byte)0xD5, (byte)0x8D,
            (byte)0x12, (byte)0x66, (byte)0xE9, (byte)0xB6, (byte)0x3B, (byte)0x50, (byte)0x88, (byte)0x19,
            (byte)0x90, (byte)0xE4, (byte)0xB4, (byte)0x0D, (byte)0x6A, (byte)0xEE, (byte)0x36, (byte)0x29
    };

    static final byte[] VERSION_MSG_ARRAY = BitcoinMsgVersionTest.ARRAY_MSG;

    public static final BitcoinHash TX_ID = TestUtils.toBitcoinHash(TXID_ARRAY, TestAmatInfo.newNamespace());

    BitcoinConfig latestVersionConfig = BitcoinConfig.newConfig(BitcoinMsg.LATEST_VERSION);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testInvalidVersionMsg() throws NoSuchAlgorithmException {
        final LittleEndianDataInputStream in = TestUtils.toLittleEndian(VERSION_MSG_ARRAY);
        final BoundedInputStream truncatedVersionIn = new BoundedInputStream(in, 40);
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());
        final Prospective<BitcoinMsgStream> invalidVersionMessageStream = creator.newSealed(truncatedVersionIn, latestVersionConfig);
        assertNotNull(invalidVersionMessageStream);
        assertTrue(invalidVersionMessageStream.isPresent());

        final Prospective<BitcoinMsgRO> incompleteVersionMsgRO = invalidVersionMessageStream.get().msgRO();
        assertNotNull(incompleteVersionMsgRO);
        assertFalse(incompleteVersionMsgRO.isPresent());
        assertTrue(incompleteVersionMsgRO.getIncompleteResult().isPresent());
        assertEquals(BitcoinMessageMagicName.MAIN, incompleteVersionMsgRO.getIncompleteResult().get().asMagicName().get());
        assertEquals(BitcoinCommandName.VERSION, incompleteVersionMsgRO.getIncompleteResult().get().asCommandName().get());
    }

    @Test
    public void testMessageCycle() throws NoSuchAlgorithmException {
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());
        final BitcoinMessageMagicName magic = BitcoinMessageMagicName.TESTNET;
        final String reason = "Unknown reason";

        // Message RW --------------------------------------------------------
        final Prospective<BitcoinMsgRW> msgRW = creator.newRW(latestVersionConfig,
                                                              magic,
                                                              BitcoinCommandName.REJECT);
        assertNotNull(msgRW);
        assertTrue(msgRW.isPresent());
        assertEquals(magic, msgRW.get().asMagicName().get());
        assertEquals(BitcoinCommandName.REJECT, msgRW.get().asCommandName().get());

        // Reject RW ---------------------------------------------------------
        final Optional<BitcoinMsgRejectRW> rejectRW = msgRW.get().asReject();
        assertNotNull(rejectRW);
        assertTrue(rejectRW.isPresent());
        assertEquals(msgRW.get().asMagicName(), rejectRW.get().asMagicName());
        assertEquals(msgRW.get().asCommandName(), rejectRW.get().asCommandName());

        // Default values. Most are not valid message values.
        assertEquals("", rejectRW.get().message());
        assertEquals(0, rejectRW.get().ccode());
        assertEquals("", rejectRW.get().reason());
        assertEquals(ByteSequenceWrapper.EMPTY, rejectRW.get().data());

        rejectRW.get().setMessage(BitcoinCommandName.TX);
        rejectRW.get().setCCode(CCodeName.REJECT_DUPLICATE);
        rejectRW.get().setReason(reason);
        rejectRW.get().setData(TX_ID.toByteArray());

        // Message Stream ----------------------------------------------------
        final Prospective<BitcoinMsgStream> msgStream = msgRW.get().msgStream();
        assertNotNull(msgStream);
        assertTrue(msgStream.isPresent());

        // Message RO --------------------------------------------------------
        final Prospective<BitcoinMsgRO> msgRO = msgStream.get().msgRO();
        assertNotNull(msgRO);
        assertTrue(msgRO.isPresent());
        assertEquals(msgRW.get().asMagicName(), msgRO.get().asMagicName());
        assertEquals(msgRW.get().asCommandName(), msgRO.get().asCommandName());
        assertTrue(msgRO.get().isReject());

        // Reject RO ---------------------------------------------------------
        final Optional<BitcoinMsgRejectRO> rejectRO = msgRO.get().asReject();
        assertNotNull(rejectRO);
        assertTrue(rejectRO.isPresent());
        assertEquals(msgRO.get().asMagicName(), rejectRO.get().asMagicName());
        assertEquals(msgRO.get().asCommandName(), rejectRO.get().asCommandName());
        assertEquals(rejectRW.get().message(), rejectRO.get().message());
        assertEquals(rejectRW.get().ccode(), rejectRO.get().ccode());
        assertEquals(rejectRW.get().reason(), rejectRO.get().reason());
        assertEquals(rejectRW.get().data(), rejectRO.get().data());
    }

}
