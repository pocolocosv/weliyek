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

import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDtreeAggregatorWriter;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.util.array.WkPrimitiveArray;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayDefinition;
import weliyek.util.array.WkSerdeDtreePrimitiveArrayWriter;

public interface WkSerdeStringFromPrimitiveArrayWriter<
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<?>,
                        YR extends WkResultSrlzPacketOperationData<String>,
                        YD extends WkSerdeStringFromPrimitiveArrayDefinition<?,?,?>,
                        SY extends WkPrimitiveArray<?, ?>,
                        SYD extends WkSerdeDtreePrimitiveArrayDefinition<SY>,
                        SYO extends WkSerdeDtreePrimitiveArrayWriter<SY,?,?,?,SYD>>
        extends WkSerdeStringFromPrimitiveArrayOperation<
                        YS, YQ, YR, YD,
                        WkSrlzOutputPacketFieldFrameNode<String,YD,?>,
                        SYO,
                        WkSrlzOutputPacketFieldFrameNode<SY,SYD,SYO>>,
                WkSerdeDtreeAggregatorWriter<String, YS, YQ, YR, YD>
{

}
