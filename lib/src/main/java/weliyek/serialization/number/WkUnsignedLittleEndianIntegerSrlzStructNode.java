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
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.filter.FieldTester;

public class WkUnsignedLittleEndianIntegerSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<
                        Long,
                        WkUnsignedLittleEndianIntegerSrlzInputNode>
{

  public static WkSrlzStructComponentFrameNodeRootCore<
                        Long,
                        WkSzOperationSettings,
                        WkUnsignedLittleEndianIntegerSrlzStructNode,
                        WkUnsignedLittleEndianIntegerSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkUnsignedLittleEndianIntegerSrlzStructNode,
                        WkUnsignedLittleEndianIntegerSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkUnsignedLittleEndianIntegerSrlzStructNode>
  newPacketStructure(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkUnsignedLittleEndianIntegerSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkSzOperationSettings,?,?,
                        WkUnsignedLittleEndianIntegerSrlzStructNode,
                        WkUnsignedLittleEndianIntegerSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,?,?,
                        WkUnsignedLittleEndianIntegerSrlzStructNode,
                        WkUnsignedLittleEndianIntegerSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkUnsignedLittleEndianIntegerSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkUnsignedLittleEndianIntegerSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkUnsignedLittleEndianIntegerSrlzInputNode,
                        WkUnsignedLittleEndianIntegerSrlzOutputNode,
                        WkUnsignedLittleEndianIntegerSrlzStructNode> definitionCore;

  private WkUnsignedLittleEndianIntegerSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                  Long,
                                  WkUnsignedLittleEndianIntegerSrlzInputNode,
                                  WkUnsignedLittleEndianIntegerSrlzOutputNode,
                                  WkUnsignedLittleEndianIntegerSrlzStructNode>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkUnsignedLittleEndianIntegerSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                      WkUnsignedLittleEndianIntegerSrlzEngineDecoder.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkUnsignedLittleEndianIntegerSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkUnsignedLittleEndianIntegerSrlzEngineEncoder.FACTORY,
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
  public FieldTester<?, ?>
  makeTester(Predicate<? super WkUnsignedLittleEndianIntegerSrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
