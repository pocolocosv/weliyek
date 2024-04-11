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

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;

public interface WkSerdeDtreeDynamicPrimitiveArrayWriter<
                        T extends WkPrimitiveArray<?,?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        D extends WkSerdeDtreeDynamicPrimitiveArrayDefinition<T,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends WkSerdeDtreeNumberWriter<ZT,?,?,?,ZD>,
                        ZD extends WkSerdeDtreeNumberDefinition<ZT>,
                        VYO extends WkSerdeDtreeVariableSizePrimitiveArrayWriter<T,?,?,?,VD>,
                        VD extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<T>>
    extends WkSerdeDtreeDynamicPrimitiveArrayOperation<
                        YS, YQ, YR, D,
                        WkSerdeDtreeNodeDataOutputComponent<T,D,?>,
                        ZYO,
                        WkSerdeDtreeNodeDataOutputComponent<ZT,ZD,ZYO>,
                        VYO,
                        WkSerdeDtreeNodeDataOutputComponent<T,VD,VYO>>,
            WkSerdeDtreeDynamicSequenceWriter<T, YS, YQ, YR, D, ZT, ZYO, ZD, VYO, VD>
{

}
