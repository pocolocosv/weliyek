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

import weliyek.amat.base.OperationSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketStructure;
import weliyek.amat.base.input.DeserializingField;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.InputPacket;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.OutputPacket;
import weliyek.ketza.util.KetzaByteOutputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PrimitiveSerializationTests {

  private static final Logger logger = LoggerFactory.getLogger(PrimitiveSerializationTests.class);

  @Test
  public void test01_SignedByte() {
    Byte b = Byte.valueOf((byte) 0xFF);
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    PacketStructure<Byte, OperationSettings, SignedByte, SignedByteDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, SignedByte, SignedByteSerializing, OutputBytestreamGeneralBase<?>, SignedByte>
      signedBytePacketStructure = SignedByte.newPacketStructure("SINGLE_SINT8");

    logger.info(signedBytePacketStructure.name() + " created");

    OutputPacket<Byte, SignedByte, SignedByteSerializing>
      byteOutput = signedBytePacketStructure.newOutputPacket(b, OperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = byteOutput.previousProcessingSteapResult();
    assertTrue(byteOutput.isCompleted());
    assertTrue(completedField.isPresent());
    assertEquals(byteOutput.firstOperation().get(), completedField.get());
    assertEquals(b, byteOutput.firstOperation().get().serializable());
    assertTrue(byteOutput.firstOperation().get().dashboard().bytestream().isClosed());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getFieldProcessedBytes());
    assertEquals(0, byteOutput.firstOperation().get().dashboard().bytestream().getStartIndexInGlobalBytestream());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getTotalPacketProcessedBytes());

    assertEquals(1, outputBuffer.size());

    InputPacket<Byte, SignedByte, SignedByteDeserializing>
      signedByteReading = signedBytePacketStructure.newInputPacket(OperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(signedByteReading.name() + " created");

    assertFalse(signedByteReading.isCompleted());
    assertTrue(signedByteReading.isEnabled());
    signedByteReading.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> signedByteCompletedField = signedByteReading.previousProcessingSteapResult();
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

    PacketStructure<Integer, OperationSettings, UnsignedByte, UnsignedByteDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, UnsignedByte, UnsignedByteSerializing, OutputBytestreamGeneralBase<?>, UnsignedByte>
      unsignedBytePacketStructure = UnsignedByte.newPacketStructure("SINGLE_UINT8");

    OutputPacket<Integer, UnsignedByte, UnsignedByteSerializing>
      byteOutput = unsignedBytePacketStructure.newOutputPacket(b, OperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>>
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

    InputPacket<Integer, UnsignedByte, UnsignedByteDeserializing>
      unsignedByteReading = unsignedBytePacketStructure.newInputPacket(OperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(unsignedByteReading.name() + " created");

    assertFalse(unsignedByteReading.isCompleted());
    assertTrue(unsignedByteReading.isEnabled());
    unsignedByteReading.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> unsignedByteCompletedField = unsignedByteReading.previousProcessingSteapResult();
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

    PacketStructure<Short, OperationSettings, SignedBigEndianShort, SignedBigEndianShortDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, SignedBigEndianShort, SignedBigEndianShortSerializing, OutputBytestreamGeneralBase<?>, SignedBigEndianShort>
      signedBigEndianShort = SignedBigEndianShort.newPacketStructure("SINGLE_SINT16BE");

    logger.info(signedBigEndianShort.name() + " created");

    OutputPacket<Short, SignedBigEndianShort, SignedBigEndianShortSerializing>
      bigEndianUnsignedShortSerializing = signedBigEndianShort.newOutputPacket(s, OperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortSerializing.name() + " created");

    assertFalse(bigEndianUnsignedShortSerializing.isCompleted());
    bigEndianUnsignedShortSerializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = bigEndianUnsignedShortSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortSerializing.isCompleted());
    assertEquals(bigEndianUnsignedShortSerializing.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortSerializing.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());

    InputPacket<Short, SignedBigEndianShort, SignedBigEndianShortDeserializing>
      bigEndianSignedShortDeserializing = signedBigEndianShort.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
    logger.info(bigEndianSignedShortDeserializing.name() + " created");

    assertFalse(bigEndianSignedShortDeserializing.isCompleted());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().isEmpty());
    bigEndianSignedShortDeserializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = bigEndianSignedShortDeserializing.previousProcessingSteapResult();
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

    PacketStructure<Short, OperationSettings, SignedLittleEndianShort, SignedLittleEndianShortDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, SignedLittleEndianShort, SignedLittleEndianShortSerializing, OutputBytestreamGeneralBase<?>, SignedLittleEndianShort>
      signedLittleEndianShort = SignedLittleEndianShort.newPacketStructure("SINGLE_SINT16LE");
    logger.info(signedLittleEndianShort.name() + " created");

    OutputPacket<Short, SignedLittleEndianShort, SignedLittleEndianShortSerializing>
      littleEndianShortOutput = signedLittleEndianShort.newOutputPacket(s, OperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortOutput.name() + " created");

    assertFalse(littleEndianShortOutput.isCompleted());
    littleEndianShortOutput.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = littleEndianShortOutput.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortOutput.isCompleted());
    assertEquals(littleEndianShortOutput.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortOutput.firstOperation().get().serializable());

    assertEquals(2,outputstream.size());

    InputPacket<Short, SignedLittleEndianShort, SignedLittleEndianShortDeserializing>
      littleEndianSignedShortReading = signedLittleEndianShort.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianSignedShortReading.name() + " created");

    assertFalse(littleEndianSignedShortReading.isCompleted());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianSignedShortReading.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = littleEndianSignedShortReading.previousProcessingSteapResult();
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

    PacketStructure<Integer, OperationSettings, UnsignedBigEndianShort, UnsignedShortBigEndianDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, UnsignedBigEndianShort, UnsignedBigEndianShortSerializing, OutputBytestreamGeneralBase<?>, UnsignedBigEndianShort>
      unsignedBigEndianShort = UnsignedBigEndianShort.newPacketStructure("SINGLE_UINT16BE");
    logger.info(unsignedBigEndianShort.name() + " created");

    OutputPacket<Integer, UnsignedBigEndianShort, UnsignedBigEndianShortSerializing>
      bigEndianUnsignedShortWriting = unsignedBigEndianShort.newOutputPacket(s, OperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortWriting.name() + " created");

    assertFalse(bigEndianUnsignedShortWriting.isCompleted());
    bigEndianUnsignedShortWriting.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = bigEndianUnsignedShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortWriting.isCompleted());
    assertEquals(bigEndianUnsignedShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xFA, (byte) 0xBC}));

    InputPacket<Integer, UnsignedBigEndianShort, UnsignedShortBigEndianDeserializing>
      bigEndianUnsignedShortReading = unsignedBigEndianShort.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedShortReading.isCompleted());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedShortReading.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = bigEndianUnsignedShortReading.previousProcessingSteapResult();
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

    PacketStructure<Integer, OperationSettings, UnsignedLittleEndianShort, UnsignedLittleEndianShortDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, UnsignedLittleEndianShort, UnsignedLittleEndianShortSerializing, OutputBytestreamGeneralBase<?>, UnsignedLittleEndianShort>
      shortOutputProtocol = UnsignedLittleEndianShort.newPacketStructure("SINGLE_UINT16LE");
    logger.info(shortOutputProtocol.name() + " created");

    OutputPacket<Integer, UnsignedLittleEndianShort, UnsignedLittleEndianShortSerializing>
      littleEndianShortWriting = shortOutputProtocol.newOutputPacket(s, OperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xBC, (byte) 0xFA}));

    InputPacket<Integer, UnsignedLittleEndianShort, UnsignedLittleEndianShortDeserializing>
      littleEndianUnsignedShortReading = shortOutputProtocol.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianUnsignedShortReading.name() + " created");

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
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

    PacketStructure<Integer, OperationSettings, SignedBigEndianInteger, SignedBigEndianIntegerDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, SignedBigEndianInteger, SignedBigEndianIntegerSerializing, OutputBytestreamGeneralBase<?>, SignedBigEndianInteger>
      signedBigEndianInt = SignedBigEndianInteger.newPacketStructure("SINGLE_SINT32BE");
    logger.info(signedBigEndianInt.name() + " created");

    OutputPacket<Integer, SignedBigEndianInteger, SignedBigEndianIntegerSerializing>
      signedBigEndianIntSerializing = signedBigEndianInt.newOutputPacket(i, OperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianIntSerializing.name() + " created");

    assertFalse(signedBigEndianIntSerializing.isCompleted());
    signedBigEndianIntSerializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = signedBigEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianIntSerializing.isCompleted());
    assertEquals(signedBigEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedBigEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    InputPacket<Integer, SignedBigEndianInteger, SignedBigEndianIntegerDeserializing>
      signedBigEndianIntDeserializing = signedBigEndianInt.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianIntDeserializing.name() + " created");

    assertFalse(signedBigEndianIntDeserializing.isCompleted());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianIntDeserializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = signedBigEndianIntDeserializing.previousProcessingSteapResult();
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

    PacketStructure<Integer, OperationSettings, SignedLittleEndianInteger, SignedLittleEndianIntegerDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, SignedLittleEndianInteger, SignedLittleEndianIntegerSerializing, OutputBytestreamGeneralBase<?>, SignedLittleEndianInteger>
      signedLittleEndianInt = SignedLittleEndianInteger.newPacketStructure("SINGLE_SINT32LE");
    logger.info(signedLittleEndianInt.name() + " created");

    OutputPacket<Integer, SignedLittleEndianInteger, SignedLittleEndianIntegerSerializing>
      signedLittleEndianIntSerializing = signedLittleEndianInt.newOutputPacket(i, OperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianIntSerializing.name() + " created");

    assertFalse(signedLittleEndianIntSerializing.isCompleted());
    signedLittleEndianIntSerializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = signedLittleEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianIntSerializing.isCompleted());
    assertEquals(signedLittleEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedLittleEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    InputPacket<Integer, SignedLittleEndianInteger, SignedLittleEndianIntegerDeserializing>
      signedLittleEndianIntDeserializing = signedLittleEndianInt.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianIntDeserializing.name() + " created");

    assertFalse(signedLittleEndianIntDeserializing.isCompleted());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianIntDeserializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = signedLittleEndianIntDeserializing.previousProcessingSteapResult();
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

    PacketStructure<Long, OperationSettings, UnsignedBigEndianInteger, UnsignedBigEndianIntegerDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, UnsignedBigEndianInteger, UnsignedBigEndianIntegerSerializing, OutputBytestreamGeneralBase<?>, UnsignedBigEndianInteger>
      unsignedBigEndianInt = UnsignedBigEndianInteger.newPacketStructure("SINGLE_UINT32BE");
    logger.info(unsignedBigEndianInt.name() + " created");

    OutputPacket<Long, UnsignedBigEndianInteger, UnsignedBigEndianIntegerSerializing>
      bigEndianShortWriting = unsignedBigEndianInt.newOutputPacket(i, OperationSettings.EMPTY, outputstream);
    logger.info(bigEndianShortWriting.name() + " created");

    assertFalse(bigEndianShortWriting.isCompleted());
    bigEndianShortWriting.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = bigEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianShortWriting.isCompleted());
    assertEquals(bigEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, bigEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xFF, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    InputPacket<Long, UnsignedBigEndianInteger, UnsignedBigEndianIntegerDeserializing>
      bigEndianUnsignedIntReading = unsignedBigEndianInt.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedIntReading.isCompleted());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedIntReading.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = bigEndianUnsignedIntReading.previousProcessingSteapResult();
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

    PacketStructure<Long, OperationSettings, UnsignedLittleEndianInteger, UnsignedIntegerLittleEndianReading, InputBytestreamGeneralBase<?>, OperationSettings, UnsignedLittleEndianInteger, UnsignedLittleEndianIntegerSerializing, OutputBytestreamGeneralBase<?>, UnsignedLittleEndianInteger>
      unsignedLittleEndianInt = UnsignedLittleEndianInteger.newPacketStructure("SINGLE_UINT32LE");
    logger.info(unsignedLittleEndianInt.name() + " created");

    OutputPacket<Long, UnsignedLittleEndianInteger, UnsignedLittleEndianIntegerSerializing>
      littleEndianShortWriting = unsignedLittleEndianInt.newOutputPacket(i, OperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0xFF}));

    InputPacket<Long, UnsignedLittleEndianInteger, UnsignedIntegerLittleEndianReading>
      littleEndianUnsignedShortReading = unsignedLittleEndianInt.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
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

    PacketStructure<Long, OperationSettings, SignedBigEndianLong, SignedBigEndianLongDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, SignedBigEndianLong, SignedBigEndianLongSerializing, OutputBytestreamGeneralBase<?>, SignedBigEndianLong>
      signedBigEndianLong = SignedBigEndianLong.newPacketStructure("SINGLE_SINT64BE");
    logger.info(signedBigEndianLong.name() + " created");

    OutputPacket<Long, SignedBigEndianLong, SignedBigEndianLongSerializing>
      signedBigEndianLongSerializing = signedBigEndianLong.newOutputPacket(l, OperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianLongSerializing.name() + " created");

    assertFalse(signedBigEndianLongSerializing.isCompleted());
    signedBigEndianLongSerializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = signedBigEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianLongSerializing.isCompleted());
    assertEquals(signedBigEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedBigEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                                        (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    InputPacket<Long, SignedBigEndianLong, SignedBigEndianLongDeserializing>
      signedBigEndianLongDeserializing = signedBigEndianLong.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianLongDeserializing.name() + " created");

    assertFalse(signedBigEndianLongDeserializing.isCompleted());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianLongDeserializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = signedBigEndianLongDeserializing.previousProcessingSteapResult();
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

    PacketStructure<Long, OperationSettings, SignedLittleEndianLong, SignedLittleEndianLongDeserializing, InputBytestreamGeneralBase<?>, OperationSettings, SignedLittleEndianLong, SignedLittleEndianLongSerializing, OutputBytestreamGeneralBase<?>, SignedLittleEndianLong>
      signedLittleEndianLong = SignedLittleEndianLong.newPacketStructure("SINGLE_SINT64LE");
    logger.info(signedLittleEndianLong.name() + " created");

    OutputPacket<Long, SignedLittleEndianLong, SignedLittleEndianLongSerializing>
      signedLittleEndianLongSerializing = signedLittleEndianLong.newOutputPacket(l, OperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianLongSerializing.name() + " created");

    assertFalse(signedLittleEndianLongSerializing.isCompleted());
    signedLittleEndianLongSerializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedField = signedLittleEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianLongSerializing.isCompleted());
    assertEquals(signedLittleEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedLittleEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89,
                                        (byte) 0x67, (byte) 0x45, (byte) 0x23, (byte) 0x01}));

    InputPacket<Long, SignedLittleEndianLong, SignedLittleEndianLongDeserializing>
      signedLittleEndianLongDeserializing = signedLittleEndianLong.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianLongDeserializing.name() + " created");

    assertFalse(signedLittleEndianLongDeserializing.isCompleted());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianLongDeserializing.processBytestream();
    Optional<OperationSegment<?,?,?,?,?>> completedInputField = signedLittleEndianLongDeserializing.previousProcessingSteapResult();
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
    DeserializingField<? extends Number,?,?> reading) {
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
