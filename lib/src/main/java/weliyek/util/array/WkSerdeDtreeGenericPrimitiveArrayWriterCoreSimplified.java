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

import java.util.Objects;
import java.util.function.Consumer;

import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;

public class WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<
                        Y extends WkPrimitiveArray<?,?>,
                        YS extends WkSerdeDtreeOperationSettings,
                        D extends WkSerdeDtreePrimitiveArrayDefinition<Y>,
                        YO extends WkSerdeDtreePrimitiveArrayWriter<
                                        Y,
                                        YS,
                                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                                        WkSerdeDtreeOperationResult<Y>,
                                        D>>
    extends WkSerdeDtreeGenericPrimitiveArrayWriterCore<
                        Y,
                        YS,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<Y>,
                        YO,
                        WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<Y,YS,D,YO>,
                        D,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<Y,?,?,YS,YO,D>>
{

  private final Consumer<? super WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<Y,YS,D,YO>>
                    onInitializing;

  public WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified(
    int index,
    Y serializable,
    YS settings,
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream,
    WkSerdeDtreeMsgOutputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
    WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<Y,?,?,YS,YO,D> definitionCore,
    YO operationBody,
    Consumer<? super WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<Y,YS,D,YO>> onInitializing) {
    super(index, serializable, settings, parentBytestream, msgFieldCore, definitionCore, operationBody);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onSerializingOperationInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected WkSerdeDtreeGenericPrimitiveArrayWriterCoreSimplified<Y, YS, D, YO> getThis() {
    return this;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
