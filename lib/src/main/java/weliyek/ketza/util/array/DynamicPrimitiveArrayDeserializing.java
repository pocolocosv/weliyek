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
import weliyek.amat.base.input.DeserializingField;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberDeserializing;

public interface DynamicPrimitiveArrayDeserializing<
                        T extends PrimitiveArrayWrapper<?,?>,
                        XS extends OperationSettings,
                        XQ extends DeserializingRuntime<?>,
                        XR extends DeserializingResult<T>,
                        XD extends DynamicPrimitiveArrayDefinition<T,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends NumberDeserializing<ZT,?,?,?,ZD>,
                        ZD extends NumberDefinition<ZT,ZXO>,
                        VXO extends VariableSizePrimitiveArrayReading<T,?,?,?,VD>,
                        VD extends VariableSizePrimitiveArrayDefinition<T,VXO>>
    extends DynamicPrimitiveArrayOperation<
                        XS, XQ, XR, XD,
                        DeserializingField<T,XD,?>,
                        ZXO,
                        DeserializingField<ZT,ZD,ZXO>,
                        DeserializingSubfieldHandler<ZT,ZD,ZXO>,
                        VXO,
                        DeserializingField<T,VD,VXO>,
                        DeserializingSubfieldHandler<T,VD,VXO>>,
            DynamicSequenceDeserializing<T, XS, XQ, XR, XD, ZT, ZXO, ZD, VXO, VD>
{

}
