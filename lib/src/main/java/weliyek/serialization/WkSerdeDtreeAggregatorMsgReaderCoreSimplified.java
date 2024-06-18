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

public class WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                        X,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeDtreeAggregatorStructDefinition<X>,
                        XO extends WkSerdeDtreeAggregatorMsgReader<
                                        X,
                                        XS,
                                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<X>,
                                        XD>>
    extends WkSerdeDtreeAggregatorMsgReaderCore<
                        X,
                        XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<X>,
                        XD,
                        WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<X,XS,XD,XO,?,?,?,? extends XD>,
                        XO,
                        WkSerdeDtreeAggregatorMsgReaderCoreSimplified<X,XS,XD,XO>,
                        WkSerdeDtreeBytestreamInputBase<?>>
{

  public WkSerdeDtreeAggregatorMsgReaderCoreSimplified(
    int index,
    XS settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<X,XS,XD,XO,?,?,?,? extends XD> definitionCore,
    XO body) {
    super(index, settings, parentBytestream, readerFieldCore, definitionCore, body);
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
  protected WkSerdeDtreeAggregatorMsgReaderCoreSimplified<X,XS,XD,XO> getThis() {
    return this;
  }

}
