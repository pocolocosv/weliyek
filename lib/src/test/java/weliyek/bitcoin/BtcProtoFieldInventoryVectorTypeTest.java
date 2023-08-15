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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import weliyek.amat2.protocol.field.Protocol;
import weliyek.amat2.protocol.field.ReadOperationDataStore;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;
import weliyek.bitcoin.BitcoinInventoryVectorType;
import weliyek.bitcoin.BtcProtoContext;
import weliyek.bitcoin.BtcProtoFieldInventoryVectorType;
import weliyek.ketza.util.KetzaByteOutputStream;

class BtcProtoFieldInventoryVectorTypeTest
{

    static final byte[] ERROR_BYTES = new byte[] {
            (byte)(0xFF & BitcoinInventoryVectorType.ERROR.code()),
            (byte)(0xFF & (BitcoinInventoryVectorType.ERROR.code() >> 8)),
            (byte)(0xFF & (BitcoinInventoryVectorType.ERROR.code() >> 16)),
            (byte)(0xFF & (BitcoinInventoryVectorType.ERROR.code() >> 24)),
    };

    private static BtcProtoFieldInventoryVectorType<BtcProtoContext> INV_VEC_TYPE_FIELD;

    private static Protocol<BtcProtoContext,
                            BtcProtoFieldInventoryVectorType<BtcProtoContext>,
                            BitcoinInventoryVectorType,
                            BitcoinInventoryVectorType,
                            WriteData<BtcProtoContext, BitcoinInventoryVectorType>,
                            ReadData<BtcProtoContext, BitcoinInventoryVectorType>,
                            WriteDataBuilder
                                <BtcProtoContext,
                                 BitcoinInventoryVectorType,
                                 WriteData<BtcProtoContext, BitcoinInventoryVectorType>,
                                 BtcProtoFieldInventoryVectorType<BtcProtoContext>>,
                            ReadDataBuilder
                                <BtcProtoContext,
                                 BitcoinInventoryVectorType,
                                 ReadData<BtcProtoContext, BitcoinInventoryVectorType>,
                                 BtcProtoFieldInventoryVectorType<BtcProtoContext>>> PROTOCOL;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        INV_VEC_TYPE_FIELD = new BtcProtoFieldInventoryVectorType<>(Collections.emptyList());
        PROTOCOL = Protocol.withMainField(INV_VEC_TYPE_FIELD, new BasicDataBuildersSupplier<>());
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        ByteArrayInputStream input = new ByteArrayInputStream(ERROR_BYTES);
        ReadOperationDataStore<BtcProtoContext> allReadInfo1 = PROTOCOL.read(mock(BtcProtoContext.class), input, (a,b) -> {});
        Optional<ReadData<BtcProtoContext, BitcoinInventoryVectorType>> typeInfo = allReadInfo1.getLatestReadOpResult(INV_VEC_TYPE_FIELD);
        assertTrue(typeInfo.isPresent());
        assertTrue(typeInfo.get().deserialized().isPresent());
        BitcoinInventoryVectorType readType1 = typeInfo.get().deserialized().get();
        assertEquals(BitcoinInventoryVectorType.ERROR, readType1);

        KetzaByteOutputStream output = new KetzaByteOutputStream();
        PROTOCOL.write(mock(BtcProtoContext.class), readType1, output, (a,b) -> {});
        assertEquals(4, output.size());

        ReadOperationDataStore<BtcProtoContext> allReadInfo2 = PROTOCOL.read(mock(BtcProtoContext.class), output.inputStream(), (a,b) -> {});
        BitcoinInventoryVectorType readType2 = allReadInfo2.getLatestDeserializedOrThrow(INV_VEC_TYPE_FIELD);
        assertEquals(readType1, readType2);
    }

}
