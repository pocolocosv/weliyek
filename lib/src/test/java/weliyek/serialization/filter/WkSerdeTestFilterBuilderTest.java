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
package weliyek.serialization.filter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.function.Predicate;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianLong;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedByte;
import weliyek.serialization.number.WkSerdeSignedByteReader;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkSerdeTestFilterBuilderTest
{

  private static Predicate<WkSerdeDtreeMsgReader<Byte,?,?,?,?>> BYTE_IS_EQUAL_TO_ONE    = (xo) -> xo.result().get().serializable().get().byteValue() == (byte)1;
  private static Predicate<WkSerdeDtreeMsgReader<Byte,?,?,?,?>> BYTE_IS_NOTEQUAL_TO_ONE = (xo) -> xo.result().get().serializable().get().byteValue() != (byte)1;
  private static Predicate<WkSerdeDtreeMsgReader<Byte,?,?,?,?>> BYTE_IS_EQUAL_TO_TWO    = (xo) -> xo.result().get().serializable().get().byteValue() == (byte)2;

  private static Predicate<WkSerdeDtreeMsgReader<Integer,?,?,?,?>> INT_IS_EQUAL_TO_400 = (xo) -> xo.result().get().serializable().get().intValue() == 400;

  private static WkSerdeDtreeStruct<
                      WkSerdeTestMultipleLists,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListStructDefinition,
                      WkSerdeTestMultipleListMsgReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListStructDefinition,
                      WkSerdeTestMultipleListMsgWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeTestMultipleListStructDefinition> MULTIPLE_LIST_PACKET;
  static WkSerdeTestPrimitivesGroupListStructDefinition PRIMITIVELIST_FIELD;
  static WkSerdeTestPrimitivesGroupStructDefinition PRIMITIVEGROUP_FIELD;
  static WkSerdeSignedByte BYTE_FIELD;
  static WkSerdeSignedBigEndianInteger INT_FIELD;
  static WkSerdeSignedBigEndianLong LONG_FIELD;

  static WkSrlzPacketNodePredicate<?,?> PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE;
  static WkSrlzPacketNodePredicate<?,?> PRIMITIVE_HAS_BYTE_EQUAL_TO_TWO;
  static WkSrlzPacketNodePredicate<?,?> PRIMITIVE_HAS_BYTE_DIFFERENT_FROM_ONE;
  static WkSrlzPacketNodePredicate<?,?> PRIMITIVE_HAS_INT_EQUAL_TO_400;
  static WkSrlzPacketNodePredicate<?,?> MULTIPLE_LIST_SIZE_IS_EQUAL_TO_ONE;

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
    MULTIPLE_LIST_PACKET = WkSerdeTestMultipleListStructDefinition.newStruct();
    PRIMITIVELIST_FIELD = MULTIPLE_LIST_PACKET.definition().variableSequence().definition().elements().definition();
    PRIMITIVEGROUP_FIELD = PRIMITIVELIST_FIELD.variableSequence().definition().elements().definition();
    BYTE_FIELD = PRIMITIVEGROUP_FIELD.byteSubcomponent.definition();
    INT_FIELD = PRIMITIVEGROUP_FIELD.intSubcomponent.definition();
    LONG_FIELD = PRIMITIVEGROUP_FIELD.longSubcomponent.definition();
    PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE = new WkSrlzReadingPacketNodePredicate<WkSerdeSignedByte, WkSerdeSignedByteReader>(BYTE_FIELD, BYTE_IS_EQUAL_TO_ONE, "IS_PRIMITIVE_BYTE_EQUAL_TO_ONE");
    PRIMITIVE_HAS_BYTE_EQUAL_TO_TWO = new WkSrlzReadingPacketNodePredicate<WkSerdeSignedByte, WkSerdeSignedByteReader>(BYTE_FIELD, BYTE_IS_EQUAL_TO_TWO, "IS_PRIMITIVE_BYTE_EQUAL_TO_TWO");
    PRIMITIVE_HAS_BYTE_DIFFERENT_FROM_ONE = new WkSrlzReadingPacketNodePredicate<WkSerdeSignedByte, WkSerdeSignedByteReader>(BYTE_FIELD, BYTE_IS_NOTEQUAL_TO_ONE, "IS_PRIMITIVE_BYTE_DIFFERENT_TO_ONE");

    PRIMITIVE_HAS_INT_EQUAL_TO_400 = new WkSrlzReadingPacketNodePredicate<WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>(INT_FIELD, INT_IS_EQUAL_TO_400, "IS_PRIMITIVE_INT_EQUAL_FROM_400");

    MULTIPLE_LIST_SIZE_IS_EQUAL_TO_ONE = new WkSrlzReadingPacketNodePredicate<WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShortReader>(MULTIPLE_LIST_PACKET.definition().size().definition(), xo -> xo.result().get().serializable().get().intValue() == 1,"IS_MULTIPLE_LIST_SIZE_EQUAL_TO_ONE");
  }

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test() {
      final WkSrlzFilterQueryBuilder queryBuilder = new WkSrlzFilterQueryBuilder();

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

      final WkSrlzFilterQuery selectListWithPrimitiveByteEqualToOne = queryBuilder.withDescription("SELECT_ALL_LISTS_WITH_PRIMITIVE_BYTE_EQUAL_TO_ONE")
                                                                            .search(PRIMITIVELIST_FIELD)
                                                                            .whereAny(PRIMITIVE_HAS_BYTE_EQUAL_TO_ONE)
                                                                            .build();

      final WkSrlzFilterQuery selectAllListWithPrimitivesIntEqualTo400 = queryBuilder.withDescription("SELECT_ALL_LISTS_WITHOUT_INT_EQUAL_TO_400")
                                                                                     .search(PRIMITIVELIST_FIELD)
                                                                                     .whereAll(PRIMITIVE_HAS_INT_EQUAL_TO_400)
                                                                                     .build();

      final WkSrlzFilterBuilder filterBuilder = new WkSrlzFilterBuilder();
      WkSrlzFilter filter = filterBuilder.setProtocol(MULTIPLE_LIST_PACKET)
                                   //.addQuery(selectMsgWithPrimitiveByteEqualToOne)
                                   //.addQuery(selectMsgWithPrimitiveByteNotEqualToOne)
                                   //.addQuery(selectMsgWithPrimitiveByteEqualToOneOrTwo)
                                   .addQuery(selectListWithPrimitiveByteEqualToOne)
                                   .addQuery(selectAllListWithPrimitivesIntEqualTo400)
                                   .build();
      assertNotNull(filter);

      final WkSerdeTestPrimitivesGroup primitive00 = new WkSerdeTestPrimitivesGroup(0, "zero");
      final WkSerdeTestPrimitivesGroup primitive01 = new WkSerdeTestPrimitivesGroup(1, "one");
      final WkSerdeTestPrimitivesGroup primitive02 = new WkSerdeTestPrimitivesGroup(2, "two");
      final WkSerdeTestPrimitivesGroup primitive03 = new WkSerdeTestPrimitivesGroup(3, "three");
      final WkSerdeTestPrimitivesGroup primitive04 = new WkSerdeTestPrimitivesGroup(4, "four");
      final WkSerdeTestPrimitivesGroup primitive05 = new WkSerdeTestPrimitivesGroup(5, "five");
      final WkSerdeTestPrimitivesGroup primitive06 = new WkSerdeTestPrimitivesGroup(6, "six");
      final WkSerdeTestPrimitivesGroup primitive07 = new WkSerdeTestPrimitivesGroup(7, "seven");

      WkSerdeTestPrimitivesGroupList.Builder primitiveListBuilder = new WkSerdeTestPrimitivesGroupList.Builder();

      final WkSerdeTestPrimitivesGroupList listNone = primitiveListBuilder.build();
      final WkSerdeTestPrimitivesGroupList list00 = primitiveListBuilder.addPrimitives(primitive00)
                                                        .build();
      final WkSerdeTestPrimitivesGroupList list00_to_01 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .build();
      final WkSerdeTestPrimitivesGroupList list00_to_02 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .build();
      final WkSerdeTestPrimitivesGroupList list00_to_03 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .build();
      final WkSerdeTestPrimitivesGroupList list00_to_04 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .build();
      final WkSerdeTestPrimitivesGroupList list00_to_05 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .addPrimitives(primitive05)
                                                              .build();
      final WkSerdeTestPrimitivesGroupList list00_to_06 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .addPrimitives(primitive05)
                                                              .addPrimitives(primitive06)
                                                              .build();
      final WkSerdeTestPrimitivesGroupList list00_to_07 = primitiveListBuilder.addPrimitives(primitive00)
                                                              .addPrimitives(primitive01)
                                                              .addPrimitives(primitive02)
                                                              .addPrimitives(primitive03)
                                                              .addPrimitives(primitive04)
                                                              .addPrimitives(primitive05)
                                                              .addPrimitives(primitive06)
                                                              .addPrimitives(primitive07)
                                                              .build();

      WkSerdeTestMultipleLists.Builder multiplePrimitiveListBuilder = new WkSerdeTestMultipleLists.Builder();

      final WkSerdeTestMultipleLists msgWrite = multiplePrimitiveListBuilder.addPrimitivesList(listNone)
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

      WkSerdeDtreeWriter<WkSerdeTestMultipleLists, WkSerdeTestMultipleListStructDefinition, WkSerdeTestMultipleListMsgWriter>
        multiListsSerializer = MULTIPLE_LIST_PACKET.newOutputPacket(msgWrite, WkSerdeDtreeOperationSettings.EMPTY, out);

      while(multiListsSerializer.isInProgress()) {
        multiListsSerializer.processBytestream();
      }

      WkSerdeDtreeReader<WkSerdeTestMultipleLists, WkSerdeTestMultipleListStructDefinition, WkSerdeTestMultipleListMsgReader>
        multiListsDeserializer = MULTIPLE_LIST_PACKET.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, out.inputStream(), filter);

      while(multiListsDeserializer.isInProgress()) {
        multiListsDeserializer.processBytestream();
      }

      if (multiListsDeserializer.firstOperation().get().result().get().serializable().isPresent()) {
        final WkSerdeTestMultipleLists readRes = multiListsDeserializer.firstOperation().get().result().get().serializable().get();

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

      WkSrlzFilterQueryResults allListWithIntEqualTo400 = multiListsDeserializer.filterResults().allResultsForQuery(selectAllListWithPrimitivesIntEqualTo400);
      assertNotNull(allListWithIntEqualTo400);
      assertEquals(4, allListWithIntEqualTo400.results().size());

      WkSrlzFilterQueryResults allListsWithIntEqto1 = multiListsDeserializer.filterResults().allResultsForQuery(selectListWithPrimitiveByteEqualToOne);
      assertNotNull(allListsWithIntEqto1);
      assertEquals(7, allListsWithIntEqto1.results().size());
  }

}
