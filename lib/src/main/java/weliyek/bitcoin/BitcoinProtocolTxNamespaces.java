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

import weliyek.amat.base.namespace.AmatNamespace;

public class BitcoinProtocolTxNamespaces
        extends AmatNamespace
{

    public final AmatNamespace segwitInfo;
    public final AmatNamespace segwitInfoMarker;
    public final AmatNamespace segwitInfoFlag;
    public final AmatNamespace version;
    public final AmatNamespace txinList;
    public final AmatNamespace txinListSize;
    public final AmatNamespace txinListElem;
    public final BitcoinProtocolTxInNamespaces txin;
    public final AmatNamespace txoutList;
    public final AmatNamespace txoutListSize;
    public final AmatNamespace txoutListElem;
    public final BitcoinProtocolTxOutNamespaces txout;
    public final AmatNamespace locktime;

    public BitcoinProtocolTxNamespaces(AmatNamespace parentNamespace) {
        super(parentNamespace, BitcoinProtocolName.TX, parentNamespace.factory());
        segwitInfo = newChildNamespace(BitcoinProtocolName.TX_SEGWIT_INFO);
        segwitInfoMarker = segwitInfo.newChildNamespace(BitcoinProtocolName.TX_SEGWIT_INFO_MARKER);
        segwitInfoFlag = segwitInfo.newChildNamespace(BitcoinProtocolName.TX_SEGWIT_INFO_FLAG);
        version = newChildNamespace(BitcoinProtocolName.TX_VERSION);
        txinList = newChildNamespace(BitcoinProtocolName.TXIN_LIST);
        txinListSize = txinList.newChildNamespace(BitcoinProtocolName.TXIN_LIST_SIZE);
        txinListElem = txinList.newChildNamespace(BitcoinProtocolName.TXIN_LIST_ELEMENT);
        txin = new BitcoinProtocolTxInNamespaces(txinListElem);
        txoutList = newChildNamespace(BitcoinProtocolName.TXOUT_LIST);
        txoutListSize = txoutList.newChildNamespace(BitcoinProtocolName.TXOUT_LIST_SIZE);
        txoutListElem = txoutList.newChildNamespace(BitcoinProtocolName.TXOUT_LIST_ELEMENT);
        txout = new BitcoinProtocolTxOutNamespaces(txinListElem);
        locktime = newChildNamespace(BitcoinProtocolName.TX_LOCKTIME);
    }

}
