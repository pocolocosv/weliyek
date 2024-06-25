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

public class WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        XO extends WkSerdeDtreeAggregatorMsgReader<
                                        T,
                                        XS,
                                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        XD>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        YO extends WkSerdeDtreeAggregatorMsgWriter<
                                        T,
                                        YS,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD>,
                        D extends WkSerdeDtreeAggregatorStructDefinition<T>>
    extends WkSerdeDtreeAggregatorStructDefinitionCore<
                        T,
                        XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<?>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        XD,
                        WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T,XS,XD,XO,?,?,?,? extends XD>,
                        XO,
                        WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<?>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YD,
                        WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T,?,?,?,YS,YD,YO,? extends YD>,
                        YO,
                        WkSerdeDtreeAggregatorMsgWriterCoreSimplified<T,YS,YD,YO>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        D,
                        WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T,XS,XD,XO,YS,YD,YO,D>>
{

  final Consumer<? super WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO>> onInitializing;
  final Function<? super WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO>, T> onFullSerializing;
  final Consumer<? super WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO>> onSkippedDeserializing;
  final Consumer<? super WkSerdeDtreeAggregatorMsgWriterCoreSimplified<T,YS,YD,YO>> onOutputInitializing;

  public WkSerdeDtreeAggregatorStructDefinitionCoreSimplified(
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<XS, WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T, XS, XD, XO, ?, ?, ?, ? extends XD>, WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T, XS, XD, XO>, WkSerdeDtreeBytestreamInputBase<?>> readerFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<T, YS, WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T, ?, ?, ?, YS, YD, YO, ? extends YD>, WkSerdeDtreeAggregatorMsgWriterCoreSimplified<T, YS, YD, YO>, WkSerdeDtreeBytestreamOutputBase<?>> writerFactory,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore,
    Consumer<? super WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO>> onReadInit,
    Function<? super WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO>, T> onReadFull,
    Consumer<? super WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO>> onReadSkipped,
    Consumer<? super WkSerdeDtreeAggregatorMsgWriterCoreSimplified<T,YS,YD,YO>> onWriteInit,
    D body,
    Class<T> serializableClass) {
    super(
          fieldCore,
          WkSerdeDtreeOperationInputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          readerFactory,
          WkSerdeDtreeOperationOutputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          writerFactory,
          body,
          serializableClass);
    this.onInitializing = Objects.requireNonNull(onReadInit);
    this.onFullSerializing = Objects.requireNonNull(onReadFull);
    this.onSkippedDeserializing = Objects.requireNonNull(onReadSkipped);
    this.onOutputInitializing = Objects.requireNonNull(onWriteInit);
  }

  public static <T,
                 XS extends WkSerdeDtreeOperationSettings,
                 XD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                 XO extends WkSerdeDtreeAggregatorMsgReader<
                                T,
                                XS,
                                WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                WkSerdeDtreeOperationResult<T>,
                                XD>>

  void doNothingOnReadInitOrSkipped(WkSerdeDtreeAggregatorMsgReaderCoreSimplified<T,XS,XD,XO> readerCore) {
  }

  public static <T,
                 XS extends WkSerdeDtreeOperationSettings,
                 XD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                 XO extends WkSerdeDtreeAggregatorMsgReader<
                                 T,
                                 XS,
                                 WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                 WkSerdeDtreeOperationResult<T>,
                                 XD>,
                 YS extends WkSerdeDtreeOperationSettings,
                 YD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                 YO extends WkSerdeDtreeAggregatorMsgWriter<
                                 T,
                                 YS,
                                 WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                 WkSerdeDtreeOperationResult<T>,
                                 YD>>
  void doNothingOnWriteInit(WkSerdeDtreeAggregatorMsgWriterCoreSimplified<T,YS,YD,YO> writerCore) {
  }

  @Override
  protected WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<T, XS, XD, XO, YS, YD, YO, D> getThis() {
    return this;
  }

}
