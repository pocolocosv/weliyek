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
import weliyek.serialization.util.array.WkSzBasicPrimitiveArrayWrapperWriteEngineFactory;
import weliyek.serialization.util.array.IntArrayWrapper;

public final class LittleEndianIntArrayOutputSerializationRule
        extends WkSzIntArrayWrapperWriteEngine
{

  public static final WkSzBasicPrimitiveArrayWrapperWriteEngineFactory<IntArrayWrapper> FACTORY =
      new WkSzBasicPrimitiveArrayWrapperWriteEngineFactory<>(
            "L_INT32[]",
            LittleEndianIntArrayOutputSerializationRule::new);

  private LittleEndianIntArrayOutputSerializationRule(
    WkSzSequenceWritingRuntimeControl<?,?,?> runtimeControl,
    WkSzPrimitiveArraySerializerWriter<? extends IntArrayWrapper,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void writeInt(int i) throws IOException {
    writeLittleEndianInt(i);
  }

}
