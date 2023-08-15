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
import java.util.ArrayList;
import java.util.List;

import weliyek.amat.base.AmatNameComposite;
import weliyek.amat.base.AmatNamespaceIndexable;
import weliyek.amat.base.protocol.message.field.AmatProtocolFieldCollectionAbstract;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolField;
import weliyek.amat.bitcoin.protocol.BitcoinProtocolFieldVarInt;

public class BitcoinProtocolFieldTxOutList
        extends AmatProtocolFieldCollectionAbstract<BitcoinTxOutField,
                                                    List<BitcoinTxOutField>,
                                                    BitcoinConfig>
        implements BitcoinProtocolField
{

    private final AmatNameComposite elementNameBase;

    private final BitcoinProtocolFieldVarInt size;

    private final List<BitcoinTxOutField> list = new ArrayList<>();

    public BitcoinProtocolFieldTxOutList(AmatNameComposite namespace) {
        super(namespace.newDescendant(BitcoinProtocolName.TXOUT_LIST));
        elementNameBase = namespace().newDescendant(BitcoinProtocolName.TXOUT_LIST_ELEMENT);
        size = new BitcoinProtocolFieldVarInt(namespace().newDescendant(BitcoinProtocolName.TXOUT_LIST_ELEMENT));
    }

    @Override
    public List<BitcoinTxOutField> container() {
        return list;
    }

    @Override
    protected long readNumberOfElements(InputStream in, BitcoinConfig config) {
        size.readFrom(in, config);
        final long s = size.value().get().longValue();
        size.onReset();
        return s;
    }

    @Override
    protected BitcoinTxOutField readNewElement(InputStream in, BitcoinConfig config, long elemIndex) {
        final BitcoinTxOutField txOut = new BitcoinTxOutField(new AmatNamespaceIndexable(elementNameBase, elemIndex));
        txOut.readFrom(in, config);
        return txOut;
    }

    @Override
    protected void writeNumberOfElements(OutputStream out, long num, BitcoinConfig config) {
        size.setValue(num);
        size.writeTo(out, config);
        size.onReset();
    }

    @Override
    protected void writeElement(OutputStream out, BitcoinTxOutField elem, BitcoinConfig config) {
        elem.writeTo(out, config);
    }

}
