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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

import ricvgx.builder.ByteInputStream;
import weliyek.amat.base.protocol.TestAmatInfo;
import weliyek.bitcoin.BitcoinCommandName;
import weliyek.bitcoin.BitcoinConfig;
import weliyek.bitcoin.BitcoinMessageMagicName;
import weliyek.bitcoin.BitcoinMsg;
import weliyek.bitcoin.BitcoinMsgRO;
import weliyek.bitcoin.BitcoinMsgRW;
import weliyek.bitcoin.BitcoinMsgStream;
import weliyek.bitcoin.MessagePayloadHandlerSerializer;
import weliyek.ketza.base.composite.lineage.Prospective;

public class BitcoinMsgTest
{

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void hash() throws NoSuchAlgorithmException {
        byte[] p = new byte[] {
                (byte)0x62, (byte)0xea, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0xb2, (byte)0xd0, (byte)0x50,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
                (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
                (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
                (byte)0x3b, (byte)0x2e, (byte)0xb3, (byte)0x5d, (byte)0x8c, (byte)0xe6, (byte)0x17, (byte)0x65,
                (byte)0x0f, (byte)0x2f, (byte)0x53, (byte)0x61, (byte)0x74, (byte)0x6f, (byte)0x73, (byte)0x68,
                (byte)0x69, (byte)0x3a, (byte)0x30, (byte)0x2e, (byte)0x37, (byte)0x2e, (byte)0x32, (byte)0x2f,
                (byte)0xc0, (byte)0x3e, (byte)0x03, (byte)0x00 };
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(p);
        hash = digest.digest(hash);
        int chksum = MessagePayloadHandlerSerializer.extractFirstFourBytes(hash);
        assertEquals(0x5A8D643B, chksum);
        assertNotNull(hash);
    }

    final byte[] verackMsg = new byte[] {
            (byte)0xF9, (byte)0xBE, (byte)0xB4, (byte)0xD9, (byte)0x76, (byte)0x65, (byte)0x72, (byte)0x61,
            (byte)0x63, (byte)0x6B, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x5D, (byte)0xF6, (byte)0xE0, (byte)0xE2 };

    @Test
    public void verackDeserialization() throws NoSuchAlgorithmException {
        ByteInputStream verackInputStream = new ByteInputStream(verackMsg, verackMsg.length);
        BitcoinConfig msgConfig = BitcoinConfig.newConfigWithUnknonwVersion();
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());
        Prospective<BitcoinMsgStream> versionStream = creator.newSealed(verackInputStream, msgConfig);
        Prospective<BitcoinMsgRO> ro = versionStream.get().msgRO();
        assertNotNull(ro);
        assertTrue(ro.isPresent());
        assertEquals(BitcoinMessageMagicName.MAIN, ro.get().asMagicName().get());
        assertEquals(BitcoinCommandName.VERACK, ro.get().asCommandName().get());
        assertEquals(0xE2E0F65D, ro.get().checksum());
    }

    @Test
    public void verack() throws NoSuchAlgorithmException {
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());
        BitcoinConfig msgConfig = BitcoinConfig.newConfig(BitcoinMsg.LATEST_VERSION);
        Prospective<BitcoinMsgRW> rw = creator.newRW(msgConfig,
                                                     BitcoinMessageMagicName.MAIN,
                                                     BitcoinCommandName.VERACK);
        assertNotNull(rw);
        assertTrue(rw.isPresent());
        assertEquals(BitcoinMessageMagicName.MAIN, rw.get().asMagicName().get());
        assertEquals(BitcoinCommandName.VERACK, rw.get().asCommandName().get());
        Prospective<BitcoinMsgStream> stream = rw.get().msgStream();
        assertNotNull(stream);
        assertTrue(stream.isPresent());
        Prospective<BitcoinMsgRO> ro = stream.get().msgRO();
        assertNotNull(ro);
        assertTrue(ro.isPresent());
        assertEquals(BitcoinMessageMagicName.MAIN, ro.get().asMagicName().get());
        assertEquals(BitcoinCommandName.VERACK, ro.get().asCommandName().get());
        assertEquals(0xE2E0F65D, ro.get().checksum());
    }

    @Test
    public void mempool() throws NoSuchAlgorithmException {
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());
        BitcoinConfig msgConfig = BitcoinConfig.newConfig(BitcoinMsg.LATEST_VERSION);
        Prospective<BitcoinMsgRW> rw = creator.newRW(msgConfig,
                                                     BitcoinMessageMagicName.MAIN,
                                                     BitcoinCommandName.MEMPOOL);
        assertTrue(rw.isPresent());
        assertEquals(BitcoinMessageMagicName.MAIN, rw.get().asMagicName().get());
        assertEquals(BitcoinCommandName.MEMPOOL, rw.get().asCommandName().get());
        Prospective<BitcoinMsgStream> stream = rw.get().msgStream();
        assertNotNull(stream);
        assertTrue(stream.isPresent());
        Prospective<BitcoinMsgRO> ro = stream.get().msgRO();
        assertNotNull(ro);
        assertTrue(ro.isPresent());
        assertEquals(BitcoinMessageMagicName.MAIN, ro.get().asMagicName().get());
        assertEquals(BitcoinCommandName.MEMPOOL, ro.get().asCommandName().get());
    }

}
