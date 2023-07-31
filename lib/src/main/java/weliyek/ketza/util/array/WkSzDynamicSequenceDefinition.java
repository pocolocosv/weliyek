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
package weliyek.ketza.util.array;

import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.basic.aggregator.WkSzAggregatorDefinition;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableSizeSequenceDefinition;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.sequence.WkSzSequenceDefinition;

public interface WkSzDynamicSequenceDefinition<
                        T,
                        XO extends DynamicSequenceDeserializing<T,?,?,?,?,?,?,?,?,?>,
                        YO extends DynamicSequenceSerializing<T,?,?,?,?,?,?,?,?,?>,
                        ZD extends WkSzNumberDefinition<?,?>,
                        VD extends WkSzVariableSizeSequenceDefinition<T,?>>
    extends WkSzDynamicSequenceSegment<
                        WkSzStructSubcomponent<XO, YO, ZD>,
                        WkSzStructSubcomponent<XO, YO, VD>>,
            WkSzSequenceDefinition<T, XO>,
            WkSzAggregatorDefinition<T, XO>
{

}
