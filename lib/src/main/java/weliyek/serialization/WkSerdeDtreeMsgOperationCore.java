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

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WkSerdeDtreeMsgOperationCore<
                        S extends WkSerdeDtreeOperationSettings,
                        Q extends WkSerdeDtreeOperationRuntimeCommon<?>,
                        QC extends WkSerdeDtreeOperationRuntimeCommonCtrl<?,?,Q>,
                        R extends WkSerdeDtreeOperationResult<?>,
                        D extends WkSerdeDtreeStructDefinition<?>,
                        DC extends WkSerdeDtreeStructDefinitionCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? extends D,?>,
                        O extends WkSerdeDtreeMsgOperation<S,?,R,D>,
                        OC extends WkSerdeDtreeMsgOperationCore<S,Q,QC,R,D,DC,O,?,KC>,
                        KC extends WkSerdeDtreeMsgFieldCore<?,?,?,?,?,?,?,?,?>>
        implements WkSerdeDtreeMsgOperation<S,Q,R,D>
{

    private static final Logger logger = LoggerFactory.getLogger(WkSerdeDtreeMsgOperationCore.class);

    private final KC msgFieldCore;
    private final DC definitionCore;
    private final S settings;
    private final int index;
    private final O operationBody;

    private boolean uninitialized = true;
    private Optional<R> result = Optional.empty();

    protected WkSerdeDtreeMsgOperationCore(
            int index,
            S settings,
            KC msgFieldCore,
            DC definitionCore,
            O operationBody) {
        this.msgFieldCore = Objects.requireNonNull(msgFieldCore);
        this.definitionCore = Objects.requireNonNull(definitionCore);
        this.settings = Objects.requireNonNull(settings);
        if (index < 0) {
          throw new IllegalArgumentException();
        }
        this.index = index;
        this.operationBody = Objects.requireNonNull(operationBody);
    }

    public final O body() {
      return this.operationBody;
    }

    protected abstract OC getThis();

    protected abstract void onStart();

    protected Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> processBytestream() {
      if (isCompleted()) {
        // TODO Warn or raise an exception?
        return Optional.empty();
      }
      if (uninitialized) {
        if (logger.isDebugEnabled()) {
          logger.debug(this + " op starting");
        }
        onStart();
        this.uninitialized = false;
      }
      return onProcessingBytestream();
    }

    protected abstract Optional<WkSerdeDtreeMsgOperation<?,?,?,?>> onProcessingBytestream();

    public abstract long expectedBytes();

    public boolean isInitialized() {
      return ! this.uninitialized;
    }

    public final boolean isCompleted() {
        return result().isPresent();
    }

    public final boolean isInProgress() {
      return ! isCompleted();
    }

    public final void completeOperation() {
      onBeforeOperationCompletion();
      this.result = Optional.of(onCompletion());
      getRuntimeControl().disableRuntime();
      onAfterOperationCompletion();
    }

    protected abstract void onBeforeOperationCompletion();

    protected abstract R onCompletion();

    protected abstract void onAfterOperationCompletion();

    public abstract QC getRuntimeControl();

    @Override
    public S settings() {
      return this.settings;
    }

    @Override
    public final Optional<R> result() {
        return this.result;
    }

    @Override
    public final int index() {
      return this.index;
    }

    public final KC packetFieldCore() {
      return this.msgFieldCore;
    }

    @Override
    public WkSerdeDtreeMsgField<?, ?, ?> parentField() {
      return this.msgFieldCore.asPacket();
    }

    @Override
    public Q dashboard() {
      return getRuntimeControl().asRuntime();
    }

    protected abstract String label();
    /*
    {
      return packetFieldCore().protocolFieldCore().definitionCore().label();
    }
    */

    @Override
    public String name() {
      return packetFieldCore().name() + label() + '[' + index() + ']';
    }

    @Override
    public final D definition() {
      return definitionCore().definition();
    }

    public final DC definitionCore() {
      return this.definitionCore;
    }

    @Override
    public String toString() {
      StringBuilder strB = new StringBuilder(name());
      strB.append(':');
      if ( ! isInitialized()) {
        strB.append("UNINITIALIZED");
      } else {
        if (isInProgress()) {
          strB.append("IN_PROGRESS");
        } else {
          strB.append("COMPLETED");
        }
      }
      return strB.toString();
    }

}
