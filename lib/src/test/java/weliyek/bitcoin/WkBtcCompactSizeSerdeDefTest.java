/*
 * Copyright (C) 2024  Ricardo Villalobos - All Rights Reserved
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

class WkBtcCompactSizeSerdeDefTest
{

  @BeforeAll
  static void setUpBeforeClass() throws Exception {
  }

  @AfterAll
  static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  void setUp() throws Exception {
  }

  @AfterEach
  void tearDown() throws Exception {
  }

  @Test
  void testLowestByte() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");

    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(0L, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(1, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(0L, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(0L, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

  @Test
  void testHighestByte() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");

    long highestByte = WkBtcCompactSizeSerdeDef.HEADER_SHORT - 1;

    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(highestByte, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(1, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(highestByte, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(highestByte, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

  @Test
  void testLowestShort() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    long lowestShort = WkBtcCompactSizeSerdeDef.HEADER_SHORT;

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");

    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(lowestShort, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(3, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(WkBtcCompactSizeSerdeDef.HEADER_SHORT, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(lowestShort, compactSizeReader.firstOperation().get().uint16le().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(lowestShort, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

  @Test
  void testHighestShort() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    long highestShort = WkBtcCompactSizeSerdeDef.SHORT_LIMIT;

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");


    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(highestShort, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(3, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(WkBtcCompactSizeSerdeDef.HEADER_SHORT, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(highestShort, compactSizeReader.firstOperation().get().uint16le().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(highestShort, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

  @Test
  void testLowestInt() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    long lowestInt = WkBtcCompactSizeSerdeDef.SHORT_LIMIT + 1;

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");

    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(lowestInt, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(5, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(WkBtcCompactSizeSerdeDef.HEADER_INT, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(lowestInt, compactSizeReader.firstOperation().get().uint32le().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(lowestInt, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

  @Test
  void testHighestInt() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    long highestInt = WkBtcCompactSizeSerdeDef.INT_LIMIT;

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");


    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(highestInt, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(5, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(WkBtcCompactSizeSerdeDef.HEADER_INT, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(highestInt, compactSizeReader.firstOperation().get().uint32le().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(highestInt, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

  @Test
  void testLowestLong() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    long lowestLong = WkBtcCompactSizeSerdeDef.INT_LIMIT + 1;

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");

    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(lowestLong, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(9, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(WkBtcCompactSizeSerdeDef.HEADER_LONG, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(lowestLong, compactSizeReader.firstOperation().get().int64le().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(lowestLong, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

  @Test
  void testHighestLong() {
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    long allBitsSet = -1;

    WkSerdeDtreeStruct<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcCompactSizeSerdeDef>
      compactSize = WkBtcCompactSizeSerdeDef.newStruct("COMPACT_SIZE");


    WkSerdeDtreeWriter<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>
      compactSizeWriter = compactSize.newOutputPacket(allBitsSet, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();
    compactSizeWriter.processBytestream();

    assertTrue(compactSizeWriter.isCompleted());

    assertEquals(9, outputBuffer.size());

    WkSerdeDtreeReader<Long, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>
      compactSizeReader = compactSize.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();
    compactSizeReader.processBytestream();

    assertTrue(compactSizeReader.isCompleted());

    assertEquals(WkBtcCompactSizeSerdeDef.HEADER_LONG, compactSizeReader.firstOperation().get().firstByte().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(allBitsSet, compactSizeReader.firstOperation().get().int64le().get().firstOperation().get().result().get().serializable().get().longValue());
    assertEquals(allBitsSet, compactSizeReader.firstOperation().get().result().get().serializable().get().longValue());
  }

}
