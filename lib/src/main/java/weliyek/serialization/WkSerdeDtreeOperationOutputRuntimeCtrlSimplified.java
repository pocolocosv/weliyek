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

import java.io.IOException;

import weliyek.serialization.util.DoesNothingRunnable;

public class WkSerdeDtreeOperationOutputRuntimeCtrlSimplified
        implements WkSerdeDtreeOperationOutputRuntimeCtrl<
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>
{

  private final WkSerdeDtreeOperationOutputRuntimeCtrlModule<
                    WkSerdeDtreeBytestreamOutputBase<?>,
                    WkSerdeDtreeBytestreamOutput,
                    WkSerdeDtreeBytestreamOutputBase<WkSerdeDtreeBytestreamOutput>,
                    WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>> core;

    public WkSerdeDtreeOperationOutputRuntimeCtrlSimplified(WkSerdeDtreeBytestreamOutputBase<?> parentBytestream) {
        this.core = new WkSerdeDtreeOperationOutputRuntimeCtrlModule<>(this, parentBytestream, WkSerdeDtreeBytestreamOutputBasic::new, DoesNothingRunnable.INSTANCE);
    }

    @Override
    public WkSerdeDtreeBytestreamOutput bytestream() {
      return this.core.bytestream();
    }

    @Override
    public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> asRuntime() {
      return core.asRuntime();
    }

    @Override
    public void disableRuntime() {
      core.disableRuntime();
    }

    @Override
    public void writeByte(int b) throws IOException {
      core.writeByte(b);
    }

    @Override
    public WkSerdeDtreeBytestreamOutputBase<WkSerdeDtreeBytestreamOutput> bytestreamCore() {
      return core.bytestreamCore();
    }

}
