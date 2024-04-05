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
package weliyek.serialization.sequence;

import java.util.Optional;

import weliyek.serialization.WkCommonRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDtreeNodeDataComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataComponentHandler;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.number.WkSerdeDtreeNumberOperation;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceOperation;

public interface WkSerdeDtreeDynamicCollectionOperation<
                        S extends WkSettingsSrlzPacketOperationData,
                        Q extends WkCommonRuntimeSrlzPacketOperationData<?>,
                        R extends WkResultSrlzPacketOperationData<?>,
                        D extends WkSerdeDtreeDynamicCollectionDefinition<?,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        K extends WkSerdeDtreeNodeDataComponent<?,?,?>,
                        ZO extends WkSerdeDtreeNumberOperation<?,?,?,?,?>,
                        ZK extends WkSerdeDtreeNodeDataComponent<?,ZO,?>,
                        ZJ extends WkSerdeDtreeNodeDataComponentHandler<ZK>,
                        VO extends WkSerdeDtreeVariableSizeSequenceOperation<?,?,?,?,?>,
                        VK extends WkSerdeDtreeNodeDataComponent<?,VO,?>,
                        VJ extends WkSerdeDtreeNodeDataComponentHandler<VK>>
    extends WkSerdeDtreeDynamicCollection<Optional<ZK>, Optional<VK>>,
            WkSerdeDtreeCollectionOperation<S, Q, R, D, K>,
            WkSerdeDtreeDynamicSequenceOperation<S, Q, R, D, K, ZO, ZK, VO, VK>
{

}
