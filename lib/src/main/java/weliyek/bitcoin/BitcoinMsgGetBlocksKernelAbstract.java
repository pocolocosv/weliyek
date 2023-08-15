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

import weliyek.amat.bitcoin.protocol.message.MessageUnpackedBody;

public abstract class BitcoinMsgGetBlocksKernelAbstract
                        <XB extends MessageUnpackedBody<?,?,?>,
                         XK extends BitcoinMsgGetBlocksKernelAbstract<XB,XK>>
        extends PayloadCoreKernelAbstract<XB, XK>
        implements BitcoinMsgGetBlocks
{

    int version;

    BitcoinHash stopHash;

    protected abstract List<BitcoinHash> modifiableHeaderHashList();

    protected abstract List<BitcoinHash> publicHeaderHashList();

    public BitcoinMsgGetBlocksKernelAbstract(PayloadCoreKernelCommissioner<XB, XK> c) {
        super(c);
    }

    @Override
    protected void onCommonPayloadKernelCommissionSteps() {
        modifiableHeaderHashList().clear();
    }

    @Override
    protected void onPayloadKernelDecommission() {
        modifiableHeaderHashList().clear();
    }

    @Override
    public boolean isGetBlocks() {
        return true;
    }

    @Override
    public int version() {
        return version;
    }

    @Override
    public BitcoinHash stopHash() {
        return stopHash;
    }

    @Override
    public List<BitcoinHash> headerHashes() {
        return publicHeaderHashList();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((stopHash == null) ? 0 : stopHash.hashCode());
        result = prime * result + version;
        result = prime * result + publicHeaderHashList().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if ( ! (obj instanceof BitcoinMsgGetBlocksKernelAbstract))
            return false;
        BitcoinMsgGetBlocksKernelAbstract<?,?> other = (BitcoinMsgGetBlocksKernelAbstract<?,?>) obj;
        if (stopHash == null) {
            if (other.stopHash != null)
                return false;
        } else if (!stopHash.equals(other.stopHash))
            return false;
        if (version != other.version)
            return false;
        if ( ! publicHeaderHashList().equals(other.publicHeaderHashList()))
            return false;
        return true;
    }

}
