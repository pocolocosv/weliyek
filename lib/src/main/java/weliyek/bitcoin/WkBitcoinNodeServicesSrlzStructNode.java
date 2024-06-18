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

import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSrlzStructSubcomponentFrameNodeCore;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongWriter;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;

public class WkBitcoinNodeServicesSrlzStructNode 
    implements WkSerdeDtreeAggregatorStructDefinition<WkBitcoinNodeServices>
{

  public static WkSerdeDtreeStructDefinitionCore<
                        WkBitcoinNodeServices,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkBitcoinNodeServicesSrlzStructNode,
                        WkBitcoinNodeServicesSrlzInputNode,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,?,?,
                        WkBitcoinNodeServicesSrlzStructNode,
                        WkBitcoinNodeServicesSrlzOutputNode,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBitcoinNodeServicesSrlzStructNode,?>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkBitcoinNodeServicesSrlzStructNode(componentCore).definitionCore;
  }
  
  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                    WkBitcoinNodeServices, 
                    WkSerdeDtreeOperationSettings, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzInputNode, 
                    WkSerdeDtreeOperationSettings, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzOutputNode, 
                    WkBitcoinNodeServicesSrlzStructNode> definitionCore;
  
  final WkSrlzStructSubcomponentFrameNodeCore<
                    Long, 
                    WkSerdeDtreeOperationSettings, 
                    WkSerdeSignedLittleEndianLong, 
                    WkSerdeSignedLittleEndianLongReader, 
                    WkBitcoinNodeServices, 
                    WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzInputNode, 
                    WkSerdeDtreeOperationSettings, 
                    WkSerdeSignedLittleEndianLong, 
                    WkSerdeSignedLittleEndianLongWriter, 
                    WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>, 
                    WkBitcoinNodeServicesSrlzStructNode, 
                    WkBitcoinNodeServicesSrlzOutputNode, 
                    WkSerdeSignedLittleEndianLong, 
                    WkBitcoinNodeServicesSrlzStructNode> int64;

  public WkBitcoinNodeServicesSrlzStructNode(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
        WkBitcoinNodeServices, 
        WkSerdeDtreeOperationSettings, 
        WkBitcoinNodeServicesSrlzStructNode, 
        WkBitcoinNodeServicesSrlzInputNode, 
        WkSerdeDtreeOperationSettings, 
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
    this.int64 = this.definitionCore.<Long, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter, WkSerdeSignedLittleEndianLong>
    addSubcomponent(
        "LONG", 
        Optional.empty(), // rxEnablingTest 
        WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
        WkSerdeDtreeOperationSettings::none,
        Optional.empty(), // txEnablingTest 
        WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
        WkSerdeDtreeOperationSettings::none,
        (k,ao,i) -> Long.valueOf(ao.serializable().toLong()), //disaggregator, 
        false, 
        WkSerdeSignedLittleEndianLong::newCore);
  }

  @Override
  public Class<WkBitcoinNodeServices> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
