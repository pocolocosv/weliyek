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

import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceReading;

public interface WkSzVariableSizePrimitiveArrayReader<
                        X extends PrimitiveArrayWrapper<?, ?>,
                        S extends VariableLengthSettings,
                        Q extends SequenceReadingRuntime<?>,
                        R extends DeserializingResult<X>,
                        D extends WkSzVariableSizePrimitiveArrayDefinition<X,?>>
    extends VariableSizeSequenceReading<X, S, Q, R, D>,
            PrimitiveArrayReading<X, S, Q, R, D>,
            WkSzVariableSizePrimitiveArrayOperation<
                        S, Q, R, D,
                        WkSzPacketReaderField<X,D,?>>
{

}
