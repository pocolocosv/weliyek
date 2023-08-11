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
import weliyek.amat.basic.number.WkSzSignedLittleEndianInteger;
import weliyek.amat.basic.number.WkSzSignedLittleEndianIntegerReader;

public class PrimitivesGroupListFieldDeserializer
    implements DynamicCollectionFieldDeserializer<
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<PrimitivesGroupList>,
                        PrimitivesGroupListField,
                        Integer,
                        WkSzSignedLittleEndianIntegerReader,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupDeserializer,
                        WkSzVariableLengthOperationSettings>
{

    final SimplifiedDynamicCollectionFieldDeserializer<
                        PrimitivesGroupList,
                        WkSzOperationSettings,
                        PrimitivesGroupListFieldDeserializer,
                        PrimitivesGroupListField,
                        Integer,
                        WkSzOperationSettings,
                        WkSzSignedLittleEndianIntegerReader,
                        WkSzSignedLittleEndianInteger,
                        PrimitivesGroup,
                        WkSzOperationSettings,
                        PrimitivesGroupField,
                        PrimitivesGroupDeserializer,
                        WkSzVariableLengthOperationSettings> readingCore;

    PrimitivesGroupListFieldDeserializer(
      int index,
      WkSzOperationSettings settings,
      WkSzInputBytestreamBase<?> parentBytestream,
      WkSzPacketReaderFieldCore<PrimitivesGroupList, ?, PrimitivesGroupListField, ?, ?, ?>
        packetfieldCore,
      SimplifiedDynamicCollectionDefinitionCore<PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListFieldDeserializer, PrimitivesGroupListField, ?, ?, ?, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, ?, ?, ?, ?, PrimitivesGroup, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?, ?>
        definitionCore) {
      readingCore = new SimplifiedDynamicCollectionFieldDeserializer<PrimitivesGroupList, WkSzOperationSettings, PrimitivesGroupListFieldDeserializer, PrimitivesGroupListField, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerReader, WkSzSignedLittleEndianInteger, PrimitivesGroup, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer, WkSzVariableLengthOperationSettings>(
          index, settings, parentBytestream, packetfieldCore, definitionCore, this);
    }

    @Override
    public
    WkSzPacketReaderSubfield<Integer, WkSzSignedLittleEndianInteger, WkSzSignedLittleEndianIntegerReader>
    size() {
      return this.readingCore.size();
    }

    @Override
    public
    WkSzPacketReaderSubfield<PrimitivesGroupList, VariableSizeCollectionField<PrimitivesGroupList, WkSzVariableLengthOperationSettings, ?, PrimitivesGroup, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<PrimitivesGroupList, WkSzVariableLengthOperationSettings, PrimitivesGroup, WkSzOperationSettings, PrimitivesGroupField, PrimitivesGroupDeserializer>>
    variableSequence() {
      return this.readingCore.variableSequence();
    }

    @Override
    public PrimitivesGroupListField definition() {
      return this.readingCore.definition();
    }

    @Override
    public WkSzOperationSettings settings() {
      return this.readingCore.settings();
    }

    @Override
    public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
      return this.readingCore.dashboard();
    }

    @Override
    public Optional<WkSzReadingResult<PrimitivesGroupList>> result() {
      return this.readingCore.result();
    }

    @Override
    public int index() {
      return this.readingCore.index();
    }

    @Override
    public WkSzPacketReaderField<PrimitivesGroupList, PrimitivesGroupListField, ?> packetField() {
      return this.readingCore.packetField();
    }

    @Override
    public String name() {
      return this.readingCore.name();
    }

    @Override
    public List<WkSzPacketReaderSubfield<?, ?, ?>> subfields() {
      return this.readingCore.subfields();
    }

}
