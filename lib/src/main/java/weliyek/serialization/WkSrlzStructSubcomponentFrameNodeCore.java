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
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class WkSrlzStructSubcomponentFrameNodeCore<
                        ST,
                        SXS extends WkSettingsSrlzPacketOperationData,
                        SXD extends WkSerdeDtreeNodeStructDefinition<ST>,
                        SXO extends WkSerdeDtreeNodeDataReader<ST,SXS,?,?,SXD>,
                        T,
                        XBC extends WkSzInputBytestreamBase<?>,
                        XD extends WkSerdeDtreeAggregatorDefinition<T>,
                        XO extends WkSerdeDtreeAggregatorReader<
                                        T,?, ? extends WkDecodingRuntimeSrlzPacketOperationData<?>,?,XD>,
                        SYS extends WkSettingsSrlzPacketOperationData,
                        SYD extends WkSerdeDtreeNodeStructDefinition<ST>,
                        SYO extends WkSerdeDtreeNodeDataWriter<ST,SYS,?,?,SYD>,
                        YBC extends WkSzOutputBytestreamBase<?>,
                        YD extends WkSerdeDtreeAggregatorDefinition<T>,
                        YO extends WkSerdeDtreeAggregatorWriter<
                                        T,?,? extends WkEncodingRuntimeSrlzPacketOperationData<?>,?,YD>,
                        SD extends WkSerdeDtreeNodeStructDefinition<ST>,
                        D extends WkSerdeDtreeAggregatorDefinition<T>>
    implements WkSerdeDtreeNodeStructComponentHandler<XO, YO, SD>
{

  private final Optional<Predicate<? super XO>> rxEnablingTest;
  private final ToIntFunction<? super XO> numberOfRxOperationsEvaluator;
  private final WkOperationSettingsFactory<XO, SXS> rxSettingsFactory;
  private final Optional<Predicate<? super YO>> txEnablingTest;
  private final ToIntFunction<? super YO> numberOfTxOperationsEvaluator;
  private final WkOperationSettingsFactory<YO, SYS> txSettingsFactory;
  private final WkSrlzStructComponentFrameNodeCore<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> protocolfieldCore;
  private final WkSerdeDtreeNodeStructComponentHandler<XO, YO, SD> body;
  private final WkSzPacketWriteDisaggregator<ST, SYD, T, YO> disaggregator;
  private boolean deserializedRequiredByAggregator;
  private int order;

  WkSrlzStructSubcomponentFrameNodeCore(
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST, SYD, T, YO> disaggregator,
    boolean deserializedRequiredByAggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory,
    WkSerdeDtreeNodeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore) {
    this.rxEnablingTest = Objects.requireNonNull(rxEnablingTest);
    this.numberOfRxOperationsEvaluator = Objects.requireNonNull(numberOfRxOperationsEvaluator);
    this.rxSettingsFactory = Objects.requireNonNull(rxSettingsFactory);
    this.txEnablingTest = Objects.requireNonNull(txEnablingTest);
    this.numberOfTxOperationsEvaluator = Objects.requireNonNull(numberOfTxOperationsEvaluator);
    this.txSettingsFactory = Objects.requireNonNull(txSettingsFactory);
    this.protocolfieldCore = new WkSrlzStructComponentFrameNodeNonrootCore<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD>(label, definitionFactory, parentDefinitionCore);
    this.disaggregator = Objects.requireNonNull(disaggregator);
    setFlagDeserializedAsRequiredByAggregator(deserializedRequiredByAggregator);
    this.body = new WkSerdeDtreeNodeStructComponentHandler<XO, YO, SD>() {

      @Override
      public Optional<Predicate<? super XO>> rxOptionalityTest() {
        return WkSrlzStructSubcomponentFrameNodeCore.this.rxOptionalityTest();
      }

      @Override
      public Optional<Predicate<? super YO>> txOptionalityTest() {
        return WkSrlzStructSubcomponentFrameNodeCore.this.txOptionalityTest();
      }

      @Override
      public ToIntFunction<? super XO> numberOfRxOperationsEvaluator() {
        return WkSrlzStructSubcomponentFrameNodeCore.this.numberOfRxOperationsEvaluator();
      }

      @Override
      public ToIntFunction<? super YO> numberOfTxOperationsEvaluator() {
        return WkSrlzStructSubcomponentFrameNodeCore.this.numberOfTxOperationsEvaluator();
      }

      @Override
      public WkSerdeDtreeNodeStructComponent<SD> field() {
        return WkSrlzStructSubcomponentFrameNodeCore.this.field();
      }

      @Override
      public int executionOrder() {
        return WkSrlzStructSubcomponentFrameNodeCore.this.executionOrder();
      }
    };
  }

  @SuppressWarnings("unchecked")
  public WkSrlzOutputPacketSubfieldFrameNodeCore<ST,SYS,SYD,SYO,T,YBC,YD,YO>
  newWritingSubfieldHandlerCore(
    WkSerdeDtreeAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?> writingParentOp) {
    WkSrlzOutputPacketSubfieldFrameNodeCore<ST,SYS,SYD,SYO,T,YBC,YD,YO>
      writingSubfieldHandler = new WkSrlzOutputPacketSubfieldFrameNodeCore<ST,SYS,SYD,SYO,T,YBC,YD,YO>();
    writingSubfieldHandler.initiliaze(
        (WkSrlzStructSubcomponentFrameNodeCore<ST,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>) this,
        writingParentOp);
    return writingSubfieldHandler;
  }

  @SuppressWarnings("unchecked")
  public WkSrlzInputPacketSubfieldFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO>
  newReadingSubfieldHandlerCore(
    WkSerdeDtreeAggregatorReaderCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?> readingParentOp) {
    WkSrlzInputPacketSubfieldFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO> readingSubfieldHandler = new WkSrlzInputPacketSubfieldFrameNodeCore<>();
    readingSubfieldHandler.initiliaze(
        (WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,?,XBC,XD,XO,?,?,?,?,?,?,? extends SXD, ? extends XD>) this, readingParentOp);
    return readingSubfieldHandler;
  }

  /*

  @SuppressWarnings("unchecked")
  public
  <
  _SX,
  _SXS extends OperationSettings,
  _SXO extends DeserializingOperation<_SX,_SXS,?,?,? super _SD>,
  _X,
  _XB extends InputBytestream<?>,
  _XO extends AggregatorReading<
                  _X,?,
                  ? extends DeserializingRuntime<_XB>,
                  ?,? super _D>,
  _SD extends DefinitionSegment<_SX,?,_SXO>,
  _D extends AggregatorDefinition<_X,?,_XO>>
  DeserializingSubfieldHandlerCore<_SX,_SXS,_SD,_SXO,_X,_XB,_D,_XO>
  newReadingSubfieldHandlerCore(
    AggregatorReadingCore<?,?,?,?,?,?,_XO,?,_D,?,?> readingParentOp) {
    DeserializingSubfieldHandlerCore<_SX,_SXS,_SD,_SXO,_X,_XB,_D,_XO> readingSubfieldHandler = new DeserializingSubfieldHandlerCore<>();
    readingSubfieldHandler.initiliaze((SubcomponentHandlerCore<_SX, _SXS, _SXO, ?, _XB, _XO, ?, ?, ?, ?, ?, ?, _SD, _D>) this, readingParentOp);
    return readingSubfieldHandler;
  }
  */

  @Override
  public Optional<Predicate<? super XO>> rxOptionalityTest() {
    return this.rxEnablingTest;
  }

  @Override
  public ToIntFunction<? super XO> numberOfRxOperationsEvaluator() {
    return this.numberOfRxOperationsEvaluator;
  }

  public SXS newRxSettings(int index, XO parentOperation) {
    return this.rxSettingsFactory.newSettings(index, parentOperation);
  }

  @Override
  public Optional<Predicate<? super YO>> txOptionalityTest() {
    return this.txEnablingTest;
  }

  @Override
  public ToIntFunction<? super YO> numberOfTxOperationsEvaluator() {
    return this.numberOfTxOperationsEvaluator;
  }

  public SYS newTxSettings(int index, YO parentOperation) {
    return this.txSettingsFactory.newSettings(index, parentOperation);
  }

  @Override
  public WkSerdeDtreeNodeStructComponent<SD> field() {
    return this.protocolfieldCore.asProtocolField();
  }

  public WkSerdeDtreeNodeStructComponentHandler<XO, YO, SD> body() {
    return this.body;
  }

  public final WkSrlzStructComponentFrameNodeCore<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> protocolFieldCore() {
    return this.protocolfieldCore;
  }

  public WkSzPacketWriteDisaggregator<ST,SYD,T,YO> disaggregator() {
    return this.disaggregator;
  }

  public boolean isDeserializedRequiredByAggregator() {
    return this.deserializedRequiredByAggregator;
  }

  public void setDeserializedAsRequiredByAggregator() {
    setFlagDeserializedAsRequiredByAggregator(true);
  }

  public void clearDeserializedAsRequiredByAggregator() {
    setFlagDeserializedAsRequiredByAggregator(false);
  }

  public void setFlagDeserializedAsRequiredByAggregator(boolean b) {
    this.deserializedRequiredByAggregator = b;
  }

  @Override
  public int executionOrder() {
    return this.order;
  }

  void setOrder(int newOrderIndex) {
    this.order = newOrderIndex;
  }

}
