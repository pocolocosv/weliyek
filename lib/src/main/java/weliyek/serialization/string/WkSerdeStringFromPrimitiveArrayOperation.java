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
package weliyek.serialization.string;

import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeOperationRuntimeCommon;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgOperation;
import weliyek.serialization.WkSerdeDtreeMsgField;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayOperation;

public interface WkSerdeStringFromPrimitiveArrayOperation<
                        S extends WkSerdeDtreeOperationSettings,
                        Q extends WkSerdeDtreeOperationRuntimeCommon<?>,
                        R extends WkSerdeDtreeOperationResult<?>,
                        D extends WkSerdeStringFromPrimitiveArrayDefinition<?,?,?>,
                        K extends WkSerdeDtreeMsgField<?,?,?>,
                        SO extends WkSerdeDtreePrimitiveArrayOperation<?,?,?,?,?>,
                        SK extends WkSerdeDtreeMsgField<?,SO,?>>
        extends WkSerdeStringFromPrimitiveArray<Optional<SK>>,
                WkSerdeDtreeAggregatorMsgOperation<S, Q, R, D, K>
{

}
