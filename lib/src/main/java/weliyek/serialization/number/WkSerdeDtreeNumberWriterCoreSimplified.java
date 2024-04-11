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
package weliyek.serialization.number;

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;

public class WkSerdeDtreeNumberWriterCoreSimplified<
                        Y extends Number,
                        YO extends WkSerdeDtreeNumberWriter<
                                        Y,
                                        WkSerdeDtreeOperationSettings,
                                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<Y>,
                                        YD>,
                        YD extends WkSerdeDtreeNumberDefinition<Y>>
    extends WkSerdeDtreeNumberWriterCore<
                        Y,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<Y>,
                        YO,
                        WkSerdeDtreeNumberWriterCoreSimplified<Y,YO,YD>,
                        YD,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeDtreeNumberDefinitionCoreSimplified<Y,?,YO,YD>>
{

  WkSerdeDtreeNumberWriterCoreSimplified(
    int index,
    Y serializable,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeNodeDataOutputComponentCore<Y,?,YD,?,?,?> packetHandlerCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<Y,?,YO,YD> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  public long expectedBytes() {
    return 0;
  }

  @Override
  protected WkSerdeDtreeNumberWriterCoreSimplified<Y,YO,YD> getThis() {
    return this;
  }

  @Override
  protected void onSerializingOperationInitialization() {
    // Nothing to do.
  }

}
