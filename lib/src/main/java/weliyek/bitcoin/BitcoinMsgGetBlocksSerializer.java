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
import weliyek.amat.base.protocol.base.AmatProtocolUtil;

public class BitcoinMsgGetBlocksSerializer
        extends PayloadKernelSerializerAbstract<BitcoinMsgGetBlocksKernelRO,
                                                BitcoinMsgGetBlocksKernelRW>
{

    @Override
    protected void serializePayloadKernel(BitcoinMsgGetBlocksKernelRW rwKernel,
                                          BitcoinConfig config,
                                          OutputStream out,
                                          AmatNamespace payloadNamespace)
    {
        final AmatNamespace getBlocksNmspc = BitcoinProtocolName.GETBLOCKS.appendTo(payloadNamespace);
        final AmatNamespace versionNmspc = BitcoinProtocolName.GETBLOCKS_VERSION.appendTo(getBlocksNmspc);
        AmatProtocolUtil.writeIntAsLittleEndian(out, rwKernel.version(), versionNmspc);
        final AmatNamespace listNmspc = BitcoinProtocolName.GETBLOCKS_HEADER_HASH_LIST.appendTo(getBlocksNmspc);
        final AmatNamespace listSizeNmspc = BitcoinProtocolName.GETBLOCKS_HEADER_HASH_LIST_SIZE.appendTo(listNmspc);
        BitcoinProtocolIOVarInt.from(rwKernel.list.size()).writeTo(out, listSizeNmspc);
        final AmatNamespace listElementNmspc = BitcoinProtocolName.GETBLOCKS_HEADER_HASH_LIST_ELEMENT.appendTo(listNmspc);
        listElementNmspc.enableCounter(rwKernel.headerHashes().size());
        for (WkBitcoinHash hash : rwKernel.headerHashes()) {
            hash.writeTo(out, listElementNmspc);
            listElementNmspc.increaseCount();
        }
        final AmatNamespace stopHashNmspc = BitcoinProtocolName.GETBLOCKS_STOP_HASH.appendTo(listNmspc);
        rwKernel.stopHash().writeTo(out, stopHashNmspc);
    }

    @Override
    protected void deserializePayloadKernel(InputStream in,
                                            AmatNamespace payloadNamespace,
                                            BitcoinConfig config,
                                            BitcoinMsgGetBlocksKernelRO roKernel)
    {
        final AmatNamespace getBlocksNmspc = BitcoinProtocolName.GETBLOCKS.appendTo(payloadNamespace);
        final AmatNamespace versionNmspc = BitcoinProtocolName.GETBLOCKS_VERSION.appendTo(getBlocksNmspc);
        roKernel.version = AmatProtocolUtil.readLittleEndianInt(in, versionNmspc);
        final AmatNamespace listNmspc = BitcoinProtocolName.GETBLOCKS_HEADER_HASH_LIST.appendTo(getBlocksNmspc);
        final AmatNamespace listSizeNmspc = BitcoinProtocolName.GETBLOCKS_HEADER_HASH_LIST_SIZE.appendTo(listNmspc);
        final int elemNum = BitcoinProtocolIOVarInt.readFrom(in, listSizeNmspc).intValue();
        final AmatNamespace listElementNmspc = BitcoinProtocolName.GETBLOCKS_HEADER_HASH_LIST_ELEMENT.appendTo(listNmspc);
        listElementNmspc.enableCounter(elemNum);
        for(int i = 0; i < elemNum; i++) {
            final WkBitcoinHash hash = WkBitcoinHash.readFrom(in, listElementNmspc);
            roKernel.modifiableHeaderHashList().add(hash);
            listElementNmspc.increaseCount();
        }
        final AmatNamespace stopHashNmspc = BitcoinProtocolName.GETBLOCKS_STOP_HASH.appendTo(listNmspc);
        roKernel.stopHash = WkBitcoinHash.readFrom(in, stopHashNmspc);
    }

}
