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

import java.util.Objects;

import weliyek.amat.base.namespace.AmatNamespace;

public class BitcoinMsgVersionCoreKernel
        extends PayloadCoreKernelAbstract<BitcoinMsgVersionCoreKernel>
        implements BitcoinMsgVersion,
                   BitcoinMsgVersionRO,
                   BitcoinMsgVersionRW,
                   PayloadCoreKernelRO<BitcoinMsgVersionCoreKernel>,
                   PayloadCoreKernelRW<BitcoinMsgVersionCoreKernel>
{

    public final AmatNamespace versionValueNamespace;
    public final AmatNamespace servicesNamespace;
    public final AmatNamespace timestampNamespace;
    public final AmatNamespace recvNamespace;
    public final AmatNamespace fromNamespace;
    public final AmatNamespace nonceNamespace;
    public final AmatNamespace userAgentNamespace;
    public final AmatNamespace startHeightNamespace;
    public final AmatNamespace relayNamespace;
    public final AmatNamespace recvTimeNamespace;
    public final AmatNamespace recvServicesNamespace;
    public final AmatNamespace recvInetAddrNamespace;
    public final AmatNamespace recvPortNamespace;
    public final AmatNamespace fromTimeNamespace;
    public final AmatNamespace fromServicesNamespace;
    public final AmatNamespace fromInetAddrNamespace;
    public final AmatNamespace fromPortNamespace;

    private WkBitcoinProtocolVersion versionValue;
    private WkBitcoinNodeServices services;
    private long timestamp;
    private BitcoinNetAddr recv;
    private BitcoinNetAddr from;
    private long nonce;
    private String userAgent;
    private int startHeight;
    private boolean relay;

    public BitcoinMsgVersionCoreKernel(AmatNamespace namespace) {
        versionValueNamespace = namespace.newChildNamespace(BitcoinProtocolName.VERSION_NUMBER);
        servicesNamespace     = namespace.newChildNamespace(BitcoinProtocolName.VERSION_SERVICES);
        timestampNamespace    = namespace.newChildNamespace(BitcoinProtocolName.VERSION_TIMESTAMP);
        nonceNamespace        = namespace.newChildNamespace(BitcoinProtocolName.VERSION_NONCE);
        userAgentNamespace    = namespace.newChildNamespace(BitcoinProtocolName.VERSION_USER_AGENT);
        startHeightNamespace  = namespace.newChildNamespace(BitcoinProtocolName.VERSION_START_HEIGHT);
        relayNamespace        = namespace.newChildNamespace(BitcoinProtocolName.VERSION_RELAY);

        AmatNamespace tmpRecv = namespace.newChildNamespace(BitcoinProtocolName.VERSION_RECV);
        recvNamespace         = tmpRecv.newChildNamespace(BitcoinProtocolName.NETADDR);
        recvTimeNamespace     = recvNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_TIME);
        recvServicesNamespace = recvNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_SERVICES);
        recvInetAddrNamespace = recvNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_ADDRESS);
        recvPortNamespace     = recvNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_PORT);

        AmatNamespace fromRecv = namespace.newChildNamespace(BitcoinProtocolName.VERSION_FROM);
        fromNamespace          = fromRecv.newChildNamespace(BitcoinProtocolName.NETADDR);
        fromTimeNamespace      = fromNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_TIME);
        fromServicesNamespace  = fromNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_SERVICES);
        fromInetAddrNamespace  = fromNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_ADDRESS);
        fromPortNamespace      = fromNamespace.newChildNamespace(BitcoinProtocolName.NETADDR_PORT);
    }

    @Override
    public void commissionPayloadKernelWithArgs(BitcoinMsgRWBody owner, MessageRWArgs args) {
        commissionWithOwnerCore(owner);
        // Don't expect values from args.
    }

    @Override
    public void commissionPayloadKernelWithRO(BitcoinMsgRWBody owner, MessagePayloadHandlerRO roHandler) {
        final BitcoinMsgVersionCoreKernel roKernel = roHandler.castPayloadAsVersion();
        setVersion(roKernel.version());
        setServices(roKernel.services());
        setTimestamp(roKernel.timestamp());
        setRecv(roKernel.recv());
        setFrom(roKernel.from());
        setNonce(roKernel.nonce());
        setUserAgent(roKernel.userAgent());
        setStartHeight(roKernel.startHeight());
        setRelay(roKernel.relay());
    }

    @Override
    protected void onCommission() {
        invalidateAttributes();
    }

    private void invalidateAttributes() {
        versionValue = null;
        services = null;
        timestamp = 0;
        recv = null;
        from = null;
        nonce = 0;
        userAgent = null;
        startHeight = 0;
        relay = false;
    }

    @Override
    protected void onPayloadDecommission() {
        invalidateAttributes();
    }

    @Override
    public WkBitcoinProtocolVersion version() {
        return versionValue;
    }

    @Override
    public void setVersion(WkBitcoinProtocolVersion v) {
        this.versionValue = Objects.requireNonNull(v);
    }

    @Override
    public WkBitcoinNodeServices services() {
        return services;
    }

    @Override
    public void setServices(WkBitcoinNodeServices s) {
        this.services = Objects.requireNonNull(s);
    }

    @Override
    public long timestamp() {
        return timestamp;
    }

    @Override
    public void setTimestamp(long t) {
        this.timestamp = t;
    }

    @Override
    public BitcoinNetAddr recv() {
        return recv;
    }

    @Override
    public void setRecv(BitcoinNetAddr r) {
        if (null == r) {
            throw new IllegalArgumentException();
        }
        this.recv = r;
    }

    @Override
    public BitcoinNetAddr from() {
        return from;
    }

    @Override
    public void setFrom(BitcoinNetAddr f) {
        this.from = Objects.requireNonNull(f);
    }

    @Override
    public String userAgent() {
        return userAgent;
    }

    @Override
    public void setUserAgent(String s) {
        this.userAgent = Objects.requireNonNull(s);
    }

    @Override
    public long nonce() {
        return nonce;
    }

    @Override
    public void setNonce(long v) {
        this.nonce = v;
    }

    @Override
    public int startHeight() {
        return startHeight;
    }

    @Override
    public void setStartHeight(int h) {
        this.startHeight = h;
    }

    @Override
    public boolean relay() {
        return relay;
    }

    @Override
    public void setRelay(boolean b) {
        this.relay = b;
    }

    @Override
    public final boolean isVersion() {
        return true;
    }

    @Override
    public BitcoinMsgVersionCoreKernel getBody() {
        return this;
    }

}
