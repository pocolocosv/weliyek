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
import weliyek.bitcoin.BitcoinInventoryVectorType;

public class BtcProtoFieldInventoryVectorType<C extends MessageContext>
        extends SingleSubfieldAggregator
                        <C,
                         Integer,
                         Number,
                         UnsignedLittleEndianIntegerField<C>,
                         BitcoinInventoryVectorType,
                         BitcoinInventoryVectorType,
                         DeserializedBuilder
                             <BitcoinInventoryVectorType,
                              ReadData<C, BitcoinInventoryVectorType>,
                              BtcProtoFieldInventoryVectorType<C>>,
                         WriteData<C, BitcoinInventoryVectorType>,
                         ReadData<C, BitcoinInventoryVectorType>,
                         BtcProtoFieldInventoryVectorType<C>>
{

    public BtcProtoFieldInventoryVectorType(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "TYPE",
                BitcoinInventoryVectorType.class,
                BitcoinInventoryVectorType.class,
                () -> (f,r) -> BitcoinInventoryVectorType.newType(r.getLatestDeserializedOrThrow(f.subfield())
                                                         .intValue()),
                SimpleSubfield.withStandardFieldData(
                        new UnsignedLittleEndianIntegerField<>(Collections.emptyList()),
                        (bt,c) -> Integer.valueOf(bt.code())),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
    }

    public UnsignedLittleEndianIntegerField<C> integerField() {
        return subfield();
    }

    @Override
    protected BtcProtoFieldInventoryVectorType<C> getThis() {
        return this;
    }

}
