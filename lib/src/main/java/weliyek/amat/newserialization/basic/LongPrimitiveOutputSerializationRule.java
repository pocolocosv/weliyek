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
package weliyek.amat.newserialization.basic;

import java.io.IOException;

import weliyek.amat.base.output.WkSzWritingRuntimeControl;
import weliyek.amat.basic.number.BasicPrimitiveSerializerEngine;
import weliyek.amat.basic.number.WkSzNumberWriter;

public abstract class LongPrimitiveOutputSerializationRule
    extends BasicPrimitiveSerializerEngine<Long>
{

  protected LongPrimitiveOutputSerializationRule(
    WkSzWritingRuntimeControl<?,?,?> runtimeControl,
    WkSzNumberWriter<Long,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected final void writePrimitive(Long n) throws IOException {
    final long l = n.longValue();
    writeLong(l);
  }

  protected abstract void writeLong(long l) throws IOException;

  @Override
  protected final long requestedBytesToIO() {
    return primitiveByteLength();
  }

  @Override
  public int primitiveByteLength() {
    return Long.BYTES;
  }

}
