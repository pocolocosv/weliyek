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
package weliyek.serialization.util.array;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import weliyek.serialization.Disaggregator;
import weliyek.serialization.OperationSubsegmentSettingsFactory;
import weliyek.serialization.PacketInputFieldReadingFactory;
import weliyek.serialization.PacketOutputFieldWritingFactory;
import weliyek.serialization.ProtocolDefinitionFactory;
import weliyek.serialization.WkSzAggregatorDefinitionCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzStructComponentCoreBase;
import weliyek.serialization.WkSzStructSubcomponent;
import weliyek.serialization.WkSzSubcomponentCore;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;
import weliyek.serialization.number.WkSzNumberWriter;
import weliyek.serialization.sequence.VariableSizeSequenceReading;
import weliyek.serialization.sequence.VariableSizeSequenceWriting;
import weliyek.serialization.sequence.WkSzVariableSizeSequenceDefinition;

public abstract class DynamicSequenceDefinitionCore<
                        T,
                        XS extends WkSzOperationSettings,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkSzReadingRuntimeControl<XB,XBC,?>,
                        XR extends WkSzReadingResult<T>,
                        XO extends DynamicSequenceDeserializing<
                                        T, XS,
                                        ? extends WkSzReadingRuntime<XB>,
                                        XR, XD, ?, ?, ?, ?, ?>,
                        XD extends WkSzDynamicSequenceDefinition<T,XO,?,?,?>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        YS extends WkSzOperationSettings,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkSzWritingRuntimeControl<YB,YBC,?>,
                        YR extends WkSzWritingResult,
                        YO extends DynamicSequenceSerializing<
                                        T, YS,
                                        ? extends WkSzWritingRuntime<YB>,
                                        YR, YD, ?, ?, ?, ?, ?>,
                        YD extends WkSzDynamicSequenceDefinition<T,?,YO,?,?>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        ZX extends Number,
                        ZXS extends WkSzOperationSettings,
                        ZXO extends WkSzNumberReader<ZX,ZXS,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZX,?>,
                        ZYS extends WkSzOperationSettings,
                        ZYO extends WkSzNumberWriter<ZX,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZX,?>,
                        ZD extends WkSzNumberDefinition<ZX,?>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VXO extends VariableSizeSequenceReading<T,VXS,?,?,VXD>,
                        VXD extends WkSzVariableSizeSequenceDefinition<T,VXO>,
                        VYS extends WkSzOperationSettings,
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
