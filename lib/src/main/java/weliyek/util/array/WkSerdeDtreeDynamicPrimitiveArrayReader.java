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
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;

public interface WkSerdeDtreeDynamicPrimitiveArrayReader<
                        T extends WkPrimitiveArray<?,?>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeDynamicPrimitiveArrayDefinition<T,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,?,?,?,ZD>,
                        ZD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        VXO extends WkSerdeDtreeVariableSizePrimitiveArrayReader<T,?,?,?,VD>,
                        VD extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<T>>
    extends WkSerdeDtreeDynamicPrimitiveArrayOperation<
                        XS, XQ, XR, XD,
                        WkSerdeDtreeMsgInputField<T,XD,?>,
                        ZXO,
                        WkSerdeDtreeMsgInputField<ZT,ZD,ZXO>,
                        VXO,
                        WkSerdeDtreeMsgInputField<T,VD,VXO>>,
            WkSerdeDtreeDynamicSequenceReader<T, XS, XQ, XR, XD, ZT, ZXO, ZD, VXO, VD>
{

}
