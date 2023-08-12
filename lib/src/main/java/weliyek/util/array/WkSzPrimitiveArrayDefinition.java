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
package weliyek.util.array;

public interface WkSzPrimitiveArrayDefinition<
                        T extends WkPrimitiveArray<?, ?>,
                        XO extends WkSzPrimitiveArrayReader<T,?,?,?,?>>
        extends WkSzPrimitiveArraySegment,
                WkSzArrayDefinition<T, XO>
{

  /**
   * The array (de)serialization is performed in steps where each step process the number of
   * bytes defined by the value returned by this method or the number of bytes of the remaining
   * elements in the array, whichever is the smallest.
   *
   * @return the number of bytes to be processed in each serialization step.
   */
  //int getSerializationStepSize();

  @Override
  default int extractLengthFromSerializablesSequence(T sequence) {
    return sequence.getLength();
  }

}
