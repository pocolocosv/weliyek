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
package weliyek.amat.bitcoin.protocol.block;

import java.io.InputStream;
import java.io.OutputStream;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.base.AmatProtocolUtil;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolIOVarInt;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolName;
import weliyek.amat.bitcoin.protocol.Complexity;
import weliyek.bitcoin.BitcoinHash;

public class BlockHeader
{

    public final int version;

    public final BitcoinHash prevBlockHash;

    public final BitcoinHash merkleRootHash;

    public final long timestamp;

    public final Complexity complexity;

    public final long nonce;

    public final int txnCount;

    BlockHeader(int version,
                BitcoinHash prevBlock,
                BitcoinHash merkleRoot,
                long timestamp,
                Complexity complexity,
                long nonce,
                int txnCount) {
        assert null != prevBlock && null != merkleRoot && null != complexity;
        this.version = version;
        this.prevBlockHash = prevBlock;
        this.merkleRootHash = merkleRoot;
        this.timestamp = timestamp;
        this.complexity = complexity;
        this.nonce = nonce;
        this.txnCount = txnCount;
    }

    public void writeTo(OutputStream out, final AmatNamespace parentNmspc) {
        final AmatNamespace headerNmspc = BitcoinProtocolName.BLOCK_HEADER.appendTo(parentNmspc);
        writeHeaderVersion(out, headerNmspc);
        writePrevBlockHash(out, headerNmspc);
        writeMerkleRootHash(out, headerNmspc);
        writeTimestamp(out, headerNmspc);
        this.complexity.writeTo(out, headerNmspc);
        writeNonce(out, headerNmspc);
        writeTxnCount(out, headerNmspc);
    }

    private void writeHeaderVersion(OutputStream out, final AmatNamespace headerNmspc) {
        final AmatNamespace versionNmspc = BitcoinProtocolName.BLOCK_HEADER_VERSION.appendTo(headerNmspc);
        AmatProtocolUtil.writeIntAsLittleEndian(out, this.version, versionNmspc);
    }

    private void writePrevBlockHash(OutputStream out, final AmatNamespace headerNmspc) {
        final AmatNamespace prevHashNmspc = BitcoinProtocolName.BLOCK_HEADER_PREV_HASH.appendTo(headerNmspc);
        this.prevBlockHash.writeTo(out, prevHashNmspc);
    }

    private void writeMerkleRootHash(OutputStream out, final AmatNamespace headerNmspc) {
        final AmatNamespace merkleNmspc = BitcoinProtocolName.BLOCK_HEADER_MERKLE_HASH.appendTo(headerNmspc);
        this.merkleRootHash.writeTo(out, merkleNmspc);
    }

    private void writeTimestamp(OutputStream out, final AmatNamespace headerNmspc) {
        final AmatNamespace timestampNmspc = BitcoinProtocolName.BLOCK_HEADER_TIMESTAMP.appendTo(headerNmspc);
        AmatProtocolUtil.writeIntAsLittleEndian(out, (int) this.timestamp, timestampNmspc);
    }

    private void writeNonce(OutputStream out, final AmatNamespace headerNmspc) {
        final AmatNamespace nonceNmspc = BitcoinProtocolName.BLOCK_HEADER_NONCE.appendTo(headerNmspc);
        AmatProtocolUtil.writeIntAsLittleEndian(out, (int) this.nonce, nonceNmspc);
    }

    private void writeTxnCount(OutputStream out, final AmatNamespace headerNmspc) {
        final AmatNamespace txnCountNmspc = BitcoinProtocolName.BLOCK_HEADER_TXN_COUNT.appendTo(headerNmspc);
        BitcoinProtocolIOVarInt.from(txnCount).writeTo(out, txnCountNmspc);
    }

    public static BlockHeader readFrom(InputStream in, final AmatNamespace parentNmspc) {
        final AmatNamespace headerNmspc = BitcoinProtocolName.BLOCK_HEADER.appendTo(parentNmspc);
        int version = readHeaderVersion(in, headerNmspc);
        BitcoinHash prevBlockHash = readPrevBlockHash(in, headerNmspc);
        BitcoinHash merkleRootHash = readMerkleRootHash(in, headerNmspc);
        long timestamp = readTimestamp(in, headerNmspc);
        Complexity complexity = Complexity.readFrom(in, headerNmspc);
        long nonce = readNonce(in, headerNmspc);
        int txnCount = readTxnCount(in, headerNmspc);
        return new BlockHeader(version, prevBlockHash, merkleRootHash, timestamp, complexity, nonce, txnCount);
    }

    private static int readHeaderVersion(InputStream in, final AmatNamespace headerNmspc) {
        final AmatNamespace versionNmspc = BitcoinProtocolName.BLOCK_HEADER_VERSION.appendTo(headerNmspc);
        int version = AmatProtocolUtil.readLittleEndianInt(in, versionNmspc);
        return version;
    }

    private static BitcoinHash readPrevBlockHash(InputStream in, final AmatNamespace headerNmspc) {
        final AmatNamespace prevHashNmspc = BitcoinProtocolName.BLOCK_HEADER_PREV_HASH.appendTo(headerNmspc);
        BitcoinHash prevBlockHash = BitcoinHash.readFrom(in, prevHashNmspc);
        return prevBlockHash;
    }

    private static BitcoinHash readMerkleRootHash(InputStream in, final AmatNamespace headerNmspc) {
        final AmatNamespace merkleNmspc = BitcoinProtocolName.BLOCK_HEADER_MERKLE_HASH.appendTo(headerNmspc);
        BitcoinHash merkleRootHash = BitcoinHash.readFrom(in, merkleNmspc);
        return merkleRootHash;
    }

    private static long readTimestamp(InputStream in, final AmatNamespace headerNmspc) {
        final AmatNamespace timestampNmspc = BitcoinProtocolName.BLOCK_HEADER_TIMESTAMP.appendTo(headerNmspc);
        long timestamp = Integer.toUnsignedLong(AmatProtocolUtil.readLittleEndianInt(in, timestampNmspc));
        return timestamp;
    }

    private static long readNonce(InputStream in, final AmatNamespace headerNmspc) {
        final AmatNamespace nonceNmspc = BitcoinProtocolName.BLOCK_HEADER_NONCE.appendTo(headerNmspc);
        long nonce = Integer.toUnsignedLong(AmatProtocolUtil.readLittleEndianInt(in, nonceNmspc));
        return nonce;
    }

    private static int readTxnCount(InputStream in, final AmatNamespace headerNmspc) {
        final AmatNamespace txnCountNmspc = BitcoinProtocolName.BLOCK_HEADER_TXN_COUNT.appendTo(headerNmspc);
        int txnCount = BitcoinProtocolIOVarInt.readFrom(in, txnCountNmspc).intValue();
        return txnCount;
    }

}
