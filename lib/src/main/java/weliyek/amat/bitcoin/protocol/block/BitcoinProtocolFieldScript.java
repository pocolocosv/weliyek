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

import java.io.IOException;

import weliyek.amat.base.AmatNameComposite;
import weliyek.amat.base.protocol.message.field.AmatProtocolFieldVariableLengthAbstract;
import weliyek.amat.bitcoin.protocol.BitcoinConfig;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolField;
import weliyek.ketza.util.KetzaByteOutputStream;

public class BitcoinProtocolFieldScript
        extends AmatProtocolFieldVariableLengthAbstract<BitcoinConfig>
        implements BitcoinProtocolField
{

    private KetzaByteOutputStream buffer = new KetzaByteOutputStream();

    protected BitcoinProtocolFieldScript(AmatNameComposite namespace) {
        super(namespace);
        onReset();
    }

    @Override
    protected void onSuccessfulVariableFieldRead(byte[] inBuffer) {
        try {
            buffer.write(inBuffer);
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException", e);
        }
    }

    @Override
    protected byte[] onBeforeWriting() {
        return buffer.toByteArray();
    }

    @Override
    protected void onReset() {
        buffer.reset();
    }

}
