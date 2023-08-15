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
import weliyek.serialization.sequence.WkSignedLittleEndianShortSrlzEngineEncoder;

public class WkSignedLittleEndianShortSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<
                        Short,
                        WkSignedLittleEndianShortSrlzInputNode>
{

  public static WkSrlzStruct<
                      Short,
                      WkSettingsSrlzPacketOperationData,
                      WkSignedLittleEndianShortSrlzStructNode,
                      WkSignedLittleEndianShortSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkSignedLittleEndianShortSrlzStructNode,
                      WkSignedLittleEndianShortSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkSignedLittleEndianShortSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSignedLittleEndianShortSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                      Short,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSignedLittleEndianShortSrlzStructNode,
                      WkSignedLittleEndianShortSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkSignedLittleEndianShortSrlzStructNode,
                      WkSignedLittleEndianShortSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkSignedLittleEndianShortSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSignedLittleEndianShortSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Short,
                        WkSignedLittleEndianShortSrlzInputNode,
                        WkSignedLittleEndianShortSrlzOutputNode,
                        WkSignedLittleEndianShortSrlzStructNode> definitionCore;

  public WkSignedLittleEndianShortSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                  Short,
                                  WkSignedLittleEndianShortSrlzInputNode,
                                  WkSignedLittleEndianShortSrlzOutputNode,
                                  WkSignedLittleEndianShortSrlzStructNode>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSignedLittleEndianShortSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                      WkSignedLittleEndianShortSrlzEngineDecoder.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSignedLittleEndianShortSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSignedLittleEndianShortSrlzEngineEncoder.FACTORY,
                                      this,
                                      Short.class);
  }

  @Override
  public Class<Short> rxClass() {
    return Short.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?>
  makeTester(Predicate<? super WkSignedLittleEndianShortSrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
