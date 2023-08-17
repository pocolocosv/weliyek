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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import weliyek.util.array.WkByteArray;

public class WkBitcoinCommand
{

    public static WkBitcoinCommand newCommand(byte[] array, int offset, int length) {
        byte[] array_copy = Arrays.copyOfRange(array, offset, offset + length);
        WkByteArray wrapper = new WkByteArray(array_copy);
        return newCommand(wrapper);
    }

    public static WkBitcoinCommand newCommand(WkByteArray wrapper) {
        if (CANONICAL_LENGTH != wrapper.getLength())
            throw new IllegalArgumentException();
        WkBitcoinCommand cmd = CMD_BY_NAME.get(wrapper);
        if (null == cmd)
            cmd = new WkBitcoinCommand(wrapper);
        return cmd;
    }

    public static byte[] asCanonicalByteArray(String str) {
        return Arrays.copyOf(str.getBytes(StandardCharsets.UTF_8), CANONICAL_LENGTH);
    }

    public static final int CANONICAL_LENGTH = 12;

    public static final WkBitcoinCommand VERSION     = new WkBitcoinCommand(WkBitcoinCommandName.VERSION);
    public static final WkBitcoinCommand VERACK      = new WkBitcoinCommand(WkBitcoinCommandName.VERACK);
    public static final WkBitcoinCommand ADDR        = new WkBitcoinCommand(WkBitcoinCommandName.ADDR);
    public static final WkBitcoinCommand INV         = new WkBitcoinCommand(WkBitcoinCommandName.INV);
    public static final WkBitcoinCommand GETDATA     = new WkBitcoinCommand(WkBitcoinCommandName.GETDATA);
    public static final WkBitcoinCommand NOTFOUND    = new WkBitcoinCommand(WkBitcoinCommandName.NOTFOUND);
    public static final WkBitcoinCommand GETBLOCKS   = new WkBitcoinCommand(WkBitcoinCommandName.GETBLOCKS);
    public static final WkBitcoinCommand GETHEADERS  = new WkBitcoinCommand(WkBitcoinCommandName.GETHEADERS);
    public static final WkBitcoinCommand TX          = new WkBitcoinCommand(WkBitcoinCommandName.TX);
    public static final WkBitcoinCommand BLOCK       = new WkBitcoinCommand(WkBitcoinCommandName.BLOCK);
    public static final WkBitcoinCommand HEADERS     = new WkBitcoinCommand(WkBitcoinCommandName.HEADERS);
    public static final WkBitcoinCommand GETADDR     = new WkBitcoinCommand(WkBitcoinCommandName.GETADDR);
    public static final WkBitcoinCommand MEMPOOL     = new WkBitcoinCommand(WkBitcoinCommandName.MEMPOOL);
    public static final WkBitcoinCommand CHECKORDER  = new WkBitcoinCommand(WkBitcoinCommandName.CHECKORDER);
    public static final WkBitcoinCommand SUBMITORDER = new WkBitcoinCommand(WkBitcoinCommandName.SUBMITORDER);
    public static final WkBitcoinCommand REPLY       = new WkBitcoinCommand(WkBitcoinCommandName.REPLY);
    public static final WkBitcoinCommand PING        = new WkBitcoinCommand(WkBitcoinCommandName.PING);
    public static final WkBitcoinCommand PONG        = new WkBitcoinCommand(WkBitcoinCommandName.PONG);
    public static final WkBitcoinCommand REJECT      = new WkBitcoinCommand(WkBitcoinCommandName.REJECT);
    public static final WkBitcoinCommand FILTERLOAD  = new WkBitcoinCommand(WkBitcoinCommandName.FILTERLOAD);
    public static final WkBitcoinCommand FILTERADD   = new WkBitcoinCommand(WkBitcoinCommandName.FILTERADD);
    public static final WkBitcoinCommand FILTERCLEAR = new WkBitcoinCommand(WkBitcoinCommandName.FILTERCLEAR);
    public static final WkBitcoinCommand MERKLEBLOCK = new WkBitcoinCommand(WkBitcoinCommandName.MERKLEBLOCK);
    public static final WkBitcoinCommand ALERT       = new WkBitcoinCommand(WkBitcoinCommandName.ALERT);
    public static final WkBitcoinCommand SENDHEADERS = new WkBitcoinCommand(WkBitcoinCommandName.SENDHEADERS);
    public static final WkBitcoinCommand FEEFILTER   = new WkBitcoinCommand(WkBitcoinCommandName.FEEFILTER);
    public static final WkBitcoinCommand SENDCMPCT   = new WkBitcoinCommand(WkBitcoinCommandName.SENDCMPCT);
    public static final WkBitcoinCommand CMPCTBLOCK  = new WkBitcoinCommand(WkBitcoinCommandName.CMPCTBLOCK);
    public static final WkBitcoinCommand GETBLOCKTXN = new WkBitcoinCommand(WkBitcoinCommandName.GETBLOCKTXN);
    public static final WkBitcoinCommand BLOCKTXN    = new WkBitcoinCommand(WkBitcoinCommandName.BLOCKTXN);

