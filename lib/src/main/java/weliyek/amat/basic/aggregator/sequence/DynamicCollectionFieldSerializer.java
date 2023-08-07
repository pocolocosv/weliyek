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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberWriter;
import weliyek.ketza.util.array.DynamicSequenceSerializing;
import weliyek.serialization.WkSzDefinition;

public interface DynamicCollectionFieldSerializer<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YQ extends SerializingRuntime<?>,
                        YR extends SerializingResult,
                        YD extends WkSzDynamicCollectionDefinition<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends WkSzNumberWriter<ZT,?,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        VYS extends OperationSettings>
    extends WkSzDynamicCollectionOperation<
                        YS, YQ, YR, YD,
                        WkSzPacketWriterField<T,YD,?>,
                        ZYO,
                        WkSzPacketWriterField<ZT,ZYD,ZYO>,
                        WkSzPacketWriterSubfield<ZT,ZYD,ZYO>,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>,
                        WkSzPacketWriterField<
                          T,
                          VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>,
                          VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>>,
                        WkSzPacketWriterSubfield<T,
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>,
                          VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>>>,
            DynamicSequenceSerializing<T, YS, YQ, YR, YD, ZT, ZYO, ZYD,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>,
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>>
{

}
