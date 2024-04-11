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

import weliyek.serialization.WkSerdeDtreeAggregatorReaderCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.number.WkSerdeDtreeNumberDefinition;
import weliyek.serialization.number.WkSerdeDtreeNumberReader;
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
                                        T,XS,XB,XBC,XQ,XQC,XR,XO,?,XD,AXB,
                                        ZT,ZXS,ZXO,ZXD,
                                        VXS,VXO,VXD,
                                        DC>,
                        XD extends WkSerdeDtreeDynamicSequenceDefinition<T,XO,?,?,?>,
                        AXB extends WkSerdeDtreeBytestreamInputBase<?>,
                        ZT extends Number,
                        ZXS extends WkSerdeDtreeOperationSettings,
                        ZXO extends WkSerdeDtreeNumberReader<ZT,ZXS,?,?,ZXD>,
                        ZXD extends WkSerdeDtreeNumberDefinition<ZT>,
                        VXS extends WkSerdeDtreeOperationSettingsVariableLength,
                        VXO extends WkSerdeDtreeVariableSizeSequenceReader<T,VXS,?,?,VXD>,
                        VXD extends WkSerdeDtreeVariableSizeSequenceDefinition<T>,
                        DC extends WkSerdeDtreeDynamicSequenceDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XO,XD,AXB,
                                        ?,?,?,?,?,?,?,?,
                                        ZT,ZXS,ZXO,ZXD,?,?,?,?,
                                        VXS,VXO,VXD,?,?,?,?,
                                        ?,DC>>
    extends WkSerdeDtreeAggregatorReaderCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXB, DC>
    implements WkSerdeDtreeDynamicSequenceReader<T, XS, XQ, XR, XD, ZT, ZXO, ZXD, VXO, VXD>
{

  private WkSrlzInputPacketSubfieldFrameNodeCore<ZT,ZXS,ZXD,ZXO,T,XBC,XD,XO>
                        sizeReadFieldHandler;
  private WkSrlzInputPacketSubfieldFrameNodeCore<T,VXS,VXD,VXO,T,XBC,XD,XO>
                        varseqReadFieldHandler;

  protected WkSerdeDtreeDynamicSequenceReaderCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.sizeReadFieldHandler = getSubfieldpacketFor(definitionCore().sizeComponent);
    this.varseqReadFieldHandler = getSubfieldpacketFor(definitionCore().varseqComponent);
  }

  @Override
  protected void onAggregatorReadingInitialization() {
  }
  
  @Override
  public Optional<WkSerdeDtreeNodeDataInputComponent<ZT, ZXD, ZXO>> size() {
    return this.sizeReadFieldHandler.field();
  }
  
  @Override
  public Optional<WkSerdeDtreeNodeDataInputComponent<T, VXD, VXO>> variableSequence() {
    return this.varseqReadFieldHandler.field();
  }

}
