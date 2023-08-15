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
package weliyek.amat.bitcoin.protocol;

import java.io.InputStream;
import java.io.OutputStream;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.base.AmatProtocolUtil;

public class BitcoinMessageMagic
{

    public static final BitcoinMessageMagic MAIN = new BitcoinMessageMagic(BitcoinMessageMagicName.MAIN);
    public static final BitcoinMessageMagic TESTNET = new BitcoinMessageMagic(BitcoinMessageMagicName.TESTNET);
    public static final BitcoinMessageMagic TESTNET3 = new BitcoinMessageMagic(BitcoinMessageMagicName.TESTNET3);
    public static final BitcoinMessageMagic NAMECOIN = new BitcoinMessageMagic(BitcoinMessageMagicName.NAMECOIN);

    public final BitcoinMessageMagicName name;

    public final long value;

    private BitcoinMessageMagic(BitcoinMessageMagicName name) {
        this.name = name;
        this.value = name.value();
    }

    BitcoinMessageMagic(int val) {
        BitcoinMessageMagicName name = BitcoinMessageMagicName.fromInt(val);
        if (null == name) {
            this.name = BitcoinMessageMagicName.UNKNOWN;
            this.value = val;
        } else {
            this.name = name;
            this.value = name.value();
        }
    }

    public void writeTo(OutputStream out, AmatNamespace namespace) {
        AmatProtocolUtil.writeLittleEndianInt(out, value, namespace);
    }

    public static BitcoinMessageMagic readFrom(InputStream in, AmatNamespace namespace) {
        final long magicVal = AmatProtocolUtil.readLittleEndianInt(in, namespace);
        return new BitcoinMessageMagic((int)magicVal);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (value ^ (value >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BitcoinMessageMagic))
            return false;
        BitcoinMessageMagic other = (BitcoinMessageMagic) obj;
        if (value != other.value)
            return false;
        return true;
    }

}
