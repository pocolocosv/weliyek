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

import weliyek.serialization.WkSzOperationException;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.util.array.WkFixedSizePrimitiveArraySrlzInputPacketDecoderFrameLeafNode;
import weliyek.util.array.WkFixedSizePrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;
import weliyek.util.array.WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore;
import weliyek.util.array.WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore;
import weliyek.util.array.WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameLeafNode;
import weliyek.util.array.WkVariableSizePrimitiveArraySrlzStructDefinitionFrameLeafNode;
import weliyek.util.array.WkVaribleSizePrimitiveArraySrlzOutputPacketEncoderFrameLeafNode;

public class WkPrimitiveArraySrlzUtils
{

  public static void onFixedSizeDeserilizingInitialization(
    WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<
      ?,?,?,? extends WkFixedSizePrimitiveArraySrlzInputPacketDecoderFrameLeafNode<?,?,?,?,?>> deserializing) {
    // Nothing to do.
  }

  public static void onFixedSizeSerilizingInitialization(
    WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore<
      ?,?,?,? extends WkFixedSizePrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<?,?,?,?,?>> serializingCore) {
    final int reqLen = serializingCore.serializable().getLength();
    final int expectedLen = serializingCore.getRequestedLength();
    if (reqLen != expectedLen) {
      throw new WkSzOperationException(
                    serializingCore,
                    "Serializable array does NOT have required length");
    }
  }

  public static void onVariableSizeDeserilizingInitialization(
    WkSimplifiedPrimitiveArraySrlzInputPacketDecoderFrameLeafNodeCore<
      ?,? extends WkSzVariableLengthOperationSettings,
      ? extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameLeafNode<?,?>,
      ? extends WkVariableSizePrimitiveArraySrlzInputPacketDecoderFrameLeafNode<?,?,?,?,?>> deserializingCore) {
    final int reqLen = deserializingCore.settings().getRequestedLength();
    final int minLen = deserializingCore.definition().minimalSize();
    final int maxLen = deserializingCore.definition().maximalSize();
    if ((reqLen < minLen) || (reqLen > maxLen)) {
      throw new WkSzOperationException(
                    deserializingCore,
                    "Requested deserializing length is not in bounds");
    }
  }

  public static void onVariableSizeSerializingInitialization(
    WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore<?,?,
      ? extends WkVariableSizePrimitiveArraySrlzStructDefinitionFrameLeafNode<?,?>,
      ? extends WkVaribleSizePrimitiveArraySrlzOutputPacketEncoderFrameLeafNode<?,?,?,?,?>> serializingCore) {
    int reqLen = serializingCore.serializable().getLength();
    final int minLen = serializingCore.definition().minimalSize();
    final int maxLen = serializingCore.definition().maximalSize();
    if ((reqLen < minLen) || (reqLen > maxLen)) {
      throw new WkSzOperationException(
                    serializingCore,
                    "Requested serializing length is not in bounds");
    }
  }

}
