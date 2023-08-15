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

public class BitcoinMsgHeadersSerialization
        extends PayloadKernelSerializerAbstract
                        <BitcoinMsgHeadersROCoreKernel,
                         BitcoinMsgHeadersRWCoreKernel>
{

    @Override
    protected void serializePayloadKernel(BitcoinMsgHeadersRWCoreKernel rwKernel,
                                                       BitcoinConfig config,
                                                           OutputStream out,
                                                  AmatNamespace payloadNamespace) {
        final AmatNamespace headersNmspc = BitcoinProtocolName.HEADERS.appendTo(payloadNamespace);
        writeListSize(rwKernel, out, headersNmspc);
        writeListElements(rwKernel, out, headersNmspc);
    }

    private void writeListSize(BitcoinMsgHeadersRWCoreKernel rwKernel, OutputStream out, AmatNamespace headersNmspc) {
        final AmatNamespace sizeNmspc = BitcoinProtocolName.HEADERS_LIST_SIZE.appendTo(headersNmspc);
        BitcoinProtocolIOVarInt.from(rwKernel.size()).writeTo(out, sizeNmspc);
    }

    private void writeListElements(BitcoinMsgHeadersRWCoreKernel rwKernel, OutputStream out, AmatNamespace headersNmspc) {
        final AmatNamespace elementNmspc = BitcoinProtocolName.HEADERS_LIST_ELEMENT.appendTo(headersNmspc);
        elementNmspc.enableCounter(rwKernel.size());
        for (BlockHeader blockHeader : rwKernel) {
            blockHeader.writeTo(out, elementNmspc);
            elementNmspc.increaseCount();
        }
    }

    @Override
    protected void deserializePayloadKernel(          InputStream in,
                                            AmatNamespace payloadNamespace,
                                                 BitcoinConfig config,
                                    BitcoinMsgHeadersROCoreKernel roKernel) {
        final AmatNamespace headersNmspc = BitcoinProtocolName.HEADERS.appendTo(payloadNamespace);
        int numElem = readListSize(in, roKernel, headersNmspc);
        readListElements(in, roKernel, numElem, headersNmspc);
    }

    private int readListSize(InputStream in, BitcoinMsgHeadersROCoreKernel roKernel, AmatNamespace headersNmspc) {
        final AmatNamespace sizeNmspc = BitcoinProtocolName.HEADERS_LIST_SIZE.appendTo(headersNmspc);
        int numElem = BitcoinProtocolIOVarInt.readFrom(in, sizeNmspc).intValue();
        return numElem;
    }

    private void readListElements(InputStream in, BitcoinMsgHeadersROCoreKernel roKernel, int numElem, AmatNamespace headersNmspc) {
        final AmatNamespace elementNmspc = BitcoinProtocolName.HEADERS_LIST_ELEMENT.appendTo(headersNmspc);
        elementNmspc.enableCounter(numElem);
        for (int i = 0; i < numElem; i++) {
            BlockHeader blockHeader = BlockHeader.readFrom(in, elementNmspc);
            roKernel.list.add(blockHeader);
            elementNmspc.increaseCount();
        }
    }

}
