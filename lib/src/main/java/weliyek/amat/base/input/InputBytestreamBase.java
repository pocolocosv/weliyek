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

import java.io.InputStream;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class InputBytestreamBase<
                        AS extends InputStream,
                        S extends InputStream,
                        B extends InputBytestream>
    extends InputstreamWrapperBytestream<AS, S, B>
{

  private final InputBytestreamGeneralBase<?> parentBytestream;

  protected InputBytestreamBase(
    InputBytestreamGeneralBase<?> parentBytestream,
    Supplier<AS> sourceInputStreamSupplier,
    Function<AS, S> inputstreamFactory,
    boolean doCloseNewStream,
    long startPos) {
    super(
        sourceInputStreamSupplier, //Objects.requireNonNull(parentBytestream).iostream(),
        inputstreamFactory,
        doCloseNewStream,
        parentBytestream.getTotalPacketProcessedBytes());
    this.parentBytestream = parentBytestream;
  }

  @Override
  public final long getTotalPacketProcessedBytes() {
    return parentBytestream.getTotalPacketProcessedBytes();
  }

}
