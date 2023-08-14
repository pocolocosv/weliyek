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
package weliyek.serialization.number;

import weliyek.serialization.WkSrlzInputPacketDecoderFrameLeafNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkDecodingResultSrlzPacketOperationData;
import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;

public interface WkNumberSrlzInputPacketDecoderFrameLeafNode<
                        T extends Number,
                        S extends WkSettingsSrlzPacketOperationData,
                        Q extends WkDecodingRuntimeSrlzPacketOperationData<?>,
                        R extends WkDecodingResultSrlzPacketOperationData<T>,
                        D extends WkNumberSrlzStructDefinitionFrameLeafNode<T,?>>
        extends WkNumberSrlzPacketOperationFrameLeafNode<
                        S, Q, R, D,
                        WkSrlzInputPacketFieldFrameNode<T,D,?>>,
                WkSrlzInputPacketDecoderFrameLeafNode<T, S, Q, R, D>
{

}
