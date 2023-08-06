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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.WkSzDefinitionCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.PacketInputFieldReadingFactory;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.output.Disaggregator;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.PacketOutputFieldWritingFactory;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;

public abstract class WkSzAggregatorDefinitionCore<
                        T,
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,?>,
                        XR extends DeserializingResult<T>,
                        XD extends WkSzAggregatorDefinition<T,?>,
                        XO extends WkSzAggregatorReader<
                                        T,XS,? extends DeserializingRuntime<XB>,XR,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,?>,
                        YR extends SerializingResult,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<
                                        T,YS,? extends SerializingRuntime<YB>,YR,YD>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        D extends WkSzAggregatorDefinition<T,XO>,
                        DC extends WkSzAggregatorDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXBC,YS,YB,YBC,YQC,YR,YD,YO,AYBC,D,?>>
    extends WkSzDefinitionCore<T, XS, XQC, XR, XD, XO, AXBC, YS, YQC, YR, YD, YO, AYBC, D, DC>
    implements WkSzAggregatorDefinition<T, XO>
{

  public static final
  ToIntFunction<WkSzAggregatorOperation<?,?,?,?,?>> singleOperation() {
    return SINGLEOP;
  }

  private static final ToIntFunction<WkSzAggregatorOperation<?,?,?,?,?>> SINGLEOP = new ToIntFunction<WkSzAggregatorOperation<?,?,?,?,?>>() {
    @Override
    public int applyAsInt(WkSzAggregatorOperation<?,?,?,?,?> value) {
      return 1;
    }
  };

  final SubcomponentHandlerList<T,XBC,XD,XO,YBC,YD,YO> subcomponentHandlers = new SubcomponentHandlerList<>();

  private List<WkSzStructSubcomponent<?, ?, ?>> roRequiredSubfields;

  protected WkSzAggregatorDefinitionCore(
    WkSzStructComponentCoreBase<?,?,?,?,?,?,?,?,?,?> componentCore,
    Function<AXBC,XQC> rxRuntimeFactory,
    BiFunction<XO,T,XR> rxResultFactory,
    PacketInputFieldReadingFactory<T,XS,XD,DC,XO,AXBC> readingOpFactory,
    Function<AYBC,YQC> txRuntimeFactory,
    Function<YO,YR> txResultFactory,
    PacketOutputFieldWritingFactory<T,YS,YD,DC,YO,AYBC> writingOpFactory,
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
  public List<WkSzStructSubcomponent<?, ?, ?>> subfields() {
    return this.subcomponentHandlers.asSubfieldList();
  }

  @Override
  public List<WkSzStructSubcomponent<?, ?, ?>> requiredSubfields() {
    return this.roRequiredSubfields;
  }

  public <ST,
          SXS extends OperationSettings,
          SXD extends WkSzDefinition<ST,?>,
          SXO extends WkSzPacketReaderOperation<ST,SXS,?,?,SXD>,
          SYS extends OperationSettings,
          SYD extends WkSzDefinition<ST,?>,
          SYO extends WkSzPacketWriterOperation<ST,SYS,?,?,SYD>,
          SD extends WkSzDefinition<ST,?>>
  WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  addSubcomponent(
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<YO, SYS> txSettingsFactory,
    Disaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    ProtocolDefinitionFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.subcomponentHandlers.addSubfield(subcomponent);
    return subcomponent;
  }

  public <ST,
          SXS extends OperationSettings,
          SXD extends WkSzDefinition<ST,?>,
          SXO extends WkSzPacketReaderOperation<ST,SXS,?,?,SXD>,
          SYS extends OperationSettings,
          SYD extends WkSzDefinition<ST,?>,
          SYO extends WkSzPacketWriterOperation<ST,SYS,?,?,SYD>,
          SD extends WkSzDefinition<ST,?>>
  WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  insertSubcomponentBefore(
    WkSzSubcomponentCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubcomponent,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<YO, SYS> txSettingsFactory,
    Disaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    ProtocolDefinitionFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.subcomponentHandlers.insertBefore(existingSubcomponent, subcomponent);
    return subcomponent;
  }

  public <ST,
          SXS extends OperationSettings,
          SXD extends WkSzDefinition<ST,?>,
          SXO extends WkSzPacketReaderOperation<ST,SXS,?,?,SXD>,
          SYS extends OperationSettings,
          SYD extends WkSzDefinition<ST,?>,
          SYO extends WkSzPacketWriterOperation<ST,SYS,?,?,SYD>,
          SD extends WkSzDefinition<ST,?>>
  WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  insertSubcomponentAfter(
    WkSzSubcomponentCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubcomponent,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<YO, SYS> txSettingsFactory,
    Disaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    ProtocolDefinitionFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
      subcomponent = createSubcomponent(
                        label, rxEnablingTest, numberOfRxOperationsEvaluator,
                        rxSettingsFactory, txEnablingTest,
                        numberOfTxOperationsEvaluator, txSettingsFactory,
                        disaggregator, deserializedRequired, definitionFactory);
    this.subcomponentHandlers.insertAfter(existingSubcomponent, subcomponent);
    return subcomponent;
  }

  private <ST,
           SXS extends OperationSettings,
           SXD extends WkSzDefinition<ST,?>,
           SXO extends WkSzPacketReaderOperation<ST,SXS,?,?,SXD>,
           SYS extends OperationSettings,
           SYD extends WkSzDefinition<ST,?>,
           SYO extends WkSzPacketWriterOperation<ST,SYS,?,?,SYD>,
           SD extends WkSzDefinition<ST,?>>
  WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>
  createSubcomponent(
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    ToIntFunction<? super XO> numberOfRxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<XO, SXS> rxSettingsFactory,
    Optional<Predicate<? super YO>> txEnablingTest,
    ToIntFunction<? super YO> numberOfTxOperationsEvaluator,
    OperationSubsegmentSettingsFactory<YO, SYS> txSettingsFactory,
    Disaggregator<ST,SYD,T,YO> disaggregator,
    boolean deserializedRequired,
    ProtocolDefinitionFactory<ST,SXS,SXD,SXO,XBC,SYS,SYD,SYO,YBC,SD> definitionFactory) {
    WkSzSubcomponentCore<ST, SXS, SXD, SXO, T, XBC, XD, XO, SYS, SYD, SYO, YBC, YD, YO, SD, D>
      newHandler = new WkSzSubcomponentCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,SYS,SYD,SYO,YBC,YD,YO,SD,D>(
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