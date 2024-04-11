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
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceReader;

public interface WkSerdeDtreeVariableSizePrimitiveArrayReader<
                        X extends WkPrimitiveArray<?, ?>,
                        S extends WkSerdeDtreeOperationSettingsVariableLength,
                        Q extends WkSerdeDtreeOperationInputRuntimeSequenceCommon<?>,
                        R extends WkSerdeDtreeOperationResult<X>,
                        D extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<X>>
    extends WkSerdeDtreeVariableSizeSequenceReader<X, S, Q, R, D>,
            WkSerdeDtreePrimitiveArrayReader<X, S, Q, R, D>,
            WkSerdeDtreeVariableSizePrimitiveArrayOperation<
                        S, Q, R, D,
                        WkSerdeDtreeNodeDataInputComponent<X,D,?>>
{

}
