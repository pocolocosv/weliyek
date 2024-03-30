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

import weliyek.serialization.WkSerdeDtreeNodeDataOperationException;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.util.array.WkSerdeDtreeGenericFixedSizePrimitiveArrayReader;
import weliyek.util.array.WkSerdeDtreeGenericFixedSizePrimitiveArrayWriter;
import weliyek.util.array.WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified;
import weliyek.util.array.WkSerdeDtreeGenericVariableSizePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreeGenericVariableSizePrimitiveArrayReader;
import weliyek.util.array.WkSerdeDtreeGenericVariableSizePrimitiveArrayWriter;
import weliyek.util.array.WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore;

public class WkPrimitiveArraySrlzUtils
{

  public static void onFixedSizeDeserilizingInitialization(
    WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<
      ?,?,?,? extends WkSerdeDtreeGenericFixedSizePrimitiveArrayReader<?,?,?,?,?>> deserializing) {
    // Nothing to do.
  }

  public static void onFixedSizeSerilizingInitialization(
    WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore<
      ?,?,?,? extends WkSerdeDtreeGenericFixedSizePrimitiveArrayWriter<?,?,?,?,?>> serializingCore) {
    final int reqLen = serializingCore.serializable().getLength();
    final int expectedLen = serializingCore.getRequestedLength();
    if (reqLen != expectedLen) {
      throw new WkSerdeDtreeNodeDataOperationException(
                    serializingCore,
                    "Serializable array does NOT have required length");
    }
  }

  public static void onVariableSizeDeserilizingInitialization(
    WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<
      ?,? extends WkSzVariableLengthOperationSettings,
      ? extends WkSerdeDtreeGenericVariableSizePrimitiveArrayDefinition<?>,
      ? extends WkSerdeDtreeGenericVariableSizePrimitiveArrayReader<?,?,?,?,?>> deserializingCore) {
    final int reqLen = deserializingCore.settings().getRequestedLength();
    final int minLen = deserializingCore.definition().minimalSize();
    final int maxLen = deserializingCore.definition().maximalSize();
    if ((reqLen < minLen) || (reqLen > maxLen)) {
      throw new WkSerdeDtreeNodeDataOperationException(
                    deserializingCore,
                    "Requested deserializing length is not in bounds");
    }
  }

  public static void onVariableSizeSerializingInitialization(
    WkSimplifiedPrimitiveArraySrlzOutputPacketEncoderFrameLeafNodeCore<?,?,
      ? extends WkSerdeDtreeGenericVariableSizePrimitiveArrayDefinition<?>,
      ? extends WkSerdeDtreeGenericVariableSizePrimitiveArrayWriter<?,?,?,?,?>> serializingCore) {
    int reqLen = serializingCore.serializable().getLength();
    final int minLen = serializingCore.definition().minimalSize();
    final int maxLen = serializingCore.definition().maximalSize();
    if ((reqLen < minLen) || (reqLen > maxLen)) {
      throw new WkSerdeDtreeNodeDataOperationException(
                    serializingCore,
                    "Requested serializing length is not in bounds");
    }
  }

}
