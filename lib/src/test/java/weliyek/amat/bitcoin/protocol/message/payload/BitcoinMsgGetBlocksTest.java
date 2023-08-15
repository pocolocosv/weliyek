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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.protocol.TestAmatInfo;
import weliyek.amat.bitcoin.protocol.BitcoinConfig;
import weliyek.amat.bitcoin.protocol.BitcoinMessageMagicName;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolName;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsg;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRO;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgROBody;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRW;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgStream;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;
import weliyek.amat.bitcoin.protocol.message.TestUtils;
import weliyek.bitcoin.BitcoinCommandName;
import weliyek.bitcoin.BitcoinHash;
import weliyek.ketza.base.composite.lineage.Prospective;
import weliyek.ketza.util.KetzaByteOutputStream;

public class BitcoinMsgGetBlocksTest
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinMsgGetBlocksTest.class);

    private static final byte[] ARRAY_MSG_GETBLOCKS;

    private static final byte[] ARRAY_MSG_GETBLOCKS_VERSION = new byte[] {0x71, 0x11, 0x01, 0x00};

    private static final byte[] ARRAY_MSG_GETBLOCKS_HASH_LIST_SIZE = new byte[] {0x02};

    private static final byte[] ARRAY_MSG_GETBLOCKS_HASH_LIST_ELEMENT_0 = new byte[] {
            (byte)0xd3, (byte)0x9f, (byte)0x60, (byte)0x8a, (byte)0x77, (byte)0x75, (byte)0xb5, (byte)0x37,
            (byte)0x72, (byte)0x98, (byte)0x84, (byte)0xd4, (byte)0xe6, (byte)0x63, (byte)0x3b, (byte)0xb2,
            (byte)0x10, (byte)0x5e, (byte)0x55, (byte)0xa1, (byte)0x6a, (byte)0x14, (byte)0xd3, (byte)0x1b,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    private static final byte[] ARRAY_MSG_GETBLOCKS_HASH_LIST_ELEMENT_1 = new byte[] {
            (byte)0x5c, (byte)0x3e, (byte)0x64, (byte)0x03, (byte)0xd4, (byte)0x08, (byte)0x37, (byte)0x11,
            (byte)0x0a, (byte)0x2e, (byte)0x8a, (byte)0xfb, (byte)0x60, (byte)0x2b, (byte)0x1c, (byte)0x01,
            (byte)0x71, (byte)0x4b, (byte)0xda, (byte)0x7c, (byte)0xe2, (byte)0x3b, (byte)0xea, (byte)0x0a,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    private static final byte[] ARRAY_MSG_GETBLOCKS_STOP_HASH = new byte[] {
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    public static final int MSG_GETBLOCKS_VERSION;
    public static final int MSG_GETBLOCKS_HASH_LIST_SIZE;
    public static final BitcoinHash MSG_GETBLOCKS_HASH_LIST_ELEMENT_0;
    public static final BitcoinHash MSG_GETBLOCKS_HASH_LIST_ELEMENT_1;
    public static final BitcoinHash MSG_GETBLOCKS_STOP_HASH;

    static {
        logger.info("Setting up static test attributes");
        ARRAY_MSG_GETBLOCKS = TestUtils.joinArrays(ARRAY_MSG_GETBLOCKS_VERSION,
                                                   ARRAY_MSG_GETBLOCKS_HASH_LIST_SIZE,
                                                   ARRAY_MSG_GETBLOCKS_HASH_LIST_ELEMENT_0,
                                                   ARRAY_MSG_GETBLOCKS_HASH_LIST_ELEMENT_1,
                                                   ARRAY_MSG_GETBLOCKS_STOP_HASH);
        MSG_GETBLOCKS_VERSION = TestUtils.toSignedInt(ARRAY_MSG_GETBLOCKS_VERSION);
        MSG_GETBLOCKS_HASH_LIST_SIZE = TestUtils.toVarInt(ARRAY_MSG_GETBLOCKS_HASH_LIST_SIZE, TestAmatInfo.newNamespace()).intValue();
        MSG_GETBLOCKS_HASH_LIST_ELEMENT_0 = TestUtils.toBitcoinHash(ARRAY_MSG_GETBLOCKS_HASH_LIST_ELEMENT_0, TestAmatInfo.newNamespace());
        MSG_GETBLOCKS_HASH_LIST_ELEMENT_1 = TestUtils.toBitcoinHash(ARRAY_MSG_GETBLOCKS_HASH_LIST_ELEMENT_1, TestAmatInfo.newNamespace());
        MSG_GETBLOCKS_STOP_HASH = TestUtils.toBitcoinHash(ARRAY_MSG_GETBLOCKS_STOP_HASH, TestAmatInfo.newNamespace());
    }

    final BitcoinConfig unknownVersionConfig = BitcoinConfig.newConfigWithUnknonwVersion();
    final BitcoinMsgGetBlocksSerializer serializer = new BitcoinMsgGetBlocksSerializer();

    InputStream payloadInputStream;
    BitcoinMsgGetBlocksKernelRO roKernel;
    BitcoinMsgGetBlocksKernelRW rwKernel;
    BitcoinMsgROBody msgROBody = mock(BitcoinMsgROBody.class);
    BitcoinMsgRWBody msgRWBody = mock(BitcoinMsgRWBody.class);

    @Before
    public void setUp() throws Exception {
        logger.info("Setting up test");
        payloadInputStream = TestUtils.toLittleEndian(ARRAY_MSG_GETBLOCKS);
        // roKernel
        when(msgROBody.namespace()).thenReturn(BitcoinProtocolName.GETBLOCKS.appendTo(TestAmatInfo.newNamespace()));
        BitcoinMsgGetBlocksKernelROCommissioner roCommissioner = mock(BitcoinMsgGetBlocksKernelROCommissioner.class);
        roKernel = new BitcoinMsgGetBlocksKernelRO(roCommissioner);
        // rwKernel
        when(msgRWBody.namespace()).thenReturn(BitcoinProtocolName.GETBLOCKS.appendTo(TestAmatInfo.newNamespace()));
        BitcoinMsgGetBlocksKernelRWCommissioner rwCommissioner = mock(BitcoinMsgGetBlocksKernelRWCommissioner.class);
        rwKernel = new BitcoinMsgGetBlocksKernelRW(rwCommissioner);
    }

    @Test
    public void testDeserialization() {
        logger.info("Starting deserialization test for " + BitcoinMsgGetBlocks.class.getSimpleName());
        serializer.deserializeKernel(payloadInputStream, unknownVersionConfig, roKernel, TestAmatInfo.newNamespace());
        assertEquals(MSG_GETBLOCKS_VERSION, roKernel.version());
        assertEquals(MSG_GETBLOCKS_HASH_LIST_SIZE, roKernel.headerHashes().size());
        assertEquals(MSG_GETBLOCKS_HASH_LIST_ELEMENT_0, roKernel.headerHashes().get(0));
        assertEquals(MSG_GETBLOCKS_HASH_LIST_ELEMENT_1, roKernel.headerHashes().get(1));
        assertEquals(MSG_GETBLOCKS_STOP_HASH, roKernel.stopHash());
    }

    @Test
    public void testSerialization() {
        KetzaByteOutputStream out = new KetzaByteOutputStream();
        logger.info("Serialization test " + BitcoinMsgGetBlocks.class.getSimpleName());
        rwKernel.commissionWithRWArgs(msgRWBody, new MessageRWArgs(BitcoinMessageMagicName.TESTNET, BitcoinCommandName.NOTFOUND));
        rwKernel.version = MSG_GETBLOCKS_VERSION;
        rwKernel.modifiableHeaderHashList().add(MSG_GETBLOCKS_HASH_LIST_ELEMENT_0);
        rwKernel.modifiableHeaderHashList().add(MSG_GETBLOCKS_HASH_LIST_ELEMENT_1);
        rwKernel.stopHash = MSG_GETBLOCKS_STOP_HASH;
        serializer.serializeKernel(rwKernel, unknownVersionConfig, out, TestAmatInfo.newNamespace());
        assertTrue(TestUtils.compareUpToShortestArrays(ARRAY_MSG_GETBLOCKS, out.backingArray()));
    }

    @Test
    public void testRWCommission() throws NoSuchAlgorithmException, IOException {
        logger.info("new RW and its serialization test " + BitcoinMsgGetBlocks.class.getSimpleName());
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());
        final Prospective<BitcoinMsgRW> rwMsg = creator.newRW(unknownVersionConfig, BitcoinMessageMagicName.TESTNET, BitcoinCommandName.GETBLOCKS);

        assertTrue(rwMsg.isPresent());
        assertTrue(rwMsg.get().isGetBlocks());

        final Optional<BitcoinMsgGetBlocksRW> rwGetBlocks = rwMsg.get().asGetBlocks();
        assertTrue(rwGetBlocks.isPresent());
        rwGetBlocks.get().setVersion(MSG_GETBLOCKS_VERSION);
        rwGetBlocks.get().headerHashes().add(MSG_GETBLOCKS_HASH_LIST_ELEMENT_0);
        rwGetBlocks.get().headerHashes().add(MSG_GETBLOCKS_HASH_LIST_ELEMENT_1);
        rwGetBlocks.get().setStopHash(MSG_GETBLOCKS_STOP_HASH);
        final Prospective<BitcoinMsgStream> streamMsg = rwMsg.get().msgStream();
        IOUtils.contentEquals(payloadInputStream, streamMsg.get().dataInput());

        final Prospective<BitcoinMsgRO> roMsg = streamMsg.get().msgRO();
        assertTrue(roMsg.isPresent());
        assertTrue(roMsg.get().isGetBlocks());
        final Optional<BitcoinMsgGetBlocksRO> roGetBlocks = roMsg.get().asGetBlocks();
        assertTrue(roGetBlocks.isPresent());
        assertEquals(rwMsg.get(), roMsg.get());
    }

}
