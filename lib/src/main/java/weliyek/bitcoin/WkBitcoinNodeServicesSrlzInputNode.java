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

import weliyek.serialization.WkSerdeDtreeAggregatorReader;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;

public class WkBitcoinNodeServicesSrlzInputNode 
    implements WkSerdeDtreeAggregatorReader<
                        WkBitcoinNodeServices, 
                        WkSerdeDtreeOperationSettings, 
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>, 
                        WkSerdeDtreeOperationResult<WkBitcoinNodeServices>, 
                        WkBitcoinNodeServicesSrlzStructNode>
{

  final WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<
                        WkBitcoinNodeServices, 
                        WkSerdeDtreeOperationSettings, 
                        WkBitcoinNodeServicesSrlzStructNode, 
                        WkBitcoinNodeServicesSrlzInputNode> inputCore;
  final WkSrlzInputPacketSubfieldFrameNodeCore<
                        Long, 
                        WkSerdeDtreeOperationSettings, 
                        WkSerdeSignedLittleEndianLong, 
                        WkSerdeSignedLittleEndianLongReader, 
                        WkBitcoinNodeServices, 
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>, 
                        WkBitcoinNodeServicesSrlzStructNode, 
                        WkBitcoinNodeServicesSrlzInputNode> int64;

  WkBitcoinNodeServicesSrlzInputNode(
    int index, 
    WkSerdeDtreeOperationSettings settings, 
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream, 
    WkSerdeDtreeNodeDataInputComponentCore<WkBitcoinNodeServices, ?, WkBitcoinNodeServicesSrlzStructNode, ?, ?, ?> packetFieldCore, 
    WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<WkBitcoinNodeServices, WkSerdeDtreeOperationSettings, WkBitcoinNodeServicesSrlzStructNode, WkBitcoinNodeServicesSrlzInputNode, ?, ?, ?, ? extends WkBitcoinNodeServicesSrlzStructNode> structCore) {
    this.inputCore = new WkSimplifiedAggregatorSrlzInputPacketDecoderFrameNodeCore<
          WkBitcoinNodeServices, 
          WkSerdeDtreeOperationSettings, 
          WkBitcoinNodeServicesSrlzStructNode, 
          WkBitcoinNodeServicesSrlzInputNode>(
        index, settings, parentBytestream, packetFieldCore, structCore, this);
    this.int64 = this.inputCore.getSubfieldpacketFor(this.definition().int64);
  }
  
  public WkSrlzInputPacketSubfieldFrameNodeCore<
            Long, 
            WkSerdeDtreeOperationSettings, 
            WkSerdeSignedLittleEndianLong, 
            WkSerdeSignedLittleEndianLongReader, 
            WkBitcoinNodeServices, 
            WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>, 
            WkBitcoinNodeServicesSrlzStructNode, 
            WkBitcoinNodeServicesSrlzInputNode> 
  int64() {
    return this.int64;
  }

  @Override
  public WkBitcoinNodeServicesSrlzStructNode definition() {
    return this.inputCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.inputCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.inputCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkBitcoinNodeServices>> result() {
    return this.inputCore.result();
  }

  @Override
  public int index() {
    return this.inputCore.index();
  }

  @Override
  public
      WkSerdeDtreeNodeDataInputComponent<WkBitcoinNodeServices, WkBitcoinNodeServicesSrlzStructNode, ?>
      packetField() {
    return this.inputCore.packetField();
  }

  @Override
  public String name() {
    return this.inputCore.name();
  }

  @Override
  public List<WkSrlzInputPacketSubfieldFrameNode<?, ?, ?>> subfields() {
    return this.inputCore.subfields();
  }

}
