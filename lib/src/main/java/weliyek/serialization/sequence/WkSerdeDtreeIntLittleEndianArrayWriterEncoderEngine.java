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

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.util.array.WkIntArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayWriter;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayEncoderEngineFactorySimplified;

public final class WkSerdeDtreeIntLittleEndianArrayWriterEncoderEngine
        extends WkSerdeDtreeIntArrayWriterEncoderEngine
{

  public static final WkSerdeDtreePrimitiveArrayEncoderEngineFactorySimplified<WkIntArray> FACTORY =
      new WkSerdeDtreePrimitiveArrayEncoderEngineFactorySimplified<>(
            "L_INT32[]",
            WkSerdeDtreeIntLittleEndianArrayWriterEncoderEngine::new);

  private WkSerdeDtreeIntLittleEndianArrayWriterEncoderEngine(
    WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<?,?,?> runtimeControl,
    WkSerdeDtreePrimitiveArrayWriter<? extends WkIntArray,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void writeInt(int i) throws IOException {
    writeLittleEndianInt(i);
  }

}
