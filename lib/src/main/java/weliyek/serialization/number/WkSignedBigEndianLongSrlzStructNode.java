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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;

public class WkSignedBigEndianLongSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<Long>
{

  public static WkSrlzStruct<
                        Long,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedBigEndianLongSrlzStructNode,
                        WkSignedBigEndianLongSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedBigEndianLongSrlzStructNode,
                        WkSignedBigEndianLongSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkSignedBigEndianLongSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSignedBigEndianLongSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSignedBigEndianLongSrlzStructNode,
                        WkSignedBigEndianLongSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSignedBigEndianLongSrlzStructNode,
                        WkSignedBigEndianLongSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkSignedBigEndianLongSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSignedBigEndianLongSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkSignedBigEndianLongSrlzInputNode,
                        WkSignedBigEndianLongSrlzOutputNode,
                        WkSignedBigEndianLongSrlzStructNode> definitionCore;

  private WkSignedBigEndianLongSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                            Long,
                            WkSignedBigEndianLongSrlzInputNode,
                            WkSignedBigEndianLongSrlzOutputNode,
                            WkSignedBigEndianLongSrlzStructNode>(
                                componentCore,
                                (i,xs,axb,xkc,dc) -> new WkSignedBigEndianLongSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                WkSignedBigEndianLongSrlzEngineDecoder.FACTORY,
                                (i,y,ys,ayb,ykc,dc) -> new WkSignedBigEndianLongSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                WkSignedBigEndianLongSrlzEngineEncoder.FACTORY,
                                this,
                                Long.class);
  }

  @Override
  public Class<Long> serializableClass() {
    return Long.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

}
