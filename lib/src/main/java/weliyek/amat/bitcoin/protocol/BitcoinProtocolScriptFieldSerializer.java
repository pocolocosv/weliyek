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
package weliyek.amat.bitcoin.protocol;

import java.io.InputStream;
import java.io.OutputStream;

import weliyek.amat.base.protocol.message.field.AmatProtocolMessageFieldBaseSerializer;
import weliyek.ketza.base.composite.serializable.SerializableConfig;

public class BitcoinProtocolScriptFieldSerializer
        implements AmatProtocolMessageFieldBaseSerializer<BitcoinProtocolScriptField<BitcoinProtocolScriptRO>,
                                                          BitcoinProtocolScriptField<BitcoinProtocolScriptRW>,
                                                          SerializableConfig>
{

    @Override
    public void serializeKernel(BitcoinProtocolScriptField<BitcoinProtocolScriptRW> scriptField,
                                OutputStream                                        out,
                                SerializableConfig                                  config) {
        scriptField.value().writeTo(out, scriptField.namespace());
    }

    @Override
    public void deserializeKernel(BitcoinProtocolScriptField<BitcoinProtocolScriptRO> scriptField,
                                  InputStream                                         in,
                                  SerializableConfig                                  config)
    {
        BitcoinProtocolScriptRO script = BitcoinProtocolScriptRO.readFrom(in, scriptField.namespace());
        scriptField.commissionWithValue(script);
    }

}
