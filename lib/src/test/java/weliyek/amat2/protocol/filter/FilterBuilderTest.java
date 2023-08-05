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
package weliyek.amat2.protocol.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Predicate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzStruct;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.WkSzInputPacket;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.WkSzOutputPacket;
import weliyek.amat.basic.number.WkSzSignedBigEndianInteger;
import weliyek.amat.basic.number.WkSzSignedBigEndianLong;
import weliyek.amat.basic.number.WkSzSignedByte;
import weliyek.ketza.util.KetzaByteOutputStream;

public class FilterBuilderTest
{

  private static Predicate<WkSzPacketReaderOperation<Byte,?,?,?,?>> BYTE_IS_EQUAL_TO_ONE    = (xo) -> xo.result().get().deserialized().get().byteValue() == (byte)1;
  private static Predicate<WkSzPacketReaderOperation<Byte,?,?,?,?>> BYTE_IS_NOTEQUAL_TO_ONE = (xo) -> xo.result().get().deserialized().get().byteValue() != (byte)1;
  private static Predicate<WkSzPacketReaderOperation<Byte,?,?,?,?>> BYTE_IS_EQUAL_TO_TWO    = (xo) -> xo.result().get().deserialized().get().byteValue() == (byte)2;

  private static Predicate<WkSzPacketReaderOperation<Integer,?,?,?,?>> INT_IS_EQUAL_TO_400 = (xo) -> xo.result().get().deserialized().get().intValue() == 400;

  private static WkSzStruct<
                      MultipleLists,
                      OperationSettings,
                      MultipleListInputField,
                      MultipleListReading,
                      InputBytestreamGeneralBase<?>,
                      OperationSettings,
                      MultipleListInputField,
                      MultipleListsWriting,
                      OutputBytestreamGeneralBase<?>,
                      MultipleListInputField> MULTIPLE_LIST_PACKET;
  private static PrimitivesGroupListField PRIMITIVELIST_FIELD;
  private static PrimitivesGroupField PRIMITIVEGROUP_FIELD;
  private static WkSzSignedByte BYTE_FIELD;
  private static WkSzSignedBigEndianInteger INT_FIELD;
  private static WkSzSignedBigEndianLong LONG_FIELD;

  private static FieldTester<?,?> PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE;
  private static FieldTester<?,?> PRIMITIVE_HAS_BYTE_EQUAL_TO_TWO;
  private static FieldTester<?,?> PRIMITIVE_HAS_BYTE_DIFFERENT_FROM_ONE;
  private static FieldTester<?,?> PRIMITIVE_HAS_INT_EQUAL_TO_400;
  private static FieldTester<?,?> MULTIPLE_LIST_SIZE_IS_EQUAL_TO_ONE;

  /*
  private static PrimitivesGroupListField GROUPS_INPUT = GROUPS_LIST_INPUT.element();
  private static BasicCollectionInputField<
                      Object,
                      PrimitivesGroupList,
                      PrimitivesGroup,
                      InputConfig,
                      PrimitivesGroupDeserializer,
                      PrimitivesGroupField> PRIMITIVEGROUP_LIST = GROUPS_INPUT.collection();
  private static PrimitivesGroupField PRIMITIVEGROUP_INPUT = PRIMITIVEGROUP_LIST.element();
  private static SignedIntegerLittleEndianInput<Object> PRIMITIVEGROUP_INT_INPUTFIELD = PRIMITIVEGROUP_INPUT.signedInt();
  private static SignedLongLittleEndianInput<Object> PRIMITIVEGROUP_LONG_INPUTFIELD = PRIMITIVEGROUP_INPUT.signedLong();
  */

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    MULTIPLE_LIST_PACKET = MultipleListInputField.newPacketStructure();
    PRIMITIVELIST_FIELD = MULTIPLE_LIST_PACKET.definition().variableSequence().field().definition().element().field().definition();
    PRIMITIVEGROUP_FIELD = PRIMITIVELIST_FIELD.variableSequence().field().definition().element().field().definition();
    BYTE_FIELD = PRIMITIVEGROUP_FIELD.byteSubcomponent.field().definition();
    INT_FIELD = PRIMITIVEGROUP_FIELD.intSubcomponent.field().definition();
    LONG_FIELD = PRIMITIVEGROUP_FIELD.longSubcomponent.field().definition();
    PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE = BYTE_FIELD.makeTester(BYTE_IS_EQUAL_TO_ONE, "IS_PRIMITIVE_BYTE_EQUAL_TO_ONE");
    PRIMITIVE_HAS_BYTE_EQUAL_TO_TWO = BYTE_FIELD.makeTester(BYTE_IS_EQUAL_TO_TWO, "IS_PRIMITIVE_BYTE_EQUAL_TO_TWO");
    PRIMITIVE_HAS_BYTE_DIFFERENT_FROM_ONE = BYTE_FIELD.makeTester(BYTE_IS_NOTEQUAL_TO_ONE, "IS_PRIMITIVE_BYTE_DIFFERENT_TO_ONE");

