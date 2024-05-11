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
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

public abstract class WkSerdeDtreeAggregatorMsgSubfields<
                        SPC extends WkSerdeDtreeStructSubfieldCore<?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        SK extends WkSerdeDtreeMsgField<?,?,?>,
                        SKC extends WkSerdeDtreeMsgFieldCore<?,?,?,?,?,? extends SK,?,?,?>,
                        OC extends WkSerdeDtreeMsgOperationCore<?,?,?,?,?,?,? extends WkSerdeDtreeAggregatorMsgOperation<?,?,?,?>,?,?>>
{

    protected final OC ownerAggregator;
    private final Map<WkSerdeDtreeStructSubfieldCore<?,?,?,?,?,?,?,?,?,?,?,?,?>, SKC> dataByStructMap = new HashMap<>();

    private final List<SKC> dataFieldCoreList = new ArrayList<>();
    private final List<SK> dataFieldList = new ArrayList<>();
    private final List<SK> roDataFieldList = Collections.unmodifiableList(dataFieldList);
    private ListIterator<? extends SPC> structIter;
    private SKC currentDataCore;

    protected WkSerdeDtreeAggregatorMsgSubfields(
      OC ownerAggregator) {
      this.ownerAggregator = ownerAggregator;
    }

    SKC getMsgSubfield(@SuppressWarnings("rawtypes") WkSerdeDtreeStructSubfieldCore structField) {
      return this.dataByStructMap.get(structField);
    }

    protected void setHandler(int i, SKC handler) {
      this.dataFieldCoreList.set(i, handler);
      this.dataFieldList.set(i, handler.asPacket());
    }

    public boolean hasStarted() {
        return null != structIter;
    }

    public Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> processBytestream() {
        if (isStructSubfieldListEmpty()) {
          throw new IllegalStateException();  // Must have at least one field.
        }
        if ( ! hasStarted()) {
          // This is the first run, get first iterator.
          structIter = getStructSubfiedIter();
          startNextField();
        }
        Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> completed = currentDataCore.processSingleStepBytestream();
        if (currentDataCore.isCompleted()) {
            startNextField();
        }
        return completed;
    }

    public boolean isCompleted() {
      return hasStarted() && (! structIter.hasNext()) && currentDataCore.isCompleted();
    }

    private void startNextField() {
        if ( ! structIter.hasNext()) {
            return;
        }
        SPC currentStructCore = structIter.next();
        currentDataCore = newAndActivatedDataSubfieldCore(currentStructCore);
        dataByStructMap.put(currentStructCore, currentDataCore);
        dataFieldCoreList.add(currentDataCore);
        dataFieldList.add(currentDataCore.asPacket());
    }

    abstract boolean isStructSubfieldListEmpty();

    abstract ListIterator<? extends SPC> getStructSubfiedIter();

    abstract SKC newAndActivatedDataSubfieldCore(SPC currentStructCore);

    boolean fieldIsAlreadyPresentInList(SKC other) {
        for (SKC h : dataFieldCoreList) {
            if (h.equals(other)) {
                return true;
            }
        }
        return false;
    }

    public <T_ extends SKC> T_ addSubfieldCore(T_ newSubfieldCore) {
        if (fieldIsAlreadyPresentInList(newSubfieldCore)) {
            throw new IllegalArgumentException();   // Can't add same subfield more than once.
        }
        dataFieldCoreList.add(newSubfieldCore);
        dataFieldList.add(newSubfieldCore.asPacket());
        return newSubfieldCore;
    }

    public <T_ extends SKC> T_ insertBefore(SKC existingHandler, T_ newHandler) {
      int existingIndex = this.dataFieldCoreList.indexOf(existingHandler);
      if (-1 == existingIndex) {
        throw new IllegalArgumentException();
      }
      this.dataFieldCoreList.add(existingIndex, newHandler);
      return newHandler;
    }

    public <T_ extends SKC> T_ insertAfter(SKC existingHandler, T_ newHandler) {
      int existingIndex = this.dataFieldCoreList.indexOf(existingHandler);
      if (-1 == existingIndex) {
        throw new IllegalArgumentException();
      }
      this.dataFieldCoreList.add(existingIndex + 1, newHandler);
      return newHandler;
    }

    public List<SK> asSubfieldList() {
      return this.roDataFieldList;
    }

}
