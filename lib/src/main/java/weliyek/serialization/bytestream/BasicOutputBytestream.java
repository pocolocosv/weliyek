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

import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public final class BasicOutputBytestream
    extends OutputBytestreamGeneralBase<OutputBytestream>
{

    private final OutputBytestreamGeneralBase<?> parent;

    public BasicOutputBytestream(OutputBytestreamGeneralBase<?> parentOutputBytestream) {
      super(Objects.requireNonNull(parentOutputBytestream).getTotalPacketProcessedBytes());
      this.parent = parentOutputBytestream;
    }

    @Override
    void writeByte(int b) throws IOException {
      this.parent.writeByte(b);
    }

    @Override
    public long getTotalPacketProcessedBytes() {
        return parent.getTotalPacketProcessedBytes();
    }

    @Override
    protected void onClose() {
      // Nothing to do.
    }

    @Override
    protected OutputStream iostream() {
      return this.parent.iostream();
    }

    @Override
    public OutputBytestream body() {
      return this;
    }

}
