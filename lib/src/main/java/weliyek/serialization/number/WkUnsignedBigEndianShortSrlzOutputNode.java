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
package weliyek.serialization.number;

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

public class WkUnsignedBigEndianShortSrlzOutputNode
    implements WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                        Integer,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingResultSrlzPacketOperationData,
                        WkUnsignedBigEndianShortSrlzStructNode>
{

  final WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<
                        Integer,
                        WkUnsignedBigEndianShortSrlzOutputNode,
                        WkUnsignedBigEndianShortSrlzStructNode> operationCore;

  WkUnsignedBigEndianShortSrlzOutputNode(
    int index,
    Integer serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      Integer,?,WkUnsignedBigEndianShortSrlzStructNode,?,?,?> serializingfieldCore,
    WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
      Integer,?,WkUnsignedBigEndianShortSrlzOutputNode,WkUnsignedBigEndianShortSrlzStructNode> definitionCore) {
    this.operationCore = new WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<>(
                                  index,
                                  serializable,
                                  settings,
                                  parentBytestream,
                                  serializingfieldCore,
                                  definitionCore,
                                  this);
  }

  @Override
  public WkUnsignedBigEndianShortSrlzStructNode definition() {
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
  public WkSrlzOutputPacketFieldFrameNode<Integer, WkUnsignedBigEndianShortSrlzStructNode, ?> packetField() {
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
  public Integer serializable() {
    return this.operationCore.serializable();
  }

}
