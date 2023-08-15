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

import java.util.List;

import weliyek.amat.bitcoin.protocol.InventoryVectorList;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;
import weliyek.bitcoin.BitcoinInventoryVector;

public class BitcoinMsgGetDataRWCoreKernel
        extends BitcoinMsgGetDataKernelAbstract<BitcoinMsgRWBody,
                                                BitcoinMsgGetDataRWCoreKernel>
        implements BitcoinMsgGetDataRW,
                   PayloadCoreKernelRW<BitcoinMsgGetDataROCoreKernel>
{

    final InventoryVectorList list = new InventoryVectorList();

    BitcoinMsgGetDataRWCoreKernel(BitcoinMsgGetDataRWCoreKernelCommissioner c) {
        super(c);
    }

    @Override
    protected BitcoinMsgGetDataRWCoreKernel getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgGetDataRWCoreKernel getThis() {
        return this;
    }

    @Override
    public void commissionWithRWArgs(BitcoinMsgRWBody owner, MessageRWArgs args) {
        commonPayloadKernelCommissionSteps(owner);
    }

    @Override
    public void commissionWithROKernel(BitcoinMsgRWBody owner, BitcoinMsgGetDataROCoreKernel roKernel) {
        commonPayloadKernelCommissionSteps(owner);
        list.addAll(roKernel);
    }

    @Override
    protected List<BitcoinInventoryVector> modifiableList() {
        return list;
    }

    @Override
    protected List<BitcoinInventoryVector> publicList() {
        return list;
    }

}
