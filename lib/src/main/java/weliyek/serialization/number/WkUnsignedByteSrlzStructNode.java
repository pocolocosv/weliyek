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

public class WkUnsignedByteSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<Integer>
{

  public static WkSrlzStruct<
                      Integer,
                      WkSettingsSrlzPacketOperationData,
                      WkUnsignedByteSrlzStructNode,
                      WkUnsignedByteSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkUnsignedByteSrlzStructNode,
                      WkUnsignedByteSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkUnsignedByteSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkUnsignedByteSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                      Integer,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkUnsignedByteSrlzStructNode,
                      WkUnsignedByteSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData, ?, ?,
                      WkUnsignedByteSrlzStructNode,
                      WkUnsignedByteSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkUnsignedByteSrlzStructNode, ?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkUnsignedByteSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Integer,
                        WkUnsignedByteSrlzInputNode,
                        WkUnsignedByteSrlzOutputNode,
                        WkUnsignedByteSrlzStructNode> definitionCore;

  private WkUnsignedByteSrlzStructNode(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<>(
                                  componentCore,
                                  (i,xs,axb,xkc,dc) -> new WkUnsignedByteSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                  WkUnsignedByteSrlzEngineDecoder.FACTORY,
                                  (i,y,ys,ayb,ykc,dc) -> new WkUnsignedByteSrlzOutputNode(i,y,ys,ayb,ykc,dc).writingCore,
                                  WkUnsignedByteSrlzEngineEncoder.FACTORY,
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
