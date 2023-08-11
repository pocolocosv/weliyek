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

public class SimplifiedAggregatorCore<
                        T,
                        XS extends WkSzOperationSettings,
                        XD extends WkSzAggregatorDefinition<T,?>,
                        XO extends WkSzAggregatorReader<
                                        T,
                                        XS,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD>,
                        YS extends WkSzOperationSettings,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<
                                        T,
                                        YS,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD>,
                        D extends WkSzAggregatorDefinition<T,XO>>
    extends WkSzAggregatorDefinitionCore<
                        T,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<?>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XD, XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<?>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YD, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        SimplifiedAggregatorCore<T,XS,XD,XO,YS,YD,YO,D>>
{

  final Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onInitializing;
  final Function<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>, T> onFullSerializing;
  final Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onSkippedDeserializing;

  public SimplifiedAggregatorCore(
    WkSzPacketReaderOperationCoreFactory<T, XS, XD, SimplifiedAggregatorCore<T, XS, XD, XO, YS, YD, YO, D>, XO, WkSzInputBytestreamBase<?>> deserializerFactory,
    WkSzPacketWriterOperationCoreFactory<T, YS, YD, SimplifiedAggregatorCore<T, XS, XD, XO, YS, YD, YO, D>, YO, WkSzOutputBytestreamBase<?>> serializerFactory,
    WkSzStructComponentCoreBase<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> componentCore,
    Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onInitializing,
    Function<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>, T> onFullDeserializing,
    Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onSkippedDeserializing,
    D body,
    Class<T> serializableClass) {
    super(
          componentCore,
          WkSzBasicReadingRuntime::new,
          WkSzBasicReadingResult::new,
          deserializerFactory,
          WkSzBasicWritingRuntime::new,
          WkSzBasicWritingResult::empty,
          serializerFactory,
          body,
          serializableClass);
    this.onInitializing = Objects.requireNonNull(onInitializing);
    this.onFullSerializing = Objects.requireNonNull(onFullDeserializing);
    this.onSkippedDeserializing = Objects.requireNonNull(onSkippedDeserializing);
  }

  @Override
  protected SimplifiedAggregatorCore<T, XS, XD, XO, YS, YD, YO, D> getThis() {
    return this;
  }

}