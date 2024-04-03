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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkVariableSizeCollectionSrlzInputNode;
import weliyek.serialization.sequence.WkVariableSizeCollectionSrlzStructNode;

public class WkTstPrimitivesGroupListSrlzInputNode
    implements WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<WkSzTstPrimitivesGroupList>,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSzVariableLengthOperationSettings>
{

    final WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        Integer,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianInteger,
                        WkSzTstPrimitivesGroup,
                        WkSettingsSrlzPacketOperationData,
                        WkSzTstPrimitivesGroupStructDefinition,
                        WkSzTstPrimitivesGroupPacketReader,
                        WkSzVariableLengthOperationSettings> readingCore;

    WkTstPrimitivesGroupListSrlzInputNode(
      int index,
      WkSettingsSrlzPacketOperationData settings,
      WkSzInputBytestreamBase<?> parentBytestream,
      WkSrlzInputPacketFieldFrameNodeCore<WkSzTstPrimitivesGroupList, ?, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?>
        packetfieldCore,
      WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, ?, ?, ?, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, ?, ?, ?, ?, WkSzTstPrimitivesGroup, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?, ?>
        definitionCore) {
      readingCore = new WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzInputNode, WkTstPrimitivesGroupListSrlzStructNode, Integer, WkSettingsSrlzPacketOperationData, WkSerdeSignedLittleEndianIntegerReader, WkSerdeSignedLittleEndianInteger, WkSzTstPrimitivesGroup, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, WkSzVariableLengthOperationSettings>(
          index, settings, parentBytestream, packetfieldCore, definitionCore, this);
    }

    @Override
    public
    Optional<WkSrlzInputPacketFieldFrameNode<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader>>
    size() {
      return this.readingCore.size();
    }

    @Override
    public
    Optional<WkSrlzInputPacketFieldFrameNode<WkSzTstPrimitivesGroupList, WkVariableSizeCollectionSrlzStructNode<WkSzTstPrimitivesGroupList, WkSzVariableLengthOperationSettings, ?, WkSzTstPrimitivesGroup, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader, ?, ?, ?, ?>, WkVariableSizeCollectionSrlzInputNode<WkSzTstPrimitivesGroupList, WkSzVariableLengthOperationSettings, WkSzTstPrimitivesGroup, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupStructDefinition, WkSzTstPrimitivesGroupPacketReader>>>
    variableSequence() {
      return this.readingCore.variableSequence();
    }

    @Override
    public WkTstPrimitivesGroupListSrlzStructNode definition() {
      return this.readingCore.definition();
    }

    @Override
    public WkSettingsSrlzPacketOperationData settings() {
      return this.readingCore.settings();
    }

    @Override
    public WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream> dashboard() {
      return this.readingCore.dashboard();
    }

    @Override
    public Optional<WkResultSrlzPacketOperationData<WkSzTstPrimitivesGroupList>> result() {
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
