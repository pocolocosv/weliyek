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

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;

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
                        ZYO extends WkSerdeDtreeNumberMsgWriter<
                                        ZT,
                                        WkSerdeDtreeOperationSettings,
                                        ?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
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
                        WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                          T,?,?,YD,YO,
                          ZT,?,?,ZYD,ZYO,? extends ZYD,
                          ?,?,VYD,VYO,? extends VYD,? extends YD>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT,
                        WkSerdeDtreeOperationSettings,
                        ZYO,
                        ZYD,
                        WkSerdeDtreeOperationSettings,
                        VYO,
                        VYD>
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
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> writerFieldCore,
    WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
      T,?,?,YD,YO,
      ZT,?,?,ZYD,ZYO,? extends ZYD,
      ?,?,VYD,VYO,? extends VYD,? extends YD> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, writerFieldCore, definitionCore, operationBody);
  }

  @Override
  protected WkSerdeDtreeDynamicPrimitiveArrayWriterCore<T,YO,YD,ZT,ZYO,ZYD,VYO,VYD>
  getThis() {
    return this;
  }

}
