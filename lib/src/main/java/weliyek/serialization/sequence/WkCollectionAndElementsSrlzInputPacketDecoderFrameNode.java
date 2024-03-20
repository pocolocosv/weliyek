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
package weliyek.serialization.sequence;

import java.util.Collection;

import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSequenceDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;

public interface WkCollectionAndElementsSrlzInputPacketDecoderFrameNode<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkSequenceDecodingRuntimeSrlzPacketOperationData<?>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        XD extends WkCollectionAndElementsSrlzStructDefinitionFrameNode<T,?,?,ET,?>,
                        ET,
                        EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDTreeNodeDataReader<ET,?,?,?,EXD>>
        extends WkCollectionAndElementsSrlzPacketOperationFrameNode<
                        XS, XQ, XR, XD,
                        WkSrlzInputPacketFieldFrameNode<T,XD,?>,
                        EXO,
                        WkSrlzInputPacketFieldFrameNode<ET,EXD,EXO>,
                        WkSrlzInputPacketSubfieldFrameNode<ET,EXD,EXO>>,
                WkCollectionSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, XD>
{

}
