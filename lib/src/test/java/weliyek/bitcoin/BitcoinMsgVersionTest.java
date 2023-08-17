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
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.EnumSet;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import weliyek.amat.base.protocol.TestAmatInfo;
import weliyek.amat.bitcoin.protocol.BitcoinNodeServiceFlag;
import weliyek.bitcoin.WkBitcoinCommandName;
import weliyek.bitcoin.BitcoinConfig;
import weliyek.bitcoin.BitcoinMessageMagicName;
import weliyek.bitcoin.BitcoinMsg;
import weliyek.bitcoin.BitcoinMsgRO;
import weliyek.bitcoin.BitcoinMsgRW;
import weliyek.bitcoin.BitcoinMsgStream;
import weliyek.bitcoin.BitcoinMsgVersion;
import weliyek.bitcoin.BitcoinMsgVersionRO;
import weliyek.bitcoin.BitcoinMsgVersionRW;
import weliyek.bitcoin.BitcoinNetAddr;
import weliyek.ketza.base.composite.lineage.Prospective;

public class BitcoinMsgVersionTest
{

    public final static byte[] ARRAY_MSG_MAGIC = new byte[] {
            (byte)0xf9, (byte)0xbe, (byte)0xb4, (byte)0xd9
    };

    public final static byte[] ARRAY_MSG_COMMAND = new byte[] {
            (byte)0x76, (byte)0x65, (byte)0x72, (byte)0x73, (byte)0x69, (byte)0x6f, (byte)0x6e, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
    };

    public final static byte[] ARRAY_MSG_PAYLOAD_LENGTH = new byte[] {
            (byte)0x64, (byte)0x00, (byte)0x00, (byte)0x00
    };

    public final static byte[] ARRAY_MSG_PAYLOAD_CHECKSUM = new byte[] {
            (byte)0x3B, (byte)0x64, (byte)0x8D, (byte)0x5A
    };

    public final static byte[] ARRAY_MSG_VERSION_VERSIONVALUE = new byte[] {
            (byte)0x62, (byte)0xea, (byte)0x00, (byte)0x00
    };

