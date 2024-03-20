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

public abstract class WkSrlzOutputPacketFieldFrameNodeCore<
                        T,
                        YS extends WkSettingsSrlzPacketOperationData,
                        YD extends WkSerdeDTreeNodeStructDefinition<T>,
                        YO extends WkSerdeDTreeNodeDataWriter<T,YS,?,?,YD>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        AYO extends WkAggregatorSrlzOutputPacketEncoderFrameNode<?,?,? extends WkEncodingRuntimeSrlzPacketOperationData<?>,?,?>>
    extends WkSrlzPacketFieldFrameNodeCore<
                        T, YS, YD,
                        WkSrlzStructComponentFrameNodeCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD>,
                        YO,
                        WkSrlzOutputPacketEncoderFrameNodeCore<?,?,?,?,?,YO,?,YD,?,?>,
                        WkSrlzOutputPacketFieldFrameNode<T,YD,YO>,
                        AYBC,
                        WkAggregatorSrlzOutputPacketEncoderFrameNodeCore<?,?,?,AYBC,?,?,?,?,AYO,?,?,?>>
    implements WkSrlzOutputPacketFieldFrameNode<T,YD,YO>
{

  public WkSrlzOutputPacketFieldFrameNodeCore(
    int initialOperationListCapacity,
    WkSrlzStructComponentFrameNodeCore<T,?,?,?,?,YS,YD,YO,AYBC,? extends YD> protocolFieldCore) {
    super(
        initialOperationListCapacity,
        protocolFieldCore,
        WkSerdeDTreeNodeDataWriter::serializable);
  }

  @Override
  protected WkSrlzOutputPacketEncoderFrameNodeCore<?,?,?,?,?,YO,?,YD,?,?>
  newOperation(int index) {
    YS settings = newSettings(index);
    T serializable = serializable(index);
    return protocolFieldCore().definitionCore().newWritingOperationCore(
        index, serializable, settings, parentBytestream(), this);
  }

  @Override
  protected abstract YS newSettings(int index);

  @Override
  protected abstract AYBC parentBytestream();

  protected abstract T serializable(int index);

  @Override
  protected void onDoneProcessing() {
    // Nothing to do
  }

  @Override
  public WkSerdeDTreeNodeStructComponent<? extends YD> structComponent() {
    return protocolFieldCore().asProtocolField();
  }

}
