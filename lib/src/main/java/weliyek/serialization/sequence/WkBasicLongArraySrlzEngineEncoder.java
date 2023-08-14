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

import weliyek.serialization.WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.util.array.WkLongArray;
import weliyek.util.array.WkBasicPrimitiveArrayWrapperSrlzEngineEncoder;

public abstract class WkBasicLongArraySrlzEngineEncoder
    extends WkBasicPrimitiveArrayWrapperSrlzEngineEncoder<WkLongArray, long[]>
{

  protected WkBasicLongArraySrlzEngineEncoder(
    WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<?,?,?> runtimeControl,
    WkPrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<? extends WkLongArray,?,?,?,?> writingOperation) {
    super(runtimeControl, writingOperation, Long.BYTES);
  }

  @Override
  protected int writeElementAtAbsolutePos(int index) throws IOException {
    long l = getArray()[ index ];
    writeLong(l);
    return primitiveByteLength();
  }

  protected abstract void writeLong(long l) throws IOException;

}
