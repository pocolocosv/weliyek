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

import java.util.Arrays;
import java.util.function.ToIntFunction;

public class WkIntArray
    extends WkPrimitiveArrayBase<int[], WkIntArray>
{

  private static final AsByteGetter<int[]> byteGetter = (a, i) -> (byte)a[i];
  private static final AsShortGetter<int[]> shortGetter = (a, i) -> (short)a[i];
  private static final AsIntGetter<int[]> intGetter = (a, i) -> a[i];
  private static final AsLongGetter<int[]> longGetter = (a, i) -> (long)a[i];
  private static final AsFloatGetter<int[]> floatGetter = (a, i) -> (float)a[i];
  private static final AsDoubleGetter<int[]> doubleGetter = (a, i) -> (double)a[i];
  private static final PrimitiveArrayComparator<int[]> arrayComparator = Arrays::compare;
  private static final SubrangeCopier<int[]> subrangeCopier = Arrays::copyOfRange;
  private static final ToIntFunction<int[]> lengthGetter = (a) -> a.length;
  private static final WrapperSupplier<int[], WkIntArray> wrapperSupplier = WkIntArray::new;
  private static final SubrangeEqualityComparator<int[]> equalityComparator = Arrays::equals;

  WkIntArray(int[] array, int offset, int to) {
    super(array, offset, to, lengthGetter);
  }

  @Override
  protected final AsByteGetter<int[]> getByteGetter() {
    return byteGetter;
  }

  @Override
  protected final AsShortGetter<int[]> getShortGetter() {
    return shortGetter;
  }

  @Override
  protected final AsIntGetter<int[]> getIntGetter() {
    return intGetter;
  }

  @Override
  protected final AsLongGetter<int[]> getLongGetter() {
    return longGetter;
  }

  @Override
  protected final AsFloatGetter<int[]> getFloatGetter() {
    return floatGetter;
  }

  @Override
  protected final AsDoubleGetter<int[]> getDoubleGetter() {
    return doubleGetter;
  }

  @Override
  protected final PrimitiveArrayComparator<int[]> getComparator() {
    return arrayComparator;
  }

  @Override
  protected final SubrangeCopier<int[]> getSubrangeCopier() {
    return subrangeCopier;
  }

  @Override
  protected final ToIntFunction<int[]> getArrayLengthGetter() {
    return lengthGetter;
  }

  @Override
  protected final WrapperSupplier<int[], WkIntArray> getWrapperSupplier() {
    return wrapperSupplier;
  }

  @Override
  protected final SubrangeEqualityComparator<int[]> getEqualityComparator() {
    return equalityComparator;
  }

}
