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

import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzPacketOperationFrameNode;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.util.KetzaByteOutputStream;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WkPrimitiveSerializationTests {

  private static final Logger logger = LoggerFactory.getLogger(WkPrimitiveSerializationTests.class);

  @Test
  public void test01_SignedByte() {
    Byte b = Byte.valueOf((byte) 0xFF);
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSrlzStructComponentFrameNodeRootCore<Byte, WkSzOperationSettings, WkSignedByteSrlzStructNode, WkSignedByteSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSignedByteSrlzStructNode, WkSignedByteSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkSignedByteSrlzStructNode>
      signedBytePacketStructure = WkSignedByteSrlzStructNode.newPacketStructure("SINGLE_SINT8");

    logger.info(signedBytePacketStructure.name() + " created");

    WkSzOutputPacket<Byte, WkSignedByteSrlzStructNode, WkSignedByteSrlzOutputNode>
      byteOutput = signedBytePacketStructure.newOutputPacket(b, WkSzOperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = byteOutput.previousProcessingSteapResult();
    assertTrue(byteOutput.isCompleted());
    assertTrue(completedField.isPresent());
    assertEquals(byteOutput.firstOperation().get(), completedField.get());
    assertEquals(b, byteOutput.firstOperation().get().serializable());
    assertTrue(byteOutput.firstOperation().get().dashboard().bytestream().isClosed());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getFieldProcessedBytes());
    assertEquals(0, byteOutput.firstOperation().get().dashboard().bytestream().getStartIndexInGlobalBytestream());
    assertEquals(1, byteOutput.firstOperation().get().dashboard().bytestream().getTotalPacketProcessedBytes());

    assertEquals(1, outputBuffer.size());

    WkSzInputPacket<Byte, WkSignedByteSrlzStructNode, WkSignedByteSrlzInputNode>
      signedByteReading = signedBytePacketStructure.newInputPacket(WkSzOperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(signedByteReading.name() + " created");

    assertFalse(signedByteReading.isCompleted());
    assertTrue(signedByteReading.isEnabled());
    signedByteReading.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> signedByteCompletedField = signedByteReading.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Integer, WkSzOperationSettings, WkUnsignedByteSrlzStructNode, WkUnsignedByteSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkUnsignedByteSrlzStructNode, WkUnsignedByteSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkUnsignedByteSrlzStructNode>
      unsignedBytePacketStructure = WkUnsignedByteSrlzStructNode.newPacketStructure("SINGLE_UINT8");

    WkSzOutputPacket<Integer, WkUnsignedByteSrlzStructNode, WkUnsignedByteSrlzOutputNode>
      byteOutput = unsignedBytePacketStructure.newOutputPacket(b, WkSzOperationSettings.EMPTY, outputBuffer);

    logger.info(byteOutput.name() + " created");

    assertFalse(byteOutput.isCompleted());
    assertTrue(byteOutput.isEnabled());
    byteOutput.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>>
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

    WkSzInputPacket<Integer, WkUnsignedByteSrlzStructNode, WkUnsignedByteSrlzInputNode>
      unsignedByteReading = unsignedBytePacketStructure.newInputPacket(WkSzOperationSettings.EMPTY, outputBuffer.inputStream());

    logger.info(unsignedByteReading.name() + " created");

    assertFalse(unsignedByteReading.isCompleted());
    assertTrue(unsignedByteReading.isEnabled());
    unsignedByteReading.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> unsignedByteCompletedField = unsignedByteReading.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkSignedBigEndianShortSrlzStructNode>
      signedBigEndianShort = WkSignedBigEndianShortSrlzStructNode.newPacketStructure("SINGLE_SINT16BE");

    logger.info(signedBigEndianShort.name() + " created");

    WkSzOutputPacket<Short, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode>
      bigEndianUnsignedShortSerializing = signedBigEndianShort.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortSerializing.name() + " created");

    assertFalse(bigEndianUnsignedShortSerializing.isCompleted());
    bigEndianUnsignedShortSerializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = bigEndianUnsignedShortSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortSerializing.isCompleted());
    assertEquals(bigEndianUnsignedShortSerializing.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortSerializing.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());

    WkSzInputPacket<Short, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode>
      bigEndianSignedShortDeserializing = signedBigEndianShort.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(bigEndianSignedShortDeserializing.name() + " created");

    assertFalse(bigEndianSignedShortDeserializing.isCompleted());
    assertTrue(bigEndianSignedShortDeserializing.firstOperation().get().result().isEmpty());
    bigEndianSignedShortDeserializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = bigEndianSignedShortDeserializing.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Short, WkSzOperationSettings, WkSignedLittleEndianShortSrlzStructNode, WkSignedLittleEndianShortSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSignedLittleEndianShortSrlzStructNode, WkSignedLittleEndianShortSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkSignedLittleEndianShortSrlzStructNode>
      signedLittleEndianShort = WkSignedLittleEndianShortSrlzStructNode.newPacketStructure("SINGLE_SINT16LE");
    logger.info(signedLittleEndianShort.name() + " created");

    WkSzOutputPacket<Short, WkSignedLittleEndianShortSrlzStructNode, WkSignedLittleEndianShortSrlzOutputNode>
      littleEndianShortOutput = signedLittleEndianShort.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortOutput.name() + " created");

    assertFalse(littleEndianShortOutput.isCompleted());
    littleEndianShortOutput.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = littleEndianShortOutput.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortOutput.isCompleted());
    assertEquals(littleEndianShortOutput.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortOutput.firstOperation().get().serializable());

    assertEquals(2,outputstream.size());

    WkSzInputPacket<Short, WkSignedLittleEndianShortSrlzStructNode, WkSignedLittleEndianShortSrlzInputNode>
      littleEndianSignedShortReading = signedLittleEndianShort.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianSignedShortReading.name() + " created");

    assertFalse(littleEndianSignedShortReading.isCompleted());
    assertTrue(littleEndianSignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianSignedShortReading.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = littleEndianSignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Integer, WkSzOperationSettings, WkUnsignedBigEndianShortSrlzStructNode, WkUnsignedBigEndianShortSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkUnsignedBigEndianShortSrlzStructNode, WkUnsignedBigEndianShortSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkUnsignedBigEndianShortSrlzStructNode>
      unsignedBigEndianShort = WkUnsignedBigEndianShortSrlzStructNode.newPacketStructure("SINGLE_UINT16BE");
    logger.info(unsignedBigEndianShort.name() + " created");

    WkSzOutputPacket<Integer, WkUnsignedBigEndianShortSrlzStructNode, WkUnsignedBigEndianShortSrlzOutputNode>
      bigEndianUnsignedShortWriting = unsignedBigEndianShort.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianUnsignedShortWriting.name() + " created");

    assertFalse(bigEndianUnsignedShortWriting.isCompleted());
    bigEndianUnsignedShortWriting.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = bigEndianUnsignedShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianUnsignedShortWriting.isCompleted());
    assertEquals(bigEndianUnsignedShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, bigEndianUnsignedShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xFA, (byte) 0xBC}));

    WkSzInputPacket<Integer, WkUnsignedBigEndianShortSrlzStructNode, WkUnsignedBigEndianShortSrlzInputNode>
      bigEndianUnsignedShortReading = unsignedBigEndianShort.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedShortReading.isCompleted());
    assertTrue(bigEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedShortReading.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = bigEndianUnsignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Integer, WkSzOperationSettings, WkUnsignedLittleEndianShortSrlzStructNode, WkUnsignedLittleEndianShortSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkUnsignedLittleEndianShortSrlzStructNode, WkUnsignedLittleEndianShortSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkUnsignedLittleEndianShortSrlzStructNode>
      shortOutputProtocol = WkUnsignedLittleEndianShortSrlzStructNode.newPacketStructure("SINGLE_UINT16LE");
    logger.info(shortOutputProtocol.name() + " created");

    WkSzOutputPacket<Integer, WkUnsignedLittleEndianShortSrlzStructNode, WkUnsignedLittleEndianShortSrlzOutputNode>
      littleEndianShortWriting = shortOutputProtocol.newOutputPacket(s, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(s, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(2, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xBC, (byte) 0xFA}));

    WkSzInputPacket<Integer, WkUnsignedLittleEndianShortSrlzStructNode, WkUnsignedLittleEndianShortSrlzInputNode>
      littleEndianUnsignedShortReading = shortOutputProtocol.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(littleEndianUnsignedShortReading.name() + " created");

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkSignedBigEndianIntegerSrlzStructNode>
      signedBigEndianInt = WkSignedBigEndianIntegerSrlzStructNode.newPacketStructure("SINGLE_SINT32BE");
    logger.info(signedBigEndianInt.name() + " created");

    WkSzOutputPacket<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>
      signedBigEndianIntSerializing = signedBigEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianIntSerializing.name() + " created");

    assertFalse(signedBigEndianIntSerializing.isCompleted());
    signedBigEndianIntSerializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = signedBigEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianIntSerializing.isCompleted());
    assertEquals(signedBigEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedBigEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSzInputPacket<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>
      signedBigEndianIntDeserializing = signedBigEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianIntDeserializing.name() + " created");

    assertFalse(signedBigEndianIntDeserializing.isCompleted());
    assertTrue(signedBigEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianIntDeserializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = signedBigEndianIntDeserializing.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkSignedLittleEndianIntegerSrlzStructNode>
      signedLittleEndianInt = WkSignedLittleEndianIntegerSrlzStructNode.newPacketStructure("SINGLE_SINT32LE");
    logger.info(signedLittleEndianInt.name() + " created");

    WkSzOutputPacket<Integer, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzOutputNode>
      signedLittleEndianIntSerializing = signedLittleEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianIntSerializing.name() + " created");

    assertFalse(signedLittleEndianIntSerializing.isCompleted());
    signedLittleEndianIntSerializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = signedLittleEndianIntSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianIntSerializing.isCompleted());
    assertEquals(signedLittleEndianIntSerializing.firstOperation().get(), completedField.get());
    assertEquals(i, signedLittleEndianIntSerializing.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());

    WkSzInputPacket<Integer, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzInputNode>
      signedLittleEndianIntDeserializing = signedLittleEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianIntDeserializing.name() + " created");

    assertFalse(signedLittleEndianIntDeserializing.isCompleted());
    assertTrue(signedLittleEndianIntDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianIntDeserializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = signedLittleEndianIntDeserializing.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Long, WkSzOperationSettings, WkUnsignedBigEndianIntegerSrlzStructNode, WkUnsignedBigEndianIntegerSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkUnsignedBigEndianIntegerSrlzStructNode, WkUnsignedBigEndianIntegerSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkUnsignedBigEndianIntegerSrlzStructNode>
      unsignedBigEndianInt = WkUnsignedBigEndianIntegerSrlzStructNode.newPacketStructure("SINGLE_UINT32BE");
    logger.info(unsignedBigEndianInt.name() + " created");

    WkSzOutputPacket<Long, WkUnsignedBigEndianIntegerSrlzStructNode, WkUnsignedBigEndianIntegerSrlzOutputNode>
      bigEndianShortWriting = unsignedBigEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(bigEndianShortWriting.name() + " created");

    assertFalse(bigEndianShortWriting.isCompleted());
    bigEndianShortWriting.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = bigEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(bigEndianShortWriting.isCompleted());
    assertEquals(bigEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, bigEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xFF, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSzInputPacket<Long, WkUnsignedBigEndianIntegerSrlzStructNode, WkUnsignedBigEndianIntegerSrlzInputNode>
      bigEndianUnsignedIntReading = unsignedBigEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(bigEndianUnsignedIntReading.isCompleted());
    assertTrue(bigEndianUnsignedIntReading.firstOperation().get().result().isEmpty());
    bigEndianUnsignedIntReading.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = bigEndianUnsignedIntReading.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Long, WkSzOperationSettings, WkUnsignedLittleEndianIntegerSrlzStructNode, WkUnsignedLittleEndianIntegerSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkUnsignedLittleEndianIntegerSrlzStructNode, WkUnsignedLittleEndianIntegerSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkUnsignedLittleEndianIntegerSrlzStructNode>
      unsignedLittleEndianInt = WkUnsignedLittleEndianIntegerSrlzStructNode.newPacketStructure("SINGLE_UINT32LE");
    logger.info(unsignedLittleEndianInt.name() + " created");

    WkSzOutputPacket<Long, WkUnsignedLittleEndianIntegerSrlzStructNode, WkUnsignedLittleEndianIntegerSrlzOutputNode>
      littleEndianShortWriting = unsignedLittleEndianInt.newOutputPacket(i, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(littleEndianShortWriting.name() + " created");

    assertFalse(littleEndianShortWriting.isCompleted());
    littleEndianShortWriting.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = littleEndianShortWriting.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(littleEndianShortWriting.isCompleted());
    assertEquals(littleEndianShortWriting.firstOperation().get(), completedField.get());
    assertEquals(i, littleEndianShortWriting.firstOperation().get().serializable());

    assertEquals(4, outputstream.size());
    assertTrue(outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0xFF}));

    WkSzInputPacket<Long, WkUnsignedLittleEndianIntegerSrlzStructNode, WkUnsignedLittleEndianIntegerSrlzInputNode>
      littleEndianUnsignedShortReading = unsignedLittleEndianInt.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    assertFalse(littleEndianUnsignedShortReading.isCompleted());
    assertTrue(littleEndianUnsignedShortReading.firstOperation().get().result().isEmpty());
    littleEndianUnsignedShortReading.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = littleEndianUnsignedShortReading.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Long, WkSzOperationSettings, WkSignedBigEndianLongSrlzStructNode, WkSignedBigEndianLongSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSignedBigEndianLongSrlzStructNode, WkSignedBigEndianLongSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkSignedBigEndianLongSrlzStructNode>
      signedBigEndianLong = WkSignedBigEndianLongSrlzStructNode.newPacketStructure("SINGLE_SINT64BE");
    logger.info(signedBigEndianLong.name() + " created");

    WkSzOutputPacket<Long, WkSignedBigEndianLongSrlzStructNode, WkSignedBigEndianLongSrlzOutputNode>
      signedBigEndianLongSerializing = signedBigEndianLong.newOutputPacket(l, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedBigEndianLongSerializing.name() + " created");

    assertFalse(signedBigEndianLongSerializing.isCompleted());
    signedBigEndianLongSerializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = signedBigEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedBigEndianLongSerializing.isCompleted());
    assertEquals(signedBigEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedBigEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0x01, (byte) 0x23, (byte) 0x45, (byte) 0x67,
                                        (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF}));

    WkSzInputPacket<Long, WkSignedBigEndianLongSrlzStructNode, WkSignedBigEndianLongSrlzInputNode>
      signedBigEndianLongDeserializing = signedBigEndianLong.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedBigEndianLongDeserializing.name() + " created");

    assertFalse(signedBigEndianLongDeserializing.isCompleted());
    assertTrue(signedBigEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedBigEndianLongDeserializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = signedBigEndianLongDeserializing.previousProcessingSteapResult();
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

    WkSrlzStructComponentFrameNodeRootCore<Long, WkSzOperationSettings, WkSignedLittleEndianLongSrlzStructNode, WkSignedLittleEndianLongSrlzInputNode, WkSzInputBytestreamBase<?>, WkSzOperationSettings, WkSignedLittleEndianLongSrlzStructNode, WkSignedLittleEndianLongSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkSignedLittleEndianLongSrlzStructNode>
      signedLittleEndianLong = WkSignedLittleEndianLongSrlzStructNode.newPacketStructure("SINGLE_SINT64LE");
    logger.info(signedLittleEndianLong.name() + " created");

    WkSzOutputPacket<Long, WkSignedLittleEndianLongSrlzStructNode, WkSignedLittleEndianLongSrlzOutputNode>
      signedLittleEndianLongSerializing = signedLittleEndianLong.newOutputPacket(l, WkSzOperationSettings.EMPTY, outputstream);
    logger.info(signedLittleEndianLongSerializing.name() + " created");

    assertFalse(signedLittleEndianLongSerializing.isCompleted());
    signedLittleEndianLongSerializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedField = signedLittleEndianLongSerializing.previousProcessingSteapResult();
    assertTrue(completedField.isPresent());
    assertTrue(signedLittleEndianLongSerializing.isCompleted());
    assertEquals(signedLittleEndianLongSerializing.firstOperation().get(), completedField.get());
    assertEquals(l, signedLittleEndianLongSerializing.firstOperation().get().serializable());

    assertEquals(8, outputstream.size());
    assertTrue(
        outputstream.equals(new byte[] {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x89,
                                        (byte) 0x67, (byte) 0x45, (byte) 0x23, (byte) 0x01}));

    WkSzInputPacket<Long, WkSignedLittleEndianLongSrlzStructNode, WkSignedLittleEndianLongSrlzInputNode>
      signedLittleEndianLongDeserializing = signedLittleEndianLong.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
    logger.info(signedLittleEndianLongDeserializing.name() + " created");

    assertFalse(signedLittleEndianLongDeserializing.isCompleted());
    assertTrue(signedLittleEndianLongDeserializing.firstOperation().get().result().isEmpty());
    signedLittleEndianLongDeserializing.processBytestream();
    Optional<WkSrlzPacketOperationFrameNode<?,?,?,?,?>> completedInputField = signedLittleEndianLongDeserializing.previousProcessingSteapResult();
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
    WkSrlzInputPacketFieldFrameNode<? extends Number,?,?> reading) {
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
