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

import weliyek.ketza.util.DoesNothingRunnable;

public final class BasicReadingRuntime
        implements ReadingRuntimeControl<
                        InputBytestream,
                        InputBytestreamGeneralBase<? extends InputBytestream>,
                        DeserializingRuntime<InputBytestream>>
{

  private final BasicReadingRuntimeModule<
                    InputBytestreamGeneralBase<?>,
                    InputBytestream,
                    InputBytestreamGeneralBase<InputBytestream>,
                    DeserializingRuntime<InputBytestream>> core;

  public BasicReadingRuntime(
    InputBytestreamGeneralBase<?> parentBytestream) {
    this.core = new BasicReadingRuntimeModule<
                      InputBytestreamGeneralBase<?>,
                      InputBytestream,
                      InputBytestreamGeneralBase<InputBytestream>,
                      DeserializingRuntime<InputBytestream>>(
                          this,
                          parentBytestream,
                          BasicInputBytestream::new,
                          DoesNothingRunnable.INSTANCE);
  }

  @Override
  public InputBytestream bytestream() {
    return this.core.bytestream();
  }

  @Override
  public DeserializingRuntime<InputBytestream> asRuntime() {
    return this.core.asRuntime();
  }

  @Override
  public void disableRuntime() {
    this.core.disableRuntime();
  }

  @Override
  public int readByte() throws IOException {
    return this.core.readByte();
  }

  @Override
  public long skipBytes(long num) throws IOException {
    return this.core.skipBytes(num);
  }

  @Override
  public InputBytestreamGeneralBase<InputBytestream> bytestreamCore() {
    return this.core.bytestreamCore();
  }

}
