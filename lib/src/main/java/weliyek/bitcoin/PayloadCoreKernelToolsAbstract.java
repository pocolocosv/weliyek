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

public abstract class PayloadCoreKernelToolsAbstract
                        <IK extends PayloadCoreKernelRO<IK>,
                         OK extends PayloadCoreKernelRW<OK>>
        implements PayloadCoreKernelTools<IK, OK>
{

    protected abstract PayloadKernelSerializer<IK, OK> serializer();

    protected abstract PayloadCoreKernelROCommissioner<IK> roCommissioner();

    protected abstract PayloadCoreKernelRWCommissioner<OK> rwCommissioner();

    @Override
    public PayloadCoreKernelRO<? extends PayloadCoreKernelRO<?>> commissionPayloadKernelRO(MessageUnpackedCore<?, ?, ?> owner,
                                                                                            InputStream                  in,
                                                                                            BitcoinProtocolMessageConfig config)
    {
        final IK roKernel = roCommissioner().commission(owner);
        serializer().deserializeKernel(roKernel, in, config);
        return roKernel;
    }

    @Override
    public PayloadCoreKernelRW<? extends PayloadCoreKernelRW<?>> commissionPayloadKernelWithArgs(BitcoinMsgRWBody owner,
                                                                                                 MessageRWArgs args) {
        return rwCommissioner().commissionPayloadKernelWithArgs(owner, args);
    }

    @Override
    public PayloadCoreKernelRW<? extends PayloadCoreKernelRW<?>> commissionPayloadKernelWithRO(BitcoinMsgRWBody owner,
                                                                                              MessagePayloadHandlerRO roHandler)
    {
        return rwCommissioner().commissionPayloadKernelWithRO(owner, roHandler);
    }

}
