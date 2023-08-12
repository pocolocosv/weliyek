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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.filter.FieldTester;
import weliyek.serialization.number.WkSzSignedLittleEndianInteger;
import weliyek.serialization.number.WkSzSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSzSignedLittleEndianIntegerWriter;
import weliyek.serialization.sequence.WkSzSimplifiedDynamicCollectionStructDefinition;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.WkSzDynamicCollectionDefinition;

public class WkSzTstPrimitivesGroupListStructDefinition
        implements WkSzDynamicCollectionDefinition<
                        WkSzTstPrimitivesGroupList,
                        WkSzTstPrimitivesGroupListPacketReader,
                        WkSzTstPrimitivesGroupListPacketWriter,
                        WkSzSignedLittleEndianInteger,
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

    final WkSzSimplifiedDynamicCollectionStructDefinition<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupListPacketReader,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupListPacketWriter,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        Integer,
                        WkSzOperationSettings,
                        WkSzSignedLittleEndianIntegerReader,
                        WkSzSignedLittleEndianInteger,
                        WkSzOperationSettings,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        WkSzSignedLittleEndianInteger,
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
                        WkSzTstPrimitivesGroupListStructDefinition> fieldCore;

    WkSzTstPrimitivesGroupListStructDefinition(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
        fieldCore = new WkSzSimplifiedDynamicCollectionStructDefinition<
                            WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListPacketReader, WkSzTstPrimitivesGroupListStructDefinition, WkSzOperationSettings, WkSzTstPrimitivesGroupListPacketWriter, WkSzTstPrimitivesGroupListStructDefinition, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianInteger, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkSzTstPrimitivesGroupListStructDefinition>(
                                0, //minSize,
                                1024, //maxSize,
                                "SIZE", //sizeFieldLabel,
                                WkSzOperationSettings::none, //sizeDeserializerSettingsFactory,
                                WkSzOperationSettings::none, //sizeSerializerSettingsFactory,
                                Integer::valueOf, //sizeValueFactory,
                                WkSzSignedLittleEndianInteger::newCore, //sizeDefinitionFactory,
                                "VARLIST", //collectionAndElementsFieldLabel,
                                (i,axo) -> WkSzVariableLengthOperationSettings.withLength(axo.size().field().get().firstOperation().get().result().get().deserialized().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                WkSzOperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                "PRIMIVITESGROUP", //elementFieldLabel,
                                (pc) -> new WkSzTstPrimitivesGroupStructDefinition(pc).definitionCore,
                                WkSzOperationSettings::none, //elementDeserializerSettingsFactory,
                                WkSzOperationSettings::none, //elementSerializerSettingsFactory,
                                (i,xs,axb,xkc,xdc) -> new WkSzTstPrimitivesGroupListPacketReader(i,xs,axb,xkc,xdc).readingCore, //deserializerFactory,
                                (i,y,ys,ayb,ykc,ydc) -> new WkSzTstPrimitivesGroupListPacketWriter(i,y,ys,ayb,ykc,ydc).writingCore, //serializerFactory,
                                (l) -> new WkSzTstPrimitivesGroupList(l),
                                WkSzTstPrimitivesGroupList.class,
                                componentCore,
                                this);
    }

    @Override
    public WkSzStructSubcomponent<WkSzTstPrimitivesGroupListPacketReader, WkSzTstPrimitivesGroupListPacketWriter, WkSzSignedLittleEndianInteger>
    size() {
      return this.fieldCore.size();
    }

    @Override
    public
    WkSzStructSubcomponent<WkSzTstPrimitivesGroupListPacketReader, WkSzTstPrimitivesGroupListPacketWriter, VariableSizeCollectionField<WkSzTstPrimitivesGroupList, WkSzVariableLengthOperationSettings, WkSzOperationSettings, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzTstPrimitivesGroupStructDefinition>>
    variableSequence() {
      return this.fieldCore.variableSequence();
    }

    @Override
    public Class<WkSzTstPrimitivesGroupList> rxClass() {
      return this.fieldCore.rxClass();
    }

    @Override
    public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
      return this.fieldCore.subfields();
    }

    @Override
    public FieldTester<?,?>
    makeTester(Predicate<? super WkSzTstPrimitivesGroupListPacketReader> test, String description) {
      return this.fieldCore.makeTester(test, description);
    }

    @Override
    public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
      return this.fieldCore.requiredSubfields();
    }

}
