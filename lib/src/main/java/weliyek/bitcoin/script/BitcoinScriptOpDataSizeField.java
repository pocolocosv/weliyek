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
import weliyek.amat2.protocol.field.basic.UnsignedLittleEndianIntegerField;
import weliyek.amat2.protocol.field.basic.UnsignedLittleEndianShortField;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;

public class BitcoinScriptOpDataSizeField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         Long,
                         Number,
                         DeserializedBuilder
                             <Number,
                              ReadData<C, Number>,
                              BitcoinScriptOpDataSizeField<C>>,
                         WriteData<C, Long>,
                         ReadData<C, Number>,
                         BitcoinScriptOpDataSizeField<C>>
        /*
                        <C,
                         ByteSequence,
                         Number,
                         Number,
                         BitcoinScriptOpDataSizeField<C>>
                         */
{

    private final UnsignedByteField<C> opcode;

    private final UnsignedByteField<C> byteField;
    private final UnsignedLittleEndianShortField<C> shortField;
    private final UnsignedLittleEndianIntegerField<C> intField;

    public BitcoinScriptOpDataSizeField(
            UnsignedByteField<C> opcode,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "SIZE",
                Long.class,
                Number.class,
                () -> new DeserializedSizeBuilder<>(),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.opcode = opcode; // Used for proper selection of subfields.
        this.byteField = buildByteField();
        this.shortField = buildShortField();
        this.intField = buildIntField();
    }

    public UnsignedByteField<C> byteField() {
        return byteField;
    }

    public UnsignedLittleEndianShortField<C> shortField() {
        return shortField;
    }

    public UnsignedLittleEndianIntegerField<C> intField() {
        return intField;
    }

    private UnsignedByteField<C> buildByteField() {
        UnsignedByteField<C> field = new UnsignedByteField<>("BYTE", Arrays.asList(opcode));
        Dissaggregator<Long, C, Byte> disaggregator = (l,c) -> Byte.valueOf(l.byteValue());
        WriteDataBuildManager<C, WriteData<C, Long>, Byte, WriteDataBuilder<C, Byte, WriteData<C,Byte>, UnsignedByteField<C>>, BitcoinScriptOpDataSizeField<C>>
            onWriteDataBuild = (su,w,swb,f) -> swb.setEnabledFlag(isWriteOpcodePUSHDATA1(w, f));
        ReadDataBuildManager<C, ReadData<C, Number>, Number, ReadDataBuilder<C, Number, ReadData<C,Number>, UnsignedByteField<C>>, BitcoinScriptOpDataSizeField<C>>
            onReadDataBuild = (r,srb,f) -> srb.setEnabledFlag(isReadOpcodePUSHDATA1(r, f));
        return addNonStandardSubfieldInfo(new SimpleSubfield<>(
                                                field,
                                                disaggregator,
                                                onWriteDataBuild,
                                                onReadDataBuild,
                                                new BasicDataBuildersSupplier<>())).subfield();
    }

    private static <C extends MessageContext> boolean isWriteOpcodePUSHDATA1(
            WriteData<C, Long> writeData,
            BitcoinScriptOpDataSizeField<C> sizeField) {
        Byte opcodeVal = writeData.getLatestSerializedOrThrow(sizeField.opcode);
        return BitcoinScriptOpName.OP_PUSHDATA1.code() == opcodeVal.intValue();
    }

    private static <C extends MessageContext> boolean isReadOpcodePUSHDATA1(
            ReadData<C, Number> readData,
            BitcoinScriptOpDataSizeField<C> sizeField) {
        Number opcodeVal = readData.getLatestDeserializedOrThrow(sizeField.opcode);
        return BitcoinScriptOpName.OP_PUSHDATA1.code() == opcodeVal.intValue();
    }

    private UnsignedLittleEndianShortField<C> buildShortField() {
        UnsignedLittleEndianShortField<C> field = new UnsignedLittleEndianShortField<>("SHORT", Arrays.asList(opcode));
        Dissaggregator<Long, C, Short> disaggregator = (l,c) -> Short.valueOf(l.shortValue());
        WriteDataBuildManager<C, WriteData<C, Long>, Short, WriteDataBuilder<C, Short, WriteData<C, Short>, UnsignedLittleEndianShortField<C>>, BitcoinScriptOpDataSizeField<C>>
            onWriteDataBuild = (su,w,swb,f) -> swb.setEnabledFlag(isWriteOpcodePUSHDATA2(w, f));
        ReadDataBuildManager<C, ReadData<C, Number>, Number, ReadDataBuilder<C, Number, ReadData<C,Number>, UnsignedLittleEndianShortField<C>>, BitcoinScriptOpDataSizeField<C>>
            onReadDataBuild = (r,srb,f) -> srb.setEnabledFlag(isReadOpcodePUSHDATA2(r, f));
        return addNonStandardSubfieldInfo(new SimpleSubfield<>(
                                                field,
                                                disaggregator,
                                                onWriteDataBuild,
                                                onReadDataBuild,
                                                new BasicDataBuildersSupplier<>())).subfield();
    }

    private static <C extends MessageContext> boolean isWriteOpcodePUSHDATA2(
            WriteData<C, Long> writeData,
            BitcoinScriptOpDataSizeField<C> sizeField) {
        Byte opcodeVal = writeData.getLatestSerializedOrThrow(sizeField.opcode);
        return BitcoinScriptOpName.OP_PUSHDATA2.code() == opcodeVal.intValue();
    }

    private static <C extends MessageContext> boolean isReadOpcodePUSHDATA2(
            ReadData<C, Number> readData,
            BitcoinScriptOpDataSizeField<C> sizeField) {
        Number opcodeVal = readData.getLatestDeserializedOrThrow(sizeField.opcode);
        return BitcoinScriptOpName.OP_PUSHDATA2.code() == opcodeVal.intValue();
    }

    private UnsignedLittleEndianIntegerField<C> buildIntField() {
        UnsignedLittleEndianIntegerField<C> field = new UnsignedLittleEndianIntegerField<>("INT", Arrays.asList(opcode));
        Dissaggregator<Long, C, Integer> disaggregator = (l,c) -> Integer.valueOf(l.intValue());
        WriteDataBuildManager<C, WriteData<C, Long>, Integer, WriteDataBuilder<C, Integer, WriteData<C, Integer>, UnsignedLittleEndianIntegerField<C>>, BitcoinScriptOpDataSizeField<C>>
            onWriteDataBuild = (su,w,swb,f) -> swb.setEnabledFlag(isWriteOpcodePUSHDATA4(w, f));
        ReadDataBuildManager<C, ReadData<C, Number>, Number, ReadDataBuilder<C, Number, ReadData<C,Number>, UnsignedLittleEndianIntegerField<C>>, BitcoinScriptOpDataSizeField<C>>
            onReadDataBuild = (r,srb,f) -> srb.setEnabledFlag(isReadOpcodePUSHDATA4(r, f));
    return addNonStandardSubfieldInfo(new SimpleSubfield<>(
                                            field,
                                            disaggregator,
                                            onWriteDataBuild,
                                            onReadDataBuild,
                                            new BasicDataBuildersSupplier<>())).subfield();
    }

    private static <C extends MessageContext> boolean isWriteOpcodePUSHDATA4(
            WriteData<C, Long> writeData,
            BitcoinScriptOpDataSizeField<C> sizeField) {
        Byte opcodeVal = writeData.getLatestSerializedOrThrow(sizeField.opcode);
        return BitcoinScriptOpName.OP_PUSHDATA4.code() == opcodeVal.intValue();
    }

    private static <C extends MessageContext> boolean isReadOpcodePUSHDATA4(
            ReadData<C, Number> readData,
            BitcoinScriptOpDataSizeField<C> sizeField) {
        Number opcodeVal = readData.getLatestDeserializedOrThrow(sizeField.opcode);
        return BitcoinScriptOpName.OP_PUSHDATA4.code() == opcodeVal.intValue();
    }

    @Override
    protected BitcoinScriptOpDataSizeField<C> getThis() {
        return this;
    }

    private static class DeserializedSizeBuilder<C extends MessageContext>
            implements DeserializedBuilder<Number, ReadData<C, Number>, BitcoinScriptOpDataSizeField<C>>
    {

        @Override
        public Number buildDeserialized(
                BitcoinScriptOpDataSizeField<C> field,
                ReadData<C, Number> readData) {
            if (isReadOpcodePUSHDATA1(readData, field))
                return readData.getLatestDeserializedOrThrow(field.byteField);
            else if (isReadOpcodePUSHDATA2(readData, field))
                return readData.getLatestDeserializedOrThrow(field.shortField);
            else if (isReadOpcodePUSHDATA4(readData, field))
                return readData.getLatestDeserializedOrThrow(field.intField);
            throw new IllegalStateException();  // Should never happen but...
        }
        /*
        @Override
        public Number buildDeserialized(
                BitcoinScriptOpDataSizeField<C> field,
                ReadProtocolData<C> records) {
            int code = records.operations().getLatestDeserializedOrThrow(field.opcode).intValue();
            if (code == BitcoinScriptOpName.OP_PUSHDATA1.code())
                return records.operations().getLatestDeserializedOrThrow(field.byteField);
            else if (code == BitcoinScriptOpName.OP_PUSHDATA2.code())
                return records.operations().getLatestDeserializedOrThrow(field.shortField);
            else if (code == BitcoinScriptOpName.OP_PUSHDATA4.code())
                return records.operations().getLatestDeserializedOrThrow(field.intField);
            else
                throw new IllegalStateException();  // Should never happen but...
        }
        */

    }

    /*
    private final SignedByteField<C, ByteSequence> byteField;
    private final SignedLittleEndianShortField<C, ByteSequence> shortField;
    private final SignedLittleEndianIntegerField<C, ByteSequence> intField;
    private final SignedByteField<C, BitcoinScriptOp> opcode;

    public BitcoinScriptOpDataSizeField(SignedByteField<C, BitcoinScriptOp> opcode) {
        super(
                "SIZE",
                Number.class,
                Number.class,
                (r) -> true,
                (w) -> true,
                (ba, c) -> Long.valueOf(ba.size()),
                new DeserializedSizeBuilder<>(),
                RecyclingBytesreamBuilder.INSTANCE,
                Arrays.asList(opcode));
        this.opcode = opcode;
        this.byteField = new SignedByteField<C, ByteSequence>(
                                    "BYTE",
                                    (r) ->    BitcoinScriptOpName.OP_PUSHDATA1.code()
                                           == r.operations()
                                               .getLatestDeserializedOrThrow(opcode)
                                               .intValue(),
                                    (w) ->    BitcoinScriptOpName.OP_PUSHDATA1.code()
                                           == w.operations()
                                               .getLatestSerializedOrThrow(opcode)
                                               .intValue(),
                                    (byteArray, c) -> Byte.valueOf((byte)byteArray.size()),
                                    RecyclingBytesreamBuilder.INSTANCE,
                                    Collections.emptyList());
        this.shortField = new SignedLittleEndianShortField<C, ByteSequence>(
                                    "SHORT",
                                    (r) ->    BitcoinScriptOpName.OP_PUSHDATA2.code()
                                           == r.operations()
                                               .getLatestDeserializedOrThrow(opcode)
                                               .intValue(),
                                    (w) ->    BitcoinScriptOpName.OP_PUSHDATA2.code()
                                           == w.operations()
                                               .getLatestSerializedOrThrow(opcode)
                                               .intValue(),
                                    (byteArray, c) -> Short.valueOf((short)byteArray.size()),
                                    RecyclingBytesreamBuilder.INSTANCE,
                                    Collections.emptyList());
        this.intField = new SignedLittleEndianIntegerField<>(
                                    "LONG",
                                    (r) ->    BitcoinScriptOpName.OP_PUSHDATA2.code()
                                           == r.operations()
                                               .getLatestDeserializedOrThrow(opcode)
                                               .intValue(),
                                    (w) ->    BitcoinScriptOpName.OP_PUSHDATA2.code()
                                           == w.operations()
                                               .getLatestSerializedOrThrow(opcode)
                                               .intValue(),
                                    (byteArray, c) -> Integer.valueOf((int)byteArray.size()),
                                    RecyclingBytesreamBuilder.INSTANCE,
                                    Collections.emptyList());
    }

    @Override
    protected BitcoinScriptOpDataSizeField<C> getThis() {
        return this;
    }

    public SignedByteField<C, ByteSequence> byteField() {
        return byteField;
    }

    public SignedLittleEndianShortField<C, ByteSequence> shortField() {
        return shortField;
    }

    public SignedLittleEndianIntegerField<C, ByteSequence> intField() {
        return intField;
    }
    */

}
