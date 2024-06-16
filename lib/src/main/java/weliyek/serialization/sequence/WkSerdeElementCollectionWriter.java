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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;

public interface WkSerdeElementCollectionWriter<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntimeSequenceCommon<?>,
                        YR extends WkSerdeDtreeOperationResult<T>,
                        YD extends WkSerdeElementCollectionDefinition<T,?,?,ET,?>,
                        ET,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,?,?,?,EYD>>
        extends WkSerdeElementCollectionOperation<
                        YS, YQ, YR, YD,
                        WkSerdeDtreeMsgOutputField<ET, EYD, EYO>>,
                WkSerdeDtreeCollectionWriter<T, YS, YQ, YR, YD>
{

  List<ET> serializableAsList();

  @SuppressWarnings("unchecked")
  static <EY, Y extends Collection<? extends EY>>
  List<EY> collectionToList(Y collection) {
    if (collection instanceof List) {
      return Collections.unmodifiableList((List<EY>)collection);
    } else {
      return Collections.unmodifiableList(new ArrayList<EY>(collection));
    }
  }

}
