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

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import weliyek.amat.base.WkSzStructSubcomponent;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfieldCore;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfieldCore;
import weliyek.serialization.bytestream.InputBytestreamGeneralBase;
import weliyek.serialization.bytestream.OutputBytestreamGeneralBase;

public class SubcomponentHandlerList<
                        T,
                        XBC extends InputBytestreamGeneralBase<?>,
                        XD extends WkSzAggregatorDefinition<T,?>,
                        XO extends WkSzAggregatorReader<T,?,? extends DeserializingRuntime<?>,?,XD>,
                        YBC extends OutputBytestreamGeneralBase<?>,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<T,?,? extends SerializingRuntime<?>,?,YD>>
    extends AbstractList<WkSzSubcomponentCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
{

    private final List<WkSzSubcomponentCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
                      handlerContainer = new ArrayList<>();
    private final List<WkSzStructSubcomponent<XO,YO,?>>
                      subfieldList = new ArrayList<>();
    private final List<WkSzStructSubcomponent<?,?,?>>
                      roSubfieldList = Collections.unmodifiableList(subfieldList);

    public SubcomponentHandlerList() {
    }

    @SuppressWarnings("unchecked")
    public
    <YYD extends WkSzAggregatorDefinition<YY,?>,
     YY,
     YYB extends OutputBytestreamGeneralBase<?>,
     YYO extends WkSzAggregatorWriter<YY,?,? extends SerializingRuntime<?>,?,YYD>>
    WritingHandlerList<YY,YYB,YYD,YYO> newSerializingHandlers(
      WkSzAggregatorWriterCore<?,?,?,YYB,?,?,?,YYD,YYO,?,?,?> parentSerializingOpCore) {
      List<WkSzPacketWriterSubfieldCore<?,?,?,?,?,?,YYD,YYO>> serializingHandlerList = new ArrayList<>();
      WkSzAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>
        parentOpCore = (WkSzAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>) parentSerializingOpCore;
      for (WkSzSubcomponentCore<?,?,?,?,?,?,?,?,?,?,?,YBC,YD,YO,?,?> subcomponentHandler : this) {
        WkSzPacketWriterSubfieldCore<?,?,?,?,?,YBC,YD,YO>
          serializingHandler = subcomponentHandler.newWritingSubfieldHandlerCore(parentOpCore);
        serializingHandlerList.add((WkSzPacketWriterSubfieldCore<?,?,?,?,?,?,YYD,YYO>)serializingHandler);
      }
      return new WritingHandlerList<YY,YYB,YYD,YYO>(serializingHandlerList);
    }

    @SuppressWarnings("unchecked")
    public
    <XXD extends WkSzAggregatorDefinition<XX,?>,
     XX,
     XXB extends InputBytestreamGeneralBase<?>,
     XXO extends WkSzAggregatorReader<XX,?,? extends DeserializingRuntime<?>,?,XXD>>
    ReadingHandlerList<XX,XXB,XXD,XXO> newDeserializingHandlers(
      WkSzAggregatorReaderCore<?,?,?,XXB,?,?,?,XXD,XXO,?,?,?> parentDeserializingOpCore) {
      List<WkSzPacketReaderSubfieldCore<?,?,?,?,?,?,XXD,XXO>> deserializingHandlerList = new ArrayList<>();
      WkSzAggregatorReaderCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>
        parentOpCore = (WkSzAggregatorReaderCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>)parentDeserializingOpCore;
      for (WkSzSubcomponentCore<?,?,?,?,?,XBC,XD,XO,?,?,?,?,?,?,?,?> subcomponentHandler : this) {
        WkSzPacketReaderSubfieldCore<?,?,?,?,?,?,XD,XO>
          deserializingHandler = subcomponentHandler
                                    .newReadingSubfieldHandlerCore(parentOpCore);
        deserializingHandlerList.add((WkSzPacketReaderSubfieldCore<?,?,?,?,?,?,XXD,XXO>)deserializingHandler);
      }
      return new ReadingHandlerList<XX,XXB,XXD,XXO>(deserializingHandlerList);
    }

    public List<WkSzStructSubcomponent<?,?,?>> asSubfieldList() {
      return this.roSubfieldList;
    }

    @Override
    public WkSzSubcomponentCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>
    get(int index) {
        return handlerContainer.get(index);
    }

    @Override
    public int size() {
        return handlerContainer.size();
    }

    public <P_ extends WkSzSubcomponentCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ addSubfield(P_ subfield) {
        if (handlerContainer.contains(subfield)) {
            throw new IllegalArgumentException();    // Cannot add same subfield
        }
        this.handlerContainer.add(subfield);
        this.subfieldList.add(subfield.body());
        computeHandlersIndexes();
        return subfield;
    }

    public <P_ extends WkSzSubcomponentCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ insertBefore(
      WkSzSubcomponentCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubfield,
      P_ newSubfield) {
      int existingIndex = this.indexOf(existingSubfield);
      if (-1 == existingIndex) {
        throw new IllegalStateException();
      }
      this.handlerContainer.set(existingIndex, newSubfield);
      this.subfieldList.set(existingIndex, newSubfield.body());
      computeHandlersIndexes();
      return newSubfield;
    }

    public <P_ extends WkSzSubcomponentCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ insertAfter(
      WkSzSubcomponentCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubfield,
      P_ newSubfield) {
      int existingIndex = this.indexOf(existingSubfield);
      if (-1 == existingIndex) {
        throw new IllegalStateException();
      }
      this.handlerContainer.set(existingIndex+1, newSubfield);
      this.subfieldList.set(existingIndex, newSubfield.body());
      computeHandlersIndexes();
      return newSubfield;
    }

    private void computeHandlersIndexes() {
      for (int i = 0; i < handlerContainer.size(); i++) {
        this.handlerContainer.get(i).setOrder(i);
      }
    }

    public List<WkSzStructSubcomponent<?,?,?>> collectRequiredSubfields() {
      List<WkSzStructSubcomponent<?,?,?>> requiredSubfields =new ArrayList<>();
      for (WkSzSubcomponentCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> subcompHandler : handlerContainer) {
        if (subcompHandler.isDeserializedRequiredByAggregator()) {
          requiredSubfields.add(subcompHandler.body());
        }
      }
      if (requiredSubfields.isEmpty()) {
        return Collections.emptyList();
      } else {
        return requiredSubfields;
      }
    }

}