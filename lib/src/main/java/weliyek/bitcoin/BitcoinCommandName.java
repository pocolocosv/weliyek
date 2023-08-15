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

public enum BitcoinCommandName {
    VERSION    ("version"),
    VERACK     ("verack"),
    ADDR       ("addr"),
    INV        ("inv"),
    GETDATA    ("getdata"),
    NOTFOUND   ("notfound"),
    GETBLOCKS  ("getblocks"),
    GETHEADERS ("getheaders"),
    TX         ("tx"),
    BLOCK      ("block"),
    HEADERS    ("headers"),
    GETADDR    ("getaddr"),
    MEMPOOL    ("mempool"),
    CHECKORDER ("checkorder"),
    SUBMITORDER("submitorder"),
    REPLY      ("reply"),
    PING       ("ping"),
    PONG       ("pong"),
    REJECT     ("reject"),
    FILTERLOAD ("filterload"),
    FILTERADD  ("filteradd"),
    FILTERCLEAR("filterclear"),
    MERKLEBLOCK("merkleblock"),
    ALERT      ("alert"),
    SENDHEADERS("sendheaders"),
    FEEFILTER  ("feefilter"),
    SENDCMPCT  ("sendcmpct"),
    CMPCTBLOCK ("cmpctblock"),
    GETBLOCKTXN("getblocktxn"),
    BLOCKTXN   ("blocktxn"),
    UNKNOWN    ("");

    public static final int CANONICAL_LENGTH = 12;

    final String text;

    private static final Map<String, BitcoinCommandName> NAME_BY_TEXT_MAP;

    static {
        NAME_BY_TEXT_MAP = new HashMap<>();
        NAME_BY_TEXT_MAP.put(VERSION.text,     VERSION);
        NAME_BY_TEXT_MAP.put(VERACK.text,      VERACK);
        NAME_BY_TEXT_MAP.put(ADDR.text,        ADDR);
        NAME_BY_TEXT_MAP.put(INV.text,         INV);
        NAME_BY_TEXT_MAP.put(GETDATA.text,     GETDATA);
        NAME_BY_TEXT_MAP.put(NOTFOUND.text,    NOTFOUND);
        NAME_BY_TEXT_MAP.put(GETBLOCKS.text,   GETBLOCKS);
        NAME_BY_TEXT_MAP.put(GETHEADERS.text,  GETHEADERS);
        NAME_BY_TEXT_MAP.put(TX.text,          TX);
        NAME_BY_TEXT_MAP.put(BLOCK.text,       BLOCK);
        NAME_BY_TEXT_MAP.put(HEADERS.text,     HEADERS);
        NAME_BY_TEXT_MAP.put(GETADDR.text,     GETADDR);
        NAME_BY_TEXT_MAP.put(MEMPOOL.text,     MEMPOOL);
        NAME_BY_TEXT_MAP.put(CHECKORDER.text,  CHECKORDER);
        NAME_BY_TEXT_MAP.put(SUBMITORDER.text, SUBMITORDER);
        NAME_BY_TEXT_MAP.put(REPLY.text,       REPLY);
        NAME_BY_TEXT_MAP.put(PING.text,        PING);
        NAME_BY_TEXT_MAP.put(PONG.text,        PONG);
        NAME_BY_TEXT_MAP.put(REJECT.text,      REJECT);
        NAME_BY_TEXT_MAP.put(FILTERLOAD.text,  FILTERLOAD);
        NAME_BY_TEXT_MAP.put(FILTERADD.text,   FILTERADD);
        NAME_BY_TEXT_MAP.put(FILTERCLEAR.text, FILTERCLEAR);
        NAME_BY_TEXT_MAP.put(MERKLEBLOCK.text, MERKLEBLOCK);
        NAME_BY_TEXT_MAP.put(ALERT.text,       ALERT);
        NAME_BY_TEXT_MAP.put(SENDHEADERS.text, SENDHEADERS);
        NAME_BY_TEXT_MAP.put(FEEFILTER.text,   FEEFILTER);
        NAME_BY_TEXT_MAP.put(SENDCMPCT.text,   SENDCMPCT);
        NAME_BY_TEXT_MAP.put(CMPCTBLOCK.text,  CMPCTBLOCK);
        NAME_BY_TEXT_MAP.put(GETBLOCKTXN.text, GETBLOCKTXN);
        NAME_BY_TEXT_MAP.put(BLOCKTXN.text,    BLOCKTXN);
    }

    BitcoinCommandName(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static BitcoinCommandName fromString(String str) {
        BitcoinCommandName name = NAME_BY_TEXT_MAP.get(str);
        if (null == name) {
            return BitcoinCommandName.UNKNOWN;
        }
        return name;
    }

}