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
import weliyek.ketza.factory.Possible;

public class BitcoinMsgGetBlocksTools
        implements PayloadCoreKernelTools<BitcoinMsgGetBlocksKernelRO,
                                          BitcoinMsgGetBlocksKernelRW>
{

    final BitcoinMsgGetBlocksKernelROCommissioner roCommissioner = new BitcoinMsgGetBlocksKernelROCommissioner();

    final BitcoinMsgGetBlocksKernelRWCommissioner rwCommissioner = new BitcoinMsgGetBlocksKernelRWCommissioner();

    final BitcoinMsgGetBlocksSerializer serializer = new BitcoinMsgGetBlocksSerializer();

    @Override
    public void serializeKernel(PayloadCoreKernelRW<?> rwKernel,
                                BitcoinConfig config,
                                OutputStream out,
                                AmatNamespace payloadNamespace) {
        serializer.serializeKernel((BitcoinMsgGetBlocksKernelRW) rwKernel, config, out, payloadNamespace);
    }

    @Override
    public void deserializeKernel(InputStream in,
                                  BitcoinConfig config,
                                  PayloadCoreKernelRO roKernel,
                                  AmatNamespace payloadNamespace) {
        serializer.deserializeKernel(in, config, (BitcoinMsgGetBlocksKernelRO) roKernel, payloadNamespace);
    }

    @Override
    public Possible<BitcoinMsgGetBlocksKernelRO> commissionROKernel(BitcoinMsgROBody owner) {
        return roCommissioner.commission(owner);
    }

    @Override
    public Possible<BitcoinMsgGetBlocksKernelRW> commissionRWKernelWithRWArgs(BitcoinMsgRWBody owner,
                                                                              MessageRWArgs args) {
        return rwCommissioner.commissionWithRWArgs(owner, args);
    }

    @Override
    public Possible<BitcoinMsgGetBlocksKernelRW> commissionRWKernelWithROKernel(BitcoinMsgRWBody owner,
                                                                                PayloadCoreKernelRO roKernel) {
        return rwCommissioner.commissionWithROKernel(owner, (BitcoinMsgGetBlocksKernelRO) roKernel);
    }

}
