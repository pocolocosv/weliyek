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
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
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
                          WkSzOperationSettings,
                          WkDynamicCollectionSrlzStructNode<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>,
                          WkDynamicCollectionSrlzInputPacketDecoderFrameNode<List<Short>, WkSzOperationSettings, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSzVariableLengthOperationSettings>,
                          WkSzInputBytestreamBase<?>,
                          WkSzOperationSettings,
                          WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSzOperationSettings>,
                          WkDynamicCollectionSrlzOutputPacketEncoderFrameNode< List<Short>, WkSzOperationSettings, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSzOperationSettings>, Integer, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSzOperationSettings>,
                          WkSzOutputBytestreamBase<?>,
                          WkDynamicCollectionSrlzStructNode<List<Short>, WkSzOperationSettings, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode, WkSzVariableLengthOperationSettings, WkSzOperationSettings>>
                              DYN_LIST_PACKET;

  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    DYN_LIST_PACKET = WkDynamicCollectionSrlzStructNode.<
                          List<Short>,
                          WkSzOperationSettings,
                          WkSzOperationSettings,
                          Integer,
                          WkSzOperationSettings,
                          WkSignedLittleEndianIntegerSrlzInputNode,
                          WkSignedLittleEndianIntegerSrlzStructNode,
                          WkSzOperationSettings,
                          WkSignedLittleEndianIntegerSrlzOutputNode,
                          WkSignedLittleEndianIntegerSrlzStructNode,
                          WkSignedLittleEndianIntegerSrlzStructNode,
                          Short,
                          WkSzOperationSettings,
                          WkSignedBigEndianShortSrlzStructNode,
                          WkSignedBigEndianShortSrlzInputNode,
                          WkSzOperationSettings,
                          WkSignedBigEndianShortSrlzStructNode,
                          WkSignedBigEndianShortSrlzOutputNode,
                          WkSignedBigEndianShortSrlzStructNode,
                          WkSzVariableLengthOperationSettings,
                          WkSzOperationSettings>newPacketStructure(
                              "DYN_SHORT_LIST",
                              0,
                              ORIGINAL_LIST.size()+1,
                              "SIZE",
                              WkSzOperationSettings::none,
                              WkSzOperationSettings::none,
                              Integer::valueOf,
                              WkSignedLittleEndianIntegerSrlzStructNode::newCore,
                              "SHORT_LIST",
                              (i,yo) -> WkSzVariableLengthOperationSettings.withLength(yo.size().field().get().firstOperation().get().result().get().deserialized().get().intValue()),
                              WkSzOperationSettings::none,
                              "SHORT",
                              WkSignedBigEndianShortSrlzStructNode::newCore,
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
    WkSzOutputPacket<List<Short>, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSzOperationSettings>, WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<List<Short>, WkSzOperationSettings, WkSzWritingRuntime<WkSzOutputBytestream>, WkSzWritingResult, WkDynamicCollectionSrlzStructNode<List<Short>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, Short, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, ?, ?, WkSzOperationSettings>, Integer, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode, WkSzOperationSettings>>
      dynlistSerializer = DYN_LIST_PACKET.newOutputPacket(ORIGINAL_LIST, WkSzOperationSettings.EMPTY, outputstream);

    while(dynlistSerializer.isInProgress()) {
      dynlistSerializer.processBytestream();
    }

    WkSzInputPacket<List<Short>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, WkDynamicCollectionSrlzInputPacketDecoderFrameNode<List<Short>, WkSzOperationSettings, WkSzReadingRuntime<WkSzInputBytestream>, WkSzReadingResult<List<Short>>, WkDynamicCollectionSrlzStructNode<List<Short>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?>, Integer, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode, WkSzVariableLengthOperationSettings>>
      dynlistDeserializer = DYN_LIST_PACKET.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    while(dynlistDeserializer.isInProgress()) {
      dynlistDeserializer.processBytestream();
    }

    assertEquals(ORIGINAL_LIST, dynlistDeserializer.firstOperation().get().result().get().deserialized().get());
  }

}
