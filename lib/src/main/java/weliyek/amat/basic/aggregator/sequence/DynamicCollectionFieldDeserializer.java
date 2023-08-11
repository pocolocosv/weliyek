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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Collection;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;
import weliyek.amat.basic.dynamic.sequence.WkSzVariableLengthOperationSettings;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;
import weliyek.ketza.util.array.DynamicSequenceDeserializing;
import weliyek.serialization.WkSzDefinition;

public interface DynamicCollectionFieldDeserializer<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzReadingRuntime<?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkSzDynamicCollectionDefinition<T,?,?,?,?,?,?,?,?,?,?,?,?,?>,
                        ZT extends Number,
                        ZXO extends WkSzNumberReader<ZT,?,?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>,
                        VXS extends WkSzVariableLengthOperationSettings>
    extends WkSzDynamicCollectionOperation<
                        XS, XQ, XR, XD,
                        WkSzPacketReaderField<T,XD,?>,
                        ZXO,
                        WkSzPacketReaderField<ZT,ZXD,ZXO>,
                        WkSzPacketReaderSubfield<ZT,ZXD,ZXO>,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>,
                        WkSzPacketReaderField<T,
                          VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                          VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>>,
                          WkSzPacketReaderSubfield<
                          T,
                          VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>,
                          VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>>>,
            DynamicSequenceDeserializing<
                        T, XS, XQ, XR, XD, ZT, ZXO, ZXD,
                        VariableSizeCollectionFieldDeserializer<T,VXS,ET,EXS,EXD,EXO>,
                        VariableSizeCollectionField<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>>
{

}
