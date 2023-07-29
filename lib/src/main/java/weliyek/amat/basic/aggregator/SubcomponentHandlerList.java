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

import weliyek.amat.base.SubcomponentHandler;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandlerCore;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandlerCore;

public class SubcomponentHandlerList<
                        T,
                        XBC extends InputBytestreamGeneralBase<?>,
                        XD extends AggregatorDefinition<T,?>,
                        XO extends AggregatorReading<T,?,? extends DeserializingRuntime<?>,?,XD>,
                        YBC extends OutputBytestreamGeneralBase<?>,
                        YD extends AggregatorDefinition<T,?>,
                        YO extends AggregatorWriting<T,?,? extends SerializingRuntime<?>,?,YD>>
    extends AbstractList<SubcomponentHandlerCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
{

    private final List<SubcomponentHandlerCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
                      handlerContainer = new ArrayList<>();
    private final List<SubcomponentHandler<XO,YO,?>>
                      subfieldList = new ArrayList<>();
    private final List<SubcomponentHandler<?,?,?>>
                      roSubfieldList = Collections.unmodifiableList(subfieldList);

    public SubcomponentHandlerList() {
    }

    @SuppressWarnings("unchecked")
    public
    <YYD extends AggregatorDefinition<YY,?>,
     YY,
     YYB extends OutputBytestreamGeneralBase<?>,
     YYO extends AggregatorWriting<YY,?,? extends SerializingRuntime<?>,?,YYD>>
    WritingHandlerList<YY,YYB,YYD,YYO> newSerializingHandlers(
      AggregatorWritingCore<?,?,?,YYB,?,?,?,YYD,YYO,?,?,?> parentSerializingOpCore) {
      List<SerializingSubfieldHandlerCore<?,?,?,?,?,?,YYD,YYO>> serializingHandlerList = new ArrayList<>();
      AggregatorWritingCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>
        parentOpCore = (AggregatorWritingCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>) parentSerializingOpCore;
      for (SubcomponentHandlerCore<?,?,?,?,?,?,?,?,?,?,?,YBC,YD,YO,?,?> subcomponentHandler : this) {
        SerializingSubfieldHandlerCore<?,?,?,?,?,YBC,YD,YO>
          serializingHandler = subcomponentHandler.newWritingSubfieldHandlerCore(parentOpCore);
        serializingHandlerList.add((SerializingSubfieldHandlerCore<?,?,?,?,?,?,YYD,YYO>)serializingHandler);
      }
      return new WritingHandlerList<YY,YYB,YYD,YYO>(serializingHandlerList);
    }

    @SuppressWarnings("unchecked")
    public
    <XXD extends AggregatorDefinition<XX,?>,
     XX,
     XXB extends InputBytestreamGeneralBase<?>,
     XXO extends AggregatorReading<XX,?,? extends DeserializingRuntime<?>,?,XXD>>
    ReadingHandlerList<XX,XXB,XXD,XXO> newDeserializingHandlers(
      AggregatorReadingCore<?,?,?,XXB,?,?,?,XXD,XXO,?,?,?> parentDeserializingOpCore) {
      List<DeserializingSubfieldHandlerCore<?,?,?,?,?,?,XXD,XXO>> deserializingHandlerList = new ArrayList<>();
      AggregatorReadingCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>
        parentOpCore = (AggregatorReadingCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>)parentDeserializingOpCore;
      for (SubcomponentHandlerCore<?,?,?,?,?,XBC,XD,XO,?,?,?,?,?,?,?,?> subcomponentHandler : this) {
        DeserializingSubfieldHandlerCore<?,?,?,?,?,?,XD,XO>
          deserializingHandler = subcomponentHandler
                                    .newReadingSubfieldHandlerCore(parentOpCore);
        deserializingHandlerList.add((DeserializingSubfieldHandlerCore<?,?,?,?,?,?,XXD,XXO>)deserializingHandler);
      }
      return new ReadingHandlerList<XX,XXB,XXD,XXO>(deserializingHandlerList);
    }

    public List<SubcomponentHandler<?,?,?>> asSubfieldList() {
      return this.roSubfieldList;
    }

    @Override
    public SubcomponentHandlerCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>
    get(int index) {
        return handlerContainer.get(index);
    }

    @Override
    public int size() {
        return handlerContainer.size();
    }

    public <P_ extends SubcomponentHandlerCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ addSubfield(P_ subfield) {
        if (handlerContainer.contains(subfield)) {
            throw new IllegalArgumentException();    // Cannot add same subfield
        }
        this.handlerContainer.add(subfield);
        this.subfieldList.add(subfield.body());
        computeHandlersIndexes();
        return subfield;
    }

    public <P_ extends SubcomponentHandlerCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ insertBefore(
      SubcomponentHandlerCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubfield,
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

    public <P_ extends SubcomponentHandlerCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ insertAfter(
      SubcomponentHandlerCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubfield,
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

    public List<SubcomponentHandler<?,?,?>> collectRequiredSubfields() {
      List<SubcomponentHandler<?,?,?>> requiredSubfields =new ArrayList<>();
      for (SubcomponentHandlerCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> subcompHandler : handlerContainer) {
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