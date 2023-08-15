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

import org.junit.Before;
import org.junit.Test;

import com.google.common.io.LittleEndianDataInputStream;

import weliyek.amat.base.protocol.TestAmatInfo;
import weliyek.amat.bitcoin.protocol.BitcoinConfig;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolName;
import weliyek.amat.bitcoin.protocol.Complexity;
import weliyek.amat.bitcoin.protocol.block.BlockHeader;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsg;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgROBody;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.TestUtils;
import weliyek.bitcoin.BitcoinHash;
import weliyek.ketza.util.KetzaByteOutputStream;

public class BitcoinMsgHeadersTest
{

    private static final byte[] ARRAY_MSG_HEADER;

    private static final byte[] ARRAY_MSG_HEADER_COUNT = new byte[] { 0x01 };

    private static final byte[] ARRAY_MSG_HEADER_VERSION = new byte[] {
            0x02, 0x00, 0x00, 0x00
    };

    private static final byte[] ARRAY_MSG_HEADER_PREV_BLOCK = new byte[] {
            (byte)0xb6, (byte)0xff, (byte)0x0b, (byte)0x1b, (byte)0x16, (byte)0x80, (byte)0xa2, (byte)0x86,
            (byte)0x2a, (byte)0x30, (byte)0xca, (byte)0x44, (byte)0xd3, (byte)0x46, (byte)0xd9, (byte)0xe8,
            (byte)0x91, (byte)0x0d, (byte)0x33, (byte)0x4b, (byte)0xeb, (byte)0x48, (byte)0xca, (byte)0x0c,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    private static final byte[] ARRAY_MSG_HEADER_MERKLE_ROOT = new byte[] {
            (byte)0x9d, (byte)0x10, (byte)0xaa, (byte)0x52, (byte)0xee, (byte)0x94, (byte)0x93, (byte)0x86,
            (byte)0xca, (byte)0x93, (byte)0x85, (byte)0x69, (byte)0x5f, (byte)0x04, (byte)0xed, (byte)0xe2,
            (byte)0x70, (byte)0xdd, (byte)0xa2, (byte)0x08, (byte)0x10, (byte)0xde, (byte)0xcd, (byte)0x12,
            (byte)0xbc, (byte)0x9b, (byte)0x04, (byte)0x8a, (byte)0xaa, (byte)0xb3, (byte)0x14, (byte)0x71
    };

    private static final byte[] ARRAY_MSG_HEADER_TIMESTAMP = new byte[] {
            (byte)0x24, (byte)0xd9, (byte)0x5a, (byte)0x54
    };

    private static final byte[] ARRAY_MSG_HEADER_COMPLEXITY = new byte[] {
            (byte)0x30, (byte)0xc3, (byte)0x1b, (byte)0x18
    };

    private static final byte[] ARRAY_MSG_HEADER_NONCE = new byte[] {
            (byte)0xfe, (byte)0x9f, (byte)0x08, (byte)0x64
    };

    private static final byte[] ARRAY_MSG_HEADER_TXNCOUNT = new byte[] {
            0x00
    };

    public static final         int MSG_HEADER_COUNT;
    public static final         int MSG_HEADER_VERSION;
    public static final BitcoinHash MSG_HEADER_PREV_BLOCK;
    public static final BitcoinHash MSG_HEADER_MERKLE_ROOT;
    public static final        long MSG_HEADER_TIMESTAMP;
    public static final  Complexity MSG_HEADER_COMPLEXITY;
    public static final        long MSG_HEADER_NONCE;
    public static final         int MSG_HEADER_TXNCOUNT;

    static {
        MSG_HEADER_COUNT = TestUtils.toVarInt(ARRAY_MSG_HEADER_COUNT, TestAmatInfo.newNamespace()).intValue();
        MSG_HEADER_VERSION = TestUtils.toSignedInt(ARRAY_MSG_HEADER_VERSION);
        MSG_HEADER_PREV_BLOCK = TestUtils.toBitcoinHash(ARRAY_MSG_HEADER_PREV_BLOCK, TestAmatInfo.newNamespace());
        MSG_HEADER_MERKLE_ROOT = TestUtils.toBitcoinHash(ARRAY_MSG_HEADER_MERKLE_ROOT, TestAmatInfo.newNamespace());
        MSG_HEADER_TIMESTAMP = TestUtils.toUnsignedInt(ARRAY_MSG_HEADER_TIMESTAMP);
        MSG_HEADER_COMPLEXITY = TestUtils.toComplexity(ARRAY_MSG_HEADER_COMPLEXITY, TestAmatInfo.newNamespace());
        MSG_HEADER_NONCE = TestUtils.toUnsignedInt(ARRAY_MSG_HEADER_NONCE);
        MSG_HEADER_TXNCOUNT = TestUtils.toVarInt(ARRAY_MSG_HEADER_TXNCOUNT, TestAmatInfo.newNamespace()).intValue();
        ARRAY_MSG_HEADER = TestUtils.joinArrays(ARRAY_MSG_HEADER_COUNT,
                                                ARRAY_MSG_HEADER_VERSION,
                                                ARRAY_MSG_HEADER_PREV_BLOCK,
                                                ARRAY_MSG_HEADER_MERKLE_ROOT,
                                                ARRAY_MSG_HEADER_TIMESTAMP,
                                                ARRAY_MSG_HEADER_COMPLEXITY,
                                                ARRAY_MSG_HEADER_NONCE,
                                                ARRAY_MSG_HEADER_TXNCOUNT);
    }

    public static final BitcoinConfig MSG_CONFIG = BitcoinConfig.newConfig(BitcoinMsg.LATEST_VERSION);
    public static final BitcoinMsgHeadersSerialization HEADERS_SERIALIZER = new BitcoinMsgHeadersSerialization();

    BitcoinMsgHeadersROCoreKernel roHeader;
    BitcoinMsgHeadersRWCoreKernel rwHeader;
    LittleEndianDataInputStream payloadHeader;

    @Before
    public void setUp() throws Exception {
        payloadHeader = TestUtils.toLittleEndian(ARRAY_MSG_HEADER);
        BitcoinMsgROBody msgBody = mock(BitcoinMsgROBody.class);
        when(msgBody.namespace()).thenReturn(BitcoinProtocolName.HEADERS.appendTo(TestAmatInfo.newNamespace()));
        // RO
        BitcoinMsgHeadersROCoreKernelCommissioner roCommissioner = mock(BitcoinMsgHeadersROCoreKernelCommissioner.class);
        roHeader = new BitcoinMsgHeadersROCoreKernel(roCommissioner);
        roHeader.commission(msgBody);
    }

    @Test
    public void testDeserialization() throws IOException {
        HEADERS_SERIALIZER.deserializeKernel(payloadHeader, MSG_CONFIG, roHeader, TestAmatInfo.newNamespace());
        assertEquals(MSG_HEADER_COUNT, roHeader.size());
        BlockHeader header = roHeader.get(0);
        assertEquals(MSG_HEADER_VERSION, header.version);
        assertEquals(MSG_HEADER_PREV_BLOCK, header.prevBlockHash);
        assertEquals(MSG_HEADER_MERKLE_ROOT, header.merkleRootHash);
        assertEquals(MSG_HEADER_TIMESTAMP, header.timestamp);
        assertEquals(MSG_HEADER_COMPLEXITY, header.complexity);
        assertEquals(MSG_HEADER_NONCE, header.nonce);
        assertEquals(MSG_HEADER_TXNCOUNT, header.txnCount);
    }


    @Test
    public void testSerialization() throws IOException {
        BitcoinMsgRWBody msgBody = mock(BitcoinMsgRWBody.class);
        when(msgBody.namespace()).thenReturn(BitcoinProtocolName.HEADERS.appendTo(TestAmatInfo.newNamespace()));
        HEADERS_SERIALIZER.deserializeKernel(payloadHeader, MSG_CONFIG, roHeader, TestAmatInfo.newNamespace());
        BitcoinMsgHeadersRWCoreKernelCommissioner rwCommissioner = mock(BitcoinMsgHeadersRWCoreKernelCommissioner.class);
        rwHeader = new BitcoinMsgHeadersRWCoreKernel(rwCommissioner);
        rwHeader.commissionWithROKernel(msgBody, roHeader);
        KetzaByteOutputStream out = new KetzaByteOutputStream();
        HEADERS_SERIALIZER.serializeKernel(rwHeader, MSG_CONFIG, out, TestAmatInfo.newNamespace());
        assertTrue(TestUtils.compareUpToShortestArrays(ARRAY_MSG_HEADER, out.backingArray()));
    }


}
