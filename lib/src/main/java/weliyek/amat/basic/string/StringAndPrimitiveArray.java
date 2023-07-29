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

import java.util.Objects;

import weliyek.ketza.util.array.PrimitiveArrayWrapper;

public class StringAndPrimitiveArray<T extends PrimitiveArrayWrapper<?,?>>
{

  private final String string;
  private final T primitiveArray;

  protected StringAndPrimitiveArray(String str, T primitiveArray) {
    this.string = Objects.requireNonNull(str);
    this.primitiveArray = Objects.requireNonNull(primitiveArray);
  }

  public String string() {
    return this.string;
  }

  public T primitiveArray() {
    return this.primitiveArray;
  }

}
