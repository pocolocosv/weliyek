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

import java.util.Arrays;
import java.util.function.ToIntFunction;

public class ShortArrayWrapper
    extends PrimitiveArrayWrapperBase<short[], ShortArrayWrapper>
{

  private static final AsByteGetter<short[]> byteGetter = (a, i) -> (byte)a[i];
  private static final AsShortGetter<short[]> shortGetter = (a, i) -> a[i];
  private static final AsIntGetter<short[]> intGetter = (a, i) -> (int)a[i];
  private static final AsLongGetter<short[]> longGetter = (a, i) -> (long)a[i];
  private static final AsFloatGetter<short[]> floatGetter = (a, i) -> (float)a[i];
  private static final AsDoubleGetter<short[]> doubleGetter = (a, i) -> (double)a[i];
  private static final PrimitiveArrayComparator<short[]> arrayComparator = Arrays::compare;
  private static final SubrangeCopier<short[]> subrangeCopier = Arrays::copyOfRange;
  private static final ToIntFunction<short[]> lengthGetter = (a) -> a.length;
  private static final WrapperSupplier<short[], ShortArrayWrapper> wrapperSupplier = ShortArrayWrapper::new;
  private static final SubrangeEqualityComparator<short[]> equalityComparator = Arrays::equals;

  ShortArrayWrapper(short[] array, int offset, int to) {
    super(array, offset, to, lengthGetter);
  }

  @Override
  protected final AsByteGetter<short[]> getByteGetter() {
    return byteGetter;
  }

  @Override
  protected final AsShortGetter<short[]> getShortGetter() {
    return shortGetter;
  }

  @Override
  protected final AsIntGetter<short[]> getIntGetter() {
    return intGetter;
  }

  @Override
  protected final AsLongGetter<short[]> getLongGetter() {
    return longGetter;
  }

  @Override
  protected final AsFloatGetter<short[]> getFloatGetter() {
    return floatGetter;
  }

  @Override
  protected final AsDoubleGetter<short[]> getDoubleGetter() {
    return doubleGetter;
  }

  @Override
  protected final PrimitiveArrayComparator<short[]> getComparator() {
    return arrayComparator;
  }

  @Override
  protected final SubrangeCopier<short[]> getSubrangeCopier() {
    return subrangeCopier;
  }

  @Override
  protected final ToIntFunction<short[]> getArrayLengthGetter() {
    return lengthGetter;
  }

  @Override
  protected final WrapperSupplier<short[], ShortArrayWrapper> getWrapperSupplier() {
    return wrapperSupplier;
  }

  @Override
  protected final SubrangeEqualityComparator<short[]> getEqualityComparator() {
    return equalityComparator;
  }

}
