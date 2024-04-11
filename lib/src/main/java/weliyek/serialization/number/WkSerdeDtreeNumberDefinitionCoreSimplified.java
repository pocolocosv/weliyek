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

import weliyek.serialization.WkSerdeDtreeOperationResultBasic;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrlSimplified;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSzPacketReaderOperationCoreFactory;
import weliyek.serialization.WkSzPacketWriterOperationCoreFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataDecoderEngineFactory;
import weliyek.serialization.WkSerdeDtreeNodeDataEncoderEngineFactory;

public final class WkSerdeDtreeNumberDefinitionCoreSimplified<
                        T extends Number,
                        XO extends WkSerdeDtreeNumberReader<
                                      T,
                                      WkSerdeDtreeOperationSettings,
                                      WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                      WkSerdeDtreeOperationResult<T>,
                                      D>,
                        YO extends WkSerdeDtreeNumberWriter<
                                      T,
                                      WkSerdeDtreeOperationSettings,
                                      WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                                      WkSerdeDtreeOperationResult<T>,
                                      D>,
                        D extends WkSerdeDtreeNumberDefinition<T>>
    extends WkSerdeDtreeNumberDefinitionCore<
                        T,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                          WkSerdeDtreeOperationResult<T>,
                        D, XO,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntimeCtrl<
                          WkSerdeDtreeBytestreamOutput,
                          WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                          WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>>,
                        WkSerdeDtreeOperationResult<T>,
                        D, YO,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        D,
                        WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D>>
{

  public WkSerdeDtreeNumberDefinitionCoreSimplified(
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore,
    WkSzPacketReaderOperationCoreFactory<
      T, WkSerdeDtreeOperationSettings, D, WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D>,
      XO, WkSerdeDtreeBytestreamInputBase<?>> rxOpFactory,
    WkSerdeDtreeNodeDataDecoderEngineFactory<
      T, ? super WkSerdeDtreeOperationInputRuntimeCtrl<WkSerdeDtreeBytestreamInput, WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
      WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>, ? super XO> rxSerializerFactory,
    WkSzPacketWriterOperationCoreFactory<
      T, WkSerdeDtreeOperationSettings, D, WkSerdeDtreeNumberDefinitionCoreSimplified<T,XO,YO,D>,
      YO, WkSerdeDtreeBytestreamOutputBase<?>> txOpFactory,
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
