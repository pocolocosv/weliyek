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
package weliyek.serialization;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XD extends WkAggregatorSrlzStructDefinitionFrameNode<T,?>,
                        XO extends WkAggregatorSrlzInputPacketDecoderFrameNode<
                                        T,
                                        XS,
                                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                                        WkDecodingResultSrlzPacketOperationData<T>,
                                        XD>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YD extends WkAggregatorSrlzStructDefinitionFrameNode<T,?>,
                        YO extends WkAggregatorSrlzOutputPacketEncoderFrameNode<
                                        T,
                                        YS,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkEncodingResultSrlzPacketOperationData,
                                        YD>,
                        D extends WkAggregatorSrlzStructDefinitionFrameNode<T,XO>>
    extends WkAggregatorSrlzStructDefinitionFrameNodeCore<
                        T,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkDecodingRuntimeSrlzPacketOperationCtrl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<?>,
                          WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>>,
                        WkDecodingResultSrlzPacketOperationData<T>,
                        XD, XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<?>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkEncodingResultSrlzPacketOperationData,
                        YD, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<T,XS,XD,XO,YS,YD,YO,D>>
{

  final Consumer<? super WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<T,XS,XD,XO>> onInitializing;
  final Function<? super WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<T,XS,XD,XO>, T> onFullSerializing;
  final Consumer<? super WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<T,XS,XD,XO>> onSkippedDeserializing;

  public WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore(
    WkSzPacketReaderOperationCoreFactory<T, XS, XD, WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<T, XS, XD, XO, YS, YD, YO, D>, XO, WkSzInputBytestreamBase<?>> deserializerFactory,
    WkSzPacketWriterOperationCoreFactory<T, YS, YD, WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<T, XS, XD, XO, YS, YD, YO, D>, YO, WkSzOutputBytestreamBase<?>> serializerFactory,
    WkSrlzStructComponentFrameNodeCore<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> componentCore,
    Consumer<? super WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<T,XS,XD,XO>> onInitializing,
    Function<? super WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<T,XS,XD,XO>, T> onFullDeserializing,
    Consumer<? super WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<T,XS,XD,XO>> onSkippedDeserializing,
    D body,
    Class<T> serializableClass) {
    super(
          componentCore,
          WkSimplifiedDecodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicDecodingResultSrlzPacketOperationData::new,
          deserializerFactory,
          WkSimplifiedEncodingRuntimeSrlzPacketOperationCtrl::new,
          WkBasicEncodingResultSrlzPacketOperationData::empty,
          serializerFactory,
          body,
          serializableClass);
    this.onInitializing = Objects.requireNonNull(onInitializing);
    this.onFullSerializing = Objects.requireNonNull(onFullDeserializing);
    this.onSkippedDeserializing = Objects.requireNonNull(onSkippedDeserializing);
  }

  @Override
  protected WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<T, XS, XD, XO, YS, YD, YO, D> getThis() {
    return this;
  }

}
