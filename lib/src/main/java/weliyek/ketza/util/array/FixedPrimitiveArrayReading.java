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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.input.DeserializingField;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.amat.basic.sequence.FixedSizeSequenceReading;

public interface FixedPrimitiveArrayReading<
                        X extends PrimitiveArrayWrapper<?,?>,
                        S extends OperationSettings,
                        Q extends SequenceReadingRuntime<?>,
                        R extends DeserializingResult<X>,
                        D extends FixedSizePrimitiveArrayDefinition<X,?>>
  extends PrimitiveArrayReading<X, S, Q, R, D>,
          FixedSizeSequenceReading<X, S, Q, R, D>,
          FixedSizePrimitiveArrayOperation<
                        S, Q, R, D,
                        DeserializingField<X,D,?>>
{

}
