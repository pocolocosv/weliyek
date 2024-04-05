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

import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorWriterCore;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayWriter;

public abstract class WkSerdeStringFromPrimitiveArrayWriterCore<
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,YQ>,
                        YR extends WkResultSrlzPacketOperationData<String>,
                        YO extends WkSerdeStringFromPrimitiveArrayWriter<YS,YQ,YR,YD,SY,SYD,SYO>,
                        YOC extends WkSerdeStringFromPrimitiveArrayWriterCore<YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYB,SY,SYS,SYO,SYD,DC>,
                        YD extends WkSerdeStringFromPrimitiveArrayDefinition<?,YO,? extends SYD>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        SY extends WkPrimitiveArray<?,?>,
                        SYS extends WkSettingsSrlzPacketOperationData,
                        SYO extends WkSerdeDtreePrimitiveArrayWriter<SY,SYS,?,?,SYD>,
                        SYD extends WkSerdeDtreePrimitiveArrayDefinition<SY>,
                        DC extends WkSerdeStringFromPrimitiveArrayDefinitionCore<?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYB,SY,?,?,?,SYS,SYO,SYD,?,?,DC>>
        extends WkSerdeDtreeAggregatorWriterCore<String, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYB, DC>
        implements WkSerdeStringFromPrimitiveArrayWriter<YS, YQ, YR, YD, SY, SYD, SYO>
{

  protected WkSrlzOutputPacketSubfieldFrameNodeCore<SY,SYS,SYD,SYO,String,YBC,YD,YO> primitiveArraySubfieldpacket;

  protected WkSerdeStringFromPrimitiveArrayWriterCore(
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
  public Optional<WkSrlzOutputPacketFieldFrameNode<SY, SYD, SYO>> primitiveArray() {
    return primitiveArraySubfieldpacket.field();
  }

}
