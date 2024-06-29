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

class WkBtcProtocolVersionTest
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
    WkBtcProtocolVersion zero = WkBtcProtocolVersion.newVersion(0);
    assertFalse(zero.isKnown());

    WkBtcProtocolVersion declared = WkBtcProtocolVersion.newVersion(70016);
    assertTrue(declared.isKnown());
    assertEquals(WkBtcProtocolVersion.WTXID_RELAY, declared);
  }

  @Test
  void testSerde() {
    WkSerdeDtreeStruct<WkBtcProtocolVersion, WkSerdeDtreeOperationSettings, WkBtcProtocolVersionSerdeDef, WkBtcProtocolVersionSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcProtocolVersionSerdeDef, WkBtcProtocolVersionSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcProtocolVersionSerdeDef>
      versionSerde = WkBtcProtocolVersionSerdeDef.newStruct("PROTOVER");

    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeWriter<WkBtcProtocolVersion, WkBtcProtocolVersionSerdeDef, WkBtcProtocolVersionSerdeWriter>
      verWriter = versionSerde.newOutputPacket(WkBtcProtocolVersion.WTXID_RELAY, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    verWriter.processBytestream();

    assertTrue(verWriter.isCompleted());

    assertEquals(4, outputBuffer.size());

    WkSerdeDtreeReader<WkBtcProtocolVersion, WkBtcProtocolVersionSerdeDef, WkBtcProtocolVersionSerdeReader>
      verReader = versionSerde.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    verReader.processBytestream();

    assertTrue(verReader.isCompleted());

    assertEquals(WkBtcProtocolVersion.WTXID_RELAY.value, verReader.firstOperation().get().int32le().get().firstOperation().get().result().get().serializable().get().intValue());
    assertEquals(WkBtcProtocolVersion.WTXID_RELAY, verReader.firstOperation().get().result().get().serializable().get());
  }

}
