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
package weliyek.bitcoin.protocol;

import java.util.Collection;
import java.util.Collections;

import weliyek.amat2.protocol.field.DeserializedBuilder;
import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.RecyclingBytesreamBuilder;
import weliyek.amat2.protocol.field.aggregator.SingleSubfieldAggregator;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.basic.UnsignedLittleEndianIntegerField;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.bitcoin.BitcoinComplexity;

public class BtcProtoFieldComplexity<C extends MessageContext>
        extends SingleSubfieldAggregator
                        <C,
                         Integer,
                         Number,
                         UnsignedLittleEndianIntegerField<C>,
                         BitcoinComplexity,
                         BitcoinComplexity,
                         DeserializedBuilder
                             <BitcoinComplexity,
                              ReadData<C, BitcoinComplexity>,
                              BtcProtoFieldComplexity<C>>,
                         WriteData<C, BitcoinComplexity>,
                         ReadData<C, BitcoinComplexity>,
                         BtcProtoFieldComplexity<C>>
{

    public BtcProtoFieldComplexity(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "COMPLEXITY",
                BitcoinComplexity.class,
                BitcoinComplexity.class,
                () -> (f,r) -> BitcoinComplexity.ofCompact(r.getLatestDeserializedOrThrow(f.subfield())
                                                            .intValue()),
                SimpleSubfield.withStandardFieldData(
                        new UnsignedLittleEndianIntegerField<>(Collections.emptyList()),
                        (bc,c) -> Integer.valueOf(bc.toCompact())),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
    }

    public UnsignedLittleEndianIntegerField<C> uint() {
        return subfield();
    }

    @Override
    protected BtcProtoFieldComplexity<C> getThis() {
        return this;
    }

    /*
    public BtcProtoFieldComplexity(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "COMPLEXITY",
                BitcoinComplexity.class,
                BitcoinComplexity.class,
                () -> (f,r) -> BitcoinComplexity.ofCompact(r.protocolData()
                                                            .operations()
                                                            .getLatestDeserializedOrThrow(f.subfield())
                                                            .intValue()),
                new UnsignedLittleEndianIntegerField<>("INT", Collections.emptyList()),
                (bc,c) -> Integer.valueOf(bc.toCompact()),
                (a,b,c,d) -> {},
                (a,b,c) -> {},
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields,
                () -> new WriteDataBuilder<>(() -> new WriteData<>()),
                () -> new ReadDataBuilder<>(() -> new ReadData<>()));
    }

    public UnsignedLittleEndianIntegerField<C> integer() {
        return core().subfield();
    }

    @Override
    protected BtcProtoFieldComplexity<C> getThis() {
        return this;
    }
    */

}
