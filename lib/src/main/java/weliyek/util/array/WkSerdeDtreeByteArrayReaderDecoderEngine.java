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
package weliyek.util.array;

import java.io.IOException;

import weliyek.serialization.WkSerdeDtreePrimitiveArrayReaderDecoderEngineBasic;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;

public final class WkSerdeDtreeByteArrayReaderDecoderEngine
  extends WkSerdeDtreePrimitiveArrayReaderDecoderEngineBasic<
                        WkByteArray,
                        byte[]>
{

  public static final WkSerdeDtreePrimitiveArrayDecoderEngineFactorySimplified<WkByteArray> FACTORY =
      new WkSerdeDtreePrimitiveArrayDecoderEngineFactorySimplified<>("INT8[]", WkSerdeDtreeByteArrayReaderDecoderEngine::new);

  private WkSerdeDtreeByteArrayReaderDecoderEngine(
    WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<?,?,?> runtimeCtrl,
    WkSerdeDtreePrimitiveArrayReader<WkByteArray,?,?,?,?> readingOperation) {
    super(WkByteArray::new, 1, runtimeCtrl, readingOperation);
  }

  @Override
  protected byte[] newEmptyArray() {
    return new byte[ sequenceExpectedLength() ];
  }

  @Override
  protected void readSequenceElement(int i) throws IOException {
    getArray()[i] = (byte) this.readByte();
  }

}
