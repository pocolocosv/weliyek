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
package weliyek.amat.basic.aggregator.sequence;

import weliyek.amat.base.WkSzCommonOperationRuntime;
import weliyek.amat.base.WkSzOperationResult;
import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.basic.aggregator.WkSzAggregatorOperation;
import weliyek.amat.basic.sequence.WkSzSequenceOperation;
import weliyek.serialization.WkSzPacketField;

public interface WkSzCollectionOperation<
                        S extends WkSzOperationSettings,
                        Q extends WkSzCommonOperationRuntime<?>,
                        R extends WkSzOperationResult,
                        D extends WzSzCollectionDefinition<?,?>,
                        K extends WkSzPacketField<?,?,?>>
    extends WkSzCollectionSegment,
            WkSzAggregatorOperation<S, Q, R, D, K>,
            WkSzSequenceOperation<S, Q, R, D, K>
{

}
