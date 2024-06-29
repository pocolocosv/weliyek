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

public class WkBtcDifficultyTarget extends WkBtcHash256
{

    public WkBtcDifficultyTarget(byte[] bytes) {
        super(bytes);
    }

    public WkBtcDifficultyTarget(byte[] buff, int offset) {
        super(buff, offset);
    }

    public static WkBtcDifficultyTarget ofCompact(int compact) {
        final BigInteger bigInt = convertCompactToBigInteger(compact);
        final byte[] bigIntArray = bigInt.toByteArray();
        if (WkBtcHash256.CANONICAL_BYTE_LENGTH < bigIntArray.length) {
          throw new IllegalArgumentException("Length of derived byte array is longer that expected");
        }
        byte[] hashBytes = new byte[WkBtcHash256.CANONICAL_BYTE_LENGTH];
        int zeroPrefixLen = WkBtcHash256.CANONICAL_BYTE_LENGTH - bigIntArray.length;
        System.arraycopy(bigIntArray, 0, hashBytes, zeroPrefixLen, bigIntArray.length);
        return new WkBtcDifficultyTarget(hashBytes);
    }

    public int toCompact() {
        BigInteger bigInt = this.asBigInteger();
        return convertBigIntegerToCompact(bigInt);
    }

    /**
     * Encodes a {@link BigInteger} into a compact 32 bit value.
     */
    static int convertBigIntegerToCompact(BigInteger bigInt) {
        boolean isNeg = bigInt.signum() == -1 ? true : false;
        if (isNeg) {
            bigInt = bigInt.abs();
        }
        final byte[] bigIntArray = bigInt.toByteArray();
        int size = bigIntArray.length;
        int result = 0;
        if (size <= 3) {
            result = bigInt.intValue() << 8 * (3 - size);
        } else {
            result = bigInt.shiftRight(8 * (size - 3)).intValue();
        }
        /*
        if (isNeg) {
            result >>= 8;
            size++;
        }
        */
        result |= size << 24;
        result |= isNeg ? 0x0080_0000 : 0;
        return result;
    }

    static BigInteger convertCompactToBigInteger(int compact) {
        final int size = (compact >> 24) & 0xFF;
        if (size > 32) {
            return BigInteger.ZERO;
        }
        int word = compact & 0x007F_FFFF;
        if (0 == size) {
            return BigInteger.ZERO;
        }
        BigInteger bigInt = null;
        if (size <= 3) {
            bigInt = BigInteger.valueOf(word >> 8 * (3 - size));
        } else {
            bigInt = BigInteger.valueOf(word).shiftLeft(8 * (size - 3));
        }
        if ((compact & 0x0080_0000) != 0) {
            bigInt = bigInt.negate();
        }
        return bigInt;
    }

}
