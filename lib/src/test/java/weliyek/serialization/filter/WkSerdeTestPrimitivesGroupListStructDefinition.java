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
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.WkSerdeVariableSizeElementCollection;

public class WkSerdeTestPrimitivesGroupListStructDefinition
        implements WkSerdeDtreeDynamicCollectionDefinition<
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeTestPrimitivesGroupListMsgReader,
                        WkSerdeTestPrimitivesGroupListMsgWriter,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgWriter,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationSettings>
{

    final WkSerdeDtreeDynamicCollectionDefinitionCore<
                        WkSerdeTestPrimitivesGroupList,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListMsgReader,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListMsgWriter,
                        WkSerdeTestPrimitivesGroupListStructDefinition,
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeTestPrimitivesGroup,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeTestPrimitivesGroupMsgWriter,
                        WkSerdeTestPrimitivesGroupStructDefinition,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeTestPrimitivesGroupListStructDefinition> fieldCore;

    WkSerdeTestPrimitivesGroupListStructDefinition(WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore) {
        fieldCore = new WkSerdeDtreeDynamicCollectionDefinitionCore<
                            WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListMsgReader, WkSerdeTestPrimitivesGroupListStructDefinition, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListMsgWriter, WkSerdeTestPrimitivesGroupListStructDefinition, Integer, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianIntegerWriter, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianInteger, WkSerdeTestPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgReader, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgWriter, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupListStructDefinition>(
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
                                (pc) -> new WkSerdeTestPrimitivesGroupStructDefinition(pc).definitionCore,
                                WkSerdeDtreeOperationSettings::none, //elementDeserializerSettingsFactory,
                                WkSerdeDtreeOperationSettings::none, //elementSerializerSettingsFactory,
                                (i,xs,axb,xkc,xdc) -> new WkSerdeTestPrimitivesGroupListMsgReader(i,xs,axb,xkc,xdc).readingCore, //deserializerFactory,
                                (i,y,ys,ayb,ykc,ydc) -> new WkSerdeTestPrimitivesGroupListMsgWriter(i,y,ys,ayb,ykc,ydc).writingCore, //serializerFactory,
                                (l) -> new WkSerdeTestPrimitivesGroupList(l),
                                WkSerdeTestPrimitivesGroupList.class,
                                componentCore,
                                this);
    }

    @Override
    public WkSerdeDtreeStructSubfield<WkSerdeTestPrimitivesGroupListMsgReader, WkSerdeTestPrimitivesGroupListMsgWriter, WkSerdeSignedLittleEndianInteger>
    size() {
      return this.fieldCore.size();
    }

    @Override
    public
    WkSerdeDtreeStructSubfield<WkSerdeTestPrimitivesGroupListMsgReader, WkSerdeTestPrimitivesGroupListMsgWriter, WkSerdeVariableSizeElementCollection<WkSerdeTestPrimitivesGroupList, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroup, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgReader, WkSerdeDtreeOperationSettings, WkSerdeTestPrimitivesGroupStructDefinition, WkSerdeTestPrimitivesGroupMsgWriter, WkSerdeTestPrimitivesGroupStructDefinition>>
    variableSequence() {
      return this.fieldCore.variableSequence();
    }

    @Override
    public Class<WkSerdeTestPrimitivesGroupList> serializableClass() {
      return this.fieldCore.serializableClass();
    }

    @Override
    public List<WkSerdeDtreeStructField<?>> subfields() {
      return this.fieldCore.subfields();
    }

    @Override
    public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
      return this.fieldCore.requiredSubfields();
    }

}
