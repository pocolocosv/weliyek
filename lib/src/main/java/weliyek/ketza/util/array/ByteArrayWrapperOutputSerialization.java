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

import java.io.IOException;

import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;
import weliyek.amat.basic.serializer.PrimitiveArraySerializerWriting;

public final class ByteArrayWrapperOutputSerialization
    extends BasicPrimitiveArrayOutputSerializationEngineBase<ByteArrayWrapper, byte[]>
{

  public static final BasicPrimitiveArraySerializationRule<ByteArrayWrapper> FACTORY =
      new BasicPrimitiveArraySerializationRule<ByteArrayWrapper>(
            "INT8[]",
            ByteArrayWrapperOutputSerialization::new);

  private ByteArrayWrapperOutputSerialization(
    SequenceWritingRuntimeControl<?,?,?> runtimeControl,
    PrimitiveArraySerializerWriting<? extends ByteArrayWrapper,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation, 1);
  }

  @Override
  protected int writeElementAtAbsolutePos(int absoluteIndex) throws IOException {
    byte b = getArray()[ absoluteIndex ];
    writeByte(b);
    return 1;
  }

}
