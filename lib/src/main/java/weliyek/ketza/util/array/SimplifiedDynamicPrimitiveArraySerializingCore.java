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
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberSerializing;

public class SimplifiedDynamicPrimitiveArraySerializingCore<
                        T extends PrimitiveArrayWrapper<?,?>,
                        YO extends DynamicPrimitiveArraySerializing<
                                        T,
                                        OperationSettings,
                                        SerializingRuntime<OutputBytestream>,
                                        SerializingResult,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD extends DynamicPrimitiveArrayDefinition<T,?,YO,? extends ZYD,? extends VYD>,
                        ZT extends Number,
                        ZYO extends NumberSerializing<
                                        ZT,
                                        OperationSettings,
                                        ?,?,ZYD>,
                        ZYD extends NumberDefinition<ZT,?>,
                        VYO extends VariableSizePrimitiveArrayWriting<
                                        T,
                                        OperationSettings,
                                        ?,?,VYD>,
                        VYD extends VariableSizePrimitiveArrayDefinition<T,?>>
    extends DynamicSequenceSerializingCore<
                        T,
                        OperationSettings,
                        OutputBytestream,
                        OutputBytestreamGeneralBase<? extends OutputBytestream>,
                        SerializingRuntime<OutputBytestream>,
                        WritingRuntimeControl<
                          OutputBytestream,
                          OutputBytestreamGeneralBase<? extends OutputBytestream>,
                          SerializingRuntime<OutputBytestream>>,
                        SerializingResult,
                        YO,
                        SimplifiedDynamicPrimitiveArraySerializingCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD,
                        OutputBytestreamGeneralBase<?>,
                        ZT,
                        OperationSettings,
                        ZYO,
                        ZYD,
                        OperationSettings,
                        VYO,
                        VYD,
                        SimplifiedDynamicPrimitiveArrayDefinitionCore<
                          T,?,?,YD,YO,
                          ZT,?,?,ZYD,ZYO,?,
                          ?,?,VYD,VYO,?,?>>
    implements DynamicPrimitiveArraySerializing<
                        T,
                        OperationSettings,
                        SerializingRuntime<OutputBytestream>,
                        SerializingResult,
                        YD, ZT, ZYO, ZYD, VYO, VYD>
{

  public SimplifiedDynamicPrimitiveArraySerializingCore(
    int index,
    T serializable,
    OperationSettings settings,
    OutputBytestreamGeneralBase<?> parentBytestream,
    SerializingFieldCore<T,?,YD,?,?,?> packetHandlerCore,
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
