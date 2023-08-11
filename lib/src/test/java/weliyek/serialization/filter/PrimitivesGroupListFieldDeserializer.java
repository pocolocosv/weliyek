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

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSzSignedLittleEndianInteger;
import weliyek.serialization.number.WkSzSignedLittleEndianIntegerReader;
import weliyek.serialization.sequence.DynamicCollectionFieldDeserializer;
import weliyek.serialization.sequence.SimplifiedDynamicCollectionDefinitionCore;
import weliyek.serialization.sequence.SimplifiedDynamicCollectionFieldDeserializer;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldDeserializer;

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
