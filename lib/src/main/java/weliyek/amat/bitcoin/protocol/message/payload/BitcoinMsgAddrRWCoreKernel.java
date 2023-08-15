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

import java.util.ArrayList;
import java.util.List;

import weliyek.amat.bitcoin.protocol.BitcoinNetAddr;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;

public class BitcoinMsgAddrRWCoreKernel
        extends BitcoinMsgAddrCoreKernelAbstract<BitcoinMsgRWBody, BitcoinMsgAddrRWCoreKernel>
        implements BitcoinMsgAddrRW,
                   PayloadCoreKernelRW<BitcoinMsgAddrROCoreKernel>

{

    private final List<BitcoinNetAddr> list = new ArrayList<>();

    public BitcoinMsgAddrRWCoreKernel(BitcoinMsgAddrRWCoreKernelCommissioner c) {
        super(c);
    }

    @Override
    public void commissionWithRWArgs(BitcoinMsgRWBody owner, MessageRWArgs args) {
        commonPayloadKernelCommissionSteps(owner);
    }

    @Override
    public void commissionWithROKernel(BitcoinMsgRWBody owner, BitcoinMsgAddrROCoreKernel roKernel) {
        commonPayloadKernelCommissionSteps(owner);
        modifiableList().addAll(roKernel.publicList());
    }

    @Override
    protected final List<BitcoinNetAddr> modifiableList() {
        return list;
    }

    @Override
    protected final List<BitcoinNetAddr> publicList() {
        return list;
    }

    @Override
    protected BitcoinMsgAddrRWCoreKernel getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgAddrRWCoreKernel getThis() {
        return this;
    }

}
