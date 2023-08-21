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

public abstract class WkAggregatorSrlzStructDefinitionFrameNodeCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,?>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        XD extends WkAggregatorSrlzStructDefinitionFrameNode<T>,
                        XO extends WkAggregatorSrlzInputPacketDecoderFrameNode<
                                        T,XS,? extends WkDecodingRuntimeSrlzPacketOperationData<XB>,XR,XD>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,?>,
                        YR extends WkResultSrlzPacketOperationData<T>,
                        YD extends WkAggregatorSrlzStructDefinitionFrameNode<T>,
                        YO extends WkAggregatorSrlzOutputPacketEncoderFrameNode<
                                        T,YS,? extends WkEncodingRuntimeSrlzPacketOperationData<YB>,YR,YD>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        D extends WkAggregatorSrlzStructDefinitionFrameNode<T>,
                        DC extends WkAggregatorSrlzStructDefinitionFrameNodeCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXBC,YS,YB,YBC,YQC,YR,YD,YO,AYBC,D,?>>
    extends WkSrlzStructDefinitionFrameNodeCore<T, XS, XQC, XR, XD, XO, AXBC, YS, YQC, YR, YD, YO, AYBC, D, DC>
    implements WkAggregatorSrlzStructDefinitionFrameNode<T>
{

  public static final
  ToIntFunction<WkAggregatorSrlzPacketOperationFrameNode<?,?,?,?,?>> singleOperation() {
    return SINGLEOP;
  }

  private static final ToIntFunction<WkAggregatorSrlzPacketOperationFrameNode<?,?,?,?,?>> SINGLEOP = new ToIntFunction<WkAggregatorSrlzPacketOperationFrameNode<?,?,?,?,?>>() {
    @Override
    public int applyAsInt(WkAggregatorSrlzPacketOperationFrameNode<?,?,?,?,?> value) {
      return 1;
    }
  };

  final WkSrlzStructSubcomponentFrameNodeCoreList<T,XBC,XD,XO,YBC,YD,YO> subcomponentHandlers = new WkSrlzStructSubcomponentFrameNodeCoreList<>();

  private List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> roRequiredSubfields;

  protected WkAggregatorSrlzStructDefinitionFrameNodeCore(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore,
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
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> subfields() {
    return this.subcomponentHandlers.asSubfieldList();
  }

  @Override
  public List<WkSrlzStructSubcomponentFrameNode<?, ?, ?>> requiredSubfields() {
    return this.roRequiredSubfields;
  }

  public <ST,
          SXS extends WkSettingsSrlzPacketOperationData,
          SXD extends WkSrlzStructDefinitionFrameNode<ST>,
          SXO extends WkSrlzInputPacketDecoderFrameNode<ST,SXS,?,?,SXD>,
          SYS extends WkSettingsSrlzPacketOperationData,
          SYD extends WkSrlzStructDefinitionFrameNode<ST>,
          SYO extends WkSrlzOutputPacketEncoderFrameNode<ST,SYS,?,?,SYD>,
          SD extends WkSrlzStructDefinitionFrameNode<ST>>
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
          SXS extends WkSettingsSrlzPacketOperationData,
          SXD extends WkSrlzStructDefinitionFrameNode<ST>,
          SXO extends WkSrlzInputPacketDecoderFrameNode<ST,SXS,?,?,SXD>,
          SYS extends WkSettingsSrlzPacketOperationData,
          SYD extends WkSrlzStructDefinitionFrameNode<ST>,
          SYO extends WkSrlzOutputPacketEncoderFrameNode<ST,SYS,?,?,SYD>,
          SD extends WkSrlzStructDefinitionFrameNode<ST>>
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
          SXS extends WkSettingsSrlzPacketOperationData,
          SXD extends WkSrlzStructDefinitionFrameNode<ST>,
          SXO extends WkSrlzInputPacketDecoderFrameNode<ST,SXS,?,?,SXD>,
          SYS extends WkSettingsSrlzPacketOperationData,
          SYD extends WkSrlzStructDefinitionFrameNode<ST>,
          SYO extends WkSrlzOutputPacketEncoderFrameNode<ST,SYS,?,?,SYD>,
          SD extends WkSrlzStructDefinitionFrameNode<ST>>
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
           SXS extends WkSettingsSrlzPacketOperationData,
           SXD extends WkSrlzStructDefinitionFrameNode<ST>,
           SXO extends WkSrlzInputPacketDecoderFrameNode<ST,SXS,?,?,SXD>,
           SYS extends WkSettingsSrlzPacketOperationData,
           SYD extends WkSrlzStructDefinitionFrameNode<ST>,
           SYO extends WkSrlzOutputPacketEncoderFrameNode<ST,SYS,?,?,SYD>,
           SD extends WkSrlzStructDefinitionFrameNode<ST>>
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
