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

public class WkSzSubcomponentCore<
                        ST,
                        SXS extends WkSzOperationSettings,
                        SXD extends WkSzDefinition<ST,?>,
                        SXO extends WkSzPacketReaderOperation<ST,SXS,?,?,SXD>,
                        T,
                        XBC extends WkSzInputBytestreamBase<?>,
                        XD extends WkSzAggregatorDefinition<T,?>,
                        XO extends WkSzAggregatorReader<
                                        T,?, ? extends WkSzReadingRuntime<?>,?,XD>,
                        SYS extends WkSzOperationSettings,
                        SYD extends WkSzDefinition<ST,?>,
                        SYO extends WkSzPacketWriterOperation<ST,SYS,?,?,SYD>,
                        YBC extends WkSzOutputBytestreamBase<?>,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<
                                        T,?,? extends WkSzWritingRuntime<?>,?,YD>,
                        SD extends WkSzDefinition<ST,?>,
                        D extends WkSzAggregatorDefinition<T,XO>>
    implements WkSzStructSubcomponent<XO, YO, SD>
{

  private final Optional<Predicate<? super XO>> rxEnablingTest;
  private final ToIntFunction<? super XO> numberOfRxOperationsEvaluator;
  private final OperationSubsegmentSettingsFactory<XO, SXS> rxSettingsFactory;
  private final Optional<Predicate<? super YO>> txEnablingTest;
  private final ToIntFunction<? super YO> numberOfTxOperationsEvaluator;
  private final OperationSubsegmentSettingsFactory<YO, SYS> txSettingsFactory;
  private final WkSzStructComponentCoreBase<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> protocolfieldCore;
  private final WkSzStructSubcomponent<XO, YO, SD> body;
  private final WkSzPacketWriteDisaggregator<ST, SYD, T, YO> disaggregator;
  private boolean deserializedRequiredByAggregator;
  private int order;

  WkSzSubcomponentCore(
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST, SYD, T, YO> disaggregator,
    boolean deserializedRequiredByAggregator,
    ProtocolDefinitionFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory,
    WkSzDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?> parentDefinitionCore) {
    this.rxEnablingTest = Objects.requireNonNull(rxEnablingTest);
    this.numberOfRxOperationsEvaluator = Objects.requireNonNull(numberOfRxOperationsEvaluator);
    this.rxSettingsFactory = Objects.requireNonNull(rxSettingsFactory);
    this.txEnablingTest = Objects.requireNonNull(txEnablingTest);
    this.numberOfTxOperationsEvaluator = Objects.requireNonNull(numberOfTxOperationsEvaluator);
    this.txSettingsFactory = Objects.requireNonNull(txSettingsFactory);
    this.protocolfieldCore = new WkSzStructChildComponent<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD>(label, definitionFactory, parentDefinitionCore);
    this.disaggregator = Objects.requireNonNull(disaggregator);
    setFlagDeserializedAsRequiredByAggregator(deserializedRequiredByAggregator);
    this.body = new WkSzStructSubcomponent<XO, YO, SD>() {

      @Override
      public Optional<Predicate<? super XO>> rxOptionalityTest() {
        return WkSzSubcomponentCore.this.rxOptionalityTest();
      }

      @Override
      public Optional<Predicate<? super YO>> txOptionalityTest() {
        return WkSzSubcomponentCore.this.txOptionalityTest();
      }

      @Override
      public ToIntFunction<? super XO> numberOfRxOperationsEvaluator() {
        return WkSzSubcomponentCore.this.numberOfRxOperationsEvaluator();
      }

      @Override
      public ToIntFunction<? super YO> numberOfTxOperationsEvaluator() {
        return WkSzSubcomponentCore.this.numberOfTxOperationsEvaluator();
      }

      @Override
      public WkSzStructComponent<SD> field() {
        return WkSzSubcomponentCore.this.field();
      }

      @Override
      public int executionOrder() {
        return WkSzSubcomponentCore.this.executionOrder();
      }
    };
  }

  @SuppressWarnings("unchecked")
  public WkSzPacketWriterSubfieldCore<ST,SYS,SYD,SYO,T,YBC,YD,YO>
  newWritingSubfieldHandlerCore(
    WkSzAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?> writingParentOp) {
    WkSzPacketWriterSubfieldCore<ST,SYS,SYD,SYO,T,YBC,YD,YO>
      writingSubfieldHandler = new WkSzPacketWriterSubfieldCore<ST,SYS,SYD,SYO,T,YBC,YD,YO>();
    writingSubfieldHandler.initiliaze(
        (WkSzSubcomponentCore<ST,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>) this,
        writingParentOp);
    return writingSubfieldHandler;
  }

  @SuppressWarnings("unchecked")
  public WkSzPacketReaderSubfieldCore<ST,SXS,SXD,SXO,T,XBC,XD,XO>
  newReadingSubfieldHandlerCore(
    WkSzAggregatorReaderCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?> readingParentOp) {
    WkSzPacketReaderSubfieldCore<ST,SXS,SXD,SXO,T,XBC,XD,XO> readingSubfieldHandler = new WkSzPacketReaderSubfieldCore<>();
    readingSubfieldHandler.initiliaze(
        (WkSzSubcomponentCore<ST,SXS,SXD,SXO,?,XBC,XD,XO,?,?,?,?,?,?,? extends SXD, ? extends XD>) this, readingParentOp);
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
  public WkSzStructComponent<SD> field() {
    return this.protocolfieldCore.asProtocolField();
  }

  public WkSzStructSubcomponent<XO, YO, SD> body() {
    return this.body;
  }

  public final WkSzStructComponentCoreBase<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> protocolFieldCore() {
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
