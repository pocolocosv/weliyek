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

import java.util.Objects;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;

public interface WkPrimitiveArray<A, W extends WkPrimitiveArray<A, ?>>
{

  A copyOfArray();

  A copyOfArray(int from, int to);

  W subrange(int from, int to);

  int getLength();

  int compare(A otherArray);

  int compare(int myFrom, int myTo, A otherArray, int otherFrom, int otherTo);

  boolean equalsToArray(A otherArray, int from);

  byte getAsByte(int index);

  short getAsShort(int index);

  int getAsInt(int index);

  long getAsLong(int index);

  float getAsFloat(int index);

  double getAsDouble(int index);

  void iterateAsIntsWhileTrue(IntPredicate visitor);

  void iterateAsIntsBackwardsWhileTrue(IntPredicate visitor);

  void iterateAsLongsWhileTrue(LongPredicate visitor);

  void iterateAsLongsBackwardsWhileTrue(LongPredicate visitor);

  public static class ContigousIntsCounter implements IntPredicate {

    private int counter = 0;
    private final int target;

    public ContigousIntsCounter(int target) {
      this.target = target;
    }

    @Override
    public boolean test(int element) {
      if (target == element) {
        this.counter++;
        return true;
      } else {
        return false;
      }
    }

    public int count() {
      return this.counter;
    }

  }

  public class ContigousLongsCounter implements LongPredicate {

    private int counter = 0;
    private final long target;

    public ContigousLongsCounter(long target) {
      this.target = target;
    }

    @Override
    public boolean test(long element) {
      if (target == element) {
        this.counter++;
        return true;
      } else {
        return false;
      }
    }

    public int count() {
      return this.counter;
    }

  }

  class ArrayEqualComparator implements IntPredicate {

    private int counter = 0;
    private boolean failedTest = false;
    private final byte[] array;
    private final int from;
    private final int to;

    public ArrayEqualComparator(byte[] array, int from, int to) {
      this.array = Objects.requireNonNull(array);
      this.from = from;
      this.to = to;
      WKArrayUtil.throwIfBoundsAreInvalid(array.length, from, to);
    }

    @Override
    public boolean test(int value) {
      if (failedTest) {
        return false;
      }
      if (this.counter == (this.to - this.from)) {
        return ! failedTest;
      }
      byte arrayVal = this.array[ this.from + this.counter++ ];
      if (value == arrayVal) {
        return true;
      } else {
        this.failedTest = true;
        return false;
      }
    }

    public int getCounter() {
      return this.counter;
    }

  }

}
