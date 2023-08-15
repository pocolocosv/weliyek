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
import weliyek.ketza.base.composite.serializable.SerializableBaseCoreSerializerSerialization;
import weliyek.ketza.bitstreamable.BitstreamableBitsCoreAbstract;
import weliyek.ketza.bitstreamable.BitstreamableExpandedChildCommissioner;
import weliyek.ketza.bitstreamable.scion.duo.BitstreamDuoScionCore;

public class BitcoinMsgStreamBody
        extends BitstreamableBitsCoreAbstract<BitcoinMsgRWBody,
                                              BitcoinMsgStream,
                                              BitcoinMsgStreamBody,
                                              BitcoinMsgRO,
                                              BitcoinProtocolMessageConfig>
        implements BitcoinMsgStream,
                   MessageBaseCore<BitcoinMsgRWBody,
                                   BitcoinMsgStream,
                                   BitcoinMsgRO>,
                   BitstreamDuoScionCore<BitcoinMsgRWBody,
                                         BitcoinMsgStream,
                                         BitcoinMsgRO,
                                         BitcoinProtocolMessageConfig>
{

    protected BitcoinMsgStreamBody(
            SerializableBaseCoreSerializerSerialization<BitcoinMsgRWBody, BitcoinProtocolMessageConfig> serializer,
            BitstreamableExpandedChildCommissioner<BitcoinMsgStreamBody, BitcoinMsgRO, ?> descendantCommissioner)
    {
        super(serializer, descendantCommissioner);
        // TODO Auto-generated constructor stub
    }

    @Override
    public BitcoinMsgStream getBody() {
        return this;
    }

    @Override
    public BitcoinMsgRO toRO() {
        return spawnDescendant();
    }

    @Override
    protected BitcoinMsgStreamBody getThis() {
        return this;
    }

}
