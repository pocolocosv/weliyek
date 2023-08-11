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

import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.ToIntFunction;

public abstract class PrimitiveArrayWrapperBase<A, W extends PrimitiveArrayWrapperBase<A, ?>>
  implements PrimitiveArrayWrapper<A, W>
{

  interface SubrangeCopier<A> {
    A copy(A original, int from, int to);
  }

  interface SubrangeEqualityComparator<A> {
    boolean isEqual(A thisArray, int thisFrom, int thisTo, A otherArray, int otherFrom, int otherTo);
  }

  interface WrapperSupplier<A, W extends GenericArrayWrapper<A,?>> {
    W getWrapper(A array, int from, int to);
  }

  public interface PrimitiveArrayComparator<A> {
    int compare(A myArray, int from, int to, A otheArray, int otherFrom, int otherTo);
  }

  @FunctionalInterface
  public interface AsByteGetter<A> {
    byte getIndexedAsByte(A array, int i);
  }

  @FunctionalInterface
  public interface AsShortGetter<A> {
    short getIndexedAsShort(A array, int i);
  }

  @FunctionalInterface
  public interface AsIntGetter<A> {
    int getIndexedAsInt(A array, int i);
  }

  @FunctionalInterface
  public interface AsLongGetter<A> {
    long getIndexedAsLong(A array, int i);
  }

  public interface AsFloatGetter<A> {
    float getIndexedAsLong(A array, int i);
  }

  public interface AsDoubleGetter<A> {
    double getIndexedAsLong(A array, int i);
  }

  private final A array;
  private final int from;
  private final int to;

  PrimitiveArrayWrapperBase(
      A array,
      int from,
      int to,
      ToIntFunction<A> arrayLengthGetter) {
    this.array = Objects.requireNonNull(array);
    WKArrayUtil.throwIfBoundsAreInvalid(arrayLengthGetter.applyAsInt(array), from, to);
    this.from = from;
    this.to = to;
  }

  @Override
  public byte getAsByte(int index) {
    return getByteGetter().getIndexedAsByte(getArray(), absolutePos(index));
  }

  protected abstract AsByteGetter<A> getByteGetter();

  @Override
  public short getAsShort(int index) {
    return getShortGetter().getIndexedAsShort(getArray(), absolutePos(index));
  }

  protected abstract AsShortGetter<A> getShortGetter();

  @Override
  public int getAsInt(int index) {
    return getIntGetter().getIndexedAsInt(getArray(), absolutePos(index));
  }

  protected abstract AsIntGetter<A> getIntGetter();

  @Override
  public long getAsLong(int index) {
    return getLongGetter().getIndexedAsLong(getArray(), absolutePos(index));
  }

  protected abstract AsLongGetter<A> getLongGetter();

  @Override
  public float getAsFloat(int index) {
    return getFloatGetter().getIndexedAsLong(getArray(), absolutePos(index));
  }

  protected abstract AsFloatGetter<A> getFloatGetter();

  @Override
  public double getAsDouble(int index) {
    return getDoubleGetter().getIndexedAsLong(getArray(), absolutePos(index));
  }

  protected abstract AsDoubleGetter<A> getDoubleGetter();

  @Override
  public int compare(A otherArray) {
    int otherLen = getArrayLengthGetter().applyAsInt(otherArray);
    return this.compare(getOffset(), getTo(), otherArray, 0, otherLen);
  }

  @Override
  public int compare(int myFrom, int myTo, A otherArray, int otherFrom, int otherTo) {
    WKArrayUtil.throwIfBoundsAreInvalid(getLength(), myFrom, myTo);
    Objects.requireNonNull(otherArray);
    WKArrayUtil.throwIfBoundsAreInvalid(getArrayLengthGetter().applyAsInt(otherArray),  otherFrom, otherTo);
    return getComparator().compare(getArray(), myFrom, myTo, otherArray, otherFrom, otherTo);
  }

  protected abstract PrimitiveArrayComparator<A> getComparator();

  @Override
  public void iterateAsIntsWhileTrue(IntPredicate visitor) {
    Objects.requireNonNull(visitor);
    for (int i = 0; i < getLength(); i++) {
      if (!visitor.test(getAsInt(i))) {
        break;
      }
    }
  }

  @Override
  public void iterateAsIntsBackwardsWhileTrue(IntPredicate visitor) {
    Objects.requireNonNull(visitor);
    for (int i = getLength()-1; i >= 0; i--) {
      if (!visitor.test(getAsInt(i))) {
        break;
      }
    }
  }

  @Override
  public void iterateAsLongsWhileTrue(LongPredicate visitor) {
    Objects.requireNonNull(visitor);
    for (int i = 0; i < getLength(); i++) {
      if (!visitor.test(getAsLong(i))) {
        break;
      }
    }
  }

  @Override
  public void iterateAsLongsBackwardsWhileTrue(LongPredicate visitor) {
    Objects.requireNonNull(visitor);
    for (int i = getLength()-1; i >= 0; i--) {
      if (!visitor.test(getAsLong(i))) {
        break;
      }
    }
  }

  final A getArray() {
    return this.array;
  }

  public final int absolutePos(int index) {
      if ((index < 0 || (index >= to))) {
          throw new IndexOutOfBoundsException(index);
      }
      return from + index;
  }

  public final int getOffset() {
    return this.from;
  }

  public final int getTo() {
    return this.to;
  }

  @Override
  public final int getLength() {
    return this.to - this.from;
  }

  public final int getArrayCapacity() {
    return getArrayLengthGetter().applyAsInt(this.array);
  }

  @Override
  public final A copyOfArray() {
    return getSubrangeCopier().copy(getArray(), getOffset(), getTo());
  }

  @Override
  public final A copyOfArray(int from, int to) {
    WKArrayUtil.throwIfBoundsAreInvalid(getLength(), from, to);
    int f = absolutePos(from);
    int t = absolutePos(to);
    return getSubrangeCopier().copy(getArray(), f, t);
  }

  @Override
  public final W subrange(int from, int to) {
    WKArrayUtil.throwIfBoundsAreInvalid(getLength(), from, to);
    int f = absolutePos(from);
    int t = absolutePos(to);
    return getWrapperSupplier().getWrapper(getArray(), f, t);
  }

  public boolean equalsToArray(A otherArray) {
    return equalsToArray(otherArray, 0);
  }

  @Override
  public boolean equalsToArray(A otherArray, int from) {
    Objects.requireNonNull(otherArray);
    final int otherCapacity = getArrayLengthGetter().applyAsInt(otherArray);
    final int otherTo = from + getLength();
    if (WKArrayUtil.areBoundsInvalid(otherCapacity, from, otherTo)) {
      return false;
    }
    return getEqualityComparator().isEqual(getArray(), getOffset(), getTo(), otherArray, from, otherTo);
  }

  protected abstract SubrangeCopier<A> getSubrangeCopier();

  protected abstract ToIntFunction<A> getArrayLengthGetter();

  protected abstract WrapperSupplier<A, W> getWrapperSupplier();

  protected abstract SubrangeEqualityComparator<A> getEqualityComparator();

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + array.hashCode();
    result = prime * result + from;
    result = prime * result + to;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PrimitiveArrayWrapperBase<?,?> o = (PrimitiveArrayWrapperBase<?,?>) obj;
    if (this.array.getClass() != o.array.getClass())
      return false;
    if (this.getLength() != o.getLength())
      return false;
    @SuppressWarnings("unchecked")
    PrimitiveArrayWrapperBase<A,?> other = (PrimitiveArrayWrapperBase<A,?>) o;
    return equalsToArray(other.array, other.getOffset());
  }

}
