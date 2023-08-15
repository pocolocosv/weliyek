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

/**
 * Bitcoin message payload public interface.
 *
 * This interface is the public face for all payload types. Caller must use one
 * of the methods provided here to access the payload as the desired type.
 *
 * @author Ricardo Villalobos Guevara
 *
 * @param <V>
 * @param <A>
 * @param <G>
 */
public interface MessagePayloadAccess<V extends BitcoinMsgVersion,
                                      A extends BitcoinMsgAddr,
                                      G extends BitcoinMsgGetData,
                                      T extends BitcoinMsgTx,
                                      H extends BitcoinMsgHeaders,
                                      N extends BitcoinMsgNotFound,
                                      GB extends BitcoinMsgGetBlocks,
                                      RJCT extends BitcoinMsgReject>
        extends MessagePayloadIdentity
{

    @Override
    boolean isVersion();

    Optional<V> asVersion();

    @Override
    boolean isAddr();

    Optional<A> asAddr();

    @Override
    boolean isGetData();

    Optional<G> asGetData();

    @Override
    boolean isTx();

    Optional<T> asTx();

    @Override
    boolean isHeaders();

    Optional<H> asHeaders();

    @Override
    boolean isNotFound();

    Optional<N> asNotFound();

    @Override
    boolean isGetBlocks();

    Optional<GB> asGetBlocks();

    @Override
    boolean isReject();

    Optional<RJCT> asReject();

}
