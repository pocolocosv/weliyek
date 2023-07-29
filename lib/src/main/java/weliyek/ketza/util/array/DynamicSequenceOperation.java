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
import weliyek.amat.base.FieldSegment;
import weliyek.amat.base.OperationResult;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.SubfieldHandler;
import weliyek.amat.basic.aggregator.AggregatorOperation;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceOperation;
import weliyek.amat.basic.number.NumberOperation;
import weliyek.amat.basic.sequence.SequenceOperation;

public interface DynamicSequenceOperation<
                        S extends OperationSettings,
                        Q extends CommonOperationRuntime<?>,
                        R extends OperationResult,
                        D extends DynamicSequenceDefinition<?,?,?,?,?>,
                        K extends FieldSegment<?,?,?>,
                        ZO extends NumberOperation<?,?,?,?,?>,
                        ZK extends FieldSegment<?,ZO,?>,
                        ZJ extends SubfieldHandler<ZK>,
                        VO extends VariableSizeSequenceOperation<?,?,?,?,?>,
                        VK extends FieldSegment<?,VO,?>,
                        VJ extends SubfieldHandler<VK>>
    extends DynamicSequence<ZJ, VJ>,
            SequenceOperation<S, Q, R, D, K>,
            AggregatorOperation<S, Q, R, D, K>
{

}
