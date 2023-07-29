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
package weliyek.ketza.util.array;

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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketStructure;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.InputPacket;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.OutputPacket;
import weliyek.amat.basic.number.SignedBigEndianInteger;
import weliyek.amat.basic.number.SignedBigEndianIntegerDeserializing;
import weliyek.amat.basic.number.SignedBigEndianIntegerSerializing;
import weliyek.amat2.protocol.PacketOperationCoreException;
import weliyek.ketza.util.KetzaByteOutputStream;

public class DynamicByteArrayTest
{

  private static final Logger logger = LoggerFactory.getLogger(DynamicByteArrayTest.class);

  private static PacketStructure<
                    ByteArrayWrapper,
                    OperationSettings,
                    DynamicByteArray<Integer, SignedBigEndianInteger, SignedBigEndianIntegerDeserializing, ?, ?, ? extends SignedBigEndianInteger>,
                    DynamicByteArrayDeserializing<Integer, SignedBigEndianIntegerDeserializing, SignedBigEndianInteger>,
                    InputBytestreamGeneralBase<?>,
                    OperationSettings,
                    DynamicByteArray<Integer, ?, ?, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, ? extends SignedBigEndianInteger>,
                    DynamicByteArraySerialzing<Integer, SignedBigEndianIntegerSerializing, SignedBigEndianInteger>,
                    OutputBytestreamGeneralBase<?>,
                    DynamicByteArray<Integer, SignedBigEndianInteger, SignedBigEndianIntegerDeserializing, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, SignedBigEndianInteger>>
                        DYNAMIC_BYTE_ARRAY;
  private static byte[] SHORT_BYTE_ARRAY;
  private static ByteArrayWrapper SHORT_ARRAY_WRAPPER;
  private static byte[] VALID_BYTE_ARRAY;
  private static ByteArrayWrapper VALID_ARRAY_WRAPPER;
  private static byte[] LONG_BYTE_ARRAY;
  private static ByteArrayWrapper LONG_ARRAY_WRAPPER;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    SHORT_BYTE_ARRAY = new byte[] { 0x00, 0x01};
    SHORT_ARRAY_WRAPPER = new ByteArrayWrapper(SHORT_BYTE_ARRAY);

