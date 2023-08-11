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
package weliyek.amat.basic.string;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.WkSzWritingResult;
import weliyek.amat.base.output.WkSzWritingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriter;
import weliyek.ketza.util.array.WkSzPrimitiveArrayDefinition;
import weliyek.ketza.util.array.PrimitiveArrayWrapper;
import weliyek.ketza.util.array.PrimitiveArrayWriting;

public interface WkSzStringFromPrimitiveWriter<
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YR extends WkSzWritingResult,
                        YD extends WkSzStringFromPrimitiveDefinition<?,?,?>,
                        SY extends PrimitiveArrayWrapper<?, ?>,
                        SYD extends WkSzPrimitiveArrayDefinition<SY,?>,
                        SYO extends PrimitiveArrayWriting<SY,?,?,?,SYD>>
        extends WkSzStringFromPrimitiveOperation<
                        YS, YQ, YR, YD,
                        WkSzPacketWriterField<String,YD,?>,
                        SYO,
                        WkSzPacketWriterField<SY,SYD,SYO>,
                        WkSzPacketWriterSubfield<SY,SYD,SYO>>,
                WkSzAggregatorWriter<String, YS, YQ, YR, YD>
{

}
