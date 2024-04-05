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
package weliyek.serialization;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WkSrlzStructSubcomponentFrameNodeCoreList<
                        T,
                        XBC extends WkSzInputBytestreamBase<?>,
                        XD extends WkSerdeDtreeAggregatorDefinition<T>,
                        XO extends WkSerdeDtreeAggregatorReader<T,?,? extends WkDecodingRuntimeSrlzPacketOperationData<?>,?,XD>,
                        YBC extends WkSzOutputBytestreamBase<?>,
                        YD extends WkSerdeDtreeAggregatorDefinition<T>,
                        YO extends WkSerdeDtreeAggregatorWriter<T,?,? extends WkEncodingRuntimeSrlzPacketOperationData<?>,?,YD>>
    extends AbstractList<WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
{

    private final List<WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
                      handlerContainer = new ArrayList<>();
    private final List<WkSerdeDtreeNodeStructComponentHandler<XO,YO,?>>
                      subfieldList = new ArrayList<>();
    private final List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>>
                      roSubfieldList = Collections.unmodifiableList(subfieldList);

    public WkSrlzStructSubcomponentFrameNodeCoreList() {
    }

    @SuppressWarnings("unchecked")
    public
    <YYD extends WkSerdeDtreeAggregatorDefinition<YY>,
     YY,
     YYB extends WkSzOutputBytestreamBase<?>,
     YYO extends WkSerdeDtreeAggregatorWriter<YY,?,? extends WkEncodingRuntimeSrlzPacketOperationData<?>,?,YYD>>
    WkSrlzOutputPacketSubfieldList<YY,YYB,YYD,YYO> newSerializingHandlers(
      WkSerdeDtreeAggregatorWriterCore<?,?,?,YYB,?,?,?,YYD,YYO,?,?,?> parentSerializingOpCore) {
      List<WkSrlzOutputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,YYD,YYO>> serializingHandlerList = new ArrayList<>();
      WkSerdeDtreeAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>
        parentOpCore = (WkSerdeDtreeAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>) parentSerializingOpCore;
      for (WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?,?,YBC,YD,YO,?,?> subcomponentHandler : this) {
        WkSrlzOutputPacketSubfieldFrameNodeCore<?,?,?,?,?,YBC,YD,YO>
          serializingHandler = subcomponentHandler.newWritingSubfieldHandlerCore(parentOpCore);
        serializingHandlerList.add((WkSrlzOutputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,YYD,YYO>)serializingHandler);
      }
      return new WkSrlzOutputPacketSubfieldList<YY,YYB,YYD,YYO>(serializingHandlerList);
    }

    @SuppressWarnings("unchecked")
    public
    <XXD extends WkSerdeDtreeAggregatorDefinition<XX>,
     XX,
     XXB extends WkSzInputBytestreamBase<?>,
     XXO extends WkSerdeDtreeAggregatorReader<XX,?,? extends WkDecodingRuntimeSrlzPacketOperationData<?>,?,XXD>>
    WkSrlzInputPacketSubfieldList<XX,XXB,XXD,XXO> newDeserializingHandlers(
      WkSerdeDtreeAggregatorReaderCore<?,?,?,XXB,?,?,?,XXD,XXO,?,?,?> parentDeserializingOpCore) {
      List<WkSrlzInputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,XXD,XXO>> deserializingHandlerList = new ArrayList<>();
      WkSerdeDtreeAggregatorReaderCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>
        parentOpCore = (WkSerdeDtreeAggregatorReaderCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>)parentDeserializingOpCore;
      for (WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,XBC,XD,XO,?,?,?,?,?,?,?,?> subcomponentHandler : this) {
        WkSrlzInputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,XD,XO>
          deserializingHandler = subcomponentHandler
                                    .newReadingSubfieldHandlerCore(parentOpCore);
        deserializingHandlerList.add((WkSrlzInputPacketSubfieldFrameNodeCore<?,?,?,?,?,?,XXD,XXO>)deserializingHandler);
      }
      return new WkSrlzInputPacketSubfieldList<XX,XXB,XXD,XXO>(deserializingHandlerList);
    }

    public List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> asSubfieldList() {
      return this.roSubfieldList;
    }

    @Override
    public WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>
    get(int index) {
        return handlerContainer.get(index);
    }

    @Override
    public int size() {
        return handlerContainer.size();
    }

    public <P_ extends WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ addSubfield(P_ subfield) {
        if (handlerContainer.contains(subfield)) {
            throw new IllegalArgumentException();    // Cannot add same subfield
        }
        this.handlerContainer.add(subfield);
        this.subfieldList.add(subfield.body());
        computeHandlersIndexes();
        return subfield;
    }

    public <P_ extends WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ insertBefore(
      WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubfield,
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

    public <P_ extends WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,XBC,XD,XO,?,?,?,YBC,YD,YO,?,?>>
    P_ insertAfter(
      WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> existingSubfield,
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

    public List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> collectRequiredSubfields() {
      List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> requiredSubfields =new ArrayList<>();
      for (WkSrlzStructSubcomponentFrameNodeCore<?,?,?,?,?,?,XD,XO,?,?,?,?,YD,YO,?,?> subcompHandler : handlerContainer) {
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