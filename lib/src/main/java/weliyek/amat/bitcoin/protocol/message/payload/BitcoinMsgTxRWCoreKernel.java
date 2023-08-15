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

import weliyek.amat.bitcoin.protocol.block.BitcoinProtocolTxInRO;
import weliyek.amat.bitcoin.protocol.block.BitcoinProtocolTxInRW;
import weliyek.amat.bitcoin.protocol.block.BitcoinProtocolTxOutRO;
import weliyek.amat.bitcoin.protocol.block.BitcoinProtocolTxOutRW;
import weliyek.amat.bitcoin.protocol.message.BitcoinMsgRWBody;
import weliyek.amat.bitcoin.protocol.message.MessagePayloadHandlerRO;
import weliyek.amat.bitcoin.protocol.message.MessageRWArgs;

public class BitcoinMsgTxRWCoreKernel
        extends BitcoinMsgTxKernelAbstract<BitcoinProtocolTxInRW,
                                           BitcoinProtocolTxOutRW,
                                           BitcoinMsgTxRWCoreKernel>
        implements BitcoinMsgTxRW,
                   PayloadCoreKernelRW<BitcoinMsgTxRWCoreKernel>
{

    @Override
    public void commissionPayloadKernelWithArgs(BitcoinMsgRWBody owner, MessageRWArgs args) {
        setOwnerCoreOnCommission(owner);
        resetFields();
    }

    @Override
    public void commissionPayloadKernelWithRO(BitcoinMsgRWBody owner, MessagePayloadHandlerRO roHandler) {
        setOwnerCoreOnCommission(owner);
        resetFields();
        final BitcoinMsgTxROCoreKernel txRO = roHandler.castPayloadAsTx();
        setVersion(txRO.version());
        setLocktime(txRO.locktime());
        for (BitcoinProtocolTxInRO txInRO : txRO.txInList) {
            final BitcoinProtocolTxInRW txInRW = new BitcoinProtocolTxInRW(txInRO);
            this.txInList.add(txInRW);
        }
        for (BitcoinProtocolTxOutRO txOutRO : txRO.txOutList) {
            final BitcoinProtocolTxOutRW txOutRW = new BitcoinProtocolTxOutRW(txOutRO);
            this.txOutList.add(txOutRW);
        }
    }

    @Override
    public List<BitcoinProtocolTxInRW> txInList() {
        return this.txInList;
    }

    @Override
    public List<BitcoinProtocolTxOutRW> txOutList() {
        return this.txOutList;
    }

    @Override
    public void setVersion(int ver) {
        super.setVersion(ver);
    }

    @Override
    public void setLocktime(long val) {
        super.setLocktime(val);
    }

    @Override
    public BitcoinMsgTxRWCoreKernel getBody() {
        return this;
    }

}
