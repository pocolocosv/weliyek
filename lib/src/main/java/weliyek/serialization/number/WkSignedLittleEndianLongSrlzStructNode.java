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
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.filter.FieldTester;

public class WkSignedLittleEndianLongSrlzStructNode
    implements WkNumberSrlzStructDefinitionFrameLeafNode<
                        Long,
                        WkSignedLittleEndianLongSrlzInputNode>
{

  public static WkSrlzStructComponentFrameNodeRootCore<
                        Long,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedLittleEndianLongSrlzStructNode,
                        WkSignedLittleEndianLongSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkSignedLittleEndianLongSrlzStructNode,
                        WkSignedLittleEndianLongSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkSignedLittleEndianLongSrlzStructNode>
  newPacketStructure(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkSignedLittleEndianLongSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSignedLittleEndianLongSrlzStructNode,
                        WkSignedLittleEndianLongSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSignedLittleEndianLongSrlzStructNode,
                        WkSignedLittleEndianLongSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkSignedLittleEndianLongSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSignedLittleEndianLongSrlzStructNode(componentCore).definitionCore;
  }

  private final WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                        Long,
                        WkSignedLittleEndianLongSrlzInputNode,
                        WkSignedLittleEndianLongSrlzOutputNode,
                        WkSignedLittleEndianLongSrlzStructNode> definitionCore;

  private WkSignedLittleEndianLongSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<
                                  Long,
                                  WkSignedLittleEndianLongSrlzInputNode,
                                  WkSignedLittleEndianLongSrlzOutputNode,
                                  WkSignedLittleEndianLongSrlzStructNode>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSignedLittleEndianLongSrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                      WkSignedLittleEndianLongSrlzEngineDecoder.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSignedLittleEndianLongSrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSignedLittleEndianLongSrlzEngineEncoder.FACTORY,
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
  makeTester(Predicate<? super WkSignedLittleEndianLongSrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

}
