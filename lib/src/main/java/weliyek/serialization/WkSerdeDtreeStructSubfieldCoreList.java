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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class WkSerdeDtreeStructSubfieldCoreList<
                        T,
                        XBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        XO extends WkSerdeDtreeAggregatorMsgReader<
                                      T,?,? extends WkSerdeDtreeOperationInputRuntime<?>,?,?>,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        YO extends WkSerdeDtreeAggregatorMsgWriter<
                                      T,?,? extends WkSerdeDtreeOperationOutputRuntime<?>,?,?>>
{

    private final List<WkSerdeDtreeStructSubfieldCore<?,T,?,?,?,XBC,XO,?,?,?,YBC,YO,?>>
                      structFieldCoreList = new ArrayList<>();
    private final List<WkSerdeDtreeStructSubfieldCore<?,T,?,?,?,XBC,XO,?,?,?,YBC,YO,?>>
                      roStructFieldCoreList = Collections.unmodifiableList(structFieldCoreList);
    private final List<WkSerdeDtreeStructField<?>>
                      subfieldList = new ArrayList<>();
    private final List<WkSerdeDtreeStructField<?>>
                      roSubfieldList = Collections.unmodifiableList(subfieldList);

    public WkSerdeDtreeStructSubfieldCoreList() {
    }

    List<WkSerdeDtreeStructSubfieldCore<?,T,?,?,?,XBC,XO,?,?,?,YBC,YO,?>>
    asRoCoreList() {
      return roStructFieldCoreList;
    }

    public List<WkSerdeDtreeStructField<?>> asSubfieldList() {
      return this.roSubfieldList;
    }

    boolean isEmpty() {
      return structFieldCoreList.isEmpty();
    }

    ListIterator<? extends WkSerdeDtreeStructSubfieldCore<?,T,?,?,?,XBC,XO,?,?,?,YBC,YO,?>>
    structIterator() {
      return roStructFieldCoreList.listIterator();
    }

    public <P_ extends WkSerdeDtreeStructSubfieldCore<?,T,?,?,?,XBC,XO,?,?,?,YBC,YO,?>>
    P_ addSubfield(P_ subfield) {
        if (structFieldCoreList.contains(subfield)) {
            throw new IllegalArgumentException();    // Cannot add same subfield
        }
        this.structFieldCoreList.add(subfield);
        this.subfieldList.add(subfield.asProtocolField());
        computeHandlersIndexes();
        return subfield;
    }

    public <P_ extends WkSerdeDtreeStructSubfieldCore<?,T,?,?,?,XBC,XO,?,?,?,YBC,YO,?>>
    P_ insertBefore(
      WkSerdeDtreeStructSubfieldCore<?,?,?,?,?,?,?,?,?,?,?,?,?> existingSubfield,
      P_ newSubfield) {
      int existingIndex = structFieldCoreList.indexOf(existingSubfield);
      if (-1 == existingIndex) {
        throw new IllegalStateException();
      }
      this.structFieldCoreList.set(existingIndex, newSubfield);
      this.subfieldList.set(existingIndex, newSubfield.asProtocolField());
      computeHandlersIndexes();
      return newSubfield;
    }

    public <P_ extends WkSerdeDtreeStructSubfieldCore<?,T,?,?,?,XBC,XO,?,?,?,YBC,YO,?>>
    P_ insertAfter(
      WkSerdeDtreeStructSubfieldCore<?,?,?,?,?,?,?,?,?,?,?,?,?> existingSubfield,
      P_ newSubfield) {
      int existingIndex = structFieldCoreList.indexOf(existingSubfield);
      if (-1 == existingIndex) {
        throw new IllegalStateException();
      }
      this.structFieldCoreList.set(existingIndex+1, newSubfield);
      this.subfieldList.set(existingIndex, newSubfield.asProtocolField());
      computeHandlersIndexes();
      return newSubfield;
    }

    private void computeHandlersIndexes() {
      for (int i = 0; i < structFieldCoreList.size(); i++) {
        this.structFieldCoreList.get(i).setOrder(i);
      }
    }

    public List<WkSerdeDtreeStructSubfield<?,?,?>> collectRequiredSubfields() {
      List<WkSerdeDtreeStructSubfield<?,?,?>> requiredSubfields =new ArrayList<>();
      for (WkSerdeDtreeStructSubfieldCore<?,?,?,?,?,?,?,?,?,?,?,?,?> subcompHandler : structFieldCoreList) {
        if (subcompHandler.isDeserializedRequiredByAggregator()) {
          requiredSubfields.add(subcompHandler.asProtocolField());
        }
      }
      if (requiredSubfields.isEmpty()) {
        return Collections.emptyList();
      } else {
        return requiredSubfields;
      }
    }

}