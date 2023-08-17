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

import weliyek.amat.base.AmatNameComposite;
import weliyek.amat.base.protocol.base.namespace.AmatProtocolFieldAbstract;
import weliyek.amat.base.protocol.message.field.AmatProtocolFieldInt;
import weliyek.amat.base.protocol.message.field.AmatProtocolFieldPrimitiveAbstract.ENDIANNESS;
import weliyek.amat.base.protocol.message.field.AmatProtocolFieldUnsignedInt;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolField;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolFieldHash;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolFieldVarInt;

public class BitcoinProtocolFieldBlockHeader
        extends AmatProtocolFieldAbstract<BitcoinConfig>
        implements BitcoinProtocolField
{

    public final AmatProtocolFieldInt version;

    public final BitcoinProtocolFieldHash prevBlock;

    public final BitcoinProtocolFieldHash merkleRoot;

    public final AmatProtocolFieldUnsignedInt timestamp;

    public final WkBitcoinComplexity complexity;

    public final AmatProtocolFieldUnsignedInt nonce;

    public final BitcoinProtocolFieldVarInt txnCount;

    public BitcoinProtocolFieldBlockHeader(AmatNameComposite namespace) {
        super(namespace.newDescendant(BitcoinProtocolName.BLOCK_HEADER));
        version = new AmatProtocolFieldInt(namespace().newDescendant(BitcoinProtocolName.BLOCK_HEADER_VERSION), ENDIANNESS.LITTLE);
        prevBlock = new BitcoinProtocolFieldHash(namespace().newDescendant(BitcoinProtocolName.BLOCK_HEADER_PREV_HASH));
        merkleRoot = new BitcoinProtocolFieldHash(namespace().newDescendant(BitcoinProtocolName.BLOCK_HEADER_MERKLE_HASH));
        timestamp = new AmatProtocolFieldUnsignedInt(namespace().newDescendant(BitcoinProtocolName.BLOCK_HEADER_TIMESTAMP), ENDIANNESS.LITTLE);
        complexity = new WkBitcoinComplexity(namespace().newDescendant(BitcoinProtocolName.COMPLEXITY));
        nonce = new AmatProtocolFieldUnsignedInt(namespace().newDescendant(BitcoinProtocolName.BLOCK_HEADER_NONCE), ENDIANNESS.LITTLE);
        txnCount = new BitcoinProtocolFieldVarInt(namespace().newDescendant(BitcoinProtocolName.BLOCK_HEADER_TXN_COUNT));
        reset();
    }

    @Override
    public void readFrom(InputStream in, BitcoinConfig config) {
        version.readFrom(in, config);
        prevBlock.readFrom(in, config);
        merkleRoot.readFrom(in, config);
        timestamp.readFrom(in, config);
        complexity.readFrom(in, config);
        nonce.readFrom(in, config);
        txnCount.readFrom(in, config);
    }

    @Override
    public void writeTo(OutputStream out, BitcoinConfig config) {
        version.writeTo(out, config);
        prevBlock.writeTo(out, config);
        merkleRoot.writeTo(out, config);
        timestamp.writeTo(out, config);
        complexity.writeTo(out, config);
        nonce.writeTo(out, config);
        txnCount.writeTo(out, config);
    }

    @Override
    public void reset() {
        version.onReset();
        prevBlock.onReset();
        merkleRoot.onReset();
        timestamp.onReset();
        complexity.onReset();
        nonce.onReset();
        txnCount.onReset();
    }

}
