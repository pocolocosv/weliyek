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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;

public final class WkSignedLittleEndianLongSrlzOutputNode
        implements WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                        Long,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<Long>,
                        WkSignedLittleEndianLongSrlzStructNode>
{

  final WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<
                        Long,
                        WkSignedLittleEndianLongSrlzOutputNode,
                        WkSignedLittleEndianLongSrlzStructNode> operationCore;

  WkSignedLittleEndianLongSrlzOutputNode(
    int index,
    Long serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<
      Long,?,WkSignedLittleEndianLongSrlzStructNode,?,?,?> serializingfieldCore,
    WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
      Long,?,WkSignedLittleEndianLongSrlzOutputNode,WkSignedLittleEndianLongSrlzStructNode> definitionCore) {
    operationCore = new WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<>(
                            index,
                            serializable,
                            settings,
                            parentBytestream,
                            serializingfieldCore,
                            definitionCore,
                            this);
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
  public Optional<WkResultSrlzPacketOperationData<Long>> result() {
    return this.operationCore.result();
  }

  @Override
  public int index() {
    return this.operationCore.index();
  }

  @Override
  public Long serializable() {
    return this.operationCore.serializable();
  }

  @Override
  public WkSrlzOutputPacketFieldFrameNode<Long, WkSignedLittleEndianLongSrlzStructNode, ?> packetField() {
    return this.operationCore.packet();
  }

  @Override
  public WkSignedLittleEndianLongSrlzStructNode definition() {
    return this.operationCore.definition();
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
  public String toString() {
    return this.operationCore.toString();
  }

}
