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
import weliyek.util.array.WkLongArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayWriter;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayEncoderEngineFactorySimplified;

public final class WkSerdeDtreeLongBigEndianArrayWriterEncoderEngine
    extends WkSerdeDtreeLongArrayWriterEncoderEngine
{

  public static final WkSerdeDtreePrimitiveArrayEncoderEngineFactorySimplified<WkLongArray> FACTORY =
      new WkSerdeDtreePrimitiveArrayEncoderEngineFactorySimplified<>(
            "B_INT64[]",
            WkSerdeDtreeLongBigEndianArrayWriterEncoderEngine::new);

  private WkSerdeDtreeLongBigEndianArrayWriterEncoderEngine(
    WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<?,?,?> runtimeControl,
    WkSerdeDtreePrimitiveArrayWriter<? extends WkLongArray,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation);
  }

  @Override
  protected void writeLong(long l) throws IOException {
    writeBigEndianLong(l);
  }

}
