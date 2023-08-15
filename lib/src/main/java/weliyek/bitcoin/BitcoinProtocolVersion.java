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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BitcoinProtocolVersion
        implements Comparable<BitcoinProtocolVersion>
{

    public static final BitcoinProtocolVersion INIT_PROTO = new BitcoinProtocolVersion(BitcoinProtocolVersionName.INIT_PROTO);
    public static final BitcoinProtocolVersion GETHEADERS = new BitcoinProtocolVersion(BitcoinProtocolVersionName.GETHEADERS);
    public static final BitcoinProtocolVersion MIN_PEER_PROTO = new BitcoinProtocolVersion(BitcoinProtocolVersionName.MIN_PEER_PROTO);
    public static final BitcoinProtocolVersion CADDR_TIME = new BitcoinProtocolVersion(BitcoinProtocolVersionName.CADDR_TIME);
    public static final BitcoinProtocolVersion BIP0031 = new BitcoinProtocolVersion(BitcoinProtocolVersionName.BIP0031);
    public static final BitcoinProtocolVersion NO_BLOOM = new BitcoinProtocolVersion(BitcoinProtocolVersionName.NO_BLOOM);
    public static final BitcoinProtocolVersion SENDHEADERS = new BitcoinProtocolVersion(BitcoinProtocolVersionName.SENDHEADERS);
    public static final BitcoinProtocolVersion FEEFILTER = new BitcoinProtocolVersion(BitcoinProtocolVersionName.FEEFILTER);
    public static final BitcoinProtocolVersion SHORT_IDS_BLOCKS = new BitcoinProtocolVersion(BitcoinProtocolVersionName.SHORT_IDS_BLOCKS);
    public static final BitcoinProtocolVersion INVALID_CB_NO_BAN_VERSION = new BitcoinProtocolVersion(BitcoinProtocolVersionName.INVALID_CB_NO_BAN_VERSION);

    public static final BitcoinProtocolVersion LATEST = INVALID_CB_NO_BAN_VERSION;

    private static final Map<Long, BitcoinProtocolVersion> VERSION_BY_VALUE_MAP;

    static {
        VERSION_BY_VALUE_MAP = new HashMap<>();
        VERSION_BY_VALUE_MAP.put(INIT_PROTO.value, INIT_PROTO);
        VERSION_BY_VALUE_MAP.put(GETHEADERS.value, GETHEADERS);
        // VERSION_BY_VALUE_MAP.put(MIN_PEER_PROTO.value, MIN_PEER_PROTO); Not used since GETHEADERS has same value.
        VERSION_BY_VALUE_MAP.put(CADDR_TIME.value, CADDR_TIME);
        VERSION_BY_VALUE_MAP.put(BIP0031.value, BIP0031);
        VERSION_BY_VALUE_MAP.put(NO_BLOOM.value, NO_BLOOM);
        VERSION_BY_VALUE_MAP.put(SENDHEADERS.value, SENDHEADERS);
        VERSION_BY_VALUE_MAP.put(FEEFILTER.value, FEEFILTER);
        VERSION_BY_VALUE_MAP.put(SHORT_IDS_BLOCKS.value, SHORT_IDS_BLOCKS);
        VERSION_BY_VALUE_MAP.put(INVALID_CB_NO_BAN_VERSION.value, INVALID_CB_NO_BAN_VERSION);
    }

    public final Optional<BitcoinProtocolVersionName> name;

    public final long value;

    private BitcoinProtocolVersion(BitcoinProtocolVersionName name) {
        this.name = Optional.of(name);
        this.value = name.value;
    }

    public BitcoinProtocolVersion(long value) {
        BitcoinProtocolVersionName n = BitcoinProtocolVersionName.fromLong(value);
        this.value = value;
        this.name = Optional.ofNullable(n);
    }

    /*
    public void writeTo(OutputStream out, AmatNamespace namespace) {
        AmatProtocolUtil.writeLittleEndianInt(out, value, namespace);
    }

    public static BitcoinProtocolVersion readFrom(InputStream in, AmatNamespace namespace) {
        final long ver = AmatProtocolUtil.readLittleEndianInt(in, namespace);
        BitcoinProtocolVersion version = VERSION_BY_VALUE_MAP.get(ver);
        if (null == version) {
            version = new BitcoinProtocolVersion(ver);
        }
        return version;
    }
    */

    @Override
    public int compareTo(BitcoinProtocolVersion o) {
        return Long.compare(value, o.value);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (value ^ (value >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof BitcoinProtocolVersion))
            return false;
        BitcoinProtocolVersion other = (BitcoinProtocolVersion) obj;
        if (value != other.value)
            return false;
        return true;
    }

}
