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

public class BitcoinMsgGetDataTools
        implements PayloadCoreKernelTools<BitcoinMsgGetDataROCoreKernel, BitcoinMsgGetDataRWCoreKernel>
{

    final BitcoinMsgGetDataROCoreKernelCommissioner roCommissioner = new BitcoinMsgGetDataROCoreKernelCommissioner();

    final BitcoinMsgGetDataRWCoreKernelCommissioner rwCommissioner = new BitcoinMsgGetDataRWCoreKernelCommissioner();

    final BitcoinMsgGetDataSerializer serializer = new BitcoinMsgGetDataSerializer();

    @Override
    public void serializeKernel(PayloadCoreKernelRW<?> rwKernel, BitcoinConfig config, OutputStream out, AmatNamespace payloadNamespace) {
        serializer.serializeKernel((BitcoinMsgGetDataRWCoreKernel) rwKernel, config, out, payloadNamespace);
    }

    @Override
    public void deserializeKernel(InputStream in, BitcoinConfig config, PayloadCoreKernelRO roKernel, AmatNamespace payloadNamespace) {
        serializer.deserializeKernel(in, config, (BitcoinMsgGetDataROCoreKernel) roKernel, payloadNamespace);
    }

    @Override
    public Possible<BitcoinMsgGetDataROCoreKernel> commissionROKernel(BitcoinMsgROBody owner) {
        return roCommissioner.commission(owner);
    }

    @Override
    public Possible<BitcoinMsgGetDataRWCoreKernel> commissionRWKernelWithRWArgs(BitcoinMsgRWBody owner,
                                                                                MessageRWArgs args) {
        return rwCommissioner.commissionWithRWArgs(owner, args);
    }

    @Override
    public Possible<BitcoinMsgGetDataRWCoreKernel> commissionRWKernelWithROKernel(BitcoinMsgRWBody owner,
                                                                                  PayloadCoreKernelRO roKernel) {
        return rwCommissioner.commissionWithROKernel(owner, (BitcoinMsgGetDataROCoreKernel) roKernel);
    }

}
