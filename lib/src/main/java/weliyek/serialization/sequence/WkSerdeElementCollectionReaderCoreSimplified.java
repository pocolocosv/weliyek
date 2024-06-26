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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;

public final class WkSerdeElementCollectionReaderCoreSimplified<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeElementCollectionDefinition<T,XO,?,ET,?>,
                        XO extends WkSerdeElementCollectionReader<
                                        T,
                                        XS,
                                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        XD,
                                        ET,EXD,EXO>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>>
    extends WkSerdeElementCollectionReaderCore<
                        T,
                        XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        XD,
                        WkSerdeElementCollectionDefinitionCoreSimplified<
                          T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD,? extends XD>,
                        XO,
                        WkSerdeElementCollectionReaderCoreSimplified<
                          T,XS,XD,XO,ET,EXS,EXD,EXO>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        ET,
                        EXS,
                        EXD,
                        EXO>
{

  WkSerdeElementCollectionReaderCoreSimplified(
    int index,
    WkSerdeDtreeMsgInputFieldCore<?,XS,?,?,WkSerdeDtreeBytestreamInputBase<?>,?,?,?>
      readerFieldCore,
    WkSerdeElementCollectionDefinitionCoreSimplified<
      T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,? extends EXD,? extends XD> definitionCore,
    XO operationBody) {
    super(index, readerFieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onCollectionReadingInitialization() {
    // Nothing to do.
  }

  @Override
  protected WkSerdeElementCollectionReaderCoreSimplified<T,XS,XD,XO,ET,EXS,EXD,EXO> getThis() {
    return this;
  }

}
