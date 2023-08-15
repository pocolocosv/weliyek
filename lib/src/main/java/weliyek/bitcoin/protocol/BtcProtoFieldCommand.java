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
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.bitcoin.BitcoinCommand;
import weliyek.ketza.util.ByteSequence;
import weliyek.ketza.util.ByteSequenceField;

public class BtcProtoFieldCommand<C extends MessageContext>
        extends SingleSubfieldAggregator
                        <C,
                         ByteSequence,
                         ByteSequence,
                         ByteSequenceField<C>,
                         BitcoinCommand,
                         BitcoinCommand,
                         DeserializedBuilder
                                 <BitcoinCommand,
                                  ReadData<C, BitcoinCommand>,
                                  BtcProtoFieldCommand<C>>,
                         WriteData<C, BitcoinCommand>,
                         ReadData<C, BitcoinCommand>,
                         BtcProtoFieldCommand<C>>
{

    public BtcProtoFieldCommand(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "COMMAND",
                BitcoinCommand.class,
                BitcoinCommand.class,
                () -> (f,r) -> BitcoinCommand.newCommand(r.getLatestDeserializedOrThrow(f.subfield())),
                SimpleSubfield.withFixedSizingFieldData(
                        new ByteSequenceField<>(Collections.emptyList()),
                        (bc,c) -> bc.bytes, // Dissaggregator<BitcoinCommand, C, ByteSequence>
                        BitcoinCommand.CANONICAL_LENGTH,
                        BitcoinCommand.CANONICAL_LENGTH),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
    }

    public ByteSequenceField<C> bytes() {
        return subfield();
    }

    @Override
    protected BtcProtoFieldCommand<C> getThis() {
        return this;
    }

}
