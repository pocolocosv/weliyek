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

import java.util.Collection;
import java.util.Collections;

import weliyek.amat2.protocol.field.DeserializedBuilder;
import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.RecyclingBytesreamBuilder;
import weliyek.amat2.protocol.field.aggregator.GroupAggregratorField;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.basic.UnsignedLittleEndianIntegerField;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;

public class BitcoinTxInField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         BitcoinTxIn,
                         BitcoinTxIn,
                         DeserializedBuilder
                             <BitcoinTxIn,
                              ReadData<C, BitcoinTxIn>,
                              BitcoinTxInField<C>>,
                         WriteData<C, BitcoinTxIn>,
                         ReadData<C, BitcoinTxIn>,
                         BitcoinTxInField<C>>
{

    private final BitcoinOutpointProtoField<C> outpoint;
    private final BitcoinScriptField<C> script;
    //private final BitcoinTxInWitnessesField<C> witnesses;
    private final UnsignedLittleEndianIntegerField<C> sequence;

    protected BitcoinTxInField() {
        this(Collections.emptyList());
    }

    protected BitcoinTxInField(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "TXIN",
                BitcoinTxIn.class,
                BitcoinTxIn.class,
                () -> (f,r) -> BitcoinTxInRO.build(
                                        r.getLatestDeserializedOrThrow(f.outpoint),
                                        r.getLatestDeserializedOrThrow(f.script),
                                        r.getLatestDeserializedOrThrow(f.sequence).longValue()),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.outpoint = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                            new BitcoinOutpointProtoField<>(Collections.emptyList()),
                            (txin, c) -> txin.outpoint())).subfield();
        this.script = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                            new BitcoinScriptField<>(Collections.emptyList()),
                            (txin, c) -> txin.script())).subfield();
        /*
        this.witnesses = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                            new BitcoinTxInWitnessesField<>(),
                            (txin, c) -> txin.segregatedWitness())).subfield();
                            */
        this.sequence = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                            new UnsignedLittleEndianIntegerField<>("SEQUENCE"),
                            (txin, c) -> Integer.valueOf((int)txin.sequence()))).subfield();
    }

    public BitcoinOutpointProtoField<C> outpoint() {
        return this.outpoint;
    }

    public BitcoinScriptField<C> script() {
        return this.script;
    }

    public UnsignedLittleEndianIntegerField<C> sequence() {
        return this.sequence;
    }

    @Override
    protected BitcoinTxInField<C> getThis() {
        return this;
    }

    /*
    public final BitcoinProtocolFieldOutpoint outpoint;

    public final BitcoinProtocolFieldScript script;

    public final BtcProtoScriptList witness;

    public final AmatProtocolFieldUnsignedInt sequence;

    public BitcoinProtocolFieldTxIn(AmatNameComposite namespace) {
        super(namespace.newDescendant(BitcoinProtocolName.TXIN));
        outpoint = new BitcoinProtocolFieldOutpoint  (namespace());
        script   = new BitcoinProtocolFieldScript    (namespace());
        witness  = new BtcProtoScriptList(namespace());
        sequence = new AmatProtocolFieldUnsignedInt  (namespace().newDescendant(BitcoinProtocolName.TXIN_SEQUENCE),
                                                      ENDIANNESS.LITTLE);
    }

    @Override
    public void readFrom(InputStream in, BitcoinConfig config) {
        outpoint.readFrom(in, config);
        script.readFrom(in, config);
        sequence.readFrom(in, config);
    }

    @Override
    public void writeTo(OutputStream out, BitcoinConfig config) {
        outpoint.writeTo(out, config);
        script.writeTo(out, config);
        sequence.writeTo(out, config);
    }

    @Override
    public void reset() {
        outpoint.reset();
        script.reset();
        witness.reset();
    }
    */

}
