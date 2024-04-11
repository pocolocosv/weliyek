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

import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeAggregatorReader;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberReader;
import weliyek.serialization.sequence.WkSerdeDtreeSequenceReader;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceReader;

public interface WkSerdeDtreeDynamicSequenceReader<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        D extends WkSerdeDtreeDynamicSequenceDefinition<T,?,?,?,?>,
                        ZX extends Number,
                        ZXO extends WkSerdeDtreeNumberReader<ZX,?,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberDefinition<ZX>,
                        VXO extends WkSerdeDtreeVariableSizeSequenceReader<T,?,?,?,VXD>,
                        VXD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>>
    extends WkSerdeDtreeDynamicSequenceOperation<
                        XS, XQ, XR, D,
                        WkSerdeDtreeNodeDataInputComponent<T,D,?>,
                        ZXO,
                        WkSerdeDtreeNodeDataInputComponent<ZX,ZXD,ZXO>,
                        VXO,
                        WkSerdeDtreeNodeDataInputComponent<T,VXD,VXO>>,
            WkSerdeDtreeSequenceReader<T, XS, XQ, XR, D>,
            WkSerdeDtreeAggregatorReader<T, XS, XQ, XR, D>
{

}
