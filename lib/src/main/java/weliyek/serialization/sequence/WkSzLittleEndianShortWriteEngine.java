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

import weliyek.serialization.WkSzPrimitiveWriteEngineFactory;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkSzPrimitiveWriteEngine;
import weliyek.serialization.number.WkSzNumberWriter;

public final class WkSzLittleEndianShortWriteEngine
    extends WkSzPrimitiveWriteEngine<Short>
{

  public static final WkSzPrimitiveWriteEngineFactory<Short> FACTORY =
      new WkSzPrimitiveWriteEngineFactory<>("SINT16LE", WkSzLittleEndianShortWriteEngine::new);

  private WkSzLittleEndianShortWriteEngine(
    WkSzWritingRuntimeControl<?,?,?> runtimeControl,
    WkSzNumberWriter<Short,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void writePrimitive(Short n) throws IOException {
    writeLittleEndianShort(n.intValue());
  }

  @Override
  protected long requestedBytesToIO() {
    return primitiveByteLength();
  }

  @Override
  public int primitiveByteLength() {
    return Short.BYTES;
  }

}