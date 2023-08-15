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

import java.io.DataInput;
import java.io.IOException;
import java.math.BigInteger;

import weliyek.ketza.util.ByteSequenceWrapper;

public class BitcoinHash extends ByteSequenceWrapper implements Comparable<BitcoinHash>
{

    /** All Bitcoin hashes are 32 bytes long. */
    public static final int CANONICAL_BYTE_LENGTH = 32;

    private BigInteger bigInt;

    protected BitcoinHash(byte[] buff) {
        super(buff);
    }

    protected BitcoinHash(byte[] buff, int offset) {
        super(buff, offset);
    }

    protected BitcoinHash(DataInput input, long len) throws IOException {
        super(input, len);
    }

    public static BitcoinHash build(byte[] buff) {
        if (CANONICAL_BYTE_LENGTH != buff.length)
            throw new IllegalArgumentException("Invalid byte array length");
        return new BitcoinHash(buff);
    }

    public static BitcoinHash build(byte[] buff, int offset) {
        if (CANONICAL_BYTE_LENGTH != (buff.length - offset))
            throw new IllegalArgumentException("Invalid byte array length");
        return new BitcoinHash(buff, offset);
    }

    public static BitcoinHash build(DataInput input) throws IOException {
        return new BitcoinHash(input, CANONICAL_BYTE_LENGTH);
    }

    public BigInteger asBigInteger() {
        if (null == bigInt)
            this.bigInt = new BigInteger(buffer);
        return this.bigInt;
    }

    @Override
    public int compareTo(BitcoinHash other) {
        return this.asBigInteger().compareTo(other.asBigInteger());
    }

    @Override
    public String toString() {
        return bytesToHex(buffer);
    }

}
