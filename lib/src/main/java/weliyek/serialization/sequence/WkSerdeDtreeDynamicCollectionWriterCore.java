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

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgWriter;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
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
                                        T,?,YO,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS>,
                        ZT extends Number,
                        ZYS extends WkSerdeDtreeOperationSettings,
                        ZYO extends WkSerdeDtreeNumberMsgWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>,
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
                        WkSerdeDtreeDynamicCollectionDefinitionCore<
                          T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS,? extends YD>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ZT, ZYS, ZYO, ZYD, VYS,
                        WkSerdeVariableSizeElementCollectionWriter<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        WkSerdeVariableSizeElementCollection<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>> // VYD
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
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> writerFieldCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<
      T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,? extends ZYD,ET,?,?,?,EYS,EYD,EYO,? extends EYD,?,VYS,? extends YD> definitionCore,
    YO operationBody) {
    super(
          index,
          serializable,
          settings,
          parentBytestream,
          writerFieldCore,
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
