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

public final class WkSzBasicWritingRuntimeModule<
                        AB extends WkSzOutputBytestream,
                        B extends WkSzOutputBytestream,
                        BC extends WkSzOutputBytestreamBase<B>,
                        Q extends WkSzWritingRuntime<B>>
    extends WkSzCommonOperationRuntimeModuleBase<AB, B, BC, Q>
    implements WkSzWritingRuntimeControl<B, BC, Q>
{

  public WkSzBasicWritingRuntimeModule(
    Q body,
    AB parentBytestream,
    Function<AB, BC> bytestreamFactory,
    Runnable onReset) {
    super(body, parentBytestream, bytestreamFactory, onReset);
  }

  @Override
  protected final String getOperationType() {
    return "WRITING";
  }

  @Override
  public void writeByte(int b) throws IOException {
    this.bytestreamCore().writeByte(b);
  }

}
