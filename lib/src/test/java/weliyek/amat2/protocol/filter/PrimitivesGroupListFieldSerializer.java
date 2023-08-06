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
import java.util.Optional;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.basic.aggregator.sequence.DynamicCollectionFieldSerializer;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionDefinitionCore;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionFieldSerializer;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionField;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionFieldSerializer;
import weliyek.amat.basic.number.WkSzSignedLittleEndianInteger;
import weliyek.amat.basic.number.WkSzSignedLittleEndianIntegerWriter;
import weliyek.serialization.bytestream.OutputBytestream;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public class PrimitivesGroupListFieldSerializer
    implements DynamicCollectionFieldSerializer<
                        PrimitivesGroupList,
                        OperationSettings,
                        SerializingRuntime<OutputBytestream>,
                        SerializingResult,
                        PrimitivesGroupListField,
                        Integer,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        OperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer,
                        OperationSettings>
{

    final SimplifiedDynamicCollectionFieldSerializer<
                        PrimitivesGroupList,
                        OperationSettings,
                        PrimitivesGroupListFieldSerializer,
                        PrimitivesGroupListField,
                        Integer,
                        OperationSettings,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        OperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupSerializer,
                        OperationSettings> writingCore;

    PrimitivesGroupListFieldSerializer(
      int index,
      PrimitivesGroupList serializable,
      OperationSettings settings,
      OutputBytestreamGeneralBase<?> parentBytestream,
      WkSzPacketWriterFieldCore<PrimitivesGroupList, ?, PrimitivesGroupListField, ?, ?, ?>
        serializerpacketCore,
      SimplifiedDynamicCollectionDefinitionCore<PrimitivesGroupList, ?, ?, ?, OperationSettings, PrimitivesGroupListFieldSerializer, PrimitivesGroupListField, Integer, ?, ?, ?, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, PrimitivesGroup, ?, ?, ?, OperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, ?, ?, OperationSettings, ?>
        definitionCore) {
      writingCore = new SimplifiedDynamicCollectionFieldSerializer<PrimitivesGroupList, OperationSettings, PrimitivesGroupListFieldSerializer, PrimitivesGroupListField, Integer, OperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, PrimitivesGroup, OperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, OperationSettings>(
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
    WkSzPacketWriterSubfield<PrimitivesGroupList, VariableSizeCollectionField<PrimitivesGroupList, ?, OperationSettings, PrimitivesGroup, ?, ?, ?, OperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer, ?>, VariableSizeCollectionFieldSerializer<PrimitivesGroupList, OperationSettings, PrimitivesGroup, OperationSettings, PrimitivesGroupField, PrimitivesGroupSerializer>>
    variableSequence() {
      return this.writingCore.variableSequence();
    }

    @Override
    public PrimitivesGroupListField definition() {
      return this.writingCore.definition();
    }

    @Override
    public OperationSettings settings() {
      return this.writingCore.settings();
    }

    @Override
    public SerializingRuntime<OutputBytestream> dashboard() {
      return this.writingCore.dashboard();
    }

    @Override
    public Optional<SerializingResult> result() {
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
