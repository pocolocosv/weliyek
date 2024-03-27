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
import weliyek.util.array.WkSerdeDTreeGenericFixedSizePrimitiveArrayReader;
import weliyek.util.array.WkSerdeDTreeGenericFixedSizePrimitiveArrayWriter;
import weliyek.util.array.WkSerdeDTreeGenericPrimitiveArrayReaderCoreSimplified;
import weliyek.util.array.WkSerdeDTreeGenericVariableSizePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDTreeGenericVariableSizePrimitiveArrayReader;
import weliyek.util.array.WkSerdeDTreeGenericVariableSizePrimitiveArrayWriter;
import weliyek.util.array.WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore;

public class WkPrimitiveArraySrlzUtils
{

  public static void onFixedSizeDeserilizingInitialization(
    WkSerdeDTreeGenericPrimitiveArrayReaderCoreSimplified<
      ?,?,?,? extends WkSerdeDTreeGenericFixedSizePrimitiveArrayReader<?,?,?,?,?>> deserializing) {
    // Nothing to do.
  }

  public static void onFixedSizeSerilizingInitialization(
    WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore<
      ?,?,?,? extends WkSerdeDTreeGenericFixedSizePrimitiveArrayWriter<?,?,?,?,?>> serializingCore) {
    final int reqLen = serializingCore.serializable().getLength();
    final int expectedLen = serializingCore.getRequestedLength();
    if (reqLen != expectedLen) {
      throw new WkSzOperationException(
                    serializingCore,
                    "Serializable array does NOT have required length");
    }
  }

  public static void onVariableSizeDeserilizingInitialization(
    WkSerdeDTreeGenericPrimitiveArrayReaderCoreSimplified<
      ?,? extends WkSzVariableLengthOperationSettings,
      ? extends WkSerdeDTreeGenericVariableSizePrimitiveArrayDefinition<?>,
      ? extends WkSerdeDTreeGenericVariableSizePrimitiveArrayReader<?,?,?,?,?>> deserializingCore) {
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
      ? extends WkSerdeDTreeGenericVariableSizePrimitiveArrayDefinition<?>,
      ? extends WkSerdeDTreeGenericVariableSizePrimitiveArrayWriter<?,?,?,?,?>> serializingCore) {
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
