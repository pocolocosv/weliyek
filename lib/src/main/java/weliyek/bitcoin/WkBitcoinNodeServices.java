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
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * A set with the capabilities of a Bitcoin node which when serialized can be used
 * in the services field in the payload of the version command. 
 */
public class WkBitcoinNodeServices
    extends AbstractSet<BitcoinServiceFlag>
{

    private final EnumSet<BitcoinServiceFlag> flagSet;

    public WkBitcoinNodeServices(BitcoinServiceFlag... flags) {
      this(Arrays.asList(flags));
    }

    public WkBitcoinNodeServices(Collection<BitcoinServiceFlag> flags) {
      EnumSet<BitcoinServiceFlag> set = EnumSet.noneOf(BitcoinServiceFlag.class);
      set.addAll(flags);
      this.flagSet = set;
    }

    public WkBitcoinNodeServices(EnumSet<BitcoinServiceFlag> set) {
        this.flagSet = set;
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
        return flagSet.stream().reduce(0L, (Long m, BitcoinServiceFlag f) -> m.longValue() | f.bitmask, (Long r, Long u) -> r | u);
    }

    public static WkBitcoinNodeServices fromLong(long val) {
      EnumSet<BitcoinServiceFlag> flags = BitcoinServiceFlag.FLAG_BY_MASK.values().stream().
          filter((f) -> (f.bitmask & val) > 0).
          collect(Collectors.toCollection(() -> EnumSet.noneOf(BitcoinServiceFlag.class)));
      return new WkBitcoinNodeServices(flags);
    }

    /**
     * Returns a string representation of this {@link WkBitcoinNodeServices} in which
     * each of the {@link BitcoinServiceFlag} that composes it is printed joined by the
     * separator character "|". The {@link BitcoinServiceFlag} with the smallest
     * bitmasks appear first.
     */
    @Override
    public String toString() {
      StringJoiner joiner = flagSet.stream().
          sorted((f1, f2) -> Long.compareUnsigned(f1.bitmask, f2.bitmask)).
          collect(Collectors.toList()).
          stream().
          reduce(
              new StringJoiner("|"), 
              (StringJoiner j, BitcoinServiceFlag f) -> j.add(f.name()), 
              (j1, j2) -> j1.merge(j2));
      return joiner.toString();
    }

}
