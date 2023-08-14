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
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzOutputNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzStructNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzInputNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzOutputNode;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkDynamicCollectionTest
{

  public static final List<Short> ORIGINAL_LIST = Arrays.asList(Short.valueOf((short) 0),
                                                                Short.valueOf((short) 1),
                                                                Short.valueOf((short) 2),
                                                                Short.valueOf((short) 3));
  private static WkSrlzStructComponentFrameNodeRootCore<
                          List<Short>,
                          WkSettingsSrlzPacketOperationData,
                          WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>,
                          WkDynamicCollectionSrlzInputPacketDecoderFrameNode<List<Short>, WkSettingsSrlzPacketOperationData, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSzVariableLengthOperationSettings>,
                          WkSzInputBytestreamBase<?>,
                          WkSettingsSrlzPacketOperationData,
                          WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSettingsSrlzPacketOperationData>,
                          WkDynamicCollectionSrlzOutputPacketEncoderFrameNode< List<Short>, WkSettingsSrlzPacketOperationData, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSettingsSrlzPacketOperationData>, Integer, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSettingsSrlzPacketOperationData>,
                          WkSzOutputBytestreamBase<?>,
                          WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode, WkSzVariableLengthOperationSettings, WkSettingsSrlzPacketOperationData>>
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
                          WkSignedLittleEndianIntegerSrlzInputNode,
                          WkSignedLittleEndianIntegerSrlzStructNode,
                          WkSettingsSrlzPacketOperationData,
                          WkSignedLittleEndianIntegerSrlzOutputNode,
                          WkSignedLittleEndianIntegerSrlzStructNode,
                          WkSignedLittleEndianIntegerSrlzStructNode,
                          Short,
                          WkSettingsSrlzPacketOperationData,
                          WkSignedBigEndianShortSrlzStructNode,
                          WkSignedBigEndianShortSrlzInputNode,
                          WkSettingsSrlzPacketOperationData,
                          WkSignedBigEndianShortSrlzStructNode,
                          WkSignedBigEndianShortSrlzOutputNode,
                          WkSignedBigEndianShortSrlzStructNode,
                          WkSzVariableLengthOperationSettings,
                          WkSettingsSrlzPacketOperationData>newPacketStructure(
                              "DYN_SHORT_LIST",
                              0,
                              ORIGINAL_LIST.size()+1,
                              "SIZE",
                              WkSettingsSrlzPacketOperationData::none,
                              WkSettingsSrlzPacketOperationData::none,
                              Integer::valueOf,
                              WkSignedLittleEndianIntegerSrlzStructNode::newCore,
                              "SHORT_LIST",
                              (i,yo) -> WkSzVariableLengthOperationSettings.withLength(yo.size().field().get().firstOperation().get().result().get().deserialized().get().intValue()),
                              WkSettingsSrlzPacketOperationData::none,
                              "SHORT",
                              WkSignedBigEndianShortSrlzStructNode::newCore,
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
    WkSzOutputPacket<List<Short>, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSettingsSrlzPacketOperationData>, WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<List<Short>, WkSettingsSrlzPacketOperationData, WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>, WkEncodingResultSrlzPacketOperationData, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSettingsSrlzPacketOperationData>, Integer, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSettingsSrlzPacketOperationData>>
      dynlistSerializer = DYN_LIST_PACKET.newOutputPacket(ORIGINAL_LIST, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);

    while(dynlistSerializer.isInProgress()) {
      dynlistSerializer.processBytestream();
    }

    WkSzInputPacket<List<Short>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, WkDynamicCollectionSrlzInputPacketDecoderFrameNode<List<Short>, WkSettingsSrlzPacketOperationData, WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>, WkDecodingResultSrlzPacketOperationData<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSzVariableLengthOperationSettings>>
      dynlistDeserializer = DYN_LIST_PACKET.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());

    while(dynlistDeserializer.isInProgress()) {
      dynlistDeserializer.processBytestream();
    }

    assertEquals(ORIGINAL_LIST, dynlistDeserializer.firstOperation().get().result().get().deserialized().get());
  }

}
