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
package weliyek.amat2.protocol.filter;

import java.util.List;
import java.util.function.Predicate;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionDefinitionCore;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionField;
import weliyek.amat.basic.aggregator.sequence.WkSzDynamicCollectionDefinition;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzSignedLittleEndianInteger;
import weliyek.amat.basic.number.WkSzSignedLittleEndianIntegerReader;
import weliyek.amat.basic.number.WkSzSignedLittleEndianIntegerWriter;
import weliyek.serialization.WkSzStructSubcomponent;

public class PrimitivesGroupListField
        implements WkSzDynamicCollectionDefinition<
                        PrimitivesGroupList,
                        PrimitivesGroupListFieldDeserializer,
                        PrimitivesGroupListFieldSerializer,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        OperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupDeserializer,
                        OperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer,
                        PrimitivesGroupField,
                        VariableLengthSettings,
                        OperationSettings>
{

    final SimplifiedDynamicCollectionDefinitionCore<
                        PrimitivesGroupList,
                        OperationSettings,
                        PrimitivesGroupListFieldDeserializer,
                        PrimitivesGroupListField,
                        OperationSettings,
                        PrimitivesGroupListFieldSerializer,
                        PrimitivesGroupListField,
                        Integer,
                        OperationSettings,
                        WkSzSignedLittleEndianIntegerReader,
                        WkSzSignedLittleEndianInteger,
                        OperationSettings,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        OperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupDeserializer,
                        OperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer,
                        PrimitivesGroupField,
                        VariableLengthSettings,
                        OperationSettings,
                        PrimitivesGroupListField> fieldCore;

    PrimitivesGroupListField(WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore) {
        fieldCore = new SimplifiedDynamicCollectionDefinitionCore<
                            PrimitivesGroupList, OperationSettings, PrimitivesGroupListFieldDeserializer, PrimitivesGroupListField, OperationSettings, PrimitivesGroupListFieldSerializer, PrimitivesGroupListField, Integer, OperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianInteger, PrimitivesGroup, OperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer, OperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, PrimitivesGroupField, VariableLengthSettings, OperationSettings, PrimitivesGroupListField>(
                                0, //minSize,
                                1024, //maxSize,
                                "SIZE", //sizeFieldLabel,
                                OperationSettings::none, //sizeDeserializerSettingsFactory,
                                OperationSettings::none, //sizeSerializerSettingsFactory,
                                Integer::valueOf, //sizeValueFactory,
                                WkSzSignedLittleEndianInteger::newCore, //sizeDefinitionFactory,
                                "VARLIST", //collectionAndElementsFieldLabel,
                                (i,axo) -> VariableLengthSettings.withLength(axo.size().field().get().firstOperation().get().result().get().deserialized().get().intValue()), //collectionAndElementsDeserializerSettingsFactory,
                                OperationSettings::none, //collectionAndElementsSerializerSettingsFactory,
                                "PRIMIVITESGROUP", //elementFieldLabel,
                                (pc) -> new PrimitivesGroupField(pc).definitionCore,
                                OperationSettings::none, //elementDeserializerSettingsFactory,
                                OperationSettings::none, //elementSerializerSettingsFactory,
                                (i,xs,axb,xkc,xdc) -> new PrimitivesGroupListFieldDeserializer(i,xs,axb,xkc,xdc).readingCore, //deserializerFactory,
                                (i,y,ys,ayb,ykc,ydc) -> new PrimitivesGroupListFieldSerializer(i,y,ys,ayb,ykc,ydc).writingCore, //serializerFactory,
                                (l) -> new PrimitivesGroupList(l),
                                PrimitivesGroupList.class,
                                componentCore,
                                this);
    }

    @Override
    public WkSzStructSubcomponent<PrimitivesGroupListFieldDeserializer, PrimitivesGroupListFieldSerializer, WkSzSignedLittleEndianInteger>
    size() {
      return this.fieldCore.size();
    }

    @Override
    public
    WkSzStructSubcomponent<PrimitivesGroupListFieldDeserializer, PrimitivesGroupListFieldSerializer, VariableSizeCollectionField<PrimitivesGroupList, VariableLengthSettings, OperationSettings, PrimitivesGroup, OperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer, OperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, PrimitivesGroupField>>
    variableSequence() {
      return this.fieldCore.variableSequence();
    }

    @Override
    public Class<PrimitivesGroupList> rxClass() {
      return this.fieldCore.rxClass();
    }

    @Override
    public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
      return this.fieldCore.subfields();
    }

    @Override
    public FieldTester<?,?>
    makeTester(Predicate<? super PrimitivesGroupListFieldDeserializer> test, String description) {
      return this.fieldCore.makeTester(test, description);
    }

    @Override
    public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
      return this.fieldCore.requiredSubfields();
    }

}
