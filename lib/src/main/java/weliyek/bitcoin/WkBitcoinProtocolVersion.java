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
import java.util.Objects;
import java.util.Optional;

/**
 * The version message provides information about the transmitting node to the
 * receiving node at the beginning of a connection.
 * 
 * https://github.com/bitcoin/bitcoin/blob/master/src/version.h
 */
public class WkBitcoinProtocolVersion
        implements Comparable<WkBitcoinProtocolVersion>
{

    public static final WkBitcoinProtocolVersion INIT_PROTO = new WkBitcoinProtocolVersion(209);
    public static final WkBitcoinProtocolVersion GETHEADERS_MIN_PEER_PROTO = new WkBitcoinProtocolVersion(31800);
    public static final WkBitcoinProtocolVersion CADDR_TIME = new WkBitcoinProtocolVersion(31402);
    public static final WkBitcoinProtocolVersion BIP0031 = new WkBitcoinProtocolVersion(60000);
    public static final WkBitcoinProtocolVersion NO_BLOOM = new WkBitcoinProtocolVersion(70011);
    public static final WkBitcoinProtocolVersion SENDHEADERS = new WkBitcoinProtocolVersion(70012);
    public static final WkBitcoinProtocolVersion FEEFILTER = new WkBitcoinProtocolVersion(70013);
    public static final WkBitcoinProtocolVersion SHORT_IDS_BLOCKS = new WkBitcoinProtocolVersion(70014);
    public static final WkBitcoinProtocolVersion INVALID_CB_NO_BAN_VERSION = new WkBitcoinProtocolVersion(70015);

    public static final WkBitcoinProtocolVersion LATEST() {
      return INVALID_CB_NO_BAN_VERSION;
    }
    
    public WkBitcoinProtocolVersion getVersion(int val) {
      return VERSION_BY_VALUE_MAP.computeIfAbsent(val, WkBitcoinProtocolVersion::new);
    }

    private static final Map<Integer, WkBitcoinProtocolVersion> VERSION_BY_VALUE_MAP;

    static {
        VERSION_BY_VALUE_MAP = new HashMap<>();
        VERSION_BY_VALUE_MAP.put(INIT_PROTO.integer, INIT_PROTO);
        VERSION_BY_VALUE_MAP.put(GETHEADERS_MIN_PEER_PROTO.integer, GETHEADERS_MIN_PEER_PROTO);
        VERSION_BY_VALUE_MAP.put(CADDR_TIME.integer, CADDR_TIME);
        VERSION_BY_VALUE_MAP.put(BIP0031.integer, BIP0031);
        VERSION_BY_VALUE_MAP.put(NO_BLOOM.integer, NO_BLOOM);
        VERSION_BY_VALUE_MAP.put(SENDHEADERS.integer, SENDHEADERS);
        VERSION_BY_VALUE_MAP.put(FEEFILTER.integer, FEEFILTER);
        VERSION_BY_VALUE_MAP.put(SHORT_IDS_BLOCKS.integer, SHORT_IDS_BLOCKS);
        VERSION_BY_VALUE_MAP.put(INVALID_CB_NO_BAN_VERSION.integer, INVALID_CB_NO_BAN_VERSION);
    }

    public final Integer integer;

    private WkBitcoinProtocolVersion(int value) {
        this(Integer.valueOf(value));
    }

    public WkBitcoinProtocolVersion(Integer value) {
        this.integer = value;
    }

    @Override
    public int compareTo(WkBitcoinProtocolVersion o) {
        return Integer.compare(integer, o.integer);
    }

    @Override
    public int hashCode() {
      return Objects.hash(integer);
    }
  
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      WkBitcoinProtocolVersion other = (WkBitcoinProtocolVersion) obj;
      return Objects.equals(integer, other.integer);
    }

}
