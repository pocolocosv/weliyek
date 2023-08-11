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

import weliyek.serialization.WkSzAggregatorOperation;
import weliyek.serialization.WkSzCommonOperationRuntime;
import weliyek.serialization.WkSzOperationResult;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketField;
import weliyek.serialization.WkSzPacketSubfield;
import weliyek.serialization.number.WkSzNumberOperation;
import weliyek.serialization.sequence.WkSzSequenceOperation;
import weliyek.serialization.sequence.WkSzVariableSizeSequenceOperation;

public interface WkSzDynamicSequenceOperation<
                        S extends WkSzOperationSettings,
                        Q extends WkSzCommonOperationRuntime<?>,
                        R extends WkSzOperationResult,
                        D extends WkSzDynamicSequenceDefinition<?,?,?,?,?>,
                        K extends WkSzPacketField<?,?,?>,
                        ZO extends WkSzNumberOperation<?,?,?,?,?>,
                        ZK extends WkSzPacketField<?,ZO,?>,
                        ZJ extends WkSzPacketSubfield<ZK>,
                        VO extends WkSzVariableSizeSequenceOperation<?,?,?,?,?>,
                        VK extends WkSzPacketField<?,VO,?>,
                        VJ extends WkSzPacketSubfield<VK>>
    extends WkSzDynamicSequenceSegment<ZJ, VJ>,
            WkSzSequenceOperation<S, Q, R, D, K>,
            WkSzAggregatorOperation<S, Q, R, D, K>
{

}
