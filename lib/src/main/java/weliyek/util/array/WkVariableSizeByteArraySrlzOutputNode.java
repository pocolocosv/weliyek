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
package weliyek.util.array;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;

public class WkVariableSizeByteArraySrlzOutputNode
    implements WkByteArraySrlzOutputPacketEncoderFrameNode<
                        WkSettingsSrlzPacketOperationData,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkVariableSizeByteArraySrlzStructNode>,
               WkVaribleSizePrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<
                        WkByteArray,
                        WkSettingsSrlzPacketOperationData,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkVariableSizeByteArraySrlzStructNode>
{

  final SimplifiedPrimitiveArraySerializingCore<
                    WkByteArray,
                    WkSettingsSrlzPacketOperationData,
                    WkVariableSizeByteArraySrlzStructNode,
                    WkVariableSizeByteArraySrlzOutputNode> operationCore;

  WkVariableSizeByteArraySrlzOutputNode(
    int index,
    WkByteArray serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      WkByteArray,?,WkVariableSizeByteArraySrlzStructNode,?,?,?> serializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<
      WkByteArray,?,?,WkSettingsSrlzPacketOperationData,WkVariableSizeByteArraySrlzOutputNode,WkVariableSizeByteArraySrlzStructNode> definitionCore) {
    this.operationCore = new SimplifiedPrimitiveArraySerializingCore<
                                WkByteArray,
                                WkSettingsSrlzPacketOperationData,
                                WkVariableSizeByteArraySrlzStructNode,
                                WkVariableSizeByteArraySrlzOutputNode>(
                                    index,
                                    serializable,
                                    settings,
                                    parentBytestream,
                                    serializingfieldCore,
                                    definitionCore,
                                    this,
                                    PrimitiveArrayUtils::onVariableSizeSerializingInitialization);
  }

  @Override
  public WkVariableSizeByteArraySrlzStructNode definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSettingsSrlzPacketOperationData settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
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
  public WkSrlzOutputPacketFieldFrameNode<WkByteArray, WkVariableSizeByteArraySrlzStructNode, ?>
  packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public WkByteArray serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public int getRequestedLength() {
    return this.operationCore.getRequestedLength();
  }

}
