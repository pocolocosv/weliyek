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
package weliyek.amat.bitcoin.protocol.message.payload;

import java.util.ArrayList;
import java.util.List;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolName;
import weliyek.bitcoin.protocol.tx.BitcoinProtocolTxIn;
import weliyek.bitcoin.protocol.tx.BitcoinProtocolTxOut;

public abstract class BitcoinMsgTxKernelAbstract
                            <I extends BitcoinProtocolTxIn,
                             O extends BitcoinProtocolTxOut,
                             K extends BitcoinMsgTxKernelAbstract<I,O,K>>
        extends PayloadCoreKernelAbstract<K>
        implements BitcoinMsgTx
{

    private int version;
    private long locktime;
    protected final List<I> txInList = new ArrayList<>();
    protected final List<O> txOutList = new ArrayList<>();

    AmatNamespace txNamespace;
    AmatNamespace txVersionNamespace;
    AmatNamespace txSegwitInfoNamespace;
    AmatNamespace txSegwitInfoMarkerNamespace;
    AmatNamespace txSegwitInfoFlagNamespace;
    AmatNamespace txInListSizeNamespace;
    AmatNamespace txInListElementOutpointHashNamespace;
    AmatNamespace txInListElementOutpointIndexNamespace;
    AmatNamespace txInListElementScriptNamespace;
    AmatNamespace txInListElementScriptSizeNamespace;
    AmatNamespace txInListElementScriptOpCodeNamespace;
    AmatNamespace txInListElementScriptOpCodeDataSizeNamespace;
    AmatNamespace txInListElementScriptOpCodeDataBodyNamespace;
    AmatNamespace txInListElementSequenceNamespace;
    AmatNamespace txInListElementSegwitListSizeNamespace;
    AmatNamespace txInListElementSegwitListElementScriptNamespace;
    AmatNamespace txInListElementSegwitListElementScriptSizeNamespace;
    AmatNamespace txInListElementSegwitListElementScriptOpNamespace;
    AmatNamespace txInListElementSegwitListElementScriptOpDataSizeNamespace;
    AmatNamespace txInListElementSegwitListElementScriptOpDataBodyNamespace;
    AmatNamespace txOutListSizeNamespace;
    AmatNamespace txOutListElementValueNamespace;
    AmatNamespace txOutListElementScriptNamespace;
    AmatNamespace txOutListElementScriptSizeNamespace;
    AmatNamespace txOutListElementScriptOpCodeNamespace;
    AmatNamespace txOutListElementScriptOpCodeDataSizeNamespace;
    AmatNamespace txOutListElementScriptOpCodeDataBodyNamespace;
    AmatNamespace txLocktimeNamespace;

    @Override
    protected void onOwnerSetting() {
        AmatNamespace payload = ownerCore().namespace().newChildNamespace(BitcoinProtocolName.MESSAGE_PAYLOAD);
        txNamespace = payload.newChildNamespace(BitcoinProtocolName.TX);
        txVersionNamespace = txNamespace.newChildNamespace(BitcoinProtocolName.TX_VERSION);
        txSegwitInfoNamespace = txNamespace.newChildNamespace(BitcoinProtocolName.TX_SEGWIT_INFO);
        txSegwitInfoMarkerNamespace = txSegwitInfoNamespace.newChildNamespace(BitcoinProtocolName.TX_SEGWIT_INFO_MARKER);
        txSegwitInfoFlagNamespace = txSegwitInfoNamespace.newChildNamespace(BitcoinProtocolName.TX_SEGWIT_INFO_FLAG);
        AmatNamespace tx_txin = txNamespace.newChildNamespace(BitcoinProtocolName.TXIN);
        AmatNamespace tx_txinList = txNamespace.newChildNamespace(BitcoinProtocolName.TXIN_LIST);
        txInListSizeNamespace = tx_txinList.newChildNamespace(BitcoinProtocolName.TXIN_LIST_SIZE);
        AmatNamespace txinListElement = tx_txinList.newChildNamespace(BitcoinProtocolName.TXIN_LIST_ELEMENT);
        AmatNamespace txin_outpoint = txinListElement.newChildNamespace(BitcoinProtocolName.OUTPOINT);
        txInListElementOutpointHashNamespace = txin_outpoint.newChildNamespace(BitcoinProtocolName.HASH);
        txInListElementOutpointIndexNamespace = txin_outpoint.newChildNamespace(BitcoinProtocolName.OUTPOINT_INDEX);
        txInListElementScriptNamespace = txinListElement.newChildNamespace(BitcoinProtocolName.SCRIPT);
        txInListElementScriptSizeNamespace = txInListElementScriptNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_SIZE);
        AmatNamespace txinListScriptBody = txInListElementScriptNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_BODY);
        txInListElementScriptOpCodeNamespace = txinListScriptBody.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_CODE);
        txInListElementScriptOpCodeDataSizeNamespace = txinListScriptBody.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_DATA_SIZE);
        txInListElementScriptOpCodeDataBodyNamespace = txinListScriptBody.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_DATA_BODY);
        txInListElementSequenceNamespace = txinListElement.newChildNamespace(BitcoinProtocolName.TXIN_SEQUENCE);
        AmatNamespace segwitList = txinListElement.newChildNamespace(BitcoinProtocolName.SEGWIT_LIST);
        txInListElementSegwitListSizeNamespace = segwitList.newChildNamespace(BitcoinProtocolName.SEGWIT_LIST_SIZE);
        AmatNamespace segwitListElem = segwitList.newChildNamespace(BitcoinProtocolName.SEGWIT_LIST_ELEMENT);
        txInListElementSegwitListElementScriptNamespace = segwitListElem.newChildNamespace(BitcoinProtocolName.SCRIPT);
        txInListElementSegwitListElementScriptSizeNamespace = txInListElementSegwitListElementScriptNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_SIZE);
        AmatNamespace segwitListElemScriptBody = txInListElementSegwitListElementScriptNamespace.newChildNamespace(BitcoinProtocolName.SCRIPT_BODY);
        txInListElementSegwitListElementScriptOpNamespace = segwitListElemScriptBody.newChildNamespace(BitcoinProtocolName.SCRIPT);
        txInListElementSegwitListElementScriptOpDataSizeNamespace = segwitListElemScriptBody.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_DATA_SIZE);
        txInListElementSegwitListElementScriptOpDataBodyNamespace = segwitListElemScriptBody.newChildNamespace(BitcoinProtocolName.SCRIPT_OP_DATA_BODY);
        AmatNamespace txOut = txNamespace.newChildNamespace(BitcoinProtocolName.TXOUT);
        AmatNamespace txOutList = txNamespace.newChildNamespace(BitcoinProtocolName.TXOUT_LIST);
        txOutListSizeNamespace = txOutList.newChildNamespace(BitcoinProtocolName.TXOUT_LIST_SIZE);
        AmatNamespace txOutListElement = txOutList.newChildNamespace(BitcoinProtocolName.TXOUT_LIST_ELEMENT);;
        txOutListElementValueNamespace = txOutListElement.newChildNamespace(BitcoinProtocolName.TXOUT_VALUE);;
        txOutListElementScriptNamespace;
        txOutListElementScriptSizeNamespace;
        txOutListElementScriptOpCodeNamespace;
        txOutListElementScriptOpCodeDataSizeNamespace;
        txOutListElementScriptOpCodeDataBodyNamespace;
        txLocktimeNamespace;
    }

    @Override
    protected void onPayloadDecommission() {
        resetFields();
    }

    @Override
    public abstract List<I> txInList();

    @Override
    public abstract List<O> txOutList();

    protected void resetFields() {
        setVersion(0);
        setLocktime(0);
        txInList.clear();
        txOutList.clear();
    }

    @Override
    public int version() {
        return version;
    }

    protected void setVersion(int ver) {
        this.version = ver;
    }

    @Override
    public long locktime() {
        return locktime;
    }

    protected void setLocktime(long val) {
        this.locktime = val;
    }

}
