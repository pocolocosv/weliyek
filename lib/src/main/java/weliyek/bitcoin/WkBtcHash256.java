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
package weliyek.bitcoin;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

import weliyek.util.array.WKArrayUtil;
import weliyek.util.array.WkByteArray;

public class WkBtcHash256 implements Comparable<WkBtcHash256>
{

    public static final int CANONICAL_BYTE_LENGTH = 32;

    final WkByteArray bytes;

    public WkBtcHash256(WkByteArray bytes) {
      Objects.requireNonNull(bytes);
      if (CANONICAL_BYTE_LENGTH != bytes.getLength()) {
        throw new IllegalArgumentException("Invalid byte array length");
      }
      this.bytes = bytes;
    }

    public WkBtcHash256(byte[] array) {
      this(array, 0, array.length);
    }

    public WkBtcHash256(byte[] array, int offset) {
      this(array, offset, array.length);
    }

    public WkBtcHash256(byte[] array, int from, int to) {
      WKArrayUtil.throwIfBoundsAreInvalid(array.length, from, to);
      final int len = to - from;
      if (CANONICAL_BYTE_LENGTH != len) {
        throw new IllegalArgumentException("Invalid byte array length");
      }
      byte[] cpy = null;
      if (0 == from) {
        cpy = Arrays.copyOf(array, len);
      } else {
        cpy = Arrays.copyOfRange(array, from, to);
      }
      this.bytes = new WkByteArray(cpy);
    }

    public WkByteArray bytes() {
      return this.bytes;
    }

    public BigInteger asBigInteger() {
        return bytes.convertToBigInteger();
    }

    @Override
    public int compareTo(WkBtcHash256 other) {
        return this.asBigInteger().compareTo(other.asBigInteger());
    }

    @Override
    public String toString() {
        return bytes.bytesToHexStr();
    }

      @Override
    public int hashCode() {
      return Objects.hash(bytes);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      WkBtcHash256 other = (WkBtcHash256) obj;
      return Objects.equals(bytes, other.bytes);
    }

}
