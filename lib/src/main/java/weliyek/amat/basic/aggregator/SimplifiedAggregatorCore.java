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
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.BasicWritingRuntime;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.serialization.bytestream.InputBytestream;
import weliyek.serialization.bytestream.InputBytestreamGeneralBase;
import weliyek.serialization.bytestream.OutputBytestream;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public class SimplifiedAggregatorCore<
                        T,
                        XS extends OperationSettings,
                        XD extends WkSzAggregatorDefinition<T,?>,
                        XO extends WkSzAggregatorReader<
                                        T,
                                        XS,
                                        DeserializingRuntime<InputBytestream>,
                                        DeserializingResult<T>,
                                        XD>,
                        YS extends OperationSettings,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<
                                        T,
                                        YS,
                                        SerializingRuntime<OutputBytestream>,
                                        SerializingResult,
                                        YD>,
                        D extends WkSzAggregatorDefinition<T,XO>>
    extends WkSzAggregatorDefinitionCore<
                        T,
                        XS,
                        InputBytestream,
                        InputBytestreamGeneralBase<? extends InputBytestream>,
                        ReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<?>,
                          DeserializingRuntime<InputBytestream>>,
                        DeserializingResult<T>,
                        XD, XO,
                        InputBytestreamGeneralBase<?>,
                        YS,
                        OutputBytestream,
                        OutputBytestreamGeneralBase<? extends OutputBytestream>,
                        WritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<?>,
                          SerializingRuntime<OutputBytestream>>,
                        SerializingResult,
                        YD, YO,
                        OutputBytestreamGeneralBase<?>,
                        D,
                        SimplifiedAggregatorCore<T,XS,XD,XO,YS,YD,YO,D>>
{

  final Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onInitializing;
  final Function<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>, T> onFullSerializing;
  final Consumer<? super SimplifiedAggregatorDeserializingCore<T,XS,XD,XO>> onSkippedDeserializing;

  public SimplifiedAggregatorCore(
    PacketInputFieldReadingFactory<T, XS, XD, SimplifiedAggregatorCore<T, XS, XD, XO, YS, YD, YO, D>, XO, InputBytestreamGeneralBase<?>> deserializerFactory,
    PacketOutputFieldWritingFactory<T, YS, YD, SimplifiedAggregatorCore<T, XS, XD, XO, YS, YD, YO, D>, YO, OutputBytestreamGeneralBase<?>> serializerFactory,
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
