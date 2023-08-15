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

import java.net.Inet6Address;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Collections;

import weliyek.amat2.protocol.ProtocolFieldException;
import weliyek.amat2.protocol.field.DeserializedBuilder;
import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.RecyclingBytesreamBuilder;
import weliyek.amat2.protocol.field.aggregator.SingleSubfieldAggregator;
import weliyek.amat2.protocol.field.basic.BasicByteArrayField;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;

public class BtcProtoFieldInet6Address<C extends MessageContext>
        extends SingleSubfieldAggregator
                        <C,
                         byte[],
                         byte[],
                         BasicByteArrayField
                             <C,
                              WriteData<C,byte[]>,
                              ReadData<C,byte[]>>,
                         Inet6Address,
                         Inet6Address,
                         DeserializedBuilder
                             <Inet6Address,
                              ReadData<C, Inet6Address>,
                              BtcProtoFieldInet6Address<C>>,
                         WriteData<C, Inet6Address>,
                         ReadData<C, Inet6Address>,
                         BtcProtoFieldInet6Address<C>>
{

    private final static int CANONICAL_BYTE_ARRAY_LENGTH = 16;

    public BtcProtoFieldInet6Address(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "IPV6",
                Inet6Address.class,
                Inet6Address.class,
                () -> (f,r) -> f.newAddress(r),
                SimpleSubfield.withStandardFieldData(
                        new BasicByteArrayField<>(
                                (w) -> CANONICAL_BYTE_ARRAY_LENGTH,
                                (r) -> CANONICAL_BYTE_ARRAY_LENGTH,
                                Collections.emptyList()),
                        (iaddr,c) -> iaddr.getAddress()),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
    }

    public BasicByteArrayField<C, WriteData<C, byte[]>, ReadData<C, byte[]>> bytes() {
        return subfield();
    }

    private Inet6Address newAddress(ReadData<C, Inet6Address> readData) {
        byte[] bytes = readData.getLatestDeserializedOrThrow(this.subfield());
        Inet6Address addr;
        try {
            addr = Inet6Address.getByAddress("", bytes, 0);
        } catch (UnknownHostException e) {
            throw new ProtocolFieldException(getThis(), e);
        }
        return addr;
    }

    @Override
    protected BtcProtoFieldInet6Address<C> getThis() {
        return this;
    }

    /*
    public BtcProtoFieldInet6Address(
            String label,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "ADDR",
                Inet6Address.class,
                Inet6Address.class,
                () -> (f,r) -> f.newAddress(r),
                new BasicByteArrayField<>(
                        "BYTES",
                        RecyclingBytesreamBuilder.INSTANCE,
                        Collections.emptyList(),
                        () -> new FixedSizingWriteDataBuilder<>(
                                () -> new SizingWriteData<C,byte[]>(),
                                CANONICAL_BYTE_ARRAY_LENGTH),
                        () -> new FixedSizingReadDataBuilder<>(
                                () -> new SizingReadData<C,byte[]>(),
                                CANONICAL_BYTE_ARRAY_LENGTH)),
                (iaddr,c) -> iaddr.getAddress(),
                (a,b,c,d) -> {},
                (a,b,c) -> {},
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields,
                () -> new WriteDataBuilder<>(() -> new WriteData<>()),
                () -> new ReadDataBuilder<>(() -> new ReadData<>()));
    }

    public BasicByteArrayField<C> bytes() {
        return core().subfield();
    }

    @Override
    protected BtcProtoFieldInet6Address<C> getThis() {
        return this;
    }
    */

}
