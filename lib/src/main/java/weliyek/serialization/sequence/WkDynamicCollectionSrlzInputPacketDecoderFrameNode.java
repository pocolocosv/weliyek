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

import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzInputPacketDecoderFrameLeafNode;
import weliyek.util.array.WkDynamicSequenceSrlzInputPacketDecoderFrameNode;

public interface WkDynamicCollectionSrlzInputPacketDecoderFrameNode<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkDynamicCollectionSrlzStructDefinitionFrameNode<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends WkNumberSrlzInputPacketDecoderFrameLeafNode<ZT,?,?,?,ZXD>,
                        ZXD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EXO extends WkSrlzInputPacketDecoderFrameNode<ET,EXS,?,?,EXD>,
                        VXS extends WkSzVariableLengthOperationSettings>
    extends WkDynamicCollectionSrlzPacketOperationFrameNode<
                        XS, XQ, XR, XD,
                        WkSrlzInputPacketFieldFrameNode<T,XD,?>,
                        ZXO,
                        WkSrlzInputPacketFieldFrameNode<ZT,ZXD,ZXO>,
                        WkSrlzInputPacketSubfieldFrameNode<ZT,ZXD,ZXO>,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>,
                        WkSrlzInputPacketFieldFrameNode<T,
                          VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                          VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>>,
                          WkSrlzInputPacketSubfieldFrameNode<
                          T,
                          VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                          VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>>>,
            WkCollectionSrlzInputPacketDecoderFrameNode<T, XS, XQ, XR, XD>,
            WkDynamicSequenceSrlzInputPacketDecoderFrameNode<
                        T, XS, XQ, XR, XD, ZT, ZXO, ZXD,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>,
                        VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>>
{

}
