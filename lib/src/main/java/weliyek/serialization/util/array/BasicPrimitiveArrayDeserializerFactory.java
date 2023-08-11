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

import java.util.function.BiFunction;

import weliyek.serialization.InputSerializationEngine;
import weliyek.serialization.WkSzPrimitiveArraySerializerReader;
import weliyek.serialization.WkSzSequenceReadingRuntimeControl;

public class BasicPrimitiveArrayDeserializerFactory<
                        X extends PrimitiveArrayWrapper<?,?>>
    extends PrimitiveArrayDeserializerFactory<
                        X,
                        WkSzSequenceReadingRuntimeControl<?,?,?>,
                        WkSzPrimitiveArraySerializerReader<X,?,?,?,?>>
{

  public BasicPrimitiveArrayDeserializerFactory(
      String label,
      BiFunction<
        WkSzSequenceReadingRuntimeControl<?,?,?>,
        WkSzPrimitiveArraySerializerReader<X,?,?,?,?>,
        InputSerializationEngine<
          X,
          ? super WkSzSequenceReadingRuntimeControl<?,?,?>,
          ? super WkSzPrimitiveArraySerializerReader<X,?,?,?,?>>> engineSupplier) {
    super(label, engineSupplier);
  }

}
