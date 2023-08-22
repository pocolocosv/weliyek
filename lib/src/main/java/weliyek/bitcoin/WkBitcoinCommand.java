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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

import weliyek.util.array.WkByteArray;

public class WkBitcoinCommand
{

    public static WkBitcoinCommand newCommand(byte[] array) {
      return newCommand(array, 0);
    }

    public static WkBitcoinCommand newCommand(byte[] array, int offset) {
        if (CANONICAL_LENGTH != (array.length - offset))
          throw new IllegalArgumentException();
        byte[] array_copy = Arrays.copyOfRange(array, offset, array.length - offset);
        WkByteArray wrapper = new WkByteArray(array_copy);
        return newCommand(wrapper);
    }

    public static WkBitcoinCommand newCommand(WkByteArray wrapper) {
        if (CANONICAL_LENGTH != wrapper.getLength())
            throw new IllegalArgumentException();
        // Get existing instance if possible.
        WkBitcoinCommand cmd = COMMAND_BY_BYTES.get(wrapper);
        if (null == cmd)
            cmd = new WkBitcoinCommand(wrapper);
        return cmd;
    }

    public static byte[] asCanonicalByteArray(String str) {
        return Arrays.copyOf(str.getBytes(StandardCharsets.US_ASCII), CANONICAL_LENGTH);
    }

    public static final int CANONICAL_LENGTH = 12;

    public static final WkBitcoinCommand VERSION     = new WkBitcoinCommand("version");
    public static final WkBitcoinCommand VERACK      = new WkBitcoinCommand("verack");
    public static final WkBitcoinCommand ADDR        = new WkBitcoinCommand("addr");
    public static final WkBitcoinCommand INV         = new WkBitcoinCommand("inv");
    public static final WkBitcoinCommand GETDATA     = new WkBitcoinCommand("getdata");
    public static final WkBitcoinCommand NOTFOUND    = new WkBitcoinCommand("notfound");
    public static final WkBitcoinCommand GETBLOCKS   = new WkBitcoinCommand("getblocks");
    public static final WkBitcoinCommand GETHEADERS  = new WkBitcoinCommand("getheaders");
    public static final WkBitcoinCommand TX          = new WkBitcoinCommand("tx");
    public static final WkBitcoinCommand BLOCK       = new WkBitcoinCommand("block");
    public static final WkBitcoinCommand HEADERS     = new WkBitcoinCommand("headers");
    public static final WkBitcoinCommand GETADDR     = new WkBitcoinCommand("getaddr");
    public static final WkBitcoinCommand MEMPOOL     = new WkBitcoinCommand("mempool");
    public static final WkBitcoinCommand CHECKORDER  = new WkBitcoinCommand("checkorder");
    public static final WkBitcoinCommand SUBMITORDER = new WkBitcoinCommand("submitorder");
    public static final WkBitcoinCommand REPLY       = new WkBitcoinCommand("reply");
    public static final WkBitcoinCommand PING        = new WkBitcoinCommand("ping");
    public static final WkBitcoinCommand PONG        = new WkBitcoinCommand("pong");
    public static final WkBitcoinCommand REJECT      = new WkBitcoinCommand("reject");
    public static final WkBitcoinCommand FILTERLOAD  = new WkBitcoinCommand("filterload");
    public static final WkBitcoinCommand FILTERADD   = new WkBitcoinCommand("filteradd");
    public static final WkBitcoinCommand FILTERCLEAR = new WkBitcoinCommand("filterclear");
    public static final WkBitcoinCommand MERKLEBLOCK = new WkBitcoinCommand("merkleblock");
    public static final WkBitcoinCommand ALERT       = new WkBitcoinCommand("alert");
    public static final WkBitcoinCommand SENDHEADERS = new WkBitcoinCommand("sendheaders");
    public static final WkBitcoinCommand FEEFILTER   = new WkBitcoinCommand("feefilter");
    public static final WkBitcoinCommand SENDCMPCT   = new WkBitcoinCommand("sendcmpct");
    public static final WkBitcoinCommand CMPCTBLOCK  = new WkBitcoinCommand("cmpctblock");
    public static final WkBitcoinCommand GETBLOCKTXN = new WkBitcoinCommand("getblocktxn");
    public static final WkBitcoinCommand BLOCKTXN    = new WkBitcoinCommand("blocktxn");

