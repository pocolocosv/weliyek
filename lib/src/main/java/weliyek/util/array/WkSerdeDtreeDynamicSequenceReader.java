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
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReader;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
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
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZX,?,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZX>,
                        VXO extends WkSerdeDtreeVariableSizeSequenceReader<T,?,?,?,VXD>,
                        VXD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>>
    extends WkSerdeDtreeDynamicSequenceOperation<
                        XS, XQ, XR, D,
                        WkSerdeDtreeMsgInputField<T,D,?>,
                        ZXO,
                        WkSerdeDtreeMsgInputField<ZX,ZXD,ZXO>,
                        VXO,
                        WkSerdeDtreeMsgInputField<T,VXD,VXO>>,
            WkSerdeDtreeSequenceReader<T, XS, XQ, XR, D>,
            WkSerdeDtreeAggregatorMsgReader<T, XS, XQ, XR, D>
{

}
