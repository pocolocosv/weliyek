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
import java.util.Optional;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSzSignedLittleEndianInteger;
import weliyek.serialization.number.WkSzSignedLittleEndianIntegerWriter;
import weliyek.serialization.sequence.DynamicCollectionFieldSerializer;
import weliyek.serialization.sequence.SimplifiedDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.SimplifiedDynamicCollectionFieldSerializer;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldSerializer;

public class PrimitivesGroupListFieldSerializer
    implements DynamicCollectionFieldSerializer<
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        PrimitivesGroupListField,
                        Integer,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer,
                        WkSzOperationSettings>
{

    final SimplifiedDynamicCollectionFieldSerializer<
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        PrimitivesGroupListFieldSerializer,
                        PrimitivesGroupListField,
                        Integer,
                        WkSzOperationSettings,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer,
                        WkSzOperationSettings> writingCore;

    PrimitivesGroupListFieldSerializer(
      int index,
      PrimitivesGroupList serializable,
      WkSzOperationSettings settings,
      WkSzOutputBytestreamBase<?> parentBytestream,
      WkSzPacketWriterFieldCore<PrimitivesGroupList, ?, PrimitivesGroupListField, ?, ?, ?>
        serializerpacketCore,
      SimplifiedDynamicCollectionDefinitionCore<PrimitivesGroupList, ?, ?, ?, WkSzOperationSettings, PrimitivesGroupListFieldSerializer, PrimitivesGroupListField, Integer, ?, ?, ?, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, PrimitivesGroup, ?, ?, ?, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, ?, ?, WkSzOperationSettings, ?>
        definitionCore) {
      writingCore = new SimplifiedDynamicCollectionFieldSerializer<PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListFieldSerializer, PrimitivesGroupListField, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, PrimitivesGroup, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, WkSzOperationSettings>(
            index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
    }

    @Override
    public
    WkSzPacketWriterSubfield<Integer, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianIntegerWriter>
    size() {
      return this.writingCore.size();
    }

    @Override
    public
    WkSzPacketWriterSubfield<PrimitivesGroupList, VariableSizeCollectionField<PrimitivesGroupList, ?, WkSzOperationSettings, PrimitivesGroup, ?, ?, ?, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, ?>, VariableSizeCollectionFieldSerializer<PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroup, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer>>
    variableSequence() {
      return this.writingCore.variableSequence();
    }

    @Override
    public PrimitivesGroupListField definition() {
      return this.writingCore.definition();
    }

    @Override
    public WkSzOperationSettings settings() {
      return this.writingCore.settings();
    }

    @Override
    public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
      return this.writingCore.dashboard();
    }

    @Override
    public Optional<WkSzWritingResult> result() {
      return this.writingCore.result();
    }

    @Override
    public int index() {
      return this.writingCore.index();
    }

    @Override
    public WkSzPacketWriterField<PrimitivesGroupList, PrimitivesGroupListField, ?> packetField() {
      return this.writingCore.packetField();
    }

    @Override
    public String name() {
      return this.writingCore.name();
    }

    @Override
    public PrimitivesGroupList serializable() {
      return this.writingCore.serializable();
    }

    @Override
    public List<WkSzPacketWriterSubfield<?, ?, ?>> subfields() {
      return this.writingCore.subfields();
    }

}
