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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Mirrors ServiceFlags enum type in bitcoin's protocol.h.
 */
public enum BitcoinServiceFlag
{
    //NODE_NONE   (0),
    NODE_NETWORK((long)1 << 0),
    NODE_GETUTXO((long)1 << 1),
    NODE_BLOOM  ((long)1 << 2),
    NODE_WITNESS((long)1 << 3),
    NODE_XTHIN  ((long)1 << 4),
    BIT05((long)1 <<  5),
    BIT06((long)1 <<  6),
    BIT07((long)1 <<  7),
    BIT08((long)1 <<  8),
    BIT09((long)1 <<  9),
    BIT10((long)1 << 10),
    BIT11((long)1 << 11),
    BIT12((long)1 << 12),
    BIT13((long)1 << 13),
    BIT14((long)1 << 14),
    BIT15((long)1 << 15),
    BIT16((long)1 << 16),
    BIT17((long)1 << 17),
    BIT18((long)1 << 18),
    BIT19((long)1 << 19),
    BIT20((long)1 << 20),
    BIT21((long)1 << 21),
    BIT22((long)1 << 22),
    BIT23((long)1 << 23),
    BIT24((long)1 << 24),
    BIT25((long)1 << 25),
    BIT26((long)1 << 26),
    BIT27((long)1 << 27),
    BIT28((long)1 << 28),
    BIT29((long)1 << 29),
    BIT30((long)1 << 30),
    BIT31((long)1 << 31),
    BIT32((long)1 << 32),
    BIT33((long)1 << 33),
    BIT34((long)1 << 34),
    BIT35((long)1 << 35),
    BIT36((long)1 << 36),
    BIT37((long)1 << 37),
    BIT38((long)1 << 38),
    BIT39((long)1 << 39),
    BIT40((long)1 << 40),
    BIT41((long)1 << 41),
    BIT42((long)1 << 42),
    BIT43((long)1 << 43),
    BIT44((long)1 << 44),
    BIT45((long)1 << 45),
    BIT46((long)1 << 46),
    BIT47((long)1 << 47),
    BIT48((long)1 << 48),
    BIT49((long)1 << 49),
    BIT50((long)1 << 50),
    BIT51((long)1 << 51),
    BIT52((long)1 << 52),
    BIT53((long)1 << 53),
    BIT54((long)1 << 54),
    BIT55((long)1 << 55),
    BIT56((long)1 << 56),
    BIT57((long)1 << 57),
    BIT58((long)1 << 58),
    BIT59((long)1 << 59),
    BIT60((long)1 << 60),
    BIT61((long)1 << 61),
    BIT62((long)1 << 62),
    BIT63((long)1 << 63);

    static final Map<Long, BitcoinServiceFlag> FLAG_BY_MASK;

