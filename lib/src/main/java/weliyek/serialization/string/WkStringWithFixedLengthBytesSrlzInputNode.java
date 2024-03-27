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
package weliyek.serialization.string;

import java.nio.charset.Charset;
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
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArray;

public class WkStringWithFixedLengthBytesSrlzInputNode
    implements WkStringFromBytesSrlzInputPacketDecoderFrameNode<
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<String>,
                        WkStringWithFixedLengthBytesSrlzStructNode,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayReader>
{

  final WkStringFromBytesSrlzInputPacketDecoderFrameNodeSimplifiedCore<
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithFixedLengthBytesSrlzInputNode,
                        WkStringWithFixedLengthBytesSrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        WkSerdeDtreeFixedSizeByteArray> operationCore;

  WkStringWithFixedLengthBytesSrlzInputNode(
    int index,
    WkSettingsSrlzPacketOperationData settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<
      String,?,WkStringWithFixedLengthBytesSrlzStructNode,?,?,?> deserializingfieldCore,
    WkStringFromBytesSrlzStructDefinitionFrameNodeSimplifiedCore<
      WkSettingsSrlzPacketOperationData,WkStringWithFixedLengthBytesSrlzInputNode,
      WkStringWithFixedLengthBytesSrlzStructNode,?,?,?,WkSettingsSrlzPacketOperationData,
      WkSerdeDtreeFixedSizeByteArrayReader,WkSerdeDtreeFixedSizeByteArray,?,?,?,?,
      ? extends WkStringWithFixedLengthBytesSrlzStructNode> definitionCore) {
    this.operationCore = new WkStringFromBytesSrlzInputPacketDecoderFrameNodeSimplifiedCore<
                                WkSettingsSrlzPacketOperationData,
                                WkStringWithFixedLengthBytesSrlzInputNode,
                                WkStringWithFixedLengthBytesSrlzStructNode,
                                WkSettingsSrlzPacketOperationData,
                                WkSerdeDtreeFixedSizeByteArrayReader,
                                WkSerdeDtreeFixedSizeByteArray>(
                                  index,
                                  settings,
                                  parentBytestream,
                                  deserializingfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader>
  primitiveArray() {
    return this.operationCore.primitiveArray();
  }

  @Override
  public WkStringWithFixedLengthBytesSrlzStructNode definition() {
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
  public Optional<WkResultSrlzPacketOperationData<String>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public WkSrlzInputPacketFieldFrameNode<String, WkStringWithFixedLengthBytesSrlzStructNode, ?> packetField() {
    return this.operationCore.packetField();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public
  WkSrlzInputPacketSubfieldFrameNode<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