    PRIMITIVE_HAS_INT_EQUAL_TO_400 = INT_FIELD.makeTester(INT_IS_EQUAL_TO_400, "IS_PRIMITIVE_INT_EQUAL_FROM_400");

    MULTIPLE_LIST_SIZE_IS_EQUAL_TO_ONE = MULTIPLE_LIST_PACKET.definition().size().field().definition().makeTester(xo -> xo.result().get().deserialized().get().intValue() == 1,"IS_MULTIPLE_LIST_SIZE_EQUAL_TO_ONE");
  }

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test() {
      final FilterQueryBuilder queryBuilder = new FilterQueryBuilder();

      //queryBuilder.search(MultiplePrimitivesListsField.PROTOCOL.message().primitiveList());
      // Tested fields must be held by searched fields as descendants.
      //assertThrows(IllegalArgumentException.class, () -> queryBuilder.add(isListOfMultiplesSizeEqualsToOne));

      /*
      final FilterQuery selectMsgWithPrimitiveByteEqualToOne = queryBuilder.withName("SELECT_MSG_WITH_PRIMITIVE_BYTE_EQUAL_TO_ONE")
                                                                           .search(MultiplePrimitivesListsField.PROTOCOL.message())
                                                                           .whereAny(PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE)
                                                                           .build();
      assertNotNull(selectMsgWithPrimitiveByteEqualToOne);
      final FilterQuery selectMsgWithPrimitiveByteNotEqualToOne = queryBuilder.search(MultiplePrimitivesListsField.PROTOCOL.message())
                                                                              .whereAll(PRIMITIVE_HAS_BYTE_DIFFERENT_FROM_ONE)
                                                                              .build();
      final FilterQuery selectMsgWithPrimitiveByteEqualToOneOrTwo = queryBuilder.search(MultiplePrimitivesListsField.PROTOCOL.message())
                                                                                .whereAny(PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE)
                                                                                .or(PRIMITIVE_HAS_BYTE_EQUAL_TO_TWO)
                                                                                .build();
      */

      final FilterQuery selectListWithPrimitiveByteEqualToOne = queryBuilder.withDescription("SELECT_ALL_LISTS_WITH_PRIMITIVE_BYTE_EQUAL_TO_ONE")
                                                                            .search(PRIMITIVELIST_FIELD)
                                                                            .whereAny(PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE)
                                                                            .build();

      final FilterQuery selectAllListWithPrimitivesIntEqualTo400 = queryBuilder.withDescription("SELECT_ALL_LISTS_WITHOUT_INT_EQUAL_TO_400")
                                                                                     .search(PRIMITIVELIST_FIELD)
                                                                                     .whereAll(PRIMITIVE_HAS_INT_EQUAL_TO_400)
                                                                                     .build();

      final FilterBuilder filterBuilder = new FilterBuilder();
      Filter filter = filterBuilder.setProtocol(MULTIPLE_LIST_PACKET)
                                   //.addQuery(selectMsgWithPrimitiveByteEqualToOne)
                                   //.addQuery(selectMsgWithPrimitiveByteNotEqualToOne)
                                   //.addQuery(selectMsgWithPrimitiveByteEqualToOneOrTwo)
                                   .addQuery(selectListWithPrimitiveByteEqualToOne)
                                   .addQuery(selectAllListWithPrimitivesIntEqualTo400)
                                   .build();
      assertNotNull(filter);

      final PrimitivesGroup primitive00 = new PrimitivesGroup(0, "zero");
      final PrimitivesGroup primitive01 = new PrimitivesGroup(1, "one");
      final PrimitivesGroup primitive02 = new PrimitivesGroup(2, "two");
      final PrimitivesGroup primitive03 = new PrimitivesGroup(3, "three");
      final PrimitivesGroup primitive04 = new PrimitivesGroup(4, "four");
      final PrimitivesGroup primitive05 = new PrimitivesGroup(5, "five");
      final PrimitivesGroup primitive06 = new PrimitivesGroup(6, "six");
      final PrimitivesGroup primitive07 = new PrimitivesGroup(7, "seven");

      PrimitivesGroupList.Builder primitiveListBuilder = new PrimitivesGroupList.Builder();

      final PrimitivesGroupList listNone = primitiveListBuilder.build();
      final PrimitivesGroupList list00 = primitiveListBuilder.addPrimitives(primitive00)
                                                        .build();
      final PrimitivesGroupList list00_to_01 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .build();
      final PrimitivesGroupList list00_to_02 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .build();
      final PrimitivesGroupList list00_to_03 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .build();
      final PrimitivesGroupList list00_to_04 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .build();
      final PrimitivesGroupList list00_to_05 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .addPrimitives(primitive05)
                                                              .build();
      final PrimitivesGroupList list00_to_06 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .addPrimitives(primitive05)
                                                              .addPrimitives(primitive06)
                                                              .build();
      final PrimitivesGroupList list00_to_07 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .addPrimitives(primitive05)
                                                              .addPrimitives(primitive06)
                                                              .addPrimitives(primitive07)
                                                              .build();

      MultipleLists.Builder multiplePrimitiveListBuilder = new MultipleLists.Builder();

      final MultipleLists msgWrite = multiplePrimitiveListBuilder.addPrimitivesList(listNone)
                                                                 .addPrimitivesList(list00)
                                                                 .addPrimitivesList(list00_to_01)
                                                                 .addPrimitivesList(list00_to_02)
                                                                 .addPrimitivesList(list00_to_03)
                                                                 .addPrimitivesList(list00_to_04)
                                                                 .addPrimitivesList(list00_to_05)
                                                                 .addPrimitivesList(list00_to_06)
                                                                 .addPrimitivesList(list00_to_07)
                                                                 .build();

      assertEquals(9, msgWrite.size());
      assertEquals(0, msgWrite.get(0).size());
      assertEquals(1, msgWrite.get(1).size());
      assertEquals(2, msgWrite.get(2).size());
      assertEquals(3, msgWrite.get(3).size());
      assertEquals(4, msgWrite.get(4).size());
      assertEquals(5, msgWrite.get(5).size());
      assertEquals(6, msgWrite.get(6).size());
      assertEquals(7, msgWrite.get(7).size());
      assertEquals(8, msgWrite.get(8).size());

      KetzaByteOutputStream out = new KetzaByteOutputStream();

      WkSzOutputPacket<MultipleLists, MultipleListInputField, MultipleListsWriting>
        multiListsSerializer = MULTIPLE_LIST_PACKET.newOutputPacket(msgWrite, OperationSettings.EMPTY, out);

      while(multiListsSerializer.isInProgress()) {
        multiListsSerializer.processBytestream();
      }

      WkSzInputPacket<MultipleLists, MultipleListInputField, MultipleListReading>
        multiListsDeserializer = MULTIPLE_LIST_PACKET.newInputPacket(OperationSettings.EMPTY, out.inputStream(), filter);

      while(multiListsDeserializer.isInProgress()) {
        multiListsDeserializer.processBytestream();
      }

      if (multiListsDeserializer.firstOperation().get().result().get().deserialized().isPresent()) {
        final MultipleLists readRes = multiListsDeserializer.firstOperation().get().result().get().deserialized().get();

        assertNotNull(readRes);
        assertEquals(listNone, readRes.get(0));
        assertEquals(list00, readRes.get(1));
        //assertEquals(list00_to_01, readRes.get(2));
        assertEquals(list00_to_02, readRes.get(3));
        assertEquals(list00_to_03, readRes.get(4));
        assertEquals(list00_to_04, readRes.get(5));
        assertEquals(list00_to_05, readRes.get(6));
        assertEquals(list00_to_06, readRes.get(7));
        assertEquals(list00_to_07, readRes.get(8));
        assertEquals(msgWrite, readRes);
      }

      FilterQueryResults allListWithIntEqualTo400 = multiListsDeserializer.filterResults().allResultsForQuery(selectAllListWithPrimitivesIntEqualTo400);
      assertNotNull(allListWithIntEqualTo400);
      assertEquals(4, allListWithIntEqualTo400.results().size());

      FilterQueryResults allListsWithIntEqto1 = multiListsDeserializer.filterResults().allResultsForQuery(selectListWithPrimitiveByteEqualToOne);
      assertNotNull(allListsWithIntEqto1);
      assertEquals(7, allListsWithIntEqto1.results().size());
  }

}
