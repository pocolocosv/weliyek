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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.sequence.SequenceFixedSizeParameter;
import weliyek.serialization.sequence.WkPrimitiveArrayLengthGetter;

public class WkFixedSizeByteArraySrlzStructNode
    implements WkSerdeDTreeByteArrayDefinition,
               WkSerdeDTreeFixedSizePrimitiveArrayDefinition<
                        WkByteArray>
{

  public static WkSrlzStruct<
                      WkByteArray,
                      WkSettingsSrlzPacketOperationData,
                      WkFixedSizeByteArraySrlzStructNode,
                      WkFixedSizeByteArraySrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkFixedSizeByteArraySrlzStructNode,
                      WkFixedSizeByteArraySrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkFixedSizeByteArraySrlzStructNode>
  newStruct(
    String label,
    int expectedLength) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkFixedSizeByteArraySrlzStructNode.newCore(expectedLength, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                      WkByteArray,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkFixedSizeByteArraySrlzStructNode,
                      WkFixedSizeByteArraySrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkFixedSizeByteArraySrlzStructNode,
                      WkFixedSizeByteArraySrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkFixedSizeByteArraySrlzStructNode,?>
  newCore(
    int expectedLength,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkFixedSizeByteArraySrlzStructNode(expectedLength, componentCore).definitionCore;
  }

  private final WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<
                        WkByteArray,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzInputNode,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzOutputNode,
                        WkFixedSizeByteArraySrlzStructNode> definitionCore;
  private final SequenceFixedSizeParameter<WkByteArray> fixedSizeParameter;

  private WkFixedSizeByteArraySrlzStructNode(
    int expectedLength,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSimplifiedPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<
        WkByteArray,
        WkSettingsSrlzPacketOperationData,
        WkFixedSizeByteArraySrlzInputNode,
        WkSettingsSrlzPacketOperationData,
        WkFixedSizeByteArraySrlzOutputNode,
        WkFixedSizeByteArraySrlzStructNode>(
                                    1024, // de/serialization step size
                                    componentCore,
                                    WkFixedSizeByteArraySrlzStructNode::getRxRequestedLengthFromDefinition,
                                    (i,xs,axb,xkc,dc) -> new WkFixedSizeByteArraySrlzInputNode(i,xs,axb,xkc,dc).operationCore,
                                    WkBasicByteArraySrlzEngineDecoder.FACTORY,
                                    (WkPrimitiveArrayLengthGetter<WkByteArray,WkSettingsSrlzPacketOperationData,WkFixedSizeByteArraySrlzStructNode>)WkFixedSizeByteArraySrlzStructNode::getTxRequestedLengthFromDefinition,
                                    (i,y,ys,ayb,ykc,dc) -> new WkFixedSizeByteArraySrlzOutputNode(i,y,ys,ayb,ykc,dc).operationCore,
                                    WkByteArraySrlzEngineEncoder.FACTORY,
                                    this,
                                    WkByteArray.class);
    this.fixedSizeParameter = new SequenceFixedSizeParameter<WkByteArray>(expectedLength, this.definitionCore);
  }

  private static int getRxRequestedLengthFromDefinition(WkSettingsSrlzPacketOperationData none, WkFixedSizeByteArraySrlzStructNode definition) {
    return definition.getExpectedLength();
  }

  private static int getTxRequestedLengthFromDefinition(WkByteArray wrapper, WkSettingsSrlzPacketOperationData none, WkFixedSizeByteArraySrlzStructNode definition) {
    return definition.getExpectedLength();
  }

  @Override
  public Class<WkByteArray> serializableClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

  @Override
  public int getSerializationStepSize() {
    return this.definitionCore.getSerializationStepSize();
  }

}
