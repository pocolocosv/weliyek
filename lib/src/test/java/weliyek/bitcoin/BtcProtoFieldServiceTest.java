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
import weliyek.bitcoin.BitcoinNodeServices;
import weliyek.bitcoin.BitcoinServiceFlag;
import weliyek.bitcoin.BtcProtoContext;
import weliyek.bitcoin.BtcProtoFieldService;
import weliyek.ketza.util.KetzaByteOutputStream;

class BtcProtoFieldServiceTest
{

    public static final Logger logger = LoggerFactory.getLogger(BtcProtoFieldServiceTest.class);

    private static BtcProtoFieldService<BtcProtoContext> SERVICES_FIELD;

    private static Protocol<BtcProtoContext,
                            BtcProtoFieldService<BtcProtoContext>,
                            BitcoinNodeServices,
                            BitcoinNodeServices,
                            WriteData<BtcProtoContext, BitcoinNodeServices>,
                            ReadData<BtcProtoContext, BitcoinNodeServices>,
                            WriteDataBuilder
                                <BtcProtoContext,
                                 BitcoinNodeServices,
                                 WriteData<BtcProtoContext, BitcoinNodeServices>,
                                 BtcProtoFieldService<BtcProtoContext>>,
                            ReadDataBuilder
                                <BtcProtoContext,
                                 BitcoinNodeServices,
                                 ReadData<BtcProtoContext, BitcoinNodeServices>,
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
        logger.info("=== Reading " + BYTES.length + " bits into EnumSet<" + BitcoinServiceFlag.class.getSimpleName() + "> ===");
        ByteArrayInputStream input = new ByteArrayInputStream(BYTES);
        ReadOperationDataStore<BtcProtoContext> allReadInfo1 = PROTOCOL.read(mock(BtcProtoContext.class), input, (a,b) -> {});
        assertNotNull(allReadInfo1);
        ReadOperationSequence<BtcProtoContext, BitcoinNodeServices> infoList = allReadInfo1.getLastReadSequenceFor(SERVICES_FIELD);
        assertNotNull(infoList);
        Optional<ReadData<BtcProtoContext, BitcoinNodeServices>> servicesInfo = infoList.getLatestOpResult();
        assertTrue(servicesInfo.isPresent());
        assertTrue(servicesInfo.get().deserialized().isPresent());
        BitcoinNodeServices readServices1 = servicesInfo.get().deserialized().get();
        assertEquals(BIT_COUNT, readServices1.size());
        assertTrue(readServices1.contains(BitcoinServiceFlag.NODE_NETWORK));
        assertTrue(readServices1.contains(BitcoinServiceFlag.NODE_GETUTXO));
        assertTrue(readServices1.contains(BitcoinServiceFlag.NODE_BLOOM));
        assertTrue(readServices1.contains(BitcoinServiceFlag.NODE_WITNESS));
        assertTrue(readServices1.contains(BitcoinServiceFlag.NODE_XTHIN));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT05));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT07));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT09));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT11));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT13));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT15));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT17));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT19));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT21));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT23));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT25));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT27));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT29));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT31));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT33));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT35));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT37));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT39));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT41));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT43));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT45));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT47));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT49));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT51));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT53));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT55));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT57));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT59));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT61));
        assertTrue(readServices1.contains(BitcoinServiceFlag.BIT63));

        logger.info("=== Writing EnumSet<" + BitcoinServiceFlag.class.getSimpleName() + "> to an OutputStream ===");
        KetzaByteOutputStream output = new KetzaByteOutputStream();
        PROTOCOL.write(mock(BtcProtoContext.class), readServices1, output, (a,b) -> {});
        assertEquals(8, output.size());

        logger.info("=== Reading back EnumSet<" + BitcoinServiceFlag.class.getSimpleName() + "> from OutputStream ===");
        ReadOperationDataStore<BtcProtoContext> allReadInfo2 = PROTOCOL.read(mock(BtcProtoContext.class), output.inputStream(), (a,b) -> {});
        BitcoinNodeServices readServices2 = allReadInfo2.getLatestDeserializedOrThrow(SERVICES_FIELD);
        assertEquals(readServices1, readServices2);
    }

}
