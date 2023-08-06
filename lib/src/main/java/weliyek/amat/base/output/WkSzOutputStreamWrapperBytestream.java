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
package weliyek.amat.base.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class WkSzOutputStreamWrapperBytestream<
                        AS extends OutputStream,
                        S extends OutputStream,
                        B extends WkSzOutputBytestream>
        extends WkSzOutputBytestreamBase<B>
{

  private final S outputstream;
  private final boolean doCloseStream;

  /**
   * Builds the wrapped output stream and accepts boolean to indicate if
   * said stream is to be closed when this object is "frozen", ie closed
   * by calling its close().
   *
   * @param outputstreamSourceSupplier Original stream to be used as an argument
   *                             to new output stream.
   * @param  outputstreamFactory Used to build a new output stream.
   * @param     doCloseNewStream Indicates if built stream is to be closed
   *                             when no longer needed.
   * @param             startPos Starting position in the byte stream for
   *                             this object.
   */
  protected WkSzOutputStreamWrapperBytestream(
    Supplier<AS> outputstreamSourceSupplier,
    Function<AS, S> outputstreamFactory,
    boolean doCloseNewStream,
    long startPos) {
    super(startPos);
    this.outputstream = Objects.requireNonNull(outputstreamFactory)
                               .apply(Objects.requireNonNull(outputstreamSourceSupplier.get()));
    this.doCloseStream = doCloseNewStream;
  }

  @Override
  protected final S iostream() {
    return this.outputstream;
  }

  @Override
  final void writeByte(int b) throws IOException {
    this.iostream().write(b);
  }

  @Override
  protected final void onClose() {
    try {
      if (this.doCloseStream) {
        this.outputstream.close();
      }
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }

}
