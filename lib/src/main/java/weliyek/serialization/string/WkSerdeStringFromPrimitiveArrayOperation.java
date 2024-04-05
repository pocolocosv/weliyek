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

import weliyek.serialization.WkCommonRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDtreeAggregatorOperation;
import weliyek.serialization.WkSerdeDtreeNodeDataComponent;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayOperation;

public interface WkSerdeStringFromPrimitiveArrayOperation<
                        S extends WkSettingsSrlzPacketOperationData,
                        Q extends WkCommonRuntimeSrlzPacketOperationData<?>,
                        R extends WkResultSrlzPacketOperationData<?>,
                        D extends WkSerdeStringFromPrimitiveArrayDefinition<?,?,?>,
                        K extends WkSerdeDtreeNodeDataComponent<?,?,?>,
                        SO extends WkSerdeDtreePrimitiveArrayOperation<?,?,?,?,?>,
                        SK extends WkSerdeDtreeNodeDataComponent<?,SO,?>>
        extends WkSerdeStringFromPrimitiveArray<Optional<SK>>,
                WkSerdeDtreeAggregatorOperation<S, Q, R, D, K>
{

}
