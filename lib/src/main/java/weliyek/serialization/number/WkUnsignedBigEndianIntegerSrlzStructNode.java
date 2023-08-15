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
import java.util.function.Predicate;

import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.filter.WkSrlzPacketNodePredicate;

public class WkUnsignedBigEndianIntegerSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<
                        Long,
                        WkUnsignedBigEndianIntegerSrlzInputNode>
{

  public static WkSrlzStruct<
                        Long,
                        WkSettingsSrlzPacketOperationData,
                        WkUnsignedBigEndianIntegerSrlzStructNode,
                        WkUnsignedBigEndianIntegerSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkUnsignedBigEndianIntegerSrlzStructNode,
                        WkUnsignedBigEndianIntegerSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkUnsignedBigEndianIntegerSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkUnsignedBigEndianIntegerSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkUnsignedBigEndianIntegerSrlzStructNode,
                        WkUnsignedBigEndianIntegerSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkUnsignedBigEndianIntegerSrlzStructNode,
                        WkUnsignedBigEndianIntegerSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkUnsignedBigEndianIntegerSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkUnsignedBigEndianIntegerSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkUnsignedBigEndianIntegerSrlzInputNode,
                        WkUnsignedBigEndianIntegerSrlzOutputNode,
                        WkUnsignedBigEndianIntegerSrlzStructNode> definitionCore;

  private WkUnsignedBigEndianIntegerSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                  Long,
                                  WkUnsignedBigEndianIntegerSrlzInputNode,
                                  WkUnsignedBigEndianIntegerSrlzOutputNode,
                                  WkUnsignedBigEndianIntegerSrlzStructNode>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkUnsignedBigEndianIntegerSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                      WkUnsignedBigEndianIntegerSrlzEngineDecoder.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkUnsignedBigEndianIntegerSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkUnsignedBigEndianIntegerSrlzEngineEncoder.FACTORY,
                                      this,
                                      Long.class);
  }

  @Override
  public Class<Long> rxClass() {
    return Long.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?>
  makeTester(Predicate<? super WkUnsignedBigEndianIntegerSrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }
}
