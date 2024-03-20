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

import weliyek.serialization.WkAggregatorSrlzStructDefinitionFrameNode;
import weliyek.serialization.WkAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSrlzStructComponentFrameNodeCore;
import weliyek.serialization.WkSrlzStructDefinitionFrameNodeCore;
import weliyek.serialization.WkSerdeDTreeNodeStructComponentHandler;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.number.WkSignedLittleEndianLongSrlzInputNode;
import weliyek.serialization.number.WkSignedLittleEndianLongSrlzOutputNode;
import weliyek.serialization.number.WkSignedLittleEndianLongSrlzStructNode;

public class WkBitcoinNodeServicesSrlzStructNode 
    implements WkAggregatorSrlzStructDefinitionFrameNode<WkBitcoinNodeServices>
{

  public static WkSrlzStructDefinitionFrameNodeCore<
                        WkBitcoinNodeServices,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkBitcoinNodeServicesSrlzStructNode,
                        WkBitcoinNodeServicesSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,?,?,
                        WkBitcoinNodeServicesSrlzStructNode,
                        WkBitcoinNodeServicesSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkBitcoinNodeServicesSrlzStructNode,?>
  newCore(WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkBitcoinNodeServicesSrlzStructNode(componentCore).definitionCore;
  }
  
  private final WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<
                    WkBitcoinNodeServices, 
                    WkSettingsSrlzPacketOperationData, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzInputNode, 
                    WkSettingsSrlzPacketOperationData, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzOutputNode, 
                    WkBitcoinNodeServicesSrlzStructNode> definitionCore;
  
  final WkSrlzStructSubcomponentFrameNodeCore<
                    Long, 
                    WkSettingsSrlzPacketOperationData, 
                    WkSignedLittleEndianLongSrlzStructNode, 
                    WkSignedLittleEndianLongSrlzInputNode, 
                    WkBitcoinNodeServices, 
                    WkSzInputBytestreamBase<? extends WkSzInputBytestream>, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzInputNode, 
                    WkSettingsSrlzPacketOperationData, 
                    WkSignedLittleEndianLongSrlzStructNode, 
                    WkSignedLittleEndianLongSrlzOutputNode, 
                    WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzOutputNode, 
                    WkSignedLittleEndianLongSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzStructNode> int64;

  public WkBitcoinNodeServicesSrlzStructNode(
    WkSrlzStructComponentFrameNodeCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSimplifiedAggregatorSrlzStructDefinitionFrameNodeCore<
        WkBitcoinNodeServices, 
        WkSettingsSrlzPacketOperationData, 
        WkBitcoinNodeServicesSrlzStructNode, 
        WkBitcoinNodeServicesSrlzInputNode, 
        WkSettingsSrlzPacketOperationData, 
        WkBitcoinNodeServicesSrlzStructNode, 
        WkBitcoinNodeServicesSrlzOutputNode, 
        WkBitcoinNodeServicesSrlzStructNode>(
            (i,xs,axb,xpc,dc) -> new WkBitcoinNodeServicesSrlzInputNode(i,xs,axb,xpc,dc).inputCore,
            (i,y,ys,ayb,ypc,dc) -> new WkBitcoinNodeServicesSrlzOutputNode(i,y,ys,ayb,ypc,dc).outputCore,
            componentCore, 
            (ic) -> {},
            (ic) -> WkBitcoinNodeServices.fromLong(ic.body().int64().field().get().firstOperation().get().result().get().serializable().get().longValue()), 
            (ic) -> {},
            (oc) -> {},
            this,
            WkBitcoinNodeServices.class);
    this.int64 = this.definitionCore.<Long, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianLongSrlzStructNode, WkSignedLittleEndianLongSrlzInputNode, WkSettingsSrlzPacketOperationData, WkSignedLittleEndianLongSrlzStructNode, WkSignedLittleEndianLongSrlzOutputNode, WkSignedLittleEndianLongSrlzStructNode>
    addSubcomponent(
        "LONG", 
        Optional.empty(), // rxEnablingTest 
        WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
        WkSettingsSrlzPacketOperationData::none,
        Optional.empty(), // txEnablingTest 
        WkAggregatorSrlzStructDefinitionFrameNodeCore.singleOperation(),
        WkSettingsSrlzPacketOperationData::none,
        (k,ao,i) -> Long.valueOf(ao.serializable().toLong()), //disaggregator, 
        false, 
        WkSignedLittleEndianLongSrlzStructNode::newCore);
  }

  @Override
  public Class<WkBitcoinNodeServices> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDTreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
