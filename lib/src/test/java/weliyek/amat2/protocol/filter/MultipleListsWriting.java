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

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.output.WkSzWritingResult;
import weliyek.amat.base.output.WkSzWritingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
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
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        MultipleListInputField,
                        Short,
                        WkSzSignedBigEndianShortWriter,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldSerializer,
                        WkSzOperationSettings>
{

    final SimplifiedDynamicCollectionFieldSerializer<
                        MultipleLists,
                        WkSzOperationSettings,
                        MultipleListsWriting,
                        MultipleListInputField,
                        Short,
                        WkSzOperationSettings,
                        WkSzSignedBigEndianShortWriter,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldSerializer,
                        WkSzOperationSettings> operationCore;

  MultipleListsWriting(
    int index,
    MultipleLists serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<MultipleLists, ?, MultipleListInputField, ?, ?, ?>
      serializerpacketCore,
    SimplifiedDynamicCollectionDefinitionCore<MultipleLists, ?, ?, ?, WkSzOperationSettings, MultipleListsWriting, MultipleListInputField, Short, ?, ?, ?, WkSzOperationSettings, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, ?, PrimitivesGroupList, ?, ?, ?, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, ?, ?, WkSzOperationSettings, ?>
      definitionCore) {
    operationCore = new SimplifiedDynamicCollectionFieldSerializer<
                            MultipleLists, WkSzOperationSettings, MultipleListsWriting, MultipleListInputField, Short, WkSzOperationSettings, WkSzSignedBigEndianShortWriter, WkSzSignedBigEndianShort, PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, WkSzOperationSettings>(
                                index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
  }

  @Override
  public WkSzPacketWriterSubfield<Short, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortWriter>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketWriterSubfield<MultipleLists, VariableSizeCollectionField<MultipleLists, ?, WkSzOperationSettings, PrimitivesGroupList, ?, ?, ?, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer, ?>, VariableSizeCollectionFieldSerializer<MultipleLists, WkSzOperationSettings, PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldSerializer>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public MultipleListInputField definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSzOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSzWritingRuntime<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzWritingResult> result() {
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
