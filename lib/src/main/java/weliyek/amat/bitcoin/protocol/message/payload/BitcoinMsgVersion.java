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

public interface BitcoinMsgVersion extends BitcoinMsgPayload
{

    BitcoinProtocolVersion version();

    /**
     * @return Features to be enabled for the connection
     */
    BitcoinNodeServices services();

    /**
     * @return The standard UNIX timestamp in seconds
     */
    long timestamp();

    /**
     * @return The network address of the node receiving this message
     */
    BitcoinNetAddr recv();

    /**
    * @return The network address of the node emitting this message
    */
    BitcoinNetAddr from();

   /**
    * Node random nonce, randomly generated every time a version packet is sent. This nonce
    * is used to detect connections to self.
    *
    * <b>Present only on versions 106 or higher</b>.
    *
    * @return Node random nonce.
    */
   long nonce();

   /**
    * <b>Present only on versions 106 or higher</b>.
    * @return The user agent string.
    */
   String userAgent();

   /**
    * <b>Present only on versions 106 or higher</b>.
    * @return The last block received by the emitting node
    */
   int startHeight();

   /**
    * <b>Present only on versions 70001 or higher</b>.
    * @return Whether the remote peer should announce relayed transactions or not, see BIP 0037
    */
   boolean relay();

}
