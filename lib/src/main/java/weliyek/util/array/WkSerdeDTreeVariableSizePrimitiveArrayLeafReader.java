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

import weliyek.serialization.WkSerdeDTreePrimitiveArrayLeafReader;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSzVariableLengthOperationSettings;

public interface WkSerdeDTreeVariableSizePrimitiveArrayLeafReader<
                        X extends WkPrimitiveArray<?, ?>,
                        S extends WkSzVariableLengthOperationSettings,
                        Q extends WkSequenceDecodingRuntimeSrlzPacketOperationData<?>,
                        R extends WkResultSrlzPacketOperationData<X>,
                        D extends WkSerdeDTreeVariableSizePrimitiveArrayLeafDefinition<X>>
    extends WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameNode<X, S, Q, R, D>,
            WkSerdeDTreePrimitiveArrayLeafReader<X, S, Q, R, D>,
            WkSerdeDTreeVariableSizePrimitiveArrayLeafOperation<
                        S, Q, R, D,
                        WkSrlzInputPacketFieldFrameNode<X,D,?>>
{

}
