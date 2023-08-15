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
import java.util.Optional;

import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.RecyclingBytesreamBuilder;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.collection.AbstractSequenceField;
import weliyek.amat2.protocol.field.collection.BasicCollectionField;
import weliyek.amat2.protocol.field.collection.SequenceElementSubfieldHandler;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.SizingReadData;
import weliyek.amat2.protocol.field.data.SizingWriteData;
import weliyek.amat2.protocol.field.data.VariableSizingDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.VariableSizingReadDataBuilder;
import weliyek.amat2.protocol.field.data.VariableSizingWriteDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;

public class BitcoinScriptField<C extends MessageContext>
        extends AbstractSequenceField
                        <C,
                         Long,
                         Number,
                         VarIntField<C>,
                         BitcoinScriptOp,
                         BitcoinScriptOp,
                         BitcoinScriptOpField<C>,
                         BitcoinScript,
                         BitcoinScript,
                         SizingWriteData<C, BitcoinScript>,
                         SizingReadData<C, BitcoinScript>,
                         VariableSizingWriteDataBuilder
                             <C,
                              BitcoinScript,
                              SizingWriteData<C, BitcoinScript>,
                              BasicCollectionField
                                  <C,
                                   BitcoinScriptOp,
                                   BitcoinScriptOp,
                                   BitcoinScriptOpField<C>,
                                   BitcoinScript,
                                   BitcoinScript>>,
                         VariableSizingReadDataBuilder
                             <C,
                              BitcoinScript,
                              SizingReadData<C, BitcoinScript>,
                              BasicCollectionField
                                  <C,
                                   BitcoinScriptOp,
                                   BitcoinScriptOp,
                                   BitcoinScriptOpField<C>,
                                   BitcoinScript,
                                   BitcoinScript>>,
                        BasicCollectionField
                             <C,
                              BitcoinScriptOp,
                              BitcoinScriptOp,
                              BitcoinScriptOpField<C>,
                              BitcoinScript,
                              BitcoinScript>,
                         WriteData<C, BitcoinScript>,
                         ReadData<C, BitcoinScript>,
                         BitcoinScriptField<C>>
{

    public BitcoinScriptField() {
        this(Collections.emptyList());
    }

    public BitcoinScriptField(Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "SCRIPT",
                BitcoinScript.class,
                BitcoinScript.class,
                SimpleSubfield.withStandardFieldData(
                        new VarIntField<C>("SIZE", Collections.emptyList()),
                        (scrpt,c) -> Long.valueOf(scrpt.size())),
                new BasicCollectionField<>(
                        "OP_LIST",
                        BitcoinScript.class,
                        BitcoinScript.class,
                        (l) -> new BitcoinScript(l),
                        SequenceElementSubfieldHandler.asStandardCollectionElementSubfield(
                                new BitcoinScriptOpField<>(Collections.emptyList()),
                                new BasicDataBuildersSupplier<>(),
                                Optional.empty()),
                        requiredFields),
                new VariableSizingDataBuildersSupplier<>(),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
    }

    public VarIntField<C> size() {
        return sizeField();
    }

    public BasicCollectionField<C,
                                BitcoinScriptOp,
                                BitcoinScriptOp,
                                BitcoinScriptOpField<C>,
                                BitcoinScript,
                                BitcoinScript>
    opList() {
        return dependentField();
    }

    @Override
    protected BitcoinScriptField<C> getThis() {
        return this;
    }

    /*
    protected BitcoinScriptField(
            OnReadPredicate<C> onReadTest,
            OnWritePredicate<C> onWriteTest,
            Dissaggregator<PU, C, BitcoinScript> disaggregator,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "SCRIPT",
                BitcoinScript.class,
                BitcoinScript.class,
                onReadTest,
                onWriteTest,
                disaggregator,
                (f,r) -> new BitcoinScript( r.operations().getLatestDeserializedOrThrow(f.list())),
                new VarIntField<>(
                        (r) -> true,
                        (w) -> true,
                        (script,c) -> Long.valueOf(script.sizeInBytes()),
                        Collections.emptyList()),
                new CollectionFieldBuilder<>(),
                RecyclingBytesreamBuilder.INSTANCE,
                requiredFields);
    }

    @Override
    public VarIntField<C, BitcoinScript> size() {
        return core().independentField();
    }

    public BasicCollectionField<C, BitcoinScriptOp, BitcoinScriptOp, BitcoinScriptOpField<C, BitcoinScriptOp>, List<BitcoinScriptOp>, List<BitcoinScriptOp>, BitcoinScript>
    list() {
        return core().dependentField();
    }

    @Override
    protected RequiredAndDependentAggregatorFieldCore<Long, Long, VarIntField<C, BitcoinScript>, List<BitcoinScriptOp>, List<BitcoinScriptOp>, BasicCollectionField<C, BitcoinScriptOp, BitcoinScriptOp, BitcoinScriptOpField<C, BitcoinScriptOp>, List<BitcoinScriptOp>, List<BitcoinScriptOp>, BitcoinScript>, C, BitcoinScript, BitcoinScript, BitcoinScriptField<C, PU>> core() {
        return super.core();
    }

    @Override
    protected BitcoinScriptField<C, PU> getThis() {
        return this;
    }

    private static class CollectionFieldBuilder< C extends MessageContext, PU>
            extends SizeAndDynamicObjectField.DynamicObjectFieldBuilder
                                <C,
                                 PU,
                                 Long,
                                 Long,
                                 VarIntField<C, BitcoinScript>,
                                 List<BitcoinScriptOp>,
                                 List<BitcoinScriptOp>,
                                 BasicCollectionField<
                                     C,
                                     BitcoinScriptOp,
                                     BitcoinScriptOp,
                                     BitcoinScriptOpField<C, BitcoinScriptOp>,
                                     List<BitcoinScriptOp>,
                                     List<BitcoinScriptOp>,
                                     BitcoinScript>,
                                 BitcoinScript,
                                 BitcoinScript,
                                 BitcoinScriptField<C,PU>,
                                 CollectionFieldBuilder<C,PU>>
    {

        public CollectionFieldBuilder() {
            super("COLLECTION");
        }

        @Override
        @SuppressWarnings("unchecked")
        public BasicCollectionField<C, BitcoinScriptOp, BitcoinScriptOp, BitcoinScriptOpField<C, BitcoinScriptOp>, List<BitcoinScriptOp>, List<BitcoinScriptOp>, BitcoinScript>
        buildField() {
            return new BasicCollectionField<>(
                                getLabel(),
                                (Class<List<BitcoinScriptOp>>)(Class<?>)List.class,
                                (Class<List<BitcoinScriptOp>>)(Class<?>)List.class,
                                (r) -> true,
                                (w) -> true,
                                (script,c) -> script,
                                (scriptopList) -> scriptopList,
                                getSizeOnRead(),
                                new BitcoinScriptOpField<>(
                                        (r) -> true,
                                        (w) -> true,
                                        (scriptop,c) -> scriptop,
                                        Collections.emptyList()),
                                RecyclingBytesreamBuilder.INSTANCE,
                                allRequiredFields());
        }

        @Override
        protected CollectionFieldBuilder<C, PU> getThis() {
            return this;
        }

    }
    */

}
