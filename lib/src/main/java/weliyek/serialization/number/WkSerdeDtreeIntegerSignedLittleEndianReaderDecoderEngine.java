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
package weliyek.serialization.number;

import java.io.IOException;

import weliyek.serialization.WkSerdeDtreePrimitiveDecoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;

public final class WkSerdeDtreeIntegerSignedLittleEndianReaderDecoderEngine
        extends WkSerdeDtreeIntPrimitiveReaderDecoderEngine<Integer>
{

  public static WkSerdeDtreePrimitiveDecoderEngineFactory<Integer> FACTORY =
      new WkSerdeDtreePrimitiveDecoderEngineFactory<>(
              "SINT32LE",
              WkSerdeDtreeIntegerSignedLittleEndianReaderDecoderEngine::new);

  private WkSerdeDtreeIntegerSignedLittleEndianReaderDecoderEngine(
    WkSerdeDtreeOperationInputRuntimeCtrl<?,?,?> runtimeCtrl,
    WkSerdeDtreeNumberMsgReader<Integer,?,?,?,?> readingOperation) {
    super(Integer::valueOf, runtimeCtrl, readingOperation);
  }

  @Override
  protected final int readPrimitiveAsInt() throws IOException {
    return readLittleEndianInt();
  }

  @Override
  protected final long requestedBytesToIO() {
    return primitiveByteLength();
  }

  @Override
  public final int primitiveByteLength() {
    return 4;
  }

}
