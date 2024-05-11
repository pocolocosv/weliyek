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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;

public class WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<
                        X extends WkPrimitiveArray<?,?>,
                        XS extends WkSerdeDtreeOperationSettings,
                        D extends WkSerdeDtreePrimitiveArrayDefinition<X>,
                        XO extends WkSerdeDtreePrimitiveArrayReader<
                                          X,
                                          XS,
                                          WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                                          WkSerdeDtreeOperationResult<X>,
                                          D>>
    extends WkSerdeDtreeGenericPrimitiveArrayReaderCore<
                        X,
                        XS,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<X>,
                        XO,
                        WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<X,XS,D,XO>,
                        D,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<X,XS,XO,?,?,D>>
{

  private final Consumer<? super WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<X,XS,D,XO>>
                    onInitializing;

  public WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified(
    int index,
    XS settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
    WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<X,XS,XO,?,?,D> definitionCore,
    XO operationBody,
    Consumer<? super WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<X,XS,D,XO>> onInitializing) {
    super(index, settings, parentBytestream, msgFieldCore, definitionCore, operationBody);
    this.onInitializing = Objects.requireNonNull(onInitializing);
  }

  @Override
  protected void onDeserilizingOperationInitialization() {
    this.onInitializing.accept(getThis());
  }

  @Override
  protected void onSerializerFullReadingCompletion(X deserialized) {
    // Nothing to do.
  }

  @Override
  protected WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<X,XS,D,XO> getThis() {
    return this;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
