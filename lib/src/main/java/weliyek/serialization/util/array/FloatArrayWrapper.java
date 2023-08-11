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
package weliyek.serialization.util.array;

import java.util.Arrays;
import java.util.function.ToIntFunction;

public class FloatArrayWrapper
    extends PrimitiveArrayWrapperBase<float[], FloatArrayWrapper>
{

  private static final AsByteGetter<float[]> byteGetter = (a, i) -> (byte)a[i];
  private static final AsShortGetter<float[]> shortGetter = (a, i) -> (short)a[i];
  private static final AsIntGetter<float[]> intGetter = (a, i) -> (int)a[i];
  private static final AsLongGetter<float[]> longGetter = (a, i) -> (long)a[i];
  private static final AsFloatGetter<float[]> floatGetter = (a, i) -> a[i];
  private static final AsDoubleGetter<float[]> doubleGetter = (a, i) -> (double)a[i];
  private static final PrimitiveArrayComparator<float[]> arrayComparator = Arrays::compare;
  private static final SubrangeCopier<float[]> subrangeCopier = Arrays::copyOfRange;
  private static final ToIntFunction<float[]> lengthGetter = (a) -> a.length;
  private static final WrapperSupplier<float[], FloatArrayWrapper> wrapperSupplier = FloatArrayWrapper::new;
  private static final SubrangeEqualityComparator<float[]> equalityComparator = Arrays::equals;

  FloatArrayWrapper(float[] array, int offset, int to) {
    super(array, offset, to, lengthGetter);
  }

  @Override
  protected final AsByteGetter<float[]> getByteGetter() {
    return byteGetter;
  }

  @Override
  protected final AsShortGetter<float[]> getShortGetter() {
    return shortGetter;
  }

  @Override
  protected final AsIntGetter<float[]> getIntGetter() {
    return intGetter;
  }

  @Override
  protected final AsLongGetter<float[]> getLongGetter() {
    return longGetter;
  }

  @Override
  protected final AsFloatGetter<float[]> getFloatGetter() {
    return floatGetter;
  }

  @Override
  protected final AsDoubleGetter<float[]> getDoubleGetter() {
    return doubleGetter;
  }

  @Override
  protected final PrimitiveArrayComparator<float[]> getComparator() {
    return arrayComparator;
  }

  @Override
  protected final SubrangeCopier<float[]> getSubrangeCopier() {
    return subrangeCopier;
  }

  @Override
  protected final ToIntFunction<float[]> getArrayLengthGetter() {
    return lengthGetter;
  }

  @Override
  protected final WrapperSupplier<float[], FloatArrayWrapper> getWrapperSupplier() {
    return wrapperSupplier;
  }

  @Override
  protected final SubrangeEqualityComparator<float[]> getEqualityComparator() {
    return equalityComparator;
  }

}
