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

public class WkSignedByteSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<
                        Byte,
                        WkSignedByteSrlzInputNode>
{

  public static WkSrlzStruct<
                      Byte,
                      WkSettingsSrlzPacketOperationData,
                      WkSignedByteSrlzStructNode,
                      WkSignedByteSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkSignedByteSrlzStructNode,
                      WkSignedByteSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkSignedByteSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSignedByteSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                      Byte,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSignedByteSrlzStructNode,
                      WkSignedByteSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSignedByteSrlzStructNode,
                      WkSignedByteSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkSignedByteSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSignedByteSrlzStructNode(componentCore).definitionCore;
  }

  private WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Byte,
                        WkSignedByteSrlzInputNode,
                        WkSignedByteSrlzOutputNode,
                        WkSignedByteSrlzStructNode> definitionCore;

  private WkSignedByteSrlzStructNode(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                Byte,
                                WkSignedByteSrlzInputNode,
                                WkSignedByteSrlzOutputNode,
                                WkSignedByteSrlzStructNode>(
                                    componentCore,
                                    (i,xs,xab,xkc,xdc) -> new WkSignedByteSrlzInputNode(i,xs,xab,xkc,xdc).operationCore,
                                    WkSignedByteSrlzEngineDecoder.FACTORY,
                                    (i,y,ys,yab,ykc,ydc) -> new WkSignedByteSrlzOutputNode(i,y,ys,yab,ykc,ydc).writingCore,
                                    WkSignedByteSrlzEngineEncoder.FACTORY,
                                    this,
                                    Byte.class);
  }

  @Override
  public Class<Byte> rxClass() {
    return Byte.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?,?>
  makeTester(Predicate<? super WkSignedByteSrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
