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

import weliyek.serialization.WkSerdeDTreePrimitiveArrayLeafWriter;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;

public interface WkSerdeDTreeVariableSizePrimitiveArrayLeafWriter<
                        Y extends WkPrimitiveArray<?, ?>,
                        S extends WkSettingsSrlzPacketOperationData,
                        Q extends WkSequenceEncodingRuntimeSrlzPacketOperationData<?>,
                        R extends WkResultSrlzPacketOperationData<Y>,
                        D extends WkSerdeDTreeVariableSizePrimitiveArrayLeafDefinition<Y>>
    extends WkVariableSizePrimitiveArraySrlzOutputPacketEncoderFrameNode<Y, S, Q, R, D>,
            WkSerdeDTreePrimitiveArrayLeafWriter<Y, S, Q, R, D>,
            WkSerdeDTreeVariableSizePrimitiveArrayLeafOperation<
                        S, Q, R, D,
                        WkSrlzOutputPacketFieldFrameNode<Y,D,?>>
{

}