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

import weliyek.serialization.WkSerdeDtreeAggregatorWriter;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreeGenericPrimitiveArrayWriter;
import weliyek.util.array.WkSerdeDtreeGenericPrimitiveArrayDefinition;

public interface WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<?>,
                        YR extends WkResultSrlzPacketOperationData<String>,
                        YD extends WkStringFromPrimitiveArraySrlzStructDefinitionFrameNode<?,?,?>,
                        SY extends WkPrimitiveArray<?, ?>,
                        SYD extends WkSerdeDtreeGenericPrimitiveArrayDefinition<SY>,
                        SYO extends WkSerdeDtreeGenericPrimitiveArrayWriter<SY,?,?,?,SYD>>
        extends WkStringFromPrimitiveArraySrlzPacketOperationFrameNode<
                        YS, YQ, YR, YD,
                        WkSrlzOutputPacketFieldFrameNode<String,YD,?>,
                        SYO,
                        WkSrlzOutputPacketFieldFrameNode<SY,SYD,SYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<SY,SYD,SYO>>,
                WkSerdeDtreeAggregatorWriter<String, YS, YQ, YR, YD>
{

}
