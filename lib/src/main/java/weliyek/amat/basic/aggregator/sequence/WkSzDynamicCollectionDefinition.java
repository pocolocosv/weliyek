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

import java.util.Collection;

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.ketza.util.array.WkSzDynamicSequenceDefinition;

public interface WkSzDynamicCollectionDefinition<
                        T extends Collection<ET>,
                        XO extends DynamicCollectionFieldDeserializer<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YO extends DynamicCollectionFieldSerializer<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZD extends WkSzNumberDefinition<?,?>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        ED extends WkSzDefinition<ET,EXO>,
                        VXS extends VariableLengthSettings,
                        VYS extends OperationSettings>
    extends WkSzDynamicCollectionSegment<
                        WkSzStructSubcomponent<XO, YO, ZD>,
                        WkSzStructSubcomponent<
                          XO, YO, VariableSizeCollectionField<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>>,
            WkSzDynamicSequenceDefinition<
                        T, XO, YO, ZD,
                        VariableSizeCollectionField<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
{

  @Override
  default int extractLengthFromSerializablesSequence(T collection) {
    return collection.size();
  }

}
