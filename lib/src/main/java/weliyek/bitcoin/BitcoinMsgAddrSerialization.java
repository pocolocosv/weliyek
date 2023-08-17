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

public class BitcoinMsgAddrSerialization
        extends PayloadKernelSerializerAbstract<BitcoinMsgAddrROCoreKernel,
                                                BitcoinMsgAddrRWCoreKernel>
{

    @Override
    protected void serializePayloadKernel(BitcoinMsgAddrRWCoreKernel rwKernel,
                                                    BitcoinConfig config,
                                                        OutputStream out,
                                               AmatNamespace payloadNamespace)
    {
        final AmatNamespace addrNmspc = BitcoinProtocolName.ADDR.appendTo(payloadNamespace);
        final AmatNamespace listSizeNmspc = BitcoinProtocolName.ADDR_NETADDR_LIST_SIZE.appendTo(addrNmspc);
        BitcoinProtocolIOVarInt.from(rwKernel.size()).writeTo(out, listSizeNmspc);
        final AmatNamespace listElementNmspc = BitcoinProtocolName.ADDR_NETADDR_LIST_ELEMENT.appendTo(addrNmspc);
        listElementNmspc.enableCounter(rwKernel.size());
        for (BitcoinNetAddr netAddr : rwKernel) {
            netAddr.writeTo(config.version(),
                            WkBitcoinCommandName.ADDR,
                            out,
                            listElementNmspc);
            listElementNmspc.increaseCount();
        }
    }


    @Override
    public void deserializePayloadKernel(               InputStream in,
                                              AmatNamespace payloadNamespace,
                                                   BitcoinConfig config,
                                         BitcoinMsgAddrROCoreKernel roKernel)
    {
        final AmatNamespace addrNmspc = BitcoinProtocolName.ADDR.appendTo(payloadNamespace);
        final AmatNamespace listSizeNmspc = BitcoinProtocolName.ADDR_NETADDR_LIST_SIZE.appendTo(addrNmspc);
        int listSize = BitcoinProtocolIOVarInt.readFrom(in, listSizeNmspc).intValue();
        final AmatNamespace listElementNmspc = BitcoinProtocolName.ADDR_NETADDR_LIST_ELEMENT.appendTo(addrNmspc);
        listElementNmspc.enableCounter(listSize);
        for(int i = 0; i < listSize; i++) {
            BitcoinNetAddr netAddr = BitcoinNetAddr.readFrom(config.version(),
                                               WkBitcoinCommandName.ADDR,
                                               in,
                                               listElementNmspc);
            roKernel.modifiableList().add(netAddr);
            listElementNmspc.increaseCount();
        }
    }

}
