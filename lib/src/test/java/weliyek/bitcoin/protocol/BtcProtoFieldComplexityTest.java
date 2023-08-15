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
package weliyek.bitcoin.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
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
import weliyek.bitcoin.BitcoinComplexity;
import weliyek.ketza.util.KetzaByteOutputStream;

public class BtcProtoFieldComplexityTest
{

    public static final Logger logger = LoggerFactory.getLogger(BtcProtoFieldComplexityTest.class);

    final static byte[] COMPACT1_BYTES = new byte[] {       0x56,       0x34,       0x12, 0x05};
    final static byte[] COMPACT2_BYTES = new byte[] { (byte)0xDE, (byte)0xC0,       0x00, 0x06};
    final static byte[] COMPACT3_BYTES = new byte[] {       0x56,       0x34, (byte)0x92, 0x04};
    final static byte[] COMPACT4_BYTES = new byte[] {       0x56,       0x34,       0x12, 0x04};
    final static byte[] COMPACT5_BYTES = new byte[] { (byte)0x00, (byte)0xDE, (byte)0xC0, 0x05};

    private static BtcProtoFieldComplexity<BtcProtoContext> COMPLEXITY_FIELD;

    private static Protocol<BtcProtoContext,
                            BtcProtoFieldComplexity<BtcProtoContext>,
                            BitcoinComplexity,
                            BitcoinComplexity,
                            WriteData<BtcProtoContext, BitcoinComplexity>,
                            ReadData<BtcProtoContext, BitcoinComplexity>,
                            WriteDataBuilder
                                <BtcProtoContext,
                                 BitcoinComplexity,
                                 WriteData<BtcProtoContext, BitcoinComplexity>,
                                 BtcProtoFieldComplexity<BtcProtoContext>>,
                            ReadDataBuilder
                                <BtcProtoContext,
                                 BitcoinComplexity,
                                 ReadData<BtcProtoContext, BitcoinComplexity>,
                                 BtcProtoFieldComplexity<BtcProtoContext>>> PROTOCOL;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        COMPLEXITY_FIELD = new BtcProtoFieldComplexity<>(Collections.emptyList());
        PROTOCOL = Protocol.withMainField(COMPLEXITY_FIELD, new BasicDataBuildersSupplier<>());
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        logger.info("=== Reading compact int into " + BitcoinComplexity.class.getSimpleName() + " ===");
        ByteArrayInputStream compact1Input = new ByteArrayInputStream(COMPACT1_BYTES);
        ReadOperationDataStore<BtcProtoContext> allReadInfo = PROTOCOL.read(mock(BtcProtoContext.class), compact1Input, (a,b) -> {});
        assertNotNull(allReadInfo);
        ReadOperationSequence<BtcProtoContext, BitcoinComplexity> compact1ReadList = allReadInfo.getLastReadSequenceFor(COMPLEXITY_FIELD);
        assertTrue(compact1ReadList.getLatestOpResult().isPresent());
        ReadData<BtcProtoContext, BitcoinComplexity> readCompact1Info = compact1ReadList.getLatestOpResult().get();
        assertTrue(readCompact1Info.deserialized().isPresent());
        BitcoinComplexity readComplexity1 = readCompact1Info.deserialized().get();

        logger.info("=== Writing " + BitcoinComplexity.class.getSimpleName() + " to an OutputStream ===");
        KetzaByteOutputStream output = new KetzaByteOutputStream();
        PROTOCOL.write(mock(BtcProtoContext.class), readComplexity1, output, (a,b) -> {});
        assertEquals(4, output.size());

        logger.info("=== Reading " + BitcoinComplexity.class.getSimpleName() + " back from OutputStream ===");
        ReadOperationDataStore<BtcProtoContext> allReadInfo2 = PROTOCOL.read(mock(BtcProtoContext.class), output.inputStream(), (a,b) -> {});
        BitcoinComplexity readComplexity1B = allReadInfo2.getLatestDeserializedOrThrow(COMPLEXITY_FIELD);
        assertEquals(readComplexity1, readComplexity1B);
    }

    /*
    @Test
    public void test() {
        assertEquals(0x05123456, BitcoinComplexity.convertBigIntegerToCompact(new BigInteger("1234560000", 16)));
        assertEquals(0x0600C0DE, BitcoinComplexity.convertBigIntegerToCompact(new BigInteger("C0DE000000", 16)));
        assertEquals(0x04923456, BitcoinComplexity.convertBigIntegerToCompact(new BigInteger("12345600", 16).negate()));
        assertEquals(0x04123456, BitcoinComplexity.convertBigIntegerToCompact(new BigInteger("12345600", 16)));
        assertEquals(0x05C0DE00, BitcoinComplexity.convertBigIntegerToCompact(new BigInteger("40de000000", 16).negate()));
    }

    @Test
    public void testConversionFromCompact() {
        assertEquals(new BigInteger("1234560000", 16), BitcoinComplexity.convertCompactToBigInteger(0x05123456));
        assertEquals(new BigInteger("C0DE000000", 16), BitcoinComplexity.convertCompactToBigInteger(0x0600C0DE));
        assertEquals(new BigInteger("12345600", 16).negate(), BitcoinComplexity.convertCompactToBigInteger(0x04923456));
        assertEquals(new BigInteger("12345600", 16), BitcoinComplexity.convertCompactToBigInteger(0x04123456));
        assertEquals(new BigInteger("40de000000", 16).negate(), BitcoinComplexity.convertCompactToBigInteger(0x05C0DE00));
    }
    */

}
