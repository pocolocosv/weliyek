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

import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

class WkBtcNetNodeServicesTest
{
  

  public static WkSerdeDtreeStruct<
                        WkBtcNetNodeServices,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetNodeServicesSerdeDef,
                        WkBtcNetNodeServicesSerdeReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetNodeServicesSerdeDef,
                        WkBtcNetNodeServicesSerdeWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcNetNodeServicesSerdeDef>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkBtcNetNodeServicesSerdeDef::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  @Test
  void testCtor() {
    WkBtcNetNodeServices empty = new WkBtcNetNodeServices();
    assertTrue(empty.isEmpty());

    WkBtcNetNodeServices nodeBloom = new WkBtcNetNodeServices(WkBtcNetNodeServicesBit.NODE_BLOOM);
    assertEquals(1, nodeBloom.size());
    assertTrue(nodeBloom.contains(WkBtcNetNodeServicesBit.NODE_BLOOM));

    WkBtcNetNodeServices twoFlags = new WkBtcNetNodeServices(WkBtcNetNodeServicesBit.NODE_BLOOM, WkBtcNetNodeServicesBit.NODE_NETWORK);
    assertEquals(2, twoFlags.size());
    assertTrue(twoFlags.contains(WkBtcNetNodeServicesBit.NODE_BLOOM));
    assertTrue(twoFlags.contains(WkBtcNetNodeServicesBit.NODE_NETWORK));
  }

  @Test
  void testToLong() {
    WkBtcNetNodeServices threeFlags = new WkBtcNetNodeServices(WkBtcNetNodeServicesBit.NODE_BLOOM, WkBtcNetNodeServicesBit.NODE_NETWORK, WkBtcNetNodeServicesBit.BIT09);
    assertEquals(3, threeFlags.size());
    
    assertEquals(
        WkBtcNetNodeServicesBit.NODE_BLOOM.bitmask 
        | WkBtcNetNodeServicesBit.NODE_NETWORK.bitmask 
        | WkBtcNetNodeServicesBit.BIT09.bitmask,
        threeFlags.toLong());
  }

  @Test
  void testFromLong() {
    WkBtcNetNodeServices threeFlags = WkBtcNetNodeServices.fromLong(
        WkBtcNetNodeServicesBit.NODE_BLOOM.bitmask 
        | WkBtcNetNodeServicesBit.NODE_NETWORK.bitmask 
        | WkBtcNetNodeServicesBit.BIT09.bitmask);
    assertEquals(3, threeFlags.size());
    assertTrue(threeFlags.contains(WkBtcNetNodeServicesBit.NODE_BLOOM));
    assertTrue(threeFlags.contains(WkBtcNetNodeServicesBit.NODE_NETWORK));
    assertTrue(threeFlags.contains(WkBtcNetNodeServicesBit.BIT09));
  }

  @Test
  void testToString() {
    WkBtcNetNodeServices twoFlags = new WkBtcNetNodeServices(WkBtcNetNodeServicesBit.NODE_BLOOM, WkBtcNetNodeServicesBit.NODE_NETWORK, WkBtcNetNodeServicesBit.BIT62);
    assertEquals("NODE_NETWORK|NODE_BLOOM|BIT62", twoFlags.toString());
  }

  @Test
  void testSerialization() {
    WkBtcNetNodeServices services = WkBtcNetNodeServices.fromLong(
        WkBtcNetNodeServicesBit.NODE_BLOOM.bitmask 
        | WkBtcNetNodeServicesBit.NODE_NETWORK.bitmask 
        | WkBtcNetNodeServicesBit.BIT09.bitmask);
    
    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();
    WkSerdeDtreeStruct<WkBtcNetNodeServices, WkSerdeDtreeOperationSettings, WkBtcNetNodeServicesSerdeDef, WkBtcNetNodeServicesSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcNetNodeServicesSerdeDef, WkBtcNetNodeServicesSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcNetNodeServicesSerdeDef> 
      servicesStruct = newStruct("SERVICES");
    
    WkSerdeDtreeWriter<WkBtcNetNodeServices, WkBtcNetNodeServicesSerdeDef, WkBtcNetNodeServicesSerdeWriter> servicesWrite = servicesStruct.newOutputPacket(services, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);
    
    servicesWrite.processBytestream();
    
    assertTrue(servicesWrite.isCompleted());
    
    assertEquals(8, outputBuffer.size());
    
    WkSerdeDtreeReader<WkBtcNetNodeServices, WkBtcNetNodeServicesSerdeDef, WkBtcNetNodeServicesSerdeReader> servicesRead = servicesStruct.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());
    
    servicesRead.processBytestream();
    
    assertTrue(servicesRead.isCompleted());
    
    assertEquals(services, servicesRead.firstOperation().get().result().get().serializable().get());
  }

}
