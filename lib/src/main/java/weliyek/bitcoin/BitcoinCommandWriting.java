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

import java.util.Optional;

import weliyek.amat.base.Direction;
import weliyek.amat.base.output.BasicWritingResult;
import weliyek.amat.base.output.WritingSettings;
import weliyek.amat.basic.aggregator.BasicSoleValueAggregatorOutputFieldCore;
import weliyek.amat.basic.aggregator.BasicSoleValueAggregatorWritingCore;
import weliyek.amat.newserialization.SoleValueAggregatorWriting;
import weliyek.amat.newserialization.operation.OutputConfig;
import weliyek.amat.newserialization.operation.SizeProviderAndPaddingElementWritingConfig;
import weliyek.ketza.util.array.BasicByteArrayWrapperOutputField;
import weliyek.ketza.util.array.BasicByteArrayWrapperWriting;
import weliyek.ketza.util.array.ByteArrayWrapper;

public class BitcoinCommandWriting
        implements BitcoinWriting<
                        Object,
                        BitcoinCommand,
                        OutputConfig,
                        WritingSettings<Object,BitcoinCommand,OutputConfig>,
                        BasicWritingResult,
                        BitcoinCommandOutputField>,
                   SoleValueAggregatorWriting<
                        Object,
                        BitcoinCommand,
                        OutputConfig,
                        WritingSettings<Object,BitcoinCommand,OutputConfig>,
                        BasicWritingResult,
                        BitcoinCommandOutputField>
{

    private final BasicSoleValueAggregatorWritingCore<
                        ByteArrayWrapper,
                        SizeProviderAndPaddingElementWritingConfig<Byte>,
                        BasicByteArrayWrapperWriting<Object>,
                        BasicByteArrayWrapperOutputField<Object>,
                        BitcoinCommand,
                        Object,
                        OutputConfig,
                        BitcoinCommandWriting,
                        BitcoinCommandOutputField> writingCore;

    BitcoinCommandWriting(
            BasicSoleValueAggregatorOutputFieldCore<
                ByteArrayWrapper,
                SizeProviderAndPaddingElementWritingConfig<Byte>,
                BasicByteArrayWrapperWriting<Object>,
                BasicByteArrayWrapperOutputField<Object>,
                BitcoinCommand,
                Object,
                OutputConfig,
                BitcoinCommandWriting,
                BitcoinCommandOutputField> outputFieldCore) {
        writingCore = new BasicSoleValueAggregatorWritingCore<ByteArrayWrapper,
        SizeProviderAndPaddingElementWritingConfig<Byte>,
        BasicByteArrayWrapperWriting<Object>,
        BasicByteArrayWrapperOutputField<Object>,
        BitcoinCommand,
        Object,
        OutputConfig,
        BitcoinCommandWriting,
        BitcoinCommandOutputField>(this, outputFieldCore);
    }

    @Override
    public BitcoinCommandOutputField definition() {
        return writingCore.definition();
    }

    @Override
    public Class<BitcoinCommand> serializableClass() {
        return writingCore.serializableClass();
    }

    @Override
    public Direction getOperationType() {
        return writingCore.getOperationType();
    }

    @Override
    public boolean hasStarted() {
        return writingCore.hasStarted();
    }

    @Override
    public boolean isDone() {
        return writingCore.isCompleted();
    }

    @Override
    public Optional<WritingSettings<Object, BitcoinCommand, OutputConfig>> settings() {
        return writingCore.settings();
    }

    @Override
    public Optional<BasicWritingResult> result() {
        return writingCore.isPremiseFound();
    }

    BasicSoleValueAggregatorWritingCore<
        ByteArrayWrapper,
        SizeProviderAndPaddingElementWritingConfig<Byte>,
        BasicByteArrayWrapperWriting<Object>,
        BasicByteArrayWrapperOutputField<Object>,
        BitcoinCommand,
        Object,
        OutputConfig,
        BitcoinCommandWriting,
        BitcoinCommandOutputField>
    core() {
        return writingCore;
    }

}
