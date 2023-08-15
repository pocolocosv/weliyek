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
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat2.protocol.field.Protocol;
import weliyek.amat2.protocol.field.ReadOperationDataStore;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;
import weliyek.bitcoin.BitcoinInventoryVector;
import weliyek.bitcoin.BitcoinInventoryVectorType;
import weliyek.bitcoin.BtcProtoContext;
import weliyek.bitcoin.BtcProtoFieldInvectoryVector;

public class BitcoinProtocolFieldInvVecTest
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinProtocolFieldInvVecTest.class);

    final byte[] TYPE_MSG_TX_BYTES = new byte[] {
            (byte)(0xFF & BitcoinInventoryVectorType.MSG_TX.code()),
            (byte)(0xFF & (BitcoinInventoryVectorType.MSG_TX.code() >> 8)),
            (byte)(0xFF & (BitcoinInventoryVectorType.MSG_TX.code() >> 16)),
            (byte)(0xFF & (BitcoinInventoryVectorType.MSG_TX.code() >> 24)),
    };

    final byte[] HASH_BYTES = new byte[] {
            (byte)0xd3, (byte)0x9f, (byte)0x60, (byte)0x8a, (byte)0x77, (byte)0x75, (byte)0xb5, (byte)0x37,
            (byte)0x72, (byte)0x98, (byte)0x84, (byte)0xd4, (byte)0xe6, (byte)0x63, (byte)0x3b, (byte)0xb2,
            (byte)0x10, (byte)0x5e, (byte)0x55, (byte)0xa1, (byte)0x6a, (byte)0x14, (byte)0xd3, (byte)0x1b,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00
    };

    private BtcProtoFieldInvectoryVector<BtcProtoContext> INV_VEC_FIELD;

    private Protocol<BtcProtoContext,
                     BtcProtoFieldInvectoryVector<BtcProtoContext>,
                     BitcoinInventoryVector,
                     BitcoinInventoryVector,
                     WriteData<BtcProtoContext, BitcoinInventoryVector>,
                     ReadData<BtcProtoContext, BitcoinInventoryVector>,
                     WriteDataBuilder
                         <BtcProtoContext,
                          BitcoinInventoryVector,
                          WriteData<BtcProtoContext, BitcoinInventoryVector>,
                          BtcProtoFieldInvectoryVector<BtcProtoContext>>,
                     ReadDataBuilder
                         <BtcProtoContext,
                          BitcoinInventoryVector,
                          ReadData<BtcProtoContext, BitcoinInventoryVector>,
                          BtcProtoFieldInvectoryVector<BtcProtoContext>>> PROTOCOL;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        INV_VEC_FIELD = new BtcProtoFieldInvectoryVector<>(Collections.emptyList());
        PROTOCOL = Protocol.withMainField(INV_VEC_FIELD, new BasicDataBuildersSupplier<>());
    }

    @Test
    public void test() {
        BtcProtoContext context = mock(BtcProtoContext.class);
        byte[] allBytes = new byte[TYPE_MSG_TX_BYTES.length + HASH_BYTES.length];
        System.arraycopy(TYPE_MSG_TX_BYTES, 0, allBytes, 0, TYPE_MSG_TX_BYTES.length);
        System.arraycopy(HASH_BYTES, 0, allBytes, TYPE_MSG_TX_BYTES.length, HASH_BYTES.length);
        ByteArrayInputStream input = new ByteArrayInputStream(allBytes);
        ReadOperationDataStore<BtcProtoContext> allReadInfo1 = PROTOCOL.read(context, input, (c,rb) -> {});
        BitcoinInventoryVector invVec = allReadInfo1.getLatestDeserializedOrThrow(INV_VEC_FIELD);
        assertNotNull(invVec);
        assertEquals(BitcoinInventoryVectorType.MSG_TX, invVec.getType());
    }

}
