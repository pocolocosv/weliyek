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
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSzByteArrayWriter;
import weliyek.util.array.WkSzByteArrayDefinition;

public interface WkSzStringFromBytesWriter<
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YR extends WkSzWritingResult,
                        YD extends WkSzStringFromBytesDefinition<?,?,? extends SD>,
                        SD extends WkSzByteArrayDefinition<?>,
                        SYO extends WkSzByteArrayWriter<?,?,?,SD>>
    extends WkSzStringFromPrimitiveWriter<
                        YS, YQ, YR, YD,
                        WkByteArray, SD, SYO>,
            WkSzStringFromBytesOperation<
                        YS, YQ, YR, YD,
                        WkSzPacketWriterField<String,YD,?>,
                        SYO,
                        WkSzPacketWriterField<WkByteArray,SD,SYO>,
                        WkSzPacketWriterSubfield<WkByteArray,SD,SYO>>
{

}
