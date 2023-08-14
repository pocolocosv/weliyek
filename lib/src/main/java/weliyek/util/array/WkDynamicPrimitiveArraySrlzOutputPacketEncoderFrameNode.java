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
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;

public interface WkDynamicPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                        T extends WkPrimitiveArray<?,?>,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<?>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        D extends WkDynamicPrimitiveArraySrlzStructDefinitionFrameNode<T,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,?,?,?,ZD>,
                        ZD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        VYO extends WkVariableSizePrimitiveArraySrlzOutputPacketEncoderFrameNode<T,?,?,?,VD>,
                        VD extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<T,?>>
    extends WkDynamicPrimitiveArraySrlzPacketOperationFrameNode<
                        YS, YQ, YR, D,
                        WkSrlzOutputPacketFieldFrameNode<T,D,?>,
                        ZYO,
                        WkSrlzOutputPacketFieldFrameNode<ZT,ZD,ZYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<ZT,ZD,ZYO>,
                        VYO,
                        WkSrlzOutputPacketFieldFrameNode<T,VD,VYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<T,VD,VYO>>,
            WkDynamicSequenceSrlzOutputPacketEncoderFrameNode<T, YS, YQ, YR, D, ZT, ZYO, ZD, VYO, VD>
{

}
