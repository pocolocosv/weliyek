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

import java.util.AbstractSet;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringJoiner;

public class BitcoinNodeServices
    extends AbstractSet<BitcoinServiceFlag>
{

    private final EnumSet<BitcoinServiceFlag> flagSet;

    public final long value;

    public static BitcoinNodeServices with(BitcoinServiceFlag first, BitcoinServiceFlag... flags) {
        EnumSet<BitcoinServiceFlag> enumset = EnumSet.of(first, flags);
        return new BitcoinNodeServices(enumset);
    }

    private BitcoinNodeServices(EnumSet<BitcoinServiceFlag> set) {
        this.flagSet = set;
        long mask = 0;
        for (BitcoinServiceFlag flag : flagSet) {
            mask |= flag.bitmask;
        }
        this.value = mask;
    }

    @Override
    public Iterator<BitcoinServiceFlag> iterator() {
        return flagSet.iterator();
    }

    @Override
    public int size() {
        return flagSet.size();
    }

    public long toLong() {
        return value;
    }

    public static BitcoinNodeServices fromLong(long val) {
        Set<BitcoinServiceFlag> services = new LinkedHashSet<>();
        for (BitcoinServiceFlag flag : BitcoinServiceFlag.FLAG_BY_MASK.values()) {
            if (0 != (val & flag.bitmask)) {
                services.add(flag);
            }
        }
        EnumSet<BitcoinServiceFlag> enumset;
        if (services.isEmpty())
            enumset = EnumSet.noneOf(BitcoinServiceFlag.class);
        else
            enumset = EnumSet.copyOf(services);
        return new BitcoinNodeServices(enumset);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("|" );
        for (BitcoinServiceFlag s : flagSet) {
            joiner.add(s.name());
        }
        return joiner.toString();
    }

    /*
    public void writeTo(OutputStream out, AmatNamespace namespace) {
        AmatProtocolUtil.writeLittleEndianInt(out, this.value, namespace);
    }

    public static BitcoinNodeServices readFrom(InputStream in, AmatNamespace namespace) {
        long val = AmatProtocolUtil.readLittleEndianInt(in, namespace);
        return new BitcoinNodeServices(val);
    }
    */

}
