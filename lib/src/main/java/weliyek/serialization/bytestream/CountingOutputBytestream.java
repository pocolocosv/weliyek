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
package weliyek.serialization.bytestream;

import java.io.OutputStream;

import org.apache.commons.io.output.CountingOutputStream;

public class CountingOutputBytestream
        extends OutputStreamWrapperBytestream<
                        OutputStream,
                        CountingOutputStream,
                        OutputBytestream>
{

  public CountingOutputBytestream(OutputStream output) {
    super(
        () -> output,
        CountingOutputStream::new,
        false, // Normally we should close the counting stream but it will
               // simply call the wrapped output stream which was supplied
               // and hence not in our responsability to close.
        0);
  }

  @Override
  public long getTotalPacketProcessedBytes() {
    return this.iostream().getByteCount();
  }

  @Override
  public OutputBytestream body() {
    return this;
  }

}
