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
package weliyek.serialization.sequence;

import java.util.Collection;

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataWriter;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkSerdeDTreeNumberWriter;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.util.array.WkDynamicSequenceSrlzOutputPacketEncoderFrameNodeCore;

public final class WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<
                        T extends Collection<ET>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YO extends WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                                        T,YS,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        YD,ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD extends WkDynamicCollectionSrlzStructDefinitionFrameNode<
                                        T,?,YO,?,ET,?,?,?,EYS,?,EYO,?,?,VYS>,
                        ZT extends Number,
                        ZYS extends WkSettingsSrlzPacketOperationData,
                        ZYO extends WkSerdeDTreeNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ET,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        VYS extends WkSettingsSrlzPacketOperationData>
    extends WkDynamicSequenceSrlzOutputPacketEncoderFrameNodeCore<
                        T, YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        YO,
                        WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<
                          T,YS,YO,YD,ZT,ZYS,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT, ZYS, ZYO, ZYD, VYS,
                        WkVariableSizeCollectionSrlzOutputNode<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        WkVariableSizeCollectionSrlzStructNode<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>, // VYD
                        WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<
                          T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS,?>>
    implements WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                        T,
                        YS,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<T>,
                        YD, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
{

  public WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T, ?, YD, ?, ?, ?> packetHandlerCore,
    WkSimplifiedDynamicCollectionSrlzStructDefinitionFrameNodeCore<T, ?, ?, ?, YS, YO, YD, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS, ?> definitionCore,
    YO operationBody) {
    super(
          index,
          serializable,
          settings,
          parentBytestream,
          packetHandlerCore,
          definitionCore,
          operationBody);
  }

  @Override
  protected
  WkSimplifiedDynamicCollectionSrlzOutputPacketEncoderFrameNodeCore<T, YS, YO, YD, ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
  getThis() {
    return this;
  }

}
