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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;

public interface WkDynamicPrimitiveArraySrlzInputPacketDecoderFrameNode<
                        T extends WkPrimitiveArray<?,?>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<?>,
                        XR extends WkDecodingResultSrlzPacketOperationData<T>,
                        XD extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,?,?,?,ZD>,
                        ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,ZXO>,
                        VXO extends WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameNode<T,?,?,?,VD>,
                        VD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T,VXO>>
    extends WkDynamicPrimitiveArraySrlzPacketOperationFrameNode<
                        XS, XQ, XR, XD,
                        WkSrlzInputPacketFieldFrameNode<T,XD,?>,
                        ZXO,
                        WkSrlzInputPacketFieldFrameNode<ZT,ZD,ZXO>,
                        WkSrlzInputPacketSubfieldFrameNode<ZT,ZD,ZXO>,
                        VXO,
                        WkSrlzInputPacketFieldFrameNode<T,VD,VXO>,
                        WkSrlzInputPacketSubfieldFrameNode<T,VD,VXO>>,
            WkDynamicSequenceSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, XD, ZT, ZXO, ZD, VXO, VD>
{

}
