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
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.basic.aggregator.sequence.DynamicCollectionFieldDeserializer;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionDefinitionCore;
import weliyek.amat.basic.aggregator.sequence.SimplifiedDynamicCollectionFieldDeserializer;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionField;
import weliyek.amat.basic.aggregator.sequence.VariableSizeCollectionFieldDeserializer;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableLengthOperationSettings;
import weliyek.amat.basic.number.WkSzSignedBigEndianShort;
import weliyek.amat.basic.number.WkSzSignedBigEndianShortReader;

public class MultipleListReading
        implements DynamicCollectionFieldDeserializer<
                        MultipleLists,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<MultipleLists>,
                        MultipleListInputField,
                        Short,
                        WkSzSignedBigEndianShortReader,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldDeserializer,
                        WkSzVariableLengthOperationSettings>
{

    final SimplifiedDynamicCollectionFieldDeserializer<
                        MultipleLists,
                        WkSzOperationSettings,
                        MultipleListReading,
                        MultipleListInputField,
                        Short,
                        WkSzOperationSettings,
                        WkSzSignedBigEndianShortReader,
                        WkSzSignedBigEndianShort,
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        PrimitivesGroupListField,
                        PrimitivesGroupListFieldDeserializer,
                        WkSzVariableLengthOperationSettings>
                        operationCore;

  MultipleListReading(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<MultipleLists, ?, MultipleListInputField, ?, ?, ?> packetfieldCore,
    SimplifiedDynamicCollectionDefinitionCore<MultipleLists, WkSzOperationSettings, MultipleListReading, MultipleListInputField, ?, ?, ?, Short, WkSzOperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, ?, ?, ?, ?, PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?, ?>
      definitionCore) {
    this.operationCore = new SimplifiedDynamicCollectionFieldDeserializer<
                                MultipleLists, WkSzOperationSettings, MultipleListReading, MultipleListInputField, Short, WkSzOperationSettings, WkSzSignedBigEndianShortReader, WkSzSignedBigEndianShort, PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, WkSzVariableLengthOperationSettings>(
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
  WkSzPacketReaderSubfield<MultipleLists, VariableSizeCollectionField<MultipleLists, WkSzVariableLengthOperationSettings, ?, PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<MultipleLists, WkSzVariableLengthOperationSettings, PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListField, PrimitivesGroupListFieldDeserializer>>
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
  public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSzReadingResult<MultipleLists>> result() {
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
