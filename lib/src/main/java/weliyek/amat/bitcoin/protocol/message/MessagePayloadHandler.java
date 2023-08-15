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

import java.util.Optional;

import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgAddr;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetBlocks;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgGetData;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgHeaders;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgNotFound;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgReject;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgTx;
import weliyek.amat.bitcoin.protocol.message.payload.BitcoinMsgVersion;
import weliyek.amat.bitcoin.protocol.message.payload.PayloadCoreKernel;
import weliyek.ketza.base.composite.commissionable.CommissionableCoreKernel;

public abstract class MessagePayloadHandler
                        <XC extends MessageUnpackedCore<?, ?, ?>,
                         V extends BitcoinMsgVersion,
                         A extends BitcoinMsgAddr,
                         G extends BitcoinMsgGetData,
                         T extends BitcoinMsgTx,
                         H extends BitcoinMsgHeaders,
                         N extends BitcoinMsgNotFound,
                         GB extends BitcoinMsgGetBlocks,
                         RJCT extends BitcoinMsgReject>
        implements MessagePayloadAccess<V, A, G, T, H, N, GB, RJCT>,
                   CommissionableCoreKernel
{

    protected final XC owner;

    protected final BitcoinMsgCommissioners tools;

    private PayloadCoreKernel<?> nullablePayloadKernel;

    private boolean commissionedWithoutPayload;

    MessagePayloadHandler(XC owner, BitcoinMsgCommissioners tools) {
        this.owner = owner;
        this.tools = tools;
    }

    protected PayloadCoreKernel<?> kernel() {
        return nullablePayloadKernel;
    }

    protected void setKernel(PayloadCoreKernel<?> kernel) {
        this.nullablePayloadKernel = kernel;
        this.commissionedWithoutPayload = false;
    }

    void commissionWithoutKernel() {
        this.nullablePayloadKernel = null;
        this.commissionedWithoutPayload = true;
    }

    @Override
    public void decommission() {
        if (null != this.nullablePayloadKernel) {
            this.nullablePayloadKernel.decommission();
        }
        this.nullablePayloadKernel = null;
        this.commissionedWithoutPayload = false;
    }

    @Override
    public boolean isCommissioned() {
        return    (nullablePayloadKernel != null)
               || commissionedWithoutPayload;
    }

    // Version
    // =======

    @Override
    public boolean isVersion() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isVersion();
    }

    @Override
    public Optional<V> asVersion() {
        if (isVersion()) {
            return Optional.of(castPayloadAsVersion());
        } else {
            return Optional.empty();
        }
    }

    public abstract V castPayloadAsVersion();

    // Addr
    // ====

    @Override
    public boolean isAddr() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isAddr();
    }

    @Override
    public Optional<A> asAddr() {
        if (isAddr()) {
            return Optional.of(castPayloadAsAddr());
        } else {
            return Optional.empty();
        }
    }

    public abstract A castPayloadAsAddr();

    // GetData
    // =======

    @Override
    public boolean isGetData() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isGetData();
    }

    @Override
    public Optional<G> asGetData() {
        if (isGetData()) {
            return Optional.of(castPayloadAsGetData());
        } else {
            return Optional.empty();
        }
    }

    public abstract G castPayloadAsGetData();

    // Tx
    // ==

    @Override
    public boolean isTx() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isTx();
    }

    @Override
    public Optional<T> asTx() {
        if (isTx()) {
            return Optional.of(castPayloadAsTx());
        } else {
            return Optional.empty();
        }
    }

    public abstract T castPayloadAsTx();

    // Headers
    // =======

    @Override
    public boolean isHeaders() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isHeaders();
    }

    @Override
    public Optional<H> asHeaders() {
        if (isGetData()) {
            return Optional.of(castPayloadAsHeaders());
        } else {
            return Optional.empty();
        }
    }

    public abstract H castPayloadAsHeaders();

    // NotFound
    // ========

    @Override
    public boolean isNotFound() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isNotFound();
    }

    @Override
    public Optional<N> asNotFound() {
        if (isNotFound()) {
            return Optional.of(castPayloadAsNotFound());
        } else {
            return Optional.empty();
        }
    }

    public abstract N castPayloadAsNotFound();

    // GetBlocks
    // =========

    @Override
    public boolean isGetBlocks() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isGetBlocks();
    }

    @Override
    public Optional<GB> asGetBlocks() {
        if (isGetBlocks()) {
            return Optional.of(castPayloadAsGetBlocks());
        } else {
            return Optional.empty();
        }
    }

    public abstract GB castPayloadAsGetBlocks();

    // NotFound
    // ========

    @Override
    public boolean isReject() {
        if (null == this.nullablePayloadKernel) {
            return false;
        }
        return this.nullablePayloadKernel.isReject();
    }

    @Override
    public Optional<RJCT> asReject() {
        if (isReject()) {
            return Optional.of(castPayloadAsReject());
        } else {
            return Optional.empty();
        }
    }

    public abstract RJCT castPayloadAsReject();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nullablePayloadKernel == null) ? 0 : nullablePayloadKernel.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if ( ! MessagePayloadHandler.class.isInstance(obj))
            return false;
        MessagePayloadHandler<?,?,?,?,?,?,?,?,?> other = (MessagePayloadHandler<?,?,?,?,?,?,?,?,?>) obj;
        if (nullablePayloadKernel == null) {
            if (other.nullablePayloadKernel != null)
                return false;
        } else if (!nullablePayloadKernel.equals(other.nullablePayloadKernel))
            return false;
        return true;
    }

}
