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

import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;
import weliyek.bitcoin.BitcoinHash;

public class BitcoinMsgGetBlocksKernelRW
        extends BitcoinMsgGetBlocksKernelAbstract<BitcoinMsgRWBody, BitcoinMsgGetBlocksKernelRW>
        implements BitcoinMsgGetBlocksRW, PayloadCoreKernelRW<BitcoinMsgGetBlocksKernelRO>
{

    protected List<BitcoinHash> list = new ArrayList<>();

    public BitcoinMsgGetBlocksKernelRW(PayloadCoreKernelCommissioner<BitcoinMsgRWBody, BitcoinMsgGetBlocksKernelRW> c) {
        super(c);
    }

    @Override
    public void commissionWithRWArgs(BitcoinMsgRWBody owner, MessageRWArgs args) {
        commonPayloadKernelCommissionSteps(owner);
    }

    @Override
    public void commissionWithROKernel(BitcoinMsgRWBody owner, BitcoinMsgGetBlocksKernelRO roKernel) {
        commonPayloadKernelCommissionSteps(owner);
        this.version = roKernel.version();
        this.list.addAll(roKernel.list);
        this.stopHash = roKernel.stopHash();
    }

    @Override
    public void setVersion(int v) {
        this.version = v;
    }

    @Override
    public void setStopHash(BitcoinHash h) {
        if (null == h) {
            throw new IllegalArgumentException();
        }
        this.stopHash = h;
    }

    @Override
    protected List<BitcoinHash> modifiableHeaderHashList() {
        return list;
    }

    @Override
    protected List<BitcoinHash> publicHeaderHashList() {
        return list;
    }

    @Override
    protected BitcoinMsgGetBlocksKernelRW getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgGetBlocksKernelRW getThis() {
        return this;
    }

}
