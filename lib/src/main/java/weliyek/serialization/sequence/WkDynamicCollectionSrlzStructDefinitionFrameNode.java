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

import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSerdeDtreeNodeDataReader;
import weliyek.serialization.WkSerdeDtreeNodeDataWriter;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceDefinition;

public interface WkDynamicCollectionSrlzStructDefinitionFrameNode<
                        T extends Collection<ET>,
                        XO extends WkDynamicCollectionSrlzInputPacketDecoderFrameNode<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YO extends WkDynamicCollectionSrlzOutputPacketEncoderFrameNode<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZD extends WkSerdeDtreeNumberDefinition<?>,
                        ET,
                        EXS extends WkSettingsSrlzPacketOperationData,
                        EXD extends WkSerdeDtreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeNodeDataReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSettingsSrlzPacketOperationData,
                        EYD extends WkSerdeDtreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDtreeNodeStructDefinition<ET>,
                        VXS extends WkSzVariableLengthOperationSettings,
                        VYS extends WkSettingsSrlzPacketOperationData>
    extends WkSerdeDtreeDynamicCollection<
                        WkSerdeDtreeNodeStructComponentHandler<XO, YO, ZD>,
                        WkSerdeDtreeNodeStructComponentHandler<
                          XO, YO, WkVariableSizeCollectionSrlzStructNode<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>>,
            WkCollectionSrlzStructDefinitionFrameNode<T>,
            WkSerdeDtreeDynamicSequenceDefinition<
                        T, XO, YO, ZD,
                        WkVariableSizeCollectionSrlzStructNode<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
{

  @Override
  default int extractLengthFromSerializablesSequence(T collection) {
    return collection.size();
  }

}
