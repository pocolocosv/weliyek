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

import java.util.HashMap;

public class BitcoinInventoryVectorType
{

    public static BitcoinInventoryVectorType ERROR              = new BitcoinInventoryVectorType(Name.ERROR, Name.ERROR.code);
    public static BitcoinInventoryVectorType MSG_TX             = new BitcoinInventoryVectorType(Name.MSG_TX, Name.MSG_TX.code);
    public static BitcoinInventoryVectorType MSG_BLOCK          = new BitcoinInventoryVectorType(Name.MSG_BLOCK, Name.MSG_BLOCK.code);
    public static BitcoinInventoryVectorType MSG_FILTERED_BLOCK = new BitcoinInventoryVectorType(Name.MSG_FILTERED_BLOCK, Name.MSG_FILTERED_BLOCK.code);
    public static BitcoinInventoryVectorType MSG_CMPCT_BLOCK    = new BitcoinInventoryVectorType(Name.MSG_CMPCT_BLOCK, Name.MSG_CMPCT_BLOCK.code);

    public enum Name {
        ERROR(0),
        MSG_TX(1),
        MSG_BLOCK(2),
        MSG_FILTERED_BLOCK(3),
        MSG_CMPCT_BLOCK(4),
        UNKNOWN(-1);

        final int code;

        private Name(int code) {
            this.code = code;
        }

    }

    private static final HashMap<Integer, BitcoinInventoryVectorType> TYPE_BY_CODE;

    static {
        TYPE_BY_CODE = new HashMap<>();
        TYPE_BY_CODE.put(ERROR.code(), ERROR);
        TYPE_BY_CODE.put(MSG_TX.code(), MSG_TX);
        TYPE_BY_CODE.put(MSG_BLOCK.code(), MSG_BLOCK);
        TYPE_BY_CODE.put(MSG_FILTERED_BLOCK.code(), MSG_FILTERED_BLOCK);
        TYPE_BY_CODE.put(MSG_CMPCT_BLOCK.code(), MSG_CMPCT_BLOCK);
    }

    private final Name name;

    private final int code;

    BitcoinInventoryVectorType(Name name, int code) {
        this.name = name;
        this.code = code;
    }

    public Name name() {
        return this.name;
    }

    public int code() {
        return this.code;
    }

    public static BitcoinInventoryVectorType newType(int code) {
        BitcoinInventoryVectorType type = TYPE_BY_CODE.get(code);
        if (null == type) {
            type = new BitcoinInventoryVectorType(Name.UNKNOWN, code);
        }
        return type;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.name.name());
        if (Name.UNKNOWN.equals(this.name)) {
            builder.append("[0x");
            builder.append(Integer.toHexString(this.code));
            builder.append(']');
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + code;
        result = prime * result + name.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BitcoinInventoryVectorType))
            return false;
        BitcoinInventoryVectorType other = (BitcoinInventoryVectorType) obj;
        if (code != other.code)
            return false;
        if (name != other.name)
            return false;
        return true;
    }

}
