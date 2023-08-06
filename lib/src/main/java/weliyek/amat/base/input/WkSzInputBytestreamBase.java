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

import weliyek.amat.base.WkSzBytestreamBase;

public abstract class WkSzInputBytestreamBase<B extends WkSzInputBytestream>
    extends WkSzBytestreamBase<InputStream, B>
    implements WkSzInputBytestream
{

  protected WkSzInputBytestreamBase(long startPos) {
    super(startPos);
  }

  abstract int readByte() throws IOException;

  abstract long skipBytes(long num) throws IOException;

  @Override
  protected abstract InputStream iostream();

}