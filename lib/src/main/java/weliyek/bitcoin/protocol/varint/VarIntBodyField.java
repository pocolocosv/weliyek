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
package weliyek.bitcoin.protocol.varint;

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
import weliyek.amat2.protocol.field.basic.LittleEndianLongField;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.basic.UnsignedByteField;
import weliyek.amat2.protocol.field.basic.UnsignedLittleEndianIntegerField;
import weliyek.amat2.protocol.field.basic.UnsignedLittleEndianShortField;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;
import weliyek.bitcoin.protocol.varint.VarIntBodyField.DeserializableBodyBuilder;

public class VarIntBodyField<C extends MessageContext>
        extends GroupAggregratorField
                    <C,
                     Long,
                     Number,
                     DeserializedBuilder
                         <Number,
                          ReadData<C, Number>,
                          VarIntBodyField<C>>,
                     WriteData<C, Long>,
                     ReadData<C, Number>,
                     VarIntBodyField<C>>
{

    private final UnsignedByteField<C> header;
    private final UnsignedLittleEndianShortField<C> shortBody;
    private final UnsignedLittleEndianIntegerField<C> intBody;
    private final LittleEndianLongField<C> longBody;

    VarIntBodyField(
            UnsignedByteField<C> header,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "VARINT_BODY",
                Long.class,
                Number.class,
                () -> new DeserializableBodyBuilder<>(),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.header = header;
        this.shortBody = buildAndAddShortBodyField();
        this.intBody = buildAndAddIntegerBodyField();
        this.longBody = buildAndAddLongBodyField();
    }

    public UnsignedLittleEndianShortField<C> shortField() {
        return shortBody;
    }

    public UnsignedLittleEndianIntegerField<C> integerField() {
        return intBody;
    }

    public LittleEndianLongField<C> longField() {
        return longBody;
    }

    private UnsignedLittleEndianShortField<C> buildAndAddShortBodyField() {

        UnsignedLittleEndianShortField<C> shortField = new UnsignedLittleEndianShortField<>(
                                                               "SHORT",
                                                               Collections.emptyList());

        Dissaggregator<Long, C, Short> disaggregator = (l,c) -> Short.valueOf(l.shortValue());

        WriteDataBuildManager<C, WriteData<C, Long>, Short, WriteDataBuilder<C, Short, WriteData<C, Short>, UnsignedLittleEndianShortField<C>>, VarIntBodyField<C>> onWriteDataBuild =
                (u,w,swb,f) -> swb.setEnabledFlag(isShortBody(w,f));

        ReadDataBuildManager<C, ReadData<C, Number>, Number, ReadDataBuilder<C, Number, ReadData<C, Number>, UnsignedLittleEndianShortField<C>>, VarIntBodyField<C>> onReadDataBuild =
                (r,srb,f) -> srb.setEnabledFlag(isShortBody(r, f));

        UnsignedLittleEndianShortField<C> sb = addNonStandardSubfieldInfo(
                new SimpleSubfield<>(
                        shortField,
                        disaggregator,
                        onWriteDataBuild,
                        onReadDataBuild,
                new BasicDataBuildersSupplier<>())).subfield();

        return sb;
    }

    private UnsignedLittleEndianIntegerField<C> buildAndAddIntegerBodyField() {

        UnsignedLittleEndianIntegerField<C> intField = new UnsignedLittleEndianIntegerField<>(
                                                               Collections.emptyList());

        Dissaggregator<Long, C, Integer> disaggregator = (l,c) -> Integer.valueOf(l.intValue());

        WriteDataBuildManager<C, WriteData<C, Long>, Integer, WriteDataBuilder<C, Integer, WriteData<C, Integer>, UnsignedLittleEndianIntegerField<C>>, VarIntBodyField<C>> onWriteDataBuild =
                (u,w,swb,f) -> swb.setEnabledFlag(isIntBody(w,f));

        ReadDataBuildManager<C, ReadData<C, Number>, Number, ReadDataBuilder<C, Number, ReadData<C, Number>, UnsignedLittleEndianIntegerField<C>>, VarIntBodyField<C>> onReadDataBuild =
                (r,srb,f) -> srb.setEnabledFlag(isIntBody(r, f));

        addNonStandardSubfieldInfo(
                new SimpleSubfield<>(
                        intField,
                        disaggregator,
                        onWriteDataBuild,
                        onReadDataBuild,
                new BasicDataBuildersSupplier<>()));

        return intField;
    }

    private LittleEndianLongField<C> buildAndAddLongBodyField() {

        LittleEndianLongField<C> longField = new LittleEndianLongField<>(
                                                               "LONG",
                                                               Collections.emptyList());

        Dissaggregator<Long, C, Long> disaggregator = (l,c) -> l;

        WriteDataBuildManager<C, WriteData<C, Long>, Long, WriteDataBuilder<C, Long, WriteData<C, Long>, LittleEndianLongField<C>>, VarIntBodyField<C>> onWriteDataBuild =
                (u,w,swb,f) -> swb.setEnabledFlag(isLongBody(w,f));

        ReadDataBuildManager<C, ReadData<C, Number>, Number, ReadDataBuilder<C, Number, ReadData<C, Number>, LittleEndianLongField<C>>, VarIntBodyField<C>> onReadDataBuild =
                (r,srb,f) -> srb.setEnabledFlag(isLongBody(r, f));

        addNonStandardSubfieldInfo(
                new SimpleSubfield<>(
                        longField,
                        disaggregator,
                        onWriteDataBuild,
                        onReadDataBuild,
                new BasicDataBuildersSupplier<>()));

        return longField;
    }

    private boolean isShortBody(WriteData<C, Long> w, VarIntBodyField<C> f) {
        Number hdrVal = w.getLatestSerializedOrThrow(f.header);
        BodyType hdr = BodyType.fromHeaderValue(hdrVal);
        return BodyType.SHORT.equals(hdr);
    }

    private boolean isIntBody(WriteData<C, Long> w, VarIntBodyField<C> f) {
        Number hdrVal = w.getLatestSerializedOrThrow(f.header);
        BodyType hdr = BodyType.fromHeaderValue(hdrVal);
        return BodyType.INT.equals(hdr);
    }

    private boolean isLongBody(WriteData<C, Long> w, VarIntBodyField<C> f) {
        Number hdrVal = w.getLatestSerializedOrThrow(f.header);
        BodyType hdr = BodyType.fromHeaderValue(hdrVal);
        return BodyType.LONG.equals(hdr);
    }

    private boolean isShortBody(ReadData<C, Number> w, VarIntBodyField<C> f) {
        Number hdrVal = w.getLatestDeserializedOrThrow(f.header);
        BodyType hdr = BodyType.fromHeaderValue(hdrVal);
        return BodyType.SHORT.equals(hdr);
    }

    private boolean isIntBody(ReadData<C, Number> w, VarIntBodyField<C> f) {
        Number hdrVal = w.getLatestDeserializedOrThrow(f.header);
        BodyType hdr = BodyType.fromHeaderValue(hdrVal);
        return BodyType.INT.equals(hdr);
    }

    private boolean isLongBody(ReadData<C, Number> w, VarIntBodyField<C> f) {
        Number hdrVal = w.getLatestDeserializedOrThrow(f.header);
        BodyType hdr = BodyType.fromHeaderValue(hdrVal);
        return BodyType.LONG.equals(hdr);
    }

    static class DeserializableBodyBuilder<C extends MessageContext>
            implements DeserializedBuilder<Number, ReadData<C, Number>, VarIntBodyField<C>>
    {

        @Override
        public Number buildDeserialized(VarIntBodyField<C> field, ReadData<C, Number> readData) {
            Number hdrVal = readData.getLatestDeserializedOrThrow(field.header);
            BodyType bodyType = BodyType.fromHeaderValue(hdrVal);
            Number deserialized = null;
            switch (bodyType) {
            case SHORT:
                deserialized = readData.getLatestDeserializedOrThrow(field.shortBody);
                break;
            case INT:
                deserialized = readData.getLatestDeserializedOrThrow(field.intBody);
                break;
            case LONG:
                deserialized = readData.getLatestDeserializedOrThrow(field.longBody);
                break;
            case NO_BODY:
            default:
                throw new IllegalStateException();
            }
            return deserialized;
        }

    }

    @Override
    protected VarIntBodyField<C> getThis() {
        return this;
    }

}