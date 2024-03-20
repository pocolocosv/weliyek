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

import weliyek.serialization.WkAggregatorSrlzPacketOperationFrameNode;
import weliyek.serialization.WkCommonRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataComponent;
import weliyek.serialization.WkSerdeDTreeNodeDataComponentHandler;
import weliyek.serialization.number.WkSerdeDTreeNumberOperation;
import weliyek.serialization.sequence.WkSerdeDTreeSequenceOperation;
import weliyek.serialization.sequence.WkSerdeDTreeVariableSizeSequenceOperation;

public interface WkDynamicSequenceSrlzPacketOperationFrameNode<
                        S extends WkSettingsSrlzPacketOperationData,
                        Q extends WkCommonRuntimeSrlzPacketOperationData<?>,
                        R extends WkResultSrlzPacketOperationData<?>,
                        D extends WkDynamicSequenceSrlzStructDefinitionFrameNode<?,?,?,?,?>,
                        K extends WkSerdeDTreeNodeDataComponent<?,?,?>,
                        ZO extends WkSerdeDTreeNumberOperation<?,?,?,?,?>,
                        ZK extends WkSerdeDTreeNodeDataComponent<?,ZO,?>,
                        ZJ extends WkSerdeDTreeNodeDataComponentHandler<ZK>,
                        VO extends WkSerdeDTreeVariableSizeSequenceOperation<?,?,?,?,?>,
                        VK extends WkSerdeDTreeNodeDataComponent<?,VO,?>,
                        VJ extends WkSerdeDTreeNodeDataComponentHandler<VK>>
    extends WkDynamicSequenceSrlzFrameNode<ZJ, VJ>,
            WkSerdeDTreeSequenceOperation<S, Q, R, D, K>,
            WkAggregatorSrlzPacketOperationFrameNode<S, Q, R, D, K>
{

}
