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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzInputNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzOutputNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzStructNode;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzStructDefinitionFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkVariableSizeCollectionSrlzStructNode;

public class WkTstPrimitivesGroupListSrlzStructNode
        implements WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        WkSzTstPrimitivesGroupList,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzVariableLengthOperationSettings,
                        WkSettingsSrlzPacketOperationData>
{

    final WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedLittleEndianIntegerSrlzInputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedLittleEndianIntegerSrlzOutputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzVariableLengthOperationSettings,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzStructNode> fieldCore;

    WkTstPrimitivesGroupListSrlzStructNode(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
        fieldCore = new WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                            WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSzTstPrimitivesGroup, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition, WkSzVariableLengthOperationSettings, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode>(
                                0, //minSize,
                                1024, //maxSize,
                                "SIZE", //sizeFieldLabel,
                                WkSettingsSrlzPacketOperationData::none, //sizeDeserializerSettingsFactory,
                                WkSettingsSrlzPacketOperationData::none, //sizeSerializerSettingsFactory,
                                Integer::valueOf, //sizeValueFactory,
                                WkSignedLittleEndianIntegerSrlzStructNode::newCore, //sizeDefinitionFactory,
                                "VARLIST", //collectionAndElementsFieldLabel,
                                (i,axo) -> WkSzVariableLengthOperationSettings.withLength(axo.size().field().get().firstOperation().get().result().get().serializable().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                WkSettingsSrlzPacketOperationData::none, //collectionAndElementsSerializerSettingsFactory,
                                "PRIMIVITESGROUP", //elementFieldLabel,
                                (pc) -> new WkSzTstPrimitivesGroupStructDefinition(pc).definitionCore,
                                WkSettingsSrlzPacketOperationData::none, //elementDeserializerSettingsFactory,
                                WkSettingsSrlzPacketOperationData::none, //elementSerializerSettingsFactory,
                                (i,xs,axb,xkc,xdc) -> new WkTstPrimitivesGroupListSrlzInputNode(i,xs,axb,xkc,xdc).readingCore, //deserializerFactory,
                                (i,y,ys,ayb,ykc,ydc) -> new WkTstPrimitivesGroupListSrlzOutputNode(i,y,ys,ayb,ykc,ydc).writingCore, //serializerFactory,
                                (l) -> new WkSzTstPrimitivesGroupList(l),
                                WkSzTstPrimitivesGroupList.class,
                                componentCore,
                                this);
    }

    @Override
    public WkSerdeDtreeNodeStructComponentHandler<WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode>
    size() {
      return this.fieldCore.size();
    }

    @Override
    public
    WkSerdeDtreeNodeStructComponentHandler<WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzOutputNode, WkVariableSizeCollectionSrlzStructNode<WkSzTstPrimitivesGroupList, WkSzVariableLengthOperationSettings, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroup, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition>>
    variableSequence() {
      return this.fieldCore.variableSequence();
    }

    @Override
    public Class<WkSzTstPrimitivesGroupList> serializableClass() {
      return this.fieldCore.serializableClass();
    }

    @Override
    public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
      return this.fieldCore.subfields();
    }

    @Override
    public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
      return this.fieldCore.requiredSubfields();
    }

}
