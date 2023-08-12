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

import weliyek.serialization.WkSzOperationException;
import weliyek.serialization.WkSzPacketOperationException;
import weliyek.serialization.WkSzVariableLengthOperationSettings;

public class PrimitiveArrayUtils
{

  public static void onFixedSizeDeserilizingInitialization(
    SimplifiedPrimitiveArrayDeserializingCore<
      ?,?,?,? extends WkSzFixedSizePrimitiveArraySerializerReader<?,?,?,?,?>> deserializing) {
    // Nothing to do.
  }

  public static void onFixedSizeSerilizingInitialization(
    SimplifiedPrimitiveArraySerializingCore<
      ?,?,?,? extends WkSzFixedSizePrimitiveArraySerializerWriter<?,?,?,?,?>> serializingCore) {
    final int reqLen = serializingCore.serializable().getLength();
    final int expectedLen = serializingCore.getRequestedLength();
    if (reqLen != expectedLen) {
      throw new WkSzOperationException(
                    serializingCore,
                    "Serializable array does NOT have required length");
    }
  }

  public static void onVariableSizeDeserilizingInitialization(
    SimplifiedPrimitiveArrayDeserializingCore<
      ?,? extends WkSzVariableLengthOperationSettings,
      ? extends WkSzVariableSizePrimitiveArraySerializerDefinition<?,?>,
      ? extends WkSzVariableSizePrimitiveArraySerializerReader<?,?,?,?,?>> deserializingCore) {
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
    SimplifiedPrimitiveArraySerializingCore<?,?,
      ? extends WkSzVariableSizePrimitiveArraySerializerDefinition<?,?>,
      ? extends WkSzVariableSizePrimitiveArraySerializerWriter<?,?,?,?,?>> serializingCore) {
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
