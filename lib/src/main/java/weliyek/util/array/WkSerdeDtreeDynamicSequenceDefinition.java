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

import weliyek.serialization.WkSerdeDtreeAggregatorDefinition;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeSequenceDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceDefinition;

public interface WkSerdeDtreeDynamicSequenceDefinition<
                        T,
                        XO extends WkSerdeDtreeDynamicSequenceReader<T,?,?,?,?,?,?,?,?,?>,
                        YO extends WkSerdeDtreeDynamicSequenceWriter<T,?,?,?,?,?,?,?,?,?>,
                        ZD extends WkSerdeDtreeNumberDefinition<?>,
                        VD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>>
    extends WkSerdeDtreeDynamicSequence<
                        WkSerdeDtreeNodeStructComponentHandler<XO, YO, ZD>,
                        WkSerdeDtreeNodeStructComponentHandler<XO, YO, VD>>,
            WkSerdeDtreeSequenceDefinition<T>,
            WkSerdeDtreeAggregatorDefinition<T>
{

}
