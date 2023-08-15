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

public class PayloadCoreKernelRWCommissionerRWKernel
{

    public static <OK extends PayloadCoreKernelRW<?>> OK commissionPayloadKernelWithArgs(Factory<OK> factory,
                                                                                         BitcoinMsgRWBody owner,
                                                                                         MessageRWArgs args)
    {
        final OK rwKernel = factory.build();
        rwKernel.commissionPayloadKernelWithArgs(owner, args);
        return rwKernel;
    }

    public static <OK extends PayloadCoreKernelRW<?>> OK commissionPayloadKernelWithRO(Factory<OK> factory,
                                                                                       BitcoinMsgRWBody owner,
                                                                                       MessagePayloadHandlerRO roHandler) {
        final OK rwKernel = factory.build();
        rwKernel.commissionPayloadKernelWithRO(owner, roHandler);
        return rwKernel;
    }

}
