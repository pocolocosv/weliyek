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

public class BitcoinMsgCommissioners
{

    final BitcoinMsgAddrTools         addrTools = new BitcoinMsgAddrTools();
    final BitcoinMsgGetDataTools   getDataTools = new BitcoinMsgGetDataTools();
    final BitcoinMsgHeadersTools   headersTools = new BitcoinMsgHeadersTools();
    final BitcoinMsgTxTools             txTools = new BitcoinMsgTxTools();
    final BitcoinMsgVersionTools   versionTools = new BitcoinMsgVersionTools();
    final BitcoinMsgNotFoundTools notFoundTools = new BitcoinMsgNotFoundTools();
    final BitcoinMsgGetBlocksTools getBlocksTools = new BitcoinMsgGetBlocksTools();
    final BitcoinMsgRejectTools rejectTools = new BitcoinMsgRejectTools();


    protected PayloadCoreKernelTools<? extends PayloadCoreKernelRO<?>, ? extends PayloadCoreKernelRW<?>>
    select(BitcoinCommand command) {
        switch(command.name) {
        case MEMPOOL:
        case VERACK:
            // No tools available.
            break;
        case ADDR:
        case GETDATA:
        case HEADERS:
        case TX:
        case VERSION:
        case NOTFOUND:
        case GETBLOCKS:
        case REJECT:
        case ALERT:
        case BLOCK:
        case BLOCKTXN:
        case CHECKORDER:
        case CMPCTBLOCK:
        case FEEFILTER:
        case FILTERADD:
        case FILTERCLEAR:
        case FILTERLOAD:
        case GETADDR:
        case GETBLOCKTXN:
        case GETHEADERS:
        case INV:
        case MERKLEBLOCK:
        case PING:
        case PONG:
        case REPLY:
        case SENDCMPCT:
        case SENDHEADERS:
        case SUBMITORDER:
        default:
            throw new UnsupportedOperationException();
        }
        return null;
    }

}
