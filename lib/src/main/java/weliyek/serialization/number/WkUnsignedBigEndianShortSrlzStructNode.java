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

public class WkUnsignedBigEndianShortSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<Integer>
{

  public static WkSrlzStruct<
                        Integer,
                        WkSettingsSrlzPacketOperationData,
                        WkUnsignedBigEndianShortSrlzStructNode,
                        WkUnsignedBigEndianShortSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkUnsignedBigEndianShortSrlzStructNode,
                        WkUnsignedBigEndianShortSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkUnsignedBigEndianShortSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkUnsignedBigEndianShortSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                        Integer,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkUnsignedBigEndianShortSrlzStructNode,
                        WkUnsignedBigEndianShortSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkUnsignedBigEndianShortSrlzStructNode,
                        WkUnsignedBigEndianShortSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkUnsignedBigEndianShortSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkUnsignedBigEndianShortSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Integer,
                        WkUnsignedBigEndianShortSrlzInputNode,
                        WkUnsignedBigEndianShortSrlzOutputNode,
                        WkUnsignedBigEndianShortSrlzStructNode> definitionCore;

  private WkUnsignedBigEndianShortSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                  Integer,
                                  WkUnsignedBigEndianShortSrlzInputNode,
                                  WkUnsignedBigEndianShortSrlzOutputNode,
                                  WkUnsignedBigEndianShortSrlzStructNode>(
                                    componentCore,
                                    (i,xs,axb,xkc,dc) -> new WkUnsignedBigEndianShortSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                    WkUnsignedBigEndianShortSrlzEngineDecoder.FACTORY,
                                    (i,y,ys,ayb,ykc,dc) -> new WkUnsignedBigEndianShortSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                    WkUnsignedBigEndianShortSrlzEngineEncoder.FACTORY,
                                    this,
                                    Integer.class);
  }

  @Override
  public Class<Integer> serializableClass() {
    return Integer.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

}
