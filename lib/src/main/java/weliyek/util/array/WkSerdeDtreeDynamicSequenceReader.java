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

import weliyek.serialization.WkSerdeDtreeAggregatorReader;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.number.WkSerdeDtreeNumberReader;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeSequenceReader;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceReader;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceDefinition;

public interface WkSerdeDtreeDynamicSequenceReader<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<?>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        D extends WkSerdeDtreeDynamicSequenceDefinition<T,?,?,?,?>,
                        ZX extends Number,
                        ZXO extends WkSerdeDtreeNumberReader<ZX,?,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberDefinition<ZX>,
                        VXO extends WkSerdeDtreeVariableSizeSequenceReader<T,?,?,?,VXD>,
                        VXD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>>
    extends WkSerdeDtreeDynamicSequenceOperation<
                        XS, XQ, XR, D,
                        WkSrlzInputPacketFieldFrameNode<T,D,?>,
                        ZXO,
                        WkSrlzInputPacketFieldFrameNode<ZX,ZXD,ZXO>,
                        WkSrlzInputPacketSubfieldFrameNode<ZX,ZXD,ZXO>,
                        VXO,
                        WkSrlzInputPacketFieldFrameNode<T,VXD,VXO>,
                        WkSrlzInputPacketSubfieldFrameNode<T,VXD,VXO>>,
            WkSerdeDtreeSequenceReader<T, XS, XQ, XR, D>,
            WkSerdeDtreeAggregatorReader<T, XS, XQ, XR, D>
{

}
