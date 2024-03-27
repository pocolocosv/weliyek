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

import weliyek.serialization.WkSerdeDtreeAggregatorWriter;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeSequenceWriter;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceWriter;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceDefinition;

public interface WkSerdeDtreeDynamicSequenceWriter<
                        T,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<?>,
                        YR extends WkResultSrlzPacketOperationData<T>,
                        D extends WkSerdeDtreeDynamicSequenceDefinition<T,?,?,?,?>,
                        ZY extends Number,
                        ZYO extends WkSerdeDtreeNumberWriter<ZY,?,?,?,ZD>,
                        ZD extends WkSerdeDtreeNumberDefinition<ZY>,
                        VYO extends WkSerdeDtreeVariableSizeSequenceWriter<T,?,?,?,VD>,
                        VD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>>
    extends WkSerdeDtreeDynamicSequenceOperation<
                        YS, YQ, YR, D,
                        WkSrlzOutputPacketFieldFrameNode<T,D,?>,
                        ZYO,
                        WkSrlzOutputPacketFieldFrameNode<ZY,ZD,ZYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<ZY,ZD,ZYO>,
                        VYO,
                        WkSrlzOutputPacketFieldFrameNode<T,VD,VYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<T,VD,VYO>>,
            WkSerdeDtreeSequenceWriter<T, YS, YQ, YR, D>,
            WkSerdeDtreeAggregatorWriter<T, YS, YQ, YR, D>
{

}
