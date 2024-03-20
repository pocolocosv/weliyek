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

import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDTreeNodeDataReader;
import weliyek.serialization.WkSerdeDTreeNodeDataWriter;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeDTreeNumberDefinition;
import weliyek.util.array.WkDynamicSequenceSrlzStructDefinitionFrameNode;

public interface WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        T extends Collection<ET>,
                        XO extends WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YO extends WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZD extends WkSerdeDTreeNumberDefinition<?>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDTreeNodeDataReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSerdeDTreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDTreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDTreeNodeStructDefinition<ET>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VYS extends WkSettingsSrlzPacketOperationData>
    extends WkDynamicCollectionSrlzFrameNode<
                        WkSerdeDTreeNodeStructComponentHandler<XO, YO, ZD>,
                        WkSerdeDTreeNodeStructComponentHandler<
                          XO, YO, WkVariableSizeCollectionSrlzStructNode<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>>,
            WkCollectionSrlzStructDefinitionFrameNode<T>,
            WkDynamicSequenceSrlzStructDefinitionFrameNode<
                        T, XO, YO, ZD,
                        WkVariableSizeCollectionSrlzStructNode<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
{

  @Override
  default int extractLengthFromSerializablesSequence(T collection) {
    return collection.size();
  }

}
