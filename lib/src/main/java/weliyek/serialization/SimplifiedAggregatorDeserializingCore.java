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

public class SimplifiedAggregatorDeserializingCore<
                        X,
                        XS extends WkSzOperationSettings,
                        XD extends WkSzAggregatorDefinition<X,?>,
                        XO extends WkSzAggregatorReader<
                                        X,
                                        XS,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<X>,
                                        XD>>
    extends WkSzAggregatorReaderCore<
                        X,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<X>,
                        XD,
                        XO,
                        SimplifiedAggregatorDeserializingCore<X,XS,XD,XO>,
                        WkSzInputBytestreamBase<?>,
                        SimplifiedAggregatorCore<X,XS,XD,XO,?,?,?,? extends XD>>
{

  public SimplifiedAggregatorDeserializingCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<X,?,XD,?,?,?> deserializingFieldCore,
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
