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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzOutputNode;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkFixedAndVariableSizeCollectionTest
{

  private static final Logger logger = LoggerFactory.getLogger(WkFixedAndVariableSizeCollectionTest.class);

  public static final List<Integer> ORIGINAL_LIST = Arrays.asList(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
  public static final int LIST_SIZE = ORIGINAL_LIST.size();

  private static WkSrlzStructComponentFrameNodeRootCore<
                        List<Integer>,
                        WkSzOperationSettings,
                        FixedSizeCollectionField<List<Integer>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ?, ?>,
                        FixedSizeCollectionFieldDeserializer<List<Integer>, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        FixedSizeCollectionField<List<Integer>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ?>,
                        FixedSizeCollectionFieldSerializer<List<Integer>, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>,
                        WkSzOutputBytestreamBase<?>,
                        FixedSizeCollectionField<List<Integer>, WkSzOperationSettings, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSignedBigEndianIntegerSrlzStructNode>>
                          FIXED_SIZE_INT_LIST;

  private static WkSrlzStructComponentFrameNodeRootCore<
                        List<Integer>,
                        WkSzVariableLengthOperationSettings,
                        VariableSizeCollectionField<List<Integer>, WkSzVariableLengthOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ?, ?>,
                        VariableSizeCollectionFieldDeserializer<List<Integer>, WkSzVariableLengthOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        VariableSizeCollectionField<List<Integer>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ?>,
                        VariableSizeCollectionFieldSerializer<List<Integer>, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>,
                        WkSzOutputBytestreamBase<?>,
                        VariableSizeCollectionField<List<Integer>, WkSzVariableLengthOperationSettings, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, WkSignedBigEndianIntegerSrlzStructNode>>
                          VAR_SIZE_INT_LIST;


  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    FIXED_SIZE_INT_LIST = FixedSizeCollectionField.<
                              List<Integer>,
                              WkSzOperationSettings,
                              WkSzOperationSettings,
                              Integer,
                              WkSzOperationSettings,
                              WkSignedBigEndianIntegerSrlzStructNode,
                              WkSignedBigEndianIntegerSrlzInputNode,
                              WkSzOperationSettings,
                              WkSignedBigEndianIntegerSrlzStructNode,
                              WkSignedBigEndianIntegerSrlzOutputNode,
                              WkSignedBigEndianIntegerSrlzStructNode>newPacketStructure(
                                  "FIXEDINTLIST",
                                  "INT",
                                  ORIGINAL_LIST.size(),
                                  (Class<List<Integer>>)(Class<?>)List.class,
                                  (l) -> l,
                                  WkSzOperationSettings::none,
                                  WkSzOperationSettings::none,
                                  WkSignedBigEndianIntegerSrlzStructNode::newCore);

    VAR_SIZE_INT_LIST = VariableSizeCollectionField.<
                              List<Integer>,
                              WkSzVariableLengthOperationSettings,
                              WkSzOperationSettings,
                              Integer,
                              WkSzOperationSettings,
                              WkSignedBigEndianIntegerSrlzStructNode,
                              WkSignedBigEndianIntegerSrlzInputNode,
                              WkSzOperationSettings,
                              WkSignedBigEndianIntegerSrlzStructNode,
                              WkSignedBigEndianIntegerSrlzOutputNode,
                              WkSignedBigEndianIntegerSrlzStructNode>newPacketStructure(
                                "FIXEDINTLIST",
                                "INT",
                                0,
                                ORIGINAL_LIST.size() + 10,
                                (Class<List<Integer>>)(Class<?>)List.class,
                                WkSignedBigEndianIntegerSrlzStructNode::newCore,
                                (i,ao) -> WkSzVariableLengthOperationSettings.withLength(ORIGINAL_LIST.size()),
                                WkSzOperationSettings::none,
                                (l) -> l);
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
  public void testFixedSizeCollectionField() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzOutputPacket<List<Integer>, FixedSizeCollectionField<List<Integer>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ?>, FixedSizeCollectionFieldSerializer<List<Integer>, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>>
      listWriting = FIXED_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST,
                                                        WkSzOperationSettings.EMPTY,
                                                        outputstream);
    logger.info(listWriting.toString() + " created");

    assertFalse(listWriting.isCompleted());
    assertEquals(0, listWriting.firstOperation().get().dashboard().nextElementIndex());

    while(listWriting.isInProgress()) {
      listWriting.processBytestream();
    }

    assertTrue(listWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, listWriting.firstOperation().get().serializable());

    WkSzInputPacket<List<Integer>, FixedSizeCollectionField<List<Integer>, WkSzOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ?, ?>, FixedSizeCollectionFieldDeserializer<List<Integer>, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>>
      listReading = FIXED_SIZE_INT_LIST.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());

    logger.info(listReading + " created");

    while(listReading.isInProgress()) {
      listReading.processBytestream();
    }

    assertTrue(listReading.isCompleted());

    assertEquals(ORIGINAL_LIST, listReading.firstOperation().get().result().get().deserialized().get());
  }

  @Test
  public void testVariableSizeCollectionField() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzOutputPacket<List<Integer>, VariableSizeCollectionField<List<Integer>, ?, WkSzOperationSettings, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ?>, VariableSizeCollectionFieldSerializer<List<Integer>, WkSzOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>>
      varlistWriting = VAR_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST, WkSzOperationSettings.EMPTY, outputstream);

    logger.info(varlistWriting.toString() + " created");

    assertFalse(varlistWriting.isCompleted());
    assertEquals(0, varlistWriting.firstOperation().get().dashboard().nextElementIndex());

    while(varlistWriting.isInProgress()) {
      varlistWriting.processBytestream();
    }

    assertTrue(varlistWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, varlistWriting.firstOperation().get().serializable());

    WkSzCountingInputBytestream inputbytestream = new WkSzCountingInputBytestream(outputstream.inputStream());

    WkSzInputPacket<List<Integer>, VariableSizeCollectionField<List<Integer>, WkSzVariableLengthOperationSettings, ?, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<List<Integer>, WkSzVariableLengthOperationSettings, Integer, WkSzOperationSettings, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>>
      varListReading = VAR_SIZE_INT_LIST.newInputPacket(WkSzVariableLengthOperationSettings.withLength(ORIGINAL_LIST.size()), inputbytestream);

    logger.info(varListReading + " created");

    while(varListReading.isInProgress()) {
      varListReading.processBytestream();
    }

    assertTrue(varListReading.isCompleted());

    assertEquals(ORIGINAL_LIST, varListReading.firstOperation().get().result().get().deserialized().get());
  }

}