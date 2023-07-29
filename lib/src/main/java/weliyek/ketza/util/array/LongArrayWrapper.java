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

public class LongArrayWrapper
    extends PrimitiveArrayWrapperBase<long[], LongArrayWrapper>
{

  private static final AsByteGetter<long[]> byteGetter = (a, i) -> (byte)a[i];
  private static final AsShortGetter<long[]> shortGetter = (a, i) -> (short)a[i];
  private static final AsIntGetter<long[]> intGetter = (a, i) -> (int)a[i];
  private static final AsLongGetter<long[]> longGetter = (a, i) -> a[i];
  private static final AsFloatGetter<long[]> floatGetter = (a, i) -> (float)a[i];
  private static final AsDoubleGetter<long[]> doubleGetter = (a, i) -> (double)a[i];
  private static final PrimitiveArrayComparator<long[]> arrayComparator = Arrays::compare;
  private static final SubrangeCopier<long[]> subrangeCopier = Arrays::copyOfRange;
  private static final ToIntFunction<long[]> lengthGetter = (a) -> a.length;
  private static final WrapperSupplier<long[], LongArrayWrapper> wrapperSupplier = LongArrayWrapper::new;
  private static final SubrangeEqualityComparator<long[]> equalityComparator = Arrays::equals;

  LongArrayWrapper(long[] array, int offset, int to) {
    super(array, offset, to, lengthGetter);
  }

  @Override
  protected final AsByteGetter<long[]> getByteGetter() {
    return byteGetter;
  }

  @Override
  protected final AsShortGetter<long[]> getShortGetter() {
    return shortGetter;
  }

  @Override
  protected final AsIntGetter<long[]> getIntGetter() {
    return intGetter;
  }

  @Override
  protected final AsLongGetter<long[]> getLongGetter() {
    return longGetter;
  }

  @Override
  protected final AsFloatGetter<long[]> getFloatGetter() {
    return floatGetter;
  }

  @Override
  protected final AsDoubleGetter<long[]> getDoubleGetter() {
    return doubleGetter;
  }

  @Override
  protected final PrimitiveArrayComparator<long[]> getComparator() {
    return arrayComparator;
  }

  @Override
  protected final SubrangeCopier<long[]> getSubrangeCopier() {
    return subrangeCopier;
  }

  @Override
  protected final ToIntFunction<long[]> getArrayLengthGetter() {
    return lengthGetter;
  }

  @Override
  protected final WrapperSupplier<long[], LongArrayWrapper> getWrapperSupplier() {
    return wrapperSupplier;
  }

  @Override
  protected final SubrangeEqualityComparator<long[]> getEqualityComparator() {
    return equalityComparator;
  }

}
