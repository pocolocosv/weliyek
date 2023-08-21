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
import java.util.function.IntFunction;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNode;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.string.WkStringFromBytesSrlzStructDefinitionFrameNodeCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkDynamicByteArraySrlzInputNode;
import weliyek.util.array.WkDynamicByteArraySrlzOutputNode;
import weliyek.util.array.WkDynamicByteArraySrlzStructNode;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;

public class WkStringWithDynamicBytesSrlzStructNode<
                        ZT extends Number,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT>,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT>,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                        ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT>>
    implements WkStringFromBytesSrlzStructDefinitionFrameNode<
                        WkStringWithDynamicBytesSrlzInputNode<ZT,ZXD,ZXO>,
                        WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
{

  public static <ZX extends Number,
                 ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX>,
                 ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                 ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX>,
                 ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZX,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                 ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX>>
  WkSrlzStruct<String,
                  WkSettingsSrlzPacketOperationData,
                  WkStringWithDynamicBytesSrlzStructNode<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  WkStringWithDynamicBytesSrlzInputNode<ZX,ZXD,ZXO>,
                  WkSzInputBytestreamBase<?>,
                  WkSettingsSrlzPacketOperationData,
                  WkStringWithDynamicBytesSrlzStructNode<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  WkStringWithDynamicBytesSrlzOutputNode<ZX,ZYD,ZYO>,
                  WkSzOutputBytestreamBase<?>,
                  WkStringWithDynamicBytesSrlzStructNode<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>
  newStruct(
    String label,
    String bytesLabel,
    String sizeLabel,
    String arrayLabel,
    int minLength,
    int maxLength,
    Charset defaultCharset,
    IntFunction<ZX> wrapperSizeGetter,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZX,WkSettingsSrlzPacketOperationData,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSettingsSrlzPacketOperationData,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeDefinitionFactory) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                  label,
                  (pc) -> WkStringWithDynamicBytesSrlzStructNode.newCore(
                                pc, bytesLabel, sizeLabel, arrayLabel, minLength, maxLength, defaultCharset, wrapperSizeGetter, sizeDefinitionFactory),
                  WkSzCountingInputBytestream::new,
                  WkSzCountingOutputBytestream::new);
  }

  public static <ZX extends Number,
                 ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX>,
                 ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                 ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX>,
                 ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZX,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                 ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX>>
  WkSrlzStructDefinitionFrameNodeCore<
                        String,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkStringWithDynamicBytesSrlzStructNode<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        WkStringWithDynamicBytesSrlzInputNode<ZX,ZXD,ZXO>,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkStringWithDynamicBytesSrlzStructNode<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        WkStringWithDynamicBytesSrlzOutputNode<ZX,ZYD,ZYO>,
                        WkSzOutputBytestreamBase<?>,
                        WkStringWithDynamicBytesSrlzStructNode<ZX,ZXD,ZXO,ZYD,ZYO,ZD>,?>
  newCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String bytesLabel,
    String sizeLabel,
    String arrayLabel,
    int minLength,
    int maxLength,
    Charset defaultCharset,
    IntFunction<ZX> wrapperSizeGetter,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZX,WkSettingsSrlzPacketOperationData,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSettingsSrlzPacketOperationData,
      ZYD,ZYO,WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeDefinitionFactory) {
    return new WkStringWithDynamicBytesSrlzStructNode<ZX,ZXD,ZXO,ZYD,ZYO,ZD>(
                        defaultCharset,
                        componentCore,
                        bytesLabel,
                        sizeLabel,
                        wrapperSizeGetter,
                        arrayLabel,
                        minLength,
                        maxLength,
                        sizeDefinitionFactory).definitionCore;
  }

  private final SimplifiedStringFromBytesCore<
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithDynamicBytesSrlzInputNode<ZT,ZXD,ZXO>,
                        WkStringWithDynamicBytesSrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSettingsSrlzPacketOperationData,
                        WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
                        WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSettingsSrlzPacketOperationData,
                        WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSettingsSrlzPacketOperationData,
                        WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
                        WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                        WkStringWithDynamicBytesSrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
                            definitionCore;

  private WkStringWithDynamicBytesSrlzStructNode(
    Charset defaultCharset,
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    String bytesLabel,
    String szieLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT,WkSettingsSrlzPacketOperationData,ZXD,ZXO,WkSzInputBytestreamBase<? extends WkSzInputBytestream>,WkSettingsSrlzPacketOperationData,ZYD,ZYO,
      WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,ZD> sizeDefinitionFactory) {
    this.definitionCore = new SimplifiedStringFromBytesCore<
                                  WkSettingsSrlzPacketOperationData,
                                  WkStringWithDynamicBytesSrlzInputNode<ZT,ZXD,ZXO>,
                                  WkStringWithDynamicBytesSrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSettingsSrlzPacketOperationData,
                                  WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
                                  WkStringWithDynamicBytesSrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSettingsSrlzPacketOperationData,
                                  WkDynamicByteArraySrlzInputNode<ZT,ZXO,ZXD>,
                                  WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSettingsSrlzPacketOperationData,
                                  WkDynamicByteArraySrlzOutputNode<ZT,ZYO,ZYD>,
                                  WkDynamicByteArraySrlzStructNode<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                                  WkStringWithDynamicBytesSrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                      defaultCharset,
                                      componentCore,
                                      (i,xs,axb,xpc,dc) -> new WkStringWithDynamicBytesSrlzInputNode<ZT,ZXD,ZXO>(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>(i,y,ys,ayb,ypc,dc).operationCore,
                                      bytesLabel,
                                      WkSettingsSrlzPacketOperationData::none,
                                      WkStringWithDynamicBytesSrlzStructNode::aggragateByteArray,
                                      WkSettingsSrlzPacketOperationData::none,
                                      new BytesFromDynamicStringDisaggregator<ZT,ZYD,ZYO>(),
                                      (pc) -> WkDynamicByteArraySrlzStructNode.<ZT,ZXD,ZXO,ZYD,ZYO,ZD>newCore(
                                          pc, szieLabel, wrapperSizeGetter, arrayLabel,
                                          minLength, maxLength, sizeDefinitionFactory),
                                      this);
  }

  private static <ZX extends Number,
                  ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX>,
                  ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>>
  String aggragateByteArray(WkStringWithDynamicBytesSrlzInputNode<ZX,ZXD,ZXO> deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    WkByteArray wrapper = deserializingStringOp.bytes().field().get().firstOperation().get().result().get().serializable().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static
  class BytesFromDynamicStringDisaggregator<
                        ZY extends Number,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZY>,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZY,WkSettingsSrlzPacketOperationData,?,?,ZYD>>
      extends ByteArrayFromStringDisaggregator<
                        WkStringWithDynamicBytesSrlzOutputNode<ZY,ZYD,ZYO>,
                        WkDynamicByteArraySrlzStructNode<ZY,?,?,ZYD,ZYO,? extends ZYD>>
  {

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      WkStringWithDynamicBytesSrlzOutputNode<ZY,ZYD,ZYO> stringSerializingOperation) {
      return Optional.empty();
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<
              WkStringWithDynamicBytesSrlzInputNode<ZT,ZXD,ZXO>,
              WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
              WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> serializableClass() {
    return String.class;
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
  WkSrlzStructSubcomponentFrameNode<
            WkStringWithDynamicBytesSrlzInputNode<ZT,ZXD,ZXO>,
            WkStringWithDynamicBytesSrlzOutputNode<ZT,ZYD,ZYO>,
            WkDynamicByteArraySrlzStructNode<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  bytes() {
    return this.definitionCore.bytes();
  }

  @Override
  public Charset charset() {
    return this.definitionCore.charset();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
