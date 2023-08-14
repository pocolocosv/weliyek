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

public abstract class WkSrlzEngine<
                        QC extends WkSzOperationRuntimeControl<?,?,?>,
                        O extends WkSrlzPacketOperationFrameNode<?,?,?,?,?>>
{

  private final QC runtimeControl;
  private final O operation;

  protected WkSrlzEngine(
      QC runtimeControl,
      O operation) {
    this.runtimeControl = Objects.requireNonNull(runtimeControl);
    this.operation = Objects.requireNonNull(operation);
  }

  public abstract int primitiveByteLength();

  public final void start() {
    onStart();
  }

  protected abstract long requestedBytesToIO();

  protected abstract void onStart();

  public final void processBytestream() {
    if ( ! this.hasStarted()) {
      throw new IllegalStateException();
    }
    if ( ! this.isDone()) {
      onBytesLeftToProcess();
      if (isDone()) {
        onDone();
      }
    }
  }

  protected abstract void onDone();

  protected abstract void onBytesLeftToProcess();

  public final boolean hasStarted() {
    return true;
  }

  protected O operation() {
    return this.operation;
  }

  public final boolean isDone() {
    return requestedBytesToIO() == getRuntimeControl().bytestream().getFieldProcessedBytes();
  }

  protected final QC getRuntimeControl() {
    return this.runtimeControl;
  }

}
