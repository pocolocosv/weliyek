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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.Collections;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.io.LittleEndianDataOutputStream;

import weliyek.amat2.protocol.field.Protocol;
import weliyek.amat2.protocol.field.ReadOperationDataStore;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;
import weliyek.bitcoin.BitcoinConfig;
import weliyek.bitcoin.VarIntField;
import weliyek.ketza.util.KetzaByteOutputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VarIntFieldTest
{

    public static final Logger logger = LoggerFactory.getLogger(VarIntFieldTest.class);

    final byte[]                  array_0x00 = new byte[] {        0x00 };
    final byte[]                  array_0x01 = new byte[] {        0x01 };
    final byte[]                  array_0xFC = new byte[] { (byte) 0xFC };
    final byte[]                array_0x00FD = new byte[] { (byte) VarIntField.VARINT_HEADER_SHORT, (byte) 0xFD, (byte) 0x00 };
    final byte[]                array_0xFFFE = new byte[] { (byte) VarIntField.VARINT_HEADER_SHORT, (byte) 0xFE, (byte) 0xFF };
    final byte[]                array_0xFFFF = new byte[] { (byte) VarIntField.VARINT_HEADER_SHORT, (byte) 0xFF, (byte) 0xFF };
    final byte[]           array_0x0001_0000 = new byte[] { (byte) VarIntField.VARINT_HEADER_INT, (byte) 0x00, (byte) 0x00,        0x01,        0x00 };
    final byte[]           array_0xFFFF_FFFF = new byte[] { (byte) VarIntField.VARINT_HEADER_INT, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };
    final byte[] array_0x0000_0001_0000_0000 = new byte[] { (byte) VarIntField.VARINT_HEADER_LONG,       0x00,        0x00,        0x00,        0x00,
                                                                                                            0x01,        0x00,        0x00,        0x00 };
    final byte[] array_0xFFFF_FFFF_FFFF_FFFF = new byte[] { (byte) VarIntField.VARINT_HEADER_LONG, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF,
                                                                                                      (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF };


    final BitcoinConfig CONFIG = mock(BitcoinConfig.class);

    private static KetzaByteOutputStream BUFFER;
    LittleEndianDataOutputStream out;

    private static VarIntField<BitcoinConfig> VARINT_FIELD;

    private static Protocol<BitcoinConfig,
                            VarIntField<BitcoinConfig>,
                            Long,
                            Number,
                            WriteData<BitcoinConfig, Long>,
                            ReadData<BitcoinConfig, Number>,
                            WriteDataBuilder
                                <BitcoinConfig,
                                 Long,
                                 WriteData<BitcoinConfig, Long>,
                                 VarIntField<BitcoinConfig>>,
                            ReadDataBuilder
                                <BitcoinConfig,
                                 Number,
                                 ReadData<BitcoinConfig, Number>,
                                 VarIntField<BitcoinConfig>>> PROTOCOL;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        VARINT_FIELD = new VarIntField<>(Collections.emptyList());
        PROTOCOL = Protocol.withMainField(VARINT_FIELD, new BasicDataBuildersSupplier<>());
        BUFFER = new KetzaByteOutputStream();
    }

    @Before
    public void setUp() throws Exception {
        BUFFER.reset();
    }

    @Test
    public void test01_byte_0x00() throws IOException {
        logger.info("=== VarInt 0x00 ===");
        test_value(0x00, array_0x00);
    }

    @Test
    public void test02_byte_0x01() throws IOException {
        logger.info("================== VarInt 0x01 ==================");
        test_value(0x01, array_0x01);
    }

    @Test
    public void test03_byte_0xFC() throws IOException {
        logger.info("================== VarInt 0xFC ==================");
        test_value(0xFC, array_0xFC);
    }

    @Test
    public void test04_short_0x00FD() throws IOException {
        logger.info("================== VarInt 0xFD ==================");
        test_value(0xFD, array_0x00FD);
    }

    @Test
    public void test05_short_0xFFFE() throws IOException {
        logger.info("================= VarInt 0xFFFE =================");
        test_value(0xFFFE, array_0xFFFE);
    }

    @Test
    public void test06_short_0xFFFF() throws IOException {
        logger.info("================= VarInt 0xFFFF =================");
        test_value(0xFFFF, array_0xFFFF);
    }

    @Test
    public void test07_int_0x10000() throws IOException {
        logger.info("================ VarInt 0x1_0000 ================");
        test_value(0x1_0000, array_0x0001_0000);
    }

    @Test
    public void test08_int_0xFFFF_FFFF() throws IOException {
        logger.info("=============== VarInt 0xFFFF_FFFF ==============");
        test_value(0xFFFF_FFFFL, array_0xFFFF_FFFF);
    }

    @Test
    public void test09_long_0x1_0000_0000() throws IOException {
        logger.info("============== VarInt 0x1_0000_0000 =============");
        test_value(0x1_0000_0000L, array_0x0000_0001_0000_0000);
    }

    @Test
    public void test10_long_0xFFFF_FFFF_FFFF_FFFF() throws IOException {
        logger.info("========== VarInt 0xFFFF_FFFF_FFFF_FFFF ==========");
        test_value(0xFFFF_FFFF_FFFF_FFFFL, array_0xFFFF_FFFF_FFFF_FFFF);
    }

    private void test_value(long val, byte[] varint_array) {
        logger.info("---------- Writting ----------");
        Long writeVal = Long.valueOf(val);
        PROTOCOL.write(CONFIG, writeVal, BUFFER, (c,wb) -> {});
        assertArrayEquals(varint_array, BUFFER.arrayCopy());
        logger.info("---------- Reading ----------");
        ReadOperationDataStore<BitcoinConfig> readResults = PROTOCOL.read(CONFIG, BUFFER.inputStream(), (c,rb) -> {});
        Number readVal = readResults.getLatestDeserializedOrThrow(PROTOCOL.top);
        assertEquals(writeVal, readVal);
    }

}
