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

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeDTreeNumberReader;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.util.array.WkSerdeDTreeDynamicSequenceReader;

public interface WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        T extends Collection<ET>,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<?>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        XD extends WkDynamicCollectionSrlzStructDefinitionFrameNode<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends WkSerdeDTreeNumberReader<ZT,?,?,?,ZXD>,
                        ZXD extends WkSerdeDTreeNumberDefinition<ZT>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                        VXS extends WkSzVariableLengthOperationSettings>
    extends WkDynamicCollectionSrlzPacketOperationFrameNode<
                        XS, XQ, XR, XD,
                        WkSrlzInputPacketFieldFrameNode<T,XD,?>,
                        ZXO,
                        WkSrlzInputPacketFieldFrameNode<ZT,ZXD,ZXO>,
                        WkSrlzInputPacketSubfieldFrameNode<ZT,ZXD,ZXO>,
                        WkVariableSizeCollectionSrlzInputNode<T,VXS,ET,EXS,EXD,EXO>,
                        WkSrlzInputPacketFieldFrameNode<T,
                          WkVariableSizeCollectionSrlzStructNode<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                          WkVariableSizeCollectionSrlzInputNode<T,VXS,ET,EXS,EXD,EXO>>,
                          WkSrlzInputPacketSubfieldFrameNode<
                          T,
                          WkVariableSizeCollectionSrlzStructNode<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                          WkVariableSizeCollectionSrlzInputNode<T,VXS,ET,EXS,EXD,EXO>>>,
            WkCollectionSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, XD>,
            WkSerdeDTreeDynamicSequenceReader<
                        T, XS, XQ, XR, XD, ZT, ZXO, ZXD,
                        WkVariableSizeCollectionSrlzInputNode<T,VXS,ET,EXS,EXD,EXO>,
                        WkVariableSizeCollectionSrlzStructNode<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>>
{

}
