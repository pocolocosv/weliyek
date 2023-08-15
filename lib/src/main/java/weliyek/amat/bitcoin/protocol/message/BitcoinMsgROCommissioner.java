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

import weliyek.ketza.base.composite.CoreAccessor;
import weliyek.ketza.bitstreamable.scion.duo.FirstDuoScionCommissionerAbstract;

public class BitcoinMsgROCommissioner
        extends FirstDuoScionCommissionerAbstract<BitcoinMsgStreamBody,
                                                  BitcoinMsgRO,
                                                  BitcoinMsgROBody>
        implements MessageBaseCommissioner<BitcoinMsgStreamBody,
                                           BitcoinMsgRO,
                                           BitcoinMsgROBody>,
                   CoreAccessor<BitcoinMsgRO,
                                BitcoinMsgROBody>
{

    private final Factory<BitcoinMsgROBody> roFactory;

    public BitcoinMsgROCommissioner(Factory<BitcoinMsgROBody> roFactory) {
        this.roFactory = roFactory;
    }

    @Override
    public BitcoinMsgROBody extractCoreFromBody(BitcoinMsgRO ro) {
        if (ro instanceof BitcoinMsgROBody) {
            return (BitcoinMsgROBody)ro;
        }
        throw new ClassCastException("Could NOT cast "
                + ro.getClass().getSimpleName()
                + " object into "
                + BitcoinMsgROBody.class.getSimpleName());
    }

    @Override
    public CoreAccessor<BitcoinMsgRO, BitcoinMsgROBody> accessor() {
        return this;
    }

    @Override
    protected Factory<BitcoinMsgROBody> factory() {
        return roFactory;
    }

}
