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

import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeMsgWriter;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;

public final class WkSerdeElementCollectionWriterCoreSimplified<
                        T extends Collection<ET>,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeElementCollectionDefinition<T,?,YO,ET,?>,
                        YO extends WkSerdeElementCollectionWriter<
                                        T,
                                        YS,
                                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        YD,
                                        ET,EYD,EYO>,
                        ET,
                        EYS extends WkSerdeDtreeOperationSettings,
                        EYD extends WkSerdeDtreeStructDefinition<ET>,
                        EYO extends WkSerdeDtreeMsgWriter<ET,EYS,?,?,EYD>>
    extends WkSerdeElementCollectionWriterCore<
                        T,
                        YS,
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        YD,
                        YO,
                        WkSerdeElementCollectionWriterCoreSimplified<T,YS,YD,YO,ET,EYS,EYD,EYO>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        ET,
                        EYS,
                        EYD,
                        EYO,
                        WkSerdeElementCollectionDefinitionCoreSimplified<
                          T,?,?,?,YS,YD,YO,
                          ET,?,?,?,EYS,EYD,EYO,?,?>>
{

  WkSerdeElementCollectionWriterCoreSimplified(
    int index,
    T serializable,
    YS settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<T,?,YD,?,?,?> packetHandlerCore,
    WkSerdeElementCollectionDefinitionCoreSimplified<
      T,?,?,?,YS,YD,YO,ET,?,?,?,EYS,EYD,EYO,?,?> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected void onCollectionWritingInitialization() {
    // Nothing to do.

  }

  @Override
  protected WkSerdeElementCollectionWriterCoreSimplified<T,YS,YD,YO,ET,EYS,EYD,EYO> getThis() {
    return this;
  }

}
