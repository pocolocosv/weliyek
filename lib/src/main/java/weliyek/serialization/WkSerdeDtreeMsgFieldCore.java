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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WkSerdeDtreeMsgFieldCore<
                        T,
                        S extends WkSerdeDtreeOperationSettings,
                        D extends WkSerdeDtreeStructDefinition<T>,
                        O extends WkSerdeDtreeMsgOperation<S,?,?,? super D>,
                        OC extends WkSerdeDtreeMsgOperationCore<?,?,?,?,?,?,O,?,?>,
                        K extends WkSerdeDtreeMsgField<T,O,?>,
                        ABC extends WkSerdeDtreeBytestreamBase<?,?>,
                        AOC extends WkSerdeDtreeMsgOperationCore<?,?,?,?,?,?,?,?,?>,
                        PC extends WkSerdeDtreeStructFieldCore<T,?,?,?,?,?,?,? extends D>>
    implements WkSerdeDtreeMsgField<T,O,D>
{

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WkSerdeDtreeMsgFieldCore.class);

    private final PC structFieldCore;
    private final WkSerdeDtreeMsgOperationCoreList<T, O, OC> operationListManager;
    private final boolean enabled;
    private Optional<O> singleOperation = Optional.empty();
    private String name;
    private int expectedNumberOfOperation = -1;

    protected WkSerdeDtreeMsgFieldCore(
      PC structFieldCore,
      Function<? super O, T> operationValueExtractor,
      boolean enabled) {
      this.enabled = enabled;
      this.structFieldCore = Objects.requireNonNull(structFieldCore);
      this.operationListManager = new WkSerdeDtreeMsgOperationCoreList<T, O, OC>(this, this::newOperation, operationValueExtractor);
    }

    protected abstract OC newOperation(int index);

    protected abstract S newSettings(int index);

    protected abstract ABC parentBytestream();

    protected abstract int computeExpectedNumberOfOperations();

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean isCompleted() {
        return !isEnabled() || this.operationListManager.isCompleted();
    }

    /*
    public void activateField() {
      boolean toBeEnabled = true;
      if (isOptional()) {
        toBeEnabled = testIfOptionalFieldIsToBeEnabled();
      }
      initialize(toBeEnabled);
    }
    */

    void initialize() {
      this.name = generateName();
      if (enabled) {
        this.expectedNumberOfOperation = computeExpectedNumberOfOperations();
        if (0 > this.expectedNumberOfOperation) {
          throw new IllegalArgumentException(); // Cannot be negative.
        }
        this.operationListManager.initialize(this.expectedNumberOfOperation);
        if (1 == this.expectedNumberOfOperation) {
          this.singleOperation = Optional.of(this.operationListManager.operationList().get(0));
        }
      }
      onPacketFieldInitialization();
      if (enabled) {
        onPacketFieldSucccessfullyEnabled();
      }
    }

    protected abstract boolean isOptional();

    protected abstract boolean testIfOptionalFieldIsToBeEnabled();

    /** Executed when the field has been activated but not necessaraly enabled. */
    protected abstract void onPacketFieldInitialization();

    /** Only executed when the packet field has been enabled and operations will be created. */
    protected abstract void onPacketFieldSucccessfullyEnabled();

    protected Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> processSingleStepBytestream() {
      if(isCompleted()) {
        return Optional.empty();
      }
      Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> opCompleted = this.operationListManager.processingBytestream();
      if(isCompleted()) {
        assertNumberOfOperations();
        onDoneProcessing();
      }
      return opCompleted;
    }

    private void assertNumberOfOperations() {
      if (operationList().size() != expectedNumberOfOperations()) {
        throw new WkSerdeDtreeNodeDataComponentException(this, "Packet field did not process expected number of operations");
      }
    }

    protected abstract void onDoneProcessing();

    public PC structFieldCore() {
      return this.structFieldCore;
    }

    public Optional<O> operation() {
      return this.singleOperation;
    }

    @Override
    public List<O> operationList() {
      return this.operationListManager.operationList();
    }

    public abstract K asPacket();

    @Override
    public int expectedNumberOfOperations() {
      return this.operationListManager.expectedSize();
    }

    @Override
    public List<T> collectAllOperationValues() {
      if (operationList().isEmpty()) {
        return Collections.emptyList();
      }
      return this.operationListManager.collectAllOperationsValues();
    }

    protected abstract AOC parentOperationCore();

    public String label() {
      return this.structFieldCore.asProtocolField().label();
    }

    @Override
    public String name() {
      return this.name;
    }

    private String generateName() {
      StringBuilder strB = new StringBuilder();
      if (null == parentOperationCore()) {
        strB.append(asPacket().direction().name());
        strB.append('_');
        strB.append(asPacket().type().name());
        strB.append('(');
        strB.append(hashCode());
        strB.append(')');
        strB.append(':');
        strB.append(label());
        return  strB.toString();
      } else {
        strB.append(parentOperationCore().name());
        strB.append(WkSerdeDtreeStructFieldCore.FIELD_NAME_SEPARATOR);
        strB.append(label());
        return strB.toString();
      }
    }

    @Override
    public String toString() {
      return name();
    }

}
