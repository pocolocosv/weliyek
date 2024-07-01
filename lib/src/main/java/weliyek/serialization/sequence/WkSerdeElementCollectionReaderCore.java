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
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;

public abstract class WkSerdeElementCollectionReaderCore<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQ extends WkSerdeDtreeOperationInputRuntimeSequenceCommon<XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<XB,XBC,XQ>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeElementCollectionDefinition<T,XO,?,ET,?>,
                        XDC extends WkSerdeElementCollectionDefinitionCore<
                                      T,XS,XB,XBC,XQC,XR,XD,?,XO,XOC,AXBC,
                                      ?,?,?,?,?,?,?,?,?,?,
                                      ET,EXS,EXD,EXO,?,
                                      ?,?,? extends EXD,? extends XD,?>,
                        XO extends WkSerdeElementCollectionReader<T,XS,XQ,XR,XD,ET,EXD,EXO>,
                        XOC extends WkSerdeElementCollectionReaderCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XD,XDC,XO,?,AXBC,ET,EXS,EXD,EXO>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,EXS,?,?,EXD>>
        extends WkSerdeDtreeAggregatorMsgReaderCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XDC, XO, XOC, AXBC>
        implements WkSerdeElementCollectionReader<T, XS, XQ, XR, XD, ET, EXD, EXO>
{

  protected WkSerdeElementCollectionReaderCore(
    int index,
    WkSerdeDtreeMsgInputFieldCore<?,XS,?,?,AXBC,?,?,?> msgFieldCore,
    XDC definitionCore,
    XO operationBody) {
    super(index, msgFieldCore, definitionCore, operationBody);
  }

  @Override
  protected final void onAggregatorReadingInitialization() {
    onCollectionReadingInitialization();
  }

  protected abstract void onCollectionReadingInitialization();

  @Override
  protected final T onFullReadingCompletion() {
    return definitionCore().collectionSerializingFactory.apply(this.body());
  }

  @Override
  protected final void onPartialReadingCompletion() {
    // Nothing to do.
  }

  @Override
  public final Optional<WkSerdeDtreeMsgInputField<ET, EXD, EXO>> elements() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().elementComponent).asPacket());
  }

}
