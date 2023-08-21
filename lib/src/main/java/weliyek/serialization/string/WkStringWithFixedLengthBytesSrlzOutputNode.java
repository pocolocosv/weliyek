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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkFixedSizeByteArraySrlzOutputNode;
import weliyek.util.array.WkFixedSizeByteArraySrlzStructNode;

public class WkStringWithFixedLengthBytesSrlzOutputNode
    implements WkStringFromBytesSrlzOutputPacketEncoderFrameNode<
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<String>,
                        WkStringWithFixedLengthBytesSrlzStructNode,
                        WkFixedSizeByteArraySrlzStructNode,
                        WkFixedSizeByteArraySrlzOutputNode>
{

  final SimpleStringFromBytesWritingCore<
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithFixedLengthBytesSrlzOutputNode,
                        WkStringWithFixedLengthBytesSrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzOutputNode,
                        WkFixedSizeByteArraySrlzStructNode> operationCore;

  WkStringWithFixedLengthBytesSrlzOutputNode(
    int index,
    String serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      String,?,WkStringWithFixedLengthBytesSrlzStructNode,?,?,?> serializingfieldCore,
    SimplifiedStringFromBytesCore<
      ?,?,?,WkSettingsSrlzPacketOperationData,WkStringWithFixedLengthBytesSrlzOutputNode,
      WkStringWithFixedLengthBytesSrlzStructNode,?,?,?,WkSettingsSrlzPacketOperationData,
      WkFixedSizeByteArraySrlzOutputNode,WkFixedSizeByteArraySrlzStructNode,?,
      ? extends WkStringWithFixedLengthBytesSrlzStructNode> definitionCore) {
    this.operationCore = new SimpleStringFromBytesWritingCore<WkSettingsSrlzPacketOperationData,
        WkStringWithFixedLengthBytesSrlzOutputNode,
        WkStringWithFixedLengthBytesSrlzStructNode,
        WkSettingsSrlzPacketOperationData,
        WkFixedSizeByteArraySrlzOutputNode,
        WkFixedSizeByteArraySrlzStructNode>(
                                  index,
                                  serializable,
                                  settings,
                                  parentBytestream,
                                  serializingfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public
  WkSrlzOutputPacketSubfieldFrameNode<WkByteArray, WkFixedSizeByteArraySrlzStructNode, WkFixedSizeByteArraySrlzOutputNode>
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
  public WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> dashboard() {
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
  public WkSrlzOutputPacketFieldFrameNode<String, WkStringWithFixedLengthBytesSrlzStructNode, ?> packetField() {
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
  public String serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public
  WkSrlzOutputPacketSubfieldFrameNode<WkByteArray, WkFixedSizeByteArraySrlzStructNode, WkFixedSizeByteArraySrlzOutputNode>
  bytes() {
    return this.operationCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.operationCore.charset();
  }

}
