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
package weliyek.bitcoin.script;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import weliyek.amat2.protocol.field.DeserializedBuilder;
import weliyek.amat2.protocol.field.Dissaggregator;
import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.ReadDataBuildManager;
import weliyek.amat2.protocol.field.RecyclingBytesreamBuilder;
import weliyek.amat2.protocol.field.WriteDataBuildManager;
import weliyek.amat2.protocol.field.aggregator.GroupAggregratorField;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.basic.UnsignedByteField;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.SizingReadData;
import weliyek.amat2.protocol.field.data.SizingWriteData;
import weliyek.amat2.protocol.field.data.VariableSizingDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.VariableSizingReadDataBuilder;
import weliyek.amat2.protocol.field.data.VariableSizingWriteDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.ketza.util.ByteSequence;
import weliyek.ketza.util.ByteSequenceField;

public class BitcoinScriptOpDataField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         ByteSequence,
                         ByteSequence,
                         DeserializedBuilder
                             <ByteSequence,
                              ReadData<C, ByteSequence>,
                              BitcoinScriptOpDataField<C>>,
                         WriteData<C, ByteSequence>,
                         ReadData<C, ByteSequence>,
                         BitcoinScriptOpDataField<C>>
{

    private final UnsignedByteField<C> opcode;
    private final BitcoinScriptOpDataSizeField<C> size;
    private final ByteSequenceField<C> data;

    BitcoinScriptOpDataField(
            UnsignedByteField<C> opcode,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "DATA",
                ByteSequence.class,
                ByteSequence.class,
                () -> (f,r) -> r.getLatestDeserializedOrThrow(f.data),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.opcode = addRequired(opcode); // Used for proper selection of subfields.
        this.size = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new BitcoinScriptOpDataSizeField<>(
                                                            opcode,
                                                            Collections.emptyList()),
                                                    (bs,c) -> Long.valueOf(bs.size()))).subfield();
        this.data = addDataSubfield();
    }

    private ByteSequenceField<C> addDataSubfield() {
        ByteSequenceField<C> seqField = new ByteSequenceField<>(Arrays.asList(opcode));
        Dissaggregator<ByteSequence, C, ByteSequence> disaggregator = (bs,c) -> bs;
        WriteDataBuildManager<C, WriteData<C, ByteSequence>, ByteSequence, VariableSizingWriteDataBuilder<C, ByteSequence, SizingWriteData<C, ByteSequence>, ByteSequenceField<C>>, BitcoinScriptOpDataField<C>>
            onWriteDataBuild = (su,w,swb,f) -> swb.setSize(w.getLatestSerializedOrThrow(f.size)
                                                            .longValue());
        ReadDataBuildManager<C, ReadData<C, ByteSequence>, ByteSequence, VariableSizingReadDataBuilder<C, ByteSequence, SizingReadData<C, ByteSequence>, ByteSequenceField<C>>, BitcoinScriptOpDataField<C>>
            onReadDataBuild = (r,srb,f) -> srb.setSize(r.getLatestDeserializedOrThrow(f.size)
                                                        .longValue());
        return addNonStandardSubfieldInfo(new SimpleSubfield<>(
                                    seqField,
                                    disaggregator,
                                    onWriteDataBuild,
                                    onReadDataBuild,
                                    new VariableSizingDataBuildersSupplier<>())).subfield();
    }

    public BitcoinScriptOpDataSizeField<C> size() {
        return this.size;
    }

    public ByteSequenceField<C> data() {
        return this.data;
    }

    @Override
    protected BitcoinScriptOpDataField<C> getThis() {
        return this;
    }

    /*

    public BitcoinScriptOpDataField(SignedByteField<C, BitcoinScriptOp> opcode) {
        super(
                "DATA",
                ByteSequence.class,
                ByteSequence.class,
                (r) -> BitcoinScriptOp.isDataOpCode(r.operations()
                                                     .getLatestDeserializedOrThrow(opcode)
                                                     .intValue()),
                (w) -> BitcoinScriptOp.isDataOpCode(w.operations()
                                                     .getLatestSerializedOrThrow(opcode)
                                                     .intValue()),
                (scriptop, c) -> scriptop.data().get(),
                (f,r) -> r.operations().getLatestDeserializedOrThrow(f.data),
                RecyclingBytesreamBuilder.INSTANCE,
                Arrays.asList(opcode));
        this.size = addSubfield(new BitcoinScriptOpDataSizeField<>(opcode));
        this.data = addSubfield(new ByteSequenceField<>(
                                    "DATA",
                                    (r) -> true,
                                    (w) -> true,
                                    (r) -> r.operations().getLatestDeserializedOrThrow(this.size),
                                    (w) -> w.operations().getLatestSerializedOrThrow(this.size),
                                    (bseq, c) -> bseq,
                                    RecyclingBytesreamBuilder.INSTANCE,
                                    Collections.emptyList()));
    }
    */

}
