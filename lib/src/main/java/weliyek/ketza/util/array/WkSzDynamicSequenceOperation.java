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
package weliyek.ketza.util.array;

import weliyek.amat.base.CommonOperationRuntime;
import weliyek.amat.base.OperationResult;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.basic.aggregator.WkSzAggregatorOperation;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableSizeSequenceOperation;
import weliyek.amat.basic.number.WkSzNumberOperation;
import weliyek.amat.basic.sequence.WkSzSequenceOperation;
import weliyek.serialization.WkSzPacketField;
import weliyek.serialization.WkSzPacketSubfield;

public interface WkSzDynamicSequenceOperation<
                        S extends OperationSettings,
                        Q extends CommonOperationRuntime<?>,
                        R extends OperationResult,
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
