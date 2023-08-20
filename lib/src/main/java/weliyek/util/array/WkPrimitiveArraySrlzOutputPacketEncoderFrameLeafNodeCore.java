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

import weliyek.serialization.WkPrimitiveArraySrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNodeCore;
import weliyek.serialization.WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameLeafNodeCore;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;

public abstract class WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore<
                        Y extends WkPrimitiveArrayBase<?,?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkSequenceEncodingRuntimeSrlzPacketOperationData<?>,
                        YQC extends WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<?,?,YQ>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        YO extends WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<Y,YS,YQ,YR,YD>,
                        YOC extends WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore<Y,YS,YQ,YQC,YR,YO,?,YD,AYB,DC>,
                        YD extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<Y>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNodeCore<Y,?,?,?,?,?,?,YS,YQC,YR,YD,YO,AYB,? extends YD,DC>>
        extends WkSrlzOutputPacketEncoderFrameLeafNodeCore<Y, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
        implements WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<Y, YS, YQ, YR, YD>
{

  private final int requestedLength;

  protected WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore(
    int index,
    Y serializable,
    YS settings,
    AYB parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<Y,?,YD,?,?,?> serializingfieldCore,
    DC definitionCore,
    YO operationBody) {
    super(
          index,
          serializable,
          settings,
          parentBytestream,
          serializingfieldCore,
          definitionCore,
          operationBody);
    this.requestedLength = definitionCore.txRequestedLengthEvaluator.retriveLength(
                                serializable, settings, definition());
  }

  @Override
  public int getRequestedLength() {
    return this.requestedLength;
  }

}
