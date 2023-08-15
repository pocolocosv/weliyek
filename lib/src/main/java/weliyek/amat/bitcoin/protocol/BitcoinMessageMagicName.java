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

import java.util.HashMap;
import java.util.Map;

public enum BitcoinMessageMagicName {
    MAIN(0xD9B4BEF9),
    TESTNET(0xDAB5BFFA),
    TESTNET3(0x0709110B),
    NAMECOIN(0xFEB4BEF9),
    UNKNOWN(-1);

    private int value;

    private static final Map<Integer, BitcoinMessageMagicName> mappedValues;

    static {
        mappedValues = new HashMap<>();
        mappedValues.put(MAIN.value, MAIN);
        mappedValues.put(TESTNET.value, TESTNET);
        mappedValues.put(TESTNET3.value, TESTNET3);
        mappedValues.put(NAMECOIN.value, NAMECOIN);
    }

    BitcoinMessageMagicName(int val) {
        this.value = val;
    }

    public int value() {
        return this.value;
    }

    static BitcoinMessageMagicName fromInt(int readInt) {
        return mappedValues.get(readInt);
    }
}