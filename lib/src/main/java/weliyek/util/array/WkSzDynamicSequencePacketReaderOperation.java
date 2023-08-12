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

import weliyek.serialization.WkSzAggregatorReader;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;
import weliyek.serialization.sequence.VariableSizeSequenceReading;
import weliyek.serialization.sequence.WkSzSequenceReader;
import weliyek.serialization.sequence.WkSzVariableSizeSequenceDefinition;

public interface WkSzDynamicSequencePacketReaderOperation<
                        T,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<T>,
                        D extends WkSzDynamicSequenceDefinition<T,?,?,?,?>,
                        ZX extends Number,
                        ZXO extends WkSzNumberReader<ZX,?,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZX,?>,
                        VXO extends VariableSizeSequenceReading<T,?,?,?,VXD>,
                        VXD extends WkSzVariableSizeSequenceDefinition<T,VXO>>
    extends WkSzDynamicSequenceOperation<
                        XS, XQ, XR, D,
                        WkSzPacketReaderField<T,D,?>,
                        ZXO,
                        WkSzPacketReaderField<ZX,ZXD,ZXO>,
                        WkSzPacketReaderSubfield<ZX,ZXD,ZXO>,
                        VXO,
                        WkSzPacketReaderField<T,VXD,VXO>,
                        WkSzPacketReaderSubfield<T,VXD,VXO>>,
            WkSzSequenceReader<T, XS, XQ, XR, D>,
            WkSzAggregatorReader<T, XS, XQ, XR, D>
{

}
