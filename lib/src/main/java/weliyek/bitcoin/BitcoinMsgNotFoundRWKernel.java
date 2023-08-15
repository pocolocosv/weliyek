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

import java.util.List;

import weliyek.amat.bitcoin.protocol.InventoryVectorList;

public class BitcoinMsgNotFoundRWKernel
        extends BitcoinMsgNotFoundKernelAbstract<BitcoinMsgRWBody,
                                                 BitcoinMsgNotFoundRWKernel>
        implements BitcoinMsgNotFoundRW,
                    PayloadCoreKernelRW<BitcoinMsgNotFoundROKernel>
{

    final InventoryVectorList list = new InventoryVectorList();

    public BitcoinMsgNotFoundRWKernel(BitcoinMsgNotFoundRWKernelCommissioner c) {
        super(c);
    }

    @Override
    public void commissionWithRWArgs(BitcoinMsgRWBody owner, MessageRWArgs args) {
        commonPayloadKernelCommissionSteps(owner);
    }

    @Override
    public void commissionWithROKernel(BitcoinMsgRWBody owner, BitcoinMsgNotFoundROKernel roKernel) {
        commonPayloadKernelCommissionSteps(owner);
        this.list.addAll(roKernel);
    }

    @Override
    protected List<BitcoinInventoryVector> modifiableList() {
        return list;
    }

    @Override
    protected List<BitcoinInventoryVector> publicList() {
        return list;
    }

    @Override
    protected BitcoinMsgNotFoundRWKernel getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgNotFoundRWKernel getThis() {
        return this;
    }

}
