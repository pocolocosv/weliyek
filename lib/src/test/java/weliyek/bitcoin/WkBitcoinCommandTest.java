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
    assertThrows(IllegalArgumentException.class, () -> WkBitcoinCommand.newCommand(bytesarray));
  }

  @Test
  void testTooLongByteStream() {
    byte[] bytesarray = new byte[] {'t','o','o','l','o','n','g',0,0,0,0,0,0};
    assertThrows(IllegalArgumentException.class, () -> WkBitcoinCommand.newCommand(bytesarray));
  }

  @Test
  void testUnknownButValidLength() {
    byte[] bytesarray = new byte[] {'u','n','k','n','o','w','n',0,0,0,0,0};
    WkBitcoinCommand unknown = WkBitcoinCommand.newCommand(bytesarray);
    assertNotNull(unknown);
    unknown.bytes.equalsToArray(bytesarray);
    assertFalse(unknown.isKnown());
    assertFalse(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testVersion() {
    byte[] bytesarray = new byte[] {'v','e','r','s','i','o','n',0,0,0,0,0};
    assertTrue(WkBitcoinCommand.VERSION.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.VERSION, WkBitcoinCommand.newCommand(bytesarray));
    assertEquals(0, WkBitcoinCommand.VERSION.bytes.compare(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testVerack() {
    byte[] bytesarray = new byte[] {'v','e','r','a','c','k',0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.VERACK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.VERACK, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testAddr() {
    byte[] bytesarray = new byte[] {'a','d','d','r',0,0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.ADDR.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.ADDR, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testInv() {
    byte[] bytesarray = new byte[] {'i','n','v',0,0,0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.INV.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.INV, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetdata() {
    byte[] bytesarray = new byte[] {'g','e','t','d','a','t','a',0,0,0,0,0};
    assertTrue(WkBitcoinCommand.GETDATA.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.GETDATA, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testNotfound() {
    byte[] bytesarray = new byte[] {'n','o','t','f','o','u','n','d',0,0,0,0};
    assertTrue(WkBitcoinCommand.NOTFOUND.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.NOTFOUND, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetblocks() {
    byte[] bytesarray = new byte[] {'g','e','t','b','l','o','c','k','s',0,0,0};
    assertTrue(WkBitcoinCommand.GETBLOCKS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.GETBLOCKS, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetheaders() {
    byte[] bytesarray = new byte[] {'g','e','t','h','e','a','d','e','r','s',0,0};
    assertTrue(WkBitcoinCommand.GETHEADERS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.GETHEADERS, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testTx() {
    byte[] bytesarray = new byte[] {'t','x',0,0,0,0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.TX.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.TX, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testBlock() {
    byte[] bytesarray = new byte[] {'b','l','o','c','k',0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.BLOCK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.BLOCK, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testHeaders() {
    byte[] bytesarray = new byte[] {'h','e','a','d','e','r','s',0,0,0,0,0};
    assertTrue(WkBitcoinCommand.HEADERS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.HEADERS, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetaddr() {
    byte[] bytesarray = new byte[] {'g','e','t','a','d','d','r',0,0,0,0,0};
    assertTrue(WkBitcoinCommand.GETADDR.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.GETADDR, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testMempool() {
    byte[] bytesarray = new byte[] {'m','e','m','p','o','o','l',0,0,0,0,0};
    assertTrue(WkBitcoinCommand.MEMPOOL.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.MEMPOOL, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testCheckorder() {
    byte[] bytesarray = new byte[] {'c','h','e','c','k','o','r','d','e','r',0,0};
    assertTrue(WkBitcoinCommand.CHECKORDER.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.CHECKORDER, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testSubmitorder() {
    byte[] bytesarray = new byte[] {'s','u','b','m','i','t','o','r','d','e','r',0};
    assertTrue(WkBitcoinCommand.SUBMITORDER.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.SUBMITORDER, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testReply() {
    byte[] bytesarray = new byte[] {'r','e','p','l','y',0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.REPLY.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.REPLY, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testPing() {
    byte[] bytesarray = new byte[] {'p','i','n','g',0,0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.PING.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.PING, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testPong() {
    byte[] bytesarray = new byte[] {'p','o','n','g',0,0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.PONG.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.PONG, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testReject() {
    byte[] bytesarray = new byte[] {'r','e','j','e','c','t',0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.REJECT.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.REJECT, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFilterload() {
    byte[] bytesarray = new byte[] {'f','i','l','t','e','r','l','o','a','d',0,0};
    assertTrue(WkBitcoinCommand.FILTERLOAD.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.FILTERLOAD, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFilteradd() {
    byte[] bytesarray = new byte[] {'f','i','l','t','e','r','a','d','d',0,0,0};
    assertTrue(WkBitcoinCommand.FILTERADD.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.FILTERADD, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFilterclear() {
    byte[] bytesarray = new byte[] {'f','i','l','t','e','r','c','l','e','a','r',0};
    assertTrue(WkBitcoinCommand.FILTERCLEAR.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.FILTERCLEAR, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testMerkleblock() {
    byte[] bytesarray = new byte[] {'m','e','r','k','l','e','b','l','o','c','k',0};
    assertTrue(WkBitcoinCommand.MERKLEBLOCK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.MERKLEBLOCK, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testAlert() {
    byte[] bytesarray = new byte[] {'a','l','e','r','t',0,0,0,0,0,0,0};
    assertTrue(WkBitcoinCommand.ALERT.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.ALERT, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testSendheaders() {
    byte[] bytesarray = new byte[] {'s','e','n','d','h','e','a','d','e','r','s',0};
    assertTrue(WkBitcoinCommand.SENDHEADERS.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.SENDHEADERS, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testFeefilter() {
    byte[] bytesarray = new byte[] {'f','e','e','f','i','l','t','e','r',0,0,0};
    assertTrue(WkBitcoinCommand.FEEFILTER.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.FEEFILTER, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testSendcmpct() {
    byte[] bytesarray = new byte[] {'s','e','n','d','c','m','p','c','t',0,0,0};
    assertTrue(WkBitcoinCommand.SENDCMPCT.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.SENDCMPCT, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testCmpctblock() {
    byte[] bytesarray = new byte[] {'c','m','p','c','t','b','l','o','c','k',0,0};
    assertTrue(WkBitcoinCommand.CMPCTBLOCK.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.CMPCTBLOCK, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testGetblocktxn() {
    byte[] bytesarray = new byte[] {'g','e','t','b','l','o','c','k','t','x','n',0};
    assertTrue(WkBitcoinCommand.GETBLOCKTXN.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.GETBLOCKTXN, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testBlocktxn() {
    byte[] bytesarray = new byte[] {'b','l','o','c','k','t','x','n',0,0,0,0};
    assertTrue(WkBitcoinCommand.BLOCKTXN.bytes.equalsToArray(bytesarray));
    assertEquals(WkBitcoinCommand.BLOCKTXN, WkBitcoinCommand.newCommand(bytesarray));
    assertTrue(WkBitcoinCommand.findKnownCommand(bytesarray).isPresent());
  }

  @Test
  void testSerde() {
    WkSerdeDtreeStruct<WkBitcoinCommand, WkSerdeDtreeOperationSettings, WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBitcoinCommandSerdeField>
      btcCmd = WkBitcoinCommandSerdeField.newStruct("BTC_CMD");

    KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

    WkSerdeDtreeWriter<WkBitcoinCommand, WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldWriter>
      cmdWriter = btcCmd.newOutputPacket(WkBitcoinCommand.VERSION, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

    cmdWriter.processBytestream();

    assertTrue(cmdWriter.isCompleted());

    WkSerdeDtreeReader<WkBitcoinCommand, WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldReader>
      cmdReader = btcCmd.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

    cmdReader.processBytestream();

    assertTrue(cmdReader.isCompleted());
    assertTrue(cmdReader.firstOperation().isPresent());
    assertTrue(cmdReader.firstOperation().get().result().isPresent());
    assertTrue(cmdReader.firstOperation().get().result().get().serializable().isPresent());

    assertEquals(
        WkBitcoinCommand.VERSION,
        cmdReader.firstOperation().get().
                  result().get().
                  serializable().get());
  }

}