    static {
        FLAG_BY_MASK = new LinkedHashMap<>();
        FLAG_BY_MASK.put(NODE_NETWORK.bitmask, NODE_NETWORK);
        FLAG_BY_MASK.put(NODE_GETUTXO.bitmask, NODE_GETUTXO);
        FLAG_BY_MASK.put(NODE_BLOOM.bitmask,   NODE_BLOOM);
        FLAG_BY_MASK.put(NODE_WITNESS.bitmask, NODE_WITNESS);
        FLAG_BY_MASK.put(NODE_XTHIN.bitmask,   NODE_XTHIN);
        FLAG_BY_MASK.put(BIT05.bitmask,   BIT05);
        FLAG_BY_MASK.put(BIT06.bitmask,   BIT06);
        FLAG_BY_MASK.put(BIT07.bitmask,   BIT07);
        FLAG_BY_MASK.put(BIT08.bitmask,   BIT08);
        FLAG_BY_MASK.put(BIT09.bitmask,   BIT09);
        FLAG_BY_MASK.put(BIT10.bitmask,   BIT10);
        FLAG_BY_MASK.put(BIT11.bitmask,   BIT11);
        FLAG_BY_MASK.put(BIT12.bitmask,   BIT12);
        FLAG_BY_MASK.put(BIT13.bitmask,   BIT13);
        FLAG_BY_MASK.put(BIT14.bitmask,   BIT14);
        FLAG_BY_MASK.put(BIT15.bitmask,   BIT15);
        FLAG_BY_MASK.put(BIT16.bitmask,   BIT16);
        FLAG_BY_MASK.put(BIT17.bitmask,   BIT17);
        FLAG_BY_MASK.put(BIT18.bitmask,   BIT18);
        FLAG_BY_MASK.put(BIT19.bitmask,   BIT19);
        FLAG_BY_MASK.put(BIT20.bitmask,   BIT20);
        FLAG_BY_MASK.put(BIT21.bitmask,   BIT21);
        FLAG_BY_MASK.put(BIT22.bitmask,   BIT22);
        FLAG_BY_MASK.put(BIT23.bitmask,   BIT23);
        FLAG_BY_MASK.put(BIT24.bitmask,   BIT24);
        FLAG_BY_MASK.put(BIT25.bitmask,   BIT25);
        FLAG_BY_MASK.put(BIT26.bitmask,   BIT26);
        FLAG_BY_MASK.put(BIT27.bitmask,   BIT27);
        FLAG_BY_MASK.put(BIT28.bitmask,   BIT28);
        FLAG_BY_MASK.put(BIT29.bitmask,   BIT29);
        FLAG_BY_MASK.put(BIT30.bitmask,   BIT30);
        FLAG_BY_MASK.put(BIT31.bitmask,   BIT31);
        FLAG_BY_MASK.put(BIT32.bitmask,   BIT32);
        FLAG_BY_MASK.put(BIT33.bitmask,   BIT33);
        FLAG_BY_MASK.put(BIT34.bitmask,   BIT34);
        FLAG_BY_MASK.put(BIT35.bitmask,   BIT35);
        FLAG_BY_MASK.put(BIT36.bitmask,   BIT36);
        FLAG_BY_MASK.put(BIT37.bitmask,   BIT37);
        FLAG_BY_MASK.put(BIT38.bitmask,   BIT38);
        FLAG_BY_MASK.put(BIT39.bitmask,   BIT39);
        FLAG_BY_MASK.put(BIT40.bitmask,   BIT40);
        FLAG_BY_MASK.put(BIT41.bitmask,   BIT41);
        FLAG_BY_MASK.put(BIT42.bitmask,   BIT42);
        FLAG_BY_MASK.put(BIT43.bitmask,   BIT43);
        FLAG_BY_MASK.put(BIT44.bitmask,   BIT44);
        FLAG_BY_MASK.put(BIT45.bitmask,   BIT45);
        FLAG_BY_MASK.put(BIT46.bitmask,   BIT46);
        FLAG_BY_MASK.put(BIT47.bitmask,   BIT47);
        FLAG_BY_MASK.put(BIT48.bitmask,   BIT48);
        FLAG_BY_MASK.put(BIT49.bitmask,   BIT49);
        FLAG_BY_MASK.put(BIT50.bitmask,   BIT50);
        FLAG_BY_MASK.put(BIT51.bitmask,   BIT51);
        FLAG_BY_MASK.put(BIT52.bitmask,   BIT52);
        FLAG_BY_MASK.put(BIT53.bitmask,   BIT53);
        FLAG_BY_MASK.put(BIT54.bitmask,   BIT54);
        FLAG_BY_MASK.put(BIT55.bitmask,   BIT55);
        FLAG_BY_MASK.put(BIT56.bitmask,   BIT56);
        FLAG_BY_MASK.put(BIT57.bitmask,   BIT57);
        FLAG_BY_MASK.put(BIT58.bitmask,   BIT58);
        FLAG_BY_MASK.put(BIT59.bitmask,   BIT59);
        FLAG_BY_MASK.put(BIT60.bitmask,   BIT60);
        FLAG_BY_MASK.put(BIT61.bitmask,   BIT61);
        FLAG_BY_MASK.put(BIT62.bitmask,   BIT62);
        FLAG_BY_MASK.put(BIT63.bitmask,   BIT63);
    }

    public final long bitmask;

    private BitcoinServiceFlag(long mask) {
        this.bitmask = mask;
    }

    /*
    public static EnumSet<BitcoinNodeService> fromLong(long val) {
        Set<BitcoinNodeService> services = new LinkedHashSet<>();
        for (BitcoinNodeService flag : BitcoinNodeService.FLAG_BY_MASK.values()) {
            if (0 != (val & flag.bitmask)) {
                services.add(flag);
            }
        }
        if (services.isEmpty())
            return EnumSet.noneOf(BitcoinNodeService.class);
        else
            return EnumSet.copyOf(services);
    }

    public static long toLong(EnumSet<BitcoinNodeService> services) {
        long setBitmask = 0;
        for (BitcoinNodeService s : services) {
            setBitmask |= s.bitmask;
        }
        return setBitmask;
    }

    public static EnumSet<BitcoinNodeService> with(BitcoinNodeService... services) {
        LinkedHashSet<BitcoinNodeService> set = new LinkedHashSet<>();
        for (BitcoinNodeService s : services) {
            set.add(s);
        }
        if (set.isEmpty())
            return EnumSet.noneOf(BitcoinNodeService.class);
        else
            return EnumSet.copyOf(set);
    }
    */

}