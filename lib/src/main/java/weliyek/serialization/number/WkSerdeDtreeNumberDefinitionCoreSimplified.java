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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgPrimitiveReaderFactory;
import weliyek.serialization.WkSerdeDtreeMsgPrimitiveWriterCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataDecoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataEncoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationResultBasic;
import weliyek.serialization.WkSerdeDtreeOperationSettings;

public final class WkSerdeDtreeNumberDefinitionCoreSimplified<
                        T extends Number,
                        XO extends WkSerdeDtreeNumberMsgReader<
                                      T,
                                      WkSerdeDtreeOperationSettings,
                                      WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                      WkSerdeDtreeOperationResult<T>,
                                      D>,
                        YO extends WkSerdeDtreeNumberMsgWriter<
                                      T,
                                      WkSerdeDtreeOperationSettings,
                                      WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                      WkSerdeDtreeOperationResult<T>,
                                      D>,
                        D extends WkSerdeDtreeNumberStructDefinition<T>>
    extends WkSerdeDtreeNumberDefinitionCore<
                        T,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        D,
                        WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,?,D>,
                        XO,
                        WkSerdeDtreeNumberMsgReaderCoreSimplified<T,XO,D>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        D,
                        WkSerdeDtreeNumberDefinitionCoreSimplified<T,?,YO,D>,
                        YO,
                        WkSerdeDtreeNumberMsgWriterCoreSimplified<T,YO,D>,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        D,
                        WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D>>
{

  public WkSerdeDtreeNumberDefinitionCoreSimplified(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore,
    WkSerdeDtreeMsgPrimitiveReaderFactory<
      WkSerdeDtreeOperationSettings,
      WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,?,D>,
      WkSerdeDtreeNumberMsgReaderCoreSimplified<T,XO,D>,
      WkSerdeDtreeBytestreamInputBase<?>> rxOpFactory,
    WkSerdeDtreeNodeDataDecoderEngineFactory<
      T, ? super WkSerdeDtreeOperationInputRuntimeCtrl<WkSerdeDtreeBytestreamInput, WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
      WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>, ? super XO> rxSerializerFactory,
    WkSerdeDtreeMsgPrimitiveWriterCoreFactory<
      T,
      WkSerdeDtreeOperationSettings,
      WkSerdeDtreeNumberDefinitionCoreSimplified<T,?,YO,D>,
      WkSerdeDtreeNumberMsgWriterCoreSimplified<T,YO,D>,
      WkSerdeDtreeBytestreamOutputBase<?>> txOpFactory,
    WkSerdeDtreeNodeDataEncoderEngineFactory<
      T, ? super WkSerdeDtreeOperationOutputRuntimeCtrl<WkSerdeDtreeBytestreamOutput, WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
      WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>, ? super YO> txSerializerFactory,
    D definitionBody,
    Class<T> serializableClass) {
    super(
          componentCore,
          WkSerdeDtreeOperationInputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          rxOpFactory,
          rxSerializerFactory,
          WkSerdeDtreeOperationOutputRuntimeCtrlSimplified::new,
          WkSerdeDtreeOperationResultBasic::new,
          txOpFactory,
          txSerializerFactory,
          definitionBody,
          serializableClass);
  }

  @Override
  protected WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D> getThis() {
    return this;
  }

}
