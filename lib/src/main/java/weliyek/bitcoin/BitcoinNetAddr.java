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

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Inet6Address;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.base.AmatProtocolException;
import weliyek.amat.base.protocol.base.AmatProtocolOperation;
import weliyek.amat.base.protocol.base.AmatProtocolUtil;

public class BitcoinNetAddr
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinNetAddr.class);

    public final static int ADDRESS_WIDTH = 16;

    public final Inet6Address address;

    public final WkBitcoinNodeServices services;

    public final int port;

    public final Optional<Long> time;

    public BitcoinNetAddr(Inet6Address        addr,
                          WkBitcoinNodeServices services,
                          int                 port,
                          Optional<Long>      time)
    {
        this.address = Objects.requireNonNull(addr);
        this.services = Objects.requireNonNull(services);
        this.port = port;
        this.time = Objects.requireNonNull(time);
    }

    public void writeTo(WkBitcoinProtocolVersion version,
                        WkBitcoinCommand command,
                        OutputStream   out,
                        AmatNamespace  timeNamespace,
                        AmatNamespace  servicesNamespace,
                        AmatNamespace  inetAddrNamespace,
                        AmatNamespace  portNamespace)
    {
        writeTime(version, command, out, timeNamespace);
        services.writeTo(out, servicesNamespace);
        AmatProtocolUtil.writeInet6Addres(address, out, inetAddrNamespace);
        AmatProtocolUtil.writeBigEndianShort(out, port, portNamespace);
    }

    private void writeTime(WkBitcoinProtocolVersion version,
                           WkBitcoinCommand         command,
                           OutputStream           out,
                           AmatNamespace          timeNamespace)
    {
        if (expectTimeField(version, command))
        {
            if ( ! time.isPresent()) {
                throw new AmatProtocolException(timeNamespace,
                                                AmatProtocolOperation.SERIALIZER_INVALID_VALUE,
                                                "Missing TIME object expected when serializing" + BitcoinNetAddr.class.getSimpleName());
            }
            AmatProtocolUtil.writeLittleEndianInt(out, time.get(), timeNamespace);
        }
    }

    private static boolean expectTimeField(WkBitcoinProtocolVersion version, WkBitcoinCommand command) {
        return    ( ! command.equals(WkBitcoinCommand.VERSION))
               && (version.value >= WkBitcoinProtocolVersion.CADDR_TIME.value);
    }

    public static BitcoinNetAddr readFrom(WkBitcoinProtocolVersion version,
                                          WkBitcoinCommand         command,
                                          InputStream            in,
                                          AmatNamespace          timeNamespace,
                                          AmatNamespace          servicesNamespace,
                                          AmatNamespace          inetAddrNamespace,
                                          AmatNamespace          portNamespace)
    {
        Optional<Long> time = readTime(version, command, in, timeNamespace);
        final WkBitcoinNodeServices srvcs = WkBitcoinNodeServices.readFrom(in, servicesNamespace);
        final Inet6Address addr = AmatProtocolUtil.readInet6Address(in, inetAddrNamespace);
        final int prt = AmatProtocolUtil.readBigEndianShort(in, portNamespace);
        return new BitcoinNetAddr(addr, srvcs, prt, time);
    }

    private static Optional<Long> readTime(WkBitcoinProtocolVersion version, WkBitcoinCommand command, InputStream in, final AmatNamespace timeNamespace) {
        if (expectTimeField(version, command))
        {
            long time = AmatProtocolUtil.readLittleEndianInt(in, timeNamespace);
            return Optional.of(Long.valueOf(time));
        }
        return Optional.empty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + address.hashCode();
        result = prime * result + port;
        result = prime * result + services.hashCode();
        result = prime * result + (time.isPresent() ? time.get().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BitcoinNetAddr))
            return false;
        BitcoinNetAddr other = (BitcoinNetAddr) obj;
        if (!address.equals(other.address))
            return false;
        if (port != other.port)
            return false;
        if (!services.equals(other.services))
            return false;
        if ( ! time.isPresent()) {
            if (other.time.isPresent())
                return false;
        } else if (!time.get().equals(other.time.orElse(null)))
            return false;
        return true;
    }

}
