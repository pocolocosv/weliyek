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
import weliyek.amat2.protocol.field.aggregator.SingleSubfieldAggregator;
import weliyek.amat2.protocol.field.basic.LittleEndianLongField;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;

public class BtcProtoFieldService<C extends MessageContext>
        extends SingleSubfieldAggregator
                        <C,
                         Long,
                         Number,
                         LittleEndianLongField<C>,
                         BitcoinNodeServices,
                         BitcoinNodeServices,
                         DeserializedBuilder
                             <BitcoinNodeServices,
                              ReadData<C, BitcoinNodeServices>,
                              BtcProtoFieldService<C>>,
                         WriteData<C, BitcoinNodeServices>,
                         ReadData<C, BitcoinNodeServices>,
                         BtcProtoFieldService<C>>
{

    public BtcProtoFieldService(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "SERVICE",
                BitcoinNodeServices.class,
                BitcoinNodeServices.class,
                () -> (f,r) -> f.newServices(r),
                SimpleSubfield.withStandardFieldData(
                        new LittleEndianLongField<>(Collections.emptyList()),
                        (ns,c) -> Long.valueOf(ns.value)),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
    }

    public LittleEndianLongField<C> longField() {
        return subfield();
    }

    private BitcoinNodeServices newServices(ReadData<C, BitcoinNodeServices> readData) {
        Number val = readData.getLatestDeserializedOrThrow(this.subfield());
        return BitcoinNodeServices.fromLong(val.longValue());
    }

    @Override
    protected BtcProtoFieldService<C> getThis() {
        return this;
    }

}
