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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayReader;

public interface WkSerdeStringFromBytesReader<
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<?>,
                        XR extends WkResultSrlzPacketOperationData<String>,
                        XD extends WkSerdeStringFromBytesDefinition<?,?,? extends SXD>,
                        SXD extends WkSerdeDtreeByteArrayDefinition,
                        SXO extends WkSerdeDtreeByteArrayReader<?,?,?,SXD>>
    extends WkSerdeStringFromPrimitiveArrayReader<XS, XQ, XR, XD, WkByteArray, SXD, SXO>,
            WkSerdeStringFromBytesOperation<
                        XS, XQ, XR, XD,
                        WkSrlzInputPacketFieldFrameNode<String,XD,?>,
                        SXO,
                        WkSrlzInputPacketFieldFrameNode<WkByteArray,SXD,SXO>>
{

}
