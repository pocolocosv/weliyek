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
package weliyek.amat.bitcoin.protocol;

import weliyek.amat.base.AmatSingleName;

public enum BitcoinProtocolName implements AmatSingleName
{
    MESSAGE_RO,
    MESSAGE_RW,

    // Message fields.
    MESSAGE_MAGIC("MAGIC"),
    MESSAGE_COMMAND("COMMAND"),
    MESSAGE_PAYLOAD("PAYLOAD"),
    MESSAGE_PAYLOAD_LENGTH("LENGTH"),
    MESSAGE_PAYLOAD_CHECKSUM("CHECKSUM"),

    // VarInt
    VARINT,
    VARINT_HEADER("HEADER"),
    VARINT_BODY("BODY"),

    // VarStr
    VARSTR,
    VARSTR_SIZE("SIZE"),
    VARSTR_BODY("UTF8"),

    HASH,

    // Inventory Vector
    INV_VEC,
    INV_VEC_TYPE("TYPE"),
    INV_VEC_HASH("HASH"),

    INV_VEC_LIST,
    INV_VEC_LIST_SIZE("SIZE"),
    INV_VEC_LIST_ELEMENT("ELEMENT"),

    COMPLEXITY,

    // Block header
    BLOCK_HEADER,
    BLOCK_HEADER_VERSION("VERSION"),
    BLOCK_HEADER_PREV_HASH("PREV_BLOCK"),
    BLOCK_HEADER_MERKLE_HASH("MERKLE_ROOT"),
    BLOCK_HEADER_TIMESTAMP("TIMESTAMP"),
    BLOCK_HEADER_NONCE("NONCE"),
    BLOCK_HEADER_TXN_COUNT("TXN_COUNT"),

    OUTPOINT,
    OUTPOINT_INDEX("INDEX"),

    // Script
    SCRIPT,
    SCRIPT_SIZE("SIZE"),
    SCRIPT_BODY("BODY"),

    SCRIPT_OP_CODE("OP"),
    SCRIPT_OP_DATA_SIZE("DATA_SIZE"),
    SCRIPT_OP_DATA_BODY("DATA"),

    // Segwit
    SEGWIT_LIST,
    SEGWIT_LIST_SIZE,
    SEGWIT_LIST_ELEMENT,

    NODE_SERVICES,

    TXIN,
    TXIN_SEQUENCE("SEQUENCE"),

    TXIN_LIST,
    TXIN_LIST_SIZE("SIZE"),
    TXIN_LIST_ELEMENT("ELEMENT"),

    TXOUT,
    TXOUT_VALUE("VALUE"),

    TXOUT_LIST("TXOUT_LIST"),
    TXOUT_LIST_SIZE("SIZE"),
    TXOUT_LIST_ELEMENT("ELEMENT"),

    // TX message
    TX,
    TX_VERSION,
    TX_SEGWIT_INFO("SEGWIT"),
    TX_SEGWIT_INFO_MARKER("MARKER"),
    TX_SEGWIT_INFO_FLAG("FLAG"),
    TX_SEGWIT_LIST_ELEMENT("ELEMENT"),
    TX_LOCKTIME("LOCKTIME"),

    // Netaddr
    NETADDR,
    NETADDR_TIME("TIME"),
    NETADDR_SERVICES("SERVICES"),
    NETADDR_ADDRESS("ADDRESS"),
    NETADDR_PORT("PORT"),

    // Version message payload
    VERSION,
    VERSION_NUMBER("NUMBER"),
    VERSION_SERVICES("SERVICES"),
    VERSION_TIMESTAMP("TIMESTAMP"),
    VERSION_RECV("RECV"),
    VERSION_FROM("FROM"),
    VERSION_NONCE("NONCE"),
    VERSION_USER_AGENT("USER_AGENT"),
    VERSION_START_HEIGHT("START_HEIGHT"),
    VERSION_RELAY("RELAY"),

    // Addr
    ADDR,
    ADDR_NETADDR_LIST_SIZE,
    ADDR_NETADDR_LIST_ELEMENT,

    GETDATA,

    GETBLOCKS,
    GETBLOCKS_VERSION("VERSION"),
    GETBLOCKS_HEADER_HASH_LIST("HASH_LIST"),
    GETBLOCKS_HEADER_HASH_LIST_SIZE("SIZE"),
    GETBLOCKS_HEADER_HASH_LIST_ELEMENT("ELEMENT"),
    GETBLOCKS_STOP_HASH("STOP_HASH"),

    // Headers payload
    HEADERS,
    HEADERS_LIST_SIZE("LIST_SIZE"),
    HEADERS_LIST_ELEMENT("LIST_ELEMENT"),

    NOTFOUND,

    REJECT,
    REJECT_MESSAGE("MESSAGE"),
    REJECT_CCODE("CCODE"),
    REJECT_REASON("REASON"),
    REJECT_DATA("DATA");

    private final String str;

    BitcoinProtocolName(String str) {
        this.str = str;
    }

    BitcoinProtocolName() {
        this.str = null;
    }

    @Override
    public String toString() {
        if (null != this.str) {
            return this.str;
        }
        return super.toString();
    }

}
