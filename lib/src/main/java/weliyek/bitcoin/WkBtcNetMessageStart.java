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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Defined in src/kernel/chainparams.cpp as of v27
 * https://github.com/bitcoin/bitcoin/tree/27.x/src/kernel/chainparams.cpp
 *
 * A description and usage can be found at
 * https://en.bitcoin.it/wiki/Protocol_documentation
 * where it is referred as <i>magic</i>
 *
 */
public class WkBtcNetMessageStart
{

    public static final WkBtcNetMessageStart MAIN = new WkBtcNetMessageStart(0xD9B4BEF9);
    public static final WkBtcNetMessageStart REGTEST = new WkBtcNetMessageStart(0xDAB5BFFA);
    public static final WkBtcNetMessageStart TESTNET3 = new WkBtcNetMessageStart(0x0709110B);
    public static final WkBtcNetMessageStart SIGNET = new WkBtcNetMessageStart(0x40CF030A);
    public static final WkBtcNetMessageStart NAMECOIN = new WkBtcNetMessageStart(0xFEB4BEF9);

    static public Map<Long,WkBtcNetMessageStart> KNOWN;

    final Long value;

    static {
      Map<Long,WkBtcNetMessageStart> map = new HashMap<>();
      map.put(MAIN.value, MAIN);
      map.put(REGTEST.value, REGTEST);
      map.put(TESTNET3.value, TESTNET3);
      map.put(SIGNET.value, SIGNET);
      map.put(NAMECOIN.value, NAMECOIN);
      KNOWN = Collections.unmodifiableMap(map);
    }

    static public WkBtcNetMessageStart newMagic(int val) {
      Long l = Long.valueOf(0x0000_0000_FFFF_FFFFL & val);
      if (KNOWN.containsKey(l)) {
        return KNOWN.get(l);
      } else {
        return new WkBtcNetMessageStart(val);
      }
    }

    private WkBtcNetMessageStart(int val) {
        this.value = Long.valueOf(0x0000_0000_FFFF_FFFFL & val);
    }

    public Long value() {
      return this.value;
    }

    public boolean isKnown() {
      return KNOWN.containsValue(this);
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
      WkBtcNetMessageStart other = (WkBtcNetMessageStart) obj;
      return Objects.equals(value, other.value);
    }

}
