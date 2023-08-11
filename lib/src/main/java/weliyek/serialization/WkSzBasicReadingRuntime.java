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

public final class WkSzBasicReadingRuntime
        implements WkSzReadingRuntimeControl<
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntime<WkSzInputBytestream>>
{

  private final WkSzBasicReadingRuntimeModule<
                    WkSzInputBytestreamBase<?>,
                    WkSzInputBytestream,
                    WkSzInputBytestreamBase<WkSzInputBytestream>,
                    WkSzReadingRuntime<WkSzInputBytestream>> core;

  public WkSzBasicReadingRuntime(
    WkSzInputBytestreamBase<?> parentBytestream) {
    this.core = new WkSzBasicReadingRuntimeModule<
                      WkSzInputBytestreamBase<?>,
                      WkSzInputBytestream,
                      WkSzInputBytestreamBase<WkSzInputBytestream>,
                      WkSzReadingRuntime<WkSzInputBytestream>>(
                          this,
                          parentBytestream,
                          WkSzBasicInputBytestream::new,
                          DoesNothingRunnable.INSTANCE);
  }

  @Override
  public WkSzInputBytestream bytestream() {
    return this.core.bytestream();
  }

  @Override
  public WkSzReadingRuntime<WkSzInputBytestream> asRuntime() {
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
  public WkSzInputBytestreamBase<WkSzInputBytestream> bytestreamCore() {
    return this.core.bytestreamCore();
  }

}
