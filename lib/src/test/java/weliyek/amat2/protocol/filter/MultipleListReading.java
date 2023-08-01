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
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.basic.aggregator.sequence.DynamicCollectionFieldDeserializer;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionDefinitionCore;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionFieldDeserializer;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionField;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionFieldDeserializer;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortReader;

public class MultipleListReading
        implements DynamicCollectionFieldDeserializer<
                        MultipleLists,
                        OperationSettings,
                        DeserializingRuntime<InputBytestream>,
                        DeserializingResult<MultipleLists>,
                        MultipleListInputField,
                        Short,
                        WkSzSignedBigEndianShortReader,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        OperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldDeserializer,
                        VariableLengthSettings>
{

    final SimplifiedDynamicCollectionFieldDeserializer<
                        MultipleLists,
                        OperationSettings,
                        MultipleListReading,
                        MultipleListInputField,
                        Short,
                        OperationSettings,
                        WkSzSignedBigEndianShortReader,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        OperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldDeserializer,
                        VariableLengthSettings>
                        operationCore;

  MultipleListReading(
    int index,
    OperationSettings settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<MultipleLists, ?, MultipleListInputField, ?, ?, ?> packetfieldCore,
    SimplifiedDynamicCollectionDefinitionCore<MultipleLists, OperationSettings, MultipleListReading, MultipleListInputField, ?, ?, ?, Short, OperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, ?, ?, ?, ?, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, ?, ?, ?, ?, VariableLengthSettings, ?, ?>
      definitionCore) {
    this.operationCore = new SimplifiedDynamicCollectionFieldDeserializer<
                                MultipleLists, OperationSettings, MultipleListReading, MultipleListInputField, Short, OperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, VariableLengthSettings>(
                                    index, settings, parentBytestream, packetfieldCore, definitionCore, this);
  }

  @Override
  public
  WkSzPacketReaderSubfield<Short, WkSzSignedBigEndianShort, WkSzSignedBigEndianShortReader>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSzPacketReaderSubfield<MultipleLists, VariableSizeCollectionField<MultipleLists, VariableLengthSettings, ?, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<MultipleLists, VariableLengthSettings, PrimitivesGroupList, OperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer>>
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
  public DeserializingRuntime<InputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<DeserializingResult<MultipleLists>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSzPacketReaderField<MultipleLists, MultipleListInputField, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
