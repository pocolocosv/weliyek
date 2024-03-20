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

import weliyek.serialization.WkAggregatorSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.serialization.sequence.WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.sequence.WkSerdeDTreeVariableSizeSequenceDefinition;

public abstract class WkDynamicSequenceSrlzInputPacketDecoderFrameNodeCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<XB>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,XQ>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        XO extends WkDynamicSequenceSrlzInputPacketDecoderFrameNode<T,XS,XQ,XR,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XOC extends WkDynamicSequenceSrlzInputPacketDecoderFrameNodeCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,
                                        ZT,ZXS,ZXO,ZXD,
                                        VXS,VXO,VXD,
                                        DC>,
                        XD extends WkDynamicSequenceSrlzStructDefinitionFrameNode<T,XO,?,?,?>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        ZT extends Number,
                        ZXS extends WkSettingsSrlzPacketOperationData,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VXO extends WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode<T,VXS,?,?,VXD>,
                        VXD extends WkSerdeDTreeVariableSizeSequenceDefinition<T>,
                        DC extends WkDynamicSequenceSrlzStructDefinitionFrameNodeCore<
                                        T,XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                        ?,?,?,?,?,?,?,?,
                                        ZT,ZXS,ZXO,ZXD,?,?,?,?,
                                        VXS,VXO,VXD,?,?,?,?,
                                        ?,DC>>
    extends WkAggregatorSrlzInputPacketDecoderFrameNodeCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXB, DC>
    implements WkDynamicSequenceSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, XD, ZT, ZXO, ZXD, VXO, VXD>
{

  private WkSrlzInputPacketSubfieldFrameNodeCore<ZT,ZXS,ZXD,ZXO,T,XBC,XD,XO>
                        sizeReadFieldHandler;
  private WkSrlzInputPacketSubfieldFrameNodeCore<T,VXS,VXD,VXO,T,XBC,XD,XO>
                        varseqReadFieldHandler;

  protected WkDynamicSequenceSrlzInputPacketDecoderFrameNodeCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.sizeReadFieldHandler = getSubfieldpacketFor(definitionCore().sizeComponent);
    this.varseqReadFieldHandler = getSubfieldpacketFor(definitionCore().varseqComponent);
  }

  @Override
  protected void onAggregatorReadingInitialization() {
  }

  @Override
  public WkSrlzInputPacketSubfieldFrameNode<ZT, ZXD, ZXO> size() {
    return this.sizeReadFieldHandler.asSubfield();
  }

  @Override
  public WkSrlzInputPacketSubfieldFrameNode<T, VXD, VXO> variableSequence() {
    return this.varseqReadFieldHandler.asSubfield();
  }

}
