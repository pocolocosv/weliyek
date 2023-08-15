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

import java.io.InputStream;
import java.io.OutputStream;

public class BitcoinProtocolTxOutRW
        extends BitcoinTxOutRO<BitcoinProtocolScriptRW>
{

    public BitcoinProtocolTxOutRW() {
        this.value = 0;
        this.script = new BitcoinProtocolScriptRW();
    }

    public BitcoinProtocolTxOutRW(BitcoinProtocolTxOutRO txoutRO) {
        this.value = txoutRO.value();
        this.script = new BitcoinProtocolScriptRW(txoutRO.script());
    }

    public void setValue(long val) {
        this.value = val;
    }

    public void writeTo(OutputStream out,
                        BitcoinProtocolTxOutNamespaces txoutNamespaces) {
        super.writeToOutputStream(out, txoutNamespaces);
    }

    @Override
    protected void writeScript(BitcoinProtocolScriptRW script,
                               OutputStream out,
                               BitcoinProtocolScriptNamespaces scriptNamespaces) {
        script.writeTo(out, scriptNamespaces);
    }

    @Override
    protected BitcoinProtocolScriptRW newScriptOnRead(InputStream                     in,
                                                      BitcoinProtocolScriptNamespaces scriptNamespaces) {
        throw new UnsupportedOperationException("Reading " + BitcoinProtocolTxOutRW.class.getSimpleName()
                                                + " is not supported");
    }

}
