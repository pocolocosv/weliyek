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
package weliyek.amat.basic.aggregator.sequence;

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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.WkSzCountingInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.input.WkSzInputPacket;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzOutputPacket;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzSignedBigEndianInteger;
import weliyek.amat.basic.number.WkSzSignedBigEndianIntegerReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianIntegerWriter;
import weliyek.ketza.util.KetzaByteOutputStream;
import weliyek.serialization.WkSzStruct;

public class FixedAndVariableSizeCollectionFieldsTest
{

  private static final Logger logger = LoggerFactory.getLogger(FixedAndVariableSizeCollectionFieldsTest.class);

  public static final List<Integer> ORIGINAL_LIST = Arrays.asList(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
  public static final int LIST_SIZE = ORIGINAL_LIST.size();

  private static WkSzStruct<
                        List<Integer>,
                        OperationSettings,
                        FixedSizeCollectionField<List<Integer>, OperationSettings, ?, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ?, ?>,
                        FixedSizeCollectionFieldDeserializer<List<Integer>, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
                        WkSzInputBytestreamBase<?>,
                        OperationSettings,
                        FixedSizeCollectionField<List<Integer>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ?>,
                        FixedSizeCollectionFieldSerializer<List<Integer>, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
                        WkSzOutputBytestreamBase<?>,
                        FixedSizeCollectionField<List<Integer>, OperationSettings, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>>
                          FIXED_SIZE_INT_LIST;

  private static WkSzStruct<
                        List<Integer>,
                        VariableLengthSettings,
                        VariableSizeCollectionField<List<Integer>, VariableLengthSettings, ?, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ?, ?>,
                        VariableSizeCollectionFieldDeserializer<List<Integer>, VariableLengthSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>,
                        WkSzInputBytestreamBase<?>,
                        OperationSettings,
                        VariableSizeCollectionField<List<Integer>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ?>,
                        VariableSizeCollectionFieldSerializer<List<Integer>, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>,
                        WkSzOutputBytestreamBase<?>,
                        VariableSizeCollectionField<List<Integer>, VariableLengthSettings, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, WkSzSignedBigEndianInteger>>
                          VAR_SIZE_INT_LIST;


  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    FIXED_SIZE_INT_LIST = FixedSizeCollectionField.<
                              List<Integer>,
                              OperationSettings,
                              OperationSettings,
                              Integer,
                              OperationSettings,
                              WkSzSignedBigEndianInteger,
                              WkSzSignedBigEndianIntegerReader,
                              OperationSettings,
                              WkSzSignedBigEndianInteger,
                              WkSzSignedBigEndianIntegerWriter,
                              WkSzSignedBigEndianInteger>newPacketStructure(
                                  "FIXEDINTLIST",
                                  "INT",
                                  ORIGINAL_LIST.size(),
                                  (Class<List<Integer>>)(Class<?>)List.class,
                                  (l) -> l,
                                  OperationSettings::none,
                                  OperationSettings::none,
                                  WkSzSignedBigEndianInteger::newCore);

    VAR_SIZE_INT_LIST = VariableSizeCollectionField.<
                              List<Integer>,
                              VariableLengthSettings,
                              OperationSettings,
                              Integer,
                              OperationSettings,
                              WkSzSignedBigEndianInteger,
                              WkSzSignedBigEndianIntegerReader,
                              OperationSettings,
                              WkSzSignedBigEndianInteger,
                              WkSzSignedBigEndianIntegerWriter,
                              WkSzSignedBigEndianInteger>newPacketStructure(
                                "FIXEDINTLIST",
                                "INT",
                                0,
                                ORIGINAL_LIST.size() + 10,
                                (Class<List<Integer>>)(Class<?>)List.class,
                                WkSzSignedBigEndianInteger::newCore,
                                (i,ao) -> VariableLengthSettings.withLength(ORIGINAL_LIST.size()),
                                OperationSettings::none,
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

    WkSzOutputPacket<List<Integer>, FixedSizeCollectionField<List<Integer>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ?>, FixedSizeCollectionFieldSerializer<List<Integer>, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>>
      listWriting = FIXED_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST,
                                                        OperationSettings.EMPTY,
                                                        outputstream);
    logger.info(listWriting.toString() + " created");

    assertFalse(listWriting.isCompleted());
    assertEquals(0, listWriting.firstOperation().get().dashboard().nextElementIndex());

    while(listWriting.isInProgress()) {
      listWriting.processBytestream();
    }

    assertTrue(listWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, listWriting.firstOperation().get().serializable());

    WkSzInputPacket<List<Integer>, FixedSizeCollectionField<List<Integer>, OperationSettings, ?, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ?, ?>, FixedSizeCollectionFieldDeserializer<List<Integer>, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>>
      listReading = FIXED_SIZE_INT_LIST.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());

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

    WkSzOutputPacket<List<Integer>, VariableSizeCollectionField<List<Integer>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter, ?>, VariableSizeCollectionFieldSerializer<List<Integer>, OperationSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerWriter>>
      varlistWriting = VAR_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST, OperationSettings.EMPTY, outputstream);

    logger.info(varlistWriting.toString() + " created");

    assertFalse(varlistWriting.isCompleted());
    assertEquals(0, varlistWriting.firstOperation().get().dashboard().nextElementIndex());

    while(varlistWriting.isInProgress()) {
      varlistWriting.processBytestream();
    }

    assertTrue(varlistWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, varlistWriting.firstOperation().get().serializable());

    WkSzCountingInputBytestream inputbytestream = new WkSzCountingInputBytestream(outputstream.inputStream());

    WkSzInputPacket<List<Integer>, VariableSizeCollectionField<List<Integer>, VariableLengthSettings, ?, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<List<Integer>, VariableLengthSettings, Integer, OperationSettings, WkSzSignedBigEndianInteger, WkSzSignedBigEndianIntegerReader>>
      varListReading = VAR_SIZE_INT_LIST.newInputPacket(VariableLengthSettings.withLength(ORIGINAL_LIST.size()), inputbytestream);

    logger.info(varListReading + " created");

    while(varListReading.isInProgress()) {
      varListReading.processBytestream();
    }

    assertTrue(varListReading.isCompleted());

    assertEquals(ORIGINAL_LIST, varListReading.firstOperation().get().result().get().deserialized().get());
  }

}
