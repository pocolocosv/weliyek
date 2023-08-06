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
package weliyek.amat.basic.aggregator;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.BasicReadingRuntime;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.BasicWritingRuntime;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WritingRuntimeControl;

public class SimplifiedAggregatorCore<
                        T,
                        XS extends OperationSettings,
                        XD extends WkSzAggregatorDefinition<T,?>,
                        XO extends WkSzAggregatorReader<
                                        T,
                                        XS,
                                        DeserializingRuntime<WkSzInputBytestream>,
                                        DeserializingResult<T>,
                                        XD>,
                        YS extends OperationSettings,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<
                                        T,
                                        YS,
                                        SerializingRuntime<WkSzOutputBytestream>,
                                        SerializingResult,
                                        YD>,
                        D extends WkSzAggregatorDefinition<T,XO>>
    extends WkSzAggregatorDefinitionCore<
                        T,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        ReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<?>,
                          DeserializingRuntime<WkSzInputBytestream>>,
                        DeserializingResult<T>,
                        XD, XO,
                        WkSzInputBytestreamBase<?>,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<?>,
                          SerializingRuntime<WkSzOutputBytestream>>,
                        SerializingResult,
                        YD, YO,
                        WkSzOutputBytestreamBase<?>,
                        D,
                        SimplifiedAggregatorCore<T,XS,XD,XO,YS,YD,YO,D>>
{

  final Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onInitializing;
  final Function<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>, T> onFullSerializing;
  final Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onSkippedDeserializing;

  public SimplifiedAggregatorCore(
    PacketInputFieldReadingFactory<T, XS, XD, SimplifiedAggregatorCore<T, XS, XD, XO, YS, YD, YO, D>, XO, WkSzInputBytestreamBase<?>> deserializerFactory,
    PacketOutputFieldWritingFactory<T, YS, YD, SimplifiedAggregatorCore<T, XS, XD, XO, YS, YD, YO, D>, YO, WkSzOutputBytestreamBase<?>> serializerFactory,
    WkSzStructComponentCoreBase<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> componentCore,
    Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onInitializing,
    Function<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>, T> onFullDeserializing,
    Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onSkippedDeserializing,
    D body,
    Class<T> serializableClass) {
    super(
          componentCore,
          BasicReadingRuntime::new,
          BasicReadingResult::new,
          deserializerFactory,
          BasicWritingRuntime::new,
          BasicWritingResult::empty,
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
