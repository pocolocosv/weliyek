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
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.util.array.WkByteArraySrlzInputPacketDecoderFrameNode;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkByteArraySrlzStructDefinitionFrameNode;

public interface WkStringFromBytesSrlzInputPacketDecoderFrameNode<
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<?>,
                        XR extends WkDecodingResultSrlzPacketOperationData<String>,
                        XD extends WkStringFromBytesSrlzStructDefinitionFrameNode<?,?,? extends SXD>,
                        SXD extends WkByteArraySrlzStructDefinitionFrameNode<SXO>,
                        SXO extends WkByteArraySrlzInputPacketDecoderFrameNode<?,?,?,SXD>>
    extends WkStringFromPrimitiveArraySrlzInputPacketDecoderFrameNode<XS, XQ, XR, XD, WkByteArray, SXD, SXO>,
            WkStringFromBytesSrlzPacketOperationFrameNode<
                        XS, XQ, XR, XD,
                        WkSrlzInputPacketFieldFrameNode<String,XD,?>,
                        SXO,
                        WkSrlzInputPacketFieldFrameNode<WkByteArray,SXD,SXO>,
                        WkSrlzInputPacketSubfieldFrameNode<WkByteArray,SXD,SXO>>
{

}