    /**
     * This table helps to obtain all the existing instances by their bytes.
     * We use the type {@link WkByteArray} because most of the time it will
     * likely be this type of object that will be used over the byte[]. 
     */
    public static final Map<WkByteArray, WkBitcoinCommand> COMMAND_BY_BYTES;

    static {
        COMMAND_BY_BYTES = new HashMap<>();
        COMMAND_BY_BYTES.put(VERSION.bytes,     VERSION);
        COMMAND_BY_BYTES.put(VERACK.bytes,      VERACK);
        COMMAND_BY_BYTES.put(ADDR.bytes,        ADDR);
        COMMAND_BY_BYTES.put(INV.bytes,         INV);
        COMMAND_BY_BYTES.put(GETDATA.bytes,     GETDATA);
        COMMAND_BY_BYTES.put(NOTFOUND.bytes,    NOTFOUND);
        COMMAND_BY_BYTES.put(GETBLOCKS.bytes,   GETBLOCKS);
        COMMAND_BY_BYTES.put(GETHEADERS.bytes,  GETHEADERS);
        COMMAND_BY_BYTES.put(TX.bytes,          TX);
        COMMAND_BY_BYTES.put(BLOCK.bytes,       BLOCK);
        COMMAND_BY_BYTES.put(HEADERS.bytes,     HEADERS);
        COMMAND_BY_BYTES.put(GETADDR.bytes,     GETADDR);
        COMMAND_BY_BYTES.put(MEMPOOL.bytes,     MEMPOOL);
        COMMAND_BY_BYTES.put(CHECKORDER.bytes,  CHECKORDER);
        COMMAND_BY_BYTES.put(SUBMITORDER.bytes, SUBMITORDER);
        COMMAND_BY_BYTES.put(REPLY.bytes,       REPLY);
        COMMAND_BY_BYTES.put(PING.bytes,        PING);
        COMMAND_BY_BYTES.put(PONG.bytes,        PONG);
        COMMAND_BY_BYTES.put(REJECT.bytes,      REJECT);
        COMMAND_BY_BYTES.put(FILTERLOAD.bytes,  FILTERLOAD);
        COMMAND_BY_BYTES.put(FILTERADD.bytes,   FILTERADD);
        COMMAND_BY_BYTES.put(FILTERCLEAR.bytes, FILTERCLEAR);
        COMMAND_BY_BYTES.put(MERKLEBLOCK.bytes, MERKLEBLOCK);
        COMMAND_BY_BYTES.put(ALERT.bytes,       ALERT);
        COMMAND_BY_BYTES.put(SENDHEADERS.bytes, SENDHEADERS);
        COMMAND_BY_BYTES.put(FEEFILTER.bytes,   FEEFILTER);
        COMMAND_BY_BYTES.put(SENDCMPCT.bytes,   SENDCMPCT);
        COMMAND_BY_BYTES.put(CMPCTBLOCK.bytes,  CMPCTBLOCK);
        COMMAND_BY_BYTES.put(GETBLOCKTXN.bytes, GETBLOCKTXN);
        COMMAND_BY_BYTES.put(BLOCKTXN.bytes,    BLOCKTXN);
    }

    public final WkByteArray bytes;
    public final String name;

    /*
     * Creates a new command using the ASCII version of the input string.
     */
    private WkBitcoinCommand(String str) {
      this(new WkByteArray(asCanonicalByteArray(str)));
    }

    private WkBitcoinCommand(WkByteArray wrapper) {
        this.bytes = Objects.requireNonNull(wrapper);
        this.name = Pattern.compile("\\p{C}").matcher(bytes.convertToString(StandardCharsets.US_ASCII)).replaceAll("?");
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof WkBitcoinCommand))
            return false;
        WkBitcoinCommand other = (WkBitcoinCommand) obj;
        if ( ! bytes.equals(other.bytes))
            return false;
        return true;
    }

}
