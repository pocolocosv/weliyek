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

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.SubfieldHandlerList;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.DeserializingSubfieldHandlerCore;
import weliyek.amat.base.input.InputBytestreamGeneralBase;

public class ReadingHandlerList<
                        X,
                        XBC extends InputBytestreamGeneralBase<?>,
                        XD extends AggregatorDefinition<X,?>,
                        XO extends AggregatorReading<X,?,? extends DeserializingRuntime<?>,?,XD>>
        extends SubfieldHandlerList<
                        DeserializingSubfieldHandler<?,?,?>,
                        DeserializingSubfieldHandlerCore<?,?,?,?,?,?,XD,XO>,
                        AggregatorReadingCore<?,?,?,?,?,?,?,XD,XO,?,?,?>>
{

    ReadingHandlerList(
      List<DeserializingSubfieldHandlerCore<?,?,?,?,?,?,XD,XO>> deserializingSubfieldHandlers) {
      super(deserializingSubfieldHandlers);
    }

    @SuppressWarnings("unchecked")
    <ST,
     SXS extends OperationSettings,
     SXD extends DefinitionSegment<ST,?>,
     SXO extends DeserializingOperation<ST,SXS,?,?,SXD>>
    DeserializingSubfieldHandlerCore<ST,SXS,SXD,SXO,X,XBC,XD,XO> findSubfieldpacket(
      SubcomponentHandlerCore<ST,SXS,SXD,SXO,X,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD> searchedSubcomponent) {
      for (DeserializingSubfieldHandlerCore<?,?,?,?,?,?,XD,XO> deserializingSubfieldHandler : this) {
        SubcomponentHandlerCore<?,?,?,?,?,?,XD,XO,?,?,?,?,?,?,?,?> subcomponentHandler = deserializingSubfieldHandler.subcomponentHandlerCore();
        if (subcomponentHandler.equals(searchedSubcomponent)) {
          return (DeserializingSubfieldHandlerCore<ST,SXS,SXD,SXO,X,XBC,XD,XO>) deserializingSubfieldHandler;
        }
      }
      throw new RuntimeException(); // Should never happen.
    }

}
