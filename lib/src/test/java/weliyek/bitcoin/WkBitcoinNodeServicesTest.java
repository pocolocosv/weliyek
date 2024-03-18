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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;
import weliyek.serialization.WkSzCountingInputBytestream;
import weliyek.serialization.WkSzCountingOutputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.util.KetzaByteOutputStream;

class WkBitcoinNodeServicesTest
{
  

  public static WkSrlzStruct<
                        WkBitcoinNodeServices,
                        WkSettingsSrlzPacketOperationData,
                        WkBitcoinNodeServicesSrlzStructNode,
                        WkBitcoinNodeServicesSrlzInputNode,
                        WkSzInputBytestreamBase<?>,
                        WkSettingsSrlzPacketOperationData,
                        WkBitcoinNodeServicesSrlzStructNode,
                        WkBitcoinNodeServicesSrlzOutputNode,
                        WkSzOutputBytestreamBase<?>,
                        WkBitcoinNodeServicesSrlzStructNode>
  newStruct(String label) {
    return new WkSrlzStructComponentFrameNodeRootCore<>(
                      label,
                      WkBitcoinNodeServicesSrlzStructNode::newCore,
                      WkSzCountingInputBytestream::new,
                      WkSzCountingOutputBytestream::new);
  }

  @Test
  void testCtor() {
    WkBitcoinNodeServices empty = new WkBitcoinNodeServices();
    assertTrue(empty.isEmpty());

    WkBitcoinNodeServices nodeBloom = new WkBitcoinNodeServices(WkBitcoinServiceFlag.NODE_BLOOM);
    assertEquals(1, nodeBloom.size());
    assertTrue(nodeBloom.contains(WkBitcoinServiceFlag.NODE_BLOOM));

    WkBitcoinNodeServices twoFlags = new WkBitcoinNodeServices(WkBitcoinServiceFlag.NODE_BLOOM, WkBitcoinServiceFlag.NODE_NETWORK);
    assertEquals(2, twoFlags.size());
    assertTrue(twoFlags.contains(WkBitcoinServiceFlag.NODE_BLOOM));
    assertTrue(twoFlags.contains(WkBitcoinServiceFlag.NODE_NETWORK));
  }

  @Test
  void testToLong() {
    WkBitcoinNodeServices threeFlags = new WkBitcoinNodeServices(WkBitcoinServiceFlag.NODE_BLOOM, WkBitcoinServiceFlag.NODE_NETWORK, WkBitcoinServiceFlag.BIT09);
    assertEquals(3, threeFlags.size());
    
    assertEquals(
        WkBitcoinServiceFlag.NODE_BLOOM.bitmask 
        | WkBitcoinServiceFlag.NODE_NETWORK.bitmask 
        | WkBitcoinServiceFlag.BIT09.bitmask,
        threeFlags.toLong());
  }

  @Test
  void testFromLong() {
    WkBitcoinNodeServices threeFlags = WkBitcoinNodeServices.fromLong(
        WkBitcoinServiceFlag.NODE_BLOOM.bitmask 
        | WkBitcoinServiceFlag.NODE_NETWORK.bitmask 
        | WkBitcoinServiceFlag.BIT09.bitmask);
    assertEquals(3, threeFlags.size());
    assertTrue(threeFlags.contains(WkBitcoinServiceFlag.NODE_BLOOM));
    assertTrue(threeFlags.contains(WkBitcoinServiceFlag.NODE_NETWORK));
    assertTrue(threeFlags.contains(WkBitcoinServiceFlag.BIT09));
  }

  @Test
  void testToString() {
    WkBitcoinNodeServices twoFlags = new WkBitcoinNodeServices(WkBitcoinServiceFlag.NODE_BLOOM, WkBitcoinServiceFlag.NODE_NETWORK, WkBitcoinServiceFlag.BIT62);
    assertEquals("NODE_NETWORK|NODE_BLOOM|BIT62", twoFlags.toString());
  }

  @Test
  void testSerialization() {
    WkBitcoinNodeServices services = WkBitcoinNodeServices.fromLong(
        WkBitcoinServiceFlag.NODE_BLOOM.bitmask 
        | WkBitcoinServiceFlag.NODE_NETWORK.bitmask 
        | WkBitcoinServiceFlag.BIT09.bitmask);
    
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();
    WkSrlzStruct<WkBitcoinNodeServices, WkSettingsSrlzPacketOperationData, WkBitcoinNodeServicesSrlzStructNode, WkBitcoinNodeServicesSrlzInputNode, WkSzInputBytestreamBase<?>, WkSettingsSrlzPacketOperationData, WkBitcoinNodeServicesSrlzStructNode, WkBitcoinNodeServicesSrlzOutputNode, WkSzOutputBytestreamBase<?>, WkBitcoinNodeServicesSrlzStructNode> 
      servicesStruct = newStruct("SERVICES");
    
    WkSzOutputPacket<WkBitcoinNodeServices, WkBitcoinNodeServicesSrlzStructNode, WkBitcoinNodeServicesSrlzOutputNode> servicesWrite = servicesStruct.newOutputPacket(services, WkSettingsSrlzPacketOperationData.EMPTY, outputBuffer);
    
    servicesWrite.processBytestream();
    
    assertTrue(servicesWrite.isCompleted());
    
    assertEquals(8, outputBuffer.size());
    
    WkSzInputPacket<WkBitcoinNodeServices, WkBitcoinNodeServicesSrlzStructNode, WkBitcoinNodeServicesSrlzInputNode> servicesRead = servicesStruct.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputBuffer.inputStream());
    
    servicesRead.processBytestream();
    
    assertTrue(servicesRead.isCompleted());
    
    assertEquals(services, servicesRead.firstOperation().get().result().get().serializable().get());
  }

}
