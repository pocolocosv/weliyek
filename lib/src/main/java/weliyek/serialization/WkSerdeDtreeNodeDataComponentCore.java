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

public abstract class WkSerdeDtreeNodeDataComponentCore<
                        T,
                        S extends WkSerdeDtreeOperationSettings,
                        D extends WkSerdeDtreeNodeStructDefinition<?>, // Not needed directly but
                        // used nonetheless to simplify the declaration of PacketFieldCore<>
                        // elsewhere where ProtocolField<D> is used.
                        PC extends WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?>,
                        O extends WkSerdeDtreeNodeDataOperation<S,?,?,? super D,? super K>,
                        OC extends WkSerdeDtreeNodeDataOperationCore<?,?,?,?,?,?,O,?,?,?,?>,
                        K extends WkSerdeDtreeNodeDataComponent<T,O,?>,
                        AB extends WkSerdeDtreeBytestreamBase<?,?>,
                        AOC extends WkSerdeDtreeNodeDataOperationCore<?,?,?,?,?,?,?,?,?,?,?>>
    implements WkSerdeDtreeNodeDataComponent<T,O,D>
{

    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(WkSerdeDtreeNodeDataComponentCore.class);

    private final PC protocolFieldCore;
    private final WkSzPacketOperationCoreList<T, O, OC> operationListManager;

    private Boolean enabled;
    private Optional<O> singleOperation;
    private String name;

    protected WkSerdeDtreeNodeDataComponentCore(
      int initialOperationListCapacity,
      PC protocolHandler,
      Function<? super O, T> operationValueExtractor) {
      this.protocolFieldCore = Objects.requireNonNull(protocolHandler);
      this.operationListManager = new WkSzPacketOperationCoreList<T, O, OC>(this, this::newOperation, initialOperationListCapacity, operationValueExtractor);
    }

    protected abstract OC newOperation(int index);

    protected abstract S newSettings(int index);

    protected abstract AB parentBytestream();

    protected abstract int computeExpectedNumberOfOperations();

    public boolean isInitialized() {
        return null != this.enabled;
    }

    @Override
    public boolean isEnabled() {
        throwIfUnitialized();
        return this.enabled.booleanValue();
    }

    @Override
    public boolean isCompleted() {      
        throwIfUnitialized();
        return this.operationListManager.isCompleted();
    }

    public void initialize(final boolean enabled) {
      this.enabled = Boolean.valueOf(enabled);
      this.name = generateName();
      int expectedNumberOfOperation = 0;
      if (enabled) {
        expectedNumberOfOperation = computeExpectedNumberOfOperations();
        if (0 > expectedNumberOfOperation) {
          throw new IllegalArgumentException(); // Cannot be negative.
        }
      }
      this.operationListManager.initialize(expectedNumberOfOperation);
      if (1 == expectedNumberOfOperation) {
        this.singleOperation = Optional.of(this.operationListManager.operationList().get(0));
      }
      onPacketFieldInitialization();
      if (enabled) {
        onPacketFieldSucccessfullyEnabled();
      }
    }

    /** Executed when the field has been activated but not necessaraly enabled. */
    protected abstract void onPacketFieldInitialization();

    /** Only executed when the packet field has been enabled and operations will be created. */
    protected abstract void onPacketFieldSucccessfullyEnabled();

    public void reset() {
      this.operationListManager.reset();
      this.enabled = null;
      this.singleOperation = Optional.empty();
    }

    protected Optional<WkSerdeDtreeNodeDataOperation<?, ?, ?, ?, ?>> processSingleStepBytestream() {
      throwIfUnitialized();
      if (isCompleted()) {
        return Optional.empty();
      }
      Optional<WkSerdeDtreeNodeDataOperation<?, ?, ?, ?, ?>> opCompleted = this.operationListManager.processingBytestream();
      if (isCompleted()) {
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

    public PC protocolFieldCore() {
      return this.protocolFieldCore;
    }

    public Optional<O> operation() {
      throwIfUnitialized();
      return this.singleOperation;
    }

    @Override
    public List<O> operationList() {
      throwIfUnitialized();
      return this.operationListManager.operationList();
    }

    public abstract K asPacket();

    @Override
    public int expectedNumberOfOperations() {
      throwIfUnitialized();
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
      return this.protocolFieldCore.asProtocolField().label();
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
        strB.append(WkSerdeDtreeNodeStructComponentCore.FIELD_NAME_SEPARATOR);
        strB.append(label());
        return strB.toString();
      }
    }

    @Override
    public String toString() {
      return name();
    }

    protected void throwIfUnitialized() {
      if ( ! isInitialized()) {
          throw new IllegalStateException();
      }
    }

}
