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
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;

public final class WkSerdeDtreeNumberMsgReaderCoreSimplified<
                        X extends Number,
                        XO extends WkSerdeDtreeNumberMsgReader<
                                      X,
                                      WkSerdeDtreeOperationSettings,
                                      WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                      WkSerdeDtreeOperationResult<X>,
                                      XD>,
                        XD extends WkSerdeDtreeNumberStructDefinition<X>>
    extends WkSerdeDtreeNumberMsgReaderCore<
                        X,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<X>,
                        XO,
                        WkSerdeDtreeNumberMsgReaderCoreSimplified<X,XO,XD>,
                        XD,
                        WkSerdeDtreeNumberDefinitionCoreSimplified<X,XO,?,XD>,
                        WkSerdeDtreeBytestreamInputBase<?>>
{

  public WkSerdeDtreeNumberMsgReaderCoreSimplified(
    int index,
    WkSerdeDtreeMsgInputFieldCore<?,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamInputBase<?>,?,?,?>
      readerFieldCore,
    WkSerdeDtreeNumberDefinitionCoreSimplified<X,XO,?,XD> definitionCore,
    XO operationBody) {
    super(index, readerFieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onSerializerFullReadingCompletion(X deserialized) {
    // Nothing to do.
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  protected WkSerdeDtreeNumberMsgReaderCoreSimplified<X,XO,XD> getThis() {
    return this;
  }

  @Override
  protected void onDeserilizingOperationInitialization() {
    // Nothing to do.
  }

}