    public final static byte[] ARRAY_MSG_VERSION_SERVICES = new byte[] {
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    public final static byte[] ARRAY_MSG_VERSION_TIMESTAMP = new byte[] {
            (byte)0x11, (byte)0xb2, (byte)0xd0, (byte)0x50, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    public final static byte[] ARRAY_MSG_VERSION_RECV = new byte[] {
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00
    };

    public final static byte[] ARRAY_MSG_VERSION_FROM = new byte[] {
            (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00
    };

    public final static byte[] ARRAY_MSG_VERSION_NONCE = new byte[] {
            (byte)0x3b, (byte)0x2e, (byte)0xb3, (byte)0x5d, (byte)0x8c, (byte)0xe6, (byte)0x17, (byte)0x65
    };

    public final static byte[] ARRAY_MSG_VERSION_USER_AGENT = new byte[] {
            (byte)0x0f, (byte)0x2f, (byte)0x53, (byte)0x61, (byte)0x74, (byte)0x6f, (byte)0x73, (byte)0x68,
            (byte)0x69, (byte)0x3a, (byte)0x30, (byte)0x2e, (byte)0x37, (byte)0x2e, (byte)0x32, (byte)0x2f
    };

    public final static byte[] ARRAY_MSG_VERSION_START_HEIGHT = new byte[] {
            (byte)0xc0, (byte)0x3e, (byte)0x03, (byte)0x00
    };

    public final static byte[] ARRAY_MSG = TestUtils.joinArrays(ARRAY_MSG_MAGIC,
                                                                ARRAY_MSG_COMMAND,
                                                                ARRAY_MSG_PAYLOAD_LENGTH,
                                                                ARRAY_MSG_PAYLOAD_CHECKSUM,
                                                                ARRAY_MSG_VERSION_VERSIONVALUE,
                                                                ARRAY_MSG_VERSION_SERVICES,
                                                                ARRAY_MSG_VERSION_TIMESTAMP,
                                                                ARRAY_MSG_VERSION_RECV,
                                                                ARRAY_MSG_VERSION_FROM,
                                                                ARRAY_MSG_VERSION_NONCE,
                                                                ARRAY_MSG_VERSION_USER_AGENT,
                                                                ARRAY_MSG_VERSION_START_HEIGHT);

    public final static   BitcoinMessageMagicName MSG_MAGIC = TestUtils.toMagic(ARRAY_MSG_MAGIC);
    public final static WkBitcoinCommandName MSG_COMMAND = TestUtils.toCommand(ARRAY_MSG_COMMAND);
    public final static     int MSG_PAYLOAD_CHECKSUM = TestUtils.toSignedInt(ARRAY_MSG_PAYLOAD_CHECKSUM);
    public final static     int MSG_VERSION_NUMBER = TestUtils.toSignedInt(ARRAY_MSG_VERSION_VERSIONVALUE);
    public final static EnumSet<BitcoinNodeServiceFlag> MSG_VERSION_SERVICES = TestUtils.toServices(ARRAY_MSG_VERSION_SERVICES);
    public final static    long MSG_VERSION_TIMESTAMP = TestUtils.toLong(ARRAY_MSG_VERSION_TIMESTAMP);
    public final static BitcoinNetAddr MSG_VERSION_RECV = TestUtils.toNetAddr(MSG_VERSION_NUMBER, MSG_COMMAND, ARRAY_MSG_VERSION_RECV, TestAmatInfo.newNamespace());
    public final static BitcoinNetAddr MSG_VERSION_FROM = TestUtils.toNetAddr(MSG_VERSION_NUMBER, MSG_COMMAND, ARRAY_MSG_VERSION_FROM, TestAmatInfo.newNamespace());
    public final static    long MSG_VERSION_NONCE = TestUtils.toLong(ARRAY_MSG_VERSION_NONCE);
    public final static  String MSG_VERSION_USER_AGENT = TestUtils.toVarStr(ARRAY_MSG_VERSION_USER_AGENT, TestAmatInfo.newNamespace());
    public final static     int MSG_VERSION_START_HEIGHT = TestUtils.toSignedInt(ARRAY_MSG_VERSION_START_HEIGHT);

    InputStream versionIn;
    BitcoinMsg creator;
    Prospective<BitcoinMsgStream> msgStream;
    Prospective<BitcoinMsgRO> msgRO;
    Optional<BitcoinMsgVersionRO> versionRO;
    Prospective<BitcoinMsgRW> msgRW;
    Optional<BitcoinMsgVersionRW> versionRW;
    Prospective<BitcoinMsgStream> msgStream2;
    Prospective<BitcoinMsgRW> newRW;
    Optional<BitcoinMsgVersionRW> newVersionRW;

    @Before
    public void setup() throws NoSuchAlgorithmException {
        versionIn = TestUtils.toLittleEndian(ARRAY_MSG);
        creator = new BitcoinMsg(TestAmatInfo.newNamespace());
    }

    @Test
    public void testStreamFromArray() {
        BitcoinConfig msgConfig = BitcoinConfig.newConfigWithUnknonwVersion();

        // BitcoinMsgStream
        msgStream = creator.newSealed(versionIn, msgConfig);
        assertTrue(msgStream.isPresent());

        // BitcoinMsgRO
        msgRO = msgStream.get().msgRO();
        assertNotNull(msgRO);
        assertTrue(msgRO.isPresent());

        // BitcoinMsgVersionRO
        assertTrue(msgRO.get().isVersion());
        versionRO = msgRO.get().asVersion();
        assertTrue(versionRO.isPresent());
        assertVersionPayloadWithGlobals(versionRO.get());

        // BitcoinMsgRW
        msgRW = msgRO.get().msgRW();
        assertTrue(msgRW.isPresent());

        // BitcoinMsgVersionRW
        versionRW = msgRW.get().asVersion();
        assertTrue(versionRW.isPresent());
        assertVersionPayloadWithGlobals(versionRW.get());

        // BitcoinMsgStream
        msgStream2 = msgRW.get().msgStream();
        assertEquals(msgStream.get(), msgStream2.get());

        Prospective<BitcoinMsgRO> msgRO2 = msgStream2.get().msgRO();
        assertTrue(msgRO2.isPresent());
    }

    @Test
    public void testNewRW() throws NoSuchAlgorithmException, UnknownHostException {
        // BitcoinMsgStream
        BitcoinConfig unknownVersionConfig = BitcoinConfig.newConfigWithUnknonwVersion();
        msgStream = creator.newSealed(versionIn, unknownVersionConfig);
        assertTrue(msgStream.isPresent());

        // BitcoinMsgRW
        BitcoinConfig knownVersionConfig = BitcoinConfig.newConfig(MSG_VERSION_NUMBER);
        newRW = creator.newRW(knownVersionConfig, BitcoinMessageMagicName.MAIN,
                              WkBitcoinCommandName.VERSION);
        assertTrue(newRW.isPresent());
        newVersionRW = newRW.get().asVersion();
        assertTrue(newVersionRW.isPresent());
        newVersionRW.get().setVersion(MSG_VERSION_NUMBER);
        newVersionRW.get().setTimestamp(MSG_VERSION_TIMESTAMP);
        newVersionRW.get().setServices(MSG_VERSION_SERVICES);
        newVersionRW.get().setRecv(MSG_VERSION_RECV);
        newVersionRW.get().setFrom(MSG_VERSION_RECV);
        newVersionRW.get().setNonce(MSG_VERSION_NONCE);
        newVersionRW.get().setUserAgent(MSG_VERSION_USER_AGENT);
        newVersionRW.get().setStartHeight(MSG_VERSION_START_HEIGHT);
        assertVersionPayloadWithGlobals(newRW.get().asVersion().get());

        msgStream2 = newRW.get().msgStream();
        assertTrue(msgStream2.isPresent());
        assertTrue(msgStream.get().equals(msgStream2.get()));

        msgRO = msgStream2.get().msgRO();
        assertNotNull(msgRO);
        assertTrue(msgRO.isPresent());

        assertVersionPayloadWithGlobals(msgRO.get().asVersion().get());
        assertEquals(MSG_PAYLOAD_CHECKSUM, msgRO.get().checksum());
    }

    void assertVersionPayloadWithGlobals(BitcoinMsgVersion version) {
        assertVersionPayload(version, MSG_MAGIC, MSG_VERSION_NUMBER,
                             MSG_VERSION_SERVICES, MSG_VERSION_TIMESTAMP, MSG_VERSION_RECV,
                             MSG_VERSION_RECV, MSG_VERSION_NONCE, MSG_VERSION_USER_AGENT,
                             MSG_VERSION_START_HEIGHT, null);
    }

    void assertVersionPayload(
            BitcoinMsgVersion version,
            BitcoinMessageMagicName expectedMagic,
            int expectedVersion,
            EnumSet<BitcoinNodeServiceFlag> expectedServices,
            long expectedTimestamp,
            BitcoinNetAddr expectedRecv,
            BitcoinNetAddr expectedFrom,
            long expectedNonce,
            String expectedUserAgent,
            int expectedStartHeight,
            Boolean expectedRelay)
    {
        assertEquals(WkBitcoinCommandName.VERSION, version.asCommandName().get());
        assertEquals(expectedMagic, version.asMagicName().get());
        assertEquals(expectedVersion, version.version());
        assertEquals(expectedServices, version.services());
        assertEquals(expectedTimestamp, version.timestamp());
        assertEquals(expectedRecv, version.recv());
        if (null != expectedFrom) {
            assertEquals(expectedFrom, version.from().get());
        }
        assertEquals(expectedNonce, version.nonce());
        if (null != expectedUserAgent) {
            assertEquals(expectedUserAgent, version.userAgent().get());
        }
        assertEquals(expectedStartHeight, version.startHeight());
        // ----- Relay -------------------------------------------------------
        if (null != expectedRelay) {
            assertEquals(expectedRelay, version.relay());
        } else {
            if (version.version() >= 70001) {
                fail("Expected relay value must be provided");
            }
        }
    }

}
