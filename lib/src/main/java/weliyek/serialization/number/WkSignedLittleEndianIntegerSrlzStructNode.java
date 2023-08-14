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
import weliyek.serialization.sequence.WkSignedLittleEndianIntegerSrlzEngineEncoder;

public class WkSignedLittleEndianIntegerSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<
                        Integer,
                        WkSignedLittleEndianIntegerSrlzInputNode>
{

  public static WkSrlzStructComponentFrameNodeRootCore<
                        Integer,
                        WkSzOperationSettings,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSignedLittleEndianIntegerSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSignedLittleEndianIntegerSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkSignedLittleEndianIntegerSrlzStructNode>
  newPacketStructure(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSignedLittleEndianIntegerSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                        Integer,
                        WkSzOperationSettings,?,?,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSignedLittleEndianIntegerSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,?,?,
                        WkSignedLittleEndianIntegerSrlzStructNode,
                        WkSignedLittleEndianIntegerSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkSignedLittleEndianIntegerSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSignedLittleEndianIntegerSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Integer,
                        WkSignedLittleEndianIntegerSrlzInputNode,
                        WkSignedLittleEndianIntegerSrlzOutputNode,
                        WkSignedLittleEndianIntegerSrlzStructNode> definitionCore;

  public WkSignedLittleEndianIntegerSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                  Integer,
                                  WkSignedLittleEndianIntegerSrlzInputNode,
                                  WkSignedLittleEndianIntegerSrlzOutputNode,
                                  WkSignedLittleEndianIntegerSrlzStructNode>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSignedLittleEndianIntegerSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                      WkSignedLittleEndianIntegerSrlzEngineDecoder.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSignedLittleEndianIntegerSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSignedLittleEndianIntegerSrlzEngineEncoder.FACTORY,
                                      this,
                                      Integer.class);
  }

  @Override
  public Class<Integer> rxClass() {
    return Integer.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public FieldTester<?, ?>
  makeTester(Predicate<? super WkSignedLittleEndianIntegerSrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
