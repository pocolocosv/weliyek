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
import java.util.List;
import java.util.Optional;

import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.collection.CollectionField;
import weliyek.amat2.protocol.field.collection.SequenceElementSubfieldHandler;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;

public class BitcoinTxOutListField<C extends MessageContext>
        extends CollectionField
                        <C,
                         Long,
                         Number,
                         VarIntField<C>,
                         BitcoinTxOut,
                         BitcoinTxOut,
                         BitcoinTxOutField<C>,
                         List<BitcoinTxOut>,
                         List<BitcoinTxOut>>
{

    public BitcoinTxOutListField() {
        this(Collections.emptyList());
    }

    @SuppressWarnings("unchecked")
    public BitcoinTxOutListField(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "TXOUTLIST",
                "LIST",
                (Class<List<BitcoinTxOut>>)(Class<?>)List.class,
                (Class<List<BitcoinTxOut>>)(Class<?>)List.class,
                SimpleSubfield.withStandardFieldData(
                        new VarIntField<>("SIZE"),
                        (l,c) -> Long.valueOf(l.size())),
                (l) -> l,
                SequenceElementSubfieldHandler.asStandardCollectionElementSubfield(
                        new BitcoinTxOutField<>(),
                        new BasicDataBuildersSupplier<>(),
                        Optional.empty()),
                requiredFields);
        // TODO Auto-generated constructor stub
    }

}
