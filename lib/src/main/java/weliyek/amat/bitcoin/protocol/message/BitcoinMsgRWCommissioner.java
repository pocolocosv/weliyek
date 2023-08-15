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

import weliyek.amat.bitcoin.protocol.BitcoinProtocolMessageConfig;
import weliyek.ketza.base.composite.CoreAccessor;
import weliyek.ketza.bitstreamable.scion.duo.SecondDuoScionCommissionerAbstract;

public class BitcoinMsgRWCommissioner
        extends SecondDuoScionCommissionerAbstract<MessageRWArgs,
                                                   BitcoinMsgROBody,
                                                   BitcoinMsgRW,
                                                   BitcoinMsgRWBody,
                                                   BitcoinProtocolMessageConfig>
        implements MessageBaseCommissioner<BitcoinMsgROBody,
                                           BitcoinMsgRW,
                                           BitcoinMsgRWBody>,
                   CoreAccessor<BitcoinMsgRW, BitcoinMsgRWBody>
{

    private final Factory<BitcoinMsgRWBody> rwFactory;

    public BitcoinMsgRWCommissioner(Factory<BitcoinMsgRWBody> rwFactory) {
        this.rwFactory = rwFactory;
    }

    @Override
    public BitcoinMsgRWBody extractCoreFromBody(BitcoinMsgRW rw) {
        if (rw instanceof BitcoinMsgRWBody) {
            return (BitcoinMsgRWBody)rw;
        }
        throw new ClassCastException("Could NOT cast "
                + rw.getClass().getSimpleName()
                + " object into "
                + BitcoinMsgRWBody.class.getSimpleName());
    }

    @Override
    public CoreAccessor<BitcoinMsgRW, BitcoinMsgRWBody> accessor() {
        return this;
    }

    @Override
    protected Factory<BitcoinMsgRWBody> factory() {
        return rwFactory;
    }

}
