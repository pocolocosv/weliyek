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

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.function.ToIntFunction;

public class WkByteArray
    extends WkPrimitiveArrayBase<byte[], WkByteArray>
{

  private static final AsByteGetter<byte[]> byteGetter = (a, i) -> a[i];
  private static final AsShortGetter<byte[]> shortGetter = (a, i) -> (short)a[i];
  private static final AsIntGetter<byte[]> intGetter = (a, i) -> (int)a[i];
  private static final AsLongGetter<byte[]> longGetter = (a, i) -> (long)a[i];
  private static final AsFloatGetter<byte[]> floatGetter = (a, i) -> (float)a[i];
  private static final AsDoubleGetter<byte[]> doubleGetter = (a, i) -> (double)a[i];
  private static final PrimitiveArrayComparator<byte[]> arrayComparator = Arrays::compare;
  private static final SubrangeCopier<byte[]> subrangeCopier = Arrays::copyOfRange;
  private static final ToIntFunction<byte[]> lengthGetter = (a) -> a.length;
  private static final WrapperSupplier<byte[], WkByteArray> wrapperSupplier = WkByteArray::new;
  private static final SubrangeEqualityComparator<byte[]> equalityComparator = Arrays::equals;

  public WkByteArray(byte[] array) {
    this(array, 0, array.length);
  }

  public WkByteArray(byte[] array, int from, int to) {
    super(array, from, to, lengthGetter);
  }

  public String convertToString(Charset charset) {
    return new String(getArray(), getOffset(), getLength(), charset);
  }

  public String convertToString(Charset charset, int offset, int length) {
    return new String(getArray(), offset, length, charset);
  }

  @Override
  protected final AsByteGetter<byte[]> getByteGetter() {
    return byteGetter;
  }

  @Override
  protected final AsShortGetter<byte[]> getShortGetter() {
    return shortGetter;
  }

  @Override
  protected final AsIntGetter<byte[]> getIntGetter() {
    return intGetter;
  }

  @Override
  protected final AsLongGetter<byte[]> getLongGetter() {
    return longGetter;
  }

  @Override
  protected final AsFloatGetter<byte[]> getFloatGetter() {
    return floatGetter;
  }

  @Override
  protected final AsDoubleGetter<byte[]> getDoubleGetter() {
    return doubleGetter;
  }

  @Override
  protected final PrimitiveArrayComparator<byte[]> getComparator() {
    return arrayComparator;
  }

  @Override
  protected final SubrangeCopier<byte[]> getSubrangeCopier() {
    return subrangeCopier;
  }

  @Override
  protected final ToIntFunction<byte[]> getArrayLengthGetter() {
    return lengthGetter;
  }

  @Override
  protected final WrapperSupplier<byte[], WkByteArray> getWrapperSupplier() {
    return wrapperSupplier;
  }

  @Override
  protected final SubrangeEqualityComparator<byte[]> getEqualityComparator() {
    return equalityComparator;
  }

}
