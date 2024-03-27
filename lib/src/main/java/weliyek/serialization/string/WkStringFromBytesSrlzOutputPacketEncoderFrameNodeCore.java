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
package weliyek.serialization.string;

import java.nio.charset.Charset;

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDTreeByteArrayWriter;
import weliyek.util.array.WkSerdeDTreeByteArrayDefinition;

public abstract class WkStringFromBytesSrlzOutputPacketEncoderFrameNodeCore<
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,YQ>,
                        YR extends WkResultSrlzPacketOperationData<String>,
                        YO extends WkStringFromBytesSrlzOutputPacketEncoderFrameNode<YS,YQ,YR,YD,SYD,SYO>,
                        YOC extends WkStringFromBytesSrlzOutputPacketEncoderFrameNodeCore<YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYB,SYS,SYO,SYD,DC>,
                        YD extends WkStringFromBytesSrlzStructDefinitionFrameNode<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        SYS extends WkSettingsSrlzPacketOperationData,
                        SYO extends WkSerdeDTreeByteArrayWriter<SYS,?,?,SYD>,
                        SYD extends WkSerdeDTreeByteArrayDefinition,
                        DC extends WkStringFromBytesSrlzStructDefinitionFrameNodeCore<
                                      ?,?,?,?,?,?,?,?,
                                      YS,YB,YBC,YQC,YR,YO,YD,AYB,
                                      ?,?,?,SYS,SYO,SYD,?,?,DC>>
    extends WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<
                        YS,YB,YBC,YQ,YQC,YR,YO,YOC,YD,AYB,WkByteArray,SYS,SYO,SYD,DC>
    implements WkStringFromBytesSrlzOutputPacketEncoderFrameNode<YS, YQ, YR, YD, SYD, SYO>
{

  protected WkStringFromBytesSrlzOutputPacketEncoderFrameNodeCore(
    int index,
    String serializable,
    YS settings,
    AYB parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<String,?,YD,?,?,?> packetHandlerCore,
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
  public final WkSrlzOutputPacketSubfieldFrameNode<WkByteArray, SYD, SYO> bytes() {
    return primitiveArray();
  }

  @Override
  public final Charset charset() {
    return definition().charset();
  }

}
