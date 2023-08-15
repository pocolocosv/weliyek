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
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;
import weliyek.bitcoin.protocol.varint.VarIntField.VarIntDeserializedBuilder;

public class VarIntField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         Long,
                         Number,
                         DeserializedBuilder
                             <Number,
                              ReadData<C, Number>,
                              VarIntField<C>>,
                         WriteData<C, Long>,
                         ReadData<C, Number>,
                         VarIntField<C>>
{

    private final UnsignedByteField<C> header;
    private final VarIntBodyField<C> body;

    public VarIntField(String label) {
        this(label, Collections.emptyList());
    }

    public VarIntField(String label, Collection<Field<C, ?, ?>> requiredFields) {
        super(
                label,
                Long.class,
                Number.class,
                () -> new VarIntDeserializedBuilder<>(),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.header = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new UnsignedByteField<>("VARINT_HEADER", Collections.emptyList()),
                                                    (l,c) -> getHeaderValue(l))).subfield();
        VarIntBodyField<C> b = buildVarIntBody();
        this.body = b;
    }

    private VarIntBodyField<C> buildVarIntBody() {
        VarIntBodyField<C> field = new VarIntBodyField<>(this.header, Arrays.asList(this.header));
        Dissaggregator<Long, C, Long> disaggregator = (l,c) -> l;
        WriteDataBuildManager<C, WriteData<C, Long>, Long,
                                  WriteDataBuilder<C, Long, WriteData<C, Long>, VarIntBodyField<C>>,
                                  VarIntField<C>>
            onBodyWriteDataBuild = (su,w,swb,f) -> swb.setEnabledFlag(hasBody(w,f));
        ReadDataBuildManager<C, ReadData<C, Number>, Number,
                                 ReadDataBuilder<C, Number,ReadData<C,Number>,VarIntBodyField<C>>,
                                 VarIntField<C>>
            onBodyReadDataBuild = (r,srb,f) -> srb.setEnabledFlag(hasBody(r, f));
        return addNonStandardSubfieldInfo(new SimpleSubfield<>(
                                    field,
                                    disaggregator,
                                    onBodyWriteDataBuild,
                                    onBodyReadDataBuild,
                                    new BasicDataBuildersSupplier<>()))
                    .subfield();
    }

    public UnsignedByteField<C> header() {
        return this.header;
    }

    public VarIntBodyField<C> body() {
        return this.body;
    }

    private boolean hasBody(WriteData<C, Long> w, VarIntField<C> f) {
        Number hdrVal = w.getLatestSerializedOrThrow(f.header);
        return isValNotEqualToNoBodyHeader(hdrVal);
    }

    private boolean hasBody(ReadData<C, Number> w, VarIntField<C> f) {
        Number hdrVal = w.getLatestDeserializedOrThrow(f.header);
        return isValNotEqualToNoBodyHeader(hdrVal);
    }

    private boolean isValNotEqualToNoBodyHeader(Number hdrVal) {
        BodyType hdr = BodyType.fromHeaderValue(hdrVal);
        return ! BodyType.NO_BODY.equals(hdr);
    }

    static class VarIntDeserializedBuilder<C extends MessageContext>
            implements DeserializedBuilder<Number, ReadData<C, Number>, VarIntField<C>>
    {

        @Override
        public Number buildDeserialized(VarIntField<C> varintField, ReadData<C, Number> readData) {
            Number hdrVal = readData.getLatestDeserializedOrThrow(varintField.header);
            BodyType hdr = BodyType.fromHeaderValue(hdrVal);
            Number deserializedVal = null;
            switch(hdr) {
            case NO_BODY:
                deserializedVal = hdrVal;
                break;
            case SHORT:
            case INT:
            case LONG:
                deserializedVal = readData.getLatestDeserializedOrThrow(varintField.body);
                break;
            default:
                throw new IllegalArgumentException();
            }
            return deserializedVal;
        }

    }

    static final long VARINT_SHORT_LIMIT  = 0xFFFF;
    static final long VARINT_INT_LIMIT    = 0xFFFF_FFFFL;
    static final  int VARINT_HEADER_SHORT = 0xFD;
    static final  int VARINT_HEADER_INT   = 0xFE;
    static final  int VARINT_HEADER_LONG  = 0xFF;

    static Byte getHeaderValue(long val) {
        if ((val >> 8 == 0L) && ((0xFF & val) < VARINT_HEADER_SHORT))
            return Byte.valueOf((byte) val);
        else if ((val >> 16 == 0L) && ((VARINT_SHORT_LIMIT & val) != 0))
            return Byte.valueOf((byte) VARINT_HEADER_SHORT);
        else if ((val >> 32 == 0) && ((VARINT_INT_LIMIT & val) != 0))
            return Byte.valueOf((byte) VARINT_HEADER_INT);
        else
            return Byte.valueOf((byte) VARINT_HEADER_LONG);
    }

    @Override
    protected VarIntField<C> getThis() {
        return this;
    }

}
