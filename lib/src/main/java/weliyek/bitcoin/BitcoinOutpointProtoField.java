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

public class BitcoinOutpointProtoField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         BitcoinOutpoint,
                         BitcoinOutpoint,
                         DeserializedBuilder
                             <BitcoinOutpoint,
                              ReadData<C, BitcoinOutpoint>,
                              BitcoinOutpointProtoField<C>>,
                         WriteData<C, BitcoinOutpoint>,
                         ReadData<C, BitcoinOutpoint>,
                         BitcoinOutpointProtoField<C>>
{

    private final BtcProtoFieldHash<C> hash;
    private final UnsignedLittleEndianIntegerField<C> index;

    public BitcoinOutpointProtoField(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "OUTPOINT",
                BitcoinOutpoint.class,
                BitcoinOutpoint.class,
                () -> (f,r) -> new BitcoinOutpoint(r.getLatestDeserializedOrThrow(f.hash),
                                                   r.getLatestDeserializedOrThrow(f.index).longValue()),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.hash = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new BtcProtoFieldHash<>(Collections.emptyList()),
                                                    (outpnt,c) -> outpnt.hash())).subfield();
        this.index = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new UnsignedLittleEndianIntegerField<>(
                                                            "INDEX",
                                                            Collections.emptyList()),
                                                    (outpnt,c) -> Integer.valueOf((int)outpnt.index()))).subfield();
    }

    public BtcProtoFieldHash<C> hash() {
        return hash;
    }

    public UnsignedLittleEndianIntegerField<C> index() {
        return index;
    }

    @Override
    protected BitcoinOutpointProtoField<C> getThis() {
        return this;
    }

    /*
    private final BtcProtoFieldHash<C, BitcoinOutpoint> hash;
    private final SignedLittleEndianIntegerField<C, BitcoinOutpoint> index;

    public BitcoinOutpointField(
            String label,
            OnReadPredicate<C> onReadTest,
            OnWritePredicate<C> onWriteTest,
            Dissaggregator<PU, C, BitcoinOutpoint> disaggregator,
            DeserializedBuilder<C, BitcoinOutpoint, BitcoinOutpointField<C, PU>> deserializedBuilder,
            BytestreamBuilder bytestreamBuilder,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                label,
                BitcoinOutpoint.class,
                BitcoinOutpoint.class,
                onReadTest,
                onWriteTest,
                disaggregator,
                deserializedBuilder,
                bytestreamBuilder,
                requiredFields);
        this.hash = addSubfield(new BtcProtoFieldHash<>(
                                            "HASH",
                                            (outpnt, c) -> outpnt.hash,
                                            Collections.emptyList()));
        this.index = addSubfield(new SignedLittleEndianIntegerField<>(
                                            "INDEX",
                                            (r) -> true,
                                            (w) -> true,
                                            (outpnt,c) -> outpnt.index,
                                            RecyclingBytesreamBuilder.INSTANCE,
                                            Collections.emptyList()));
    }

    public BtcProtoFieldHash<C, BitcoinOutpoint> hash() {
        return hash;
    }

    public SignedLittleEndianIntegerField<C, BitcoinOutpoint> index() {
        return index;
    }

    @Override
    protected BitcoinOutpointField<C, PU> getThis() {
        return this;
    }
    */

}
