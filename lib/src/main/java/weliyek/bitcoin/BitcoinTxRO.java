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

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitcoinTxRO
        implements BitcoinTx
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinTxRO.class);

    public final static int WITNESS_MARKER = 0;

    public final static int WITNESS_FLAG = 1;

    protected final int version;

    protected final List<BitcoinTxIn> txInList;

    protected final List<BitcoinTxOut> txOutList;

    protected final long lockTime;

    private final boolean segwitPresent;

    public static BitcoinTxRO build(
            int version,
            List<BitcoinTxIn> txInList,
            List<BitcoinTxOut> txOutList,
            long lockTime) {
        return new BitcoinTxRO(version, txInList, txOutList, lockTime);
    }

    BitcoinTxRO(
            int version,
            List<BitcoinTxIn> txInList,
            List<BitcoinTxOut> txOutList,
            long lockTime) {
        this.version = version;
        this.txInList = new LinkedList<>(txInList);
        this.txOutList = new LinkedList<>(txOutList);
        this.lockTime = lockTime;
        this.segwitPresent = checkIfSegwitArePresent(txInList);
    }

    private boolean checkIfSegwitArePresent(List<BitcoinTxIn> txInList) {
        boolean hasSegwit = false;
        for (BitcoinTxIn txin : txInList) {
            if ( ! txin.segregatedWitness().isEmpty()) {
                hasSegwit = true;
                break;
            }
        }
        return hasSegwit;
    }

    @Override
    public int version() {
        return this.version;
    }

    @Override
    public List<BitcoinTxIn> txInList() {
        return txInList;
    }

    @Override
    public List<BitcoinTxOut> txOutList() {
        return txOutList;
    }

    @Override
    public long locktime() {
        return this.lockTime;
    }

    @Override
    public boolean hasSegregatedWitnesses() {
        return segwitPresent;
    }

    /*
    // writeTo ===============================================================

    protected void writeToOutputStream(OutputStream                out,
                                       BitcoinProtocolTxNamespaces txNamespaces)
    {
        BitcoinProtocolIO.writeLittleEndianInt(out, version, txNamespaces.version);
        final boolean segwitPresent = writeSegwitMarkerAndFlag(out, txNamespaces);
        writeTxInList(out, txNamespaces);
        writeTxOutList(out, txNamespaces);
        writeTxInSegwitList(out, segwitPresent, txNamespaces.txin);
        BitcoinProtocolIO.writeLittleEndianInt(out, lockTime, txNamespaces.locktime);
    }

    private boolean writeSegwitMarkerAndFlag(OutputStream                out,
                                             BitcoinProtocolTxNamespaces txNamespaces)
    {
        boolean segwitPresent = false;
        for (I txIn : txInList) {
            if ( ! txIn.scriptWitness.isEmpty()) {
                segwitPresent = true;
                BitcoinProtocolIO.writeByte(out, WITNESS_MARKER, txNamespaces.segwitInfoMarker);
                BitcoinProtocolIO.writeByte(out, WITNESS_FLAG, txNamespaces.segwitInfoFlag);
                break;
            }
        }
        return segwitPresent;
    }

    private void writeTxInList(OutputStream                out,
                               BitcoinProtocolTxNamespaces txNamespaces)
    {
        BitcoinProtocolIO.writeAsVarInt(txInList.size(), out, txNamespaces.txinListSize);
        for (I txIn : txInList) {
            txIn.writeBodyTo(out, txNamespaces.txin);
        }
    }

    private void writeTxOutList(OutputStream                out,
                                BitcoinProtocolTxNamespaces txNamespaces)
    {
        BitcoinProtocolIO.writeAsVarInt(txOutList.size(), out, txNamespaces.txoutListSize);
        for (O txOut : txOutList) {
            txOut.writeToOutputStream(out, txNamespaces.txout);
        }
    }

    private void writeTxInSegwitList(OutputStream                  out,
                                     boolean                       segwitPresent,
                                     BitcoinProtocolTxInNamespaces txinNamespaces)
    {
        if (segwitPresent) {
            for (I txIn : txInList) {
                writeTxInSegwit(txIn, out, txinNamespaces.segregatedWitness);
            }
        }
    }

    protected abstract void writeTxInSegwit(I txIn,
                                            OutputStream out,
                                            BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces);

    // readFrom() ============================================================

    public void readFrom(InputStream in,
                         BitcoinProtocolTxNamespaces txNamespaces)
    {
        this.version = (int) BitcoinProtocolIO.readLittleEndianInt(in, txNamespaces.version);
        boolean segwitPresent = readSegwitMarkerAndFlag(in, txNamespaces);
        readTxInList(in, txNamespaces);
        readTxOutList(in, txNamespaces);
        readTxInSegwitList(in, segwitPresent, txNamespaces.txin);
        this.lockTime = BitcoinProtocolIO.readLittleEndianInt(in, txNamespaces.locktime);
    }

    private void readTxInList(InputStream in,
                              BitcoinProtocolTxNamespaces txNamespaces)
    {
        long txInListSize = BitcoinProtocolIO.readVarInt(in, txNamespaces.txinListSize);
        this.txInList.clear();
        for(long i = 0; i < txInListSize; i++) {
            I txIn = newTxInOnRead(in, txNamespaces.txin);
            this.txInList.add(txIn);
        }
    }

    protected abstract I newTxInOnRead(InputStream in,
                                       BitcoinProtocolTxInNamespaces txinNamespace);

    private void readTxOutList(InputStream in,
                               BitcoinProtocolTxNamespaces txNamespaces)
    {
        long txOutListSize = BitcoinProtocolIO.readVarInt(in, txNamespaces.txoutListSize);
        this.txOutList.clear();
        for(long i = 0; i < txOutListSize; i++) {
            O txOut = newTxOutOnRead(in, txNamespaces.txout);
            this.txOutList.add(txOut);
        }
    }

    protected abstract O newTxOutOnRead(InputStream in,
                                        BitcoinProtocolTxOutNamespaces txoutNamespaces);

    private void readTxInSegwitList(InputStream in,
                                    boolean segwitPresent,
                                    BitcoinProtocolTxInNamespaces txinNamespaces)
    {
        if (segwitPresent) {
            for (I txIn : this.txInList) {
                readSegwit(txIn, in, txinNamespaces.segregatedWitness);
            }
        }
    }

    protected abstract void readSegwit(I txIn,
                                       InputStream in,
                                       BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces);

    private boolean readSegwitMarkerAndFlag(InputStream in,
                                            BitcoinProtocolTxNamespaces txNamespaces)
    {
        if ( ! in.markSupported()) {
            in = new BufferedInputStream(in, 2);
        }
        // https://bitcoincore.org/en/segwit_wallet_dev/
        in.mark(2);
        final int marker = BitcoinProtocolIO.readByte(in, txNamespaces.segwitInfoMarker);
        final int flag = BitcoinProtocolIO.readByte(in, txNamespaces.segwitInfoFlag);
        if ((WITNESS_MARKER == marker) && (WITNESS_FLAG == flag)) {
            if (logger.isDebugEnabled()) logger.debug("{} segwit flag present", txNamespaces.segwitInfo);
            return true;
        }
        try {
            in.reset();
        } catch (IOException e) {
            throw new AmatProtocolException(txNamespaces.segwitInfo, AmatProtocolOperation.IO_CTRL, e);
        }
        return false;
    }
    */

}
