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

import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataComponent;
import weliyek.serialization.sequence.WkSerdeDtreeOperationRuntimeSequenceCommon;
import weliyek.serialization.sequence.WkSerdeDtreeFixedSizeSequenceOperation;

public interface WkSerdeDtreeFixedSizePrimitiveArrayOperation<
                        S extends WkSerdeDtreeOperationSettings,
                        Q extends WkSerdeDtreeOperationRuntimeSequenceCommon<?>,
                        R extends WkSerdeDtreeOperationResult<?>,
                        D extends WkSerdeDtreeFixedSizePrimitiveArrayDefinition<?>,
                        K extends WkSerdeDtreeNodeDataComponent<?,?,?>>
    extends WkSerdeDtreeFixedSizePrimitiveArray,
            WkSerdeDtreePrimitiveArrayOperation<S, Q, R, D, K>,
            WkSerdeDtreeFixedSizeSequenceOperation<S, Q, R, D, K>
{

}
