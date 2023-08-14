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

import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzInputNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzOutputNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzStructNode;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzStructDefinitionFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;

public class WkTstPrimitivesGroupListSrlzStructNode
        implements WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        WkSzTstPrimitivesGroupList,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzVariableLengthOperationSettings,
                        WkSzOperationSettings>
{

    final WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSzOperationSettings,
                        WkSignedLittleEndianIntegerSrlzInputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzOperationSettings,
                        WkSignedLittleEndianIntegerSrlzOutputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzVariableLengthOperationSettings,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzStructNode> fieldCore;

    WkTstPrimitivesGroupListSrlzStructNode(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
        fieldCore = new WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                            WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzStructNode>(
                                0, //minSize,
                                1024, //maxSize,
                                "SIZE", //sizeFieldLabel,
                                WkSzOperationSettings::none, //sizeDeserializerSettingsFactory,
                                WkSzOperationSettings::none, //sizeSerializerSettingsFactory,
                                Integer::valueOf, //sizeValueFactory,
                                WkSignedLittleEndianIntegerSrlzStructNode::newCore, //sizeDefinitionFactory,
                                "VARLIST", //collectionAndElementsFieldLabel,
                                (i,axo) -> WkSzVariableLengthOperationSettings.withLength(axo.size().field().get().firstOperation().get().result().get().deserialized().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                WkSzOperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                "PRIMIVITESGROUP", //elementFieldLabel,
                                (pc) -> new WkSzTstPrimitivesGroupStructDefinition(pc).definitionCore,
                                WkSzOperationSettings::none, //elementDeserializerSettingsFactory,
                                WkSzOperationSettings::none, //elementSerializerSettingsFactory,
                                (i,xs,axb,xkc,xdc) -> new WkTstPrimitivesGroupListSrlzInputNode(i,xs,axb,xkc,xdc).readingCore, //deserializerFactory,
                                (i,y,ys,ayb,ykc,ydc) -> new WkTstPrimitivesGroupListSrlzOutputNode(i,y,ys,ayb,ykc,ydc).writingCore, //serializerFactory,
                                (l) -> new WkSzTstPrimitivesGroupList(l),
                                WkSzTstPrimitivesGroupList.class,
                                componentCore,
                                this);
    }

    @Override
    public WkSrlzStructSubcomponentFrameNode<WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode>
    size() {
      return this.fieldCore.size();
    }

    @Override
    public
    WkSrlzStructSubcomponentFrameNode<WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzOutputNode, VariableSizeCollectionField<WkSzTstPrimitivesGroupList, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition>>
    variableSequence() {
      return this.fieldCore.variableSequence();
    }

    @Override
    public Class<WkSzTstPrimitivesGroupList> rxClass() {
      return this.fieldCore.rxClass();
    }

    @Override
    public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
      return this.fieldCore.subfields();
    }

    @Override
    public FieldTester<?,?>
    makeTester(Predicate<? super WkTstPrimitivesGroupListSrlzInputNode> test, String description) {
      return this.fieldCore.makeTester(test, description);
    }

    @Override
    public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
      return this.fieldCore.requiredSubfields();
    }

}
