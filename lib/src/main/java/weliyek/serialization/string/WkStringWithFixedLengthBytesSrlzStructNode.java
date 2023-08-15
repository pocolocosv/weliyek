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
import weliyek.serialization.string.WkStringFromBytesSrlzStructDefinitionFrameNodeCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkFixedSizeByteArraySrlzStructNode;
import weliyek.util.array.WkFixedSizeByteArraySrlzInputNode;
import weliyek.util.array.WkFixedSizeByteArraySrlzOutputNode;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;

/**
 * Packet structure and data for handling fixed length bytes array. The serialization
 * handles padding and truncation of the bytes array in case the original String
 * object's own bytes array is not same length. Uses zero as padding for serializing
 * and deserializing.
 */
public class WkStringWithFixedLengthBytesSrlzStructNode
    implements WkStringFromBytesSrlzStructDefinitionFrameNode<
                        WkStringWithFixedLengthBytesSrlzInputNode,
                        WkStringWithFixedLengthBytesSrlzOutputNode,
                        WkFixedSizeByteArraySrlzStructNode>
{

  public static WkSrlzStruct<
                      String,
                      WkSettingsSrlzPacketOperationData,
                      WkStringWithFixedLengthBytesSrlzStructNode,
                      WkStringWithFixedLengthBytesSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,
                      WkStringWithFixedLengthBytesSrlzStructNode,
                      WkStringWithFixedLengthBytesSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkStringWithFixedLengthBytesSrlzStructNode>
  newStruct(
    String label,
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      (pc) -> WkStringWithFixedLengthBytesSrlzStructNode.newCore(
                                    bytesLabel, expectedSize, defaultCharset, pc),
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  public static WkSrlzStructDefinitionFrameNodeCore<
                      String,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkStringWithFixedLengthBytesSrlzStructNode,
                      WkStringWithFixedLengthBytesSrlzInputNode,
                      WkSzInputBytestreamBase<?>,
                      WkSettingsSrlzPacketOperationData,?,?,
                      WkStringWithFixedLengthBytesSrlzStructNode,
                      WkStringWithFixedLengthBytesSrlzOutputNode,
                      WkSzOutputBytestreamBase<?>,
                      WkStringWithFixedLengthBytesSrlzStructNode,?>
  newCore(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkStringWithFixedLengthBytesSrlzStructNode(bytesLabel, expectedSize, defaultCharset, componentCore).definitionCore;
  }

  private final SimplifiedStringFromBytesCore<
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithFixedLengthBytesSrlzInputNode,
                        WkStringWithFixedLengthBytesSrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithFixedLengthBytesSrlzOutputNode,
                        WkStringWithFixedLengthBytesSrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzInputNode,
                        WkFixedSizeByteArraySrlzStructNode,
                        WkSettingsSrlzPacketOperationData,
                        WkFixedSizeByteArraySrlzOutputNode,
                        WkFixedSizeByteArraySrlzStructNode,
                        WkFixedSizeByteArraySrlzStructNode,
                        WkStringWithFixedLengthBytesSrlzStructNode> definitionCore;

  private WkStringWithFixedLengthBytesSrlzStructNode(
    String bytesLabel,
    int expectedSize,
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {

    this.definitionCore = new SimplifiedStringFromBytesCore<>(
                                  defaultCharset,
                                  componentCore,
                                  (i,xs,axb,xpc,dc) -> new WkStringWithFixedLengthBytesSrlzInputNode(i,xs,axb,xpc,dc).operationCore,
                                  (i,y,ys,ayb,ypc,dc) -> new WkStringWithFixedLengthBytesSrlzOutputNode(i,y,ys,ayb,ypc,dc).operationCore,
                                  bytesLabel,
                                  WkSettingsSrlzPacketOperationData::none,
                                  WkStringWithFixedLengthBytesSrlzStructNode::aggragateByteArray,
                                  WkSettingsSrlzPacketOperationData::none,
                                  new FixedLengthBytesDisaggregatorFromString(expectedSize),
                                  (pc) -> WkFixedSizeByteArraySrlzStructNode.newCore(expectedSize, pc),
                                  this);
  }

  private static String aggragateByteArray(WkStringWithFixedLengthBytesSrlzInputNode deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    WkByteArray wrapper = deserializingStringOp.bytes().field().get().firstOperation().get().result().get().deserialized().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static class FixedLengthBytesDisaggregatorFromString
      extends ByteArrayFromStringDisaggregator<
                        WkStringWithFixedLengthBytesSrlzOutputNode,
                        WkFixedSizeByteArraySrlzStructNode>
  {

    private final Optional<Integer> fixedLength;

    FixedLengthBytesDisaggregatorFromString(int fixedLength) {
      this.fixedLength = Optional.of(Integer.valueOf(fixedLength));
    }

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      WkStringWithFixedLengthBytesSrlzOutputNode stringSerializingOperation) {
      return this.fixedLength;
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<WkStringWithFixedLengthBytesSrlzInputNode, WkStringWithFixedLengthBytesSrlzOutputNode, WkFixedSizeByteArraySrlzStructNode>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?,?,?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> rxClass() {
    return String.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?,?,?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public WkSrlzPacketNodePredicate<?, ?>
  makeTester(
    Predicate<? super WkStringWithFixedLengthBytesSrlzInputNode> test,
    String description) {
    return this.definitionCore.makeTester(test, description);
  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<WkStringWithFixedLengthBytesSrlzInputNode, WkStringWithFixedLengthBytesSrlzOutputNode, WkFixedSizeByteArraySrlzStructNode>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

}
