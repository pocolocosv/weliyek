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

import java.util.Optional;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.ketza.base.composite.serializable.SerializableBaseCoreSerializerDeserialization;
import weliyek.ketza.bitstreamable.scion.duo.FirstDuoScionCoreAbstract;
import weliyek.ketza.bitstreamable.scion.duo.SecondDuoScionCommissioner;

public class BitcoinMsgROBody
        extends FirstDuoScionCoreAbstract<BitcoinMsgStreamBody,
                                          BitcoinMsgRO,
                                          BitcoinMsgROBody,
                                          BitcoinMsgRW,
                                          BitcoinProtocolMessageConfig>
        implements MessageUnpackedCore<BitcoinMsgStreamBody,
                                       BitcoinMsgRO,
                                       BitcoinMsgRW>,
                   BitcoinMsgRO
{

    protected BitcoinMsgROBody(
            AmatNamespace namespace,
            SerializableBaseCoreSerializerDeserialization<BitcoinMsgROBody, BitcoinProtocolMessageConfig> deserializer,
            SecondDuoScionCommissioner<?, BitcoinMsgROBody, BitcoinMsgRW, ?, BitcoinProtocolMessageConfig> secondScionCommissioner) {
        super(deserializer, secondScionCommissioner);
        this.namespace = namespace.newChildNamespace(BitcoinProtocolName.MESSAGE_RO);
        this.commonKernel = new MessageCommonCoreKernelBase(this);
        this.payloadHandler = new MessagePayloadHandlerRO(this);
    }

    /*
    BitcoinMsgROBody(BitcoinMsgCommissioners c) {
        this.commissioners = c;
        this.commonKernel = new MessageCommonCoreKernelRO(c, this);
        this.payloadHandler = new MessagePayloadHandlerRO(this);
    }
    */

    /*
     * MessageRO
     * #######################################################################
     */

    int checksum;

    @Override
    public int checksum() {
        return checksum;
    }

    /*
     * MessageRO and MessageRW common
     * #######################################################################
     */

    final AmatNamespace namespace;

    final MessageCommonCoreKernelBase commonKernel;

    final MessagePayloadHandlerRO payloadHandler;

    @Override
    public BitcoinMessageMagic magic() {
        return this.commonKernel.magic();
    }

    @Override
    public BitcoinCommand command() {
        return this.commonKernel.command();
    }

    @Override
    public boolean isVersion() {
        return payloadHandler.isVersion();
    }

    @Override
    public Optional<BitcoinMsgVersionRO> asVersion() {
        return payloadHandler.asVersion();
    }

    @Override
    public boolean isAddr() {
        return payloadHandler.isAddr();
    }

    @Override
    public Optional<BitcoinMsgAddrRO> asAddr() {
        return payloadHandler.asAddr();
    }

    @Override
    public boolean isGetData() {
        return payloadHandler.isGetData();
    }

    @Override
    public Optional<BitcoinMsgGetDataRO> asGetData() {
        return payloadHandler.asGetData();
    }

    @Override
    public boolean isTx() {
        return payloadHandler.isTx();
    }

    @Override
    public Optional<BitcoinMsgTxRO> asTx() {
        return payloadHandler.asTx();
    }

    @Override
    public boolean isHeaders() {
        return payloadHandler.isHeaders();
    }

    @Override
    public Optional<BitcoinMsgHeadersRO> asHeaders() {
        return payloadHandler.asHeaders();
    }

    @Override
    public boolean isNotFound() {
        return payloadHandler.isNotFound();
    }

    @Override
    public Optional<BitcoinMsgNotFoundRO> asNotFound() {
        return payloadHandler.asNotFound();
    }

    @Override
    public boolean isGetBlocks() {
        return payloadHandler.isGetBlocks();
    }

    @Override
    public Optional<BitcoinMsgGetBlocksRO> asGetBlocks() {
        return payloadHandler.asGetBlocks();
    }

    @Override
    public boolean isReject() {
        return payloadHandler.isReject();
    }

    @Override
    public Optional<BitcoinMsgRejectRO> asReject() {
        return payloadHandler.asReject();
    }

    @Override
    public BitcoinMsgRW toRW() {
        return spawnDescendant();
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
    protected void onFirstDuoScionDecommission() {
        commonKernel.decommission();
        payloadHandler.decommission();
        checksum = 0;
    }

    /*
     * Sealable
     * #######################################################################
     */

    @Override
    protected BitcoinMsgROBody getThis() {
        return this;
    }

    @Override
    public BitcoinMsgRO getBody() {
        return this;
    }

}
