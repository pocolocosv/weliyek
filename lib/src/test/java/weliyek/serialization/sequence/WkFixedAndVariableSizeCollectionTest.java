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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkFixedAndVariableSizeCollectionTest
{

  private static final Logger logger = LoggerFactory.getLogger(WkFixedAndVariableSizeCollectionTest.class);

  public static final List<Integer> ORIGINAL_LIST = Arrays.asList(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
  public static final int LIST_SIZE = ORIGINAL_LIST.size();

  private static WkSrlzStruct<
                        List<Integer>,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeCollectionSrlzStructNode<List<Integer>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?, ?>,
                        WkFixedSizeCollectionSrlzInputNode<List<Integer>, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeCollectionSrlzStructNode<List<Integer>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ?>,
                        WkFixedSizeCollectionSrlzOutputNode<List<Integer>, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                        WkSzOutputBytestreamBase<?>,
                        WkFixedSizeCollectionSrlzStructNode<List<Integer>, WkSettingsSrlzPacketOperationData, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
                          FIXED_SIZE_INT_LIST;

  private static WkSrlzStruct<
                        List<Integer>,
                        WkSzVariableLengthOperationSettings,
                        WkVariableSizeCollectionSrlzStructNode<List<Integer>, WkSzVariableLengthOperationSettings, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?, ?>,
                        WkVariableSizeCollectionSrlzInputNode<List<Integer>, WkSzVariableLengthOperationSettings, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkVariableSizeCollectionSrlzStructNode<List<Integer>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ?>,
                        WkVariableSizeCollectionSrlzOutputNode<List<Integer>, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                        WkSzOutputBytestreamBase<?>,
                        WkVariableSizeCollectionSrlzStructNode<List<Integer>, WkSzVariableLengthOperationSettings, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
                          VAR_SIZE_INT_LIST;


  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    FIXED_SIZE_INT_LIST = WkFixedSizeCollectionSrlzStructNode.<
                              List<Integer>,
                              WkSettingsSrlzPacketOperationData,
                              WkSettingsSrlzPacketOperationData,
                              Integer,
                              WkSettingsSrlzPacketOperationData,
                              WkSerdeSignedBigEndianInteger,
                              WkSerdeSignedBigEndianIntegerReader,
                              WkSettingsSrlzPacketOperationData,
                              WkSerdeSignedBigEndianInteger,
                              WkSerdeSignedBigEndianIntegerWriter,
                              WkSerdeSignedBigEndianInteger>newStruct(
                                  "FIXEDINTLIST",
                                  "INT",
                                  ORIGINAL_LIST.size(),
                                  (Class<List<Integer>>)(Class<?>)List.class,
                                  (l) -> l,
                                  WkSettingsSrlzPacketOperationData::none,
                                  WkSettingsSrlzPacketOperationData::none,
                                  WkSerdeSignedBigEndianInteger::newCore);

    VAR_SIZE_INT_LIST = WkVariableSizeCollectionSrlzStructNode.<
                              List<Integer>,
                              WkSzVariableLengthOperationSettings,
                              WkSettingsSrlzPacketOperationData,
                              Integer,
                              WkSettingsSrlzPacketOperationData,
                              WkSerdeSignedBigEndianInteger,
                              WkSerdeSignedBigEndianIntegerReader,
                              WkSettingsSrlzPacketOperationData,
                              WkSerdeSignedBigEndianInteger,
                              WkSerdeSignedBigEndianIntegerWriter,
                              WkSerdeSignedBigEndianInteger>newStruct(
                                "FIXEDINTLIST",
                                "INT",
                                0,
                                ORIGINAL_LIST.size() + 10,
                                (Class<List<Integer>>)(Class<?>)List.class,
                                WkSerdeSignedBigEndianInteger::newCore,
                                (i,ao) -> WkSzVariableLengthOperationSettings.withLength(ORIGINAL_LIST.size()),
                                WkSettingsSrlzPacketOperationData::none,
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

    WkSzOutputPacket<List<Integer>, WkFixedSizeCollectionSrlzStructNode<List<Integer>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ?>, WkFixedSizeCollectionSrlzOutputNode<List<Integer>, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>>
      listWriting = FIXED_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST,
                                                        WkSettingsSrlzPacketOperationData.EMPTY,
                                                        outputstream);
    logger.info(listWriting.toString() + " created");

    assertFalse(listWriting.isCompleted());
    assertEquals(0, listWriting.firstOperation().get().dashboard().nextElementIndex());

    while(listWriting.isInProgress()) {
      listWriting.processBytestream();
    }

    assertTrue(listWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, listWriting.firstOperation().get().serializable());

    WkSzInputPacket<List<Integer>, WkFixedSizeCollectionSrlzStructNode<List<Integer>, WkSettingsSrlzPacketOperationData, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?, ?>, WkFixedSizeCollectionSrlzInputNode<List<Integer>, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>>
      listReading = FIXED_SIZE_INT_LIST.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());

    logger.info(listReading + " created");

    while(listReading.isInProgress()) {
      listReading.processBytestream();
    }

    assertTrue(listReading.isCompleted());

    assertEquals(ORIGINAL_LIST, listReading.firstOperation().get().result().get().serializable().get());
  }

  @Test
  public void testVariableSizeCollectionField() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSzOutputPacket<List<Integer>, WkVariableSizeCollectionSrlzStructNode<List<Integer>, ?, WkSettingsSrlzPacketOperationData, Integer, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ?>, WkVariableSizeCollectionSrlzOutputNode<List<Integer>, WkSettingsSrlzPacketOperationData, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>>
      varlistWriting = VAR_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST, WkSettingsSrlzPacketOperationData.EMPTY, outputstream);

    logger.info(varlistWriting.toString() + " created");

    assertFalse(varlistWriting.isCompleted());
    assertEquals(0, varlistWriting.firstOperation().get().dashboard().nextElementIndex());

    while(varlistWriting.isInProgress()) {
      varlistWriting.processBytestream();
    }

    assertTrue(varlistWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, varlistWriting.firstOperation().get().serializable());

    WkSzCountingInputBytestream inputbytestream = new WkSzCountingInputBytestream(outputstream.inputStream());

    WkSzInputPacket<List<Integer>, WkVariableSizeCollectionSrlzStructNode<List<Integer>, WkSzVariableLengthOperationSettings, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?, ?>, WkVariableSizeCollectionSrlzInputNode<List<Integer>, WkSzVariableLengthOperationSettings, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>>
      varListReading = VAR_SIZE_INT_LIST.newInputPacket(WkSzVariableLengthOperationSettings.withLength(ORIGINAL_LIST.size()), inputbytestream);

    logger.info(varListReading + " created");

    while(varListReading.isInProgress()) {
      varListReading.processBytestream();
    }

    assertTrue(varListReading.isCompleted());

    assertEquals(ORIGINAL_LIST, varListReading.firstOperation().get().result().get().serializable().get());
  }

}
