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

import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.string.WkSerdeStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;
import weliyek.util.array.WkSerdeDynamicByteArray;
import weliyek.util.array.WkSerdeDynamicByteArrayReader;
import weliyek.util.array.WkSerdeDynamicByteArrayWriter;

public class WkSerdeStringDynamicBytes<
                        ZT extends Number,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                        ZD extends WkSerdeDtreeNumberStructDefinition<ZT>>
    implements WkSerdeStringFromBytesDefinition<
                        WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                        WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
{

  public static <ZT extends Number,
                 ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberStructDefinition<ZT>>
  WkSerdeDtreeStruct<String,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                  WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                  WkSerdeDtreeBytestreamInputBase<?>,
                  WkSerdeDtreeOperationSettings,
                  WkSerdeStringDynamicBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                  WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                  WkSerdeDtreeBytestreamOutputBase<?>,
                  WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  newStruct(
    String label,
    String bytesLabel,
    String sizeLabel,
    String arrayLabel,
    int minLength,
    int maxLength,
    Charset defaultCharset,
    IntFunction<ZT> wrapperSizeGetter,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT, WkSerdeDtreeOperationSettings, ZXO, WkSerdeDtreeBytestreamInputBase<?>,
      WkSerdeDtreeOperationSettings, ZYO, WkSerdeDtreeBytestreamOutputBase<?>,
      ZD> sizeDefinitionFactory) {
    return new WkSerdeDtreeStructCore<>(
                  label,
                  (pc) -> WkSerdeStringDynamicBytes.newCore(
                                pc, bytesLabel, sizeLabel, arrayLabel, minLength, maxLength, defaultCharset, wrapperSizeGetter, sizeDefinitionFactory),
                  WkSerdeDtreeBytestreamCountingInputStream::new,
                  WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static <ZT extends Number,
                 ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZXO extends WkSerdeDtreeNumberMsgReader<ZT,WkSerdeDtreeOperationSettings,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                 ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,WkSerdeDtreeOperationSettings,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberStructDefinition<ZT>>
  WkSerdeStringFromBytesDefinitionCoreSimplified<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                        WkSerdeStringDynamicBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                        WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  newCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    String bytesLabel,
    String sizeLabel,
    String arrayLabel,
    int minLength,
    int maxLength,
    Charset defaultCharset,
    IntFunction<ZT> wrapperSizeGetter,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT, WkSerdeDtreeOperationSettings, ZXO, WkSerdeDtreeBytestreamInputBase<?>,
      WkSerdeDtreeOperationSettings, ZYO, WkSerdeDtreeBytestreamOutputBase<?>,
      ZD> sizeDefinitionFactory) {
    return new WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>(
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

  private final WkSerdeStringFromBytesDefinitionCoreSimplified<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                        WkSerdeStringDynamicBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                        WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
                            definitionCore;

  private WkSerdeStringDynamicBytes(
    Charset defaultCharset,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    String bytesLabel,
    String szieLabel,
    IntFunction<ZT> wrapperSizeGetter,
    String arrayLabel,
    int minLength,
    int maxLength,
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ZT, WkSerdeDtreeOperationSettings, ZXO, WkSerdeDtreeBytestreamInputBase<?>,
      WkSerdeDtreeOperationSettings, ZYO, WkSerdeDtreeBytestreamOutputBase<?>,
      ZD> sizeDefinitionFactory) {
    this.definitionCore = new WkSerdeStringFromBytesDefinitionCoreSimplified<
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                                  WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                                  WkSerdeStringDynamicBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSerdeDtreeOperationSettings,
                                  WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                                  WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                                  WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                      defaultCharset,
                                      componentCore,
                                      (i,xs,axb,xpc,dc) -> new WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>(i,y,ys,ayb,ypc,dc).operationCore,
                                      bytesLabel,
                                      WkSerdeDtreeOperationSettings::none,
                                      WkSerdeStringDynamicBytes::aggragateByteArray,
                                      WkSerdeDtreeOperationSettings::none,
                                      new BytesFromDynamicStringDisaggregator<ZT,ZYD,ZYO>(),
                                      (pc) -> WkSerdeDynamicByteArray.<ZT,ZXD,ZXO,ZYD,ZYO,ZD>newCore(
                                          pc, szieLabel, wrapperSizeGetter, arrayLabel,
                                          minLength, maxLength, sizeDefinitionFactory),
                                      this);
  }

  private static <ZX extends Number,
                  ZXD extends WkSerdeDtreeNumberStructDefinition<ZX>,
                  ZXO extends WkSerdeDtreeNumberMsgReader<ZX,WkSerdeDtreeOperationSettings,?,?,ZXD>>
  String aggragateByteArray(WkSerdeStringDynamicBytesReader<ZX,ZXD,ZXO> deserializingStringOp) {
    Charset charset = deserializingStringOp.charset();
    ContigousIntsCounter zeroPaddingCounter = new ContigousIntsCounter(0);
    WkByteArray wrapper = deserializingStringOp.bytes().get().firstOperation().get().result().get().serializable().get();
    wrapper.iterateAsIntsBackwardsWhileTrue(zeroPaddingCounter);
    int zeroPaddingLen = zeroPaddingCounter.count();
    int strBytesLen = wrapper.getLength() - zeroPaddingLen;
    return wrapper.convertToString(charset, 0, strBytesLen);
  }

  public static
  class BytesFromDynamicStringDisaggregator<
                        ZY extends Number,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZY>,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZY,WkSerdeDtreeOperationSettings,?,?,ZYD>>
      extends ByteArrayFromStringDisaggregator<
                        WkSerdeStringDynamicBytesWriter<ZY,ZYD,ZYO>,
                        WkSerdeDynamicByteArray<ZY,?,?,ZYD,ZYO,? extends ZYD>>
  {

    @Override
    protected Optional<Integer> requestedPrimitiveArrayLength(
      WkSerdeStringDynamicBytesWriter<ZY,ZYD,ZYO> stringSerializingOperation) {
      return Optional.empty();
    }

    @Override
    protected int requestedPrimitiveArrayPadding() {
      return 0;
    }

  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeStringDynamicBytesReader<ZT, ZXD, ZXO>, WkSerdeStringDynamicBytesWriter<ZT, ZYD, ZYO>, WkSerdeDynamicByteArray<ZT, ZXD, ZXO, ZYD, ZYO, ZD>>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> serializableClass() {
    return String.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
  WkSerdeDtreeStructSubfield<WkSerdeStringDynamicBytesReader<ZT, ZXD, ZXO>, WkSerdeStringDynamicBytesWriter<ZT, ZYD, ZYO>, WkSerdeDynamicByteArray<ZT, ZXD, ZXO, ZYD, ZYO, ZD>>
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
