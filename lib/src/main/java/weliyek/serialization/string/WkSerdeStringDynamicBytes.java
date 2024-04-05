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
import weliyek.serialization.WkSerdeDtreeNodeStructDefinitionCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkSerdeDtreeNumberReader;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.string.WkSerdeStringFromBytesDefinitionCore.ByteArrayFromStringDisaggregator;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDynamicByteArrayReader;
import weliyek.util.array.WkSerdeDynamicByteArrayWriter;
import weliyek.util.array.WkSerdeDynamicByteArray;
import weliyek.util.array.WkPrimitiveArray.ContigousIntsCounter;

public class WkSerdeStringDynamicBytes<
                        ZT extends Number,
                        ZXD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ZXO extends WkSerdeDtreeNumberReader<ZT,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ZYO extends WkSerdeDtreeNumberWriter<ZT,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                        ZD extends WkSerdeDtreeNumberDefinition<ZT>>
    implements WkSerdeStringFromBytesDefinition<
                        WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                        WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
{

  public static <ZX extends Number,
                 ZXD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZXO extends WkSerdeDtreeNumberReader<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZYO extends WkSerdeDtreeNumberWriter<ZX,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberDefinition<ZX>>
  WkSrlzStruct<String,
                  WkSettingsSrlzPacketOperationData,
                  WkSerdeStringDynamicBytes<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                  WkSerdeStringDynamicBytesReader<ZX,ZXD,ZXO>,
                  WkSzInputBytestreamBase<?>,
                  WkSettingsSrlzPacketOperationData,
                  WkSerdeStringDynamicBytes<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                  WkSerdeStringDynamicBytesWriter<ZX,ZYD,ZYO>,
                  WkSzOutputBytestreamBase<?>,
                  WkSerdeStringDynamicBytes<ZX,ZXD,ZXO,ZYD,ZYO,ZD>>
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
                  (pc) -> WkSerdeStringDynamicBytes.newCore(
                                pc, bytesLabel, sizeLabel, arrayLabel, minLength, maxLength, defaultCharset, wrapperSizeGetter, sizeDefinitionFactory),
                  WkSzCountingInputBytestream::new,
                  WkSzCountingOutputBytestream::new);
  }

  public static <ZX extends Number,
                 ZXD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZXO extends WkSerdeDtreeNumberReader<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>,
                 ZYD extends WkSerdeDtreeNumberDefinition<ZX>,
                 ZYO extends WkSerdeDtreeNumberWriter<ZX,WkSettingsSrlzPacketOperationData,?,?,ZYD>,
                 ZD extends WkSerdeDtreeNumberDefinition<ZX>>
  WkSerdeDtreeNodeStructDefinitionCore<
                        String,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSerdeStringDynamicBytes<ZX,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSerdeStringDynamicBytesReader<ZX,ZXD,ZXO>,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkSerdeStringDynamicBytes<ZX,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeStringDynamicBytesWriter<ZX,ZYD,ZYO>,
                        WkSzOutputBytestreamBase<?>,
                        WkSerdeStringDynamicBytes<ZX,ZXD,ZXO,ZYD,ZYO,ZD>,?>
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
    return new WkSerdeStringDynamicBytes<ZX,ZXD,ZXO,ZYD,ZYO,ZD>(
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
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                        WkSerdeStringDynamicBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                        WkSettingsSrlzPacketOperationData,
                        WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                        WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                        WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                        WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
                            definitionCore;

  private WkSerdeStringDynamicBytes(
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
    this.definitionCore = new WkSerdeStringFromBytesDefinitionCoreSimplified<
                                  WkSettingsSrlzPacketOperationData,
                                  WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
                                  WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSettingsSrlzPacketOperationData,
                                  WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
                                  WkSerdeStringDynamicBytes<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSettingsSrlzPacketOperationData,
                                  WkSerdeDynamicByteArrayReader<ZT,ZXO,ZXD>,
                                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,?,?,? extends ZXD>,
                                  WkSettingsSrlzPacketOperationData,
                                  WkSerdeDynamicByteArrayWriter<ZT,ZYO,ZYD>,
                                  WkSerdeDynamicByteArray<ZT,?,?,ZYD,ZYO,? extends ZYD>,
                                  WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>,
                                  WkSerdeStringDynamicBytes<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>(
                                      defaultCharset,
                                      componentCore,
                                      (i,xs,axb,xpc,dc) -> new WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>(i,xs,axb,xpc,dc).operationCore,
                                      (i,y,ys,ayb,ypc,dc) -> new WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>(i,y,ys,ayb,ypc,dc).operationCore,
                                      bytesLabel,
                                      WkSettingsSrlzPacketOperationData::none,
                                      WkSerdeStringDynamicBytes::aggragateByteArray,
                                      WkSettingsSrlzPacketOperationData::none,
                                      new BytesFromDynamicStringDisaggregator<ZT,ZYD,ZYO>(),
                                      (pc) -> WkSerdeDynamicByteArray.<ZT,ZXD,ZXO,ZYD,ZYO,ZD>newCore(
                                          pc, szieLabel, wrapperSizeGetter, arrayLabel,
                                          minLength, maxLength, sizeDefinitionFactory),
                                      this);
  }

  private static <ZX extends Number,
                  ZXD extends WkSerdeDtreeNumberDefinition<ZX>,
                  ZXO extends WkSerdeDtreeNumberReader<ZX,WkSettingsSrlzPacketOperationData,?,?,ZXD>>
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
                        ZYD extends WkSerdeDtreeNumberDefinition<ZY>,
                        ZYO extends WkSerdeDtreeNumberWriter<ZY,WkSettingsSrlzPacketOperationData,?,?,ZYD>>
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
  WkSerdeDtreeNodeStructComponentHandler<
              WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
              WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
              WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
  primitiveArray() {
    return this.definitionCore.primitiveArray();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

  @Override
  public Class<String> serializableClass() {
    return String.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public
  WkSerdeDtreeNodeStructComponentHandler<
            WkSerdeStringDynamicBytesReader<ZT,ZXD,ZXO>,
            WkSerdeStringDynamicBytesWriter<ZT,ZYD,ZYO>,
            WkSerdeDynamicByteArray<ZT,ZXD,ZXO,ZYD,ZYO,ZD>>
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
