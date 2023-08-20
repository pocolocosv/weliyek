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

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkEncodingResultSrlzPacketOperationData;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationData;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkByteArraySrlzOutputPacketEncoderFrameNode;
import weliyek.util.array.WkByteArraySrlzStructDefinitionFrameNode;

public interface WkStringFromBytesSrlzOutputPacketEncoderFrameNode<
                        YS extends WkSettingsSrlzPacketOperationData,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<?>,
                        YR extends WkEncodingResultSrlzPacketOperationData,
                        YD extends WkStringFromBytesSrlzStructDefinitionFrameNode<?,?,? extends SD>,
                        SD extends WkByteArraySrlzStructDefinitionFrameNode,
                        SYO extends WkByteArraySrlzOutputPacketEncoderFrameNode<?,?,?,SD>>
    extends WkStringFromPrimitiveArraySrlzOutputPacketEncoderFrameNode<
                        YS, YQ, YR, YD,
                        WkByteArray, SD, SYO>,
            WkStringFromBytesSrlzPacketOperationFrameNode<
                        YS, YQ, YR, YD,
                        WkSrlzOutputPacketFieldFrameNode<String,YD,?>,
                        SYO,
                        WkSrlzOutputPacketFieldFrameNode<WkByteArray,SD,SYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<WkByteArray,SD,SYO>>
{

}
