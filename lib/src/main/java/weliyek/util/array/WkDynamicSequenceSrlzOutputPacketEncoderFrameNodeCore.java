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

import weliyek.serialization.WkAggregatorSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.sequence.WkVariableSizeSequenceSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.sequence.WkVariableSizeSequenceSrlzStructDefinitionFrameNode;

public abstract class WkDynamicSequenceSrlzOutputPacketEncoderFrameNodeCore<
                        T,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,YQ>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        YO extends WkDynamicSequenceSrlzOutputPacketEncoderFrameNode<
                                        T,YS,YQ,YR,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YOC extends WkDynamicSequenceSrlzOutputPacketEncoderFrameNodeCore<
                                        T,YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYBC,
                                        ZT,ZYS,ZYO,ZYD,
                                        VYS,VYO,VYD,
                                        DC>,
                        YD extends WkDynamicSequenceSrlzStructDefinitionFrameNode<T,?,YO,?,?>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        ZT extends Number,
                        ZYS extends WkSettingsSrlzPacketOperationData,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT>,
                        VYS extends WkSettingsSrlzPacketOperationData,
                        VYO extends WkVariableSizeSequenceSrlzOutputPacketEncoderFrameNode<T,VYS,?,?,VYD>,
                        VYD extends WkVariableSizeSequenceSrlzStructDefinitionFrameNode<T>,
                        DC extends WkDynamicSequenceSrlzStructDefinitionFrameNodeCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYBC,
                                        ZT,?,?,?,ZYS,ZYO,ZYD,
                                        ?,?,?,?,VYS,VYO,VYD,
                                        ?,?,DC>>
    extends WkAggregatorSrlzOutputPacketEncoderFrameNodeCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYBC, DC>
    implements WkDynamicSequenceSrlzOutputPacketEncoderFrameNode<T, YS, YQ, YR, YD, ZT, ZYO, ZYD, VYO, VYD>
{

  private WkSrlzOutputPacketSubfieldFrameNodeCore<ZT,ZYS,ZYD,ZYO,T,?,YD,YO> sizeWriteField;
  private WkSrlzOutputPacketSubfieldFrameNodeCore<T,VYS,VYD,VYO,T,?,YD,YO> varseqWriteField;

  protected WkDynamicSequenceSrlzOutputPacketEncoderFrameNodeCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
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
  public WkSrlzOutputPacketSubfieldFrameNode<ZT, ZYD, ZYO> size() {
    return this.sizeWriteField.asSubfield();
  }

  @Override
  public WkSrlzOutputPacketSubfieldFrameNode<T, VYD, VYO> variableSequence() {
    return this.varseqWriteField.asSubfield();
  }

}
