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

import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.serializer.BasicPrimitiveSerializerFactory;

public final class BigEndianShortOutputSerializer
    extends BasicPrimitiveSerializerEngine<Short>
{

  public static final BasicPrimitiveSerializerFactory<Short> FACTORY =
      new BasicPrimitiveSerializerFactory<>("SINT16BE", BigEndianShortOutputSerializer::new);

  private BigEndianShortOutputSerializer(
    WritingRuntimeControl<?,?,?> runtimeControl,
    NumberSerializing<Short,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected final void writePrimitive(Short n) throws IOException {
    writeBigEndianShort(n.intValue());
  }

  @Override
  public final int primitiveByteLength() {
    return Short.BYTES;
  }

  @Override
  protected final long requestedBytesToIO() {
    return primitiveByteLength();
  }

}
