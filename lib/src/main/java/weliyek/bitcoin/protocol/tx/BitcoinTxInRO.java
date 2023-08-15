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
package weliyek.bitcoin.protocol.tx;

import java.util.List;
import java.util.Objects;

import weliyek.bitcoin.BitcoinOutpoint;
import weliyek.bitcoin.script.BitcoinScript;

public class BitcoinTxInRO
        implements BitcoinTxIn
{

    /**
     * The previous outpoint being spent.
     */
    private final BitcoinOutpoint outpoint;

    /**
     * A script-language script which satisfies the conditions placed in
     * the outpoint’s pubkey script. Should only contain data pushes;
     */
    private final BitcoinScript script;

    /**
     * Sequence number. Default for Bitcoin Core and almost all other
     * programs is 0xffffffff.
     */
    private final long sequence;

    private List<BitcoinScript> scriptWitness;

    public static BitcoinTxInRO build(
            BitcoinOutpoint outpoint,
            BitcoinScript script,
            long sequence) {
        return new BitcoinTxInRO(outpoint, script, sequence);
    }

    BitcoinTxInRO(
            BitcoinOutpoint outpoint,
            BitcoinScript script,
            long sequence) {
        this.outpoint = Objects.requireNonNull(outpoint);
        this.script = Objects.requireNonNull(script);
        this.sequence = sequence;
    }

    @Override
    public BitcoinOutpoint outpoint() {
        return outpoint;
    }

    @Override
    public BitcoinScript script() {
        return script;
    }

    @Override
    public long sequence() {
        return sequence;
    }

    @Override
    public List<BitcoinScript> segregatedWitness() {
        return scriptWitness;
    }

}
