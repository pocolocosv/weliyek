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
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzOutputNode;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzStructDefinitionFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;

public class WkTstMultipleListSrlzStructNode
    implements WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        WkSzTstMultipleLists,
                        WkTstMultipleListSrlzInputNode,
                        WkTstMultipleListSrlzOutputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkSzVariableLengthOperationSettings,
                        WkSzOperationSettings>
{

  public static WkSrlzStructComponentFrameNodeRootCore<
                        WkSzTstMultipleLists,
                        WkSzOperationSettings,
                        WkTstMultipleListSrlzStructNode,
                        WkTstMultipleListSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkTstMultipleListSrlzStructNode,
                        WkTstMultipleListSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkTstMultipleListSrlzStructNode>
  newPacketStructure() {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                        "MULTILIST",
                        (pc) -> new WkTstMultipleListSrlzStructNode(pc).definitionCore,
                        WkSzCountingInputBytestream::new,
                        WkSzCountingOutputBytestream::new);
  }

  private final WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                      WkSzTstMultipleLists,
                      WkSzOperationSettings,
                      WkTstMultipleListSrlzInputNode,
                      WkTstMultipleListSrlzStructNode,
                      WkSzOperationSettings,
                      WkTstMultipleListSrlzOutputNode,
                      WkTstMultipleListSrlzStructNode,
                      Short,
                      WkSzOperationSettings,
                      WkSignedBigEndianShortSrlzInputNode,
                      WkSignedBigEndianShortSrlzStructNode,
                      WkSzOperationSettings,
                      WkSignedBigEndianShortSrlzOutputNode,
                      WkSignedBigEndianShortSrlzStructNode,
                      WkSignedBigEndianShortSrlzStructNode,
                      WkSzTstPrimitivesGroupList,
                      WkSzOperationSettings,
                      WkTstPrimitivesGroupListSrlzStructNode,
                      WkTstPrimitivesGroupListSrlzInputNode,
                      WkSzOperationSettings,
                      WkTstPrimitivesGroupListSrlzStructNode,
                      WkTstPrimitivesGroupListSrlzOutputNode,
                      WkTstPrimitivesGroupListSrlzStructNode,
                      WkSzVariableLengthOperationSettings,
                      WkSzOperationSettings,
                      WkTstMultipleListSrlzStructNode> definitionCore;

  WkTstMultipleListSrlzStructNode(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
      definitionCore = new WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                              WkSzTstMultipleLists, WkSzOperationSettings, WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzStructNode, WkSzOperationSettings, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, WkSzOperationSettings, WkSignedBigEndianShortSrlzInputNode, WkSignedBigEndianShortSrlzStructNode, WkSzOperationSettings, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzStructNode, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkTstMultipleListSrlzStructNode>(
                                  0, // minSize
                                  1024, // maxSize
                                  "SIZE", // sizeFieldLabel
                                  WkSzOperationSettings::none, //sizeDeserializerSettingsFactory,
                                  WkSzOperationSettings::none, //sizeSerializerSettingsFactory,
                                  (i) -> Short.valueOf((short)i), //sizeValueFactory,
                                  WkSignedBigEndianShortSrlzStructNode::newCore,
                                  "VARLIST", //collectionAndElementsFieldLabel,
                                  (i,axo) -> WkSzVariableLengthOperationSettings.withLength(
                                                  axo.size().field().get()
                                                  .firstOperation().get()
                                                  .result().get()
                                                  .deserialized().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                  WkSzOperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                  "PRIMITIVELIST", //elementFieldLabel,
                                  (pc) -> new WkTstPrimitivesGroupListSrlzStructNode(pc).fieldCore,
                                  WkSzOperationSettings::none, //elementDeserializerSettingsFactory,
                                  WkSzOperationSettings::none, //elementSerializerSettingsFactory,
                                  (i,xs,axb,xkc,xdc) -> new WkTstMultipleListSrlzInputNode(i,xs,axb,xkc,xdc).operationCore,
                                  (i,y,ys,ayb,ykc,ydc) -> new WkTstMultipleListSrlzOutputNode(i,y,ys,ayb,ykc,ydc).operationCore,
                                  WkSzTstMultipleLists::new,
                                  WkSzTstMultipleLists.class,
                                  componentCore,
                                  this);
  }

  @Override
  public WkSrlzStructSubcomponentFrameNode<WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode>
  size() {
    return this.definitionCore.size();
  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzOutputNode, VariableSizeCollectionField<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode>>
  variableSequence() {
    return this.definitionCore.variableSequence();
  }

  @Override
  public Class<WkSzTstMultipleLists> rxClass() {
    return WkSzTstMultipleLists.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?,?>
  makeTester(Predicate<? super WkTstMultipleListSrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
