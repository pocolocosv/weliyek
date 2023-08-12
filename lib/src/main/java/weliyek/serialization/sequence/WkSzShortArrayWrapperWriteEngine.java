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
package weliyek.serialization.sequence;

import java.io.IOException;

import weliyek.serialization.WkSzPrimitiveArraySerializerWriter;
import weliyek.serialization.WkSzSequenceWritingRuntimeControl;
import weliyek.util.array.WkShortArray;
import weliyek.util.array.WkSzBasicPrimitiveArrayWrapperWriteEngineBase;

public abstract class WkSzShortArrayWrapperWriteEngine
        extends WkSzBasicPrimitiveArrayWrapperWriteEngineBase<WkShortArray, short[]>
{

  protected WkSzShortArrayWrapperWriteEngine(
    WkSzSequenceWritingRuntimeControl<?,?,?> runtimeControl,
    WkSzPrimitiveArraySerializerWriter<? extends WkShortArray,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation, Short.BYTES);
  }

  @Override
  protected int writeElementAtAbsolutePos(int index) throws IOException {
    short s = getArray()[ index ];
    writeShort(s);
    return primitiveByteLength();
  }

  protected abstract void writeShort(int s) throws IOException;

}
