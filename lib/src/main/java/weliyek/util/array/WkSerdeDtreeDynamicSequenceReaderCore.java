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

import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeDtreeNumberStructDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberMsgReader;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceDefinition;
import weliyek.serialization.sequence.WkSerdeDtreeVariableSizeSequenceReader;

public abstract class WkSerdeDtreeDynamicSequenceReaderCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQ extends WkSerdeDtreeOperationInputRuntime<XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeCtrl<XB,XBC,XQ>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XO extends WkSerdeDtreeDynamicSequenceReader<T,XS,XQ,XR,XD,ZT,ZXO,ZXD,VXO,VXD>,
                        XOC extends WkSerdeDtreeDynamicSequenceReaderCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XO,?,XD,XDC,AXBC,
                                        ZT,ZXS,ZXO,ZXD,
                                        VXS,VXO,VXD>,
                        XD extends WkSerdeDtreeDynamicSequenceDefinition<T,XO,?,?,?>,
                        XDC extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XO,XOC,XD,?,AXBC,
                                        ?,?,?,?,?,?,?,?,?,?,
                                        ZT,ZXS,ZXO,ZXD,?,?,?,? extends ZXD,
                                        VXS,VXO,VXD,?,?,?,? extends VXD,
                                        ? extends XD,?>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        ZT extends Number,
                        ZXS extends WkSerdeDtreeOperationSettings,
                        ZXO extends WkSerdeDtreeNumberMsgReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberStructDefinition<ZT>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength,
                        VXO extends WkSerdeDtreeVariableSizeSequenceReader<T,VXS,?,?,VXD>,
                        VXD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>>
    extends WkSerdeDtreeAggregatorMsgReaderCore<
                        T, XS, XB, XBC, XQ, XQC, XR, XD, XDC, XO, XOC, AXBC>
    implements WkSerdeDtreeDynamicSequenceReader<T, XS, XQ, XR, XD, ZT, ZXO, ZXD, VXO, VXD>
{

  protected WkSerdeDtreeDynamicSequenceReaderCore(
    int index,
    XS settings,
    AXBC parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
    XDC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, msgFieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onAggregatorReadingInitialization() {
  }

  @Override
  public Optional<WkSerdeDtreeMsgInputField<ZT, ZXD, ZXO>> size() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().sizeComponent));
  }

  @Override
  public Optional<WkSerdeDtreeMsgInputField<T, VXD, VXO>> variableSequence() {
    return Optional.ofNullable(getSubfieldpacketFor(definitionCore().varseqComponent));
  }

}
