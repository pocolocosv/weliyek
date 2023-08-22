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
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat2.protocol.field.Protocol;
import weliyek.amat2.protocol.field.ReadOperationDataStore;
import weliyek.amat2.protocol.field.ReadOperationSequence;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;
import weliyek.bitcoin.WkBitcoinNodeServices;
import weliyek.bitcoin.WkBitcoinServiceFlag;
import weliyek.bitcoin.BtcProtoContext;
import weliyek.bitcoin.BtcProtoFieldService;
import weliyek.ketza.util.KetzaByteOutputStream;

class BtcProtoFieldServiceTest
{

    public static final Logger logger = LoggerFactory.getLogger(BtcProtoFieldServiceTest.class);

    private static BtcProtoFieldService<BtcProtoContext> SERVICES_FIELD;

    private static Protocol<BtcProtoContext,
                            BtcProtoFieldService<BtcProtoContext>,
                            WkBitcoinNodeServices,
                            WkBitcoinNodeServices,
                            WriteData<BtcProtoContext, WkBitcoinNodeServices>,
                            ReadData<BtcProtoContext, WkBitcoinNodeServices>,
                            WriteDataBuilder
                                <BtcProtoContext,
                                 WkBitcoinNodeServices,
                                 WriteData<BtcProtoContext, WkBitcoinNodeServices>,
                                 BtcProtoFieldService<BtcProtoContext>>,
                            ReadDataBuilder
                                <BtcProtoContext,
                                 WkBitcoinNodeServices,
                                 ReadData<BtcProtoContext, WkBitcoinNodeServices>,
                                 BtcProtoFieldService<BtcProtoContext>>> PROTOCOL;

    final static byte[] BYTES = new byte[] { (byte)0xBF, (byte)0xAA, (byte)0xAA, (byte)0xAA,
                                              (byte)0xAA, (byte)0xAA, (byte)0xAA, (byte)0xAA};

    final int BIT_COUNT =   Integer.bitCount(0xFF & BYTES[0]) + Integer.bitCount(0xFF & BYTES[1])
                          + Integer.bitCount(0xFF & BYTES[2]) + Integer.bitCount(0xFF & BYTES[3])
                          + Integer.bitCount(0xFF & BYTES[4]) + Integer.bitCount(0xFF & BYTES[5])
                          + Integer.bitCount(0xFF & BYTES[6]) + Integer.bitCount(0xFF & BYTES[7]);

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        SERVICES_FIELD = new BtcProtoFieldService<>(Collections.emptyList());
        PROTOCOL = Protocol.withMainField(SERVICES_FIELD, new BasicDataBuildersSupplier<>());
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void test() {
        logger.info("=== Reading " + BYTES.length + " bits into EnumSet<" + WkBitcoinServiceFlag.class.getSimpleName() + "> ===");
        ByteArrayInputStream input = new ByteArrayInputStream(BYTES);
        ReadOperationDataStore<BtcProtoContext> allReadInfo1 = PROTOCOL.read(mock(BtcProtoContext.class), input, (a,b) -> {});
        assertNotNull(allReadInfo1);
        ReadOperationSequence<BtcProtoContext, WkBitcoinNodeServices> infoList = allReadInfo1.getLastReadSequenceFor(SERVICES_FIELD);
        assertNotNull(infoList);
        Optional<ReadData<BtcProtoContext, WkBitcoinNodeServices>> servicesInfo = infoList.getLatestOpResult();
        assertTrue(servicesInfo.isPresent());
        assertTrue(servicesInfo.get().deserialized().isPresent());
        WkBitcoinNodeServices readServices1 = servicesInfo.get().deserialized().get();
        assertEquals(BIT_COUNT, readServices1.size());
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.NODE_NETWORK));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.NODE_GETUTXO));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.NODE_BLOOM));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.NODE_WITNESS));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.NODE_XTHIN));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT05));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT07));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT09));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT11));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT13));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT15));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT17));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT19));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT21));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT23));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT25));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT27));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT29));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT31));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT33));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT35));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT37));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT39));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT41));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT43));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT45));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT47));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT49));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT51));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT53));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT55));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT57));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT59));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT61));
        assertTrue(readServices1.contains(WkBitcoinServiceFlag.BIT63));

        logger.info("=== Writing EnumSet<" + WkBitcoinServiceFlag.class.getSimpleName() + "> to an OutputStream ===");
        KetzaByteOutputStream output = new KetzaByteOutputStream();
        PROTOCOL.write(mock(BtcProtoContext.class), readServices1, output, (a,b) -> {});
        assertEquals(8, output.size());

        logger.info("=== Reading back EnumSet<" + WkBitcoinServiceFlag.class.getSimpleName() + "> from OutputStream ===");
        ReadOperationDataStore<BtcProtoContext> allReadInfo2 = PROTOCOL.read(mock(BtcProtoContext.class), output.inputStream(), (a,b) -> {});
        WkBitcoinNodeServices readServices2 = allReadInfo2.getLatestDeserializedOrThrow(SERVICES_FIELD);
        assertEquals(readServices1, readServices2);
    }

}
