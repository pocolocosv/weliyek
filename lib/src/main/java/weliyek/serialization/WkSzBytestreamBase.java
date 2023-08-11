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

import java.io.InputStream;

/**
 * Base implementation shared by the input and output {@link WkSzBytestream} types.
 * 
 * @param <S> {@link InputStream} or {@link OutputStream}.
 * @param <B>
 */
public abstract class WkSzBytestreamBase<
                        S,
                        B extends WkSzBytestream>
        implements WkSzBytestream
{

  private final long startIndex;
  private long endIndex = -1;

  protected WkSzBytestreamBase(final long startPos) {
    this.startIndex = startPos;
  }

  protected abstract S iostream();

  public abstract B body();

  protected final void close() {
    if (isClosed()) {
      throw new IllegalStateException();
    }
    onClose();
    this.endIndex = getTotalPacketProcessedBytes() - getStartIndexInGlobalBytestream();
  }

  protected abstract void onClose();

  @Override
public boolean isClosed() {
    return -1 != this.endIndex;
  }

  @Override
  public long getStartIndexInGlobalBytestream() {
    return this.startIndex;
  }

  @Override
  public long getFieldProcessedBytes() {
    if (isClosed())
      return this.endIndex;
    else
      return getTotalPacketProcessedBytes() - getStartIndexInGlobalBytestream();
  }

}
