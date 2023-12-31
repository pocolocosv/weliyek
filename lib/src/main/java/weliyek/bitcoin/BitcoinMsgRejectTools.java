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

public class BitcoinMsgRejectTools
        implements PayloadCoreKernelTools<BitcoinMsgRejectROKernel,
                                          BitcoinMsgRejectRWKernel>
{

    final BitcoinMsgRejectROKernelCommissioner roCommissioner = new BitcoinMsgRejectROKernelCommissioner();

    final BitcoinMsgRejectRWKernelCommissioner rwCommissioner = new BitcoinMsgRejectRWKernelCommissioner();

    final BitcoinMsgRejectSerializer serializer = new BitcoinMsgRejectSerializer();

    @Override
    public void serializeKernel(final PayloadCoreKernelRW<?> rwKernel,
                                final BitcoinConfig config,
                                final OutputStream out,
                                final AmatNamespace payloadNamespace) {
        serializer.serializeKernel((BitcoinMsgRejectRWKernel) rwKernel, config, out, payloadNamespace);
    }

    @Override
    public void deserializeKernel(final InputStream in,
                                  final BitcoinConfig config,
                                  final PayloadCoreKernelRO roKernel,
                                  final AmatNamespace payloadNamespace) {
        serializer.deserializeKernel(in, config, (BitcoinMsgRejectROKernel) roKernel, payloadNamespace);
    }

    @Override
    public Possible<BitcoinMsgRejectROKernel> commissionROKernel(final BitcoinMsgROBody owner) {
        return roCommissioner.commission(owner);
    }

    @Override
    public Possible<BitcoinMsgRejectRWKernel> commissionRWKernelWithRWArgs(final BitcoinMsgRWBody owner,
                                                                           final MessageRWArgs args) {
        return rwCommissioner.commissionWithRWArgs(owner, args);
    }

    @Override
    public Possible<BitcoinMsgRejectRWKernel> commissionRWKernelWithROKernel(final BitcoinMsgRWBody owner,
                                                                             final PayloadCoreKernelRO roKernel) {
        return rwCommissioner.commissionWithROKernel(owner, (BitcoinMsgRejectROKernel) roKernel);
    }

}
