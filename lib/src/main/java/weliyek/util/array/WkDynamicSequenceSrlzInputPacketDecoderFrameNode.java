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

import weliyek.serialization.WkAggregatorSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.sequence.WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.sequence.WkSequenceSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.sequence.WkVariableSizeSequenceSrlzStructDefinitionFrameNode;

public interface WkDynamicSequenceSrlzInputPacketDecoderFrameNode<
                        T,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<T>,
                        D extends WkDynamicSequenceSrlzStructDefinitionFrameNode<T,?,?,?,?>,
                        ZX extends Number,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZX,?,?,?,ZXD>,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZX,?>,
                        VXO extends WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode<T,?,?,?,VXD>,
                        VXD extends WkVariableSizeSequenceSrlzStructDefinitionFrameNode<T,VXO>>
    extends WkDynamicSequenceSrlzPacketOperationFrameNode<
                        XS, XQ, XR, D,
                        WkSrlzInputPacketFieldFrameNode<T,D,?>,
                        ZXO,
                        WkSrlzInputPacketFieldFrameNode<ZX,ZXD,ZXO>,
                        WkSrlzInputPacketSubfieldFrameNode<ZX,ZXD,ZXO>,
                        VXO,
                        WkSrlzInputPacketFieldFrameNode<T,VXD,VXO>,
                        WkSrlzInputPacketSubfieldFrameNode<T,VXD,VXO>>,
            WkSequenceSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, D>,
            WkAggregatorSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, D>
{

}
