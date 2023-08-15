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
package weliyek.amat.bitcoin.protocol.message.payload;

import java.io.InputStream;
import java.io.OutputStream;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.bitcoin.protocol.BitcoinConfig;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolName;

public class BitcoinMsgGetDataSerializer
        extends PayloadKernelSerializerAbstract<BitcoinMsgGetDataROCoreKernel,
                                                BitcoinMsgGetDataRWCoreKernel>
{

    @Override
    protected void serializePayloadKernel(BitcoinMsgGetDataRWCoreKernel rwKernel,
                                                       BitcoinConfig config,
                                                           OutputStream out,
                                                  AmatNamespace payloadNamespace)
    {
        final AmatNamespace getDataNmspc = BitcoinProtocolName.GETDATA.appendTo(payloadNamespace);
        rwKernel.list.writeTo(out, getDataNmspc);
    }

    @Override
    protected void deserializePayloadKernel(          InputStream in,
                                            AmatNamespace payloadNamespace,
                                                 BitcoinConfig config,
                                    BitcoinMsgGetDataROCoreKernel roKernel)
    {
        final AmatNamespace getDataNmspc = BitcoinProtocolName.GETDATA.appendTo(payloadNamespace);
        roKernel.list.readFrom(in, getDataNmspc);
    }

}
