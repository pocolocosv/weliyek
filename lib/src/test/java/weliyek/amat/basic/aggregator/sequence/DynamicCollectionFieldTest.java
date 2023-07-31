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

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.PacketStructure;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.WkSzInputPacket;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.WkSzOutputPacket;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortWriter;
import weliyek.amat.basic.number.WkSzSignedLittleEndianInteger;
import weliyek.amat.basic.number.WkSzSignedLittleEndianIntegerReader;
import weliyek.amat.basic.number.WkSzSignedLittleEndianIntegerWriter;
import weliyek.ketza.util.KetzaByteOutputStream;

public class DynamicCollectionFieldTest
{

  public static final List<Short> ORIGINAL_LIST = Arrays.asList(Short.valueOf((short) 0),
                                                                Short.valueOf((short) 1),
                                                                Short.valueOf((short) 2),
                                                                Short.valueOf((short) 3));
  private static PacketStructure<
                          List<Short>,
                          OperationSettings,
                          DynamicCollectionField<List<Short>, OperationSettings, ?, Integer, OperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, VariableLengthSettings, ?>,
                          DynamicCollectionFieldDeserializer<List<Short>, OperationSettings, DeserializingRuntime<InputBytestream>, DeserializingResult<List<Short>>, DynamicCollectionField<List<Short>, OperationSettings, ?, Integer, OperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, VariableLengthSettings, ?>, Integer, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, VariableLengthSettings>,
                          InputBytestreamGeneralBase<?>,
                          OperationSettings,
                          DynamicCollectionField<List<Short>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, OperationSettings>,
                          DynamicCollectionFieldSerializer< List<Short>, OperationSettings, SerializingRuntime<OutputBytestream>, SerializingResult, DynamicCollectionField<List<Short>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, OperationSettings>, Integer, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, OperationSettings>,
                          OutputBytestreamGeneralBase<?>,
                          DynamicCollectionField<List<Short>, OperationSettings, OperationSettings, Integer, OperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianInteger, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, VariableLengthSettings, OperationSettings>>
                              DYN_LIST_PACKET;

  @SuppressWarnings("unchecked")
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    DYN_LIST_PACKET = DynamicCollectionField.<
                          List<Short>,
                          OperationSettings,
                          OperationSettings,
                          Integer,
                          OperationSettings,
                          WkSzSignedLittleEndianIntegerReader,
                          WkSzSignedLittleEndianInteger,
                          OperationSettings,
                          WkSzSignedLittleEndianIntegerWriter,
                          WkSzSignedLittleEndianInteger,
                          WkSzSignedLittleEndianInteger,
                          Short,
                          OperationSettings,
                          WkSzSignedBigEndianShort,
                          WkSzSignedBigEndianShortReader,
                          OperationSettings,
                          WkSzSignedBigEndianShort,
                          WkSzSignedBigEndianShortWriter,
                          WkSzSignedBigEndianShort,
                          VariableLengthSettings,
                          OperationSettings>newPacketStructure(
                              "DYN_SHORT_LIST",
                              0,
                              ORIGINAL_LIST.size()+1,
                              "SIZE",
                              OperationSettings::none,
                              OperationSettings::none,
                              Integer::valueOf,
                              WkSzSignedLittleEndianInteger::newCore,
                              "SHORT_LIST",
                              (i,yo) -> VariableLengthSettings.withLength(yo.size().field().get().firstOperation().get().result().get().deserialized().get().intValue()),
                              OperationSettings::none,
                              "SHORT",
                              WkSzSignedBigEndianShort::newCore,
                              OperationSettings::none,
                              OperationSettings::none,
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
    WkSzOutputPacket<List<Short>, DynamicCollectionField<List<Short>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, OperationSettings>, DynamicCollectionFieldSerializer<List<Short>, OperationSettings, SerializingRuntime<OutputBytestream>, SerializingResult, DynamicCollectionField<List<Short>, ?, OperationSettings, Integer, ?, ?, ?, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, Short, ?, ?, ?, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, ?, ?, OperationSettings>, Integer, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter, OperationSettings>>
      dynlistSerializer = DYN_LIST_PACKET.newOutputPacket(ORIGINAL_LIST, OperationSettings.EMPTY, outputstream);

    while(dynlistSerializer.isInProgress()) {
      dynlistSerializer.processBytestream();
    }

    WkSzInputPacket<List<Short>, DynamicCollectionField<List<Short>, OperationSettings, ?, Integer, OperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, VariableLengthSettings, ?>, DynamicCollectionFieldDeserializer<List<Short>, OperationSettings, DeserializingRuntime<InputBytestream>, DeserializingResult<List<Short>>, DynamicCollectionField<List<Short>, OperationSettings, ?, Integer, OperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, ?, ?, ?, ?, VariableLengthSettings, ?>, Integer, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, Short, OperationSettings, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader, VariableLengthSettings>>
      dynlistDeserializer = DYN_LIST_PACKET.newInputPacket(OperationSettings.EMPTY, outputstream.inputStream());

    while(dynlistDeserializer.isInProgress()) {
      dynlistDeserializer.processBytestream();
    }

    assertEquals(ORIGINAL_LIST, dynlistDeserializer.firstOperation().get().result().get().deserialized().get());
  }

}
