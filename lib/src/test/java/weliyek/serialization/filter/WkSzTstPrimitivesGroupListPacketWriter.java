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
import weliyek.serialization.sequence.WkSzDynamicCollectionPacketWriterOperation;
import weliyek.serialization.sequence.WkSzSimplifiedDynamicCollectionStructDefinition;
import weliyek.serialization.sequence.WkSzSimplifiedDynamicSequencePacketWriterCore;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldSerializer;

public class WkSzTstPrimitivesGroupListPacketWriter
    implements WkSzDynamicCollectionPacketWriterOperation<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        Integer,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzOperationSettings>
{

    final WkSzSimplifiedDynamicSequencePacketWriterCore<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupListPacketWriter,
                        WkSzTstPrimitivesGroupListStructDefinition,
                        Integer,
                        WkSzOperationSettings,
                        WkSzSignedLittleEndianIntegerWriter,
                        WkSzSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzOperationSettings> writingCore;

    WkSzTstPrimitivesGroupListPacketWriter(
      int index,
      WkSzTstPrimitivesGroupList serializable,
      WkSzOperationSettings settings,
      WkSzOutputBytestreamBase<?> parentBytestream,
      WkSzPacketWriterFieldCore<WkSzTstPrimitivesGroupList, ?, WkSzTstPrimitivesGroupListStructDefinition, ?, ?, ?>
        serializerpacketCore,
      WkSzSimplifiedDynamicCollectionStructDefinition<WkSzTstPrimitivesGroupList, ?, ?, ?, WkSzOperationSettings, WkSzTstPrimitivesGroupListPacketWriter, WkSzTstPrimitivesGroupListStructDefinition, Integer, ?, ?, ?, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, ?, WkSzTstPrimitivesGroup, ?, ?, ?, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ?, ?, WkSzOperationSettings, ?>
        definitionCore) {
      writingCore = new WkSzSimplifiedDynamicSequencePacketWriterCore<WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroupListPacketWriter, WkSzTstPrimitivesGroupListStructDefinition, Integer, WkSzOperationSettings, WkSzSignedLittleEndianIntegerWriter, WkSzSignedLittleEndianInteger, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzOperationSettings>(
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
    WkSzPacketWriterSubfield<WkSzTstPrimitivesGroupList, VariableSizeCollectionField<WkSzTstPrimitivesGroupList, ?, WkSzOperationSettings, WkSzTstPrimitivesGroup, ?, ?, ?, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ?>, VariableSizeCollectionFieldSerializer<WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter>>
    variableSequence() {
      return this.writingCore.variableSequence();
    }

    @Override
    public WkSzTstPrimitivesGroupListStructDefinition definition() {
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
    public WkSzPacketWriterField<WkSzTstPrimitivesGroupList, WkSzTstPrimitivesGroupListStructDefinition, ?> packetField() {
      return this.writingCore.packetField();
    }

    @Override
    public String name() {
      return this.writingCore.name();
    }

    @Override
    public WkSzTstPrimitivesGroupList serializable() {
      return this.writingCore.serializable();
    }

    @Override
    public List<WkSzPacketWriterSubfield<?, ?, ?>> subfields() {
      return this.writingCore.subfields();
    }

}
