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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.SerializingField;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberSerializing;

public interface DynamicPrimitiveArraySerializing<
                        T extends PrimitiveArrayWrapper<?,?>,
                        YS extends OperationSettings,
                        YQ extends SerializingRuntime<?>,
                        YR extends SerializingResult,
                        D extends DynamicPrimitiveArrayDefinition<T,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends NumberSerializing<ZT,?,?,?,ZD>,
                        ZD extends NumberDefinition<ZT,?>,
                        VYO extends VariableSizePrimitiveArrayWriting<T,?,?,?,VD>,
                        VD extends VariableSizePrimitiveArrayDefinition<T,?>>
    extends DynamicPrimitiveArrayOperation<
                        YS, YQ, YR, D,
                        SerializingField<T,D,?>,
                        ZYO,
                        SerializingField<ZT,ZD,ZYO>,
                        SerializingSubfieldHandler<ZT,ZD,ZYO>,
                        VYO,
                        SerializingField<T,VD,VYO>,
                        SerializingSubfieldHandler<T,VD,VYO>>,
            DynamicSequenceSerializing<T, YS, YQ, YR, D, ZT, ZYO, ZD, VYO, VD>
{

}
