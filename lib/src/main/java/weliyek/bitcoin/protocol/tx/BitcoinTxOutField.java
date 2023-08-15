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
package weliyek.bitcoin.protocol.tx;

import java.util.Collection;
import java.util.Collections;

import weliyek.amat2.protocol.field.DeserializedBuilder;
import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.RecyclingBytesreamBuilder;
import weliyek.amat2.protocol.field.aggregator.GroupAggregratorField;
import weliyek.amat2.protocol.field.basic.LittleEndianLongField;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.bitcoin.script.BitcoinScriptField;

public class BitcoinTxOutField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         BitcoinTxOut,
                         BitcoinTxOut,
                         DeserializedBuilder
                             <BitcoinTxOut,
                              ReadData<C, BitcoinTxOut>,
                              BitcoinTxOutField<C>>,
                         WriteData<C, BitcoinTxOut>,
                         ReadData<C, BitcoinTxOut>,
                         BitcoinTxOutField<C>>
{

    private final LittleEndianLongField<C> txValue;
    private final BitcoinScriptField<C> script;

    public BitcoinTxOutField() {
        this(Collections.emptyList());
    }

    public BitcoinTxOutField(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "TXOUT",
                BitcoinTxOut.class,
                BitcoinTxOut.class,
                () -> (f,r) -> BitcoinTxOutRO.build(
                                        r.getLatestDeserializedOrThrow(f.txValue).longValue(),
                                        r.getLatestDeserializedOrThrow(f.script)),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.txValue = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                new LittleEndianLongField<>("VALUE"),
                                (txout,c) -> Long.valueOf(txout.value()))).subfield();
        this.script = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                new BitcoinScriptField<>(requiredFields),
                                (txout,c) -> txout.script())).subfield();
    }

    public LittleEndianLongField<C> txValue() {
        return txValue;
    }

    public BitcoinScriptField<C> script() {
        return script;
    }

    @Override
    protected BitcoinTxOutField<C> getThis() {
        return this;
    }

    /*
    public final AmatProtocolFieldLong transactionValue;

    public final BitcoinProtocolFieldScript script;

    public BitcoinProtocolFieldTxOut(AmatNameComposite namespace) {
        super(namespace.newDescendant(BitcoinProtocolName.TXOUT));
        transactionValue = new AmatProtocolFieldLong     (namespace().newDescendant(BitcoinProtocolName.TXOUT_VALUE),
                                                          ENDIANNESS.LITTLE);
        script           = new BitcoinProtocolFieldScript(namespace());
    }

    @Override
    public void readFrom(InputStream in, BitcoinConfig config) {
        transactionValue.readFrom(in, config);
        script.readFrom(in, config);
    }

    @Override
    public void writeTo(OutputStream out, BitcoinConfig config) {
        transactionValue.writeTo(out, config);
        script.writeTo(out, config);
    }

    @Override
    public void reset() {
        transactionValue.reset();
        script.reset();
    }
    */

}
