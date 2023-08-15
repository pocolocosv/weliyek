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

import weliyek.amat.base.namespace.AmatNamespace;

public class BitcoinMsgNotFoundSerializer
        extends PayloadKernelSerializerAbstract<BitcoinMsgNotFoundROKernel,
                                                BitcoinMsgNotFoundRWKernel>
{

    @Override
    protected void serializePayloadKernel(BitcoinMsgNotFoundRWKernel rwKernel,
                                          BitcoinConfig config,
                                          OutputStream out,
                                          AmatNamespace payloadNamespace) {
        final AmatNamespace notFoundNmspc = BitcoinProtocolName.NOTFOUND.appendTo(payloadNamespace);
        rwKernel.list.writeTo(out, notFoundNmspc);
    }

    @Override
    protected void deserializePayloadKernel(InputStream in,
                                            AmatNamespace payloadNamespace,
                                            BitcoinConfig config,
                                            BitcoinMsgNotFoundROKernel roKernel) {
        final AmatNamespace notFoundNmspc = BitcoinProtocolName.NOTFOUND.appendTo(payloadNamespace);
        roKernel.list.readFrom(in, notFoundNmspc);
    }

}
