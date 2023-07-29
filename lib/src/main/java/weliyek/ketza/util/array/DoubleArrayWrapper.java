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

public class DoubleArrayWrapper
    extends PrimitiveArrayWrapperBase<double[], DoubleArrayWrapper>
{

  private static final AsByteGetter<double[]> byteGetter = (a, i) -> (byte)a[i];
  private static final AsShortGetter<double[]> shortGetter = (a, i) -> (short)a[i];
  private static final AsIntGetter<double[]> intGetter = (a, i) -> (int)a[i];
  private static final AsLongGetter<double[]> longGetter = (a, i) -> (long)a[i];
  private static final AsFloatGetter<double[]> floatGetter = (a, i) -> (float)a[i];
  private static final AsDoubleGetter<double[]> doubleGetter = (a, i) -> a[i];
  private static final PrimitiveArrayComparator<double[]> arrayComparator = Arrays::compare;
  private static final SubrangeCopier<double[]> subrangeCopier = Arrays::copyOfRange;
  private static final ToIntFunction<double[]> lengthGetter = (a) -> a.length;
  private static final WrapperSupplier<double[], DoubleArrayWrapper> wrapperSupplier = DoubleArrayWrapper::new;
  private static final SubrangeEqualityComparator<double[]> equalityComparator = Arrays::equals;

  DoubleArrayWrapper(double[] array, int offset, int to) {
    super(array, offset, to, lengthGetter);
  }

  @Override
  protected final AsByteGetter<double[]> getByteGetter() {
    return byteGetter;
  }

  @Override
  protected final AsShortGetter<double[]> getShortGetter() {
    return shortGetter;
  }

  @Override
  protected final AsIntGetter<double[]> getIntGetter() {
    return intGetter;
  }

  @Override
  protected final AsLongGetter<double[]> getLongGetter() {
    return longGetter;
  }

  @Override
  protected final AsFloatGetter<double[]> getFloatGetter() {
    return floatGetter;
  }

  @Override
  protected final AsDoubleGetter<double[]> getDoubleGetter() {
    return doubleGetter;
  }

  @Override
  protected final PrimitiveArrayComparator<double[]> getComparator() {
    return arrayComparator;
  }

  @Override
  protected final SubrangeCopier<double[]> getSubrangeCopier() {
    return subrangeCopier;
  }

  @Override
  protected final ToIntFunction<double[]> getArrayLengthGetter() {
    return lengthGetter;
  }

  @Override
  protected final WrapperSupplier<double[], DoubleArrayWrapper> getWrapperSupplier() {
    return wrapperSupplier;
  }

  @Override
  protected final SubrangeEqualityComparator<double[]> getEqualityComparator() {
    return equalityComparator;
  }

}
