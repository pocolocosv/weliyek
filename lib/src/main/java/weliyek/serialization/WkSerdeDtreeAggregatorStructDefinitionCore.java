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

public abstract class WkSerdeDtreeAggregatorStructDefinitionCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        XDC extends WkSerdeDtreeAggregatorStructDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,?,XO,XOC,AXBC,?,?,?,?,?,?,?,?,?,?,? extends XD,?>,
                        XO extends WkSerdeDtreeAggregatorMsgReader<
                                        T,XS,? extends WkSerdeDtreeOperationInputRuntime<XB>,XR,XD>,
                        XOC extends WkSerdeDtreeAggregatorMsgReaderCore<
                                        T,XS,XB,XBC,?,XQC,XR,XD,XDC,XO,?,AXBC>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        YDC extends WkSerdeDtreeAggregatorStructDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,?,YO,YOC,AYBC,? extends YD,?>,
                        YO extends WkSerdeDtreeAggregatorMsgWriter<
                                        T,YS,? extends WkSerdeDtreeOperationOutputRuntime<YB>,YR,YD>,
                        YOC extends WkSerdeDtreeAggregatorMsgWriterCore<T,YS,YB,YBC,?,YQC,YR,YD,YDC,YO,?,AYBC>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        D extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        DC extends WkSerdeDtreeAggregatorStructDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XDC,XO,XOC,AXBC,YS,YB,YBC,YQC,YR,YD,YDC,YO,YOC,AYBC,D,?>>
    extends WkSerdeDtreeStructDefinitionCore<
                        T, XS, XQC, XR, XD, XDC, XO, XOC, AXBC,
                        YS, YQC, YR, YD, YDC, YO, YOC, AYBC, D, DC>
    implements WkSerdeDtreeAggregatorStructDefinition<T>
{

  final WkSerdeDtreeStructSubfieldCoreList<T,XBC,XO,YBC,YO>
    structSubfieldCoreList = new WkSerdeDtreeStructSubfieldCoreList<>();

  private List<WkSerdeDtreeStructField<?>> roRequiredSubfields;

  protected WkSerdeDtreeAggregatorStructDefinitionCore(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore,
    Function<AXBC,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    WkSerdeDtreeAggregatorMsgReaderCoreFactory<XS,XDC,XOC,AXBC> readingOpFactory,
    Function<AYBC,YQC> txRuntimeFactory,
    BiFunction<YO,T,YR> txResultFactory,
    WkSerdeDtreeAggregatorMsgWriterCoreFactory<T,YS,YDC,YOC,AYBC> writingOpFactory,
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

  public static int opWithSingleResult(WkSerdeDtreeAggregatorMsgOperation<?,?,?,?> value) {
    return 1;
  }

  @Override
  protected void onInitialization() {
    this.roRequiredSubfields = Collections.unmodifiableList(this.structSubfieldCoreList.collectRequiredSubfields());
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.structSubfieldCoreList.asSubfieldList();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.roRequiredSubfields;
  }

  public <ST,
          SXS extends WkSerdeDtreeOperationSettings,
          SXD extends WkSerdeDtreeStructDefinition<ST>,
          SXO extends WkSerdeDtreeMsgReader<ST,SXS,?,?,SXD>,
          SYS extends WkSerdeDtreeOperationSettings,
          SYD extends WkSerdeDtreeStructDefinition<ST>,
          SYO extends WkSerdeDtreeMsgWriter<ST,SYS,?,?,SYD>,
          SD extends WkSerdeDtreeStructDefinition<ST>>
  WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
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
    WkSrlzStructDefinitionFrameNodeCoreFactory<
      ST,SXS,SXO,XBC,SYS,SYO,YBC,SD> definitionFactory) {
    WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.structSubfieldCoreList.addSubfield(subcomponent);
    return subcomponent;
  }

  public <ST,
          SXS extends WkSerdeDtreeOperationSettings,
          SXD extends WkSerdeDtreeStructDefinition<ST>,
          SXO extends WkSerdeDtreeMsgReader<ST,SXS,?,?,SXD>,
          SYS extends WkSerdeDtreeOperationSettings,
          SYD extends WkSerdeDtreeStructDefinition<ST>,
          SYO extends WkSerdeDtreeMsgWriter<ST,SYS,?,?,SYD>,
          SD extends WkSerdeDtreeStructDefinition<ST>>
  WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
  insertSubcomponentBefore(
    WkSerdeDtreeStructSubfieldCore<?,?,?,?,XO,?,?,?,?,YO,?,?,?> existingSubcomponent,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXO,XBC,SYS,SYO,YBC,SD> definitionFactory) {
    WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.structSubfieldCoreList.insertBefore(existingSubcomponent, subcomponent);
    return subcomponent;
  }

  public <ST,
          SXS extends WkSerdeDtreeOperationSettings,
          SXD extends WkSerdeDtreeStructDefinition<ST>,
          SXO extends WkSerdeDtreeMsgReader<ST,SXS,?,?,SXD>,
          SYS extends WkSerdeDtreeOperationSettings,
          SYD extends WkSerdeDtreeStructDefinition<ST>,
          SYO extends WkSerdeDtreeMsgWriter<ST,SYS,?,?,SYD>,
          SD extends WkSerdeDtreeStructDefinition<ST>>
  WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
  insertSubcomponentAfter(
    WkSerdeDtreeStructSubfieldCore<?,?,?,?,XO,?,?,?,?,YO,?,?,?> existingSubcomponent,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    WkOperationSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    WkOperationSettingsFactory<YO, SYS> txSettingsFactory,
    WkSzPacketWriteDisaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXO,XBC,SYS,SYO,YBC,SD> definitionFactory) {
    WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
      newStructSubfield = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.structSubfieldCoreList.insertAfter(existingSubcomponent, newStructSubfield);
    return newStructSubfield;
  }

  private <ST,
           SXS extends WkSerdeDtreeOperationSettings,
           SXD extends WkSerdeDtreeStructDefinition<ST>,
           SXO extends WkSerdeDtreeMsgReader<ST,SXS,?,?,SXD>,
           SYS extends WkSerdeDtreeOperationSettings,
           SYD extends WkSerdeDtreeStructDefinition<ST>,
           SYO extends WkSerdeDtreeMsgWriter<ST,SYS,?,?,SYD>,
           SD extends WkSerdeDtreeStructDefinition<ST>>
  WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
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
    WkSrlzStructDefinitionFrameNodeCoreFactory<ST,SXS,SXO,XBC,SYS,SYO,YBC,SD> definitionFactory) {
    WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>
      newStructSubfield = new WkSerdeDtreeStructSubfieldCore<ST,T,SXS,SXD,SXO,XBC,XO,SYS,SYD,SYO,YBC,YO,SD>(
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
    return newStructSubfield;
  }

}
