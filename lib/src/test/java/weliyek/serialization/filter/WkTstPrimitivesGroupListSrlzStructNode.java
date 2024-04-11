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
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;

public class WkTstPrimitivesGroupListSrlzStructNode
        implements WkSerdeDtreeDynamicCollectionDefinition<
                        WkSzTstPrimitivesGroupList,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationSettings>
{

    final WkSerdeDtreeDynamicCollectionDefinitionCore<
                        WkSzTstPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSerdeDtreeOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode> fieldCore;

    WkTstPrimitivesGroupListSrlzStructNode(WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
        fieldCore = new WkSerdeDtreeDynamicCollectionDefinitionCore<
                            WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianInteger, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkTstPrimitivesGroupListSrlzStructNode>(
                                0, //minSize,
                                1024, //maxSize,
                                "SIZE", //sizeFieldLabel,
                                WkSerdeDtreeOperationSettings::none, //sizeDeserializerSettingsFactory,
                                WkSerdeDtreeOperationSettings::none, //sizeSerializerSettingsFactory,
                                Integer::valueOf, //sizeValueFactory,
                                WkSerdeSignedLittleEndianInteger::newCore, //sizeDefinitionFactory,
                                "VARLIST", //collectionAndElementsFieldLabel,
                                (i,axo) -> WkSerdeDtreeOperationSettingsVariableLength.withLength(axo.size().get().firstOperation().get().result().get().serializable().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                WkSerdeDtreeOperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                "PRIMIVITESGROUP", //elementFieldLabel,
                                (pc) -> new WkSzTstPrimitivesGroupStructDefinition(pc).definitionCore,
                                WkSerdeDtreeOperationSettings::none, //elementDeserializerSettingsFactory,
                                WkSerdeDtreeOperationSettings::none, //elementSerializerSettingsFactory,
                                (i,xs,axb,xkc,xdc) -> new WkTstPrimitivesGroupListSrlzInputNode(i,xs,axb,xkc,xdc).readingCore, //deserializerFactory,
                                (i,y,ys,ayb,ykc,ydc) -> new WkTstPrimitivesGroupListSrlzOutputNode(i,y,ys,ayb,ykc,ydc).writingCore, //serializerFactory,
                                (l) -> new WkSzTstPrimitivesGroupList(l),
                                WkSzTstPrimitivesGroupList.class,
                                componentCore,
                                this);
    }

    @Override
    public WkSerdeDtreeNodeStructComponentHandler<WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSerdeSignedLittleEndianInteger>
    size() {
      return this.fieldCore.size();
    }

    @Override
    public
    WkSerdeDtreeNodeStructComponentHandler<WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSerdeVariableSizeElementCollection<WkSzTstPrimitivesGroupList, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSerdeDtreeOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition>>
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
