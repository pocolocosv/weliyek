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
import weliyek.serialization.WkSrlzOutputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketEncoderFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.number.WkNumberSrlzStructDefinitionFrameLeafNode;
import weliyek.serialization.number.WkNumberSrlzOutputPacketEncoderFrameLeafNode;
import weliyek.util.array.WkDynamicSequenceSrlzOutputPacketEncoderFrameNode;

public interface WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<
                        T extends Collection<ET>,
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YR extends WkSzWritingResult,
                        YD extends WkDynamicCollectionSrlzStructDefinitionFrameNode<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends WkNumberSrlzOutputPacketEncoderFrameLeafNode<ZT,?,?,?,ZYD>,
                        ZYD extends WkNumberSrlzStructDefinitionFrameLeafNode<ZT,?>,
                        ET,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSrlzStructDefinitionFrameNode<ET,?>,
                        EYO extends WkSrlzOutputPacketEncoderFrameNode<ET,EYS,?,?,EYD>,
                        VYS extends WkSzOperationSettings>
    extends WkDynamicCollectionSrlzPacketOperationFrameNode<
                        YS, YQ, YR, YD,
                        WkSrlzOutputPacketFieldFrameNode<T,YD,?>,
                        ZYO,
                        WkSrlzOutputPacketFieldFrameNode<ZT,ZYD,ZYO>,
                        WkSrlzOutputPacketSubfieldFrameNode<ZT,ZYD,ZYO>,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>,
                        WkSrlzOutputPacketFieldFrameNode<
                          T,
                          VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>,
                          VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>>,
                        WkSrlzOutputPacketSubfieldFrameNode<T,
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>,
                          VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>>>,
            WkCollectionSrlzOutputPacketEncoderFrameNode<T, YS, YQ, YR, YD>,
            WkDynamicSequenceSrlzOutputPacketEncoderFrameNode<T, YS, YQ, YR, YD, ZT, ZYO, ZYD,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>,
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>>
{

}
