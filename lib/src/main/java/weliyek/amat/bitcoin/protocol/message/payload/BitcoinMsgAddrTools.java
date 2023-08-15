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
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgROBody;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;
import weliyek.ketza.factory.Possible;

public class BitcoinMsgAddrTools implements PayloadCoreKernelTools<BitcoinMsgAddrROCoreKernel, BitcoinMsgAddrRWCoreKernel>
{

    final BitcoinMsgAddrROCoreKernelCommissioner roCommissioner = new BitcoinMsgAddrROCoreKernelCommissioner();

    final BitcoinMsgAddrRWCoreKernelCommissioner rwCommissioner = new BitcoinMsgAddrRWCoreKernelCommissioner();

    final BitcoinMsgAddrSerialization serializer = new BitcoinMsgAddrSerialization();

    @Override
    public Possible<BitcoinMsgAddrROCoreKernel> commissionROKernel(BitcoinMsgROBody owner) {
        return roCommissioner.commission(owner);
    }

    @Override
    public Possible<BitcoinMsgAddrRWCoreKernel> commissionRWKernelWithRWArgs(BitcoinMsgRWBody owner,
                                                                             MessageRWArgs args) {
        return rwCommissioner.commissionWithRWArgs(owner, args);
    }

    @Override
    public Possible<BitcoinMsgAddrRWCoreKernel> commissionRWKernelWithROKernel(BitcoinMsgRWBody owner,
                                                                               PayloadCoreKernelRO roKernel) {
        return rwCommissioner.commissionWithROKernel(owner, (BitcoinMsgAddrROCoreKernel)roKernel);
    }

    @Override
    public void serializeKernel(PayloadCoreKernelRW<?> rwKernel, BitcoinConfig config, OutputStream out, AmatNamespace payloadNamespace) {
        serializer.serializeKernel((BitcoinMsgAddrRWCoreKernel) rwKernel, config, out, payloadNamespace);
    }

    @Override
    public void deserializeKernel(InputStream in, BitcoinConfig config, PayloadCoreKernelRO roKernel, AmatNamespace payloadNamespace) {
        serializer.deserializeKernel(in, config, (BitcoinMsgAddrROCoreKernel) roKernel, payloadNamespace);
    }

}
