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

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

class WkBitcoinCommandTest
{

  @Test
  void testTooShortByteStream() {
    byte[] bytesarray = new byte[] {'t','o','o','s','h','o','r','t',0};
    assertThrows(IllegalArgumentException.class, () -> WkBtcNetMessageType.newCommand(bytesarray));
  }

  @Test
  void testTooLongByteStream() {
    byte[] bytesarray = new byte[] {'t','o','o','l','o','n','g',0,0,0,0,0,0};
    assertThrows(IllegalArgumentException.class, () -> WkBtcNetMessageType.newCommand(bytesarray));
  }

  @Test
  void testUnknownButValidLength() {
    byte[] bytesarray = new byte[] {'u','n','k','n','o','w','n',0,0,0,0,0};
    WkBtcNetMessageType unknown = WkBtcNetMessageType.newCommand(bytesarray);
    assertNotNull(unknown);
    unknown.bytes.equalsToArray(bytesarray);
    assertFalse(unknown.isKnown());
    assertFalse(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testVersion() {
    byte[] bytesarray = new byte[] {'v','e','r','s','i','o','n',0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.VERSION.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.VERSION, WkBtcNetMessageType.newCommand(bytesarray));
    assertEquals(0, WkBtcNetMessageType.VERSION.bytes.compare(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testVerack() {
    byte[] bytesarray = new byte[] {'v','e','r','a','c','k',0,0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.VERACK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.VERACK, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testAddr() {
    byte[] bytesarray = new byte[] {'a','d','d','r',0,0,0,0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.ADDR.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.ADDR, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testInv() {
    byte[] bytesarray = new byte[] {'i','n','v',0,0,0,0,0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.INV.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.INV, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetdata() {
    byte[] bytesarray = new byte[] {'g','e','t','d','a','t','a',0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.GETDATA.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.GETDATA, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testNotfound() {
    byte[] bytesarray = new byte[] {'n','o','t','f','o','u','n','d',0,0,0,0};
    assertTrue(WkBtcNetMessageType.NOTFOUND.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.NOTFOUND, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetblocks() {
    byte[] bytesarray = new byte[] {'g','e','t','b','l','o','c','k','s',0,0,0};
    assertTrue(WkBtcNetMessageType.GETBLOCKS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.GETBLOCKS, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetheaders() {
    byte[] bytesarray = new byte[] {'g','e','t','h','e','a','d','e','r','s',0,0};
    assertTrue(WkBtcNetMessageType.GETHEADERS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.GETHEADERS, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testTx() {
    byte[] bytesarray = new byte[] {'t','x',0,0,0,0,0,0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.TX.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.TX, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testBlock() {
    byte[] bytesarray = new byte[] {'b','l','o','c','k',0,0,0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.BLOCK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.BLOCK, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testHeaders() {
    byte[] bytesarray = new byte[] {'h','e','a','d','e','r','s',0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.HEADERS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.HEADERS, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetaddr() {
    byte[] bytesarray = new byte[] {'g','e','t','a','d','d','r',0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.GETADDR.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.GETADDR, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testMempool() {
    byte[] bytesarray = new byte[] {'m','e','m','p','o','o','l',0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.MEMPOOL.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.MEMPOOL, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testPing() {
    byte[] bytesarray = new byte[] {'p','i','n','g',0,0,0,0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.PING.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.PING, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testPong() {
    byte[] bytesarray = new byte[] {'p','o','n','g',0,0,0,0,0,0,0,0};
    assertTrue(WkBtcNetMessageType.PONG.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.PONG, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFilterload() {
    byte[] bytesarray = new byte[] {'f','i','l','t','e','r','l','o','a','d',0,0};
    assertTrue(WkBtcNetMessageType.FILTERLOAD.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.FILTERLOAD, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFilteradd() {
    byte[] bytesarray = new byte[] {'f','i','l','t','e','r','a','d','d',0,0,0};
    assertTrue(WkBtcNetMessageType.FILTERADD.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.FILTERADD, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFilterclear() {
    byte[] bytesarray = new byte[] {'f','i','l','t','e','r','c','l','e','a','r',0};
    assertTrue(WkBtcNetMessageType.FILTERCLEAR.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.FILTERCLEAR, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testMerkleblock() {
    byte[] bytesarray = new byte[] {'m','e','r','k','l','e','b','l','o','c','k',0};
    assertTrue(WkBtcNetMessageType.MERKLEBLOCK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.MERKLEBLOCK, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testSendheaders() {
    byte[] bytesarray = new byte[] {'s','e','n','d','h','e','a','d','e','r','s',0};
    assertTrue(WkBtcNetMessageType.SENDHEADERS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.SENDHEADERS, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFeefilter() {
    byte[] bytesarray = new byte[] {'f','e','e','f','i','l','t','e','r',0,0,0};
    assertTrue(WkBtcNetMessageType.FEEFILTER.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.FEEFILTER, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testSendcmpct() {
    byte[] bytesarray = new byte[] {'s','e','n','d','c','m','p','c','t',0,0,0};
    assertTrue(WkBtcNetMessageType.SENDCMPCT.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.SENDCMPCT, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testCmpctblock() {
    byte[] bytesarray = new byte[] {'c','m','p','c','t','b','l','o','c','k',0,0};
    assertTrue(WkBtcNetMessageType.CMPCTBLOCK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.CMPCTBLOCK, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetblocktxn() {
    byte[] bytesarray = new byte[] {'g','e','t','b','l','o','c','k','t','x','n',0};
    assertTrue(WkBtcNetMessageType.GETBLOCKTXN.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.GETBLOCKTXN, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testBlocktxn() {
    byte[] bytesarray = new byte[] {'b','l','o','c','k','t','x','n',0,0,0,0};
    assertTrue(WkBtcNetMessageType.BLOCKTXN.bytes.equalsToArray(bytesarray));
    assertEquals(WkBtcNetMessageType.BLOCKTXN, WkBtcNetMessageType.newCommand(bytesarray));
    assertTrue(WkBtcNetMessageType.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testSerde() {
    WkSerdeDtreeStruct<WkBtcNetMessageType, WkSerdeDtreeOperationSettings, WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcNetMessageTypeSerdeField>
      btcCmd = WkBtcNetMessageTypeSerdeField.newStruct("BTC_CMD");

    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeWriter<WkBtcNetMessageType, WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldWriter>
      cmdWriter = btcCmd.newOutputPacket(WkBtcNetMessageType.VERSION, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    cmdWriter.processBytestream();

    assertTrue(cmdWriter.isCompleted());

    WkSerdeDtreeReader<WkBtcNetMessageType, WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldReader>
      cmdReader = btcCmd.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    cmdReader.processBytestream();

    assertTrue(cmdReader.isCompleted());
    assertTrue(cmdReader.firstOperation().isPresent());
    assertTrue(cmdReader.firstOperation().get().result().isPresent());
    assertTrue(cmdReader.firstOperation().get().result().get().serializable().isPresent());

    assertEquals(
        WkBtcNetMessageType.VERSION,
        cmdReader.firstOperation().get().
                  result().get().
                  serializable().get());
  }

}
