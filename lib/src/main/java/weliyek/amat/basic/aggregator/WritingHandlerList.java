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

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.SubfieldHandlerList;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.base.output.WkSzPacketWriterSubfieldCore;

public class WritingHandlerList<
                        Y,
                        YBC extends OutputBytestreamGeneralBase<?>,
                        YD extends WkSzAggregatorDefinition<Y,?>,
                        YO extends WkSzAggregatorWriter<Y,?,? extends SerializingRuntime<?>,?,YD>>
        extends SubfieldHandlerList<
                        WkSzPacketWriterSubfield<?,?,?>,
                        WkSzPacketWriterSubfieldCore<?,?,?,?,?,?,YD,YO>,
                        WkSzAggregatorWriterCore<?,?,?,?,?,?,?,YD,YO,?,?,?>>
{

  WritingHandlerList(
    List<WkSzPacketWriterSubfieldCore<?,?,?,?,?,?,YD,YO>> subfieldHandlers) {
    super(subfieldHandlers);
  }

  @SuppressWarnings("unchecked")
  <SY,
   SYS extends OperationSettings,
   SYD extends WkSzDefinition<SY,?>,
   SYO extends WkSzPacketWriterOperation<SY,SYS,?,?,SYD>>
  WkSzPacketWriterSubfieldCore<SY,SYS,SYD,SYO,Y,YBC,YD,YO> findSubfieldpacket(
    WkSzSubcomponentCore<SY,?,?,?,Y,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD> searchedSubcomponent) {
    for (WkSzPacketWriterSubfieldCore<?,?,?,?,?,?,YD,YO> serializingSubfieldHandler : this) {
      WkSzSubcomponentCore<?,?,?,?,?,?,?,?,?,?,?,?,YD,YO,?,? extends YD> subcomponentHandler = serializingSubfieldHandler.subcomponentHandlerCore();
      if (subcomponentHandler.equals(searchedSubcomponent)) {
        return (WkSzPacketWriterSubfieldCore<SY,SYS,SYD,SYO,Y,YBC,YD,YO>) serializingSubfieldHandler;
      }
    }
    throw new RuntimeException(); // Should never happen.
  }

}