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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.sequence.WkSzSequenceWriter;

public interface WkSzGenericArrayWriter<
                        Y extends WkGenericArray<?,?>,
                        S extends WkSzOperationSettings,
                        Q extends WkSzWritingRuntime<?>,
                        R extends WkSzWritingResult,
                        D extends WkSzArrayDefinition<Y,?>>
        extends WkSzSequenceWriter<Y, S, Q, R, D>,
                WkSzArrayOperation<
                        S, Q, R, D,
                        WkSzPacketWriterField<Y,D,?>>
{

}
