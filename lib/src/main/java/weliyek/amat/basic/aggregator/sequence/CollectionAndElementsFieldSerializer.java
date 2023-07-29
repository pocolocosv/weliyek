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
package weliyek.amat.basic.aggregator.sequence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.SerializingField;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;

public interface CollectionAndElementsFieldSerializer<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YQ extends SequenceWritingRuntime<?>,
                        YR extends SerializingResult,
                        YD extends CollectionAndElementsFieldDefinition<T,?,?,ET,?>,
                        ET,
                        EYD extends DefinitionSegment<ET,?>,
                        EYO extends SerializingOperation<ET,?,?,?,EYD>>
        extends CollectionAndElementsFieldOperation<
                        YS, YQ, YR, YD,
                        SerializingField<T,YD,?>,
                        EYO,
                        SerializingField<ET,EYD,EYO>,
                        SerializingSubfieldHandler<ET,EYD,EYO>>,
                CollectionFieldSerializer<T, YS, YQ, YR, YD>
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
