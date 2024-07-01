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

public class WkSerdeTestMultipleListSerdeDef
    implements WkSerdeDtreeDynamicCollectionDefinition<
                        WkSerdeTestMultipleLists,
                        WkSerdeTestMultipleListSerdeReader,
                        WkSerdeTestMultipleListSerdeWriter,
                        WkSerdeSignedBigEndianShort,
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListSerdeDef,
                        WkSerdeTestPrimitivesGroupListSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListSerdeDef,
                        WkSerdeTestPrimitivesGroupListSerdeWriter,
                        WkSerdeTestPrimitivesGroupListSerdeDef,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationSettings>
{

  public static WkSerdeDtreeStruct<
                        WkSerdeTestMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestMultipleListSerdeDef,
                        WkSerdeTestMultipleListSerdeReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestMultipleListSerdeDef,
                        WkSerdeTestMultipleListSerdeWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeTestMultipleListSerdeDef>
  newStruct() {
    return new WkSerdeDtreeStructCore<>(
                        "MULTILIST",
                        (pc) -> new WkSerdeTestMultipleListSerdeDef(pc).definitionCore,
                        WkSerdeDtreeBytestreamCountingInputStream::new,
                        WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  private final WkSerdeDtreeDynamicCollectionDefinitionCore<
                      WkSerdeTestMultipleLists,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListSerdeReader,
                      WkSerdeTestMultipleListSerdeDef,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListSerdeWriter,
                      WkSerdeTestMultipleListSerdeDef,
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
                      WkSerdeTestPrimitivesGroupListSerdeDef,
                      WkSerdeTestPrimitivesGroupListSerdeReader,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestPrimitivesGroupListSerdeDef,
                      WkSerdeTestPrimitivesGroupListSerdeWriter,
                      WkSerdeTestPrimitivesGroupListSerdeDef,
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeTestMultipleListSerdeDef> definitionCore;

  WkSerdeTestMultipleListSerdeDef(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
      definitionCore = new WkSerdeDtreeDynamicCollectionDefinitionCore<
                              WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListSerdeReader, WkSerdeTestMultipleListSerdeDef, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListSerdeWriter, WkSerdeTestMultipleListSerdeDef, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortReader, WkSerdeSignedBigEndianShort, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShort, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeTestPrimitivesGroupListSerdeReader, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeTestPrimitivesGroupListSerdeWriter, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSerdeTestMultipleListSerdeDef>(
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
                                  (pc) -> new WkSerdeTestPrimitivesGroupListSerdeDef(pc).fieldCore,
                                  WkSerdeDtreeOperationSettings::none, //elementDeserializerSettingsFactory,
                                  WkSerdeDtreeOperationSettings::none, //elementSerializerSettingsFactory,
                                  (i,xkc,xdc) -> new WkSerdeTestMultipleListSerdeReader(i,xkc,xdc).operationCore,
                                  (i,ykc,ydc) -> new WkSerdeTestMultipleListSerdeWriter(i,ykc,ydc).operationCore,
                                  WkSerdeTestMultipleLists::new,
                                  WkSerdeTestMultipleLists.class,
                                  componentCore,
                                  this);
  }

  @Override
  public WkSerdeDtreeStructSubfield<WkSerdeTestMultipleListSerdeReader, WkSerdeTestMultipleListSerdeWriter, WkSerdeSignedBigEndianShort>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeTestMultipleListSerdeReader, WkSerdeTestMultipleListSerdeWriter, WkSerdeVariableSizeElementCollection<WkSerdeTestMultipleLists, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeTestPrimitivesGroupListSerdeReader, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListSerdeDef, WkSerdeTestPrimitivesGroupListSerdeWriter, WkSerdeTestPrimitivesGroupListSerdeDef>>
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
