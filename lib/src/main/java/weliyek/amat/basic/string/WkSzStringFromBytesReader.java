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
package weliyek.amat.basic.string;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.ketza.util.array.WkSzByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayReading;
import weliyek.ketza.util.array.ByteArrayWrapper;

public interface WkSzStringFromBytesReader<
                        XS extends OperationSettings,
                        XQ extends DeserializingRuntime<?>,
                        XR extends DeserializingResult<String>,
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
