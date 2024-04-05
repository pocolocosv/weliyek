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

public abstract class WkSerdeDtreeAggregatorWriterCore<
                        T,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkEncodingRuntimeSrlzPacketOperationData<YB>,
                        YQC extends WkEncodingRuntimeSrlzPacketOperationCtrl<YB,YBC,YQ>,
                        YR extends WkResultSrlzPacketOperationData<T>,
                        YD extends WkSerdeDtreeAggregatorDefinition<T>,
                        YO extends WkSerdeDtreeAggregatorWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSerdeDtreeAggregatorWriterCore<T,YS,YB,YBC,YQ,YQC,YR,YD,YO,?,AYB,DC>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkSerdeDtreeAggregatorDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,YO,AYB,?,DC>>
        extends WkSerdeDtreeNodeDataWriterCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
        implements WkSerdeDtreeAggregatorWriter<T, YS, YQ, YR, YD>
{

  private WkSrlzOutputPacketSubfieldList<T,YBC,YD,YO> writingSubfields;

  protected WkSerdeDtreeAggregatorWriterCore(
    int index,
    T serializable,
    YS settings,
    AYB parentBytestream,
    WkSrlzOutputPacketFieldFrameNodeCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.writingSubfields = definitionCore().subcomponentHandlers.newSerializingHandlers(this);
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields() {
    return this.writingSubfields.asSubfieldList();
  }

  @Override
  protected void onStart() {
    onAggregatorInitialization();
  }

  protected abstract void onAggregatorInitialization();

  @Override
  protected Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> onProcessingBytestream() {
    Optional<WkSerdeDtreeNodeDataOperation<?,?,?,?,?>> latestSubfield = this.writingSubfields.processBytestream();
    if (this.writingSubfields.isCompleted()) {
      this.completeOperation();
    }
    return latestSubfield;
  }

  @SuppressWarnings("unchecked")
  public <SY,
             SYS extends WkSettingsSrlzPacketOperationData,
             SYD extends WkSerdeDtreeNodeStructDefinition<SY>,
             SYO extends WkSerdeDtreeNodeDataWriter<SY,SYS,?,?,SYD>>
  WkSrlzOutputPacketSubfieldFrameNodeCore<SY,SYS,SYD,SYO,T,YBC,YD,YO> getSubfieldpacketFor(
    WkSrlzStructSubcomponentFrameNodeCore<SY,?,?,?,T,?,?,?,SYS,SYD,SYO,?,YD,YO,?,?>
    protocolSubfieldCore) {
    return this.writingSubfields.findSubfieldpacket(
        (WkSrlzStructSubcomponentFrameNodeCore<SY,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>) protocolSubfieldCore);
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
