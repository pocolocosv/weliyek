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

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.SubcomponentHandler;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.ketza.util.array.DynamicSequenceDefinition;

public interface DynamicCollectionFieldDefinition<
                        T extends Collection<ET>,
                        XO extends DynamicCollectionFieldDeserializer<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        YO extends DynamicCollectionFieldSerializer<T,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZD extends NumberDefinition<?,?>,
                        ET,
                        EXS extends OperationSettings,
                        EXD extends DefinitionSegment<ET,?>,
                        EXO extends DeserializingOperation<ET,EXS,?,?,EXD>,
                        EYS extends OperationSettings,
                        EYD extends DefinitionSegment<ET,?>,
                        EYO extends SerializingOperation<ET,EYS,?,?,EYD>,
                        ED extends DefinitionSegment<ET,EXO>,
                        VXS extends VariableLengthSettings,
                        VYS extends OperationSettings>
    extends DynamicCollectionFieldSegment<
                        SubcomponentHandler<XO, YO, ZD>,
                        SubcomponentHandler<
                          XO, YO, VariableSizeCollectionField<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>>,
            DynamicSequenceDefinition<
                        T, XO, YO, ZD,
                        VariableSizeCollectionField<T,VXS,VYS,ET,EXS,EXD,EXO,EYS,EYD,EYO,ED>>
{

  @Override
  default int extractLengthFromSerializablesSequence(T collection) {
    return collection.size();
  }

}
