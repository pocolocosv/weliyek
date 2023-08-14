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

import weliyek.serialization.WkAggregatorSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkPrimitiveArraySrlzOutputPacketEncoderFrameNode;
import weliyek.util.array.WkPrimitiveArraySrlzStructDefinitionFrameNode;

public interface WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YR extends WkSzWritingResult,
                        YD extends WkStringFromPrimitiveArraySrlzStructDefinitionFrameNode<?,?,?>,
                        SY extends WkPrimitiveArray<?, ?>,
                        SYD extends WkPrimitiveArraySrlzStructDefinitionFrameNode<SY,?>,
                        SYO extends WkPrimitiveArraySrlzOutputPacketEncoderFrameNode<SY,?,?,?,SYD>>
        extends WkStringFromPrimitiveArraySrlzPacketOperationFrameNode<
                        YS, YQ, YR, YD,
                        WkSrlzOutputPacketFieldFrameNode<String,YD,?>,
                        SYO,
                        WkSrlzOutputPacketFieldFrameNode<SY,SYD,SYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<SY,SYD,SYO>>,
                WkAggregatorSrlzOutputPacketEncoderFrameNode<String, YS, YQ, YR, YD>
{

}
