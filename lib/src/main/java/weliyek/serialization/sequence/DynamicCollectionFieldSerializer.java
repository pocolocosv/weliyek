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

import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterOperation;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;
import weliyek.util.array.DynamicSequenceSerializing;

public interface DynamicCollectionFieldSerializer<
                        T extends Collection<ET>,
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YR extends WkSzWritingResult,
                        YD extends WkSzDynamicCollectionDefinition<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends WkSzNumberWriter<ZT,?,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        VYS extends WkSzOperationSettings>
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
