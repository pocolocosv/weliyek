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

import weliyek.serialization.WkAggregatorSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.serialization.sequence.WkSerdeDTreeSequenceWriter;
import weliyek.serialization.sequence.WkVariableSizeSequenceSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.sequence.WkSerdeDTreeVariableSizeSequenceDefinition;

public interface WkDynamicSequenceSrlzOutputPacketEncoderFrameNode<
                        T,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<?>,
                        YR extends WkResultSrlzPacketOperationData<T>,
                        D extends WkDynamicSequenceSrlzStructDefinitionFrameNode<T,?,?,?,?>,
                        ZY extends Number,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZY,?,?,?,ZD>,
                        ZD extends WkSerdeDTreeNumberDefinition<ZY>,
                        VYO extends WkVariableSizeSequenceSrlzOutputPacketEncoderFrameNode<T,?,?,?,VD>,
                        VD extends WkSerdeDTreeVariableSizeSequenceDefinition<T>>
    extends WkDynamicSequenceSrlzPacketOperationFrameNode<
                        YS, YQ, YR, D,
                        WkSrlzOutputPacketFieldFrameNode<T,D,?>,
                        ZYO,
                        WkSrlzOutputPacketFieldFrameNode<ZY,ZD,ZYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<ZY,ZD,ZYO>,
                        VYO,
                        WkSrlzOutputPacketFieldFrameNode<T,VD,VYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<T,VD,VYO>>,
            WkSerdeDTreeSequenceWriter<T, YS, YQ, YR, D>,
            WkAggregatorSrlzOutputPacketEncoderFrameNode<T, YS, YQ, YR, D>
{

}
