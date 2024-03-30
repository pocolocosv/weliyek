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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSerdeDtreeNodeDataOperation;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.util.KetzaByteOutputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WkSerdeDtreeNumberTest {

  private static final Logger logger = LoggerFactory.getLogger(WkSerdeDtreeNumberTest.class);

  @Test
  public void test01_SignedByte() {
    Byte b = Byte.valueOf((byte) 0xFF);
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSrlzStruct<Byte, WkSettingsSrlzPacketOperationData, WkSerdeSignedByte, WkSerdeSignedByteReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeSignedByte, WkSerdeSignedByteWriter, WkSzOutputBytestreamBase<?>, WkSerdeSignedByte>
      signedBytePacketStructure = WkSerdeSignedByte.newStruct("SINGLE_SINT8");

    logger.info(signedBytePacketStructure.name() + " created");

    WkSzOutputPacket<Byte, WkSerdeSignedByte, WkSerdeSignedByteWriter>
      byteOutput = signedBytePacketStructure.newOutputPacket(b, WkSettingsSrlzPacketOperationData.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = byteOutput.previousProcessingSteapResult();
    assertTrue(byteOutput.isCompleted());
    assertTrue(completedField.isPresent());
    assertEquals(byteOutput.firstOperation().get(), completedField.get());
    assertEquals(b, byteOutput.firstOperation().get().serializable());
    assertTrue(byteOutput.firstOperation().get().dashboard().bytestream().isClosed());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getFieldProcessedBytes());
    assertEquals(0, byteOutput.firstOperation().get().dashboard().bytestream().getStartIndexInGlobalBytestream());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getTotalPacketProcessedBytes());

    assertEquals(1, outputBuffer.size());

    WkSzInputPacket<Byte, WkSerdeSignedByte, WkSerdeSignedByteReader>
      signedByteReading = signedBytePacketStructure.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputBuffer.inputStream());

    logger.info(signedByteReading.name() + " created");

    assertFalse(signedByteReading.isCompleted());
    assertTrue(signedByteReading.isEnabled());
    signedByteReading.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> signedByteCompletedField = signedByteReading.previousProcessingSteapResult();
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

    WkSrlzStruct<Integer, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedByte, WkSerdeUnsignedByteReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedByte, WkSerdeUnsignedByteWriter, WkSzOutputBytestreamBase<?>, WkSerdeUnsignedByte>
      unsignedBytePacketStructure = WkSerdeUnsignedByte.newStruct("SINGLE_UINT8");

    WkSzOutputPacket<Integer, WkSerdeUnsignedByte, WkSerdeUnsignedByteWriter>
      byteOutput = unsignedBytePacketStructure.newOutputPacket(b, WkSettingsSrlzPacketOperationData.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>>
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

    WkSzInputPacket<Integer, WkSerdeUnsignedByte, WkSerdeUnsignedByteReader>
      unsignedByteReading = unsignedBytePacketStructure.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputBuffer.inputStream());

    logger.info(unsignedByteReading.name() + " created");

    assertFalse(unsignedByteReading.isCompleted());
    assertTrue(unsignedByteReading.isEnabled());
    unsignedByteReading.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> unsignedByteCompletedField = unsignedByteReading.previousProcessingSteapResult();
    assertEquals(unsignedByteReading.firstOperation().get(), unsignedByteCompletedField.get());
    assertTrue(unsignedByteReading.isCompleted());
    //assertTrue(unsignedByteReading.isRequiredByProtocol());
    assertTrue(unsignedByteReading.firstOperation().get().result().isPresent());
    assertTrue(unsignedByteReading.firstOperation().get().result().get().serializable().isPresent());
    assertReadingValueEqualsTo((byte) 0xFF, (short) 0x00FF, 0x00FF, 0x00FFL, unsignedByteReading);
  }

  @Test
  public void test03_SignedBigEndianShort() {
    Short s = Short.valueOf((short) 0xFFFF);
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSrlzStruct<Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSerdeSignedBigEndianShort>
      signedBigEndianShort = WkSerdeSignedBigEndianShort.newStruct("SINGLE_SINT16BE");

    logger.info(signedBigEndianShort.name() + " created");

    WkSzOutputPacket<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter>
      bigEndianUnsignedShortSerializing = signedBigEndianShort.newOutputPacket(s, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortSerializing.name() + " created");

    assertFalse(bigEndianUnsignedShortSerializing.isCompleted());
    bigEndianUnsignedShortSerializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = bigEndianUnsignedShortSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortSerializing.isCompleted());
    assertEquals(bigEndianUnsignedShortSerializing.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortSerializing.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());

    WkSzInputPacket<Short, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader>
      bigEndianSignedShortDeserializing = signedBigEndianShort.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());
    logger.info(bigEndianSignedShortDeserializing.name() + " created");

    assertFalse(bigEndianSignedShortDeserializing.isCompleted());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().isEmpty());
    bigEndianSignedShortDeserializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = bigEndianSignedShortDeserializing.previousProcessingSteapResult();
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

    WkSrlzStruct<Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSerdeSignedLittleEndianShort>
      signedLittleEndianShort = WkSerdeSignedLittleEndianShort.newStruct("SINGLE_SINT16LE");
    logger.info(signedLittleEndianShort.name() + " created");

    WkSzOutputPacket<Short, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortWriter>
      littleEndianShortOutput = signedLittleEndianShort.newOutputPacket(s, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(littleEndianShortOutput.name() + " created");

    assertFalse(littleEndianShortOutput.isCompleted());
    littleEndianShortOutput.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = littleEndianShortOutput.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortOutput.isCompleted());
    assertEquals(littleEndianShortOutput.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortOutput.firstOperation().get().serializable());

    assertEquals(2,outputstream.size());

    WkSzInputPacket<Short, WkSerdeSignedLittleEndianShort, WkSerdeSignedLittleEndianShortReader>
      littleEndianSignedShortReading = signedLittleEndianShort.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());
    logger.info(littleEndianSignedShortReading.name() + " created");

    assertFalse(littleEndianSignedShortReading.isCompleted());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianSignedShortReading.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = littleEndianSignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStruct<Integer, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSerdeUnsignedBigEndianShort>
      unsignedBigEndianShort = WkSerdeUnsignedBigEndianShort.newStruct("SINGLE_UINT16BE");
    logger.info(unsignedBigEndianShort.name() + " created");

    WkSzOutputPacket<Integer, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortWriter>
      bigEndianUnsignedShortWriting = unsignedBigEndianShort.newOutputPacket(s, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortWriting.name() + " created");

    assertFalse(bigEndianUnsignedShortWriting.isCompleted());
    bigEndianUnsignedShortWriting.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = bigEndianUnsignedShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortWriting.isCompleted());
    assertEquals(bigEndianUnsignedShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xFA, (byte) 0xBC}));

    WkSzInputPacket<Integer, WkSerdeUnsignedBigEndianShort, WkSerdeUnsignedBigEndianShortReader>
      bigEndianUnsignedShortReading = unsignedBigEndianShort.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedShortReading.isCompleted());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedShortReading.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = bigEndianUnsignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStruct<Integer, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter, WkSzOutputBytestreamBase<?>, WkSerdeUnsignedLittleEndianShort>
      shortOutputProtocol = WkSerdeUnsignedLittleEndianShort.newStruct("SINGLE_UINT16LE");
    logger.info(shortOutputProtocol.name() + " created");

    WkSzOutputPacket<Integer, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter>
      littleEndianShortWriting = shortOutputProtocol.newOutputPacket(s, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xBC, (byte) 0xFA}));

    WkSzInputPacket<Integer, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortReader>
      littleEndianUnsignedShortReading = shortOutputProtocol.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());
    logger.info(littleEndianUnsignedShortReading.name() + " created");

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStruct<Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSerdeSignedBigEndianInteger>
      signedBigEndianInt = WkSerdeSignedBigEndianInteger.newStruct("SINGLE_SINT32BE");
    logger.info(signedBigEndianInt.name() + " created");

    WkSzOutputPacket<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>
      signedBigEndianIntSerializing = signedBigEndianInt.newOutputPacket(i, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(signedBigEndianIntSerializing.name() + " created");

    assertFalse(signedBigEndianIntSerializing.isCompleted());
    signedBigEndianIntSerializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = signedBigEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianIntSerializing.isCompleted());
    assertEquals(signedBigEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedBigEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSzInputPacket<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>
      signedBigEndianIntDeserializing = signedBigEndianInt.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianIntDeserializing.name() + " created");

    assertFalse(signedBigEndianIntDeserializing.isCompleted());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianIntDeserializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = signedBigEndianIntDeserializing.previousProcessingSteapResult();
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

    WkSrlzStruct<Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSerdeSignedLittleEndianInteger>
      signedLittleEndianInt = WkSerdeSignedLittleEndianInteger.newStruct("SINGLE_SINT32LE");
    logger.info(signedLittleEndianInt.name() + " created");

    WkSzOutputPacket<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerWriter>
      signedLittleEndianIntSerializing = signedLittleEndianInt.newOutputPacket(i, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(signedLittleEndianIntSerializing.name() + " created");

    assertFalse(signedLittleEndianIntSerializing.isCompleted());
    signedLittleEndianIntSerializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = signedLittleEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianIntSerializing.isCompleted());
    assertEquals(signedLittleEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedLittleEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSzInputPacket<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader>
      signedLittleEndianIntDeserializing = signedLittleEndianInt.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianIntDeserializing.name() + " created");

    assertFalse(signedLittleEndianIntDeserializing.isCompleted());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianIntDeserializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = signedLittleEndianIntDeserializing.previousProcessingSteapResult();
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

    WkSrlzStruct<Long, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSerdeUnsignedBigEndianInteger>
      unsignedBigEndianInt = WkSerdeUnsignedBigEndianInteger.newStruct("SINGLE_UINT32BE");
    logger.info(unsignedBigEndianInt.name() + " created");

    WkSzOutputPacket<Long, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerWriter>
      bigEndianShortWriting = unsignedBigEndianInt.newOutputPacket(i, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(bigEndianShortWriting.name() + " created");

    assertFalse(bigEndianShortWriting.isCompleted());
    bigEndianShortWriting.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = bigEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianShortWriting.isCompleted());
    assertEquals(bigEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, bigEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xFF, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSzInputPacket<Long, WkSerdeUnsignedBigEndianInteger, WkSerdeUnsignedBigEndianIntegerReader>
      bigEndianUnsignedIntReading = unsignedBigEndianInt.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedIntReading.isCompleted());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedIntReading.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = bigEndianUnsignedIntReading.previousProcessingSteapResult();
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

    WkSrlzStruct<Long, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerWriter, WkSzOutputBytestreamBase<?>, WkSerdeUnsignedLittleEndianInteger>
      unsignedLittleEndianInt = WkSerdeUnsignedLittleEndianInteger.newStruct("SINGLE_UINT32LE");
    logger.info(unsignedLittleEndianInt.name() + " created");

    WkSzOutputPacket<Long, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerWriter>
      littleEndianShortWriting = unsignedLittleEndianInt.newOutputPacket(i, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0xFF}));

    WkSzInputPacket<Long, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerReader>
      littleEndianUnsignedShortReading = unsignedLittleEndianInt.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStruct<Long, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter, WkSzOutputBytestreamBase<?>, WkSerdeSignedBigEndianLong>
      signedBigEndianLong = WkSerdeSignedBigEndianLong.newStruct("SINGLE_SINT64BE");
    logger.info(signedBigEndianLong.name() + " created");

    WkSzOutputPacket<Long, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongWriter>
      signedBigEndianLongSerializing = signedBigEndianLong.newOutputPacket(l, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(signedBigEndianLongSerializing.name() + " created");

    assertFalse(signedBigEndianLongSerializing.isCompleted());
    signedBigEndianLongSerializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = signedBigEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianLongSerializing.isCompleted());
    assertEquals(signedBigEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedBigEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                                        (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSzInputPacket<Long, WkSerdeSignedBigEndianLong, WkSerdeSignedBigEndianLongReader>
      signedBigEndianLongDeserializing = signedBigEndianLong.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianLongDeserializing.name() + " created");

    assertFalse(signedBigEndianLongDeserializing.isCompleted());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianLongDeserializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = signedBigEndianLongDeserializing.previousProcessingSteapResult();
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

    WkSrlzStruct<Long, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter, WkSzOutputBytestreamBase<?>, WkSerdeSignedLittleEndianLong>
      signedLittleEndianLong = WkSerdeSignedLittleEndianLong.newStruct("SINGLE_SINT64LE");
    logger.info(signedLittleEndianLong.name() + " created");

    WkSzOutputPacket<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter>
      signedLittleEndianLongSerializing = signedLittleEndianLong.newOutputPacket(l, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);
    logger.info(signedLittleEndianLongSerializing.name() + " created");

    assertFalse(signedLittleEndianLongSerializing.isCompleted());
    signedLittleEndianLongSerializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedField = signedLittleEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianLongSerializing.isCompleted());
    assertEquals(signedLittleEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedLittleEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89,
                                        (byte) 0x67, (byte) 0x45, (byte) 0x23, (byte) 0x01}));

    WkSzInputPacket<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader>
      signedLittleEndianLongDeserializing = signedLittleEndianLong.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianLongDeserializing.name() + " created");

    assertFalse(signedLittleEndianLongDeserializing.isCompleted());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianLongDeserializing.processBytestream();
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedInputField = signedLittleEndianLongDeserializing.previousProcessingSteapResult();
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
    WkSrlzInputPacketFieldFrameNode<? extends Number,?,?> reading) {
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
