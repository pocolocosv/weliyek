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
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.number.WkSerdeDtreeIntegerSignedWriterEncoderEngine;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;

public final class WkSerdeDtreeIntSignedLittleEndianWriterEncoderEngine
        extends WkSerdeDtreeIntegerSignedWriterEncoderEngine
{

  public static final WkSzPrimitiveWriteEngineFactory<Integer> FACTORY =
      new WkSzPrimitiveWriteEngineFactory<>("SINT32LE", WkSerdeDtreeIntSignedLittleEndianWriterEncoderEngine::new);

  private WkSerdeDtreeIntSignedLittleEndianWriterEncoderEngine(
    WkEncodingRuntimeSrlzPacketOperationCtrl<?,?,?> runtimeControl,
    WkSerdeDtreeNumberWriter<Integer,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected final void writeInt(int i) throws IOException {
    writeLittleEndianInt(i);
  }

  @Override
  protected long requestedBytesToIO() {
    return primitiveByteLength();
  }

  @Override
  public int primitiveByteLength() {
    return Integer.BYTES;
  }

}
