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

import java.util.List;
import java.util.function.Predicate;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzStruct;
import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.WkSzCountingInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.WkSzCountingOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionDefinitionCore;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionField;
import weliyek.amat.basic.aggregator.sequence.WkSzDynamicCollectionDefinition;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortReader;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortWriter;

public class MultipleListInputField
    implements WkSzDynamicCollectionDefinition<
                        MultipleLists,
                        MultipleListReading,
                        MultipleListsWriting,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        OperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldDeserializer,
                        OperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldSerializer,
                        PrimitivesGroupListField,
                        VariableLengthSettings,
                        OperationSettings>
{

  public static WkSzStruct<
                        MultipleLists,
                        OperationSettings,
                        MultipleListInputField,
                        MultipleListReading,
                        WkSzInputBytestreamBase<?>,
                        OperationSettings,
                        MultipleListInputField,
                        MultipleListsWriting,
                        WkSzOutputBytestreamBase<?>,
                        MultipleListInputField>
  newPacketStructure() {
    return new WkSzStruct<>(
                        "MULTILIST",
                        (pc) -> new MultipleListInputField(pc).definitionCore,
                        WkSzCountingInputBytestream::new,
                        WkSzCountingOutputBytestream::new);
  }

  private final SimplifiedDynamicCollectionDefinitionCore<
                      MultipleLists,
                      OperationSettings,
                      MultipleListReading,
                      MultipleListInputField,
                      OperationSettings,
                      MultipleListsWriting,
                      MultipleListInputField,
                      Short,
                      OperationSettings,
                      WkSzSignedBigEndianShortReader,
                      WkSzSignedBigEndianShort,
                      OperationSettings,
                      WkSzSignedBigEndianShortWriter,
                      WkSzSignedBigEndianShort,
                      WkSzSignedBigEndianShort,
                      PrimitivesGroupList,
                      OperationSettings,
                      PrimitivesGroupListField,
                      PrimitivesGroupListFieldDeserializer,
                      OperationSettings,
                      PrimitivesGroupListField,
                      PrimitivesGroupListFieldSerializer,
                      PrimitivesGroupListField,
                      VariableLengthSettings,
                      OperationSettings,
                      MultipleListInputField> definitionCore;

  MultipleListInputField(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
      definitionCore = new SimplifiedDynamicCollectionDefinitionCore<
                              MultipleLists, OperationSettings, MultipleListReading, MultipleListInputField, OperationSettings, MultipleListsWriting, MultipleListInputField, Short, OperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, OperationSettings, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, WkSzSignedBigEndianShort, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, PrimitivesGroupListField, VariableLengthSettings, OperationSettings, MultipleListInputField>(
                                  0, // minSize
                                  1024, // maxSize
                                  "SIZE", // sizeFieldLabel
                                  OperationSettings::none, //sizeDeserializerSettingsFactory,
                                  OperationSettings::none, //sizeSerializerSettingsFactory,
                                  (i) -> Short.valueOf((short)i), //sizeValueFactory,
                                  WkSzSignedBigEndianShort::newCore,
                                  "VARLIST", //collectionAndElementsFieldLabel,
                                  (i,axo) -> VariableLengthSettings.withLength(
                                                  axo.size().field().get()
                                                  .firstOperation().get()
                                                  .result().get()
                                                  .deserialized().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                  OperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                  "PRIMITIVELIST", //elementFieldLabel,
                                  (pc) -> new PrimitivesGroupListField(pc).fieldCore,
                                  OperationSettings::none, //elementDeserializerSettingsFactory,
                                  OperationSettings::none, //elementSerializerSettingsFactory,
                                  (i,xs,axb,xkc,xdc) -> new MultipleListReading(i,xs,axb,xkc,xdc).operationCore,
                                  (i,y,ys,ayb,ykc,ydc) -> new MultipleListsWriting(i,y,ys,ayb,ykc,ydc).operationCore,
                                  MultipleLists::new,
                                  MultipleLists.class,
                                  componentCore,
                                  this);
  }

  @Override
  public WkSzStructSubcomponent<MultipleListReading, MultipleListsWriting, WkSzSignedBigEndianShort>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSzStructSubcomponent<MultipleListReading, MultipleListsWriting, VariableSizeCollectionField<MultipleLists, VariableLengthSettings, OperationSettings, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, PrimitivesGroupListField>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<MultipleLists> rxClass() {
    return MultipleLists.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?,?>
  makeTester(Predicate<? super MultipleListReading> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
