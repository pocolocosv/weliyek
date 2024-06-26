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

import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceWriter;

public interface WkSerdeDtreeDynamicCollectionWriter<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeDtreeDynamicCollectionDefinition<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,?,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                        VYS extends WkSerdeDtreeOperationSettings>
    extends WkSerdeDtreeDynamicCollectionOperation<
                        YS, YQ, YR, YD,
                        WkSerdeDtreeMsgOutputField<T,YD,?>,
                        ZYO,
                        WkSerdeDtreeMsgOutputField<ZT,ZYD,ZYO>,
                        WkSerdeVariableSizeElementCollectionWriter<T,VYS,ET,EYS,EYD,EYO>,
                        WkSerdeDtreeMsgOutputField<
                          T,
                          WkSerdeVariableSizeElementCollection<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>,
                          WkSerdeVariableSizeElementCollectionWriter<T,VYS,ET,EYS,EYD,EYO>>>,
            WkSerdeDtreeCollectionWriter<T, YS, YQ, YR, YD>,
            WkSerdeDtreeDynamicSequenceWriter<T, YS, YQ, YR, YD, ZT, ZYO, ZYD,
                        WkSerdeVariableSizeElementCollectionWriter<T,VYS,ET,EYS,EYD,EYO>,
                        WkSerdeVariableSizeElementCollection<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>>
{

}
