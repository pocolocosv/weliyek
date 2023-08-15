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
package weliyek.serialization.string;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSzOptionalLengthOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.filter.WkSrlzPacketNodePredicate;
import weliyek.serialization.string.WkStringFromBytesSrlzStructDefinitionFrameNodeCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkVariableSizeByteArraySrlzStructNode;
import weliyek.util.array.WkVariableSizeByteArraySrlzInputNode;
import weliyek.util.array.WkVariableSizeByteArraySrlzOutputNode;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;

public class WkStringWithVariableBytesSrlzStructNode
    implements WkStringFromBytesSrlzStructDefinitionFrameNode<
                        WkStringWithVariableBytesSrlzInputNode,
                        WkStringWithVariableBytesSrlzOutputNode,
                        WkVariableSizeByteArraySrlzStructNode>
{

  public static WkSrlzStruct<
                      String,
                      WkSzVariableLengthOperationSettings,
                      WkStringWithVariableBytesSrlzStructNode,
                      WkStringWithVariableBytesSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSzOptionalLengthOperationSettings,
                      WkStringWithVariableBytesSrlzStructNode,
                      WkStringWithVariableBytesSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkStringWithVariableBytesSrlzStructNode>
  newStruct(
    String label,
    String bytesLabel,
    int minimalLength,
    int maximalLength,
    Charset defaultCharset) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkStringWithVariableBytesSrlzStructNode.newCore(
                                    bytesLabel, minimalLength, maximalLength, defaultCharset, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                      String,
                      WkSzVariableLengthOperationSettings,?,?,
                      WkStringWithVariableBytesSrlzStructNode,
                      WkStringWithVariableBytesSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSzOptionalLengthOperationSettings,?,?,
                      WkStringWithVariableBytesSrlzStructNode,
                      WkStringWithVariableBytesSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkStringWithVariableBytesSrlzStructNode,?>
  newCore(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkStringWithVariableBytesSrlzStructNode(bytesLabel, minSize, maxSize, defaultCharset, componentCore).definitionCore;
  }

  private final SimplifiedStringFromBytesCore<
                        WkSzVariableLengthOperationSettings,
                        WkStringWithVariableBytesSrlzInputNode,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkSzOptionalLengthOperationSettings,
                        WkStringWithVariableBytesSrlzOutputNode,
                        WkStringWithVariableBytesSrlzStructNode,
                        WkSzVariableLengthOperationSettings,
                        WkVariableSizeByteArraySrlzInputNode,
                        WkVariableSizeByteArraySrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkVariableSizeByteArraySrlzOutputNode,
                        WkVariableSizeByteArraySrlzStructNode,
                        WkVariableSizeByteArraySrlzStructNode,
                        WkStringWithVariableBytesSrlzStructNode> definitionCore;

  WkStringWithVariableBytesSrlzStructNode(
    String bytesLabel,
    int minSize,
    int maxSize,
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new SimplifiedStringFromBytesCore<>(
                                    defaultCharset,
                                    componentCore,
                                    (i,xs,axb,xpc,dc) -> new WkStringWithVariableBytesSrlzInputNode(i,xs,axb,xpc,dc).operationCore,
                                    (i,y,ys,ayb,ypc,dc) -> new WkStringWithVariableBytesSrlzOutputNode(i,y,ys,ayb,ypc,dc).operationCore,
                                    bytesLabel,
                                    WkOperationSettingsFactory.parentSettingsReuser(), // reusing deserializing settings
                                    WkStringWithVariableBytesSrlzStructNode::aggragateByteArray,
                                    WkSettingsSrlzPacketOperationData::none,
                                    new VariableLengthBytesDissagregatorFromString(),
                                    (pc) -> WkVariableSizeByteArraySrlzStructNode.newCore(minSize, maxSize, pc),
                                    this);
  }

  private static String aggragateByteArray(WkStringWithVariableBytesSrlzInputNode deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    WkByteArray wrapper = deserializingStringOp.bytes().field().get().firstOperation().get().result().get().deserialized().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static class VariableLengthBytesDissagregatorFromString
      extends ByteArrayFromStringDisaggregator<
                        WkStringWithVariableBytesSrlzOutputNode,
                        WkVariableSizeByteArraySrlzStructNode>
  {

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      WkStringWithVariableBytesSrlzOutputNode stringSerializingOperation) {
      return stringSerializingOperation.settings().getOptionalLength();
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<WkStringWithVariableBytesSrlzInputNode, WkStringWithVariableBytesSrlzOutputNode, WkVariableSizeByteArraySrlzStructNode>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> rxClass() {
    return String.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?>
  makeTester(
    Predicate<? super WkStringWithVariableBytesSrlzInputNode> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<WkStringWithVariableBytesSrlzInputNode, WkStringWithVariableBytesSrlzOutputNode, WkVariableSizeByteArraySrlzStructNode>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}
