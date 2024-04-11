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

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;

public class WkSerdeDtreeDynamicPrimitiveArrayWriterCore<
                        T extends WkPrimitiveArray<?,?>,
                        YO extends WkSerdeDtreeDynamicPrimitiveArrayWriter<
                                        T,
                                        WkSerdeDtreeOperationSettings,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD extends WkSerdeDtreeDynamicPrimitiveArrayDefinition<T,?,YO,? extends ZYD,? extends VYD>,
                        ZT extends Number,
                        ZYO extends WkSerdeDtreeNumberWriter<
                                        ZT,
                                        WkSerdeDtreeOperationSettings,
                                        ?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>,
                        VYO extends WkSerdeDtreeVariableSizePrimitiveArrayWriter<
                                        T,
                                        WkSerdeDtreeOperationSettings,
                                        ?,?,VYD>,
                        VYD extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<T>>
    extends WkSerdeDtreeDynamicSequenceWriterCore<
                        T,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YO,
                        WkSerdeDtreeDynamicPrimitiveArrayWriterCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YD,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT,
                        WkSerdeDtreeOperationSettings,
                        ZYO,
                        ZYD,
                        WkSerdeDtreeOperationSettings,
                        VYO,
                        VYD,
                        WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                          T,?,?,YD,YO,
                          ZT,?,?,ZYD,ZYO,?,
                          ?,?,VYD,VYO,?,?>>
    implements WkSerdeDtreeDynamicPrimitiveArrayWriter<
                        T,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<T>,
                        YD, ZT, ZYO, ZYD, VYO, VYD>
{

  public WkSerdeDtreeDynamicPrimitiveArrayWriterCore(
    int index,
    T serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<T,?,YD,?,?,?> packetHandlerCore,
    WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
      T,?,?,YD,YO,
      ZT,?,?,ZYD,ZYO,?,
      ?,?,VYD,VYO,?,?> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected WkSerdeDtreeDynamicPrimitiveArrayWriterCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>
  getThis() {
    return this;
  }

}
