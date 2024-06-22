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

import weliyek.serialization.WkOperationSettingsFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCore;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCoreFactory;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceReader;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceWriter;

public abstract class WkSerdeDtreeDynamicSequenceDefinitionCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XO extends WkSerdeDtreeDynamicSequenceReader<
                                        T, XS,
                                        ? extends WkSerdeDtreeOperationInputRuntime<XB>,
                                        XR, XD, ZT, ZXO, ZXD, VXO, VXD>,
                        XOC extends WkSerdeDtreeDynamicSequenceReaderCore<
                                        T,XS,XB,XBC,?,XQC,XR,XO,?,XD,XDC,AXBC,
                                        ZT,ZXS,ZXO,ZXD,
                                        VXS,VXO,VXD>,
                        XD extends WkSerdeDtreeDynamicSequenceDefinition<T,XO,?,?,?>,
                        XDC extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XO,XOC,XD,?,AXBC,
                                        ?,?,?,?,?,?,?,?,?,?,
                                        ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,
                                        VXS,VXO,VXD,?,?,?,? extends VXD,
                                        ? extends XD,?>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YO extends WkSerdeDtreeDynamicSequenceWriter<
                                        T, YS,
                                        ? extends WkSerdeDtreeOperationOutputRuntime<YB>,
                                        YR, YD, ?, ?, ?, ?, ?>,
                        YOC extends WkSerdeDtreeAggregatorMsgWriterCore<
                                        T,YS,YB,YBC,?,YQC,YR,YD,YDC,YO,?,AYBC>,
                        YD extends WkSerdeDtreeDynamicSequenceDefinition<T,?,YO,?,?>,
                        YDC extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,?,?,
                                        YS,YB,YBC,YQC,YR,YO,YOC,YD,YDC,AYBC,
                                        ?,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,
                                        ?,?,?,VYS,VYO,VYD,? extends VYD,
                                        ? extends YD,?>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT extends Number,
                        ZXS extends WkSerdeDtreeOperationSettings,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZYS extends WkSerdeDtreeOperationSettings,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ZD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength,
                        VXO extends WkSerdeDtreeVariableSizeSequenceReader<T,VXS,?,?,VXD>,
                        VXD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>,
                        VYS extends WkSerdeDtreeOperationSettings,
                        VYO extends WkSerdeDtreeVariableSizeSequenceWriter<T,VYS,?,?,VYD>,
                        VYD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>,
                        VD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>,
                        D extends WkSerdeDtreeDynamicSequenceDefinition<T,XO,YO,ZD,VD>,
                        DC extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                                      T,XS,XB,XBC,XQC,XR,XO,XOC,XD,XDC,AXBC,
                                      YS,YB,YBC,YQC,YR,YO,YOC,YD,YDC,AYBC,
                                      ZT,ZXS,ZXO,ZXD,ZYS,ZYO,ZYD,ZD,
                                      VXS,VXO,VXD,VYS,VYO,VYD,VD,
                                      D,?>>
    extends WkSerdeDtreeAggregatorStructDefinitionCore<
                        T, XS, XB, XBC, XQC, XR, XD, XDC, XO, XOC, AXBC,
                        YS, YB, YBC, YQC, YR, YD, YDC, YO, YOC, AYBC, D, DC>
    implements WkSerdeDtreeDynamicSequenceDefinition<T, XO, YO, ZD, VD>
{

  final WkSerdeDtreeStructSubfieldCore<ZT,T,ZXS,ZXD,ZXO,XBC,XO,ZYS,ZYD,ZYO,YBC,YO,ZD>
                        sizeComponent;
  final WkSerdeDtreeStructSubfieldCore<T,T,VXS,VXD,VXO,XBC,XO,VYS,VYD,VYO,YBC,YO,VD>
                        varseqComponent;

  protected WkSerdeDtreeDynamicSequenceDefinitionCore(
    String sizeComponentLabel,
    WkOperationSettingsFactory<XO,ZXS> sizeComponentRxSettingsFactory,
    WkOperationSettingsFactory<YO, ZYS> sizeComponentTxSettingsFactory,
    WkSzPacketWriteDisaggregator<ZT, ZYD, T, YO> sizeComponentTxDesaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<ZT,ZXS,ZXO,XBC,ZYS,ZYO,YBC,ZD> sizeComponentDefinitionFactory,
    String varseqComponentLabel,
    WkOperationSettingsFactory<XO,VXS> varseqComponentRxSettingsFactory,
    WkOperationSettingsFactory<YO,VYS> varseqComponentTxSettingsFactory,
    WkSzPacketWriteDisaggregator<T, VYD, T, YO> varseqComponentTxDesaggregator,
    WkSrlzStructDefinitionFrameNodeCoreFactory<T,VXS,VXO,XBC,VYS,VYO,YBC,VD> varseqComponentDefinitionFactory,
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
    this.sizeComponent = this.<ZT,ZXS,ZXD,ZXO,ZYS,ZYD,ZYO,ZD>addSubcomponent(
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
  public WkSerdeDtreeStructSubfield<XO, YO, ZD> size() {
    return this.sizeComponent.asProtocolField();
  }

  @Override
  public WkSerdeDtreeStructSubfield<XO, YO, VD> variableSequence() {
    return this.varseqComponent.asProtocolField();
  }

}
