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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameLeafNodeCore;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSzOutputBytestreamBase;

public abstract class WkNumberSrlzOutputPacketEncoderFrameLeafNodeCore<
                        T extends Number,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<?>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<?,?,YQ>,
                        YR extends WkResultSrlzPacketOperationData<T>,
                        YO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<T,YS,YQ,YR,YD>,
                        YOC extends WkNumberSrlzOutputPacketEncoderFrameLeafNodeCore<T,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkSerdeDTreeNumberDefinition<T>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkNumberSrlzStructDefinitionFrameNodeCore<T,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
    extends WkSrlzOutputPacketEncoderFrameLeafNodeCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
    implements WkNumberSrlzOutputPacketEncoderFrameLeafNode<T, YS, YQ, YR, YD>
{

  protected WkNumberSrlzOutputPacketEncoderFrameLeafNodeCore(
    int index,
    T serializable,
    YS settings,
    AYB parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
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
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
