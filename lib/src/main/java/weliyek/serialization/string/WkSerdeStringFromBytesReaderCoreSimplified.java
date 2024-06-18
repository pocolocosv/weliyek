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
package weliyek.serialization.string;

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayReader;

public class WkSerdeStringFromBytesReaderCoreSimplified<
                        XS extends WkSerdeDtreeOperationSettings,
                        XO extends WkSerdeStringFromBytesReader<
                                      XS,
                                      WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                      WkSerdeDtreeOperationResult<String>,
                                      XD,SXD,SXO>,
                        XD extends WkSerdeStringFromBytesDefinition<XO,?,? extends SXD>,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXO extends WkSerdeDtreeByteArrayReader<SXS,?,?,SXD>,
                        SXD extends WkSerdeDtreeByteArrayDefinition>
    extends WkSerdeStringFromBytesReaderCore<
                        XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<String>,
                        XO,
                        WkSerdeStringFromBytesReaderCoreSimplified<XS,XO,XD,SXS,SXO,SXD>,
                        XD,
                        WkSerdeStringFromBytesDefinitionCoreSimplified<XS,XO,XD,?,?,?,SXS,SXO,SXD,?,?,?,? extends SXD,? extends XD>,
                        WkSerdeDtreeBytestreamInputBase<?>, SXS, SXO, SXD>
{

  public WkSerdeStringFromBytesReaderCoreSimplified(
    int index,
    XS settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeStringFromBytesDefinitionCoreSimplified<XS,XO,XD,?,?,?,SXS,SXO,SXD,?,?,?,? extends SXD,? extends XD> definitionCore,
    XO operationBody) {
    super(
        index,
        settings,
        parentBytestream,
        readerFieldCore,
        definitionCore,
        operationBody);
  }

  @Override
  protected void onStringFromPrimitiveReadingInitialization() {
    // Nothing to do.
  }

  @Override
  protected void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  protected WkSerdeStringFromBytesReaderCoreSimplified<XS,XO,XD,SXS,SXO,SXD> getThis() {
    return this;
  }

}
