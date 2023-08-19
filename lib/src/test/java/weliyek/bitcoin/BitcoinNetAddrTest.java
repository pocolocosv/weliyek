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

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.Inet6Address;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.namespace.AmatNamespaceFactoryWithSimpleCache;
import weliyek.amat.base.protocol.TestAmatName;
import weliyek.bitcoin.WkBitcoinCommand;
import weliyek.bitcoin.BitcoinNetAddr;
import weliyek.bitcoin.BitcoinNodeServices;
import weliyek.bitcoin.BitcoinProtocolName;
import weliyek.bitcoin.WkBitcoinProtocolVersion;
import weliyek.bitcoin.BitcoinServiceFlag;
import weliyek.ketza.util.KetzaByteOutputStream;

public class BitcoinNetAddrTest
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinNetAddrTest.class);

    static AmatNamespaceFactoryWithSimpleCache NAMESPACE_FACTORY;
    public AmatNamespace baseNamespace;
    public AmatNamespace netaddrNamespace;
    public AmatNamespace timeNamespace;
    public AmatNamespace servicesNamespace;
    public AmatNamespace inetAddrNamespace;
    public AmatNamespace portNamespace;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        NAMESPACE_FACTORY = new AmatNamespaceFactoryWithSimpleCache();
    }

    @Before
    public void setUp() throws Exception {
        baseNamespace = NAMESPACE_FACTORY.newNamespace(TestAmatName.BASE_AMAT);
        final AmatNamespace netaddrNamespace = baseNamespace.newChildNamespace(BitcoinProtocolName.NETADDR);
        timeNamespace = netaddrNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_TIME);
        servicesNamespace = netaddrNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_SERVICES);
        inetAddrNamespace = netaddrNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_ADDRESS);
        portNamespace = netaddrNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_PORT);
    }

    @Test
    public void test() throws IOException {
        Inet6Address address = Inet6Address.getByAddress("",
                                                         new byte[] {0,0,0,0,0,0,0,0,0,0,(byte)0xFF,(byte)0xFF,10,0,0,1},
                                                         0);

        final BitcoinNodeServices services = new BitcoinNodeServices(BitcoinServiceFlag.NODE_BLOOM, BitcoinServiceFlag.NODE_GETUTXO);
        Optional<Long> time = Optional.of(Long.valueOf(0x0000_0000_1234_5678L));
        int port = 0x0000_1234;
        final BitcoinNetAddr netaddr = new BitcoinNetAddr(address, services, port, time);

        KetzaByteOutputStream out = new KetzaByteOutputStream();
        netaddr.writeTo(WkBitcoinProtocolVersion.CADDR_TIME, WkBitcoinCommand.TX, out,
                        timeNamespace, servicesNamespace, inetAddrNamespace, portNamespace);

        final BitcoinNetAddr netaddr2 = BitcoinNetAddr.readFrom(WkBitcoinProtocolVersion.CADDR_TIME, WkBitcoinCommand.TX, out.inputStream(),
                                                                timeNamespace, servicesNamespace, inetAddrNamespace, portNamespace);

        assertEquals(netaddr, netaddr2);
    }

}
