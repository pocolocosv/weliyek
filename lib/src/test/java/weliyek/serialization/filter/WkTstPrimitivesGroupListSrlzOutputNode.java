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
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzStructNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzOutputNode;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldSerializer;

public class WkTstPrimitivesGroupListSrlzOutputNode
    implements WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSignedLittleEndianIntegerSrlzOutputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzOperationSettings>
{

    final WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSzOperationSettings,
                        WkSignedLittleEndianIntegerSrlzOutputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketWriter,
                        WkSzOperationSettings> writingCore;

    WkTstPrimitivesGroupListSrlzOutputNode(
      int index,
      WkSzTstPrimitivesGroupList serializable,
      WkSzOperationSettings settings,
      WkSzOutputBytestreamBase<?> parentBytestream,
      WkSrlzOutputPacketFieldFrameNodeCore<WkSzTstPrimitivesGroupList, ?, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?>
        serializerpacketCore,
      WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<WkSzTstPrimitivesGroupList, ?, ?, ?, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, ?, ?, ?, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, WkSzTstPrimitivesGroup, ?, ?, ?, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ?, ?, WkSzOperationSettings, ?>
        definitionCore) {
      writingCore = new WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzOutputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzOutputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, WkSzOperationSettings>(
            index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
    }

    @Override
    public
    WkSrlzOutputPacketSubfieldFrameNode<Integer, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzOutputNode>
    size() {
      return this.writingCore.size();
    }

    @Override
    public
    WkSrlzOutputPacketSubfieldFrameNode<WkSzTstPrimitivesGroupList, VariableSizeCollectionField<WkSzTstPrimitivesGroupList, ?, WkSzOperationSettings, WkSzTstPrimitivesGroup, ?, ?, ?, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter, ?>, VariableSizeCollectionFieldSerializer<WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketWriter>>
    variableSequence() {
      return this.writingCore.variableSequence();
    }

    @Override
    public WkTstPrimitivesGroupListSrlzStructNode definition() {
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
    public WkSrlzOutputPacketFieldFrameNode<WkSzTstPrimitivesGroupList, WkTstPrimitivesGroupListSrlzStructNode, ?> packetField() {
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
    public List<WkSrlzOutputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
      return this.writingCore.subfields();
    }

}
