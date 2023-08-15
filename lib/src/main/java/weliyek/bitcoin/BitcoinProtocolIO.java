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
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.base.AmatProtocolUtil;

public class BitcoinProtocolIO extends AmatProtocolUtil
{
    public static final Logger logger = LoggerFactory.getLogger(BitcoinProtocolIO.class);

    // VarInt ################################################################

    public static final long VARINT_SHORT_LIMIT  = 0xFFFF;
    public static final long VARINT_INT_LIMIT    = 0xFFFF_FFFFL;
    public static final  int VARINT_HEADER_SHORT = 0xFD;
    public static final  int VARINT_HEADER_INT   = 0xFE;
    public static final  int VARINT_HEADER_LONG  = 0xFF;

    public static boolean isEncodedableAsVarIntWithHeaderAndLongBody(long val)  {
        return val >> 32 != 0;
    }

    public static boolean isEncodedableAsVarIntWithHeaderAndIntBody(long val) {
        return (val >> 32 == 0) && ((VARINT_INT_LIMIT & val) != 0);
    }

    public static boolean isEncodedableAsVarIntWithHeaderAndShortBody(long val) {
        return (val >> 16 == 0L) && ((VARINT_SHORT_LIMIT & val) != 0);
    }

    public static boolean isEncodedableAsVarIntWithHeaderOnly(long val) {
        return (val >> 8 == 0L) && ((0xFF & val) < VARINT_HEADER_SHORT);
    }

    public static void writeAsVarInt(long val, OutputStream  out, AmatNamespace namespace) {
        final AmatNamespace varintNmspc = namespace.newChildNamespace(BitcoinProtocolName.VARINT);

        final AmatNamespace headerNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_HEADER);
        if (logger.isDebugEnabled())
            logger.debug("{} writing VarInt header ...", headerNmspc);
        if (isEncodedableAsVarIntWithHeaderOnly(val)) {
            AmatProtocolUtil.writeByte(out, (int) val, headerNmspc);
            return;
        }

        final AmatNamespace bodyNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_BODY);
        if (isEncodedableAsVarIntWithHeaderAndShortBody(val)) {
            AmatProtocolUtil.writeByte(out, VARINT_HEADER_SHORT, headerNmspc);
            if (logger.isDebugEnabled())
                logger.debug("{} writing VarInt short body ...", bodyNmspc);
            AmatProtocolUtil.writeLittleEndianShort(out, (int) val, bodyNmspc);
        } else if (isEncodedableAsVarIntWithHeaderAndIntBody(val)) {
            AmatProtocolUtil.writeByte(out, VARINT_HEADER_INT, headerNmspc);
            if (logger.isDebugEnabled())
                logger.debug("{} writing VarInt int body ...", bodyNmspc);
            AmatProtocolUtil.writeLittleEndianInt(out, val, bodyNmspc);
        } else {
            AmatProtocolUtil.writeByte(out, VARINT_HEADER_LONG, headerNmspc);
            if (logger.isDebugEnabled())
                logger.debug("{} writing VarInt long body ...", bodyNmspc);
            AmatProtocolUtil.writeLittleEndianLong(out, val, bodyNmspc);
        }
    }

    public static long readVarInt(InputStream in, final AmatNamespace namespace) {
        final AmatNamespace varintNmspc = namespace.newChildNamespace(BitcoinProtocolName.VARINT);

        // Read header and return it if less than limit.
        final AmatNamespace headerNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_HEADER);
        if (logger.isDebugEnabled())
            logger.debug("{} reading VarInt header ...", headerNmspc);
        final int header = AmatProtocolUtil.readByte(in, headerNmspc);
        if (header < VARINT_HEADER_SHORT) {
            return header;
        }

        final AmatNamespace bodyNmspc = varintNmspc.newChildNamespace(BitcoinProtocolName.VARINT_BODY);
        if (logger.isDebugEnabled())
            logger.debug("{} reading VarInt body ...", bodyNmspc);
        if (header == VARINT_HEADER_SHORT) {
            return VARINT_SHORT_LIMIT & AmatProtocolUtil.readLittleEndianShort(in, bodyNmspc);
        } else if (header == VARINT_HEADER_INT) {
            return VARINT_INT_LIMIT & AmatProtocolUtil.readLittleEndianInt(in, bodyNmspc);
        } else {
            return AmatProtocolUtil.readLittleEndianLong(in, bodyNmspc);
        }
    }

    // VarStr ################################################################

    public static void writeVarStr(String str, OutputStream out, AmatNamespace namespace) {
        final AmatNamespace varstrNmspc = namespace.newChildNamespace(BitcoinProtocolName.VARSTR);
        final AmatNamespace sizeNmspc = varstrNmspc.newChildNamespace(BitcoinProtocolName.VARSTR_SIZE);
        byte[] strBuf = str.getBytes(StandardCharsets.UTF_8);
        if (logger.isDebugEnabled())
            logger.debug("{} writing VarStr size ...", sizeNmspc);
        writeAsVarInt(strBuf.length, out, sizeNmspc);
        final AmatNamespace bodyNmspc = varstrNmspc.newChildNamespace(BitcoinProtocolName.VARSTR_BODY);
        if (logger.isDebugEnabled())
            logger.debug("{} writing VarStr string ...", bodyNmspc);
        writeByteArray(out, strBuf, bodyNmspc);
    }

    public static String readVarStr(InputStream in, AmatNamespace namespace) {
        final AmatNamespace varstrNmspc = namespace.newChildNamespace(BitcoinProtocolName.VARSTR);
        final AmatNamespace sizeNmspc = varstrNmspc.newChildNamespace(BitcoinProtocolName.VARSTR_SIZE);
        if (logger.isDebugEnabled())
            logger.debug("{} reading VarStr size ...", sizeNmspc);
        int strLen = (int) readVarInt(in, sizeNmspc);
        final AmatNamespace bodyNmspc = varstrNmspc.newChildNamespace(BitcoinProtocolName.VARSTR_BODY);
        if (logger.isDebugEnabled())
            logger.debug("{} reading VarStr string ...", bodyNmspc);
        byte[] strBuf = new byte[strLen];
        readByteArray(in, strBuf, bodyNmspc);
        String string = new String(strBuf, StandardCharsets.UTF_8);
        if (logger.isDebugEnabled())
            logger.debug("{} read \"{}\"", varstrNmspc, string);
        return string;
    }

}
