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
package weliyek.serialization.string;

import weliyek.serialization.WkSzAggregatorReader;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderOperation;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.util.array.PrimitiveArrayReading;
import weliyek.util.array.PrimitiveArrayWrapper;
import weliyek.util.array.WkSzPrimitiveArrayDefinition;

public interface WkSzStringFromPrimitiveReader<
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<String>,
                        XD extends WkSzStringFromPrimitiveDefinition<?,?,? extends SXD>,
                        SX extends PrimitiveArrayWrapper<?,?>,
                        SXD extends WkSzPrimitiveArrayDefinition<SX, SXO>,
                        SXO extends PrimitiveArrayReading<SX,?,?,?,SXD>>
        extends WkSzStringFromPrimitiveOperation<
                        XS, XQ, XR, XD,
                        WkSzPacketReaderField<String,XD,?>,
                        SXO,
                        WkSzPacketReaderField<SX,SXD,SXO>,
                        WkSzPacketReaderSubfield<SX,SXD,SXO>>,
                WkSzPacketReaderOperation<String,XS,XQ,XR,XD>,
                WkSzAggregatorReader<String, XS, XQ, XR, XD>
{

  static boolean isPrimitiveArrayReady(WkSzStringFromPrimitiveReader<?,?,?,?,?,?,?> stringReading) {
    if (   stringReading.primitiveArray().isActivated()
        && stringReading.primitiveArray().field().get().isCompleted()
        && stringReading.primitiveArray().field().get().firstOperation().isPresent()
        && stringReading.primitiveArray().field().get().firstOperation().get().result().isPresent()
        && stringReading.primitiveArray().field().get().firstOperation().get().result().get().deserialized().isPresent()) {
      return true;
    }
    return false;
  }

}
