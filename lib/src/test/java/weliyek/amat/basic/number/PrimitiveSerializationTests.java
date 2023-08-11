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
package weliyek.amat.basic.number;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.input.WkSzInputPacket;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzOutputPacket;
import weliyek.ketza.util.KetzaByteOutputStream;
import weliyek.serialization.WkSzPacketOperation;
import weliyek.serialization.WkSzStruct;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrimitiveSerializationTests {

  private static final Logger logger = LoggerFactory.getLogger(PrimitiveSerializationTests.class);

  @Test
  public void test01_SignedByte() {
    Byte b = Byte.valueOf((byte) 0xFF);
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSzStruct<Byte, WkSzOperationSettings, WkSzSignedByte, WkSzSignedByteReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzSignedByte, WkSzSignedByteWriter, WkSzOutputBytestreamBase<?>, WkSzSignedByte>
      signedBytePacketStructure = WkSzSignedByte.newPacketStructure("SINGLE_SINT8");

    logger.info(signedBytePacketStructure.name() + " created");

    WkSzOutputPacket<Byte, WkSzSignedByte, WkSzSignedByteWriter>
      byteOutput = signedBytePacketStructure.newOutputPacket(b, WkSzOperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = byteOutput.previousProcessingSteapResult();
    assertTrue(byteOutput.isCompleted());
    assertTrue(completedField.isPresent());
    assertEquals(byteOutput.firstOperation().get(), completedField.get());
    assertEquals(b, byteOutput.firstOperation().get().serializable());
    assertTrue(byteOutput.firstOperation().get().dashboard().bytestream().isClosed());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getFieldProcessedBytes());
    assertEquals(0, byteOutput.firstOperation().get().dashboard().bytestream().getStartIndexInGlobalBytestream());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getTotalPacketProcessedBytes());

    assertEquals(1, outputBuffer.size());

    WkSzInputPacket<Byte, WkSzSignedByte, WkSzSignedByteReader>
      signedByteReading = signedBytePacketStructure.newInputPacket(WkSzOperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(signedByteReading.name() + " created");

    assertFalse(signedByteReading.isCompleted());
    assertTrue(signedByteReading.isEnabled());
    signedByteReading.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> signedByteCompletedField = signedByteReading.previousProcessingSteapResult();
    assertEquals(signedByteReading.firstOperation().get(), signedByteCompletedField.get());
    assertTrue(signedByteReading.isCompleted());
    //assertTrue(signedByteReading.isRequiredByProtocol());
    assertTrue(signedByteReading.firstOperation().get().result().isPresent());
    assertTrue(signedByteReading.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(b, signedByteReading.firstOperation().get().result().get().deserialized().get());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, signedByteReading);
  }

  @Test
  public void test02_UnsignedByte() {
    Integer b = Integer.valueOf(0xFF);
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSzStruct<Integer, WkSzOperationSettings, WkSzUnsignedByte, WkSzUnsignedByteReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzUnsignedByte, WkSzUnsignedByteWriter, WkSzOutputBytestreamBase<?>, WkSzUnsignedByte>
      unsignedBytePacketStructure = WkSzUnsignedByte.newPacketStructure("SINGLE_UINT8");

    WkSzOutputPacket<Integer, WkSzUnsignedByte, WkSzUnsignedByteWriter>
      byteOutput = unsignedBytePacketStructure.newOutputPacket(b, WkSzOperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>>
      completedField = byteOutput.previousProcessingSteapResult();
    assertTrue(byteOutput.isCompleted());
    assertTrue(completedField.isPresent());
    assertEquals(byteOutput.firstOperation().get(), completedField.get());
    assertEquals(b, byteOutput.firstOperation().get().serializable());
    assertTrue(byteOutput.firstOperation().get().dashboard().bytestream().isClosed());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getFieldProcessedBytes());
    assertEquals(0, byteOutput.firstOperation().get().dashboard().bytestream().getStartIndexInGlobalBytestream());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getTotalPacketProcessedBytes());

    assertEquals(1, outputBuffer.size());

    WkSzInputPacket<Integer, WkSzUnsignedByte, WkSzUnsignedByteReader>
      unsignedByteReading = unsignedBytePacketStructure.newInputPacket(WkSzOperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(unsignedByteReading.name() + " created");

    assertFalse(unsignedByteReading.isCompleted());
    assertTrue(unsignedByteReading.isEnabled());
    unsignedByteReading.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> unsignedByteCompletedField = unsignedByteReading.previousProcessingSteapResult();
    assertEquals(unsignedByteReading.firstOperation().get(), unsignedByteCompletedField.get());
    assertTrue(unsignedByteReading.isCompleted());
    //assertTrue(unsignedByteReading.isRequiredByProtocol());
    assertTrue(unsignedByteReading.firstOperation().get().result().isPresent());
    assertTrue(unsignedByteReading.firstOperation().get().result().get().deserialized().isPresent());
    assertReadingValueEqualsTo((byte) 0xFF, (short) 0x00FF, 0x00FF, 0x00FFL, unsignedByteReading);
  }

  @Test
  public void test03_SignedBigEndianShort() {
    Short s = Short.valueOf((short) 0xFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSzSignedBigEndianShort>
      signedBigEndianShort = WkSzSignedBigEndianShort.newPacketStructure("SINGLE_SINT16BE");

    logger.info(signedBigEndianShort.name() + " created");

    WkSzOutputPacket<Short, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter>
      bigEndianUnsignedShortSerializing = signedBigEndianShort.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortSerializing.name() + " created");

    assertFalse(bigEndianUnsignedShortSerializing.isCompleted());
    bigEndianUnsignedShortSerializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = bigEndianUnsignedShortSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortSerializing.isCompleted());
    assertEquals(bigEndianUnsignedShortSerializing.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortSerializing.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());

    WkSzInputPacket<Short, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader>
      bigEndianSignedShortDeserializing = signedBigEndianShort.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(bigEndianSignedShortDeserializing.name() + " created");

    assertFalse(bigEndianSignedShortDeserializing.isCompleted());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().isEmpty());
    bigEndianSignedShortDeserializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = bigEndianSignedShortDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(bigEndianSignedShortDeserializing.isCompleted());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().isPresent());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(bigEndianSignedShortDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(s, bigEndianSignedShortDeserializing.firstOperation().get().result().get().deserialized().get());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, bigEndianSignedShortDeserializing);
  }

  @Test
  public void test04_SignedLittleEndianShort() {
    Short s = Short.valueOf((short) 0xFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Short, WkSzOperationSettings, WkSzSignedLittleEndianShort, WkSzSignedLittleEndianShortReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzSignedLittleEndianShort, WkSzSignedLittleEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSzSignedLittleEndianShort>
      signedLittleEndianShort = WkSzSignedLittleEndianShort.newPacketStructure("SINGLE_SINT16LE");
    logger.info(signedLittleEndianShort.name() + " created");

    WkSzOutputPacket<Short, WkSzSignedLittleEndianShort, WkSzSignedLittleEndianShortWriter>
      littleEndianShortOutput = signedLittleEndianShort.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortOutput.name() + " created");

    assertFalse(littleEndianShortOutput.isCompleted());
    littleEndianShortOutput.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = littleEndianShortOutput.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortOutput.isCompleted());
    assertEquals(littleEndianShortOutput.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortOutput.firstOperation().get().serializable());

    assertEquals(2,outputstream.size());

    WkSzInputPacket<Short, WkSzSignedLittleEndianShort, WkSzSignedLittleEndianShortReader>
      littleEndianSignedShortReading = signedLittleEndianShort.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianSignedShortReading.name() + " created");

    assertFalse(littleEndianSignedShortReading.isCompleted());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianSignedShortReading.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = littleEndianSignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(littleEndianSignedShortReading.isCompleted());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(littleEndianSignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(s, littleEndianSignedShortReading.firstOperation().get().result().get().deserialized().get());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, littleEndianSignedShortReading);
  }

  @Test
  public void test05_UnsignedBigEndianShort() {
    Integer s = Integer.valueOf(0xFABC);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Integer, WkSzOperationSettings, WkSzUnsignedBigEndianShort, WkSzUnsignedBigEndianShortReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzUnsignedBigEndianShort, WkSzUnsignedBigEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSzUnsignedBigEndianShort>
      unsignedBigEndianShort = WkSzUnsignedBigEndianShort.newPacketStructure("SINGLE_UINT16BE");
    logger.info(unsignedBigEndianShort.name() + " created");

    WkSzOutputPacket<Integer, WkSzUnsignedBigEndianShort, WkSzUnsignedBigEndianShortWriter>
      bigEndianUnsignedShortWriting = unsignedBigEndianShort.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortWriting.name() + " created");

    assertFalse(bigEndianUnsignedShortWriting.isCompleted());
    bigEndianUnsignedShortWriting.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = bigEndianUnsignedShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortWriting.isCompleted());
    assertEquals(bigEndianUnsignedShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xFA, (byte) 0xBC}));

    WkSzInputPacket<Integer, WkSzUnsignedBigEndianShort, WkSzUnsignedBigEndianShortReader>
      bigEndianUnsignedShortReading = unsignedBigEndianShort.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedShortReading.isCompleted());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedShortReading.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = bigEndianUnsignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(bigEndianUnsignedShortReading.isCompleted());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(bigEndianUnsignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(s.shortValue(), bigEndianUnsignedShortReading.firstOperation().get().result().get().deserialized().get().shortValue());
    assertReadingValueEqualsTo((byte) 0xBC, (short) 0xFABC, 0xFABC, 0xFABCL, bigEndianUnsignedShortReading);
  }

  @Test
  public void test06_UnsignedLittleEndianShort() {
    Integer s = Integer.valueOf(0x0000FABC);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Integer, WkSzOperationSettings, WkSzUnsignedLittleEndianShort, WkSzUnsignedLittleEndianShortReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzUnsignedLittleEndianShort, WkSzUnsignedLittleEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSzUnsignedLittleEndianShort>
      shortOutputProtocol = WkSzUnsignedLittleEndianShort.newPacketStructure("SINGLE_UINT16LE");
    logger.info(shortOutputProtocol.name() + " created");

    WkSzOutputPacket<Integer, WkSzUnsignedLittleEndianShort, WkSzUnsignedLittleEndianShortWriter>
      littleEndianShortWriting = shortOutputProtocol.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xBC, (byte) 0xFA}));

    WkSzInputPacket<Integer, WkSzUnsignedLittleEndianShort, WkSzUnsignedLittleEndianShortReader>
      littleEndianUnsignedShortReading = shortOutputProtocol.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianUnsignedShortReading.name() + " created");

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(littleEndianUnsignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(0xFFFF & s.intValue(), littleEndianUnsignedShortReading.firstOperation().get().result().get().deserialized().get().intValue());
    assertReadingValueEqualsTo((byte) 0xBC, (short) 0xFABC, 0xFABC, 0xFABCL, littleEndianUnsignedShortReading);
  }

  @Test
  public void test07_SignedBigEndianInteger() {
    Integer i = Integer.valueOf(0xFFFFFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Integer, WkSzOperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSzSignedBigEndianInteger>
      signedBigEndianInt = WkSzSignedBigEndianInteger.newPacketStructure("SINGLE_SINT32BE");
    logger.info(signedBigEndianInt.name() + " created");

    WkSzOutputPacket<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>
      signedBigEndianIntSerializing = signedBigEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianIntSerializing.name() + " created");

    assertFalse(signedBigEndianIntSerializing.isCompleted());
    signedBigEndianIntSerializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = signedBigEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianIntSerializing.isCompleted());
    assertEquals(signedBigEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedBigEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSzInputPacket<Integer, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>
      signedBigEndianIntDeserializing = signedBigEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianIntDeserializing.name() + " created");

    assertFalse(signedBigEndianIntDeserializing.isCompleted());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianIntDeserializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = signedBigEndianIntDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedBigEndianIntDeserializing.isCompleted());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(signedBigEndianIntDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), signedBigEndianIntDeserializing.firstOperation().get().result().get().deserialized().get().intValue());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, signedBigEndianIntDeserializing);
  }

  @Test
  public void test08_SignedLittleEndianInteger() {
    Integer i = Integer.valueOf(0xFFFFFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Integer, WkSzOperationSettings, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSzSignedLittleEndianInteger>
      signedLittleEndianInt = WkSzSignedLittleEndianInteger.newPacketStructure("SINGLE_SINT32LE");
    logger.info(signedLittleEndianInt.name() + " created");

    WkSzOutputPacket<Integer, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianIntegerWriter>
      signedLittleEndianIntSerializing = signedLittleEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianIntSerializing.name() + " created");

    assertFalse(signedLittleEndianIntSerializing.isCompleted());
    signedLittleEndianIntSerializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = signedLittleEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianIntSerializing.isCompleted());
    assertEquals(signedLittleEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedLittleEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSzInputPacket<Integer, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianIntegerReader>
      signedLittleEndianIntDeserializing = signedLittleEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianIntDeserializing.name() + " created");

    assertFalse(signedLittleEndianIntDeserializing.isCompleted());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianIntDeserializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = signedLittleEndianIntDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedLittleEndianIntDeserializing.isCompleted());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(signedLittleEndianIntDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), signedLittleEndianIntDeserializing.firstOperation().get().result().get().deserialized().get().intValue());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, signedLittleEndianIntDeserializing);
  }

  @Test
  public void test09_UnsignedBigEndianInteger() {
    Long i = Long.valueOf(0xFFAB_CDEF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Long, WkSzOperationSettings, WkSzUnsignedBigEndianInteger, WkSzUnsignedBigEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzUnsignedBigEndianInteger, WkSzUnsignedBigEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSzUnsignedBigEndianInteger>
      unsignedBigEndianInt = WkSzUnsignedBigEndianInteger.newPacketStructure("SINGLE_UINT32BE");
    logger.info(unsignedBigEndianInt.name() + " created");

    WkSzOutputPacket<Long, WkSzUnsignedBigEndianInteger, WkSzUnsignedBigEndianIntegerWriter>
      bigEndianShortWriting = unsignedBigEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianShortWriting.name() + " created");

    assertFalse(bigEndianShortWriting.isCompleted());
    bigEndianShortWriting.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = bigEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianShortWriting.isCompleted());
    assertEquals(bigEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, bigEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xFF, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSzInputPacket<Long, WkSzUnsignedBigEndianInteger, WkSzUnsignedBigEndianIntegerReader>
      bigEndianUnsignedIntReading = unsignedBigEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedIntReading.isCompleted());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedIntReading.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = bigEndianUnsignedIntReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(bigEndianUnsignedIntReading.isCompleted());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().isPresent());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(bigEndianUnsignedIntReading.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), bigEndianUnsignedIntReading.firstOperation().get().result().get().deserialized().get().intValue());
    assertReadingValueEqualsTo(
        (byte) 0xEF,
        (short) 0xCDEF,
        0xFFABCDEF,
        0xFFABCDEFL,
        bigEndianUnsignedIntReading);
  }

  @Test
  public void test10_UnsignedLittleEndianInteger() {
    Long i = Long.valueOf(0xFFABCDEF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Long, WkSzOperationSettings, WkSzUnsignedLittleEndianInteger, WkSzUnsignedLittleEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzUnsignedLittleEndianInteger, WkSzUnsignedLittleEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSzUnsignedLittleEndianInteger>
      unsignedLittleEndianInt = WkSzUnsignedLittleEndianInteger.newPacketStructure("SINGLE_UINT32LE");
    logger.info(unsignedLittleEndianInt.name() + " created");

    WkSzOutputPacket<Long, WkSzUnsignedLittleEndianInteger, WkSzUnsignedLittleEndianIntegerWriter>
      littleEndianShortWriting = unsignedLittleEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0xFF}));

    WkSzInputPacket<Long, WkSzUnsignedLittleEndianInteger, WkSzUnsignedLittleEndianIntegerReader>
      littleEndianUnsignedShortReading = unsignedLittleEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(littleEndianUnsignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), littleEndianUnsignedShortReading.firstOperation().get().result().get().deserialized().get().intValue());
    assertReadingValueEqualsTo(
        (byte) 0xEF,
        (short) 0xCDEF,
        0xFFABCDEF,
        0xFFABCDEFL,
        littleEndianUnsignedShortReading);
  }

  @Test
  public void test11_SignedBigEndianLong() {
    Long l = Long.valueOf(0x01234567_89ABCDEFL);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Long, WkSzOperationSettings, WkSzSignedBigEndianLong, WkSzSignedBigEndianLongReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzSignedBigEndianLong, WkSzSignedBigEndianLongWriter, WkSzOutputBytestreamBase<?>, WkSzSignedBigEndianLong>
      signedBigEndianLong = WkSzSignedBigEndianLong.newPacketStructure("SINGLE_SINT64BE");
    logger.info(signedBigEndianLong.name() + " created");

    WkSzOutputPacket<Long, WkSzSignedBigEndianLong, WkSzSignedBigEndianLongWriter>
      signedBigEndianLongSerializing = signedBigEndianLong.newOutputPacket(l, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianLongSerializing.name() + " created");

    assertFalse(signedBigEndianLongSerializing.isCompleted());
    signedBigEndianLongSerializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = signedBigEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianLongSerializing.isCompleted());
    assertEquals(signedBigEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedBigEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                                        (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSzInputPacket<Long, WkSzSignedBigEndianLong, WkSzSignedBigEndianLongReader>
      signedBigEndianLongDeserializing = signedBigEndianLong.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianLongDeserializing.name() + " created");

    assertFalse(signedBigEndianLongDeserializing.isCompleted());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianLongDeserializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = signedBigEndianLongDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedBigEndianLongDeserializing.isCompleted());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(signedBigEndianLongDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(l.longValue(), signedBigEndianLongDeserializing.firstOperation().get().result().get().deserialized().get().longValue());
    assertReadingValueEqualsTo(
        (byte) 0xEF,
        (short) 0xCDEF,
        0x89ABCDEF,
        0x01234567_89ABCDEFL,
        signedBigEndianLongDeserializing);
  }

  @Test
  public void test12_SignedLittleEndianLong() {
    Long l = Long.valueOf(0x01234567_89ABCDEFL);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzStruct<Long, WkSzOperationSettings, WkSzSignedLittleEndianLong, WkSzSignedLittleEndianLongReader, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSzSignedLittleEndianLong, WkSzSignedLittleEndianLongWriter, WkSzOutputBytestreamBase<?>, WkSzSignedLittleEndianLong>
      signedLittleEndianLong = WkSzSignedLittleEndianLong.newPacketStructure("SINGLE_SINT64LE");
    logger.info(signedLittleEndianLong.name() + " created");

    WkSzOutputPacket<Long, WkSzSignedLittleEndianLong, WkSzSignedLittleEndianLongWriter>
      signedLittleEndianLongSerializing = signedLittleEndianLong.newOutputPacket(l, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianLongSerializing.name() + " created");

    assertFalse(signedLittleEndianLongSerializing.isCompleted());
    signedLittleEndianLongSerializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedField = signedLittleEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianLongSerializing.isCompleted());
    assertEquals(signedLittleEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedLittleEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89,
                                        (byte) 0x67, (byte) 0x45, (byte) 0x23, (byte) 0x01}));

    WkSzInputPacket<Long, WkSzSignedLittleEndianLong, WkSzSignedLittleEndianLongReader>
      signedLittleEndianLongDeserializing = signedLittleEndianLong.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianLongDeserializing.name() + " created");

    assertFalse(signedLittleEndianLongDeserializing.isCompleted());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianLongDeserializing.processBytestream();
    Optional<WkSzPacketOperation<?,?,?,?,?>> completedInputField = signedLittleEndianLongDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedLittleEndianLongDeserializing.isCompleted());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().get().deserialized().isPresent());
    assertEquals(signedLittleEndianLongDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(l.longValue(), signedLittleEndianLongDeserializing.firstOperation().get().result().get().deserialized().get().longValue());
    assertReadingValueEqualsTo(
        (byte) 0xEF,
        (short) 0xCDEF,
        0x89ABCDEF,
        0x01234567_89ABCDEFL,
        signedLittleEndianLongDeserializing);
  }

  private static void assertReadingValueEqualsTo(
    byte expectedByte,
    short expectedShort,
    int expectedInt,
    long expectedLong,
    WkSzPacketReaderField<? extends Number,?,?> reading) {
    Number deserializedNumber =
        reading.firstOperation().get().result().get().deserialized().get();
    byte obtainedByte = deserializedNumber.byteValue();
    assertEquals(expectedByte, obtainedByte);
    short obtainedShort = deserializedNumber.shortValue();
    assertEquals(expectedShort, obtainedShort);
    int obtainedInt = deserializedNumber.intValue();
    assertEquals(expectedInt, obtainedInt);
    long obtainedLong = deserializedNumber.longValue();
    assertEquals(expectedLong, obtainedLong);
  }

}
