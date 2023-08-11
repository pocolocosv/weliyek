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
package weliyek.serialization.string;

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.util.array.ByteArrayReading;
import weliyek.serialization.util.array.ByteArrayWrapper;
import weliyek.serialization.util.array.WkSzByteArrayDefinition;

public interface WkSzStringFromBytesReader<
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<String>,
                        XD extends WkSzStringFromBytesDefinition<?,?,? extends SXD>,
                        SXD extends WkSzByteArrayDefinition<SXO>,
                        SXO extends ByteArrayReading<?,?,?,SXD>>
    extends WkSzStringFromPrimitiveReader<XS, XQ, XR, XD, ByteArrayWrapper, SXD, SXO>,
            WkSzStringFromBytesOperation<
                        XS, XQ, XR, XD,
                        WkSzPacketReaderField<String,XD,?>,
                        SXO,
                        WkSzPacketReaderField<ByteArrayWrapper,SXD,SXO>,
                        WkSzPacketReaderSubfield<ByteArrayWrapper,SXD,SXO>>
{

}
