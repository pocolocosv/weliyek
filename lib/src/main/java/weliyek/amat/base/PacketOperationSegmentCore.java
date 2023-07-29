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

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.ketza.base.Completable;
import weliyek.ketza.base.Initializable;

public abstract class PacketOperationSegmentCore<
                        S extends OperationSettings,
                        Q extends CommonOperationRuntime<?>,
                        QC extends CommonOperationRuntimeControl<?,?,Q>,
                        R extends OperationResult,
                        _D extends DefinitionSegment<?,?>,
                        DC extends DefinitionSegmentCore<?,?,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        O extends OperationSegment<S,?,R,_D,?>,
                        OC extends PacketOperationSegmentCore<S,Q,QC,R,_D,DC,O,?,AB,K,KC>,
                        AB extends BytestreamCore<?,?>,
                        K extends FieldSegment<?,?,?>,
                        KC extends FieldCore<?,?,?,?,?,?,? extends K,?,?>>
        implements Completable,
                   Initializable,
                   OperationSegment<S,Q,R,_D,K>
{

    private static final Logger logger = LoggerFactory.getLogger(PacketOperationSegmentCore.class);

    private final KC packetFieldCore;
    private final S settings;
    private final int index;
    private final O operationBody;
    /**
     * Gives direct access to it's definition. This could be obtained through
     * packetfieldCore but it would require defining maybe several generic parameters
     * across diferent classes such as packet cores and protocol cores. This single
     * class field helps eliminate some generic parameters.
     */
    private final DC definitionCore;

    private boolean uninitialized = true;
    private Optional<R> result = Optional.empty();


    protected PacketOperationSegmentCore(
            int index,
            S settings,
            AB parentBytestream,
            KC packetCore,
            DC definitionCore,
            O operationBody) {
        this.settings = Objects.requireNonNull(settings);
        this.packetFieldCore = Objects.requireNonNull(packetCore);
        this.definitionCore = Objects.requireNonNull(definitionCore);
        if (index < 0)
          throw new IllegalArgumentException();
        this.index = index;
        this.operationBody = Objects.requireNonNull(operationBody);
    }

    public final O body() {
      return this.operationBody;
    }

    protected abstract OC getThis();

    protected abstract void onStart();

    protected Optional<OperationSegment<?,?,?,?,?>> processBytestream() {
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

    protected abstract Optional<OperationSegment<?,?,?,?,?>> onProcessingBytestream();

    public abstract long expectedBytes();

    @Override
    public boolean isInitialized() {
      return ! this.uninitialized;
    }

    @Override
    public final boolean isCompleted() {
        return result().isPresent();
    }

    @Override
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

    public final K packet() {
      return packetFieldCore().asPacket();
    }

    public final KC packetFieldCore() {
      return this.packetFieldCore;
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

    @SuppressWarnings("unchecked")
    @Override
    public final _D definition() {
      return (_D) definitionCore().definition();
    }

    public final DC definitionCore() {
      return this.definitionCore;
    }

    @Override
    public String toString() {
      StringBuilder strB = new StringBuilder(name());
      strB.append(':');
      if (isUnitialized()) {
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

    @Override
    public final K packetField() {
      return packetFieldCore().asPacket();
    }

}
