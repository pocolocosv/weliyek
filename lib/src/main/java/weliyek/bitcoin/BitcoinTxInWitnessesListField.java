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

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.collection.CollectionField;
import weliyek.amat2.protocol.field.collection.SequenceElementSubfieldHandler;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;

public class BitcoinTxInWitnessesListField<C extends MessageContext>
        extends CollectionField
                        <C,
                         Long,
                         Number,
                         VarIntField<C>,
                         BitcoinScript,
                         BitcoinScript,
                         BitcoinScriptField<C>,
                         List<BitcoinScript>,
                         List<BitcoinScript>>
{

    @SuppressWarnings("unchecked")
    public BitcoinTxInWitnessesListField() {
        super(
                "TXINSEGWIT",
                "LIST",
                (Class<List<BitcoinScript>>)(Class<?>)List.class,
                (Class<List<BitcoinScript>>)(Class<?>)List.class,
                SimpleSubfield.withStandardFieldData(
                        new VarIntField<>("SIZE"),
                        (l,c) -> Long.valueOf(l.size())),
                (l) -> l,
                SequenceElementSubfieldHandler.asStandardCollectionElementSubfield(
                        new BitcoinScriptField<>(),
                        new BasicDataBuildersSupplier<>(),
                        Optional.empty()),
                Collections.emptyList());
    }

}
