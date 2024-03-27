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
package weliyek.serialization;

import java.util.List;
import java.util.Optional;

public abstract class WkAggregatorSrlzInputPacketDecoderFrameNodeCore<
                        T,
                        XS extends WkSettingsSrlzPacketOperationData,
                        XB extends WkSzInputBytestream,
                        XBC extends WkSzInputBytestreamBase<? extends XB>,
                        XQ extends WkDecodingRuntimeSrlzPacketOperationData<XB>,
                        XQC extends WkDecodingRuntimeSrlzPacketOperationCtrl<XB,XBC,XQ>,
                        XR extends WkResultSrlzPacketOperationData<T>,
                        XD extends WkSerdeDtreeAggregatorDefinition<T>,
                        XO extends WkSerdeDtreeAggregatorReader<T,XS,XQ,XR,XD>,
                        XOC extends WkAggregatorSrlzInputPacketDecoderFrameNodeCore<T,XS,XB,XBC,XQ,XQC,XR,XD,XO,?,AXB,DC>,
                        AXB extends WkSzInputBytestreamBase<?>,
                        DC extends WkSerdeDtreeAggregatorDefinitionCore<T,XS,XB,XBC,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,?,?,?,DC>>
    extends WkSerdeDtreeNodeDataReaderCore<T, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
    implements WkSerdeDtreeAggregatorReader<T, XS, XQ, XR, XD>
{

  protected WkSrlzInputPacketSubfieldList<T,XBC,XD,XO> readinHandlerList;

  protected WkAggregatorSrlzInputPacketDecoderFrameNodeCore(
    int index,
    XS settings,
    AXB parentBytestream,
    WkSrlzInputPacketFieldFrameNodeCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.readinHandlerList = definitionCore().subcomponentHandlers.newDeserializingHandlers(this);
  }

  @SuppressWarnings("unchecked")
  public <ST,
          SXS extends WkSettingsSrlzPacketOperationData,
          SXD extends WkSerdeDtreeNodeStructDefinition<ST>,
          SXO extends WkSerdeDtreeNodeDataReader<ST,SXS,?,?,SXD>>
  WkSrlzInputPacketSubfieldFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO> getSubfieldpacketFor(
    WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,?,XD,XO,?,?,?,?,?,?,?,?> protocolSubfieldCore) {
    return this.readinHandlerList.findSubfieldpacket(
        (WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD>) protocolSubfieldCore);
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.readinHandlerList.asSubfieldList();
  }

  @Override
  protected void onStartReading() {
    onAggregatorReadingInitialization();
  }

  protected abstract void onAggregatorReadingInitialization();

  @Override
  protected Optional<WkSerdeDtreeNodeDataOperation<?, ?, ?, ?, ?>> onProcessingBytestream() {
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> completedSubfield = this.readinHandlerList.processBytestream();
    if (this.readinHandlerList.isCompleted()) {
        completeOperation();
    }
    return completedSubfield;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
