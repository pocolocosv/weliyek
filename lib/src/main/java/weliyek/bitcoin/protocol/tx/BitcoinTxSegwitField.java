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
package weliyek.bitcoin.protocol.tx;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.collection.BasicCollectionField;
import weliyek.amat2.protocol.field.collection.SequenceElementSubfieldHandler;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.bitcoin.script.BitcoinScript;

public class BitcoinTxSegwitField<C extends MessageContext>
        extends BasicCollectionField
                        <C,
                         List<BitcoinScript>,
                         List<BitcoinScript>,
                         BitcoinTxInWitnessesListField<C>,
                         List<List<BitcoinScript>>,
                         List<List<BitcoinScript>>>
{

    @SuppressWarnings("unchecked")
    public BitcoinTxSegwitField() {
        super(
                "WITNESSES",
                (Class<List<List<BitcoinScript>>>)(Class<?>)List.class,
                (Class<List<List<BitcoinScript>>>)(Class<?>)List.class,
                (ll) -> ll,
                SequenceElementSubfieldHandler.asStandardCollectionElementSubfield(
                        new BitcoinTxInWitnessesListField<>(),
                        new BasicDataBuildersSupplier<>(),
                        Optional.empty()),
                Collections.emptyList());
    }


    /*
                SequenceElementSubfieldHandler.asStandardCollectionElementSubfield(
                        new BitcoinSegwitTxInField<>(),
                        new VariableSizingDataBuildersSupplier<>(),
                        Optional.empty()),

    @SuppressWarnings("unchecked")
    public BitcoinSegwitField(
            Function<List<List<BitcoinScript>>, List<List<BitcoinScript>>> readElementListToDeserialized,
            SequenceElementSubfieldHandler<C, List<BitcoinScript>, List<BitcoinScript>, ?, ?, ?, ?, CollectionField<C, Long, Number, VarIntField<C>, BitcoinScript, BitcoinScript, BitcoinScriptField<C>, List<BitcoinScript>, List<BitcoinScript>>, List<List<BitcoinScript>>, List<List<BitcoinScript>>, SizingWriteData<C, List<List<BitcoinScript>>>, SizingReadData<C, List<List<BitcoinScript>>>, BasicCollectionField<C, List<BitcoinScript>, List<BitcoinScript>, CollectionField<C, Long, Number, VarIntField<C>, BitcoinScript, BitcoinScript, BitcoinScriptField<C>, List<BitcoinScript>, List<BitcoinScript>>, List<List<BitcoinScript>>, List<List<BitcoinScript>>>> elementSubfield) {
        super(
                "WITNESSES",
                "LISTS",
                (Class<List<List<BitcoinScript>>>)(Class<?>)List.class,
                (Class<List<List<BitcoinScript>>>)(Class<?>)List.class,
                SimpleSubfield.withStandardFieldData(
                        new VarIntField<>("SIZE"),
                        (ll,c) -> Long.valueOf(ll.size())),
                (ll) -> ll,
                elementSubfield,
                Collections.emptyList());
    }
    */

    /*
    public BitcoinTxInWitnessesField() {
        this(Collections.emptyList());
    }

    @SuppressWarnings("unchecked")
    public BitcoinTxInWitnessesField(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "WITNESSES",
                "LIST",
                (Class<List<BitcoinScript>>)(Class<?>)List.class,
                (Class<List<BitcoinScript>>)(Class<?>)List.class,
                SimpleSubfield.withStandardFieldData(
                        new VarIntField<>("SIZE", Collections.emptyList()),
                        (l,c) -> Long.valueOf(l.size())),
                (l) -> l,
                SequenceElementSubfieldHandler.asStandardCollectionElementSubfield(
                        new BitcoinScriptField<C>(Collections.emptyList()),
                        new BasicDataBuildersSupplier<>(),
                        Optional.empty()),
                requiredFields);
    }
    */

}
