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

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkDynamicCollectionTest
{

  public static final List<Short> ORIGINAL_LIST = Arrays.asList(Short.valueOf((short) 0),
                                                                Short.valueOf((short) 1),
                                                                Short.valueOf((short) 2),
                                                                Short.valueOf((short) 3));
  private static WkSerdeDtreeStruct<
                      List<Short>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDynamicCollection<
                        List<Short>, WkSerdeDtreeOperationSettings, ?, Integer,
                        WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger, ?, ?, ?,
                        ? extends WkSerdeSignedLittleEndianInteger, Short,
                        WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort,
                        WkSerdeSignedBigEndianShortReader, ?, ?, ?,
                        ? extends WkSerdeSignedBigEndianShort,
                        WkSerdeDtreeOperationSettingsVariableLength, ?>,
                      WkSerdeDtreeDynamicCollectionReader<
                        List<Short>, WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<List<Short>>,
                        WkSerdeDynamicCollection<
                          List<Short>, WkSerdeDtreeOperationSettings, ?, Integer,
                          WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader,
                          WkSerdeSignedLittleEndianInteger, ?, ?, ?,
                          ? extends WkSerdeSignedLittleEndianInteger, Short,
                          WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort,
                          WkSerdeSignedBigEndianShortReader, ?, ?, ?,
                          ? extends WkSerdeSignedBigEndianShort, WkSerdeDtreeOperationSettingsVariableLength, ?>,
                      Integer, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSerdeDtreeOperationSettingsVariableLength>, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings,
                      WkSerdeDynamicCollection<List<Short>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ? extends WkSerdeSignedLittleEndianInteger, Short, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ? extends WkSerdeSignedBigEndianShort, ?, WkSerdeDtreeOperationSettings>, WkSerdeDtreeDynamicCollectionWriter<List<Short>, WkSerdeDtreeOperationSettings, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<List<Short>>, WkSerdeDynamicCollection<List<Short>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ? extends WkSerdeSignedLittleEndianInteger, Short, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ? extends WkSerdeSignedBigEndianShort, ?, WkSerdeDtreeOperationSettings>, Integer, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeDtreeOperationSettings>, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeDynamicCollection<List<Short>, WkSerdeDtreeOperationSettings, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianInteger, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings>>
                              DYN_LIST_PACKET;

  @SuppressWarnings("unchecked")
  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    DYN_LIST_PACKET = WkSerdeDynamicCollection.<
                          List<Short>,
                          WkSerdeDtreeOperationSettings,
                          WkSerdeDtreeOperationSettings,
                          Integer,
                          WkSerdeDtreeOperationSettings,
                          WkSerdeSignedLittleEndianIntegerReader,
                          WkSerdeSignedLittleEndianInteger,
                          WkSerdeDtreeOperationSettings,
                          WkSerdeSignedLittleEndianIntegerWriter,
                          WkSerdeSignedLittleEndianInteger,
                          WkSerdeSignedLittleEndianInteger,
                          Short,
                          WkSerdeDtreeOperationSettings,
                          WkSerdeSignedBigEndianShort,
                          WkSerdeSignedBigEndianShortReader,
                          WkSerdeDtreeOperationSettings,
                          WkSerdeSignedBigEndianShort,
                          WkSerdeSignedBigEndianShortWriter,
                          WkSerdeSignedBigEndianShort,
                          WkSerdeDtreeOperationSettingsVariableLength,
                          WkSerdeDtreeOperationSettings>newStruct(
                              "DYN_SHORT_LIST",
                              0,
                              ORIGINAL_LIST.size()+1,
                              "SIZE",
                              WkSerdeDtreeOperationSettings::none,
                              WkSerdeDtreeOperationSettings::none,
                              Integer::valueOf,
                              WkSerdeSignedLittleEndianInteger::newCore,
                              "SHORT_LIST",
                              (i,yo) -> WkSerdeDtreeOperationSettingsVariableLength.withLength(yo.size().get().firstOperation().get().result().get().serializable().get().intValue()),
                              WkSerdeDtreeOperationSettings::none,
                              "SHORT",
                              WkSerdeSignedBigEndianShort::newCore,
                              WkSerdeDtreeOperationSettings::none,
                              WkSerdeDtreeOperationSettings::none,
                              (l) -> l,
                              (Class<List<Short>>)(Class<?>)List.class);

  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  public void setUp() throws Exception {
  }

  @AfterEach
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSerdeDtreeWriter<List<Short>, WkSerdeDynamicCollection<List<Short>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ? extends WkSerdeSignedLittleEndianInteger, Short, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ? extends WkSerdeSignedBigEndianShort, ?, WkSerdeDtreeOperationSettings>, WkSerdeDtreeDynamicCollectionWriter<List<Short>, WkSerdeDtreeOperationSettings, WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>, WkSerdeDtreeOperationResult<List<Short>>, WkSerdeDynamicCollection<List<Short>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ? extends WkSerdeSignedLittleEndianInteger, Short, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ? extends WkSerdeSignedBigEndianShort, ?, WkSerdeDtreeOperationSettings>, Integer, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeDtreeOperationSettings>>
      dynlistSerializer = DYN_LIST_PACKET.newOutputPacket(ORIGINAL_LIST, WkSerdeDtreeOperationSettings.EMPTY, outputstream);

    while(dynlistSerializer.isInProgress()) {
      dynlistSerializer.processBytestream();
    }

    WkSerdeDtreeReader<List<Short>, WkSerdeDynamicCollection<List<Short>, WkSerdeDtreeOperationSettings, ?, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ? extends WkSerdeSignedLittleEndianInteger, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, ?, ?, ?, ? extends WkSerdeSignedBigEndianShort, WkSerdeDtreeOperationSettingsVariableLength, ?>, WkSerdeDtreeDynamicCollectionReader<List<Short>, WkSerdeDtreeOperationSettings, WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, WkSerdeDtreeOperationResult<List<Short>>, WkSerdeDynamicCollection<List<Short>, WkSerdeDtreeOperationSettings, ?, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ? extends WkSerdeSignedLittleEndianInteger, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, ?, ?, ?, ? extends WkSerdeSignedBigEndianShort, WkSerdeDtreeOperationSettingsVariableLength, ?>, Integer, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSerdeDtreeOperationSettingsVariableLength>>
      dynlistDeserializer = DYN_LIST_PACKET.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());

    while(dynlistDeserializer.isInProgress()) {
      dynlistDeserializer.processBytestream();
    }

    assertEquals(ORIGINAL_LIST, dynlistDeserializer.firstOperation().get().result().get().serializable().get());
  }

}
