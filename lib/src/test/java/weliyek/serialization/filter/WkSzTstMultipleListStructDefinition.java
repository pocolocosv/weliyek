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

import java.util.List;
import java.util.function.Predicate;

import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSzSignedBigEndianShort;
import weliyek.serialization.number.WkSzSignedBigEndianShortReader;
import weliyek.serialization.number.WkSzSignedBigEndianShortWriter;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.WkSzDynamicCollectionDefinition;
import weliyek.serialization.sequence.WkSzSimplifiedDynamicCollectionStructDefinition;

public class WkSzTstMultipleListStructDefinition
    implements WkSzDynamicCollectionDefinition<
                        WkSzTstMultipleLists,
                        WkSzTstMultipleListsPacketReader,
                        WkSzTstMultipleListsPacketWriter,
                        WkSzSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        WkSzTstPrimitivesGroupListPacketReader,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        WkSzTstPrimitivesGroupListPacketWriter,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        WkSzVariableLengthOperationSettings,
                        WkSzOperationSettings>
{

  public static WkSzStruct<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkSzTstMultipleListStructDefinition,
                        WkSzTstMultipleListsPacketReader,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkSzTstMultipleListStructDefinition,
                        WkSzTstMultipleListsPacketWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSzTstMultipleListStructDefinition>
  newPacketStructure() {
    return new WkSzStruct<>(
                        "MULTILIST",
                        (pc) -> new WkSzTstMultipleListStructDefinition(pc).definitionCore,
                        WkSzCountingInputBytestream::new,
                        WkSzCountingOutputBytestream::new);
  }

  private final WkSzSimplifiedDynamicCollectionStructDefinition<
                      WkSzTstMultipleLists,
                      WkSzOperationSettings,
                      WkSzTstMultipleListsPacketReader,
                      WkSzTstMultipleListStructDefinition,
                      WkSzOperationSettings,
                      WkSzTstMultipleListsPacketWriter,
                      WkSzTstMultipleListStructDefinition,
                      Short,
                      WkSzOperationSettings,
                      WkSzSignedBigEndianShortReader,
                      WkSzSignedBigEndianShort,
                      WkSzOperationSettings,
                      WkSzSignedBigEndianShortWriter,
                      WkSzSignedBigEndianShort,
                      WkSzSignedBigEndianShort,
                      WkSzTstPrimitivesGroupList,
                      WkSzOperationSettings,
                      WkSzTstPrimitivesGroupListStructDefinition,
                      WkSzTstPrimitivesGroupListPacketReader,
                      WkSzOperationSettings,
                      WkSzTstPrimitivesGroupListStructDefinition,
                      WkSzTstPrimitivesGroupListPacketWriter,
                      WkSzTstPrimitivesGroupListStructDefinition,
                      WkSzVariableLengthOperationSettings,
                      WkSzOperationSettings,
                      WkSzTstMultipleListStructDefinition> definitionCore;

  WkSzTstMultipleListStructDefinition(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
      definitionCore = new WkSzSimplifiedDynamicCollectionStructDefinition<
                              WkSzTstMultipleLists, WkSzOperationSettings, WkSzTstMultipleListsPacketReader, WkSzTstMultipleListStructDefinition, WkSzOperationSettings, WkSzTstMultipleListsPacketWriter, WkSzTstMultipleListStructDefinition, Short, WkSzOperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, WkSzOperationSettings, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, WkSzSignedBigEndianShort, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketReader, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketWriter, WkSzTstPrimitivesGroupListStructDefinition, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkSzTstMultipleListStructDefinition>(
                                  0, // minSize
                                  1024, // maxSize
                                  "SIZE", // sizeFieldLabel
                                  WkSzOperationSettings::none, //sizeDeserializerSettingsFactory,
                                  WkSzOperationSettings::none, //sizeSerializerSettingsFactory,
                                  (i) -> Short.valueOf((short)i), //sizeValueFactory,
                                  WkSzSignedBigEndianShort::newCore,
                                  "VARLIST", //collectionAndElementsFieldLabel,
                                  (i,axo) -> WkSzVariableLengthOperationSettings.withLength(
                                                  axo.size().field().get()
                                                  .firstOperation().get()
                                                  .result().get()
                                                  .deserialized().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                  WkSzOperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                  "PRIMITIVELIST", //elementFieldLabel,
                                  (pc) -> new WkSzTstPrimitivesGroupListStructDefinition(pc).fieldCore,
                                  WkSzOperationSettings::none, //elementDeserializerSettingsFactory,
                                  WkSzOperationSettings::none, //elementSerializerSettingsFactory,
                                  (i,xs,axb,xkc,xdc) -> new WkSzTstMultipleListsPacketReader(i,xs,axb,xkc,xdc).operationCore,
                                  (i,y,ys,ayb,ykc,ydc) -> new WkSzTstMultipleListsPacketWriter(i,y,ys,ayb,ykc,ydc).operationCore,
                                  WkSzTstMultipleLists::new,
                                  WkSzTstMultipleLists.class,
                                  componentCore,
                                  this);
  }

  @Override
  public WkSzStructSubcomponent<WkSzTstMultipleListsPacketReader, WkSzTstMultipleListsPacketWriter, WkSzSignedBigEndianShort>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSzStructSubcomponent<WkSzTstMultipleListsPacketReader, WkSzTstMultipleListsPacketWriter, VariableSizeCollectionField<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketReader, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition, WkSzTstPrimitivesGroupListPacketWriter, WkSzTstPrimitivesGroupListStructDefinition>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<WkSzTstMultipleLists> rxClass() {
    return WkSzTstMultipleLists.class;
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?,?>
  makeTester(Predicate<? super WkSzTstMultipleListsPacketReader> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
