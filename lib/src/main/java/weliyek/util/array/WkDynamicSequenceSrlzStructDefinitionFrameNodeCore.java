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
package weliyek.util.array;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import weliyek.serialization.WkSerdeDTreeAggregatorDefinitionCore;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeDTreeNumberReader;
import weliyek.serialization.number.WkSerdeDTreeNumberWriter;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.serialization.sequence.WkSerdeDTreeVariableSizeSequenceReader;
import weliyek.serialization.sequence.WkSerdeDTreeVariableSizeSequenceWriter;
import weliyek.serialization.sequence.WkSerdeDTreeVariableSizeSequenceDefinition;

public abstract class WkDynamicSequenceSrlzStructDefinitionFrameNodeCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,?>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        XO extends WkSerdeDTreeDynamicSequenceReader<
                                        T, XS,
                                        ? extends WkDecodingRuntimeSrlzPacketOperationData<XB>,
                                        XR, XD, ?, ?, ?, ?, ?>,
                        XD extends WkSerdeDTreeDynamicSequenceDefinition<T,XO,?,?,?>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,?>,
                        YR extends WkResultSrlzPacketOperationData<T>,
                        YO extends WkSerdeDTreeDynamicSequenceWriter<
                                        T, YS,
                                        ? extends WkEncodingRuntimeSrlzPacketOperationData<YB>,
                                        YR, YD, ?, ?, ?, ?, ?>,
                        YD extends WkSerdeDTreeDynamicSequenceDefinition<T,?,YO,?,?>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        ZX extends Number,
                        ZXS extends WkSettingsSrlzPacketOperationData,
                        ZXO extends WkSerdeDTreeNumberReader<ZX,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZX>,
                        ZYS extends WkSettingsSrlzPacketOperationData,
                        ZYO extends WkSerdeDTreeNumberWriter<ZX,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDTreeNumberDefinition<ZX>,
                        ZD extends WkSerdeDTreeNumberDefinition<ZX>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VXO extends WkSerdeDTreeVariableSizeSequenceReader<T,VXS,?,?,VXD>,
                        VXD extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
                        VYS extends WkSettingsSrlzPacketOperationData,
                        VYO extends WkSerdeDTreeVariableSizeSequenceWriter<T,VYS,?,?,VYD>,
                        VYD extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
                        VD extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
                        D extends WkSerdeDTreeDynamicSequenceDefinition<T,XO,YO,ZD,VD>,
                        DC extends WkDynamicSequenceSrlzStructDefinitionFrameNodeCore<
                                      T,XS,XB,XBC,XQC,XR,XO,XD,AXBC,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYBC,
                                      ZX,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                                      VXS,VXO,VXD,VYS,VYO,VYD,VD,
                                      D,?>>
    extends WkSerdeDTreeAggregatorDefinitionCore<
                        T, XS, XB, XBC, XQC, XR, XD, XO, AXBC,
                        YS, YB, YBC, YQC, YR, YD, YO, AYBC, D, DC>
    implements WkSerdeDTreeDynamicSequenceDefinition<T, XO, YO, ZD, VD>
{

  final WkSrlzStructSubcomponentFrameNodeCore<ZX,ZXS,ZXD,ZXO,T,XBC,XD,XO,ZYS,ZYD,ZYO,YBC,YD,YO,ZD,D>
                        sizeComponent;
  final WkSrlzStructSubcomponentFrameNodeCore<T,VXS,VXD,VXO,T,XBC,XD,XO,VYS,VYD,VYO,YBC,YD,YO,VD,D>
                        varseqComponent;

  protected WkDynamicSequenceSrlzStructDefinitionFrameNodeCore(
    String sizeComponentLabel,
    WkOperationSettingsFactory<XO,ZXS> sizeComponentRxSettingsFactory,
    WkOperationSettingsFactory<YO, ZYS> sizeComponentTxSettingsFactory,
    WkSzPacketWriteDisaggregator<ZX, ZYD, T, YO> sizeComponentTxDesaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZX,ZXS,ZXD,ZXO,XBC,ZYS,ZYD,ZYO,YBC,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    WkOperationSettingsFactory<XO,VXS> varseqComponentRxSettingsFactory,
    WkOperationSettingsFactory<YO,VYS> varseqComponentTxSettingsFactory,
    WkSzPacketWriteDisaggregator<T, VYD, T, YO> varseqComponentTxDesaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<T,VXS,VXD,VXO,XBC,VYS,VYD,VYO,YBC,VD> varseqComponentDefinitionFactory,
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
  public WkSerdeDTreeNodeStructComponentHandler<XO, YO, ZD> size() {
    return this.sizeComponent.body();
  }

  @Override
  public WkSerdeDTreeNodeStructComponentHandler<XO, YO, VD> variableSequence() {
    return this.varseqComponent.body();
  }

}
