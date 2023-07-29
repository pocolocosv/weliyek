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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;

public class SimplifiedAggregatorSerializingCore<
                        T,
                        YS extends OperationSettings,
                        YD extends AggregatorDefinition<T,?>,
                        YO extends AggregatorWriting<
                                        T,YS,
                                        SerializingRuntime<OutputBytestream>,
                                        SerializingResult,
                                        YD>>
    extends AggregatorWritingCore<
                        T,
                        YS,
                        OutputBytestream,
                        OutputBytestreamGeneralBase<? extends OutputBytestream>,
                        SerializingRuntime<OutputBytestream>,
                        WritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<? extends OutputBytestream>,
                          SerializingRuntime<OutputBytestream>>,
                        SerializingResult,
                        YD,
                        YO,
                        SimplifiedAggregatorSerializingCore<T,YS,YD,YO>,
                        OutputBytestreamGeneralBase<?>,
                        SimplifiedAggregatorCore<T,?,?,?,YS,YD,YO,?>>
{

  private Consumer<? super SimplifiedAggregatorSerializingCore<T,YS,YD,YO>> onInitializing;

  public SimplifiedAggregatorSerializingCore(
    int index,
    T serializable,
    YS settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    SerializingFieldCore<T,?,YD,?,?,?> serializingfieldCore,
    SimplifiedAggregatorCore<T,?,?,?,YS,YD,YO,?> definitionCore,
    YO body,
    Consumer<? super SimplifiedAggregatorSerializingCore<T,YS,YD,YO>> onInitializing) {
    super(index, serializable, settings, parentBytestream, serializingfieldCore, definitionCore, body);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onAggregatorInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected SimplifiedAggregatorSerializingCore<T,YS,YD,YO> getThis() {
    return this;
  }

}
