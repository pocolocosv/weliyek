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
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;

public class BtcProtoFieldInvectoryVector<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                        BitcoinInventoryVector,
                        BitcoinInventoryVector,
                         DeserializedBuilder
                             <BitcoinInventoryVector,
                              ReadData<C, BitcoinInventoryVector>,
                              BtcProtoFieldInvectoryVector<C>>,
                         WriteData<C, BitcoinInventoryVector>,
                         ReadData<C, BitcoinInventoryVector>,
                         BtcProtoFieldInvectoryVector<C>>
        /*
                        <C,
                         PU,
                         BitcoinInventoryVector,
                         BitcoinInventoryVector,
                         BtcProtoFieldInvectoryVector<C,PU>>
                         */
{

    private final BtcProtoFieldInventoryVectorType<C> type;
    private final BtcProtoFieldHash<C> hash;

    public BtcProtoFieldInvectoryVector(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "INVENTORY_VECTOR",
                BitcoinInventoryVector.class,
                BitcoinInventoryVector.class,
                () -> (f,r) -> new BitcoinInventoryVector(
                                        r.getLatestDeserializedOrThrow(f.type),
                                        r.getLatestDeserializedOrThrow(f.hash)),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.type = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new BtcProtoFieldInventoryVectorType<>(Collections.emptyList()),
                                                    (inv,c) -> inv.getType())).subfield();
        this.hash = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                                                    new BtcProtoFieldHash<>(Collections.emptyList()),
                                                    (inv,c) -> inv.getHash())).subfield();
    }

    public BtcProtoFieldInventoryVectorType<C> type() {
        return type;
    }

    public BtcProtoFieldHash<C> hash() {
        return hash;
    }

    @Override
    protected BtcProtoFieldInvectoryVector<C> getThis() {
        return this;
    }

    /*
    private final BtcProtoFieldInventoryVectorType<C, BitcoinInventoryVector> type;
    private final BtcProtoFieldHash<C, BitcoinInventoryVector> hash;

    public BtcProtoFieldInvectoryVector(
            Dissaggregator<PU, C, BitcoinInventoryVector> disaggregator,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "INVENTORY_VECTOR",
                BitcoinInventoryVector.class,
                BitcoinInventoryVector.class,
                (r) -> true,
                (w) -> true,
                disaggregator,
                (f,r) -> new BitcoinInventoryVector(
                                r.operations().getLatestDeserializedOrThrow(f.getType()),
                                r.operations().getLatestDeserializedOrThrow(f.getHash())),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
        this.type = addSubfield(new BtcProtoFieldInventoryVectorType<>((iv,c) -> iv.getType(), Collections.emptyList()));
        this.hash = addSubfield(new BtcProtoFieldHash<>("HASH", (iv,c) -> iv.getHash(), Collections.emptyList()));
    }

    public BtcProtoFieldInventoryVectorType<C, BitcoinInventoryVector> getType() {
        return type;
    }

    public BtcProtoFieldHash<C, BitcoinInventoryVector> getHash() {
        return hash;
    }

    @Override
    protected BtcProtoFieldInvectoryVector<C, PU> getThis() {
        return this;
    }
    */

}
