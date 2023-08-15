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

public class BitcoinInventoryVector
{

    private final BitcoinInventoryVectorType type;

    private final BitcoinHash hash;

    public BitcoinInventoryVector(BitcoinInventoryVectorType t, BitcoinHash hash) {
        this.type = Objects.requireNonNull(t);
        this.hash = Objects.requireNonNull(hash);
    }

    public BitcoinInventoryVectorType getType() {
        return type;
    }

    public BitcoinHash getHash() {
        return hash;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getHash().hashCode();
        result = prime * result + getType().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BitcoinInventoryVector))
            return false;
        BitcoinInventoryVector other = (BitcoinInventoryVector) obj;
        if (!getHash().equals(other.getHash()))
            return false;
        if (!getType().equals(other.getType()))
            return false;
        return true;
    }

}
