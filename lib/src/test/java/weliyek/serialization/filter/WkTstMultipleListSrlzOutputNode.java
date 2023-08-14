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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzStructNode;
import weliyek.serialization.number.WkSignedBigEndianShortSrlzOutputNode;
import weliyek.serialization.sequence.WkDynamicCollectionSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.sequence.WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.sequence.VariableSizeCollectionField;
import weliyek.serialization.sequence.VariableSizeCollectionFieldSerializer;

public class WkTstMultipleListSrlzOutputNode
        implements WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                        WkSzTstMultipleLists,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSignedBigEndianShortSrlzOutputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSettingsSrlzPacketOperationData>
{

    final WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<
                        WkSzTstMultipleLists,
                        WkSettingsSrlzPacketOperationData,
                        WkTstMultipleListSrlzOutputNode,
                        WkTstMultipleListSrlzStructNode,
                        Short,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedBigEndianShortSrlzOutputNode,
                        WkSignedBigEndianShortSrlzStructNode,
                        WkSzTstPrimitivesGroupList,
                        WkSettingsSrlzPacketOperationData,
                        WkTstPrimitivesGroupListSrlzStructNode,
                        WkTstPrimitivesGroupListSrlzOutputNode,
                        WkSettingsSrlzPacketOperationData> operationCore;

  WkTstMultipleListSrlzOutputNode(
    int index,
    WkSzTstMultipleLists serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<WkSzTstMultipleLists, ?, WkTstMultipleListSrlzStructNode, ?, ?, ?>
      serializerpacketCore,
    WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<WkSzTstMultipleLists, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode, ?, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?, ?, WkSettingsSrlzPacketOperationData, ?>
      definitionCore) {
    operationCore = new WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<
                            WkSzTstMultipleLists, WkSettingsSrlzPacketOperationData, WkTstMultipleListSrlzOutputNode, WkTstMultipleListSrlzStructNode, Short, WkSettingsSrlzPacketOperationData, WkSignedBigEndianShortSrlzOutputNode, WkSignedBigEndianShortSrlzStructNode, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, WkSettingsSrlzPacketOperationData>(
                                index, serializable, settings, parentBytestream, serializerpacketCore, definitionCore, this);
  }

  @Override
  public WkSrlzOutputPacketSubfieldFrameNode<Short, WkSignedBigEndianShortSrlzStructNode, WkSignedBigEndianShortSrlzOutputNode>
  size() {
    return this.operationCore.size();
  }

  @Override
  public
  WkSrlzOutputPacketSubfieldFrameNode<WkSzTstMultipleLists, VariableSizeCollectionField<WkSzTstMultipleLists, ?, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupList, ?, ?, ?, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode, ?>, VariableSizeCollectionFieldSerializer<WkSzTstMultipleLists, WkSettingsSrlzPacketOperationData, WkSzTstPrimitivesGroupList, WkSettingsSrlzPacketOperationData, WkTstPrimitivesGroupListSrlzStructNode, WkTstPrimitivesGroupListSrlzOutputNode>>
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
  public WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkEncodingResultSrlzPacketOperationData> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<WkSzTstMultipleLists, WkTstMultipleListSrlzStructNode, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkSzTstMultipleLists serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

}
