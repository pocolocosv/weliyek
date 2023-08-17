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

public class WkBitcoinHash implements Comparable<WkBitcoinHash>
{

    /** All Bitcoin hashes are 32 bytes long. */
    public static final int CANONICAL_BYTE_LENGTH = 32;

    private WkByteArray bytes;

    public WkBitcoinHash(WkByteArray bytes) {
      if (CANONICAL_BYTE_LENGTH != bytes.getLength())
        throw new IllegalArgumentException("Invalid byte array length");
      this.bytes = Objects.requireNonNull(bytes);
    }

    public WkBitcoinHash(byte[] array) {
      this(array, 0, array.length);
    }

    public WkBitcoinHash(byte[] array, int offset) {
      this(array, offset, array.length);
    }

    public WkBitcoinHash(byte[] array, int from, int to) {
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

    public BigInteger asBigInteger() {
        return bytes.convertToBigInteger();
    }

    @Override
    public int compareTo(WkBitcoinHash other) {
        return this.asBigInteger().compareTo(other.asBigInteger());
    }

    @Override
    public String toString() {
        return bytes.bytesToHexStr();
    }

}
