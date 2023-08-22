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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WkBitcoinNodeServicesTest
{

  @Test
  void testCtor() {
    WkBitcoinNodeServices empty = new WkBitcoinNodeServices();
    assertTrue(empty.isEmpty());

    WkBitcoinNodeServices nodeBloom = new WkBitcoinNodeServices(BitcoinServiceFlag.NODE_BLOOM);
    assertEquals(1, nodeBloom.size());
    assertTrue(nodeBloom.contains(BitcoinServiceFlag.NODE_BLOOM));

    WkBitcoinNodeServices twoFlags = new WkBitcoinNodeServices(BitcoinServiceFlag.NODE_BLOOM, BitcoinServiceFlag.NODE_NETWORK);
    assertEquals(2, twoFlags.size());
    assertTrue(twoFlags.contains(BitcoinServiceFlag.NODE_BLOOM));
    assertTrue(twoFlags.contains(BitcoinServiceFlag.NODE_NETWORK));
  }

  @Test
  void testToLong() {
    WkBitcoinNodeServices threeFlags = new WkBitcoinNodeServices(BitcoinServiceFlag.NODE_BLOOM, BitcoinServiceFlag.NODE_NETWORK, BitcoinServiceFlag.BIT09);
    assertEquals(3, threeFlags.size());
    
    assertEquals(
        BitcoinServiceFlag.NODE_BLOOM.bitmask 
        | BitcoinServiceFlag.NODE_NETWORK.bitmask 
        | BitcoinServiceFlag.BIT09.bitmask,
        threeFlags.toLong());
  }

  @Test
  void testFromLong() {
    WkBitcoinNodeServices threeFlags = WkBitcoinNodeServices.fromLong(
        BitcoinServiceFlag.NODE_BLOOM.bitmask 
        | BitcoinServiceFlag.NODE_NETWORK.bitmask 
        | BitcoinServiceFlag.BIT09.bitmask);
    assertEquals(3, threeFlags.size());
    assertTrue(threeFlags.contains(BitcoinServiceFlag.NODE_BLOOM));
    assertTrue(threeFlags.contains(BitcoinServiceFlag.NODE_NETWORK));
    assertTrue(threeFlags.contains(BitcoinServiceFlag.BIT09));
  }

  @Test
  void testToString() {
    WkBitcoinNodeServices twoFlags = new WkBitcoinNodeServices(BitcoinServiceFlag.NODE_BLOOM, BitcoinServiceFlag.NODE_NETWORK, BitcoinServiceFlag.BIT62);
    assertEquals("NODE_NETWORK|NODE_BLOOM|BIT62", twoFlags.toString());
  }

}
