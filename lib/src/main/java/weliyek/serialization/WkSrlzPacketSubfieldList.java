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
import java.util.ListIterator;
import java.util.Optional;

public abstract class WkSrlzPacketSubfieldList<
                        SJ extends WkSerdeDTreeNodeDataComponentHandler<?>,
                        SJC extends WkSrlzPacketSubfieldFrameNodeCore<?,?,?,?,?,? extends SJ,?,?>,
                        O extends WkSerdeDTreeAggregatorOperation<?,?,?,?,?>>
    extends AbstractList<SJC>
{

    private final List<SJC> packetCoreListContainer;
    private final List<SJ> packetBodyList;
    private final List<SJ> roPacketBodyList;
    private ListIterator<SJC> handlerIter;
    private SJC currentHandler;

    protected WkSrlzPacketSubfieldList(
      List<SJC> subfieldHandlers) {
      this.packetCoreListContainer = subfieldHandlers;
      this.packetBodyList = new ArrayList<>(subfieldHandlers.size());
      this.roPacketBodyList = Collections.unmodifiableList(packetBodyList);
      for (SJC sjc : subfieldHandlers) {
        this.packetBodyList.add(sjc.asSubfield());
      }
    }

    protected void setHandler(int i, SJC handler) {
      this.packetCoreListContainer.set(i, handler);
      this.packetBodyList.set(i, handler.asSubfield());
    }

    public boolean hasStarted() {
        return null != handlerIter;
    }

    public Optional<WkSerdeDTreeNodeDataOperation<?,?,?,?,?>> processBytestream() {
        if (isEmpty()) {
          throw new IllegalStateException();  // Must have at least one handler.
        }
        if ( ! hasStarted()) {
          // This is the first run, get first iterator.
          handlerIter = packetCoreListContainer.listIterator();
          startNextHandler();
        }
        Optional<WkSerdeDTreeNodeDataOperation<?,?,?,?,?>> completed = currentHandler.processBytestream();
        if (currentHandler.fieldCore().isCompleted()) {
            startNextHandler();
        }
        return completed;
    }

    public boolean isCompleted() {
      return hasStarted() && (! handlerIter.hasNext()) && currentHandler.field().get().isCompleted();
    }

    private void startNextHandler() {
        if ( ! handlerIter.hasNext()) {
            return;
        }
        currentHandler = handlerIter.next();
        currentHandler.activateField();
    }

    @Override
    public SJC get(int index) {
        return packetCoreListContainer.get(index);
    }

    @Override
    public int size() {
        return packetCoreListContainer.size();
    }

    boolean fieldIsAlreadyPresentInList(SJC other) {
        for (SJC h : packetCoreListContainer) {
            if (h.equals(other)) {
                return true;
            }
        }
        return false;
    }

    public <T_ extends SJC> T_ addHandler(T_ newSubfieldCore) {
        if (fieldIsAlreadyPresentInList(newSubfieldCore)) {
            throw new IllegalArgumentException();   // Can't add same subfield more than once.
        }
        packetCoreListContainer.add(newSubfieldCore);
        packetBodyList.add(newSubfieldCore.asSubfield());
        return newSubfieldCore;
    }

    public <T_ extends SJC> T_ insertBefore(SJC existingHandler, T_ newHandler) {
      int existingIndex = this.packetCoreListContainer.indexOf(existingHandler);
      if (-1 == existingIndex) {
        throw new IllegalArgumentException();
      }
      this.packetCoreListContainer.add(existingIndex, newHandler);
      return newHandler;
    }

    public <T_ extends SJC> T_ insertAfter(SJC existingHandler, T_ newHandler) {
      int existingIndex = this.packetCoreListContainer.indexOf(existingHandler);
      if (-1 == existingIndex) {
        throw new IllegalArgumentException();
      }
      this.packetCoreListContainer.add(existingIndex + 1, newHandler);
      return newHandler;
    }

    public List<SJ> asSubfieldList() {
      return this.roPacketBodyList;
    }

}
