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
import static org.junit.jupiter.api.Assertions.assertFalse;
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

class WkBtcNetMessageStartTest
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
  void testValues() {
    WkBtcNetMessageStart unknown = WkBtcNetMessageStart.newMagic(0);
    assertFalse(unknown.isKnown());

    WkBtcNetMessageStart main = WkBtcNetMessageStart.newMagic(0xD9B4BEF9);
    assertEquals(WkBtcNetMessageStart.MAIN, main);
  }

  @Test
  void testSerde() {
    WkSerdeDtreeStruct<WkBtcNetMessageStart, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeDef, WkBtcNetMessageStartSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeDef, WkBtcNetMessageStartSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcNetMessageStartSerdeDef>
      magicProto = WkBtcNetMessageStartSerdeDef.newStruct("MAGIC");

    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeWriter<WkBtcNetMessageStart, WkBtcNetMessageStartSerdeDef, WkBtcNetMessageStartSerdeWriter>
      magicWriter = magicProto.newOutputPacket(WkBtcNetMessageStart.MAIN, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    magicWriter.processBytestream();

    assertTrue(magicWriter.isCompleted());

    assertEquals(Integer.BYTES, outputBuffer.size());

    WkSerdeDtreeReader<WkBtcNetMessageStart, WkBtcNetMessageStartSerdeDef, WkBtcNetMessageStartSerdeReader>
      magicReader = magicProto.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    magicReader.processBytestream();

    assertTrue(magicReader.isCompleted());

    assertEquals(WkBtcNetMessageStart.MAIN.value, magicReader.firstOperation().get().uint32le().get().firstOperation().get().result().get().serializable().get());
    assertEquals(WkBtcNetMessageStart.MAIN, magicReader.firstOperation().get().result().get().serializable().get());
  }

}
