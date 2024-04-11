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

import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCoreRoot;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianShortWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianShort;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;

public class WkTstMultipleListSrlzStructNode
    implements WkSerdeDtreeDynamicCollectionDefinition<
                        WkSzTstMultipleLists,
                        WkTstMultipleListSrlzInputNode,
                        WkTstMultipleListSrlzOutputNode,
                        WkSerdeSignedBigEndianShort,
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationSettings>
{

  public static WkSerdeDtreeStruct<
                        WkSzTstMultipleLists,
                        WkSerdeDtreeOperationSettings,
                        WkTstMultipleListSrlzStructNode,
                        WkTstMultipleListSrlzInputNode,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkTstMultipleListSrlzStructNode,
                        WkTstMultipleListSrlzOutputNode,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkTstMultipleListSrlzStructNode>
  newStruct() {
    return new WkSerdeDtreeNodeStructComponentCoreRoot<>(
                        "MULTILIST",
                        (pc) -> new WkTstMultipleListSrlzStructNode(pc).definitionCore,
                        WkSerdeDtreeBytestreamCountingInputStream::new,
                        WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  private final WkSerdeDtreeDynamicCollectionDefinitionCore<
                      WkSzTstMultipleLists,
                      WkSerdeDtreeOperationSettings,
                      WkTstMultipleListSrlzInputNode,
                      WkTstMultipleListSrlzStructNode,
                      WkSerdeDtreeOperationSettings,
                      WkTstMultipleListSrlzOutputNode,
                      WkTstMultipleListSrlzStructNode,
                      Short,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedBigEndianShortReader,
                      WkSerdeSignedBigEndianShort,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedBigEndianShortWriter,
                      WkSerdeSignedBigEndianShort,
                      WkSerdeSignedBigEndianShort,
                      WkSzTstPrimitivesGroupList,
                      WkSerdeDtreeOperationSettings,
                      WkTstPrimitivesGroupListSrlzStructNode,
                      WkTstPrimitivesGroupListSrlzInputNode,
                      WkSerdeDtreeOperationSettings,
                      WkTstPrimitivesGroupListSrlzStructNode,
                      WkTstPrimitivesGroupListSrlzOutputNode,
                      WkTstPrimitivesGroupListSrlzStructNode,
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeDtreeOperationSettings,
                      WkTstMultipleListSrlzStructNode> definitionCore;

  WkTstMultipleListSrlzStructNode(WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
      definitionCore = new WkSerdeDtreeDynamicCollectionDefinitionCore<
                              WkSzTstMultipleLists, WkSerdeDtreeOperationSettings, WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzStructNode, WkSerdeDtreeOperationSettings, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortReader, WkSerdeSignedBigEndianShort, WkSerdeDtreeOperationSettings, WkSerdeSignedBigEndianShortWriter, WkSerdeSignedBigEndianShort, WkSerdeSignedBigEndianShort, WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkTstMultipleListSrlzStructNode>(
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
                                  (pc) -> new WkTstPrimitivesGroupListSrlzStructNode(pc).fieldCore,
                                  WkSerdeDtreeOperationSettings::none, //elementDeserializerSettingsFactory,
                                  WkSerdeDtreeOperationSettings::none, //elementSerializerSettingsFactory,
                                  (i,xs,axb,xkc,xdc) -> new WkTstMultipleListSrlzInputNode(i,xs,axb,xkc,xdc).operationCore,
                                  (i,y,ys,ayb,ykc,ydc) -> new WkTstMultipleListSrlzOutputNode(i,y,ys,ayb,ykc,ydc).operationCore,
                                  WkSzTstMultipleLists::new,
                                  WkSzTstMultipleLists.class,
                                  componentCore,
                                  this);
  }

  @Override
  public WkSerdeDtreeNodeStructComponentHandler<WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzOutputNode, WkSerdeSignedBigEndianShort>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSerdeDtreeNodeStructComponentHandler<WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzOutputNode, WkSerdeVariableSizeElementCollection<WkSzTstMultipleLists, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<WkSzTstMultipleLists> serializableClass() {
    return WkSzTstMultipleLists.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
