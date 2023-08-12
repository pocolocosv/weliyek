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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;

public class SimplifiedDynamicPrimitiveArraySerializingCore<
                        T extends WkPrimitiveArray<?,?>,
                        YO extends DynamicPrimitiveArraySerializing<
                                        T,
                                        WkSzOperationSettings,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD extends WkSzDynamicPrimitiveArrayDefinition<T,?,YO,? extends ZYD,? extends VYD>,
                        ZT extends Number,
                        ZYO extends WkSzNumberWriter<
                                        ZT,
                                        WkSzOperationSettings,
                                        ?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        VYO extends WkSzVariableSizePrimitiveArrayWriter<
                                        T,
                                        WkSzOperationSettings,
                                        ?,?,VYD>,
                        VYD extends WkSzVariableSizePrimitiveArrayDefinition<T,?>>
    extends DynamicSequenceSerializingCore<
                        T,
                        WkSzOperationSettings,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO,
                        SimplifiedDynamicPrimitiveArraySerializingCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT,
                        WkSzOperationSettings,
                        ZYO,
                        ZYD,
                        WkSzOperationSettings,
                        VYO,
                        VYD,
                        SimplifiedDynamicPrimitiveArrayDefinitionCore<
                          T,?,?,YD,YO,
                          ZT,?,?,ZYD,ZYO,?,
                          ?,?,VYD,VYO,?,?>>
    implements DynamicPrimitiveArraySerializing<
                        T,
                        WkSzOperationSettings,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        YD, ZT, ZYO, ZYD, VYO, VYD>
{

  public SimplifiedDynamicPrimitiveArraySerializingCore(
    int index,
    T serializable,
    WkSzOperationSettings settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
    SimplifiedDynamicPrimitiveArrayDefinitionCore<
      T,?,?,YD,YO,
      ZT,?,?,ZYD,ZYO,?,
      ?,?,VYD,VYO,?,?> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected SimplifiedDynamicPrimitiveArraySerializingCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>
  getThis() {
    return this;
  }

}
