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

import weliyek.serialization.WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;

public interface WkFixedSizePrimitiveArraySrlzInputPacketDecoderFrameLeafNode<
                        X extends WkPrimitiveArray<?,?>,
                        S extends WkSettingsSrlzPacketOperationData,
                        Q extends WkSequenceDecodingRuntimeSrlzPacketOperationData<?>,
                        R extends WkResultSrlzPacketOperationData<X>,
                        D extends WkFixedSizePrimitiveArraySrlzStructDefinitionFrameLeafNode<X>>
    extends WkFixedSizePrimitiveArraySrlzInputPacketDecoderFrameNode<X, S, Q, R, D>,
            WkPrimitiveArraySrlzInputPacketDecoderFrameLeafNode<X, S, Q, R, D>,
            WkFixedSizePrimitiveArraySrlzPacketOperationFrameLeafNode<
                        S, Q, R, D,
                        WkSrlzInputPacketFieldFrameNode<X,D,?>>
{

}
