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
package weliyek.ketza.util.array;

public class WKArrayUtil {

  public static void throwIfBoundsAreInvalid(
      int capacity, int from, int to) {
    if (from > to) {
      throw new IllegalArgumentException("Subrange from=" + from + " > to=" + to);
    }
    if ((from < 0) || (from > capacity)) {
      throw new IndexOutOfBoundsException("Subrange from=" + from + " capacity=" + capacity);
    }
  }

  public static boolean areBoundsInvalid(
      int capacity, int from, int to) {
    if (capacity < 0 | from < 0 | to < 0) {
      return true;
    }
    if (from > to) {
      return true;
    }
    if (from > capacity) {
      return true;
    }
    return false;
  }

  public static int findFirstDivergingByte(byte[] first, int firstFrom, byte[] second, int secondFrom, int commonLen) {
    throwIfBoundsAreInvalid(first.length, firstFrom, firstFrom + commonLen);
    throwIfBoundsAreInvalid(second.length, secondFrom, secondFrom + commonLen);
    int i = 0;
    for (; i < commonLen; i++) {
      if (first[i + firstFrom] != second[i + secondFrom]) {
        break;
      }
    }
    return i;
  }

}
