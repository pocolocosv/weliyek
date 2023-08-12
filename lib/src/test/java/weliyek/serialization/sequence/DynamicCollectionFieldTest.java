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
package weliyek.serialization.sequence;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSzSignedBigEndianShort;
import weliyek.serialization.number.WkSzSignedBigEndianShortReader;
import weliyek.serialization.number.WkSzSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSzSignedLittleEndianInteger;
import weliyek.serialization.number.WkSzSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSzSignedLittleEndianIntegerWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

public class DynamicCollectionFieldTest
{

  public static final List<Short> ORIGINAL_LIST = Arrays.asList(Short.valueOf((short) 0),
                                                                Short.valueOf((short) 1),
                                                                Short.valueOf((short) 2),
                                                                Short.valueOf((short) 3));
  private static WkSzStruct<
                          List<Short>,
                          WkSzOperationSettings,
                          WkSzDynamicCollection<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>,
                          WkSzDynamicCollectionPacketReaderOperation<List<Short>, WkSzOperationSettings, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<List<Short>>, WkSzDynamicCollection<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, WkSzVariableLengthOperationSettings>,
                          WkSzInputBytestreamBase<?>,
                          WkSzOperationSettings,
                          WkSzDynamicCollection<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, WkSzOperationSettings>,
                          WkSzDynamicCollectionPacketWriterOperation< List<Short>, WkSzOperationSettings, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, WkSzDynamicCollection<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, WkSzOperationSettings>, Integer, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, WkSzOperationSettings>,
                          WkSzOutputBytestreamBase<?>,
                          WkSzDynamicCollection<List<Short>, WkSzOperationSettings, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianInteger, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, WkSzVariableLengthOperationSettings, WkSzOperationSettings>>
                              DYN_LIST_PACKET;

  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    DYN_LIST_PACKET = WkSzDynamicCollection.<
                          List<Short>,
                          WkSzOperationSettings,
                          WkSzOperationSettings,
                          Integer,
                          WkSzOperationSettings,
                          WkSzSignedLittleEndianIntegerReader,
                          WkSzSignedLittleEndianInteger,
                          WkSzOperationSettings,
                          WkSzSignedLittleEndianIntegerWriter,
                          WkSzSignedLittleEndianInteger,
                          WkSzSignedLittleEndianInteger,
                          Short,
                          WkSzOperationSettings,
                          WkSzSignedBigEndianShort,
                          WkSzSignedBigEndianShortReader,
                          WkSzOperationSettings,
                          WkSzSignedBigEndianShort,
                          WkSzSignedBigEndianShortWriter,
                          WkSzSignedBigEndianShort,
                          WkSzVariableLengthOperationSettings,
                          WkSzOperationSettings>newPacketStructure(
                              "DYN_SHORT_LIST",
                              0,
                              ORIGINAL_LIST.size()+1,
                              "SIZE",
                              WkSzOperationSettings::none,
                              WkSzOperationSettings::none,
                              Integer::valueOf,
                              WkSzSignedLittleEndianInteger::newCore,
                              "SHORT_LIST",
                              (i,yo) -> WkSzVariableLengthOperationSettings.withLength(yo.size().field().get().firstOperation().get().result().get().deserialized().get().intValue()),
                              WkSzOperationSettings::none,
                              "SHORT",
                              WkSzSignedBigEndianShort::newCore,
                              WkSzOperationSettings::none,
                              WkSzOperationSettings::none,
                              (l) -> l,
                              (Class<List<Short>>)(Class<?>)List.class);

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
  public void test() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSzOutputPacket<List<Short>, WkSzDynamicCollection<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, WkSzOperationSettings>, WkSzDynamicCollectionPacketWriterOperation<List<Short>, WkSzOperationSettings, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, WkSzDynamicCollection<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, WkSzOperationSettings>, Integer, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, WkSzOperationSettings>>
      dynlistSerializer = DYN_LIST_PACKET.newOutputPacket(ORIGINAL_LIST, WkSzOperationSettings.EMPTY, outputstream);

    while(dynlistSerializer.isInProgress()) {
      dynlistSerializer.processBytestream();
    }

    WkSzInputPacket<List<Short>, WkSzDynamicCollection<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, WkSzDynamicCollectionPacketReaderOperation<List<Short>, WkSzOperationSettings, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<List<Short>>, WkSzDynamicCollection<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, Short, WkSzOperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, WkSzVariableLengthOperationSettings>>
      dynlistDeserializer = DYN_LIST_PACKET.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    while(dynlistDeserializer.isInProgress()) {
      dynlistDeserializer.processBytestream();
    }

    assertEquals(ORIGINAL_LIST, dynlistDeserializer.firstOperation().get().result().get().deserialized().get());
  }

}
