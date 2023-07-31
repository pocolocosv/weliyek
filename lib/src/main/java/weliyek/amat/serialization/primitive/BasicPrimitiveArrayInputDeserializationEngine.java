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
package weliyek.amat.serialization.primitive;

import java.util.function.Function;

import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;
import weliyek.amat.basic.serializer.WkSzPrimitiveArraySerializerReader;
import weliyek.ketza.util.array.PrimitiveArrayWrapper;

public abstract class BasicPrimitiveArrayInputDeserializationEngine<
                        X extends PrimitiveArrayWrapper<V, ?>,
                        V>
  extends SequenceInputSerializationRule<
                        X, V,
                        SequenceReadingRuntimeControl<?,?,?>>
{

  public static final int STEP_SIZE = 1024;

  protected BasicPrimitiveArrayInputDeserializationEngine(
      Function<V, X> newWrapperFactory,
      int elementByteLength,
      SequenceReadingRuntimeControl<?,?,?> runtimeCtrl,
      WkSzPrimitiveArraySerializerReader<X,?,?,?,?> readingOperation) {
    super(newWrapperFactory, elementByteLength, STEP_SIZE, runtimeCtrl, readingOperation);
  }

  @Override
  protected void onSequenceDeserializationStart() {
    // Nothing to do.
  }

  @Override
  protected void onDone() {
    // Nothing to do.
  }

}
