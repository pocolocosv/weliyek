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

import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.sequence.WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode;

public interface WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameNode<
                        X extends WkPrimitiveArray<?, ?>,
                        S extends WkSzVariableLengthOperationSettings,
                        Q extends WkSequenceDecodingRuntimeSrlzPacketOperationData<?>,
                        R extends WkResultSrlzPacketOperationData<X>,
                        D extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameNode<X>>
    extends WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode<X, S, Q, R, D>,
            WkPrimitiveArraySrlzInputPacketDecoderFrameNode<X, S, Q, R, D>,
            WkVariableSizePrimitiveArraySrlzPacketOperationFrameNode<
                        S, Q, R, D,
                        WkSrlzInputPacketFieldFrameNode<X,D,?>>
{

}
