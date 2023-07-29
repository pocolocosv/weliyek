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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingRuntimeControl;

public class SimplifiedAggregatorDeserializingCore<
                        X,
                        XS extends OperationSettings,
                        XD extends AggregatorDefinition<X,?>,
                        XO extends AggregatorReading<
                                        X,
                                        XS,
                                        DeserializingRuntime<InputBytestream>,
                                        DeserializingResult<X>,
                                        XD>>
    extends AggregatorReadingCore<
                        X,
                        XS,
                        InputBytestream,
                        InputBytestreamGeneralBase<? extends InputBytestream>,
                        DeserializingRuntime<InputBytestream>,
                        ReadingRuntimeControl<
                          InputBytestream,
                          InputBytestreamGeneralBase<? extends InputBytestream>,
                          DeserializingRuntime<InputBytestream>>,
                        DeserializingResult<X>,
                        XD,
                        XO,
                        SimplifiedAggregatorDeserializingCore<X,XS,XD,XO>,
                        InputBytestreamGeneralBase<?>,
                        SimplifiedAggregatorCore<X,XS,XD,XO,?,?,?,? extends XD>>
{

  public SimplifiedAggregatorDeserializingCore(
    int index,
    XS settings,
    InputBytestreamGeneralBase<?> parentBytestream,
    DeserializingFieldCore<X,?,XD,?,?,?> deserializingFieldCore,
    SimplifiedAggregatorCore<X,XS,XD,XO,?,?,?,? extends XD> definitionCore,
    XO body) {
    super(index, settings, parentBytestream, deserializingFieldCore, definitionCore, body);
  }

  @Override
  protected void onAggregatorReadingInitialization() {
    this.definitionCore().onInitializing.accept(getThis());
  }

  @Override
  protected X onFullReadingCompletion() {
    return this.definitionCore().onFullSerializing.apply(getThis());
  }

  @Override
  protected void onPartialReadingCompletion() {
    this.definitionCore().onSkippedDeserializing.accept(getThis());
  }

  @Override
  protected SimplifiedAggregatorDeserializingCore<X,XS,XD,XO> getThis() {
    return this;
  }

}
