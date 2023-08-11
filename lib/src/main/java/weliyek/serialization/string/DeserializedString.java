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
package weliyek.serialization.string;

import weliyek.util.array.PrimitiveArrayWrapper;

/**
 * Holds the string obtained when deserializing data from a primitive array
 * wrapper. Also holds other data elements obtained during deserialization.
 *
 * @author Ricardo Villalobos Guevara
 *
 * @param <SX> Primitive array wrapper type.
 */
public class DeserializedString<SX extends PrimitiveArrayWrapper<?,?>>
    extends StringAndPrimitiveArray<SX>
{

  private final int paddedLen;

  public DeserializedString(String string, SX primitiveArray, int paddedLen) {
    super(string, primitiveArray);
    this.paddedLen = paddedLen;
  }

  public int paddedLength() {
    return this.paddedLen;
  }

  public int nonPaddedLength() {
    return primitiveArray().getLength() - paddedLength();
  }

}
