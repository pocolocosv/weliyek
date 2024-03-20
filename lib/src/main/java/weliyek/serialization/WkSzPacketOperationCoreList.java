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
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WkSzPacketOperationCoreList<
                        T,
                        O extends WkSerdeDTreeNodeDataOperation<?, ?, ?, ?, ?>,
                        OC extends WkSrlzPacketOperationFrameNodeCore<?,?,?,?,?,?,O,?,?,?,?>>
        extends AbstractList<OC>
{

    private static final Logger logger = LoggerFactory.getLogger(WkSzPacketOperationCoreList.class);

    private static final int UNINITIALIZED_LIST = -1;

    private final WkSrlzPacketFieldFrameNodeCore<?,?,?,?,?,?,?,?,?> ownerField;
    private final IntFunction<OC> operationFactory;
    private final Function<? super O, T> valueExtractor;

    private List<OC> coreContainer;
    private List<O> operationList;
    private int expectedSize;
    private OC currentOpCore;

    public WkSzPacketOperationCoreList(
      WkSrlzPacketFieldFrameNodeCore<?,?,?,?,?,?,?,?,?> ownerField,
      IntFunction<OC> operationFactory,
      int initialCapacity,
      Function<? super O, T> valueExtractor) {
        this.ownerField = ownerField;
        this.operationFactory = Objects.requireNonNull(operationFactory);
        this.valueExtractor = Objects.requireNonNull(valueExtractor);
        reset();
    }

    public boolean isInitialized() {
        return UNINITIALIZED_LIST < this.expectedSize;
    }

    public boolean isCompleted() {
        if ( ! isInitialized()) {
            return false;
        }
        if (0 == this.expectedSize) {
          return true;
        }
        if (this.coreContainer.size() < this.expectedSize) {
          return false;
        }
        return this.currentOpCore.isCompleted();
    }

    public void initialize(int expectedSize) {
        this.expectedSize = expectedSize;
        initializeLists(expectedSize);
        if (logger.isDebugEnabled()) {
          logger.debug(ownerField.name() + " expected operations: " + expectedSize);
        }
        if (0 < expectedSize) {
          startIteratorNextOpCore(0);
        }
    }

    public void reset() {
      this.expectedSize = UNINITIALIZED_LIST;
      this.currentOpCore = null;
      resetLists();
    }

    public int expectedSize() {
      if ( ! isInitialized()) {
        throw new IllegalStateException();
      }
      return this.expectedSize;
    }

    @Override
    public OC get(int index) {
      if ( ! isInitialized()) {
        throw new IllegalStateException();
      }
      return this.coreContainer.get(index);
    }

    @Override
    public int size() {
      if ( ! isInitialized()) {
        throw new IllegalStateException();
      }
      return coreContainer.size();
    }

    private void startIteratorNextOpCore(int index) {
      if (logger.isDebugEnabled()) {
        logger.debug(ownerField.name() + " initializing op " + index);
      }
      this.currentOpCore = this.operationFactory.apply(index);
      this.coreContainer.add(this.currentOpCore);
      this.operationList.add(this.currentOpCore.body());
    }

    public Optional<WkSerdeDTreeNodeDataOperation<?,?,?,?,?>> processingBytestream() {
        if ( ! isInitialized()) {
          throw new IllegalStateException();
        }
        if (isCompleted()) {
          // Two possibilities here, either the last operation is done and we should not
          // retry processing the bytestream, or the expected size is 0 and no operation
          // core is available.
          return Optional.empty();
        }
        Optional<WkSerdeDTreeNodeDataOperation<?,?,?,?,?>> lastOp = this.currentOpCore.processBytestream();
        if (this.currentOpCore.isCompleted()) {
          if (this.coreContainer.size() < this.expectedSize) {
            // Still operation left to perform.
            startIteratorNextOpCore(this.coreContainer.size());
          }
        }
        return lastOp;
    }

    public List<O> operationList() {
        return this.operationList;
    }

    public List<T> collectAllOperationsValues() {
        if ( ! isInitialized()) {
          throw new IllegalStateException();
        }
        ArrayList<T> vals = operationList.stream()
                                            .map(this.valueExtractor)
                                            .collect(
                                                    ArrayList::new,
                                                    ArrayList::add,
                                                    ArrayList::addAll);
        return vals;
    }

    private void initializeLists(int initialCapacity) {
      this.coreContainer = new ArrayList<>(initialCapacity);
      this.operationList = new ArrayList<>(initialCapacity);
    }

    private void resetLists() {
      this.coreContainer = null;
      this.operationList = null;
    }

}
