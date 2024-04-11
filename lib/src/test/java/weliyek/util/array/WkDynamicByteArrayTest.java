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
package weliyek.util.array;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSerdeDtreeNodeDataOperationException;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkDynamicByteArrayTest
{

  private static final Logger logger = LoggerFactory.getLogger(WkDynamicByteArrayTest.class);

  private static WkSerdeDtreeStruct<
                    WkByteArray,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeDynamicByteArray<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                    WkSerdeDynamicByteArrayReader<Integer, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger>,
                    WkSerdeDtreeBytestreamInputBase<?>,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeDynamicByteArray<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                    WkSerdeDynamicByteArrayWriter<Integer, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>,
                    WkSerdeDtreeBytestreamOutputBase<?>,
                    WkSerdeDynamicByteArray<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
                        DYNAMIC_BYTE_ARRAY;
  private static byte[] SHORT_BYTE_ARRAY;
  private static WkByteArray SHORT_ARRAY_WRAPPER;
  private static byte[] VALID_BYTE_ARRAY;
  private static WkByteArray VALID_ARRAY_WRAPPER;
  private static byte[] LONG_BYTE_ARRAY;
  private static WkByteArray LONG_ARRAY_WRAPPER;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    SHORT_BYTE_ARRAY = new byte[] { 0x00, 0x01};
    SHORT_ARRAY_WRAPPER = new WkByteArray(SHORT_BYTE_ARRAY);

    VALID_BYTE_ARRAY = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                                       0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
    VALID_ARRAY_WRAPPER = new WkByteArray(VALID_BYTE_ARRAY);

    LONG_BYTE_ARRAY = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                                   0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x00};
    LONG_ARRAY_WRAPPER = new WkByteArray(LONG_BYTE_ARRAY);

    DYNAMIC_BYTE_ARRAY = WkSerdeDynamicByteArray.
                                          newStruct(
                                              "DYNBYTEARRAY",
                                              "SIZENUM",
                                              "VARBYTEARRAY",
                                              SHORT_BYTE_ARRAY.length + 1,
                                              VALID_BYTE_ARRAY.length,
                                              Integer::valueOf,
                                              WkSerdeSignedBigEndianInteger::newCore);
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testSerializationShortArray() {
    logger.info("Testing array smaller than minimum");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSzOutputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayWriter<Integer, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(SHORT_ARRAY_WRAPPER,
                                                      WkSerdeDtreeOperationSettings.EMPTY,
                                                      outputstream);
    // This call will trigger serialization of the size part and initialize the
    // variable array which will trigger the exception.
    assertThrows(WkSerdeDtreeNodeDataOperationException.class, () -> serializer.processBytestream());
  }

  @Test
  public void testDeserializationShortArray() {
    logger.info("Testing deserialization of invalid array shorter than the minimum");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSzOutputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayWriter<Integer, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(VALID_ARRAY_WRAPPER,
                                                      WkSerdeDtreeOperationSettings.EMPTY,
                                                      outputstream);
    while(serializer.isInProgress()) {
      serializer.processBytestream();
    }

    // WARNING : this assumes that the length value can be held in the least significant
    // byte of the size in the byte array.
    byte[] allbytes = outputstream.arrayCopy();
    allbytes[3] = (byte) SHORT_ARRAY_WRAPPER.getLength(); // <- This invalidates the size
    ByteArrayInputStream inputstream = new ByteArrayInputStream(allbytes);

    WkSzInputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayReader<Integer, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger>>
      deserializer = DYNAMIC_BYTE_ARRAY.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, inputstream);

    deserializer.processBytestream();
    assertThrows(WkSerdeDtreeNodeDataOperationException.class, () -> deserializer.processBytestream());
  }

  @Test
  public void testDeserializationLongArray() {
    logger.info("Testing deserialization of invalid array shorter than the minimum");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSzOutputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayWriter<Integer, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(VALID_ARRAY_WRAPPER,
                                                      WkSerdeDtreeOperationSettings.EMPTY,
                                                      outputstream);
    while(serializer.isInProgress()) {
      serializer.processBytestream();
    }

    // WARNING : this assumes that the length value can be held in the least significant
    // byte of the size in the byte array.
    byte[] allbytes = outputstream.arrayCopy();
    allbytes[3] = (byte) LONG_ARRAY_WRAPPER.getLength(); // <- This invalidates the size
    ByteArrayInputStream inputstream = new ByteArrayInputStream(allbytes);

    WkSzInputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayReader<Integer, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger>>
      deserializer = DYNAMIC_BYTE_ARRAY.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, inputstream);

    deserializer.processBytestream();
    assertThrows(WkSerdeDtreeNodeDataOperationException.class, () -> deserializer.processBytestream());
  }

  @Test
  public void testSerializationLongArray() {
    logger.info("Testing array longer than maximum");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSzOutputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayWriter<Integer, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(LONG_ARRAY_WRAPPER,
                                                      WkSerdeDtreeOperationSettings.EMPTY,
                                                      outputstream);
    // This call will trigger serialization of the size part and initialize the
    // variable array which will trigger the exception.
    assertThrows(WkSerdeDtreeNodeDataOperationException.class, () -> serializer.processBytestream());
  }

  @Test
  public void testValidArray() {
    logger.info("Testing valid array within bounds");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSzOutputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayWriter<Integer, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
      dynArrayWriting = DYNAMIC_BYTE_ARRAY.newOutputPacket(VALID_ARRAY_WRAPPER,
                                                           WkSerdeDtreeOperationSettings.EMPTY,
                                                           outputstream);
    logger.info(dynArrayWriting.name() + " created");
    while(dynArrayWriting.isInProgress()) {
      dynArrayWriting.processBytestream();
      if (dynArrayWriting.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info(DYNAMIC_BYTE_ARRAY.definition().size().field().definition() + " writing completed");
    assertEquals(DYNAMIC_BYTE_ARRAY.definition().size().field().definition(),
                 dynArrayWriting.previousProcessingSteapResult().get().definition());
    assertEquals(VALID_ARRAY_WRAPPER.getLength(), dynArrayWriting.firstOperation().get().size().get().firstOperation().get().serializable().intValue());
    while(dynArrayWriting.isInProgress()) {
      dynArrayWriting.processBytestream();
      if (dynArrayWriting.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info(DYNAMIC_BYTE_ARRAY.definition().variableSequence().field().definition() + " writing completed");
    assertEquals(DYNAMIC_BYTE_ARRAY.definition().variableSequence().field().definition(),
                 dynArrayWriting.previousProcessingSteapResult().get().definition());
    assertEquals(VALID_ARRAY_WRAPPER, dynArrayWriting.firstOperation().get().variableSequence().get().firstOperation().get().serializable());
    WkSzInputPacket<WkByteArray, WkSerdeDynamicByteArray<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ? extends WkSerdeSignedBigEndianInteger>, WkSerdeDynamicByteArrayReader<Integer, WkSerdeSignedBigEndianIntegerReader, WkSerdeSignedBigEndianInteger>>
      dynArrayReading = DYNAMIC_BYTE_ARRAY.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(DYNAMIC_BYTE_ARRAY.definition().size().field().definition() + " reading started");
    while(dynArrayReading.isInProgress()) {
      dynArrayReading.processBytestream();
      if (dynArrayReading.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info(DYNAMIC_BYTE_ARRAY.definition().size().field().definition() + " reading completed");
    assertEquals(DYNAMIC_BYTE_ARRAY.definition().size().field().definition(),
                 dynArrayReading.previousProcessingSteapResult().get().definition());
    assertEquals(VALID_ARRAY_WRAPPER.getLength(),
                 dynArrayReading.firstOperation().get()
                                .size().get()
                                .firstOperation().get()
                                .result().get()
                                .serializable().get().intValue());
    logger.info(DYNAMIC_BYTE_ARRAY.definition().variableSequence().field().definition() + " reading started");
    while(dynArrayReading.isInProgress()) {
      dynArrayReading.processBytestream();
      if (dynArrayReading.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info(DYNAMIC_BYTE_ARRAY.definition().variableSequence().field().definition() + " reading completed");
    assertEquals(DYNAMIC_BYTE_ARRAY.definition().variableSequence().field().definition(),
                 dynArrayReading.previousProcessingSteapResult().get().definition());
    assertEquals(VALID_ARRAY_WRAPPER,
                 dynArrayReading.firstOperation().get()
                                .variableSequence().get()
                                .firstOperation().get()
                                .result().get().serializable().get());
    assertEquals(VALID_ARRAY_WRAPPER,
                 dynArrayReading.firstOperation().get()
                                .result().get().serializable().get());
  }

}
