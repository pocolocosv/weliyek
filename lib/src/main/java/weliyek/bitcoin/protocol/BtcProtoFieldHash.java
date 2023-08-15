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

import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.bitcoin.BitcoinHash;
import weliyek.ketza.util.AbstractByteSequenceField;
import weliyek.ketza.util.ByteSequence;

public class BtcProtoFieldHash<C extends MessageContext>
        extends AbstractByteSequenceField
                        <C,
                         BitcoinHash,
                         WriteData<C, ByteSequence>,
                         ReadData<C, BitcoinHash>>
{

    public BtcProtoFieldHash(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "HASH",
                BitcoinHash.class,
                (w) -> BitcoinHash.CANONICAL_BYTE_LENGTH,
                (r) -> BitcoinHash.CANONICAL_BYTE_LENGTH,
                (di,l) -> BitcoinHash.build(di),
                requiredFields);
    }

}
