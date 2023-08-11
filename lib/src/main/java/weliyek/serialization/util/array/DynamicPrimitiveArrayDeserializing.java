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
package weliyek.serialization.util.array;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;

public interface DynamicPrimitiveArrayDeserializing<
                        T extends PrimitiveArrayWrapper<?,?>,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkSzDynamicPrimitiveArrayDefinition<T,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends WkSzNumberReader<ZT,?,?,?,ZD>,
                        ZD extends WkSzNumberDefinition<ZT,ZXO>,
                        VXO extends WkSzVariableSizePrimitiveArrayReader<T,?,?,?,VD>,
                        VD extends WkSzVariableSizePrimitiveArrayDefinition<T,VXO>>
    extends WkSzDynamicPrimitiveArrayOperation<
                        XS, XQ, XR, XD,
                        WkSzPacketReaderField<T,XD,?>,
                        ZXO,
                        WkSzPacketReaderField<ZT,ZD,ZXO>,
                        WkSzPacketReaderSubfield<ZT,ZD,ZXO>,
                        VXO,
                        WkSzPacketReaderField<T,VD,VXO>,
                        WkSzPacketReaderSubfield<T,VD,VXO>>,
            DynamicSequenceDeserializing<T, XS, XQ, XR, XD, ZT, ZXO, ZD, VXO, VD>
{

}
