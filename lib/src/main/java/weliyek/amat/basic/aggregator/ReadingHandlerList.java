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
package weliyek.amat.basic.aggregator;

import java.util.List;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.SubfieldHandlerList;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.base.input.WkSzPacketReaderSubfieldCore;
import weliyek.serialization.WkSzDefinition;

public class ReadingHandlerList<
                        X,
                        XBC extends WkSzInputBytestreamBase<?>,
                        XD extends WkSzAggregatorDefinition<X,?>,
                        XO extends WkSzAggregatorReader<X,?,? extends WkSzReadingRuntime<?>,?,XD>>
        extends SubfieldHandlerList<
                        WkSzPacketReaderSubfield<?,?,?>,
                        WkSzPacketReaderSubfieldCore<?,?,?,?,?,?,XD,XO>,
                        WkSzAggregatorReaderCore<?,?,?,?,?,?,?,XD,XO,?,?,?>>
{

    ReadingHandlerList(
      List<WkSzPacketReaderSubfieldCore<?,?,?,?,?,?,XD,XO>> deserializingSubfieldHandlers) {
      super(deserializingSubfieldHandlers);
    }

    @SuppressWarnings("unchecked")
    <ST,
     SXS extends WkSzOperationSettings,
     SXD extends WkSzDefinition<ST,?>,
     SXO extends WkSzPacketReaderOperation<ST,SXS,?,?,SXD>>
    WkSzPacketReaderSubfieldCore<ST,SXS,SXD,SXO,X,XBC,XD,XO> findSubfieldpacket(
      WkSzSubcomponentCore<ST,SXS,SXD,SXO,X,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD> searchedSubcomponent) {
      for (WkSzPacketReaderSubfieldCore<?,?,?,?,?,?,XD,XO> deserializingSubfieldHandler : this) {
        WkSzSubcomponentCore<?,?,?,?,?,?,XD,XO,?,?,?,?,?,?,?,?> subcomponentHandler = deserializingSubfieldHandler.subcomponentHandlerCore();
        if (subcomponentHandler.equals(searchedSubcomponent)) {
          return (WkSzPacketReaderSubfieldCore<ST,SXS,SXD,SXO,X,XBC,XD,XO>) deserializingSubfieldHandler;
        }
      }
      throw new RuntimeException(); // Should never happen.
    }

}
