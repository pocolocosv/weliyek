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
package weliyek.amat.bitcoin.protocol.message;

import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgAddrRW;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgAddrRWCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetBlocksRW;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetDataRW;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetDataRWCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgHeadersRW;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgHeadersRWCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgNotFoundRW;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgNotFoundRWKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgRejectRW;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgTxRW;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgTxRWCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgVersionCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgVersionRW;
import weliyek.amat.bitcoin.protocol.message.payload.PayloadCoreKernelRO;
import weliyek.amat.bitcoin.protocol.message.payload.PayloadCoreKernelRW;
import weliyek.amat.bitcoin.protocol.message.payload.PayloadCoreKernelTools;

public class MessagePayloadHandlerRW
        extends MessagePayloadHandler<BitcoinMsgRWBody,
                                      BitcoinMsgVersionRW,
                                      BitcoinMsgAddrRW,
                                      BitcoinMsgGetDataRW,
                                      BitcoinMsgTxRW,
                                      BitcoinMsgHeadersRW,
                                      BitcoinMsgNotFoundRW,
                                      BitcoinMsgGetBlocksRW,
                                      BitcoinMsgRejectRW>
        implements MessagePayloadRW
{

    MessagePayloadHandlerRW(BitcoinMsgRWBody owner, BitcoinMsgCommissioners tools) {
        super(owner, tools);
    }

    public void commissionWithRWArgs(MessageRWArgs args) {
        final PayloadCoreKernelTools<? extends PayloadCoreKernelRO<?>, ? extends PayloadCoreKernelRW<?>>
            kernelTools = tools.select(owner.command());
        if (null == kernelTools) {
            this.commissionWithoutKernel();
            return;
        }
        final PayloadCoreKernelRW<?> kernel = kernelTools.commissionPayloadKernelWithArgs(owner, args);
        setKernel(kernel);
    }

    public void commissionWithInvariantKernel(MessagePayloadHandlerRO roHandler) {
        final PayloadCoreKernelTools<? extends PayloadCoreKernelRO<?>, ? extends PayloadCoreKernelRW<?>>
            kernelTools = tools.select(owner.command());
        if (null == kernelTools) {
            this.commissionWithoutKernel();
            return;
        }
        final PayloadCoreKernelRW<?> kernel = kernelTools.commissionPayloadKernelWithRO(owner, roHandler);
        setKernel(kernel);
    }

    @Override
    public void finilizeCommission() {
        // Nothing to do.
    }

    @Override
    public BitcoinMsgVersionCoreKernel castPayloadAsVersion() {
        return (BitcoinMsgVersionCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgAddrRWCoreKernel castPayloadAsAddr() {
        return (BitcoinMsgAddrRWCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgGetDataRWCoreKernel castPayloadAsGetData() {
        return (BitcoinMsgGetDataRWCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgTxRWCoreKernel castPayloadAsTx() {
        return (BitcoinMsgTxRWCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgHeadersRWCoreKernel castPayloadAsHeaders() {
        return (BitcoinMsgHeadersRWCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgNotFoundRWKernel castPayloadAsNotFound() {
        return (BitcoinMsgNotFoundRWKernel) this.kernel();
    }

    @Override
    public BitcoinMsgGetBlocksRW castPayloadAsGetBlocks() {
        return (BitcoinMsgGetBlocksRW) this.kernel();
    }

    @Override
    public BitcoinMsgRejectRW castPayloadAsReject() {
        return (BitcoinMsgRejectRW) this.kernel();
    }

}
