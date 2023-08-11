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

import weliyek.serialization.WkSzAggregatorWriter;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;
import weliyek.serialization.sequence.VariableSizeSequenceWriting;
import weliyek.serialization.sequence.WkSzSequenceWriter;
import weliyek.serialization.sequence.WkSzVariableSizeSequenceDefinition;

public interface DynamicSequenceSerializing<
                        T,
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YR extends WkSzWritingResult,
                        D extends WkSzDynamicSequenceDefinition<T,?,?,?,?>,
                        ZY extends Number,
                        ZYO extends WkSzNumberWriter<ZY,?,?,?,ZD>,
                        ZD extends WkSzNumberDefinition<ZY,?>,
                        VYO extends VariableSizeSequenceWriting<T,?,?,?,VD>,
                        VD extends WkSzVariableSizeSequenceDefinition<T,?>>
    extends WkSzDynamicSequenceOperation<
                        YS, YQ, YR, D,
                        WkSzPacketWriterField<T,D,?>,
                        ZYO,
                        WkSzPacketWriterField<ZY,ZD,ZYO>,
                        WkSzPacketWriterSubfield<ZY,ZD,ZYO>,
                        VYO,
                        WkSzPacketWriterField<T,VD,VYO>,
                        WkSzPacketWriterSubfield<T,VD,VYO>>,
            WkSzSequenceWriter<T, YS, YQ, YR, D>,
            WkSzAggregatorWriter<T, YS, YQ, YR, D>
{

}