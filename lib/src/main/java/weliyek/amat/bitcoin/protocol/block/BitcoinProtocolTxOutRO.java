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

import weliyek.amat.bitcoin.protocol.BitcoinProtocolScriptNamespaces;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolScriptRO;
import weliyek.bitcoin.protocol.tx.BitcoinTxOutRO;

public class BitcoinProtocolTxOutRO
        extends BitcoinTxOutRO<BitcoinProtocolScriptRO>
{

    public static BitcoinProtocolTxOutRO readFrom(InputStream                    in,
                                                  BitcoinProtocolTxOutNamespaces txoutNamespaces) {
        final BitcoinProtocolTxOutRO txoutRO = new BitcoinProtocolTxOutRO();
        txoutRO.readFromInputStream(in, txoutNamespaces);
        return txoutRO;
    }

    @Override
    protected BitcoinProtocolScriptRO newScriptOnRead(InputStream                     in,
                                                      BitcoinProtocolScriptNamespaces scriptNamespaces) {
        return BitcoinProtocolScriptRO.readFrom(in, scriptNamespaces);
    }

    @Override
    protected void writeScript(BitcoinProtocolScriptRO         script,
                               OutputStream                    out,
                               BitcoinProtocolScriptNamespaces scriptNamespaces) {
        throw new UnsupportedOperationException("Writing " + BitcoinProtocolTxOutRO.class.getSimpleName()
                                                + " is not supported");
    }

}
