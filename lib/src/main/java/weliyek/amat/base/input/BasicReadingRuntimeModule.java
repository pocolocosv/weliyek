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
package weliyek.amat.base.input;

import java.io.IOException;
import java.util.function.Function;

import weliyek.amat.base.CommonOperationRuntimeModuleBase;
import weliyek.serialization.bytestream.InputBytestream;
import weliyek.serialization.bytestream.InputBytestreamGeneralBase;

public final class BasicReadingRuntimeModule<
                        AB extends InputBytestream,
                        B extends InputBytestream,
                        BC extends InputBytestreamGeneralBase<B>,
                        Q extends DeserializingRuntime<B>>
    extends CommonOperationRuntimeModuleBase<AB, B, BC, Q>
    implements ReadingRuntimeControl<B, BC, Q>
{

  public BasicReadingRuntimeModule(
    Q body,
    AB parentBytestream,
    Function<AB, BC> bytestreamFactory,
    Runnable onReset) {
    super(body, parentBytestream, bytestreamFactory, onReset);
  }

  @Override
  protected final String getOperationType() {
    return "READING";
  }

  @Override
  public int readByte() throws IOException {
    return this.bytestreamCore().readByte();
  }

  @Override
  public long skipBytes(long num) throws IOException {
    return this.bytestreamCore().skipBytes(num);
  }

}
