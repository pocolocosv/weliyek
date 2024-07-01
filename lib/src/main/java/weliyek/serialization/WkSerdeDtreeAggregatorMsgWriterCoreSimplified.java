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

public class WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        YO extends WkSerdeDtreeAggregatorMsgWriter<
                                        T,YS,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD>>
    extends WkSerdeDtreeAggregatorMsgWriterCore<
                        T,
                        YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YD,
                        WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T,?,?,?,YS,YD,YO,? extends YD>,
                        YO,
                        WkSerdeDtreeAggregatorMsgWriterCoreSimplified<T,YS,YD,YO>,
                        WkSerdeDtreeBytestreamOutputBase<?>>
{

  public WkSerdeDtreeAggregatorMsgWriterCoreSimplified(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<T,YS,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      writerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T, ?, ?, ?, YS, YD, YO, ? extends YD> definitionCore,
    YO body) {
    super(index, writerFieldCore, definitionCore, body);
  }

  @Override
  protected void onAggregatorInitialization() {
    this.definitionCore().onOutputInitializing.accept(getThis());
  }

  @Override
  protected WkSerdeDtreeAggregatorMsgWriterCoreSimplified<T,YS,YD,YO> getThis() {
    return this;
  }

}
