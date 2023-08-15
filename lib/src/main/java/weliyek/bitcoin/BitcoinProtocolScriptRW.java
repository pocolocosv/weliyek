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

import java.io.OutputStream;

public class BitcoinProtocolScriptRW
        extends BitcoinProtocolScriptAbstract
{

    public BitcoinProtocolScriptRW() {
        super();
    }

    public BitcoinProtocolScriptRW(BitcoinProtocolScriptRO ro) {
        super(ro);
    }

    @Override
    public BitcoinScriptOp set(int index, BitcoinScriptOp element) {
        return this.list.set(index, element);
    }

    @Override
    public void add(int index, BitcoinScriptOp element) {
        this.list.add(index, element);
    }

    @Override
    public BitcoinScriptOp remove(int index) {
        return this.list.remove(index);
    }

    public void writeTo(OutputStream  out,
                        BitcoinProtocolScriptNamespaces scriptNamespace) {
        super.writeToOutputStream(out, scriptNamespace);
    }

}
