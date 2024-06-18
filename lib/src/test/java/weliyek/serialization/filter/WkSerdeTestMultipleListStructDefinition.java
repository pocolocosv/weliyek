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

import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;

public class WkSerdeTestMultipleListStructDefinition
    implements WkSerdeDtreeDynamicCollectionDefinition<
                        WkSerdeTestMultipleLists,
                        WkSerdeTestMultipleListMsgReader,
                        WkSerdeTestMultipleListMsgWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        WkSerdeTestPrimitivesGroupListMsgReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        WkSerdeTestPrimitivesGroupListMsgWriter,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationSettings>
{

  public static WkSerdeDtreeStruct<
                        WkSerdeTestMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestMultipleListStructDefinition,
                        WkSerdeTestMultipleListMsgReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestMultipleListStructDefinition,
                        WkSerdeTestMultipleListMsgWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeTestMultipleListStructDefinition>
  newStruct() {
    return new WkSerdeDtreeStructCore<>(
                        "MULTILIST",
                        (pc) -> new WkSerdeTestMultipleListStructDefinition(pc).definitionCore,
                        WkSerdeDtreeBytestreamCountingInputStream::new,
                        WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  private final WkSerdeDtreeDynamicCollectionDefinitionCore<
                      WkSerdeTestMultipleLists,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListMsgReader,
                      WkSerdeTestMultipleListStructDefinition,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListMsgWriter,
                      WkSerdeTestMultipleListStructDefinition,
                      Short,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedBigEndianShortReader,
                      WkSerdeSignedBigEndianShort,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedBigEndianShortWriter,
                      WkSerdeSignedBigEndianShort,
                      WkSerdeSignedBigEndianShort,
                      WkSerdeTestPrimitivesGroupList,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestPrimitivesGroupListStructDefinition,
                      WkSerdeTestPrimitivesGroupListMsgReader,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestPrimitivesGroupListStructDefinition,
                      WkSerdeTestPrimitivesGroupListMsgWriter,
                      WkSerdeTestPrimitivesGroupListStructDefinition,
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListStructDefinition> definitionCore;

  WkSerdeTestMultipleListStructDefinition(WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore) {
      definitionCore = new WkSerdeDtreeDynamicCollectionDefinitionCore<
                              WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListMsgReader, WkSerdeTestMultipleListStructDefinition, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListMsgWriter, WkSerdeTestMultipleListStructDefinition, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortReader, WkSerdeSignedBigEndianShort, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShort, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgReader, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgWriter, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListStructDefinition>(
                                  0, // minSize
                                  1024, // maxSize
                                  "SIZE", // sizeFieldLabel
                                  WkSerdeDtreeOperationSettings::none, //sizeDeserializerSettingsFactory,
                                  WkSerdeDtreeOperationSettings::none, //sizeSerializerSettingsFactory,
                                  (i) -> Short.valueOf((short)i), //sizeValueFactory,
                                  WkSerdeSignedBigEndianShort::newCore,
                                  "VARLIST", //collectionAndElementsFieldLabel,
                                  (i,axo) -> WkSerdeDtreeOperationSettingsVariableLength.withLength(
                                                  axo.size().get()
                                                  .firstOperation().get()
                                                  .result().get()
                                                  .serializable().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                  WkSerdeDtreeOperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                  "PRIMITIVELIST", //elementFieldLabel,
                                  (pc) -> new WkSerdeTestPrimitivesGroupListStructDefinition(pc).fieldCore,
                                  WkSerdeDtreeOperationSettings::none, //elementDeserializerSettingsFactory,
                                  WkSerdeDtreeOperationSettings::none, //elementSerializerSettingsFactory,
                                  (i,xs,axb,xkc,xdc) -> new WkSerdeTestMultipleListMsgReader(i,xs,axb,xkc,xdc).operationCore,
                                  (i,y,ys,ayb,ykc,ydc) -> new WkSerdeTestMultipleListMsgWriter(i,y,ys,ayb,ykc,ydc).operationCore,
                                  WkSerdeTestMultipleLists::new,
                                  WkSerdeTestMultipleLists.class,
                                  componentCore,
                                  this);
  }

  @Override
  public WkSerdeDtreeStructSubfield<WkSerdeTestMultipleListMsgReader, WkSerdeTestMultipleListMsgWriter, WkSerdeSignedBigEndianShort>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeTestMultipleListMsgReader, WkSerdeTestMultipleListMsgWriter, WkSerdeVariableSizeElementCollection<WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgReader, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeTestPrimitivesGroupListMsgWriter, WkSerdeTestPrimitivesGroupListStructDefinition>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<WkSerdeTestMultipleLists> serializableClass() {
    return WkSerdeTestMultipleLists.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
