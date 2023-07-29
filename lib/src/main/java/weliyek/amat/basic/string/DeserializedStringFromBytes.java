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
package weliyek.amat.basic.string;

import java.nio.charset.Charset;
import java.util.Objects;

import weliyek.ketza.util.array.ByteArrayWrapper;

public class DeserializedStringFromBytes
    extends DeserializedString<ByteArrayWrapper>
{

  private final Charset charset;

  public DeserializedStringFromBytes(
    String string,
    ByteArrayWrapper primitiveArray,
    int paddedLen,
    Charset charset) {
    super(string, primitiveArray, paddedLen);
    this.charset = Objects.requireNonNull(charset);
  }

  public Charset charset() {
    return this.charset;
  }

}
