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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public abstract class WkSerdeDtreeAggregatorDefinitionCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeAggregatorDefinition<T>,
                        XO extends WkSerdeDtreeAggregatorReader<
                                        T,XS,? extends WkSerdeDtreeOperationInputRuntime<XB>,XR,XD>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeAggregatorDefinition<T>,
                        YO extends WkSerdeDtreeAggregatorWriter<
                                        T,YS,? extends WkSerdeDtreeOperationOutputRuntime<YB>,YR,YD>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeAggregatorDefinition<T>,
                        DC extends WkSerdeDtreeAggregatorDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXBC,YS,YB,YBC,YQC,YR,YD,YO,AYBC,D,?>>
    extends WkSerdeDtreeNodeStructDefinitionCore<T, XS, XQC, XR, XD, XO, AXBC, YS, YQC, YR, YD, YO, AYBC, D, DC>
    implements WkSerdeDtreeAggregatorDefinition<T>
{

  public static final
  ToIntFunction<WkSerdeDtreeAggregatorOperation<?,?,?,?,?>> singleOperation() {
    return SINGLEOP;
  }

  private static final ToIntFunction<WkSerdeDtreeAggregatorOperation<?,?,?,?,?>> SINGLEOP = new ToIntFunction<WkSerdeDtreeAggregatorOperation<?,?,?,?,?>>() {
    @Override
    public int applyAsInt(WkSerdeDtreeAggregatorOperation<?,?,?,?,?> value) {
      return 1;
    }
  };

  final WkSrlzStructSubcomponentFrameNodeCoreList<T,XBC,XD,XO,YBC,YD,YO> subcomponentHandlers = new WkSrlzStructSubcomponentFrameNodeCoreList<>();

  private List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> roRequiredSubfields;

  protected WkSerdeDtreeAggregatorDefinitionCore(
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXBC,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSzPacketReaderOperationCoreFactory<T,XS,XD,DC,XO,AXBC> readingOpFactory,
    Function<AYBC,YQC> txRuntimeFactory,
    BiFunction<YO,T,YR> txResultFactory,
    WkSzPacketWriterOperationCoreFactory<T,YS,YD,DC,YO,AYBC> writingOpFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          rxRuntimeFactory,
          rxResultFactory,
          readingOpFactory,
          txRuntimeFactory,
          txResultFactory,
          writingOpFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected void onInitialization() {
    this.roRequiredSubfields = Collections.unmodifiableList(this.subcomponentHandlers.collectRequiredSubfields());
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.subcomponentHandlers.asSubfieldList();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.roRequiredSubfields;
  }

  public <ST,
          SXS extends WkSerdeDtreeOperationSettings,
          SXD extends WkSerdeDtreeNodeStructDefinition<ST>,
          SXO extends WkSerdeDtreeNodeDataReader<ST,SXS,?,?,SXD>,
          SYS extends WkSerdeDtreeOperationSettings,
          SYD extends WkSerdeDtreeNodeStructDefinition<ST>,
          SYO extends WkSerdeDtreeNodeDataWriter<ST,SYS,?,?,SYD>,
          SD extends WkSerdeDtreeNodeStructDefinition<ST>>
  WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  addSubcomponent(
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.subcomponentHandlers.addSubfield(subcomponent);
    return subcomponent;
  }

  public <ST,
          SXS extends WkSerdeDtreeOperationSettings,
          SXD extends WkSerdeDtreeNodeStructDefinition<ST>,
          SXO extends WkSerdeDtreeNodeDataReader<ST,SXS,?,?,SXD>,
          SYS extends WkSerdeDtreeOperationSettings,
          SYD extends WkSerdeDtreeNodeStructDefinition<ST>,
          SYO extends WkSerdeDtreeNodeDataWriter<ST,SYS,?,?,SYD>,
          SD extends WkSerdeDtreeNodeStructDefinition<ST>>
  WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  insertSubcomponentBefore(
    WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubcomponent,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.subcomponentHandlers.insertBefore(existingSubcomponent, subcomponent);
    return subcomponent;
  }

  public <ST,
          SXS extends WkSerdeDtreeOperationSettings,
          SXD extends WkSerdeDtreeNodeStructDefinition<ST>,
          SXO extends WkSerdeDtreeNodeDataReader<ST,SXS,?,?,SXD>,
          SYS extends WkSerdeDtreeOperationSettings,
          SYD extends WkSerdeDtreeNodeStructDefinition<ST>,
          SYO extends WkSerdeDtreeNodeDataWriter<ST,SYS,?,?,SYD>,
          SD extends WkSerdeDtreeNodeStructDefinition<ST>>
  WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  insertSubcomponentAfter(
    WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubcomponent,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.subcomponentHandlers.insertAfter(existingSubcomponent, subcomponent);
    return subcomponent;
  }

  private <ST,
           SXS extends WkSerdeDtreeOperationSettings,
           SXD extends WkSerdeDtreeNodeStructDefinition<ST>,
           SXO extends WkSerdeDtreeNodeDataReader<ST,SXS,?,?,SXD>,
           SYS extends WkSerdeDtreeOperationSettings,
           SYD extends WkSerdeDtreeNodeStructDefinition<ST>,
           SYO extends WkSerdeDtreeNodeDataWriter<ST,SYS,?,?,SYD>,
           SD extends WkSerdeDtreeNodeStructDefinition<ST>>
  WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  createSubcomponent(
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSrlzStructSubcomponentFrameNodeCore<ST, SXS, SXD, SXO, T, XBC, XD, XO, SYS, SYD, SYO, YBC, YD, YO, SD, D>
      newHandler = new WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>(
                          label,
                          rxEnablingTest,
                          numberOfRxOperationsEvaluator,
                          rxSettingsFactory,
                          txEnablingTest,
                          numberOfTxOperationsEvaluator,
                          txSettingsFactory,
                          disaggregator,
                          deserializedRequired,
                          definitionFactory,
                          this);
    return newHandler;
  }

}
