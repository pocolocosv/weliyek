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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;

public class WkSerdeDtreeDynamicPrimitiveArrayReaderCore<
                        T extends WkPrimitiveArray<?,?>,
                        XO extends WkSerdeDtreeDynamicPrimitiveArrayReader<
                                        T,
                                        WkSerdeDtreeOperationSettings,
                                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD extends WkSerdeDtreeDynamicPrimitiveArrayDefinition<
                                        T,XO,?,? extends ZXD,? extends VXD>,
                        ZT extends Number,
                        ZXO extends WkSerdeDtreeNumberMsgReader<
                                        ZT,
                                        WkSerdeDtreeOperationSettings,
                                        ?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        VXO extends WkSerdeDtreeVariableSizePrimitiveArrayReader<
                                        T,
                                        WkSerdeDtreeOperationSettingsVariableLength,
                                        ?,?,VXD>,
                        VXD extends WkSerdeDtreeVariableSizePrimitiveArrayDefinition<T>>
    extends WkSerdeDtreeDynamicSequenceReaderCore<
                        T,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        XO,
                        WkSerdeDtreeDynamicPrimitiveArrayReaderCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XD,
                        WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
                          T,XD,XO,?,?,
                          ZT,ZXD,ZXO,?,?,? extends ZXD,
                          VXD,VXO,?,?,? extends VXD,? extends XD>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        ZT,
                        WkSerdeDtreeOperationSettings,
                        ZXO,
                        ZXD,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        VXO,
                        VXD>
    implements WkSerdeDtreeDynamicPrimitiveArrayReader<
                        T,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<T>,
                        XD, ZT, ZXO, ZXD, VXO, VXD>
{

  public WkSerdeDtreeDynamicPrimitiveArrayReaderCore(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeDynamicPrimitiveArrayDefinitionCore<
      T,XD,XO,?,?,
      ZT,ZXD,ZXO,?,?,? extends ZXD,
      VXD,VXO,?,?,? extends VXD,? extends XD> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, readerFieldCore, definitionCore, operationBody);
  }

  @Override
  protected T onFullReadingCompletion() {
    return variableSequence().get()
                             .firstOperation().get()
                             .result().get()
                             .serializable().get();
  }

  @Override
  protected void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  protected WkSerdeDtreeDynamicPrimitiveArrayReaderCore<T,XO,XD,ZT,ZXO,ZXD,VXO,VXD>
  getThis() {
    return this;
  }

}
