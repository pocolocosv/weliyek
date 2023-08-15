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

import java.security.NoSuchAlgorithmException;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.ketza.base.composite.lineage.Prospective;
import weliyek.ketza.factory.Possible;
import weliyek.ketza.sealable.SealableCreator;
import weliyek.ketza.sealable.SealableCreatorAbstract;

public class BitcoinMsg
        extends SealableCreatorAbstract<BitcoinMsgRO,
                                        BitcoinMsgRW,
                                        MessageRWArgs,
                                        BitcoinMsgRWBody,
                                        BitcoinMsgStream,
                                        BitcoinConfig>
        implements SealableCreator<BitcoinMsgRO,
                                   BitcoinMsgRW,
                                   BitcoinMsgStream,
                                   BitcoinConfig>
{

    public final static BitcoinProtocolVersionName LATEST_VERSION = BitcoinProtocolVersionName.INVALID_CB_NO_BAN_VERSION;


    public BitcoinMsg(AmatNamespace messageNamespace) throws NoSuchAlgorithmException {
        super(new BitcoinMsgCommissioners(messageNamespace));
    }

    public Prospective<BitcoinMsgRW> newRW(
            BitcoinConfig config,
            BitcoinMessageMagicName magic,
            BitcoinCommandName command) {
        Possible<BitcoinMsgRWBody> vb = newVariantBody(config, new MessageRWArgs(magic, command));
        return Prospective.ofSealableBody(vb);
    }

}
