/*
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
package weliyek.bitcoin;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorWriter;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponentCore;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzOutputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongWriter;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;

public class WkBitcoinNodeServicesSrlzOutputNode 
    implements WkSerdeDtreeAggregatorWriter<
                        WkBitcoinNodeServices, 
                        WkSerdeDtreeOperationSettings, 
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkBitcoinNodeServices>, 
                        WkBitcoinNodeServicesSrlzStructNode>
{

  final WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                        WkBitcoinNodeServices, 
                        WkSerdeDtreeOperationSettings, 
                        WkBitcoinNodeServicesSrlzStructNode, 
                        WkBitcoinNodeServicesSrlzOutputNode> outputCore;
  final WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Long, 
                        WkSerdeDtreeOperationSettings, 
                        WkSerdeSignedLittleEndianLong, 
                        WkSerdeSignedLittleEndianLongWriter, 
                        WkBitcoinNodeServices, 
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>, 
                        WkBitcoinNodeServicesSrlzStructNode, 
                        WkBitcoinNodeServicesSrlzOutputNode> int64;

  WkBitcoinNodeServicesSrlzOutputNode(
    int index, 
    WkBitcoinNodeServices serializable, 
    WkSerdeDtreeOperationSettings settings, 
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream, 
    WkSerdeDtreeNodeDataOutputComponentCore<WkBitcoinNodeServices, ?, WkBitcoinNodeServicesSrlzStructNode, ?, ?, ?> packetFieldCore, 
    WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<WkBitcoinNodeServices, ?, ?, ?, WkSerdeDtreeOperationSettings, WkBitcoinNodeServicesSrlzStructNode, WkBitcoinNodeServicesSrlzOutputNode, ?> structCore) {
    this.outputCore = new WkSimplifiedAggregatorSrlzOutputPacketEncoderFrameNodeCore<
                            WkBitcoinNodeServices, 
                            WkSerdeDtreeOperationSettings, 
                            WkBitcoinNodeServicesSrlzStructNode, 
                            WkBitcoinNodeServicesSrlzOutputNode>(
                                index, serializable, settings, parentBytestream, packetFieldCore, structCore, this);
    this.int64 = this.outputCore.getSubfieldpacketFor(this.definition().int64);
  }
  
  public WkSrlzOutputPacketSubfieldFrameNodeCore<
                        Long, 
                        WkSerdeDtreeOperationSettings, 
                        WkSerdeSignedLittleEndianLong, 
                        WkSerdeSignedLittleEndianLongWriter, 
                        WkBitcoinNodeServices, 
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>, 
                        WkBitcoinNodeServicesSrlzStructNode, 
                        WkBitcoinNodeServicesSrlzOutputNode> 
  int64() {
    return this.int64;
  }

  @Override
  public WkBitcoinNodeServicesSrlzStructNode definition() {
    return this.outputCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.outputCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.outputCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkBitcoinNodeServices>> result() {
    return this.outputCore.result();
  }

  @Override
  public int index() {
    return this.outputCore.index();
  }

  @Override
  public
      WkSerdeDtreeNodeDataOutputComponent<WkBitcoinNodeServices, WkBitcoinNodeServicesSrlzStructNode, ?>
      packetField() {
    return this.outputCore.packetField();
  }

  @Override
  public String name() {
    return this.outputCore.name();
  }

  @Override
  public WkBitcoinNodeServices serializable() {
    return this.outputCore.serializable();
  }

  @Override
  public List<WkSrlzOutputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.outputCore.subfields();
  }

}
