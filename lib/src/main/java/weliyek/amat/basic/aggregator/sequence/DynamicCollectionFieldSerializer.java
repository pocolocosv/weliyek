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

import java.util.Collection;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.SerializingField;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberSerializing;
import weliyek.ketza.util.array.DynamicSequenceSerializing;

public interface DynamicCollectionFieldSerializer<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YQ extends SerializingRuntime<?>,
                        YR extends SerializingResult,
                        YD extends DynamicCollectionFieldDefinition<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends NumberSerializing<ZT,?,?,?,ZYD>,
                        ZYD extends NumberDefinition<ZT,?>,
                        ET,
                        EYS extends OperationSettings,
                        EYD extends DefinitionSegment<ET,?>,
                        EYO extends SerializingOperation<ET,EYS,?,?,EYD>,
                        VYS extends OperationSettings>
    extends DynamicCollectionFieldOperation<
                        YS, YQ, YR, YD,
                        SerializingField<T,YD,?>,
                        ZYO,
                        SerializingField<ZT,ZYD,ZYO>,
                        SerializingSubfieldHandler<ZT,ZYD,ZYO>,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>,
                        SerializingField<
                          T,
                          VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>,
                          VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>>,
                        SerializingSubfieldHandler<T,
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>,
                          VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>>>,
            DynamicSequenceSerializing<T, YS, YQ, YR, YD, ZT, ZYO, ZYD,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>,
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>>
{

}
