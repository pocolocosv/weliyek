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

import java.util.Objects;
import java.util.Optional;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.ketza.bitstreamable.scion.duo.SecondDuoScionCoreAbstract;

public class BitcoinMsgRWBody
        extends SecondDuoScionCoreAbstract<MessageRWArgs,
                                           BitcoinMsgROBody,
                                           BitcoinMsgRW,
                                           BitcoinMsgRWBody,
                                           BitcoinMsgStream,
                                           BitcoinProtocolMessageConfig>
        implements MessageUnpackedCore<BitcoinMsgROBody,
                                       BitcoinMsgRW,
                                       BitcoinMsgStream>,
                   BitcoinMsgRW
{

    final AmatNamespace namespace;

    BitcoinMsgRWBody(BitcoinMsgStreamCommissioner bitstreamCommissioner,
                     AmatNamespace namespace)
    {
        super(bitstreamCommissioner);
        this.namespace = Objects.requireNonNull(namespace);
        this.commonKernel = new MessageCommonCoreKernelBase(this);
        this.payloadHandler = new MessagePayloadHandlerRW(this);
    }


    /*
     * MessageRO and MessageRW common
     * #######################################################################
     */

    final MessageCommonCoreKernelBase commonKernel;

    final MessagePayloadHandlerRW payloadHandler;

    @Override
    public BitcoinMessageMagic magic() {
        return commonKernel.magic();
    }

    @Override
    public WkBitcoinCommand command() {
        return commonKernel.command();
    }

    @Override
    public boolean isVersion() {
        return payloadHandler.isVersion();
    }

    @Override
    public Optional<BitcoinMsgVersionRW> asVersion() {
        return payloadHandler.asVersion();
    }

    @Override
    public boolean isAddr() {
        return payloadHandler.isAddr();
    }

    @Override
    public Optional<BitcoinMsgAddrRW> asAddr() {
        return payloadHandler.asAddr();
    }

    @Override
    public boolean isGetData() {
        return payloadHandler.isGetData();
    }

    @Override
    public Optional<BitcoinMsgGetDataRW> asGetData() {
        return payloadHandler.asGetData();
    }

    @Override
    public boolean isTx() {
        return payloadHandler.isTx();
    }

    @Override
    public Optional<BitcoinMsgTxRW> asTx() {
        return payloadHandler.asTx();
    }

    @Override
    public boolean isHeaders() {
        return payloadHandler.isHeaders();
    }

    @Override
    public Optional<BitcoinMsgHeadersRW> asHeaders() {
        return payloadHandler.asHeaders();
    }

    @Override
    public boolean isNotFound() {
        return payloadHandler.isNotFound();
    }

    @Override
    public Optional<BitcoinMsgNotFoundRW> asNotFound() {
        return payloadHandler.asNotFound();
    }

    @Override
    public boolean isGetBlocks() {
        return payloadHandler.isGetBlocks();
    }

    @Override
    public Optional<BitcoinMsgGetBlocksRW> asGetBlocks() {
        return payloadHandler.asGetBlocks();
    }

    @Override
    public boolean isReject() {
        return payloadHandler.isReject();
    }

    @Override
    public Optional<BitcoinMsgRejectRW> asReject() {
        return payloadHandler.asReject();
    }

    @Override
    public BitcoinMsgStream toBitstream() {
        return spawningKernel.spawnDescendant();
    }

    @Override
    public AmatNamespace namespace() {
        return namespace;
    }

    /*
     * Commissioning
     * #######################################################################
     */

    @Override
    protected void onSecondDuoScionCommission(MessageRWArgs args) {
        commonKernel.commissionWithMagicAndCommand(args.magic, args.command);
        payloadHandler.commissionWithRWArgs(args);
    }

    @Override
    protected void onSecondDuoScionCommissionWithFirstScion(BitcoinMsgROBody roCore) {
        commonKernel.commissionWithMagicAndCommand(roCore.magic(), roCore.command());
        payloadHandler.commissionWithInvariantKernel(roCore.payloadHandler);
    }

    @Override
    protected void onSecondDuoScionDecommission() {
        commonKernel.decommission();
        payloadHandler.decommission();
    }


    /*
     * Sealable
     * #######################################################################
     */

    @Override
    public BitcoinMsgRW getBody() {
        return this;
    }

    @Override
    protected BitcoinMsgRWBody getThis() {
        return this;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + commonKernel.hashCode();
        result = prime * result + payloadHandler.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (BitcoinMsgRWBody.class.isInstance(obj)) {
            BitcoinMsgRWBody other = (BitcoinMsgRWBody) obj;
            if (!commonKernel.equals(other.commonKernel))
                return false;
            if (!payloadHandler.equals(other.payloadHandler))
                return false;
            return true;
        }
        if (BitcoinMsgROBody.class.isInstance(obj)) {
            BitcoinMsgROBody other = (BitcoinMsgROBody) obj;
            if (!commonKernel.equals(other.commonKernel))
                return false;
            if (!payloadHandler.equals(other.payloadHandler))
                return false;
            return true;
        }
        return false;
    }

}
