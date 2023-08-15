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

import java.util.Objects;

public class BitcoinOutpoint implements Comparable<BitcoinOutpoint>
{

    public static final BitcoinOutpoint NULL = new BitcoinOutpoint();

    public final BitcoinHash hash;

    public final long index;

    private final int hashCode;

    public static BitcoinOutpoint buildFrom(BitcoinHash hash, long index) {
        if (null == hash) // ignore index and return NULL
            return NULL;
        else
            return new BitcoinOutpoint(hash, index);
    }

    private BitcoinOutpoint() {
        this.hash = null;
        this.index = -1;
        this.hashCode = Objects.hash(index);
    }

    BitcoinOutpoint(BitcoinHash hash, long index) {
        if (null == hash || index == -1) {
            throw new IllegalArgumentException();
        }
        this.hash = hash;
        this.index = index;
        this.hashCode = Objects.hash(hash, index);
    }

    public BitcoinHash hash() {
        return hash;
    }

    public long index() {
        return index;
    }

    public boolean isNull() {
        return null == hash || index == -1;
    }

    @Override
    public int compareTo(BitcoinOutpoint o) {
        if (isNull()) {
            return -1;
        }
        final int hashRes = hash.compareTo(o.hash);
        if (hashRes != 0) {
            // Bitcoin core code does not do a complete comparaison. Instead
            // it only checks for 'less than'.
            // See bitcoin/src/primitives/transaction.h
            return hashRes;
        }
        return Long.compare(this.index, o.index);
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BitcoinOutpoint))
            return false;
        BitcoinOutpoint other = (BitcoinOutpoint) obj;
        return Objects.equals(hash, other.hash) && index == other.index;
    }

    /*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hash == null) ? 0 : hash.hashCode());
        result = prime * result + (index ^ (index >>> 32));
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
        BitcoinOutpoint other = (BitcoinOutpoint) obj;
        if (hash == null) {
            if (other.hash != null)
                return false;
        } else if (!hash.equals(other.hash))
            return false;
        if (index != other.index)
            return false;
        return true;
    }
    */

    /*
    public void writeTo(OutputStream out, BitcoinOutpointNamespace outpointNamespace) {
        hash.writeTo(out, outpointNamespace.hash);
        AmatProtocolUtil.writeLittleEndianInt(out, this.index, outpointNamespace.index);
    }

    public static BitcoinOutpoint readFrom(InputStream in, BitcoinOutpointNamespace outpointNamespace) {
        final BitcoinHash h = BitcoinHash.readFrom(in, outpointNamespace.hash);
        final int i = (int) AmatProtocolUtil.readLittleEndianInt(in, outpointNamespace.index);
        return new BitcoinOutpoint(h, i);
    }
    */

}
