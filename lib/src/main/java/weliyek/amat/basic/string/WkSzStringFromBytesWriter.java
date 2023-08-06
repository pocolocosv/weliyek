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
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.ketza.util.array.WkSzByteArrayDefinition;
import weliyek.ketza.util.array.ByteArrayWrapper;
import weliyek.ketza.util.array.ByteArrayWriting;

public interface WkSzStringFromBytesWriter<
                        YS extends OperationSettings,
                        YQ extends SerializingRuntime<?>,
                        YR extends SerializingResult,
                        YD extends WkSzStringFromBytesDefinition<?,?,? extends SD>,
                        SD extends WkSzByteArrayDefinition<?>,
                        SYO extends ByteArrayWriting<?,?,?,SD>>
    extends WkSzStringFromPrimitiveWriter<
                        YS, YQ, YR, YD,
                        ByteArrayWrapper, SD, SYO>,
            WkSzStringFromBytesOperation<
                        YS, YQ, YR, YD,
                        WkSzPacketWriterField<String,YD,?>,
                        SYO,
                        WkSzPacketWriterField<ByteArrayWrapper,SD,SYO>,
                        WkSzPacketWriterSubfield<ByteArrayWrapper,SD,SYO>>
{

}