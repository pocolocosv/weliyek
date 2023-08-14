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
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzStructNode;
import weliyek.serialization.number.WkSignedLittleEndianIntegerSrlzInputNode;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldDeserializer;

public class WkTstPrimitivesGroupListSrlzInputNode
    implements WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<WkSzTstPrimitivesGroupList>,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSignedLittleEndianIntegerSrlzInputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSzVariableLengthOperationSettings>
{

    final WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                        WkSzTstPrimitivesGroupList,
                        WkSzOperationSettings,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSzOperationSettings,
                        WkSignedLittleEndianIntegerSrlzInputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSzTstPrimitivesGroup,
                        WkSzOperationSettings,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSzVariableLengthOperationSettings> readingCore;

    WkTstPrimitivesGroupListSrlzInputNode(
      int index,
      WkSzOperationSettings settings,
      WkSzInputBytestreamBase<?> parentBytestream,
      WkSrlzInputPacketFieldFrameNodeCore<WkSzTstPrimitivesGroupList, ?, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?>
        packetfieldCore,
      WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, ?, ?, ?, ?, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?, ?>
        definitionCore) {
      readingCore = new WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<WkSzTstPrimitivesGroupList, WkSzOperationSettings, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSzOperationSettings, WkSignedLittleEndianIntegerSrlzInputNode, WkSignedLittleEndianIntegerSrlzStructNode, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSzVariableLengthOperationSettings>(
          index, settings, parentBytestream, packetfieldCore, definitionCore, this);
    }

    @Override
    public
    WkSrlzInputPacketSubfieldFrameNode<Integer, WkSignedLittleEndianIntegerSrlzStructNode, WkSignedLittleEndianIntegerSrlzInputNode>
    size() {
      return this.readingCore.size();
    }

    @Override
    public
    WkSrlzInputPacketSubfieldFrameNode<WkSzTstPrimitivesGroupList, VariableSizeCollectionField<WkSzTstPrimitivesGroupList, WkSzVariableLengthOperationSettings, ?, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, ?>, VariableSizeCollectionFieldDeserializer<WkSzTstPrimitivesGroupList, WkSzVariableLengthOperationSettings, WkSzTstPrimitivesGroup, WkSzOperationSettings, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader>>
    variableSequence() {
      return this.readingCore.variableSequence();
    }

    @Override
    public WkTstPrimitivesGroupListSrlzStructNode definition() {
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
    public Optional<WkSzReadingResult<WkSzTstPrimitivesGroupList>> result() {
      return this.readingCore.result();
    }

    @Override
    public int index() {
      return this.readingCore.index();
    }

    @Override
    public WkSrlzInputPacketFieldFrameNode<WkSzTstPrimitivesGroupList, WkTstPrimitivesGroupListSrlzStructNode, ?> packetField() {
      return this.readingCore.packetField();
    }

    @Override
    public String name() {
      return this.readingCore.name();
    }

    @Override
    public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
      return this.readingCore.subfields();
    }

}
