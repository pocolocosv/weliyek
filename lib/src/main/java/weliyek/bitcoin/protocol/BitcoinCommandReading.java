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

import java.util.Optional;

import weliyek.amat.base.Direction;
import weliyek.amat.base.input.BasicReadingResult;
import weliyek.amat.base.input.ReadingSettings;
import weliyek.amat.basic.aggregator.BasicSoleValueAggregatorInputFieldCore;
import weliyek.amat.basic.aggregator.BasicSoleValueAggregatorReadingCore;
import weliyek.amat.newserialization.SoleValueAggregatorReading;
import weliyek.amat.newserialization.operation.InputConfig;
import weliyek.amat.newserialization.operation.SizeProviderReadingConfig;
import weliyek.bitcoin.BitcoinCommand;
import weliyek.bitcoin.BitcoinReading;
import weliyek.ketza.util.array.BasicByteArrayWrapperInputField;
import weliyek.ketza.util.array.BasicByteArrayWrapperReading;
import weliyek.ketza.util.array.ByteArrayWrapper;

public class BitcoinCommandReading
        implements BitcoinReading<
                        Object,
                        BitcoinCommand,
                        InputConfig,
                        ReadingSettings<Object, InputConfig>,
                        BasicReadingResult<BitcoinCommand>,
                        BitcoinCommandInputField>,
                   SoleValueAggregatorReading<
                        Object,
                        BitcoinCommand,
                        InputConfig,
                        ReadingSettings<Object, InputConfig>,
                        BasicReadingResult<BitcoinCommand>,
                        BitcoinCommandInputField>
{

    private final BasicSoleValueAggregatorReadingCore<
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
                        BitcoinCommandInputField> readingCore;

    BitcoinCommandReading(
            BasicSoleValueAggregatorInputFieldCore<
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
                BitcoinCommandInputField> inputFieldCore) {
        readingCore = new BasicSoleValueAggregatorReadingCore<>(this, inputFieldCore);
    }

    @Override
    public BitcoinCommandInputField definition() {
        return readingCore.definition();
    }

    @Override
    public Class<BitcoinCommand> serializableClass() {
        return readingCore.serializableClass();
    }

    @Override
    public Direction getOperationType() {
        return readingCore.getOperationType();
    }

    @Override
    public boolean hasStarted() {
        return readingCore.hasStarted();
    }

    @Override
    public boolean isDone() {
        return readingCore.isCompleted();
    }

    @Override
    public Optional<ReadingSettings<Object, InputConfig>> settings() {
        return readingCore.settings();
    }

    @Override
    public Optional<BasicReadingResult<BitcoinCommand>> result() {
        return readingCore.isPremiseFound();
    }

    BasicSoleValueAggregatorReadingCore<
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
        BitcoinCommandInputField>
    getCore() {
        return readingCore;
    }

}
