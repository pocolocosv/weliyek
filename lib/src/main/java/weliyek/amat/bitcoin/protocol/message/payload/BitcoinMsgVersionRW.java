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
package weliyek.amat.bitcoin.protocol.message.payload;

import weliyek.amat.bitcoin.protocol.BitcoinNetAddr;
import weliyek.bitcoin.BitcoinNodeServices;
import weliyek.bitcoin.protocol.BitcoinProtocolVersion;

public interface BitcoinMsgVersionRW
        extends BitcoinMsgVersion,
                BitcoinMsgPayloadRW
{

    void setVersion(BitcoinProtocolVersion v);

    /**
     * Sets features to be enabled for the connection
     */
    void setServices(BitcoinNodeServices s);

    /**
     * @return The standard UNIX timestamp in seconds
     */
    void setTimestamp(long t);

    /**
     * @return The network address of the node receiving this message
     */
    void setRecv(BitcoinNetAddr r);

    /**
     * Sets the network address of the node emitting this message
     * <b>Present only on versions 106 or higher</b>.
     */
    void setFrom(BitcoinNetAddr f);

    /**
     * Node random nonce, randomly generated every time a version packet is sent. This nonce
     * is used to detect connections to self.
     */
    void setNonce(long v);

    /**
     * Sets the user agent string.
     * <b>Present only on versions 106 or higher</b>.
     */
    void setUserAgent(String s);

    /**
     * Sets the last block received by the emitting node
     * <b>Present only on versions 106 or higher</b>.
     */
    void setStartHeight(int h);

    /**
     * Set to request that the remote peer should announce relayed transactions or not, see BIP 0037
     * <b>Present only on versions 70001 or higher</b>.
     */
    void setRelay(boolean b);

}
