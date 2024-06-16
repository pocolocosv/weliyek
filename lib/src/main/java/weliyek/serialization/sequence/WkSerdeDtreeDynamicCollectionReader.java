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
package weliyek.serialization.sequence;

import java.util.Collection;

import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceReader;

public interface WkSerdeDtreeDynamicCollectionReader<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeDtreeDynamicCollectionDefinition<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,?,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength>
    extends WkSerdeDtreeDynamicCollectionOperation<
                        XS, XQ, XR, XD,
                        WkSerdeDtreeMsgInputField<T,XD,?>,
                        ZXO,
                        WkSerdeDtreeMsgInputField<ZT,ZXD,ZXO>,
                        WkSerdeVariableSizeElementCollectionReader<T,VXS,ET,EXS,EXD,EXO>,
                        WkSerdeDtreeMsgInputField<T,
                          WkSerdeVariableSizeElementCollection<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                          WkSerdeVariableSizeElementCollectionReader<T,VXS,ET,EXS,EXD,EXO>>>,
            WkSerdeDtreeCollectionReader<T, XS, XQ, XR, XD>,
            WkSerdeDtreeDynamicSequenceReader<
                        T, XS, XQ, XR, XD, ZT, ZXO, ZXD,
                        WkSerdeVariableSizeElementCollectionReader<T,VXS,ET,EXS,EXD,EXO>,
                        WkSerdeVariableSizeElementCollection<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>>
{

}
