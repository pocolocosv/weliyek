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
import java.util.function.Function;

public final class WkSerdeDtreeOperationOutputRuntimeCtrlModule<
                        AB extends WkSerdeDtreeBytestreamOutput,
                        B extends WkSerdeDtreeBytestreamOutput,
                        BC extends WkSerdeDtreeBytestreamOutputBase<B>,
                        Q extends WkSerdeDtreeOperationOutputRuntime<B>>
    extends WkSerdeDtreeOperationRuntimeCommonCtrlModule<AB, B, BC, Q>
    implements WkSerdeDtreeOperationOutputRuntimeCtrl<B, BC, Q>
{

  public WkSerdeDtreeOperationOutputRuntimeCtrlModule(
    Q body,
    AB parentBytestream,
    Function<AB, BC> bytestreamFactory,
    Runnable onReset) {
    super(body, parentBytestream, bytestreamFactory, onReset);
  }

  @Override
  protected final WkSzPacketDirection getOperationType() {
    return WkSzPacketDirection.WRITE;
  }

  @Override
  public void writeByte(int b) throws IOException {
    this.bytestreamCore().writeByte(b);
  }

}
