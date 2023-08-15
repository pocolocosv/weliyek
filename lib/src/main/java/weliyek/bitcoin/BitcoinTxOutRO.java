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

public class BitcoinTxOutRO
        implements BitcoinTxOut
{

    /**
     * Number of satoshis to spend. May be zero; the sum of all outputs may
     * not exceed the sum of satoshis previously spent to the outpoints
     * provided in the input section. (Exception: coinbase transactions
     * spend the block subsidy and collected transaction fees.)
     */
    protected final long value;

    protected final BitcoinScript script;

    public static BitcoinTxOutRO build(long value, BitcoinScript script) {
        return new BitcoinTxOutRO(value, script);
    }

    BitcoinTxOutRO(long value, BitcoinScript script) {
        this.value = value;
        this.script = Objects.requireNonNull(script);
    }

    @Override
    public long value() {
        return value;
    }

    @Override
    public BitcoinScript script() {
        return script;
    }

}
