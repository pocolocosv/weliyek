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

import java.io.OutputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import weliyek.amat.base.output.ArrayOutputSubfield;
import weliyek.amat.base.output.CollectionOutputSubfield;
import weliyek.amat.base.output.ProtocolWriting;
import weliyek.amat.base.output.SoleValueOutputSubfield;
import weliyek.amat.basic.aggregator.AggregatorOutputFieldDefinition;
import weliyek.amat.basic.aggregator.AggregatorWriting;
import weliyek.amat.basic.aggregator.BasicSoleValueAggregatorOutputFieldCore;
import weliyek.amat.newserialization.SoleValueAggregatorOutputField;
import weliyek.amat.newserialization.operation.BasicSequenceWritingConfig;
import weliyek.amat.newserialization.operation.OutputConfig;
import weliyek.amat.newserialization.operation.SizeProviderAndPaddingElementWritingConfig;
import weliyek.bitcoin.BitcoinCommand;
import weliyek.bitcoin.BitcoinOutputField;
import weliyek.ketza.util.array.BasicByteArrayWrapperOutputField;
import weliyek.ketza.util.array.BasicByteArrayWrapperWriting;
import weliyek.ketza.util.array.ByteArrayWrapper;

public class BitcoinCommandOutputField
        implements BitcoinOutputField<
                        BitcoinCommand,
                        Object,
                        OutputConfig,
                        BitcoinCommandWriting,
                        BitcoinCommandOutputField>,
                   SoleValueAggregatorOutputField<
                        BitcoinCommand,
                        Object,
                        OutputConfig,
                        BitcoinCommandWriting,
                        BitcoinCommandOutputField>
{

    private final BasicSoleValueAggregatorOutputFieldCore<
                        ByteArrayWrapper,
                        SizeProviderAndPaddingElementWritingConfig<Byte>,
                        BasicByteArrayWrapperWriting<Object>,
                        BasicByteArrayWrapperOutputField<Object>,
                        BitcoinCommand,
                        Object,
                        OutputConfig,
                        BitcoinCommandWriting,
                        BitcoinCommandOutputField> fieldCore;

    public BitcoinCommandOutputField(
            String label,
            AggregatorOutputFieldDefinition<?, ?, ?, ?, ?> parentField) {
        fieldCore = new BasicSoleValueAggregatorOutputFieldCore<
                            ByteArrayWrapper,
                            SizeProviderAndPaddingElementWritingConfig<Byte>,
                            BasicByteArrayWrapperWriting<Object>,
                            BasicByteArrayWrapperOutputField<Object>,
                            BitcoinCommand,
                            Object,
                            OutputConfig,
                            BitcoinCommandWriting,
                            BitcoinCommandOutputField>(
                                    this,
                                    label,
                                    Optional.ofNullable(parentField),
                                    BitcoinCommand.class,
                                    (fCore) -> new BitcoinCommandWriting(fCore).core(),
                                    (f) -> new BasicByteArrayWrapperOutputField<>(
                                            "BYTES",
                                            Optional.empty(),
                                            BitcoinCommand.CANONICAL_LENGTH,
                                            BitcoinCommand.CANONICAL_LENGTH,
                                            f),
                                    (commandWriting) -> commandWriting.settings().get().getSerializable().bytes,
                                    (commandWriting) -> new BasicSequenceWritingConfig<>(BitcoinCommand.CANONICAL_LENGTH, Optional.empty()));
    }

    @Override
    public Class<BitcoinCommand> serializedClass() {
        return fieldCore.serializedClass();
    }

    @Override
    public <Y_, W_ extends AggregatorWriting<Object, Y_, ?, ?, ?, ?>>
            SoleValueOutputSubfield<Object, Y_, W_, BitcoinCommand, OutputConfig, BitcoinCommandWriting, ?, BitcoinCommandOutputField, ?>
            asSoleValueOutputSubfield(
                Predicate<W_> activationTest,
                Function<W_, BitcoinCommand> disaggregator,
                Function<W_, OutputConfig> configBuilder) {
        return fieldCore.asSoleValueOutputSubfield(activationTest, disaggregator, configBuilder);
    }

    @Override
    public <Y_ extends Collection<? extends BitcoinCommand>, W_ extends AggregatorWriting<Object, Y_, ?, ?, ?, ?>>
            CollectionOutputSubfield<Object, Y_, W_, BitcoinCommand, OutputConfig, BitcoinCommandWriting, ?, BitcoinCommandOutputField, ?>
            asCollectionElementSubfield(
                ToIntFunction<W_> sequenceSize,
                Predicate<W_> activationTest,
                Function<W_, OutputConfig> configBuilder) {
        return fieldCore.asCollectionElementSubfield(sequenceSize, activationTest, configBuilder);
    }

    @Override
    public <W_ extends AggregatorWriting<Object, BitcoinCommand[], ?, ?, ?, ?>>
            ArrayOutputSubfield<Object, W_, BitcoinCommand, OutputConfig, BitcoinCommandWriting, ?, BitcoinCommandOutputField, ?>
            asArrayOutputSubfield(
                ToIntFunction<W_> sequenceSize,
                Predicate<W_> activationTest,
                Function<W_, OutputConfig> configBuilder) {
        return fieldCore.asArrayOutputSubfield(sequenceSize, activationTest, configBuilder);
    }

    @Override
    public ProtocolWriting<BitcoinCommand, Object, OutputConfig, BitcoinCommandWriting, BitcoinCommandOutputField>
            startWriting(
                BitcoinCommand serializable,
                Object context,
                OutputConfig config,
                OutputStream output) {
        return fieldCore.startWriting(serializable, context, config, output);
    }

}
