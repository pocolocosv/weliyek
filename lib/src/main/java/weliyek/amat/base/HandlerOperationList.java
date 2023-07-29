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
package weliyek.amat.base;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.ketza.base.Completable;
import weliyek.ketza.base.Initializable;
import weliyek.ketza.base.Resetable;

public class HandlerOperationList<
                        T,
                        O extends OperationSegment<?, ?, ?, ?, ?>,
                        OC extends PacketOperationSegmentCore<?,?,?,?,?,?,O,?,?,?,?>>
        extends AbstractList<OC>
        implements Initializable, Completable, Resetable
{

    private static final Logger logger = LoggerFactory.getLogger(HandlerOperationList.class);

    private static final int UNINITIALIZED_LIST = -1;

    private final FieldCore<?,?,?,?,?,?,?,?,?> ownerField;
    private final IntFunction<OC> operationFactory;
    private final Function<? super O, T> valueExtractor;

    private List<OC> coreContainer;
    private List<O> operationList;
    private int expectedSize;
    private OC currentOpCore;

    public HandlerOperationList(
      FieldCore<?,?,?,?,?,?,?,?,?> ownerField,
      IntFunction<OC> operationFactory,
      int initialCapacity,
      Function<? super O, T> valueExtractor) {
        this.ownerField = ownerField;
        this.operationFactory = Objects.requireNonNull(operationFactory);
        this.valueExtractor = Objects.requireNonNull(valueExtractor);
        reset();
    }

    @Override
    public boolean isInitialized() {
        return UNINITIALIZED_LIST < this.expectedSize;
    }

    @Override
    public boolean isCompleted() {
        if (isUnitialized()) {
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

    @Override
    public void reset() {
      this.expectedSize = UNINITIALIZED_LIST;
      this.currentOpCore = null;
      resetLists();
    }

    public int expectedSize() {
      if (isUnitialized()) {
        throw new IllegalStateException();
      }
      return this.expectedSize;
    }

    @Override
    public OC get(int index) {
      if (isUnitialized()) {
        throw new IllegalStateException();
      }
      return this.coreContainer.get(index);
    }

    @Override
    public int size() {
      if (isUnitialized()) {
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

    public Optional<OperationSegment<?,?,?,?,?>> processingBytestream() {
        if (isUnitialized()) {
          throw new IllegalStateException();
        }
        if (isCompleted()) {
          // Two possibilities here, either the last operation is done and we should not
          // retry processing the bytestream, or the expected size is 0 and no operation
          // core is available.
          return Optional.empty();
        }
        Optional<OperationSegment<?,?,?,?,?>> lastOp = this.currentOpCore.processBytestream();
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
        if (isUnitialized()) {
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
