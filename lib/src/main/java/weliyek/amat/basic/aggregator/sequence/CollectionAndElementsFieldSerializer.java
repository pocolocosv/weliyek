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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.serialization.WkSzDefinition;

public interface CollectionAndElementsFieldSerializer<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YQ extends SequenceWritingRuntime<?>,
                        YR extends SerializingResult,
                        YD extends WkSzCollectionAndElementsDefinition<T,?,?,ET,?>,
                        ET,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,?,?,?,EYD>>
        extends WkSzCollectionAndElementsOperation<
                        YS, YQ, YR, YD,
                        WkSzPacketWriterField<T,YD,?>,
                        EYO,
                        WkSzPacketWriterField<ET,EYD,EYO>,
                        WkSzPacketWriterSubfield<ET,EYD,EYO>>,
                WkSzCollectionWriter<T, YS, YQ, YR, YD>
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
