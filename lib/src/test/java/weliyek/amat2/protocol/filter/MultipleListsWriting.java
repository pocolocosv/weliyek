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
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
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
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortWriter;

public class MultipleListsWriting
        implements DynamicCollectionFieldSerializer<
                        MultipleLists,
                        OperationSettings,
                        SerializingRuntime<OutputBytestream>,
                        SerializingResult,
                        MultipleListInputField,
                        Short,
                        WkSzSignedBigEndianShortWriter,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        OperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldSerializer,
                        OperationSettings>
{

    final SimplifiedDynamicCollectionFieldSerializer<
                        MultipleLists,
                        OperationSettings,
                        MultipleListsWriting,
                        MultipleListInputField,
                        Short,
                        OperationSettings,
                        WkSzSignedBigEndianShortWriter,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        OperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldSerializer,
                        OperationSettings> operationCore;

  MultipleListsWriting(
    int index,
    MultipleLists serializable,
    OperationSettings settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<MultipleLists, ?, MultipleListInputField, ?, ?, ?>
      serializerpacketCore,
    SimplifiedDynamicCollectionDefinitionCore<MultipleLists, ?, ?, ?, OperationSettings, MultipleListsWriting, MultipleListInputField, Short, ?, ?, ?, OperationSettings, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, ?, PrimitivesGroupList, ?, ?, ?, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, ?, ?, OperationSettings, ?>
      definitionCore) {
    operationCore = new SimplifiedDynamicCollectionFieldSerializer<
                            MultipleLists, OperationSettings, MultipleListsWriting, MultipleListInputField, Short, OperationSettings, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, OperationSettings>(
                                index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
  }

  @Override
  public WkSzPacketWriterSubfield<Short, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketWriterSubfield<MultipleLists, VariableSizeCollectionField<MultipleLists, ?, OperationSettings, PrimitivesGroupList, ?, ?, ?, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, ?>, VariableSizeCollectionFieldSerializer<MultipleLists, OperationSettings, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public MultipleListInputField definition() {
    return this.operationCore.definition();
  }

  @Override
  public OperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public SerializingRuntime<OutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<SerializingResult> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketWriterField<MultipleLists, MultipleListInputField, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public MultipleLists serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSzPacketWriterSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
