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

import weliyek.ketza.util.array.ByteArrayWrapper;

public class BitcoinCommand
{

    public static BitcoinCommand newCommand(byte[] array, int offset, int length) {
        byte[] array_copy = Arrays.copyOfRange(array, offset, offset + length);
        ByteArrayWrapper wrapper = new ByteArrayWrapper(array_copy);
        return newCommand(wrapper);
    }

    public static BitcoinCommand newCommand(ByteArrayWrapper wrapper) {
        if (CANONICAL_LENGTH != wrapper.getLength())
            throw new IllegalArgumentException();
        BitcoinCommand cmd = CMD_BY_NAME.get(wrapper);
        if (null == cmd)
            cmd = new BitcoinCommand(wrapper);
        return cmd;
    }

    public static byte[] asCanonicalByteArray(String str) {
        return Arrays.copyOf(str.getBytes(StandardCharsets.UTF_8), CANONICAL_LENGTH);
    }

    public static final int CANONICAL_LENGTH = 12;

    public static final BitcoinCommand VERSION     = new BitcoinCommand(BitcoinCommandName.VERSION);
    public static final BitcoinCommand VERACK      = new BitcoinCommand(BitcoinCommandName.VERACK);
    public static final BitcoinCommand ADDR        = new BitcoinCommand(BitcoinCommandName.ADDR);
    public static final BitcoinCommand INV         = new BitcoinCommand(BitcoinCommandName.INV);
    public static final BitcoinCommand GETDATA     = new BitcoinCommand(BitcoinCommandName.GETDATA);
    public static final BitcoinCommand NOTFOUND    = new BitcoinCommand(BitcoinCommandName.NOTFOUND);
    public static final BitcoinCommand GETBLOCKS   = new BitcoinCommand(BitcoinCommandName.GETBLOCKS);
    public static final BitcoinCommand GETHEADERS  = new BitcoinCommand(BitcoinCommandName.GETHEADERS);
    public static final BitcoinCommand TX          = new BitcoinCommand(BitcoinCommandName.TX);
    public static final BitcoinCommand BLOCK       = new BitcoinCommand(BitcoinCommandName.BLOCK);
    public static final BitcoinCommand HEADERS     = new BitcoinCommand(BitcoinCommandName.HEADERS);
    public static final BitcoinCommand GETADDR     = new BitcoinCommand(BitcoinCommandName.GETADDR);
    public static final BitcoinCommand MEMPOOL     = new BitcoinCommand(BitcoinCommandName.MEMPOOL);
    public static final BitcoinCommand CHECKORDER  = new BitcoinCommand(BitcoinCommandName.CHECKORDER);
    public static final BitcoinCommand SUBMITORDER = new BitcoinCommand(BitcoinCommandName.SUBMITORDER);
    public static final BitcoinCommand REPLY       = new BitcoinCommand(BitcoinCommandName.REPLY);
    public static final BitcoinCommand PING        = new BitcoinCommand(BitcoinCommandName.PING);
    public static final BitcoinCommand PONG        = new BitcoinCommand(BitcoinCommandName.PONG);
    public static final BitcoinCommand REJECT      = new BitcoinCommand(BitcoinCommandName.REJECT);
    public static final BitcoinCommand FILTERLOAD  = new BitcoinCommand(BitcoinCommandName.FILTERLOAD);
    public static final BitcoinCommand FILTERADD   = new BitcoinCommand(BitcoinCommandName.FILTERADD);
    public static final BitcoinCommand FILTERCLEAR = new BitcoinCommand(BitcoinCommandName.FILTERCLEAR);
    public static final BitcoinCommand MERKLEBLOCK = new BitcoinCommand(BitcoinCommandName.MERKLEBLOCK);
    public static final BitcoinCommand ALERT       = new BitcoinCommand(BitcoinCommandName.ALERT);
    public static final BitcoinCommand SENDHEADERS = new BitcoinCommand(BitcoinCommandName.SENDHEADERS);
    public static final BitcoinCommand FEEFILTER   = new BitcoinCommand(BitcoinCommandName.FEEFILTER);
    public static final BitcoinCommand SENDCMPCT   = new BitcoinCommand(BitcoinCommandName.SENDCMPCT);
    public static final BitcoinCommand CMPCTBLOCK  = new BitcoinCommand(BitcoinCommandName.CMPCTBLOCK);
    public static final BitcoinCommand GETBLOCKTXN = new BitcoinCommand(BitcoinCommandName.GETBLOCKTXN);
    public static final BitcoinCommand BLOCKTXN    = new BitcoinCommand(BitcoinCommandName.BLOCKTXN);

    private static final Map<ByteArrayWrapper, BitcoinCommand> CMD_BY_NAME;

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

    public final Optional<BitcoinCommandName> name;

    public final ByteArrayWrapper bytes;

    /**
     * Cached hash code.
     */
    private final int hashCode;

    /**
     * Creates a new instance from the {@link BitcoinCommandName} string value. The bytes are
     * generated from the string value.
     *
     * @param name Is the already defined Bitcoin commmand name.
     */
    private BitcoinCommand(BitcoinCommandName name) {
        this.name = Optional.of(name);
        final byte[] buff = asCanonicalByteArray(name.text);
        this.bytes  = new ByteArrayWrapper(buff);
        this.hashCode = computeHashCode();
    }

    /**
     * Creates a new instance without an associated Bitcoin command.
     *
     * @param wrapper Is the byte array wrapper with the command bytes.
     */
    private BitcoinCommand(ByteArrayWrapper wrapper) {
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
        if (!(obj instanceof BitcoinCommand))
            return false;
        BitcoinCommand other = (BitcoinCommand) obj;
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
