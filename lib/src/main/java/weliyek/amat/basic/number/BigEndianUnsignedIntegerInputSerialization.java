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

import weliyek.amat.base.input.WkSzReadingRuntimeControl;
import weliyek.amat.basic.serializer.BasicPrimitiveDeserializerFactory;

public final class BigEndianUnsignedIntegerInputSerialization
    extends LongPrimitiveInputSerializationRule
{

  public static BasicPrimitiveDeserializerFactory<Long> FACTORY =
      new BasicPrimitiveDeserializerFactory<>(
              "UINT32BE",
              BigEndianUnsignedIntegerInputSerialization::new);

  private BigEndianUnsignedIntegerInputSerialization(
    WkSzReadingRuntimeControl<?,?,?> runtimeCtrl,
    WkSzNumberReader<Long,?,?,?,?> readingOperation) {
    super(runtimeCtrl, readingOperation);
  }

  @Override
  protected long readPrimitiveAsLong() throws IOException {
    return 0xFFFF_FFFFL & readBigEndianInt();
  }

  @Override
  protected long requestedBytesToIO() {
    return primitiveByteLength();
  }

  @Override
  public int primitiveByteLength() {
    return 4;
  }

}
