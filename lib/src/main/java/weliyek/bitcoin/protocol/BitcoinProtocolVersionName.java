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
package weliyek.bitcoin.protocol;

import java.util.HashMap;
import java.util.Map;

public enum BitcoinProtocolVersionName {
    /** Initial protocol version, to be increased after version/verack negotiation */
    INIT_PROTO(209),

    /** In this version, {@link BitcoinMessageCommand#GETHEADERS} was introduced. */
    GETHEADERS(31800),

    /** disconnect from peers older than this proto version */
    MIN_PEER_PROTO(31800),

    /** {@link NetAddr#time} starting with this version; if possible, avoid
     *  requesting addresses nodes older than this. */
    CADDR_TIME(31402),

    /** BIP 0031, {@link BitcoinMessageCommand#PONG} message, is enabled for
     *  all versions <b>AFTER</b> this one. */
    BIP0031(60000),

    /** Filter commands are disabled without this version inclusively. */
    NO_BLOOM(70011),

    /** {@link BitcoinMessageCommand#SENDHEADERS} command and announcing blocks
     *  with headers starts with this version. Version after which witness
     *  support potentially exists. */
    SENDHEADERS(70012),

    /** {@link BitcoinMessageCommand#FEEFILTER} tells peers to filter
     *  {@link InventoryVector} to you by fee starts with this version. */
    FEEFILTER(70013),

    /** Short-id-based block download starts with this version. */
    SHORT_IDS_BLOCKS(70014),

    /** Not banning for invalid compact blocks starts with this version. */
    INVALID_CB_NO_BAN_VERSION(70015),

    UNNAMED(-1);

    public final long value;

    static final Map<Long, BitcoinProtocolVersionName> NAME_BY_VALUE_MAP;

    static {
        NAME_BY_VALUE_MAP = new HashMap<Long, BitcoinProtocolVersionName>();
        NAME_BY_VALUE_MAP.put(INIT_PROTO.value, INIT_PROTO);
        NAME_BY_VALUE_MAP.put(GETHEADERS.value, GETHEADERS);
        // Not adding MIN_PEER_PROTO since it's the same as GETHEADERS
        NAME_BY_VALUE_MAP.put(CADDR_TIME.value, CADDR_TIME);
        NAME_BY_VALUE_MAP.put(BIP0031.value, BIP0031);
        NAME_BY_VALUE_MAP.put(NO_BLOOM.value, NO_BLOOM);
        NAME_BY_VALUE_MAP.put(SENDHEADERS.value, SENDHEADERS);
        NAME_BY_VALUE_MAP.put(FEEFILTER.value, FEEFILTER);
        NAME_BY_VALUE_MAP.put(SHORT_IDS_BLOCKS.value, SHORT_IDS_BLOCKS);
        NAME_BY_VALUE_MAP.put(INVALID_CB_NO_BAN_VERSION.value, INVALID_CB_NO_BAN_VERSION);
    }

    private BitcoinProtocolVersionName(long val) {
        this.value = val;
    }

    public static BitcoinProtocolVersionName fromLong(long l) {
        return NAME_BY_VALUE_MAP.get(l);
    }

}