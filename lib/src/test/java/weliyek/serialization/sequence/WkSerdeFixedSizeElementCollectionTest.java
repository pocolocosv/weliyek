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
package weliyek.serialization.sequence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

class WkSerdeFixedSizeElementCollectionTest
{

  private static final Logger logger = LoggerFactory.getLogger(WkSerdeFixedSizeElementCollectionTest.class);

  public static final List<Integer> ORIGINAL_LIST = Arrays.asList(Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3));
  public static final int LIST_SIZE = ORIGINAL_LIST.size();

  private static WkSerdeDtreeStruct<
                        List<Integer>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeFixedSizeElementCollection<
                          List<Integer>, WkSerdeDtreeOperationSettings, ?, Integer, WkSerdeDtreeOperationSettings,
                          WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?, ? extends WkSerdeSignedBigEndianInteger>,
                        WkSerdeFixedSizeElementCollectionReader<List<Integer>, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeFixedSizeElementCollection<
                          List<Integer>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?, WkSerdeDtreeOperationSettings,
                          WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                        WkSerdeFixedSizeElementCollectionWriter<List<Integer>, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeFixedSizeElementCollection<List<Integer>, WkSerdeDtreeOperationSettings, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, WkSerdeSignedBigEndianInteger>>
                          FIXED_SIZE_INT_LIST;

  @SuppressWarnings("unchecked")
  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    FIXED_SIZE_INT_LIST = WkSerdeFixedSizeElementCollection.<
                            List<Integer>,
                            WkSerdeDtreeOperationSettings,
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
                                ORIGINAL_LIST.size(),
                                (Class<List<Integer>>)(Class<?>)List.class,
                                (l) -> l,
                                WkSerdeDtreeOperationSettings::none,
                                WkSerdeDtreeOperationSettings::none,
                                WkSerdeSignedBigEndianInteger::newCore);
  }

  @Test
  public void testFixedSize() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

    WkSerdeDtreeWriter<
      List<Integer>,
      WkSerdeFixedSizeElementCollection<
        List<Integer>, ?, WkSerdeDtreeOperationSettings, Integer, ?, ?, ?,
        WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianInteger,
        WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
      WkSerdeFixedSizeElementCollectionWriter<
        List<Integer>, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings,
        WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>>
    listWriting = FIXED_SIZE_INT_LIST.newOutputPacket(ORIGINAL_LIST,
                                                        WkSerdeDtreeOperationSettings.EMPTY,
                                                        outputstream);
    logger.info(listWriting.toString() + " created");

    assertFalse(listWriting.isCompleted());
    assertEquals(0, listWriting.firstOperation().get().dashboard().nextElementIndex());

    while(listWriting.isInProgress()) {
      listWriting.processBytestream();
    }

    assertTrue(listWriting.isCompleted());

    assertEquals(ORIGINAL_LIST, listWriting.firstOperation().get().serializable());

    WkSerdeDtreeReader<
      List<Integer>,
      WkSerdeFixedSizeElementCollection<
        List<Integer>, WkSerdeDtreeOperationSettings, ?, Integer, WkSerdeDtreeOperationSettings,
        WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader, ?, ?, ?,
        ? extends WkSerdeSignedBigEndianInteger>,
      WkSerdeFixedSizeElementCollectionReader<
        List<Integer>, WkSerdeDtreeOperationSettings, Integer, WkSerdeDtreeOperationSettings,
        WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>>
    listReading = FIXED_SIZE_INT_LIST.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());

    logger.info(listReading + " created");

    while(listReading.isInProgress()) {
      listReading.processBytestream();
    }

    assertTrue(listReading.isCompleted());

    assertEquals(ORIGINAL_LIST, listReading.firstOperation().get().result().get().serializable().get());
  }

}
