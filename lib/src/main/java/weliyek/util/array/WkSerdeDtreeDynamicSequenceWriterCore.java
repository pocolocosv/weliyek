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

import weliyek.serialization.WkSerdeDtreeAggregatorWriterCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
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
                                        T,YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYBC,
                                        ZT,ZYS,ZYO,ZYD,
                                        VYS,VYO,VYD,
                                        DC>,
                        YD extends WkSerdeDtreeDynamicSequenceDefinition<T,?,YO,?,?>,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT extends Number,
                        ZYS extends WkSerdeDtreeOperationSettings,
                        ZYO extends WkSerdeDtreeNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>,
                        VYS extends WkSerdeDtreeOperationSettings,
                        VYO extends WkSerdeDtreeVariableSizeSequenceWriter<T,VYS,?,?,VYD>,
                        VYD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>,
                        DC extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYBC,
                                        ZT,?,?,?,ZYS,ZYO,ZYD,
                                        ?,?,?,?,VYS,VYO,VYD,
                                        ?,?,DC>>
    extends WkSerdeDtreeAggregatorWriterCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYBC, DC>
    implements WkSerdeDtreeDynamicSequenceWriter<T, YS, YQ, YR, YD, ZT, ZYO, ZYD, VYO, VYD>
{

  private WkSrlzOutputPacketSubfieldFrameNodeCore<ZT,ZYS,ZYD,ZYO,T,?,YD,YO> sizeWriteField;
  private WkSrlzOutputPacketSubfieldFrameNodeCore<T,VYS,VYD,VYO,T,?,YD,YO> varseqWriteField;

  protected WkSerdeDtreeDynamicSequenceWriterCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.sizeWriteField = getSubfieldpacketFor(definitionCore().sizeComponent);
    this.varseqWriteField = getSubfieldpacketFor(definitionCore().varseqComponent);
  }

  @Override
  protected void onAggregatorInitialization() {
  }

  @Override
  public Optional<WkSerdeDtreeNodeDataOutputComponent<ZT, ZYD, ZYO>> size() {
    return this.sizeWriteField.field();
  }

  @Override
  public Optional<WkSerdeDtreeNodeDataOutputComponent<T, VYD, VYO>> variableSequence() {
    return this.varseqWriteField.field();
  }

}
