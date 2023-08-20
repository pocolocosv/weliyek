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
package weliyek.serialization;

import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkPrimitiveArraySrlzOutputPacketEncoderFrameNode;

public interface WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<
                        Y extends WkPrimitiveArray<?, ?>,
                        S extends WkSettingsSrlzPacketOperationData,
                        Q extends WkSequenceEncodingRuntimeSrlzPacketOperationData<?>,
                        R extends WkEncodingResultSrlzPacketOperationData,
                        D extends WkPrimitiveArraySrlzStructDefinitionFrameLeafNode<Y>>
    extends WkSrlzOutputPacketEncoderFrameLeafNode<Y, S, Q, R, D>,
            WkPrimitiveArraySrlzOutputPacketEncoderFrameNode<Y, S, Q, R, D>,
            WkPrimitiveArraySrlzPacketOperationFrameLeafNode<
                        S,Q,R,D,
                        WkSrlzOutputPacketFieldFrameNode<Y,D,?>>
{

}
