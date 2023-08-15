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
import weliyek.amat2.protocol.field.basic.UnsignedByteField;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;

public class BitcoinScriptOpField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         BitcoinScriptOp,
                         BitcoinScriptOp,
                         DeserializedBuilder
                             <BitcoinScriptOp,
                              ReadData<C, BitcoinScriptOp>,
                              BitcoinScriptOpField<C>>,
                         WriteData<C, BitcoinScriptOp>,
                         ReadData<C, BitcoinScriptOp>,
                         BitcoinScriptOpField<C>>
{

    private final UnsignedByteField<C> opcode;
    private final BitcoinScriptOpDataField<C> data;

    BitcoinScriptOpField(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "SCRIPT_OP",
                BitcoinScriptOp.class,
                BitcoinScriptOp.class,
                () -> (f,r) -> BitcoinScriptOp.fromCodeAndData(
                                                  r.getLatestDeserializedOrThrow(f.opcode).intValue(),
                                                  r.getLatestDeserializedOrThrow(f.data)),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.opcode = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new UnsignedByteField<>("OPCODE", Collections.emptyList()),
                                                    (op, c) -> Byte.valueOf((byte)op.opcode()))).subfield();
        this.data = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new BitcoinScriptOpDataField<>(opcode, requiredFields),
                                                    (op,c) -> op.data())).subfield();
    }

    public UnsignedByteField<C> opcode() {
        return opcode;
    }

    public BitcoinScriptOpDataField<C> data() {
        return data;
    }

    @Override
    protected BitcoinScriptOpField<C> getThis() {
        return this;
    }

    /*
    private final SignedByteField<C, BitcoinScriptOp> opcode;
    private final BitcoinScriptOpDataField<C> data;

    public BitcoinScriptOpField(
            OnReadPredicate<C> onReadTest,
            OnWritePredicate<C> onWriteTest,
            Dissaggregator<PU, C, BitcoinScriptOp> disaggregator,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "SCRIPT_OP",
                BitcoinScriptOp.class,
                BitcoinScriptOp.class,
                onReadTest,
                onWriteTest,
                disaggregator,
                (f,r) -> BitcoinScriptOp.fromCodeAndData(
                                            r.operations().getLatestDeserializedOrThrow(f.opcode).intValue(),
                                            r.operations().getLatestDeserializedOrElse(f.data, null)),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.opcode = addSubfield(new SignedByteField<>(
                                            "OPCODE",
                                            (r) -> true,
                                            (w) -> true,
                                            (scriptop, c) -> Byte.valueOf((byte)scriptop.opcode()),
                                            RecyclingBytesreamBuilder.INSTANCE,
                                            Collections.emptyList()));
        this.data = new BitcoinScriptOpDataField<>(this.opcode);
    }

    @Override
    protected BitcoinScriptOpField<C, PU> getThis() {
        return this;
    }
    */

}
