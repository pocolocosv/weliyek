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

import weliyek.amat.bitcoin.protocol.BitcoinNetAddr;
import weliyek.amat.bitcoin.protocol.message.MessageUnpackedBody;

public abstract class BitcoinMsgAddrCoreKernelAbstract<B extends MessageUnpackedBody<?,?,?>,
                                                       K extends BitcoinMsgAddrCoreKernelAbstract<B,K>>
        extends PayloadListCoreKernelAbstract<BitcoinNetAddr, B, K>
        implements BitcoinMsgAddr
{

    public BitcoinMsgAddrCoreKernelAbstract(PayloadCoreKernelCommissionerBase<B,K> c) {
        super(c);
    }

    @Override
    public final boolean isAddr() {
        return true;
    }

    @Override
    protected final void onCommonPayloadListKernelCommissionSteps() {
        // Nothing to do.
    }

    @Override
    protected final void onPayloadListKernelDecommission() {
        // Nothing to do.
    }

}
