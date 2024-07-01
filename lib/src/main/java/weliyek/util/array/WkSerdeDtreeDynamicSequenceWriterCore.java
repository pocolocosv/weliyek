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

import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceWriter;

public abstract class WkSerdeDtreeDynamicSequenceWriterCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YB extends WkSerdeDtreeBytestreamOutput,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<? extends YB>,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<YB>,
                        YQC extends WkSerdeDtreeOperationOutputRuntimeCtrl<YB,YBC,YQ>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YO extends WkSerdeDtreeDynamicSequenceWriter<
                                        T,YS,YQ,YR,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YOC extends WkSerdeDtreeDynamicSequenceWriterCore<
                                        T,YS,YB,YBC,YQ,YQC,YR,YO,?,YD,YDC,AYBC,
                                        ZT,ZYS,ZYO,ZYD,
                                        VYS,VYO,VYD>,
                        YD extends WkSerdeDtreeDynamicSequenceDefinition<T,?,YO,?,?>,
                        YDC extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,?,?,
                                        YS,YB,YBC,YQC,YR,YO,YOC,YD,?,AYBC,
                                        ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,
                                        ?,?,?,VYS,VYO,VYD,? extends VYD,
                                        ? extends YD,?>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT extends Number,
                        ZYS extends WkSerdeDtreeOperationSettings,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        VYS extends WkSerdeDtreeOperationSettings,
                        VYO extends WkSerdeDtreeVariableSizeSequenceWriter<T,VYS,?,?,VYD>,
                        VYD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>>
    extends WkSerdeDtreeAggregatorMsgWriterCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YDC, YO, YOC, AYBC>
    implements WkSerdeDtreeDynamicSequenceWriter<T, YS, YQ, YR, YD, ZT, ZYO, ZYD, VYO, VYD>
{

  protected WkSerdeDtreeDynamicSequenceWriterCore(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<T,YS,?,?,AYBC,?,?,?> writerFieldCore,
    YDC definitionCore,
    YO operationBody) {
    super(index, writerFieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onAggregatorInitialization() {
  }

  @Override
  public Optional<WkSerdeDtreeMsgOutputField<ZT, ZYD, ZYO>> size() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().sizeComponent));
  }

  @Override
  public Optional<WkSerdeDtreeMsgOutputField<T, VYD, VYO>> variableSequence() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().varseqComponent));
  }

}
