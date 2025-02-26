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
package weliyek.serialization.number;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgOperation;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class WkSerdeDtreeNumberTest {

  private static final Logger logger = LoggerFactory.getLogger(WkSerdeDtreeNumberTest.class);

  @Test
  public void test01_SignedByte() {
    Byte b = Byte.valueOf((byte) 0xFF);
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Byte, WkSerdeDtreeOperationSettings, WkSerdeSignedByte, WkSerdeSignedByteReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeSignedByte, WkSerdeSignedByteWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeSignedByte>
      signedBytePacketStructure = WkSerdeSignedByte.newStruct("SINGLE_SINT8");

    logger.info(signedBytePacketStructure.name() + " created");

    WkSerdeDtreeWriter<Byte, WkSerdeSignedByte, WkSerdeSignedByteWriter>
      byteOutput = signedBytePacketStructure.newOutputPacket(b, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    //assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = byteOutput.previousProcessingSteapResult();
    assertTrue(byteOutput.isCompleted());
    assertTrue(completedField.isPresent());
    assertEquals(byteOutput.firstOperation().get(), completedField.get());
    assertEquals(b, byteOutput.firstOperation().get().serializable());
    assertTrue(byteOutput.firstOperation().get().dashboard().bytestream().isClosed());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getFieldProcessedBytes());
    assertEquals(0, byteOutput.firstOperation().get().dashboard().bytestream().getStartIndexInGlobalBytestream());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getTotalPacketProcessedBytes());

    assertEquals(1, outputBuffer.size());

    WkSerdeDtreeReader<Byte, WkSerdeSignedByte, WkSerdeSignedByteReader>
      signedByteReading = signedBytePacketStructure.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(signedByteReading.name() + " created");

    assertFalse(signedByteReading.isCompleted());
    assertTrue(signedByteReading.isEnabled());
    signedByteReading.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> signedByteCompletedField = signedByteReading.previousProcessingSteapResult();
    assertEquals(signedByteReading.firstOperation().get(), signedByteCompletedField.get());
    assertTrue(signedByteReading.isCompleted());
    //assertTrue(signedByteReading.isRequiredByProtocol());
    assertTrue(signedByteReading.firstOperation().get().result().isPresent());
    assertTrue(signedByteReading.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(b, signedByteReading.firstOperation().get().result().get().serializable().get());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, signedByteReading);
  }

  @Test
  public void test02_UnsignedByte() {
    Integer b = Integer.valueOf(0xFF);
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Integer, WkSerdeDtreeOperationSettings, WkSerdeUnsignedByte, WkSerdeUnsignedByteReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeUnsignedByte, WkSerdeUnsignedByteWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeUnsignedByte>
      unsignedBytePacketStructure = WkSerdeUnsignedByte.newStruct("SINGLE_UINT8");

    WkSerdeDtreeWriter<Integer, WkSerdeUnsignedByte, WkSerdeUnsignedByteWriter>
      byteOutput = unsignedBytePacketStructure.newOutputPacket(b, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>>
      completedField = byteOutput.previousProcessingSteapResult();
    assertTrue(byteOutput.isCompleted());
    assertTrue(completedField.isPresent());
    assertEquals(byteOutput.firstOperation().get(), completedField.get());
    assertEquals(b, byteOutput.firstOperation().get().serializable());

    assertEquals(1, outputBuffer.size());

    WkSerdeDtreeReader<Integer, WkSerdeUnsignedByte, WkSerdeUnsignedByteReader>
      unsignedByteReading = unsignedBytePacketStructure.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(unsignedByteReading.name() + " created");

    assertFalse(unsignedByteReading.isCompleted());
    assertTrue(unsignedByteReading.isEnabled());
    unsignedByteReading.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> unsignedByteCompletedField = unsignedByteReading.previousProcessingSteapResult();
    assertEquals(unsignedByteReading.firstOperation().get(), unsignedByteCompletedField.get());
    assertTrue(unsignedByteReading.isCompleted());
    //assertTrue(unsignedByteReading.isRequiredByProtocol());
    assertTrue(unsignedByteReading.firstOperation().get().result().isPresent());
    assertTrue(unsignedByteReading.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(b, unsignedByteReading.firstOperation().get().result().get().serializable().get());
    assertReadingValueEqualsTo((byte) 0xFF, (short) 0x00FF, 0x00FF, 0x00FFL, unsignedByteReading);
  }

  @Test
  public void test03_SignedBigEndianShort() {
    Short s = Short.valueOf((short) 0xFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeSignedBigEndianShort>
      signedBigEndianShort = WkSerdeSignedBigEndianShort.newStruct("SINGLE_SINT16BE");

    logger.info(signedBigEndianShort.name() + " created");

    WkSerdeDtreeWriter<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter>
      bigEndianUnsignedShortSerializing = signedBigEndianShort.newOutputPacket(s, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortSerializing.name() + " created");

    assertFalse(bigEndianUnsignedShortSerializing.isCompleted());
    bigEndianUnsignedShortSerializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = bigEndianUnsignedShortSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortSerializing.isCompleted());
    assertEquals(bigEndianUnsignedShortSerializing.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortSerializing.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());

    WkSerdeDtreeReader<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader>
      bigEndianSignedShortDeserializing = signedBigEndianShort.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(bigEndianSignedShortDeserializing.name() + " created");

    assertFalse(bigEndianSignedShortDeserializing.isCompleted());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().isEmpty());
    bigEndianSignedShortDeserializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = bigEndianSignedShortDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(bigEndianSignedShortDeserializing.isCompleted());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().isPresent());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(bigEndianSignedShortDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(s, bigEndianSignedShortDeserializing.firstOperation().get().result().get().serializable().get());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, bigEndianSignedShortDeserializing);
  }

  @Test
  public void test04_SignedLittleEndianShort() {
    Short s = Short.valueOf((short) 0xFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Short, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeSignedLittleEndianShort>
      signedLittleEndianShort = WkSerdeSignedLittleEndianShort.newStruct("SINGLE_SINT16LE");
    logger.info(signedLittleEndianShort.name() + " created");

    WkSerdeDtreeWriter<Short, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortWriter>
      littleEndianShortOutput = signedLittleEndianShort.newOutputPacket(s, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortOutput.name() + " created");

    assertFalse(littleEndianShortOutput.isCompleted());
    littleEndianShortOutput.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = littleEndianShortOutput.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortOutput.isCompleted());
    assertEquals(littleEndianShortOutput.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortOutput.firstOperation().get().serializable());

    assertEquals(2,outputstream.size());

    WkSerdeDtreeReader<Short, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortReader>
      littleEndianSignedShortReading = signedLittleEndianShort.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianSignedShortReading.name() + " created");

    assertFalse(littleEndianSignedShortReading.isCompleted());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianSignedShortReading.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = littleEndianSignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(littleEndianSignedShortReading.isCompleted());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(littleEndianSignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(s, littleEndianSignedShortReading.firstOperation().get().result().get().serializable().get());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, littleEndianSignedShortReading);
  }

  @Test
  public void test05_UnsignedBigEndianShort() {
    Integer s = Integer.valueOf(0xFABC);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Integer, WkSerdeDtreeOperationSettings, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeUnsignedBigEndianShort>
      unsignedBigEndianShort = WkSerdeUnsignedBigEndianShort.newStruct("SINGLE_UINT16BE");
    logger.info(unsignedBigEndianShort.name() + " created");

    WkSerdeDtreeWriter<Integer, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortWriter>
      bigEndianUnsignedShortWriting = unsignedBigEndianShort.newOutputPacket(s, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortWriting.name() + " created");

    assertFalse(bigEndianUnsignedShortWriting.isCompleted());
    bigEndianUnsignedShortWriting.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = bigEndianUnsignedShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortWriting.isCompleted());
    assertEquals(bigEndianUnsignedShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xFA, (byte) 0xBC}));

    WkSerdeDtreeReader<Integer, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortReader>
      bigEndianUnsignedShortReading = unsignedBigEndianShort.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedShortReading.isCompleted());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedShortReading.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = bigEndianUnsignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(bigEndianUnsignedShortReading.isCompleted());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(bigEndianUnsignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(s.shortValue(), bigEndianUnsignedShortReading.firstOperation().get().result().get().serializable().get().shortValue());
    assertReadingValueEqualsTo((byte) 0xBC, (short) 0xFABC, 0xFABC, 0xFABCL, bigEndianUnsignedShortReading);
  }

  @Test
  public void test06_UnsignedLittleEndianShort() {
    Integer s = Integer.valueOf(0x0000FABC);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Integer, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeUnsignedLittleEndianShort>
      shortOutputProtocol = WkSerdeUnsignedLittleEndianShort.newStruct("SINGLE_UINT16LE");
    logger.info(shortOutputProtocol.name() + " created");

    WkSerdeDtreeWriter<Integer, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter>
      littleEndianShortWriting = shortOutputProtocol.newOutputPacket(s, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xBC, (byte) 0xFA}));

    WkSerdeDtreeReader<Integer, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortReader>
      littleEndianUnsignedShortReading = shortOutputProtocol.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianUnsignedShortReading.name() + " created");

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(littleEndianUnsignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(0xFFFF & s.intValue(), littleEndianUnsignedShortReading.firstOperation().get().result().get().serializable().get().intValue());
    assertReadingValueEqualsTo((byte) 0xBC, (short) 0xFABC, 0xFABC, 0xFABCL, littleEndianUnsignedShortReading);
  }

  @Test
  public void test07_SignedBigEndianInteger() {
    Integer i = Integer.valueOf(0xFFFFFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeSignedBigEndianInteger>
      signedBigEndianInt = WkSerdeSignedBigEndianInteger.newStruct("SINGLE_SINT32BE");
    logger.info(signedBigEndianInt.name() + " created");

    WkSerdeDtreeWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>
      signedBigEndianIntSerializing = signedBigEndianInt.newOutputPacket(i, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianIntSerializing.name() + " created");

    assertFalse(signedBigEndianIntSerializing.isCompleted());
    signedBigEndianIntSerializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = signedBigEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianIntSerializing.isCompleted());
    assertEquals(signedBigEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedBigEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSerdeDtreeReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>
      signedBigEndianIntDeserializing = signedBigEndianInt.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianIntDeserializing.name() + " created");

    assertFalse(signedBigEndianIntDeserializing.isCompleted());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianIntDeserializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = signedBigEndianIntDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedBigEndianIntDeserializing.isCompleted());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(signedBigEndianIntDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), signedBigEndianIntDeserializing.firstOperation().get().result().get().serializable().get().intValue());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, signedBigEndianIntDeserializing);
  }

  @Test
  public void test08_SignedLittleEndianInteger() {
    Integer i = Integer.valueOf(0xFFFFFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeSignedLittleEndianInteger>
      signedLittleEndianInt = WkSerdeSignedLittleEndianInteger.newStruct("SINGLE_SINT32LE");
    logger.info(signedLittleEndianInt.name() + " created");

    WkSerdeDtreeWriter<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerWriter>
      signedLittleEndianIntSerializing = signedLittleEndianInt.newOutputPacket(i, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianIntSerializing.name() + " created");

    assertFalse(signedLittleEndianIntSerializing.isCompleted());
    signedLittleEndianIntSerializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = signedLittleEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianIntSerializing.isCompleted());
    assertEquals(signedLittleEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedLittleEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSerdeDtreeReader<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader>
      signedLittleEndianIntDeserializing = signedLittleEndianInt.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianIntDeserializing.name() + " created");

    assertFalse(signedLittleEndianIntDeserializing.isCompleted());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianIntDeserializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = signedLittleEndianIntDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedLittleEndianIntDeserializing.isCompleted());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(signedLittleEndianIntDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), signedLittleEndianIntDeserializing.firstOperation().get().result().get().serializable().get().intValue());
    assertReadingValueEqualsTo((byte) -1, (short) -1, -1, -1L, signedLittleEndianIntDeserializing);
  }

  @Test
  public void test09_UnsignedBigEndianInteger() {
    Long i = Long.valueOf(0xFFAB_CDEF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeUnsignedBigEndianInteger>
      unsignedBigEndianInt = WkSerdeUnsignedBigEndianInteger.newStruct("SINGLE_UINT32BE");
    logger.info(unsignedBigEndianInt.name() + " created");

    WkSerdeDtreeWriter<Long, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerWriter>
      bigEndianShortWriting = unsignedBigEndianInt.newOutputPacket(i, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianShortWriting.name() + " created");

    assertFalse(bigEndianShortWriting.isCompleted());
    bigEndianShortWriting.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = bigEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianShortWriting.isCompleted());
    assertEquals(bigEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, bigEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xFF, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSerdeDtreeReader<Long, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerReader>
      bigEndianUnsignedIntReading = unsignedBigEndianInt.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedIntReading.isCompleted());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedIntReading.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = bigEndianUnsignedIntReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(bigEndianUnsignedIntReading.isCompleted());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().isPresent());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(bigEndianUnsignedIntReading.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), bigEndianUnsignedIntReading.firstOperation().get().result().get().serializable().get().intValue());
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

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeUnsignedLittleEndianInteger>
      unsignedLittleEndianInt = WkSerdeUnsignedLittleEndianInteger.newStruct("SINGLE_UINT32LE");
    logger.info(unsignedLittleEndianInt.name() + " created");

    WkSerdeDtreeWriter<Long, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerWriter>
      littleEndianShortWriting = unsignedLittleEndianInt.newOutputPacket(i, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0xFF}));

    WkSerdeDtreeReader<Long, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerReader>
      littleEndianUnsignedShortReading = unsignedLittleEndianInt.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isPresent());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(littleEndianUnsignedShortReading.firstOperation().get(), completedInputField.get());
    assertEquals(i.intValue(), littleEndianUnsignedShortReading.firstOperation().get().result().get().serializable().get().intValue());
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

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeSignedBigEndianLong>
      signedBigEndianLong = WkSerdeSignedBigEndianLong.newStruct("SINGLE_SINT64BE");
    logger.info(signedBigEndianLong.name() + " created");

    WkSerdeDtreeWriter<Long, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter>
      signedBigEndianLongSerializing = signedBigEndianLong.newOutputPacket(l, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianLongSerializing.name() + " created");

    assertFalse(signedBigEndianLongSerializing.isCompleted());
    signedBigEndianLongSerializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = signedBigEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianLongSerializing.isCompleted());
    assertEquals(signedBigEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedBigEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                                        (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSerdeDtreeReader<Long, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader>
      signedBigEndianLongDeserializing = signedBigEndianLong.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianLongDeserializing.name() + " created");

    assertFalse(signedBigEndianLongDeserializing.isCompleted());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianLongDeserializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = signedBigEndianLongDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedBigEndianLongDeserializing.isCompleted());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(signedBigEndianLongDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(l.longValue(), signedBigEndianLongDeserializing.firstOperation().get().result().get().serializable().get().longValue());
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

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeSignedLittleEndianLong>
      signedLittleEndianLong = WkSerdeSignedLittleEndianLong.newStruct("SINGLE_SINT64LE");
    logger.info(signedLittleEndianLong.name() + " created");

    WkSerdeDtreeWriter<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter>
      signedLittleEndianLongSerializing = signedLittleEndianLong.newOutputPacket(l, WkSerdeDtreeOperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianLongSerializing.name() + " created");

    assertFalse(signedLittleEndianLongSerializing.isCompleted());
    signedLittleEndianLongSerializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedField = signedLittleEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianLongSerializing.isCompleted());
    assertEquals(signedLittleEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedLittleEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89,
                                        (byte) 0x67, (byte) 0x45, (byte) 0x23, (byte) 0x01}));

    WkSerdeDtreeReader<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader>
      signedLittleEndianLongDeserializing = signedLittleEndianLong.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianLongDeserializing.name() + " created");

    assertFalse(signedLittleEndianLongDeserializing.isCompleted());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianLongDeserializing.processBytestream();
    Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completedInputField = signedLittleEndianLongDeserializing.previousProcessingSteapResult();
    assertTrue(completedInputField.isPresent());
    assertTrue(signedLittleEndianLongDeserializing.isCompleted());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().isPresent());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().get().serializable().isPresent());
    assertEquals(signedLittleEndianLongDeserializing.firstOperation().get(), completedInputField.get());
    assertEquals(l.longValue(), signedLittleEndianLongDeserializing.firstOperation().get().result().get().serializable().get().longValue());
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
    WkSerdeDtreeMsgInputField<? extends Number,?,?> reading) {
    Number deserializedNumber =
        reading.firstOperation().get().result().get().serializable().get();
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
