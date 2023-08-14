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
package weliyek.util.array;

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
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.filter.WkSrlzPacketNodePredicate;
import weliyek.serialization.sequence.SequenceSizeParameters;

public class WkVariableSizeByteArraySrlzStructNode
    implements WkByteArraySrlzStructDefinitionFrameNode<
                        WkVariableSizeByteArraySrlzInputNode>,
               WkVariableSizePrimitiveArraySrlzStructDefinitionFrameLeafNode<
                        WkByteArray,
                        WkVariableSizeByteArraySrlzInputNode>
{

  public static WkSrlzStructComponentFrameNodeRootCore<
                      WkByteArray,
                      WkSzVariableLengthOperationSettings,
                      WkVariableSizeByteArraySrlzStructNode,
                      WkVariableSizeByteArraySrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkVariableSizeByteArraySrlzStructNode,
                      WkVariableSizeByteArraySrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkVariableSizeByteArraySrlzStructNode>
  newPacketStructure(String label, int minSize, int maxSize) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkVariableSizeByteArraySrlzStructNode.newCore(minSize, maxSize, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static
  WkSrlzStructDefinitionFrameNodeCore<
                      WkByteArray,
                      WkSzVariableLengthOperationSettings,?,?,
                      WkVariableSizeByteArraySrlzStructNode,
                      WkVariableSizeByteArraySrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkVariableSizeByteArraySrlzStructNode,
                      WkVariableSizeByteArraySrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkVariableSizeByteArraySrlzStructNode,?>
  newCore(
    int minSize,
    int maxSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkVariableSizeByteArraySrlzStructNode(minSize, maxSize, componentCore).definitionCore;
  }

  private final SimplifiedPrimitiveArraySerializerCore<
                        WkByteArray,
                        WkSzVariableLengthOperationSettings,
                        WkVariableSizeByteArraySrlzInputNode,
                        WkSettingsSrlzPacketOperationData,
                        WkVariableSizeByteArraySrlzOutputNode,
                        WkVariableSizeByteArraySrlzStructNode> definitionCore;
  private final SequenceSizeParameters<WkByteArray> sizeLimits;

  private WkVariableSizeByteArraySrlzStructNode(
    int minSize,
    int maxSize,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedPrimitiveArraySerializerCore<
        WkByteArray,
        WkSzVariableLengthOperationSettings,
        WkVariableSizeByteArraySrlzInputNode,
        WkSettingsSrlzPacketOperationData,
        WkVariableSizeByteArraySrlzOutputNode,
        WkVariableSizeByteArraySrlzStructNode>(
                                  1024, // de/serialization step size
                                  componentCore,
                                  WkVariableSizeByteArraySrlzStructNode::getRxRequestedLength,
                                  (i,xs,axb,xkc,dc) -> new WkVariableSizeByteArraySrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                  WkBasicByteArraySrlzEngineDecoder.FACTORY,
                                  (SerializingPrimitiveArrayLengthProvider<WkByteArray,WkSettingsSrlzPacketOperationData,WkVariableSizeByteArraySrlzStructNode>)WkVariableSizeByteArraySrlzStructNode::getTxRequestedLength,
                                  (i,y,ys,ayb,ykc,dc) -> new WkVariableSizeByteArraySrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                  WkByteArraySrlzEngineEncoder.FACTORY,
                                  this,
                                  WkByteArray.class);
    this.sizeLimits = new SequenceSizeParameters<>(minSize, maxSize, definitionCore);
  }

  private static int getRxRequestedLength(WkSzVariableLengthOperationSettings settings, WkVariableSizeByteArraySrlzStructNode definition) {
    return settings.getRequestedLength();
  }

  private static int getTxRequestedLength(
    WkByteArray wrapper,
    WkSettingsSrlzPacketOperationData settings,
    WkVariableSizeByteArraySrlzStructNode definition) {
    return wrapper.getLength();
  }

  @Override
  public Class<WkByteArray> rxClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?>
  makeTester(Predicate<? super WkVariableSizeByteArraySrlzInputNode> test, String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public int minimalSize() {
    return this.sizeLimits.minimalSize();
  }

  @Override
  public int maximalSize() {
    return this.sizeLimits.maximalSize();
  }

  @Override
  public int getSerializationStepSize() {
    return this.definitionCore.getSerializationStepSize();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
