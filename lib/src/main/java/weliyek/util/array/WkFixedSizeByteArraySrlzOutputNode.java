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

public class WkFixedSizeByteArraySrlzOutputNode
    implements WkByteArraySrlzOutputPacketEncoderFrameNode<
                        WkSettingsSrlzPacketOperationData,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzStructNode>,
               WkFixedSizePrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<
                        WkByteArray,
                        WkSettingsSrlzPacketOperationData,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzStructNode>
{

  final SimplifiedPrimitiveArraySerializingCore<
                        WkByteArray,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzStructNode,
                        WkFixedSizeByteArraySrlzOutputNode> operationCore;

  WkFixedSizeByteArraySrlzOutputNode(
    int index,
    WkByteArray serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      WkByteArray,?,WkFixedSizeByteArraySrlzStructNode,?,?,?> serializingfieldCore,
    SimplifiedPrimitiveArraySerializerCore<
      WkByteArray,?,?,WkSettingsSrlzPacketOperationData,WkFixedSizeByteArraySrlzOutputNode,WkFixedSizeByteArraySrlzStructNode> definitionCore) {
    this.operationCore = new SimplifiedPrimitiveArraySerializingCore<
                                  WkByteArray,
                                  WkSettingsSrlzPacketOperationData,
                                  WkFixedSizeByteArraySrlzStructNode,
                                  WkFixedSizeByteArraySrlzOutputNode>(
                                      index,
                                      serializable,
                                      settings,
                                      parentBytestream,
                                      serializingfieldCore,
                                      definitionCore,
                                      this,
                                      PrimitiveArrayUtils::onFixedSizeSerilizingInitialization);
  }

  @Override
  public WkByteArray serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkFixedSizeByteArraySrlzStructNode definition() {
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
  public WkSrlzOutputPacketFieldFrameNode<WkByteArray, WkFixedSizeByteArraySrlzStructNode, ?>
      packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public int getRequestedLength() {
    return this.operationCore.getRequestedLength();
  }

}
