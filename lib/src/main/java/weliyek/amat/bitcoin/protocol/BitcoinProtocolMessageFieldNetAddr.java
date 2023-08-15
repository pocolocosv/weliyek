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
package weliyek.amat.bitcoin.protocol;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.message.field.AmatProtocolMessageFieldValue;

public class BitcoinProtocolMessageFieldNetAddr
        extends AmatProtocolMessageFieldValue<BitcoinNetAddr,
                                              AmatNamespace>
{

    public final AmatNamespace timeNamespace;
    public final AmatNamespace servicesNamespace;
    public final AmatNamespace inetAddrNamespace;
    public final AmatNamespace portNamespace;

    public BitcoinProtocolMessageFieldNetAddr(AmatNamespace namespace) {
        super(namespace.newChildNamespace(BitcoinProtocolName.NETADDR));
        timeNamespace = namespace().newChildNamespace(BitcoinProtocolName.NETADDR_TIME);
        servicesNamespace = namespace().newChildNamespace(BitcoinProtocolName.NETADDR_SERVICES);
        inetAddrNamespace = namespace().newChildNamespace(BitcoinProtocolName.NETADDR_ADDRESS);
        portNamespace = namespace().newChildNamespace(BitcoinProtocolName.NETADDR_PORT);
    }

}
