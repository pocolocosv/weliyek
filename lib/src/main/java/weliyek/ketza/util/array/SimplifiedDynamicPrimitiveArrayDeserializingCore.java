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
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.ReadingRuntimeControl;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.basic.dynamic.sequence.VariableLengthSettings;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberReader;

public class SimplifiedDynamicPrimitiveArrayDeserializingCore<
                        T extends PrimitiveArrayWrapper<?,?>,
                        XO extends DynamicPrimitiveArrayDeserializing<
                                        T,
                                        OperationSettings,
                                        DeserializingRuntime<WkSzInputBytestream>,
                                        DeserializingResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD extends WkSzDynamicPrimitiveArrayDefinition<T,XO,?,? extends ZXD,? extends VXD>,
                        ZT extends Number,
                        ZXO extends WkSzNumberReader<
                                        ZT,
                                        OperationSettings,
                                        ?,?,ZXD>,
                        ZXD extends WkSzNumberDefinition<ZT,ZXO>,
                        VXO extends WkSzVariableSizePrimitiveArrayReader<
                                        T,
                                        VariableLengthSettings,
                                        ?,?,VXD>,
                        VXD extends WkSzVariableSizePrimitiveArrayDefinition<T,VXO>>
    extends DynamicSequenceDeserializingCore<
                        T,
                        OperationSettings,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        DeserializingRuntime<WkSzInputBytestream>,
                        ReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          DeserializingRuntime<WkSzInputBytestream>>,
                        DeserializingResult<T>,
                        XO,
                        SimplifiedDynamicPrimitiveArrayDeserializingCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD,
                        WkSzInputBytestreamBase<?>,
                        ZT,
                        OperationSettings,
                        ZXO,
                        ZXD,
                        VariableLengthSettings,
                        VXO,
                        VXD,
                        SimplifiedDynamicPrimitiveArrayDefinitionCore<
                          T,XD,XO,?,?,
                          ZT,ZXD,ZXO,?,?,?,
                          VXD,VXO,?,?,?,?>>
    implements DynamicPrimitiveArrayDeserializing<
                        T,
                        OperationSettings,
                        DeserializingRuntime<WkSzInputBytestream>,
                        DeserializingResult<T>,
                        XD, ZT, ZXO, ZXD, VXO, VXD>
{

  public SimplifiedDynamicPrimitiveArrayDeserializingCore(
    int index,
    OperationSettings settings,
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
