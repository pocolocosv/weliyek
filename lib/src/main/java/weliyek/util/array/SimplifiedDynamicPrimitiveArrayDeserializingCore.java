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
package weliyek.util.array;

import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzReadingRuntime;
import weliyek.serialization.WkSzReadingRuntimeControl;
import weliyek.serialization.WkSzVariableLengthOperationSettings;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberReader;

public class SimplifiedDynamicPrimitiveArrayDeserializingCore<
                        T extends WkPrimitiveArray<?,?>,
                        XO extends DynamicPrimitiveArrayDeserializing<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD extends WkSzDynamicPrimitiveArrayDefinition<T,XO,?,? extends ZXD,? extends VXD>,
                        ZT extends Number,
                        ZXO extends WkSzNumberReader<
                                        ZT,
                                        WkSzOperationSettings,
                                        ?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        VXO extends WkSzVariableSizePrimitiveArrayReader<
                                        T,
                                        WkSzVariableLengthOperationSettings,
                                        ?,?,VXD>,
                        VXD extends WkSzVariableSizePrimitiveArrayDefinition<T,VXO>>
    extends DynamicSequenceDeserializingCore<
                        T,
                        WkSzOperationSettings,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XO,
                        SimplifiedDynamicPrimitiveArrayDeserializingCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        ZT,
                        WkSzOperationSettings,
                        ZXO,
                        ZXD,
                        WkSzVariableLengthOperationSettings,
                        VXO,
                        VXD,
                        SimplifiedDynamicPrimitiveArrayDefinitionCore<
                          T,XD,XO,?,?,
                          ZT,ZXD,ZXO,?,?,?,
                          VXD,VXO,?,?,?,?>>
    implements DynamicPrimitiveArrayDeserializing<
                        T,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<T>,
                        XD, ZT, ZXO, ZXD, VXO, VXD>
{

  public SimplifiedDynamicPrimitiveArrayDeserializingCore(
    int index,
    WkSzOperationSettings settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<T,?,XD,?,?,?> deserializingfieldCore,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<
      T,XD,XO,?,?,
      ZT,ZXD,ZXO,?,?,?,
      VXD,VXO,?,?,?,?> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, deserializingfieldCore, definitionCore, operationBody);
  }

  @Override
  protected T onFullReadingCompletion() {
    return variableSequence().field().get()
                             .firstOperation().get()
                             .result().get()
                             .deserialized().get();
  }

  @Override
  protected void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  protected SimplifiedDynamicPrimitiveArrayDeserializingCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>
  getThis() {
    return this;
  }

}
