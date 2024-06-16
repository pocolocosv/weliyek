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
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.util.array.WkSerdeDtreeDynamicSequenceReaderCore;

public final class WkSerdeDtreeDynamicCollectionReaderCore<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XO extends WkSerdeDtreeDynamicCollectionReader<
                                        T,XS,
                                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                                        WkSerdeDtreeOperationResult<T>,
                                        XD,ZT,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD extends WkSerdeDtreeDynamicCollectionDefinition<
                                        T,XO,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?>,
                        ZT extends Number,
                        ZXS extends WkSerdeDtreeOperationSettings,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength>
    extends WkSerdeDtreeDynamicSequenceReaderCore<
                        T, XS,
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeCtrl<
                          WkSerdeDtreeBytestreamInput,
                          WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                          WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>>,
                        WkSerdeDtreeOperationResult<T>,
                        XO,
                        WkSerdeDtreeDynamicCollectionReaderCore<
                          T,XS,XO,XD,ZT,ZXS,ZXO,ZXD,ET,EXS,EXD,EXO,VXS>,
                        XD,
                        WkSerdeDtreeDynamicCollectionDefinitionCore<
                          T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?,? extends XD>,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        ZT, ZXS, ZXO, ZXD, VXS,
                        WkSerdeVariableSizeElementCollectionReader<T,VXS,ET,EXS,EXD,EXO>, // VXO
                        WkSerdeVariableSizeElementCollection<T,VXS,?,ET,EXS,EXD,EXO,?,?,?,?>>
    implements WkSerdeDtreeDynamicCollectionReader<
                        T, XS,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<T>,
                        XD, ZT, ZXO, ZXD, ET, EXS, EXD, EXO,
                        VXS>
{

  public WkSerdeDtreeDynamicCollectionReaderCore(
    int index,
    XS settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> readerFieldCore,
    WkSerdeDtreeDynamicCollectionDefinitionCore<
      T,XS,XO,XD,?,?,?,ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,ET,EXS,EXD,EXO,?,?,?,? extends EXD,VXS,?,? extends XD> definitionCore,
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
  protected
  WkSerdeDtreeDynamicCollectionReaderCore<T, XS, XO, XD, ZT, ZXS, ZXO, ZXD, ET, EXS, EXD, EXO, VXS>
  getThis() {
    return this;
  }

}
