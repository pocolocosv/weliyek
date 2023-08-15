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

import java.io.InputStream;
import java.io.OutputStream;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.base.AmatProtocolUtil;

public class BitcoinProtocolIOVarInt
{

    public static final long BYTE_LIMIT = 253;
    public static final long SHORT_LIMIT  = 0xFFFF;
    public static final  int SHORT_HEADER = 0xFD;
    public static final long INT_LIMIT = 0xFFFF_FFFFL;
    public static final  int INT_HEADER = 0xFE;
    public static final  int LONG_HEADER = 0xFF;

    final long value;

    private BitcoinProtocolIOVarInt(long val) {
        this.value = val;
    }

    public static BitcoinProtocolIOVarInt from(long l) {
        return new BitcoinProtocolIOVarInt(l);
    }

    public byte byteValue() {
        return (byte) (0xFF & value);
    }

    public short shortValue() {
        return (short) (0xFFFF & value);
    }

    public int intValue() {
        return (int) (0xFFFF_FFFFL & value);
    }

    public long longValue() {
        return value;
    }

    public static boolean isEncodedAsALong(long val)  {
        return val >> 32 != 0;
    }

    public static boolean isEncodedAsAInt(long val) {
        return (val >> 32 == 0) && ((0xFFFF_FFFFL & val) != 0);
    }

    public static boolean isEncodedAsAShort(long val) {
        return (val >> 16 == 0L) && ((0xFFFF & val) != 0);
    }

    public static boolean isEncodedAsAByte(long val) {
        return (val >> 8 == 0L) && ((0xFF & val) < 0xFD);
    }

    public static void write(long val, OutputStream  out, AmatNamespace namespace) {
        final AmatNamespace varintNmspc = namespace.newChildNamespace(BitcoinProtocolName.VARINT);

        final AmatNamespace headerNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_HEADER);
        if (isEncodedAsAByte(val)) {
            AmatProtocolUtil.writeByte(out, (int) val, headerNmspc);
            return;
        }

        final AmatNamespace bodyNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_BODY);
        if (isEncodedAsAShort(val)) {
            AmatProtocolUtil.writeByte(out, SHORT_HEADER, headerNmspc);
            AmatProtocolUtil.writeLittleEndianShort(out, (int) val, bodyNmspc);
        } else if (isEncodedAsAInt(val)) {
            AmatProtocolUtil.writeByte(out, INT_HEADER, headerNmspc);
            AmatProtocolUtil.writeLittleEndianInt(out, val, bodyNmspc);
        } else {
            AmatProtocolUtil.writeByte(out, LONG_HEADER, headerNmspc);
            AmatProtocolUtil.writeLittleEndianLong(out, val, bodyNmspc);
        }
    }

    public static long read(InputStream in, final AmatNamespace namespace) {
        final AmatNamespace varintNmspc = namespace.newChildNamespace(BitcoinProtocolName.VARINT);

        // Read header and return it if less than limit.
        final AmatNamespace headerNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_HEADER);
        final int header = AmatProtocolUtil.readByte(in, headerNmspc);
        if (header < BYTE_LIMIT) {
            return header;
        }

        final AmatNamespace bodyNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_BODY);
        if (header == SHORT_HEADER) {
            return 0xFFFFL & AmatProtocolUtil.readLittleEndianShort(in, bodyNmspc);
        } else if (header == INT_HEADER) {
            return 0xFFFF_FFFFL & AmatProtocolUtil.readLittleEndianInt(in, bodyNmspc);
        } else {
            return AmatProtocolUtil.readLittleEndianLong(in, bodyNmspc);
        }
    }

}
