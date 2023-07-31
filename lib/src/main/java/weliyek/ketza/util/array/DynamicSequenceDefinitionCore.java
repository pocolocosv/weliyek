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
package weliyek.ketza.util.array;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import weliyek.amat.base.WkSzStructComponentCore;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.OperationSubsegmentSettingsFactory;
import weliyek.amat.base.ProtocolDefinitionFactory;
import weliyek.amat.base.WkSzStructSubcomponent;
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
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.aggregator.WkSzAggregatorDefinitionCore;
import weliyek.amat.basic.aggregator.WkSzSubcomponentCore;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableSizeSequenceDefinition;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceReading;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceWriting;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.amat.basic.number.WkSzNumberWriter;

public abstract class DynamicSequenceDefinitionCore<
                        T,
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,?>,
                        XR extends DeserializingResult<T>,
                        XO extends DynamicSequenceDeserializing<
                                        T, XS,
                                        ? extends DeserializingRuntime<XB>,
                                        XR, XD, ?, ?, ?, ?, ?>,
                        XD extends WkSzDynamicSequenceDefinition<T,XO,?,?,?>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,?>,
                        YR extends SerializingResult,
                        YO extends DynamicSequenceSerializing<
                                        T, YS,
                                        ? extends SerializingRuntime<YB>,
                                        YR, YD, ?, ?, ?, ?, ?>,
                        YD extends WkSzDynamicSequenceDefinition<T,?,YO,?,?>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        ZX extends Number,
                        ZXS extends OperationSettings,
                        ZXO extends WkSzNumberReader<ZX,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZX,?>,
                        ZYS extends OperationSettings,
                        ZYO extends WkSzNumberWriter<ZX,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZX,?>,
                        ZD extends WkSzNumberDefinition<ZX,?>,
                        VXS extends VariableLengthSettings,
                        VXO extends VariableSizeSequenceReading<T,VXS,?,?,VXD>,
                        VXD extends WkSzVariableSizeSequenceDefinition<T,VXO>,
                        VYS extends OperationSettings,
                        VYO extends VariableSizeSequenceWriting<T,VYS,?,?,VYD>,
                        VYD extends WkSzVariableSizeSequenceDefinition<T,?>,
                        VD extends WkSzVariableSizeSequenceDefinition<T,VXO>,
                        D extends WkSzDynamicSequenceDefinition<T,XO,YO,ZD,VD>,
                        DC extends DynamicSequenceDefinitionCore<
                                      T,XS,XB,XBC,XQC,XR,XO,XD,AXBC,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYBC,
                                      ZX,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                                      VXS,VXO,VXD,VYS,VYO,VYD,VD,
                                      D,?>>
    extends WkSzAggregatorDefinitionCore<
                        T, XS, XB, XBC, XQC, XR, XD, XO, AXBC,
                        YS, YB, YBC, YQC, YR, YD, YO, AYBC, D, DC>
    implements WkSzDynamicSequenceDefinition<T, XO, YO, ZD, VD>
{

  final WkSzSubcomponentCore<ZX,ZXS,ZXD,ZXO,T,XBC,XD,XO,ZYS,ZYD,ZYO,YBC,YD,YO,ZD,D>
                        sizeComponent;
  final WkSzSubcomponentCore<T,VXS,VXD,VXO,T,XBC,XD,XO,VYS,VYD,VYO,YBC,YD,YO,VD,D>
                        varseqComponent;

  protected DynamicSequenceDefinitionCore(
    String sizeComponentLabel,
    OperationSubsegmentSettingsFactory<XO,ZXS> sizeComponentRxSettingsFactory,
    OperationSubsegmentSettingsFactory<YO, ZYS> sizeComponentTxSettingsFactory,
    Disaggregator<ZX, ZYD, T, YO> sizeComponentTxDesaggregator,
    ProtocolDefinitionFactory<ZX,ZXS,ZXD,ZXO,XBC,ZYS,ZYD,ZYO,YBC,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    OperationSubsegmentSettingsFactory<XO,VXS> varseqComponentRxSettingsFactory,
    OperationSubsegmentSettingsFactory<YO,VYS> varseqComponentTxSettingsFactory,
    Disaggregator<T, VYD, T, YO> varseqComponentTxDesaggregator,
    ProtocolDefinitionFactory<T,VXS,VXD,VXO,XBC,VYS,VYD,VYO,YBC,VD> varseqComponentDefinitionFactory,
    WkSzStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
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
    this.sizeComponent = this.<ZX,ZXS,ZXD,ZXO,ZYS,ZYD,ZYO,ZD>addSubcomponent(
                                    sizeComponentLabel,
                                    Optional.empty(), // Always enabled.
                                    singleOperation(),
                                    sizeComponentRxSettingsFactory,
                                    Optional.empty(), // Always enabled.
                                    singleOperation(),
                                    sizeComponentTxSettingsFactory,
                                    sizeComponentTxDesaggregator,
                                    true,             // Deserialized value is always needed.
                                    sizeComponentDefinitionFactory);
    this.varseqComponent = this.<T,VXS,VXD,VXO,VYS,VYD,VYO,VD>addSubcomponent(
                                    varseqComponentLabel,
                                    Optional.empty(), // Always enabled.
                                    singleOperation(),
                                    varseqComponentRxSettingsFactory,
                                    Optional.empty(), // Always enabled.
                                    singleOperation(),
                                    varseqComponentTxSettingsFactory,
                                    varseqComponentTxDesaggregator,
                                    false,
                                    varseqComponentDefinitionFactory);
  }

  @Override
  public WkSzStructSubcomponent<XO, YO, ZD> size() {
    return this.sizeComponent.body();
  }

  @Override
  public WkSzStructSubcomponent<XO, YO, VD> variableSequence() {
    return this.varseqComponent.body();
  }

}
