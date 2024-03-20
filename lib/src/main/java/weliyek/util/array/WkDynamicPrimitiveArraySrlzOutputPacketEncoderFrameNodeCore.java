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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;

public class WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<
                        T extends WkPrimitiveArray<?,?>,
                        YO extends WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,
                                        WkSettingsSrlzPacketOperationData,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkResultSrlzPacketOperationData<T>,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,?,YO,? extends ZYD,? extends VYD>,
                        ZT extends Number,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                                        ZT,
                                        WkSettingsSrlzPacketOperationData,
                                        ?,?,ZYD>,
                        ZYD extends WkSerdeDTreeNumberDefinition<ZT>,
                        VYO extends WkVariableSizePrimitiveArraySrlzOutputPacketEncoderFrameNode<
                                        T,
                                        WkSettingsSrlzPacketOperationData,
                                        ?,?,VYD>,
                        VYD extends WkSerdeDTreeVariableSizePrimitiveArrayDefinition<T>>
    extends WkDynamicSequenceSrlzOutputPacketEncoderFrameNodeCore<
                        T,
                        WkSettingsSrlzPacketOperationData,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkResultSrlzPacketOperationData<T>,
                        YO,
                        WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT,
                        WkSettingsSrlzPacketOperationData,
                        ZYO,
                        ZYD,
                        WkSettingsSrlzPacketOperationData,
                        VYO,
                        VYD,
                        WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
                          T,?,?,YD,YO,
                          ZT,?,?,ZYD,ZYO,?,
                          ?,?,VYD,VYO,?,?>>
    implements WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                        T,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkResultSrlzPacketOperationData<T>,
                        YD, ZT, ZYO, ZYD, VYO, VYD>
{

  public WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore(
    int index,
    T serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
    WkDynamicPrimitiveArraySrlzStructDefinitionFrameNodeCore<
      T,?,?,YD,YO,
      ZT,?,?,ZYD,ZYO,?,
      ?,?,VYD,VYO,?,?> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>
  getThis() {
    return this;
  }

}
