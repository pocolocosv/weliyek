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
package weliyek.ketza.util.array;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterField;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberWriter;

public interface DynamicPrimitiveArraySerializing<
                        T extends PrimitiveArrayWrapper<?,?>,
                        YS extends OperationSettings,
                        YQ extends SerializingRuntime<?>,
                        YR extends SerializingResult,
                        D extends WkSzDynamicPrimitiveArrayDefinition<T,?,?,?,?>,
                        ZT extends Number,
                        ZYO extends WkSzNumberWriter<ZT,?,?,?,ZD>,
                        ZD extends WkSzNumberDefinition<ZT,?>,
                        VYO extends WkSzVariableSizePrimitiveArrayWriter<T,?,?,?,VD>,
                        VD extends WkSzVariableSizePrimitiveArrayDefinition<T,?>>
    extends WkSzDynamicPrimitiveArrayOperation<
                        YS, YQ, YR, D,
                        WkSzPacketWriterField<T,D,?>,
                        ZYO,
                        WkSzPacketWriterField<ZT,ZD,ZYO>,
                        WkSzPacketWriterSubfield<ZT,ZD,ZYO>,
                        VYO,
                        WkSzPacketWriterField<T,VD,VYO>,
                        WkSzPacketWriterSubfield<T,VD,VYO>>,
            DynamicSequenceSerializing<T, YS, YQ, YR, D, ZT, ZYO, ZD, VYO, VD>
{

}
