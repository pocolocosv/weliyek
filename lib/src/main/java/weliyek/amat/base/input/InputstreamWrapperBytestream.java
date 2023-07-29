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
import java.io.InputStream;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class InputstreamWrapperBytestream<
                        AS extends InputStream,
                        S extends InputStream,
                        B extends InputBytestream>
        extends InputBytestreamGeneralBase<B>
{

  private final S inputstream;
  private final boolean doCloseStream;

  protected InputstreamWrapperBytestream(
    Supplier<AS> sourceInputstream,
    Function<AS, S> inputstreamFactory,
    boolean doCloseNewStream,
    long startPos) {
    super(startPos);
    this.inputstream = Objects.requireNonNull(inputstreamFactory)
                              .apply(Objects.requireNonNull(sourceInputstream.get()));
    this.doCloseStream = doCloseNewStream;
  }

  @Override
  protected final S iostream() {
    return this.inputstream;
  }

  @Override
  final int readByte() throws IOException {
    return this.inputstream.read();
  }

  @Override
  long skipBytes(long num) throws IOException {
    return this.inputstream.skip(num);
  }

  @Override
  protected final void onClose() {
    try {
      if (this.doCloseStream) {
        this.inputstream.close();
      }
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

}
