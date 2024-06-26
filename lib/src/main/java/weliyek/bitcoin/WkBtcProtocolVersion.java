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

/**
 * The version message provides information about the transmitting node to the
 * receiving node at the beginning of a connection.
 *
 * src/node/protocol_version.h
 */
public class WkBtcProtocolVersion
        implements Comparable<WkBtcProtocolVersion>
{

    private static final Map<Integer, WkBtcProtocolVersion> KNOWN = new HashMap<>();

    public static final WkBtcProtocolVersion INIT              = new WkBtcProtocolVersion(209, true);
    public static final WkBtcProtocolVersion MIN_PEER          = new WkBtcProtocolVersion(31800, true);
    public static final WkBtcProtocolVersion BIP0031           = new WkBtcProtocolVersion(60000, true);
    public static final WkBtcProtocolVersion SENDHEADERS       = new WkBtcProtocolVersion(70012, true);
    public static final WkBtcProtocolVersion FEEFILTER         = new WkBtcProtocolVersion(70013, true);
    public static final WkBtcProtocolVersion SHORT_IDS_BLOCKS  = new WkBtcProtocolVersion(70014, true);
    public static final WkBtcProtocolVersion INVALID_CB_NO_BAN = new WkBtcProtocolVersion(70015, true);
    public static final WkBtcProtocolVersion WTXID_RELAY       = new WkBtcProtocolVersion(70016, true);


    public final int value;

    private WkBtcProtocolVersion(int val, boolean isKnown) {
        this.value = val;
        if (isKnown) {
          if (KNOWN.containsKey(val)) {
            // Possible duplicate version number
            throw new IllegalArgumentException();
          }
          KNOWN.put(val, this);
        }
    }

    static public WkBtcProtocolVersion newVersion(int val) {
      if (KNOWN.containsKey(val)) {
        return KNOWN.get(val);
      }
      return new WkBtcProtocolVersion(val, false);
    }

    static public final WkBtcProtocolVersion getLatest() {
      return WTXID_RELAY;
    }

    public boolean isKnown() {
      return KNOWN.containsKey(this.value);
    }

    @Override
    public int compareTo(WkBtcProtocolVersion o) {
        return Integer.compare(value, o.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      WkBtcProtocolVersion other = (WkBtcProtocolVersion) obj;
      return value == other.value;
    }

}
