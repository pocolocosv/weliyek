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

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

import weliyek.util.array.WkByteArray;

public class WkBtcNetMessageType
{

    public static WkBtcNetMessageType newCommand(byte[] array) {
      return newCommand(array, 0);
    }

    public static WkBtcNetMessageType newCommand(byte[] array, int offset) {
        if (CANONICAL_LENGTH != (array.length - offset)) {
          throw new IllegalArgumentException();
        }
        Optional<WkBtcNetMessageType> cmd = findKnownCommand(array, offset);
        if (cmd.isPresent()) {
          return cmd.get();
        }
        byte[] array_copy = Arrays.copyOfRange(array, offset, array.length - offset);
        WkByteArray wrapper = new WkByteArray(array_copy);
        return newCommand(wrapper);
    }

    public static WkBtcNetMessageType newCommand(WkByteArray wrapper) {
        if (CANONICAL_LENGTH != wrapper.getLength()) {
          throw new IllegalArgumentException();
        }
        for (WkBtcNetMessageType cmd : KNOWN_CMDS) {
          if (cmd.bytes.equals(wrapper)) {
            return cmd;
          }
        }
        return new WkBtcNetMessageType(wrapper);
    }

    public static Optional<WkBtcNetMessageType> findKnownCommand(byte[] array) {
      return findKnownCommand(array, 0);
    }

    public static Optional<WkBtcNetMessageType> findKnownCommand(byte[] array, int offset) {
      if (CANONICAL_LENGTH != (array.length - offset)) {
        // Compared array must have the canonical length.
        throw new IllegalArgumentException();
      }
      for (WkBtcNetMessageType cmd : KNOWN_CMDS) {
        if(0 == cmd.bytes.compare(array, offset, offset + CANONICAL_LENGTH)) {
          return Optional.of(cmd);
        }
      }
      return Optional.empty();
    }

    public static byte[] asCanonicalByteArray(String str) {
        return Arrays.copyOf(str.getBytes(StandardCharsets.US_ASCII), CANONICAL_LENGTH);
    }

    public static final int CANONICAL_LENGTH = 12;

    public static final WkBtcNetMessageType VERSION     = new WkBtcNetMessageType("version", true);
    public static final WkBtcNetMessageType VERACK      = new WkBtcNetMessageType("verack", true);
    public static final WkBtcNetMessageType ADDR        = new WkBtcNetMessageType("addr", true);
    public static final WkBtcNetMessageType ADDRV2      = new WkBtcNetMessageType("addrv2", true);
    public static final WkBtcNetMessageType SENDADDRV2  = new WkBtcNetMessageType("sendaddrv2", true);
    public static final WkBtcNetMessageType INV         = new WkBtcNetMessageType("inv", true);
    public static final WkBtcNetMessageType GETDATA     = new WkBtcNetMessageType("getdata", true);
    public static final WkBtcNetMessageType MERKLEBLOCK = new WkBtcNetMessageType("merkleblock", true);
    public static final WkBtcNetMessageType GETBLOCKS   = new WkBtcNetMessageType("getblocks", true);
    public static final WkBtcNetMessageType GETHEADERS  = new WkBtcNetMessageType("getheaders", true);
    public static final WkBtcNetMessageType TX          = new WkBtcNetMessageType("tx", true);
    public static final WkBtcNetMessageType HEADERS     = new WkBtcNetMessageType("headers", true);
    public static final WkBtcNetMessageType BLOCK       = new WkBtcNetMessageType("block", true);
    public static final WkBtcNetMessageType GETADDR     = new WkBtcNetMessageType("getaddr", true);
    public static final WkBtcNetMessageType MEMPOOL     = new WkBtcNetMessageType("mempool", true);
    public static final WkBtcNetMessageType PING        = new WkBtcNetMessageType("ping", true);
    public static final WkBtcNetMessageType PONG        = new WkBtcNetMessageType("pong", true);
    public static final WkBtcNetMessageType NOTFOUND    = new WkBtcNetMessageType("notfound", true);
    public static final WkBtcNetMessageType FILTERLOAD  = new WkBtcNetMessageType("filterload", true);
    public static final WkBtcNetMessageType FILTERADD   = new WkBtcNetMessageType("filteradd", true);
    public static final WkBtcNetMessageType FILTERCLEAR = new WkBtcNetMessageType("filterclear", true);
    public static final WkBtcNetMessageType SENDHEADERS = new WkBtcNetMessageType("sendheaders", true);
    public static final WkBtcNetMessageType FEEFILTER   = new WkBtcNetMessageType("feefilter", true);
    public static final WkBtcNetMessageType SENDCMPCT   = new WkBtcNetMessageType("sendcmpct", true);
    public static final WkBtcNetMessageType CMPCTBLOCK  = new WkBtcNetMessageType("cmpctblock", true);
    public static final WkBtcNetMessageType GETBLOCKTXN = new WkBtcNetMessageType("getblocktxn", true);
    public static final WkBtcNetMessageType BLOCKTXN    = new WkBtcNetMessageType("blocktxn", true);
    public static final WkBtcNetMessageType GETCFILTERS = new WkBtcNetMessageType("getcfilters", true);
    public static final WkBtcNetMessageType CFILTER = new WkBtcNetMessageType("cfilter", true);
    public static final WkBtcNetMessageType GETCFHEADERS = new WkBtcNetMessageType("getcfheaders", true);
    public static final WkBtcNetMessageType CFHEADERS = new WkBtcNetMessageType("cfheaders", true);
    public static final WkBtcNetMessageType GETCFCHECKPT = new WkBtcNetMessageType("getcfcheckpt", true);
    public static final WkBtcNetMessageType CFCHECKPT = new WkBtcNetMessageType("cfcheckpt", true);
    public static final WkBtcNetMessageType WTXIDRELAY = new WkBtcNetMessageType("wtxidrelay", true);
    public static final WkBtcNetMessageType SENDTXRCNCL = new WkBtcNetMessageType("sendtxrcncl", true);

    public final WkByteArray bytes;
    public final String name;

    private static final List<WkBtcNetMessageType> KNOWN_CMDS = new ArrayList<>();

    /*
     * Creates a new command using the ASCII version of the input string.
     */
    private WkBtcNetMessageType(String str, boolean addSelfToKnown) {
      this(new WkByteArray(asCanonicalByteArray(str)));
      KNOWN_CMDS.add(this);
    }

    private WkBtcNetMessageType(WkByteArray wrapper) {
        this.bytes = Objects.requireNonNull(wrapper);
        this.name = Pattern.compile("\\p{C}").matcher(bytes.convertToString(StandardCharsets.US_ASCII)).replaceAll("?");
    }

    public boolean isKnown() {
      return KNOWN_CMDS.contains(this);
    }

    public  List<WkBtcNetMessageType> knownCommands() {
      return Collections.unmodifiableList(KNOWN_CMDS);
    }

    @Override
    public String toString() {
      return this.name;
    }

    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result += prime * result + bytes.hashCode();
      result += prime * result + name.hashCode();
      return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
          return true;
        }
        if (obj == null) {
          return false;
        }
        if (!(obj instanceof WkBtcNetMessageType)) {
          return false;
        }
        WkBtcNetMessageType other = (WkBtcNetMessageType) obj;
        if ( ! bytes.equals(other.bytes)) {
          return false;
        }
        return true;
    }

}
