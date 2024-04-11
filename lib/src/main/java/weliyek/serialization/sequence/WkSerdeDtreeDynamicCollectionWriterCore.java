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
package weliyek.serialization.sequence;

import java.util.Collection;

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataWriter;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeDtreeNumberWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceWriterCore;

public final class WkSerdeDtreeDynamicCollectionWriterCore<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YO extends WkSerdeDtreeDynamicCollectionWriter<
                                        T,YS,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD,ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD extends WkSerdeDtreeDynamicCollectionDefinition<
                                        T,?,YO,?,ET,?,?,?,EYS,?,EYO,?,?,VYS>,
                        ZT extends Number,
                        ZYS extends WkSerdeDtreeOperationSettings,
                        ZYO extends WkSerdeDtreeNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberDefinition<ZT>,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeNodeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeNodeDataWriter<ET,EYS,?,?,EYD>,
                        VYS extends WkSerdeDtreeOperationSettings>
    extends WkSerdeDtreeDynamicSequenceWriterCore<
                        T, YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YO,
                        WkSerdeDtreeDynamicCollectionWriterCore<
                          T,YS,YO,YD,ZT,ZYS,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT, ZYS, ZYO, ZYD, VYS,
                        WkSerdeVariableSizeElementCollectionWriter<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        WkSerdeVariableSizeElementCollection<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>, // VYD
                        WkSerdeDtreeDynamicCollectionDefinitionCore<
                          T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS,?>>
    implements WkSerdeDtreeDynamicCollectionWriter<
                        T,
                        YS,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<T>,
                        YD, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
{

  public WkSerdeDtreeDynamicCollectionWriterCore(
    int index,
    T serializable,
    YS settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<T, ?, YD, ?, ?, ?> packetHandlerCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<T, ?, ?, ?, YS, YO, YD, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS, ?> definitionCore,
    YO operationBody) {
    super(
          index,
          serializable,
          settings,
          parentBytestream,
          packetHandlerCore,
          definitionCore,
          operationBody);
  }

  @Override
  protected
  WkSerdeDtreeDynamicCollectionWriterCore<T, YS, YO, YD, ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
  getThis() {
    return this;
  }

}
