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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkSerdeVariableSizeElementCollectionTest
{

  private static final Logger logger = LoggerFactory.getLogger(WkSerdeVariableSizeElementCollectionTest.class);

  public static final List<Integer> ORIGINAL_LIST = Arrays.asList(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
  public static final int LIST_SIZE = ORIGINAL_LIST.size();


  private static WkSerdeDtreeStruct<
                        List<Integer>,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeVariableSizeElementCollection<List<Integer>, WkSerdeDtreeOperationSettingsVariableLength, ?, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?, ?>,
                        WkSerdeVariableSizeElementCollectionReader<List<Integer>, WkSerdeDtreeOperationSettingsVariableLength, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeVariableSizeElementCollection<List<Integer>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ?>,
                        WkSerdeVariableSizeElementCollectionWriter<List<Integer>, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeVariableSizeElementCollection<List<Integer>, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
                          VAR_SIZE_INT_LIST;


  @SuppressWarnings("unchecked")
  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    VAR_SIZE_INT_LIST = WkSerdeVariableSizeElementCollection.<
                              List<Integer>,
                              WkSerdeDtreeOperationSettingsVariableLength,
                              WkSerdeDtreeOperationSettings,
                              Integer,
                              WkSerdeDtreeOperationSettings,
                              WkSerdeSignedBigEndianInteger,
                              WkSerdeSignedBigEndianIntegerReader,
                              WkSerdeDtreeOperationSettings,
                              WkSerdeSignedBigEndianInteger,
                              WkSerdeSignedBigEndianIntegerWriter,
                              WkSerdeSignedBigEndianInteger>newStruct(
                                "FIXEDINTLIST",
                                "INT",
                                0,
                                ORIGINAL_LIST.size() + 10,
                                (Class<List<Integer>>)(Class<?>)List.class,
                                WkSerdeSignedBigEndianInteger::newCore,
                                (i,ao) -> WkSerdeDtreeOperationSettingsVariableLength.withLength(ORIGINAL_LIST.size()),
                                WkSerdeDtreeOperationSettings::none,
                                (l) -> l);
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
  public void testVariableSizeCollectionField() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeWriter<List<Integer>, WkSerdeVariableSizeElementCollection<List<Integer>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ?>, WkSerdeVariableSizeElementCollectionWriter<List<Integer>, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>>
      varlistWriting = VAR_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST, WkSerdeDtreeOperationSettings.EMPTY, outputstream);

    logger.info(varlistWriting.toString() + " created");

    assertFalse(varlistWriting.isCompleted());
    assertEquals(0, varlistWriting.firstOperation().get().dashboard().nextElementIndex());

    while(varlistWriting.isInProgress()) {
      varlistWriting.processBytestream();
    }

    assertTrue(varlistWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, varlistWriting.firstOperation().get().serializable());

    WkSerdeDtreeBytestreamCountingInputStream inputbytestream = new WkSerdeDtreeBytestreamCountingInputStream(outputstream.inputStream());

    WkSerdeDtreeReader<List<Integer>, WkSerdeVariableSizeElementCollection<List<Integer>, WkSerdeDtreeOperationSettingsVariableLength, ?, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?, ?>, WkSerdeVariableSizeElementCollectionReader<List<Integer>, WkSerdeDtreeOperationSettingsVariableLength, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>>
      varListReading = VAR_SIZE_INT_LIST.newInputPacket(WkSerdeDtreeOperationSettingsVariableLength.withLength(ORIGINAL_LIST.size()), inputbytestream);

    logger.info(varListReading + " created");

    while(varListReading.isInProgress()) {
      varListReading.processBytestream();
    }

    assertTrue(varListReading.isCompleted());

    assertEquals(ORIGINAL_LIST, varListReading.firstOperation().get().result().get().serializable().get());
  }

}
