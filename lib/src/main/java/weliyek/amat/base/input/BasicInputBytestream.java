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

public final class BasicInputBytestream
    extends InputBytestreamGeneralBase<InputBytestream>
{

    private final InputBytestreamGeneralBase<?> parent;

    public BasicInputBytestream(InputBytestreamGeneralBase<?> parentInputBytestream) {
      super(parentInputBytestream.getTotalPacketProcessedBytes());
      this.parent = Objects.requireNonNull(parentInputBytestream);
    }

    @Override
    public long getTotalPacketProcessedBytes() {
      return this.parent.getTotalPacketProcessedBytes();
    }

    @Override
    int readByte() throws IOException {
      return this.parent.readByte();
    }

    @Override
    long skipBytes(long num) throws IOException {
      return this.parent.skipBytes(num);
    }

    @Override
    protected InputStream iostream() {
      return this.parent.iostream();
    }

    @Override
    protected void onClose() {
      // Nothing to do.
    }

    @Override
    public InputBytestream body() {
      return this;
    }

}
