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

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.basic.aggregator.WkSzAggregatorReader;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableSizeSequenceDefinition;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceReading;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.amat.basic.sequence.WkSzSequenceReader;

public interface DynamicSequenceDeserializing<
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
