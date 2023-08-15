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
package weliyek.amat.bitcoin.protocol.block;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

import weliyek.amat.bitcoin.protocol.BitcoinProtocolSegregatedWitnessNamespace;
import weliyek.bitcoin.protocol.tx.BitcoinTxRO;

public class BitcoinProtocolTxRO
        extends BitcoinTxRO<BitcoinProtocolTxInRO,
                                          BitcoinProtocolTxOutRO>
{

    private List<BitcoinProtocolTxInRO> unmodifiableTxInList = Collections.unmodifiableList(this.txInList);
    private List<BitcoinProtocolTxOutRO> unmodifiableTxOutList = Collections.unmodifiableList(this.txOutList);

    @Override
    public List<BitcoinProtocolTxInRO> txInList() {
        return unmodifiableTxInList;
    }

    @Override
    public List<BitcoinProtocolTxOutRO> txOutList() {
        return unmodifiableTxOutList;
    }

    @Override
    protected BitcoinProtocolTxInRO newTxInOnRead(InputStream in,
                                                  BitcoinProtocolTxInNamespaces txinNamespace)
    {
        return BitcoinProtocolTxInRO.readFrom(in, txinNamespace);
    }

    @Override
    protected BitcoinProtocolTxOutRO newTxOutOnRead(InputStream in,
                                                    BitcoinProtocolTxOutNamespaces txoutNamespaces)
    {
        return BitcoinProtocolTxOutRO.readFrom(in, txoutNamespaces);
    }

    @Override
    protected void readSegwit(BitcoinProtocolTxInRO txIn,
                              InputStream in,
                              BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces)
    {
        txIn.segregatedWitness().readFrom(in, segwitNamespaces);
    }

    private final static String WRITE_UNSUPPORTED = "Serializing " + BitcoinProtocolTxRO.class.getSimpleName()
                                                   + " is not supported";

    @Override
    protected void writeToOutputStream(OutputStream out, BitcoinProtocolTxNamespaces txNamespaces) {
        throw new UnsupportedOperationException(WRITE_UNSUPPORTED);
    }

    @Override
    protected void writeTxInSegwit(BitcoinProtocolTxInRO txIn, OutputStream out,
                                   BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces) {
        throw new UnsupportedOperationException(WRITE_UNSUPPORTED);
    }


}
