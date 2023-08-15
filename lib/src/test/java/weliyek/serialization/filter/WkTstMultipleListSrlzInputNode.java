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
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzInputNode;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.sequence.WkVariableSizeCollectionSrlzStructNode;
import weliyek.serialization.sequence.WkVariableSizeCollectionSrlzInputNode;

public class WkTstMultipleListSrlzInputNode
        implements WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        WkSzTstMultipleLists,
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkDecodingResultSrlzPacketOperationData<WkSzTstMultipleLists>,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSignedBigEndianShortSrlzInputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkSzVariableLengthOperationSettings>
{

    final WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                        WkSzTstMultipleLists,
                        WkSettingsSrlzPacketOperationData,
                        WkTstMultipleListSrlzInputNode,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedBigEndianShortSrlzInputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzInputNode,
                        WkSzVariableLengthOperationSettings>
                        operationCore;

  WkTstMultipleListSrlzInputNode(
    int index,
    WkSettingsSrlzPacketOperationData settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<WkSzTstMultipleLists, ?, WkTstMultipleListSrlzStructNode, ?, ?, ?> packetfieldCore,
    WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<WkSzTstMultipleLists, WkSettingsSrlzPacketOperationData, WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzStructNode, ?, ?, ?, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzInputNode, WkSignedBigEndianShortSrlzStructNode, ?, ?, ?, ?, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, ?, ?, ?, ?, WkSzVariableLengthOperationSettings, ?, ?>
      definitionCore) {
    this.operationCore = new WkSimplifiedDynamicCollectionSrlzInputPacketDecoderFrameNodeCore<
                                WkSzTstMultipleLists, WkSettingsSrlzPacketOperationData, WkTstMultipleListSrlzInputNode, WkTstMultipleListSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzInputNode, WkSignedBigEndianShortSrlzStructNode, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, WkSzVariableLengthOperationSettings>(
                                    index, settings, parentBytestream, packetfieldCore, definitionCore, this);
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<Short, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzInputNode>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkSzTstMultipleLists, WkVariableSizeCollectionSrlzStructNode<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, ?, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode, ?, ?, ?, ?>, WkVariableSizeCollectionSrlzInputNode<WkSzTstMultipleLists, WkSzVariableLengthOperationSettings, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzInputNode>>
  variableSequence() {
    return this.operationCore.variableSequence();
  }

  @Override
  public WkTstMultipleListSrlzStructNode definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSettingsSrlzPacketOperationData settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkDecodingResultSrlzPacketOperationData<WkSzTstMultipleLists>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzInputPacketFieldFrameNode<WkSzTstMultipleLists, WkTstMultipleListSrlzStructNode, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
