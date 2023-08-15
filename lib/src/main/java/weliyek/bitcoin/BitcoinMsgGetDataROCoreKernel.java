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

import java.util.Collections;
import java.util.List;

import weliyek.amat.bitcoin.protocol.InventoryVectorList;

public class BitcoinMsgGetDataROCoreKernel
        extends BitcoinMsgGetDataKernelAbstract<BitcoinMsgROBody,
                                                BitcoinMsgGetDataROCoreKernel>
        implements BitcoinMsgGetDataRO,
                   PayloadCoreKernelRO
{

    final InventoryVectorList list = new InventoryVectorList();

    final List<BitcoinInventoryVector> unmodifiableList = Collections.unmodifiableList(list);


    BitcoinMsgGetDataROCoreKernel(BitcoinMsgGetDataROCoreKernelCommissioner c) {
        super(c);
    }

    @Override
    protected BitcoinMsgGetDataROCoreKernel getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgGetDataROCoreKernel getThis() {
        return this;
    }

    @Override
    public void commission(BitcoinMsgROBody owner) {
        commonPayloadKernelCommissionSteps(owner);
    }

    @Override
    protected List<BitcoinInventoryVector> modifiableList() {
        return list;
    }

    @Override
    protected List<BitcoinInventoryVector> publicList() {
        return unmodifiableList;
    }

}
