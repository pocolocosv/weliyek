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
import java.util.List;

import weliyek.amat.bitcoin.protocol.BitcoinProtocolSegregatedWitnessNamespace;
import weliyek.bitcoin.protocol.tx.BitcoinTxRO;

public class BitcoinProtocolTxRW
        extends BitcoinTxRO<BitcoinProtocolTxInRW,
                                          BitcoinProtocolTxOutRW>
{

    public BitcoinProtocolTxRW() {
        super();
    }

    @Override
    public List<BitcoinProtocolTxInRW> txInList() {
        return this.txInList;
    }

    @Override
    public List<BitcoinProtocolTxOutRW> txOutList() {
        return this.txOutList;
    }

    @Override
    protected void writeTxInSegwit(BitcoinProtocolTxInRW                     txIn,
                                   OutputStream                              out,
                                   BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces)
    {
        txIn.segregatedWitness().writeTo(out, segwitNamespaces);
    }

    private final static String READ_UNSUPPORTED = "Deserializing " + BitcoinProtocolTxRW.class.getSimpleName()
                                                   + " is not supported";

    @Override
    public void readFrom(InputStream in, BitcoinProtocolTxNamespaces txNamespaces) {
        throw new UnsupportedOperationException(READ_UNSUPPORTED);
    }

    @Override
    protected BitcoinProtocolTxInRW newTxInOnRead(InputStream in, BitcoinProtocolTxInNamespaces txinNamespace) {
        throw new UnsupportedOperationException(READ_UNSUPPORTED);
    }

    @Override
    protected BitcoinProtocolTxOutRW newTxOutOnRead(InputStream in, BitcoinProtocolTxOutNamespaces txoutNamespaces) {
        throw new UnsupportedOperationException(READ_UNSUPPORTED);
    }

    @Override
    protected void readSegwit(BitcoinProtocolTxInRW txIn,
                              InputStream in,
                              BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces) {
        throw new UnsupportedOperationException(READ_UNSUPPORTED);
    }

}
