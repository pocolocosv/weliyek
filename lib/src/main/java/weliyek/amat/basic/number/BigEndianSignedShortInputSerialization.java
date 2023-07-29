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
package weliyek.amat.basic.number;

import java.io.IOException;

import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.basic.serializer.BasicPrimitiveDeserializerFactory;

public final class BigEndianSignedShortInputSerialization
    extends IntPrimitiveInputSerializationRule<Short>
{

  public static BasicPrimitiveDeserializerFactory<Short> FACTORY =
      new BasicPrimitiveDeserializerFactory<>(
              "SINT16BE",
              BigEndianSignedShortInputSerialization::new);

  private BigEndianSignedShortInputSerialization(
    ReadingRuntimeControl<?,?,?> runtimeCtrl,
    NumberDeserializing<Short,?,?,?,?> readingOperation) {
    super((i) -> Short.valueOf((short)i), runtimeCtrl, readingOperation);
  }

  @Override
  protected int readPrimitiveAsInt() throws IOException {
    return readBigEndianShort();
  }

  @Override
  protected long requestedBytesToIO() {
    return primitiveByteLength();
  }

  @Override
  public final int primitiveByteLength() {
    return 2;
  }

}
