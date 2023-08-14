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
import java.util.function.Function;

public abstract class WkRuntimeSrlzPacketOperationCtrlModuleBase<
                        AB extends WkSzBytestream,
                        B extends WkSzBytestream,
                        BC extends WkSzBytestreamBase<?,B>,
                        Q extends WkCommonRuntimeSrlzPacketOperationData<B>>
        implements WkRuntimeSrlzPacketOperationCtrl<B, BC, Q>
{

    private final Q body;
    private final BC bytestreamCore;
    private final Runnable onReset;

    private boolean disabled = false;

    protected WkRuntimeSrlzPacketOperationCtrlModuleBase(
      Q runtimeBody,
      AB parentBytestream,
      Function<AB, BC> bytestreamFactory,
      Runnable onReset) {
        this.body = Objects.requireNonNull(runtimeBody);
        this.bytestreamCore = Objects.requireNonNull(bytestreamFactory).apply(parentBytestream);
        this.onReset = Objects.requireNonNull(onReset);
    }

    @Override
    public B bytestream() {
      return this.bytestreamCore.body();
    }

    @Override
    public BC bytestreamCore() {
      return this.bytestreamCore;
    }

    @Override
    public final Q asRuntime() {
      return this.body;
    }

    @Override
    public void disableRuntime() {
        this.onReset.run();
        this.bytestreamCore.close();
        this.disabled = true;
    }

    protected abstract WkSzPacketDirection getOperationType();

    @Override
    public String toString() {
      if (this.disabled) {
        return '{' + getOperationType().name() + " done}";
      } else {
        StringBuilder strB = new StringBuilder();
        strB.append("{");
        strB.append(getOperationType().name());
        strB.append("}");
        return strB.toString();
      }
    }

}
