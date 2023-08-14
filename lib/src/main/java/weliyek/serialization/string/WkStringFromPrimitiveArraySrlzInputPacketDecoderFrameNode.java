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

import weliyek.serialization.WkAggregatorSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.util.array.WkPrimitiveArraySrlzInputPacketDecoderFrameNode;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkPrimitiveArraySrlzStructDefinitionFrameNode;

public interface WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNode<
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<String>,
                        XD extends WkStringFromPrimitiveArraySrlzStructDefinitionFrameNode<?,?,? extends SXD>,
                        SX extends WkPrimitiveArray<?,?>,
                        SXD extends WkPrimitiveArraySrlzStructDefinitionFrameNode<SX, SXO>,
                        SXO extends WkPrimitiveArraySrlzInputPacketDecoderFrameNode<SX,?,?,?,SXD>>
        extends WkStringFromPrimitiveArraySrlzPacketOperationFrameNode<
                        XS, XQ, XR, XD,
                        WkSrlzInputPacketFieldFrameNode<String,XD,?>,
                        SXO,
                        WkSrlzInputPacketFieldFrameNode<SX,SXD,SXO>,
                        WkSrlzInputPacketSubfieldFrameNode<SX,SXD,SXO>>,
                WkSrlzInputPacketDecoderFrameNode<String,XS,XQ,XR,XD>,
                WkAggregatorSrlzInputPacketDecoderFrameNode<String, XS, XQ, XR, XD>
{

  static boolean isPrimitiveArrayReady(WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNode<?,?,?,?,?,?,?> stringReading) {
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