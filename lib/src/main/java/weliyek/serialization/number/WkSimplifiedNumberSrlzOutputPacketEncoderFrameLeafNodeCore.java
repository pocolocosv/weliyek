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
package weliyek.serialization.number;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;

public class WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<
                        Y extends Number,
                        YO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<
                                        Y,
                                        WkSettingsSrlzPacketOperationData,
                                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                                        WkEncodingResultSrlzPacketOperationData,
                                        YD>,
                        YD extends WkNumberSrlzStructDefinitionFrameLeafNode<Y>>
    extends WkNumberSrlzOutputPacketEncoderFrameLeafNodeCore<
                        Y,
                        WkSettingsSrlzPacketOperationData,
                        WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>,
                        WkEncodingRuntimeSrlzPacketOperationCtrl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>,
                        WkEncodingResultSrlzPacketOperationData,
                        YO,
                        WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<Y,YO,YD>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<Y,?,YO,YD>>
{

  WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore(
    int index,
    Y serializable,
    WkSettingsSrlzPacketOperationData settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Y,?,YD,?,?,?> packetHandlerCore,
    WkNumberSimplifiedSrlzStructDefinitionFrameNodeCore<Y,?,YO,YD> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  public long expectedBytes() {
    return 0;
  }

  @Override
  protected WkSimplifiedNumberSrlzOutputPacketEncoderFrameLeafNodeCore<Y,YO,YD> getThis() {
    return this;
  }

  @Override
  protected void onSerializingOperationInitialization() {
    // Nothing to do.
  }

}
