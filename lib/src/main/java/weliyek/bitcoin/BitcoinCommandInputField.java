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

import java.io.InputStream;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.InputFieldReading;
import weliyek.amat.base.input.InputSubcomponentHandler;
import weliyek.amat.base.input.ProtocolReading;
import weliyek.amat.base.input.ReadingSettings;
import weliyek.amat.base.input.SequenceInputSubfield;
import weliyek.amat.base.input.SoleValueInputSubfield;
import weliyek.amat.basic.aggregator.AggregatorInputFieldDefinition;
import weliyek.amat.basic.aggregator.BasicSoleValueAggregatorInputFieldCore;
import weliyek.amat.newserialization.SoleValueAggregatorInputField;
import weliyek.amat.newserialization.operation.BasicSequenceReadingConfig;
import weliyek.amat.newserialization.operation.InputConfig;
import weliyek.amat.newserialization.operation.SizeProviderReadingConfig;
import weliyek.amat2.protocol.filter.Filter;
import weliyek.amat2.protocol.filter.FilterMatcher;
import weliyek.ketza.util.array.BasicByteArrayWrapperInputField;
import weliyek.ketza.util.array.BasicByteArrayWrapperReading;
import weliyek.ketza.util.array.ByteArrayWrapper;

public class BitcoinCommandInputField
        implements BitcoinInputField<
                        BitcoinCommand,
                        Object,
                        InputConfig,
                        BitcoinCommandReading,
                        BitcoinCommandInputField>,
                   SoleValueAggregatorInputField<
                        BitcoinCommand,
                        Object,
                        InputConfig,
                        BitcoinCommandReading,
                        BitcoinCommandInputField>
{

    private final BasicSoleValueAggregatorInputFieldCore<
                        ByteArrayWrapper,
                        SizeProviderReadingConfig,
                        BasicByteArrayWrapperReading,
                        BasicByteArrayWrapperInputField,
                        BitcoinCommand,
                        Object,
                        InputConfig,
                        ReadingSettings<Object, InputConfig>,
                        BasicReadingResult<BitcoinCommand>,
                        BitcoinCommandReading,
                        BitcoinCommandInputField> fieldCore;

    public BitcoinCommandInputField(String label, AggregatorInputFieldDefinition<?, ?, ?, ?, ?> parentField) {
        fieldCore = new BasicSoleValueAggregatorInputFieldCore<>(
                            label,
                            this,
                            Optional.ofNullable(parentField),
                            BitcoinCommand.class,
                            (fCore) -> new BitcoinCommandReading(fCore).getCore(),
                            (bytewrapper) -> BitcoinCommand.newCommand(bytewrapper),
                            (f) -> new BasicByteArrayWrapperInputField(
                                        "BYTES",
                                        BitcoinCommand.CANONICAL_LENGTH,
                                        BitcoinCommand.CANONICAL_LENGTH,
                                        f),
                            (agReading) -> new BasicSequenceReadingConfig(BitcoinCommand.CANONICAL_LENGTH));
    }

    @Override
    public Class<BitcoinCommand> serializedClass() {
        return fieldCore.serializedClass();
    }

    @Override
    public FilterMatcher<BitcoinCommand> testIf(Predicate<BitcoinCommand> test, String desc) {
        return fieldCore.testIf(test, desc);
    }

    @Override
    public <R_ extends InputFieldReading<Object, ?, ?, ?, ?, ?>>
            SoleValueInputSubfield<Object, R_, BitcoinCommand, InputConfig, BitcoinCommandReading, ?, BitcoinCommandInputField, ?>
            asSoleValueSubfield(
                Predicate<R_> activationTest,
                boolean forceFullRead,
                Function<R_, InputConfig> configBuilder,
                Collection<InputSubcomponentHandler<Object, ?, ?, ?, ?, ?, ?, ?, ?, ?>> required) {
        return fieldCore.asSoleValueSubfield(activationTest, forceFullRead, configBuilder, required);
    }

    @Override
    public <R_ extends InputFieldReading<Object, ?, ?, ?, ?, ?>>
            SequenceInputSubfield<Object, R_, BitcoinCommand, InputConfig, BitcoinCommandReading, ?, BitcoinCommandInputField, ?>
            asSequenceSubfield(
                ToIntFunction<R_> expectedSequenceSize,
                Predicate<R_> activationTest,
                boolean forceFullRead,
                Function<R_, InputConfig> configBuilder,
                Collection<InputSubcomponentHandler<Object, ?, ?, ?, ?, ?, ?, ?, ?, ?>> required) {
        return fieldCore.asSequenceSubfield(expectedSequenceSize, activationTest, forceFullRead, configBuilder, required);
    }

    @Override
    public ProtocolReading<BitcoinCommand, Object, InputConfig, BitcoinCommandReading, BitcoinCommandInputField>
            newFullReadOperation(Object context, InputConfig config, InputStream input) {
        return fieldCore.newFullReadOperation(context, config, input);
    }

    @Override
    public ProtocolReading<BitcoinCommand, Object, InputConfig, BitcoinCommandReading, BitcoinCommandInputField>
            newPartialReadOperation(
                Object context,
                InputConfig config,
                Filter filter,
                InputStream input) {
        return fieldCore.newPartialReadOperation(context, config, filter, input);
    }

}
