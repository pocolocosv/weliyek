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
package weliyek.util.array;

import weliyek.serialization.WkAggregatorSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.serialization.sequence.WkSerdeDTreeSequenceDefinition;
import weliyek.serialization.sequence.WkSerdeDTreeVariableSizeSequenceDefinition;

public interface WkDynamicSequenceSrlzStructDefinitionFrameNode<
                        T,
                        XO extends WkDynamicSequenceSrlzInputPacketDecoderFrameNode<T,?,?,?,?,?,?,?,?,?>,
                        YO extends WkDynamicSequenceSrlzOutputPacketEncoderFrameNode<T,?,?,?,?,?,?,?,?,?>,
                        ZD extends WkSerdeDTreeNumberDefinition<?>,
                        VD extends WkSerdeDTreeVariableSizeSequenceDefinition<T>>
    extends WkDynamicSequenceSrlzFrameNode<
                        WkSerdeDTreeNodeStructComponentHandler<XO, YO, ZD>,
                        WkSerdeDTreeNodeStructComponentHandler<XO, YO, VD>>,
            WkSerdeDTreeSequenceDefinition<T>,
            WkAggregatorSrlzStructDefinitionFrameNode<T>
{

}
