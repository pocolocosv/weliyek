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

import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.MessagePayloadHandlerRO;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;
import weliyek.amat.bitcoin.protocol.message.MessageUnpackedCore;

public class BitcoinMsgVersionCoreKernelCommissioner
        extends PayloadCoreKernelCommissionerBase<BitcoinMsgVersionCoreKernel>
        implements PayloadCoreKernelROCommissioner<BitcoinMsgVersionCoreKernel>,
                   PayloadCoreKernelRWCommissioner<BitcoinMsgVersionCoreKernel>
{

    public BitcoinMsgVersionCoreKernelCommissioner(PayloadFactory<BitcoinMsgVersionCoreKernel> factory) {
        super(factory);
    }

    @Override
    public BitcoinMsgVersionCoreKernel commissionPayloadKernelWithArgs(BitcoinMsgRWBody owner,
                                                                       MessageRWArgs args)
    {
        return PayloadCoreKernelRWCommissionerRWKernel.commissionPayloadKernelWithArgs(factory(), owner, args);
    }

    @Override
    public BitcoinMsgVersionCoreKernel commissionPayloadKernelWithRO(BitcoinMsgRWBody owner,
                                                                     MessagePayloadHandlerRO roHandler)
    {
        return PayloadCoreKernelRWCommissionerRWKernel.commissionPayloadKernelWithRO(factory(), owner, roHandler);
    }

    @Override
    public BitcoinMsgVersionCoreKernel commission(MessageUnpackedCore<?, ?, ?> ownerCore) {
        return PayloadCoreKernelROCommissionerROKernel.commission(factory(), ownerCore);
    }

}
