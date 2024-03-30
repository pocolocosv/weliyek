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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkDynamicCollectionTest
{

  public static final List<Short> ORIGINAL_LIST = Arrays.asList(Short.valueOf((short) 0),
                                                                Short.valueOf((short) 1),
                                                                Short.valueOf((short) 2),
                                                                Short.valueOf((short) 3));
  private static WkSrlzStruct<
                          List<Short>,
                          WkSettingsSrlzPacketOperationData,
                          WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>,
                          WkDynamicCollectionSrlzInputPacketDecoderFrameNode<List<Short>, WkSettingsSrlzPacketOperationData, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSzVariableLengthOperationSettings>,
                          WkSzInputBytestreamBase<?>,
                          WkSettingsSrlzPacketOperationData,
                          WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ?, ?, WkSettingsSrlzPacketOperationData>,
                          WkDynamicCollectionSrlzOutputPacketEncoderFrameNode< List<Short>, WkSettingsSrlzPacketOperationData, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ?, ?, WkSettingsSrlzPacketOperationData>, Integer, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSettingsSrlzPacketOperationData>,
                          WkSzOutputBytestreamBase<?>,
                          WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianInteger, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, WkSzVariableLengthOperationSettings, WkSettingsSrlzPacketOperationData>>
                              DYN_LIST_PACKET;

  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    DYN_LIST_PACKET = WkDynamicCollectionSrlzStructNode.<
                          List<Short>,
                          WkSettingsSrlzPacketOperationData,
                          WkSettingsSrlzPacketOperationData,
                          Integer,
                          WkSettingsSrlzPacketOperationData,
                          WkSerdeSignedLittleEndianIntegerReader,
                          WkSerdeSignedLittleEndianInteger,
                          WkSettingsSrlzPacketOperationData,
                          WkSerdeSignedLittleEndianIntegerWriter,
                          WkSerdeSignedLittleEndianInteger,
                          WkSerdeSignedLittleEndianInteger,
                          Short,
                          WkSettingsSrlzPacketOperationData,
                          WkSerdeSignedBigEndianShort,
                          WkSerdeSignedBigEndianShortReader,
                          WkSettingsSrlzPacketOperationData,
                          WkSerdeSignedBigEndianShort,
                          WkSerdeSignedBigEndianShortWriter,
                          WkSerdeSignedBigEndianShort,
                          WkSzVariableLengthOperationSettings,
                          WkSettingsSrlzPacketOperationData>newStruct(
                              "DYN_SHORT_LIST",
                              0,
                              ORIGINAL_LIST.size()+1,
                              "SIZE",
                              WkSettingsSrlzPacketOperationData::none,
                              WkSettingsSrlzPacketOperationData::none,
                              Integer::valueOf,
                              WkSerdeSignedLittleEndianInteger::newCore,
                              "SHORT_LIST",
                              (i,yo) -> WkSzVariableLengthOperationSettings.withLength(yo.size().field().get().firstOperation().get().result().get().serializable().get().intValue()),
                              WkSettingsSrlzPacketOperationData::none,
                              "SHORT",
                              WkSerdeSignedBigEndianShort::newCore,
                              WkSettingsSrlzPacketOperationData::none,
                              WkSettingsSrlzPacketOperationData::none,
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
    WkSzOutputPacket<List<Short>, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ?, ?, WkSettingsSrlzPacketOperationData>, WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<List<Short>, WkSettingsSrlzPacketOperationData, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkResultSrlzPacketOperationData<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, ?, ?, WkSettingsSrlzPacketOperationData>, Integer, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortWriter, WkSettingsSrlzPacketOperationData>>
      dynlistSerializer = DYN_LIST_PACKET.newOutputPacket(ORIGINAL_LIST, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);

    while(dynlistSerializer.isInProgress()) {
      dynlistSerializer.processBytestream();
    }

    WkSzInputPacket<List<Short>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, WkDynamicCollectionSrlzInputPacketDecoderFrameNode<List<Short>, WkSettingsSrlzPacketOperationData, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkResultSrlzPacketOperationData<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, Short, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader, WkSzVariableLengthOperationSettings>>
      dynlistDeserializer = DYN_LIST_PACKET.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());

    while(dynlistDeserializer.isInProgress()) {
      dynlistDeserializer.processBytestream();
    }

    assertEquals(ORIGINAL_LIST, dynlistDeserializer.firstOperation().get().result().get().serializable().get());
  }

}
