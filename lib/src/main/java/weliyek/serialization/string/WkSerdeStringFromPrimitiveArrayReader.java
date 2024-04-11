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

import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeAggregatorReader;
import weliyek.serialization.WkSerdeDtreeNodeDataReader;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayReader;

public interface WkSerdeStringFromPrimitiveArrayReader<
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntime<?>,
                        XR extends WkSerdeDtreeOperationResult<String>,
                        XD extends WkSerdeStringFromPrimitiveArrayDefinition<?,?,? extends SXD>,
                        SX extends WkPrimitiveArray<?,?>,
                        SXD extends WkSerdeDtreePrimitiveArrayDefinition<SX>,
                        SXO extends WkSerdeDtreePrimitiveArrayReader<SX,?,?,?,SXD>>
        extends WkSerdeStringFromPrimitiveArrayOperation<
                        XS, XQ, XR, XD,
                        WkSerdeDtreeNodeDataInputComponent<String,XD,?>,
                        SXO,
                        WkSerdeDtreeNodeDataInputComponent<SX,SXD,SXO>>,
                WkSerdeDtreeNodeDataReader<String,XS,XQ,XR,XD>,
                WkSerdeDtreeAggregatorReader<String, XS, XQ, XR, XD>
{

  static boolean isPrimitiveArrayReady(WkSerdeStringFromPrimitiveArrayReader<?,?,?,?,?,?,?> stringReading) {
    if (   stringReading.primitiveArray().isPresent()
        && stringReading.primitiveArray().get().isCompleted()
        && stringReading.primitiveArray().get().firstOperation().isPresent()
        && stringReading.primitiveArray().get().firstOperation().get().result().isPresent()
        && stringReading.primitiveArray().get().firstOperation().get().result().get().serializable().isPresent()) {
      return true;
    }
    return false;
  }

}