    VALID_BYTE_ARRAY = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                                       0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F};
    VALID_ARRAY_WRAPPER = new ByteArrayWrapper(VALID_BYTE_ARRAY);

    LONG_BYTE_ARRAY = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
                                   0x08, 0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x00};
    LONG_ARRAY_WRAPPER = new ByteArrayWrapper(LONG_BYTE_ARRAY);

    DYNAMIC_BYTE_ARRAY = DynamicByteArray.
                                          newPacketStructure(
                                              "DYNBYTEARRAY",
                                              "SIZENUM",
                                              "VARBYTEARRAY",
                                              SHORT_BYTE_ARRAY.length + 1,
                                              VALID_BYTE_ARRAY.length,
                                              Integer::valueOf,
                                              SignedBigEndianInteger::newCore);
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
    OutputPacket<ByteArrayWrapper, DynamicByteArray<Integer, ?, ?, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, ? extends SignedBigEndianInteger>, DynamicByteArraySerialzing<Integer, SignedBigEndianIntegerSerializing, SignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(SHORT_ARRAY_WRAPPER,
                                                      OperationSettings.EMPTY,
                                                      outputstream);
    // This call will trigger serialization of the size part and initialize the
    // variable array which will trigger the exception.
    assertThrows(PacketOperationCoreException.class, () -> serializer.processBytestream());
  }

  @Test
  public void testDeserializationShortArray() {
    logger.info("Testing deserialization of invalid array shorter than the minimum");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    OutputPacket<ByteArrayWrapper, DynamicByteArray<Integer, ?, ?, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, ? extends SignedBigEndianInteger>, DynamicByteArraySerialzing<Integer, SignedBigEndianIntegerSerializing, SignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(VALID_ARRAY_WRAPPER,
                                                      OperationSettings.EMPTY,
                                                      outputstream);
    while(serializer.isInProgress()) {
      serializer.processBytestream();
    }

    // WARNING : this assumes that the length value can be held in the least significant
    // byte of the size in the byte array.
    byte[] allbytes = outputstream.arrayCopy();
    allbytes[3] = (byte) SHORT_ARRAY_WRAPPER.getLength(); // <- This invalidates the size
    ByteArrayInputStream inputstream = new ByteArrayInputStream(allbytes);

    InputPacket<ByteArrayWrapper, DynamicByteArray<Integer, SignedBigEndianInteger, SignedBigEndianIntegerDeserializing, ?, ?, ? extends SignedBigEndianInteger>, DynamicByteArrayDeserializing<Integer, SignedBigEndianIntegerDeserializing, SignedBigEndianInteger>>
      deserializer = DYNAMIC_BYTE_ARRAY.newInputPacket(OperationSettings.EMPTY, inputstream);

    deserializer.processBytestream();
    assertThrows(PacketOperationCoreException.class, () -> deserializer.processBytestream());
  }

  @Test
  public void testDeserializationLongArray() {
    logger.info("Testing deserialization of invalid array shorter than the minimum");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    OutputPacket<ByteArrayWrapper, DynamicByteArray<Integer, ?, ?, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, ? extends SignedBigEndianInteger>, DynamicByteArraySerialzing<Integer, SignedBigEndianIntegerSerializing, SignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(VALID_ARRAY_WRAPPER,
                                                      OperationSettings.EMPTY,
                                                      outputstream);
    while(serializer.isInProgress()) {
      serializer.processBytestream();
    }

    // WARNING : this assumes that the length value can be held in the least significant
    // byte of the size in the byte array.
    byte[] allbytes = outputstream.arrayCopy();
    allbytes[3] = (byte) LONG_ARRAY_WRAPPER.getLength(); // <- This invalidates the size
    ByteArrayInputStream inputstream = new ByteArrayInputStream(allbytes);

    InputPacket<ByteArrayWrapper, DynamicByteArray<Integer, SignedBigEndianInteger, SignedBigEndianIntegerDeserializing, ?, ?, ? extends SignedBigEndianInteger>, DynamicByteArrayDeserializing<Integer, SignedBigEndianIntegerDeserializing, SignedBigEndianInteger>>
      deserializer = DYNAMIC_BYTE_ARRAY.newInputPacket(OperationSettings.EMPTY, inputstream);

    deserializer.processBytestream();
    assertThrows(PacketOperationCoreException.class, () -> deserializer.processBytestream());
  }

  @Test
  public void testSerializationLongArray() {
    logger.info("Testing array longer than maximum");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    OutputPacket<ByteArrayWrapper, DynamicByteArray<Integer, ?, ?, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, ? extends SignedBigEndianInteger>, DynamicByteArraySerialzing<Integer, SignedBigEndianIntegerSerializing, SignedBigEndianInteger>>
      serializer = DYNAMIC_BYTE_ARRAY.newOutputPacket(LONG_ARRAY_WRAPPER,
                                                      OperationSettings.EMPTY,
                                                      outputstream);
    // This call will trigger serialization of the size part and initialize the
    // variable array which will trigger the exception.
    assertThrows(PacketOperationCoreException.class, () -> serializer.processBytestream());
  }

  @Test
  public void testValidArray() {
    logger.info("Testing valid array within bounds");
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    OutputPacket<ByteArrayWrapper, DynamicByteArray<Integer, ?, ?, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, ? extends SignedBigEndianInteger>, DynamicByteArraySerialzing<Integer, SignedBigEndianIntegerSerializing, SignedBigEndianInteger>>
      dynArrayWriting = DYNAMIC_BYTE_ARRAY.newOutputPacket(VALID_ARRAY_WRAPPER,
                                                           OperationSettings.EMPTY,
                                                           outputstream);
    logger.info(dynArrayWriting.name() + " created");
    while(dynArrayWriting.isInProgress()) {
      dynArrayWriting.processBytestream();
      if (dynArrayWriting.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info(DYNAMIC_BYTE_ARRAY.definition().size().field().definition() + " writing completed");
    assertEquals(DYNAMIC_BYTE_ARRAY.definition().size().field().definition(),
                 dynArrayWriting.previousProcessingSteapResult().get().definition());
    assertEquals(VALID_ARRAY_WRAPPER.getLength(), dynArrayWriting.firstOperation().get().size().field().get().firstOperation().get().serializable().intValue());
    while(dynArrayWriting.isInProgress()) {
      dynArrayWriting.processBytestream();
      if (dynArrayWriting.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info(DYNAMIC_BYTE_ARRAY.definition().variableSequence().field().definition() + " writing completed");
    assertEquals(DYNAMIC_BYTE_ARRAY.definition().variableSequence().field().definition(),
                 dynArrayWriting.previousProcessingSteapResult().get().definition());
    assertEquals(VALID_ARRAY_WRAPPER, dynArrayWriting.firstOperation().get().variableSequence().field().get().firstOperation().get().serializable());
    InputPacket<ByteArrayWrapper, DynamicByteArray<Integer, SignedBigEndianInteger, SignedBigEndianIntegerDeserializing, ?, ?, ? extends SignedBigEndianInteger>, DynamicByteArrayDeserializing<Integer, SignedBigEndianIntegerDeserializing, SignedBigEndianInteger>>
      dynArrayReading = DYNAMIC_BYTE_ARRAY.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
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
                                .size().field().get()
                                .firstOperation().get()
                                .result().get()
                                .deserialized().get().intValue());
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
                                .variableSequence().field().get()
                                .firstOperation().get()
                                .result().get().deserialized().get());
    assertEquals(VALID_ARRAY_WRAPPER,
                 dynArrayReading.firstOperation().get()
                                .result().get().deserialized().get());
  }

}
