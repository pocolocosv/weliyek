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

import weliyek.serialization.WkSerdeDtreeAggregatorReaderCore;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataReader;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;

public abstract class WkSerdeElementCollectionReaderCore<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XB extends WkSerdeDtreeBytestreamInput,
                        XBC extends WkSerdeDtreeBytestreamInputBase<? extends XB>,
                        XQ extends WkSerdeDtreeOperationInputRuntimeSequenceCommon<XB>,
                        XQC extends WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<XB,XBC,XQ>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeElementCollectionDefinition<T,XO,?,ET,?>,
                        XO extends WkSerdeElementCollectionReader<T,XS,XQ,XR,XD,ET,EXD,EXO>,
                        XOC extends WkSerdeElementCollectionReaderCore<
                                        T,XS,XB,XBC,XQ,XQC,XR,XD,XO,?,AXBC,ET,EXS,EXD,EXO,DC>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        ET,
                        EXS extends WkSerdeDtreeOperationSettings,
                        EXD extends WkSerdeDtreeNodeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeNodeDataReader<ET,EXS,?,?,EXD>,
                        DC extends WkSerdeElementCollectionDefinitionCore<
                                        T,XS,XB,XBC,XQC,XR,XD,XO,AXBC,
                                        ?,?,?,?,?,?,?,?,
                                        ET,EXS,EXD,EXO,
                                        ?,?,?,?,?,DC>>
        extends WkSerdeDtreeAggregatorReaderCore<T, XS, XB, XBC, XQ, XQC, XR, XD, XO, XOC, AXBC, DC>
        implements WkSerdeElementCollectionReader<T, XS, XQ, XR, XD, ET, EXD, EXO>
{

  private WkSrlzInputPacketSubfieldFrameNodeCore<ET,EXS,EXD,EXO,T,XBC,XD,XO> elementPacketSubfield;

  protected WkSerdeElementCollectionReaderCore(
    int index,
    XS settings,
    AXBC parentBytestream,
    WkSerdeDtreeNodeDataInputComponentCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.elementPacketSubfield = getSubfieldpacketFor(definitionCore().elementComponent);
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
  public final WkSrlzInputPacketSubfieldFrameNode<ET, EXD, EXO> elements() {
    return this.elementPacketSubfield.asSubfield();
  }

}