    private static final Map<WkByteArray, WkBitcoinCommand> CMD_BY_NAME;

    static {
        CMD_BY_NAME = new HashMap<>();
        CMD_BY_NAME.put(VERSION.bytes,     VERSION);
        CMD_BY_NAME.put(VERACK.bytes,      VERACK);
        CMD_BY_NAME.put(ADDR.bytes,        ADDR);
        CMD_BY_NAME.put(INV.bytes,         INV);
        CMD_BY_NAME.put(GETDATA.bytes,     GETDATA);
        CMD_BY_NAME.put(NOTFOUND.bytes,    NOTFOUND);
        CMD_BY_NAME.put(GETBLOCKS.bytes,   GETBLOCKS);
        CMD_BY_NAME.put(GETHEADERS.bytes,  GETHEADERS);
        CMD_BY_NAME.put(TX.bytes,          TX);
        CMD_BY_NAME.put(BLOCK.bytes,       BLOCK);
        CMD_BY_NAME.put(HEADERS.bytes,     HEADERS);
        CMD_BY_NAME.put(GETADDR.bytes,     GETADDR);
        CMD_BY_NAME.put(MEMPOOL.bytes,     MEMPOOL);
        CMD_BY_NAME.put(CHECKORDER.bytes,  CHECKORDER);
        CMD_BY_NAME.put(SUBMITORDER.bytes, SUBMITORDER);
        CMD_BY_NAME.put(REPLY.bytes,       REPLY);
        CMD_BY_NAME.put(PING.bytes,        PING);
        CMD_BY_NAME.put(PONG.bytes,        PONG);
        CMD_BY_NAME.put(REJECT.bytes,      REJECT);
        CMD_BY_NAME.put(FILTERLOAD.bytes,  FILTERLOAD);
        CMD_BY_NAME.put(FILTERADD.bytes,   FILTERADD);
        CMD_BY_NAME.put(FILTERCLEAR.bytes, FILTERCLEAR);
        CMD_BY_NAME.put(MERKLEBLOCK.bytes, MERKLEBLOCK);
        CMD_BY_NAME.put(ALERT.bytes,       ALERT);
        CMD_BY_NAME.put(SENDHEADERS.bytes, SENDHEADERS);
        CMD_BY_NAME.put(FEEFILTER.bytes,   FEEFILTER);
        CMD_BY_NAME.put(SENDCMPCT.bytes,   SENDCMPCT);
        CMD_BY_NAME.put(CMPCTBLOCK.bytes,  CMPCTBLOCK);
        CMD_BY_NAME.put(GETBLOCKTXN.bytes, GETBLOCKTXN);
        CMD_BY_NAME.put(BLOCKTXN.bytes,    BLOCKTXN);
    }

    public final Optional<WkBitcoinCommandName> name;

    public final WkByteArray bytes;

    /**
     * Cached hash code.
     */
    private final int hashCode;

    /**
     * Creates a new instance from the {@link WkBitcoinCommandName} string value. The bytes are
     * generated from the string value.
     *
     * @param name Is the already defined Bitcoin commmand name.
     */
    private WkBitcoinCommand(WkBitcoinCommandName name) {
        this.name = Optional.of(name);
        final byte[] buff = asCanonicalByteArray(name.text);
        this.bytes  = new WkByteArray(buff);
        this.hashCode = computeHashCode();
    }

    /**
     * Creates a new instance without an associated Bitcoin command.
     *
     * @param wrapper Is the byte array wrapper with the command bytes.
     */
    private WkBitcoinCommand(WkByteArray wrapper) {
        this.name = Optional.empty();
        this.bytes = Objects.requireNonNull(wrapper);
        this.hashCode = computeHashCode();
    }

    @Override
    public String toString() {
        if (name.isPresent())
            return name.get().name();
        else
            return "UNKNOWN";
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }

    private int computeHashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (name.isPresent() ? name.hashCode() : 0);
        result = prime * result + bytes.hashCode();
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
        if ( ! name.isPresent()) {
            if (other.name.isPresent())
                return false;
        } else if (!name.get().equals(other.name.orElse(null)))
            return false;
        return true;
    }

    /* ========================================================================================= */

    /* Retrieved from: https://stackoverflow.com/a/9855338 */
    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes, int from, int to) {
        PrintStream out = new PrintStream(new ByteArrayOutputStream());
        bytesToHex(out, bytes, from ,to);
        return out.toString();
    }

    /**
     *
     * @param out
     * @param bytes
     * @param from Is the byte position of the first byte to be processed inclusively.
     * @param to Is the position after the last byte to be printed (i.e., exclusively).
     */
    public static void bytesToHex(PrintStream out, byte[] bytes, int from, int to) {
        if (    (from > to)
             || (from < 0)
             || (to < 0)
             || (from >= bytes.length)
             || (to > bytes.length)) {
            throw new IndexOutOfBoundsException();
        }
        char nibble = 0;
        for ( int j = from; j < to; j++ ) {
            int v = bytes[j] & 0xFF;
            nibble = hexArray[v >>> 4];
            out.print(nibble);
            nibble = hexArray[v & 0x0F];
            out.print(nibble);
        }
    }

}
