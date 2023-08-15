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

import java.io.InputStream;
import java.io.OutputStream;

import weliyek.amat.base.protocol.base.AmatProtocolUtil;
import weliyek.amat.bitcoin.protocol.BitcoinNetAddr;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolIO;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolMessageConfig;
import weliyek.bitcoin.BitcoinNodeServices;
import weliyek.bitcoin.protocol.BitcoinProtocolVersion;

public class BitcoinMsgVersionSerializer
        implements PayloadKernelSerializer<BitcoinMsgVersionCoreKernel, BitcoinMsgVersionCoreKernel>
{

    @Override
    public void deserializeKernel(BitcoinMsgVersionCoreKernel  versionKernel,
                                  InputStream                  in,
                                  BitcoinProtocolMessageConfig config)
    {
        final BitcoinProtocolVersion ver = BitcoinProtocolVersion.readFrom(in, versionKernel.versionValueNamespace);
        versionKernel.setVersion(ver);
        final BitcoinNodeServices services = BitcoinNodeServices.readFrom(in, versionKernel.servicesNamespace);
        versionKernel.setServices(services);
        final long timestamp = AmatProtocolUtil.readLittleEndianLong(in, versionKernel.timestampNamespace);
        versionKernel.setTimestamp(timestamp);
        final BitcoinNetAddr recv = BitcoinNetAddr.readFrom(ver, config.messageCommand(), in,
                                                            versionKernel.recvTimeNamespace,
                                                            versionKernel.recvServicesNamespace,
                                                            versionKernel.recvInetAddrNamespace,
                                                            versionKernel.recvPortNamespace);
        versionKernel.setRecv(recv);
        if (ver.value >= 106) {
            final BitcoinNetAddr from = BitcoinNetAddr.readFrom(ver, config.messageCommand(), in,
                                                                versionKernel.fromTimeNamespace,
                                                                versionKernel.fromServicesNamespace,
                                                                versionKernel.fromInetAddrNamespace,
                                                                versionKernel.fromPortNamespace);
            versionKernel.setFrom(from);
            final long nonce = AmatProtocolUtil.readLittleEndianLong(in, versionKernel.nonceNamespace);
            versionKernel.setNonce(nonce);
            final String userAgntStr = BitcoinProtocolIO.readVarStr(in, versionKernel.userAgentNamespace);
            versionKernel.setUserAgent(userAgntStr);
            final long startHeight = AmatProtocolUtil.readLittleEndianInt(in, versionKernel.startHeightNamespace);
            versionKernel.setStartHeight((int) startHeight);
        }
        if (ver.value >=  70001) {
            final int relayByte = AmatProtocolUtil.readByte(in, versionKernel.relayNamespace);
            versionKernel.setRelay(relayByte != 0 ? true : false);
        }
    }

    @Override
    public void serializeKernel(BitcoinMsgVersionCoreKernel  versionKernel,
                                OutputStream                 out,
                                BitcoinProtocolMessageConfig config)
    {
        versionKernel.version().writeTo(out, versionKernel.versionValueNamespace);
        versionKernel.services().writeTo(out, versionKernel.servicesNamespace);
        AmatProtocolUtil.writeLittleEndianInt(out, versionKernel.timestamp(), versionKernel.timestampNamespace);
        versionKernel.recv().writeTo(versionKernel.version(), config.messageCommand(), out,
                                     versionKernel.recvTimeNamespace,
                                     versionKernel.recvServicesNamespace,
                                     versionKernel.recvInetAddrNamespace,
                                     versionKernel.recvPortNamespace);
        if (versionKernel.version().value >= 106) {
            versionKernel.from().writeTo(versionKernel.version(), config.messageCommand(), out,
                                         versionKernel.fromTimeNamespace,
                                         versionKernel.fromServicesNamespace,
                                         versionKernel.fromInetAddrNamespace,
                                         versionKernel.fromPortNamespace);
            AmatProtocolUtil.writeLittleEndianLong(out, versionKernel.nonce(), versionKernel.nonceNamespace);
            BitcoinProtocolIO.writeVarStr(versionKernel.userAgent(), out, versionKernel.userAgentNamespace);
            BitcoinProtocolIO.writeLittleEndianInt(out, versionKernel.startHeight(), versionKernel.startHeightNamespace);
        }
        if (versionKernel.version().value >= 70001) {
            BitcoinProtocolIO.writeByte(out, versionKernel.relay() ? 1 : 0, versionKernel.relayNamespace);
        }
    }

}
