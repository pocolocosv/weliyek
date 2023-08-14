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
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkPrimitiveArraySrlzOutputPacketEncoderFrameNode;
import weliyek.util.array.WkPrimitiveArraySrlzStructDefinitionFrameNode;

public abstract class WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,YQ>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        YO extends WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNode<YS,YQ,YR,YD,SY,SYD,SYO>,
                        YOC extends WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore<YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYB,SY,SYS,SYO,SYD,DC>,
                        YD extends WkStringFromPrimitiveArraySrlzStructDefinitionFrameNode<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        SY extends WkPrimitiveArray<?,?>,
                        SYS extends WkSettingsSrlzPacketOperationData,
                        SYO extends WkPrimitiveArraySrlzOutputPacketEncoderFrameNode<SY,SYS,?,?,SYD>,
                        SYD extends WkPrimitiveArraySrlzStructDefinitionFrameNode<SY,?>,
                        DC extends WkSzStringFromPrimitiveDefinitionCore<?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYB,SY,?,?,?,SYS,SYO,SYD,?,?,DC>>
        extends WkAggregatorSrlzOutputPacketEncoderFrameNodeCore<String, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYB, DC>
        implements WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNode<YS, YQ, YR, YD, SY, SYD, SYO>
{

  protected WkSrlzOutputPacketSubfieldFrameNodeCore<SY,SYS,SYD,SYO,String,YBC,YD,YO> primitiveArraySubfieldpacket;

  protected WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNodeCore(
    int index,
    String serializable,
    YS settings,
    AYB parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<String,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected void onAggregatorInitialization() {
    primitiveArraySubfieldpacket = getSubfieldpacketFor(definitionCore().primitiveArraySubcomponent);
    onStringFromPrimitiveWritingInitialization();
  }

  protected abstract void onStringFromPrimitiveWritingInitialization();

  @Override
  public WkSrlzOutputPacketSubfieldFrameNode<SY,SYD,SYO> primitiveArray() {
    return primitiveArraySubfieldpacket.asSubfield();
  }

}
