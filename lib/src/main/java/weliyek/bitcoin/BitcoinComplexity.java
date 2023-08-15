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

public class BitcoinComplexity extends BitcoinHash
{

    private BitcoinComplexity(byte[] bytes) {
        super(bytes);
    }

    private BitcoinComplexity(byte[] buff, int offset) {
        super(buff, offset);
    }

    public static BitcoinComplexity ofCompact(int compact) {
        final BigInteger bigInt = convertCompactToBigInteger(compact);
        final byte[] bigIntByteArray = bigInt.toByteArray();
        if (BitcoinHash.CANONICAL_BYTE_LENGTH < bigIntByteArray.length)
            throw new IllegalArgumentException("Length of derived byte array is longer that expected");
        byte[] hashBytes = bigIntByteArray;
        if (bigIntByteArray.length < BitcoinHash.CANONICAL_BYTE_LENGTH)
            hashBytes = Arrays.copyOf(bigIntByteArray, CANONICAL_BYTE_LENGTH);
        return new BitcoinComplexity(hashBytes);
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
