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
package weliyek.serialization;

import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSzPrimitiveArrayWriter;

public interface WkSzPrimitiveArraySerializerWriter<
                        Y extends WkPrimitiveArray<?, ?>,
                        S extends WkSzOperationSettings,
                        Q extends WkSzSequenceWritingRuntime<?>,
                        R extends WkSzWritingResult,
                        D extends WkSzPrimitiveArraySerializerDefinition<Y,?>>
    extends WkSzSerializerWriter<Y, S, Q, R, D>,
            WkSzPrimitiveArrayWriter<Y, S, Q, R, D>,
            WkSzPrimitiveArraySerializerOperation<
                        S,Q,R,D,
                        WkSzPacketWriterField<Y,D,?>>
{

}
