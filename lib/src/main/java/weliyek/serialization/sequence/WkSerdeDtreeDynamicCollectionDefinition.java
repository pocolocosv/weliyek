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

import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceDefinition;

public interface WkSerdeDtreeDynamicCollectionDefinition<
                        T extends Collection<ET>,
                        XO extends WkSerdeDtreeDynamicCollectionReader<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YO extends WkSerdeDtreeDynamicCollectionWriter<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZD extends WkSerdeDtreeNumberStructDefinition<?>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
                        ED extends WkSerdeDtreeStructDefinition<ET>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength,
                        VYS extends WkSerdeDtreeOperationSettings>
    extends WkSerdeDtreeDynamicCollection<
                        WkSerdeDtreeStructSubfield<XO, YO, ZD>,
                        WkSerdeDtreeStructSubfield<
                          XO, YO, WkSerdeVariableSizeElementCollection<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>>,
            WkSerdeDtreeCollectionDefinition<T>,
            WkSerdeDtreeDynamicSequenceDefinition<
                        T, XO, YO, ZD,
                        WkSerdeVariableSizeElementCollection<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
{

  @Override
  default int extractLengthFromSerializablesSequence(T collection) {
    return collection.size();
  }

}
