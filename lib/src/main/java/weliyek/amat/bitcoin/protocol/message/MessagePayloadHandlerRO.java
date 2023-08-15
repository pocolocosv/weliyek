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

import java.io.InputStream;

import weliyek.amat.bitcoin.protocol.BitcoinProtocolMessageConfig;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgAddrRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgAddrROCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetBlocksRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetDataRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetDataROCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgHeadersRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgHeadersROCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgNotFoundRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgNotFoundROKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgRejectRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgTxRO;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgTxROCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgVersionCoreKernel;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgVersionRO;
import weliyek.amat.bitcoin.protocol.message.payload.PayloadCoreKernelRO;
import weliyek.amat.bitcoin.protocol.message.payload.PayloadCoreKernelRW;
import weliyek.amat.bitcoin.protocol.message.payload.PayloadCoreKernelTools;

public class MessagePayloadHandlerRO
        extends MessagePayloadHandler<BitcoinMsgROBody,
                                      BitcoinMsgVersionRO,
                                      BitcoinMsgAddrRO,
                                      BitcoinMsgGetDataRO,
                                      BitcoinMsgTxRO,
                                      BitcoinMsgHeadersRO,
                                      BitcoinMsgNotFoundRO,
                                      BitcoinMsgGetBlocksRO,
                                      BitcoinMsgRejectRO>
        implements MessagePayloadRO
{

    MessagePayloadHandlerRO(BitcoinMsgROBody owner, BitcoinMsgCommissioners tools) {
        super(owner, tools);
    }

    public void commissionPayloadKernel(InputStream in, BitcoinProtocolMessageConfig config) {
        final PayloadCoreKernelTools<? extends PayloadCoreKernelRO<?>, ? extends PayloadCoreKernelRW<?>>
            kernelTools = tools.select(owner.command());
        final PayloadCoreKernelRO<?> roKernel = kernelTools.commissionPayloadKernelRO(owner, in, config);
        setKernel(roKernel);
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
    public BitcoinMsgAddrROCoreKernel castPayloadAsAddr() {
        return (BitcoinMsgAddrROCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgGetDataROCoreKernel castPayloadAsGetData() {
        return (BitcoinMsgGetDataROCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgTxROCoreKernel castPayloadAsTx() {
        return (BitcoinMsgTxROCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgHeadersROCoreKernel castPayloadAsHeaders() {
        return (BitcoinMsgHeadersROCoreKernel) this.kernel();
    }

    @Override
    public BitcoinMsgNotFoundROKernel castPayloadAsNotFound() {
        return (BitcoinMsgNotFoundROKernel) this.kernel();
    }

    @Override
    public BitcoinMsgGetBlocksRO castPayloadAsGetBlocks() {
        return (BitcoinMsgGetBlocksRO) this.kernel();
    }

    @Override
    public BitcoinMsgRejectRO castPayloadAsReject() {
        return (BitcoinMsgRejectRO) this.kernel();
    }

}
