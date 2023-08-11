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

import weliyek.util.array.PrimitiveArrayReading;
import weliyek.util.array.PrimitiveArrayWrapper;

public interface WkSzPrimitiveArraySerializerReader<
                        X extends PrimitiveArrayWrapper<?, ?>,
                        S extends WkSzOperationSettings,
                        Q extends WkSzSequenceReadingRuntime<?>,
                        R extends WkSzReadingResult<X>,
                        D extends PrimitiveArraySerializerDefinition<X,?>>
    extends WkSzSerializerReader<X, S, Q, R, D>,
            PrimitiveArrayReading<X, S, Q, R, D>,
            WkSzPrimitiveArraySerializerOperation<
                        S, Q, R, D,
                        WkSzPacketReaderField<X,D,?>>
{

}