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
package weliyek.serialization.string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzInputNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzOutputNode;
import weliyek.serialization.number.WkSignedBigEndianIntegerSrlzStructNode;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkStringWithDynamicSizeBytesTest
{

  private static final Logger logger = LoggerFactory.getLogger(WkStringWithDynamicSizeBytesTest.class);

  private static Charset defaultCharset = StandardCharsets.UTF_8;

  private static WkSrlzStruct<
                          String,
                          WkSettingsSrlzPacketOperationData,
                          WkStringWithDynamicBytesSrlzStructNode<Integer,
                                    WkSignedBigEndianIntegerSrlzStructNode,
                                    WkSignedBigEndianIntegerSrlzInputNode,
                                    ?, ?,
                                    ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                          WkStringWithDynamicBytesSrlzInputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>,
                          WkSzInputBytestreamBase<?>,
                          WkSettingsSrlzPacketOperationData,
                          WkStringWithDynamicBytesSrlzStructNode<
                                    Integer, ?, ?,
                                    WkSignedBigEndianIntegerSrlzStructNode,
                                    WkSignedBigEndianIntegerSrlzOutputNode,
                                    ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                          WkStringWithDynamicBytesSrlzOutputNode<
                                    Integer,
                                    WkSignedBigEndianIntegerSrlzStructNode,
                                    WkSignedBigEndianIntegerSrlzOutputNode>,
                          WkSzOutputBytestreamBase<?>,
                          WkStringWithDynamicBytesSrlzStructNode<
                                    Integer,
                                    WkSignedBigEndianIntegerSrlzStructNode,
                                    WkSignedBigEndianIntegerSrlzInputNode,
                                    WkSignedBigEndianIntegerSrlzStructNode,
                                    WkSignedBigEndianIntegerSrlzOutputNode,
                                    WkSignedBigEndianIntegerSrlzStructNode>>
                              DYNAMIC_STR_STRUCT;

  static final String originalStr = "Test string";
  static final byte[] expectedBytes = originalStr.getBytes(defaultCharset);

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    DYNAMIC_STR_STRUCT = WkStringWithDynamicBytesSrlzStructNode.<
                            Integer,
                            WkSignedBigEndianIntegerSrlzStructNode,
                            WkSignedBigEndianIntegerSrlzInputNode,
                            WkSignedBigEndianIntegerSrlzStructNode,
                            WkSignedBigEndianIntegerSrlzOutputNode,
                            WkSignedBigEndianIntegerSrlzStructNode>newStruct(
                                "DYNAMICSTR",
                                "BYTES",
                                "ARRAYSIZE",
                                "BYTEARRAY",
                                0,
                                1024,
                                defaultCharset,
                                Integer::valueOf,
                                WkSignedBigEndianIntegerSrlzStructNode::newCore);
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSzOutputPacket<
                String,
                WkStringWithDynamicBytesSrlzStructNode<Integer, ?, ?, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode, ? extends WkSignedBigEndianIntegerSrlzStructNode>,
                WkStringWithDynamicBytesSrlzOutputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzOutputNode>>
      dynstrWriting = DYNAMIC_STR_STRUCT.newOutputPacket(
                                              originalStr,
                                              WkSettingsSrlzPacketOperationData.EMPTY,
                                              outputstream);
    logger.info(dynstrWriting.name() + " created");
    while(dynstrWriting.isInProgress()) {
      dynstrWriting.processBytestream();
      if (dynstrWriting.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info("Checking if is "
                + DYNAMIC_STR_STRUCT.definition().bytes().field().definition().size().field().definition());
    assertEquals(DYNAMIC_STR_STRUCT.definition().bytes().field().definition().size().field().definition(),
                 dynstrWriting.previousProcessingSteapResult().get().definition());
    assertEquals(expectedBytes.length,
                 dynstrWriting.firstOperation().get()
                              .bytes().field().get()
                              .firstOperation().get()
                              .size().field().get()
                              .firstOperation().get()
                              .serializable().intValue());
    while(dynstrWriting.isInProgress()) {
      dynstrWriting.processBytestream();
      if (dynstrWriting.previousProcessingSteapResult().isPresent()) break;
    }
    logger.info("Checking if is "
                + DYNAMIC_STR_STRUCT.definition().bytes().field().definition().variableSequence().field().definition());
    assertEquals(DYNAMIC_STR_STRUCT.definition().bytes().field().definition().variableSequence().field().definition(),
                 dynstrWriting.previousProcessingSteapResult().get().definition());
    assertTrue(dynstrWriting.firstOperation().get()
                     .bytes().field().get()
                     .firstOperation().get()
                     .variableSequence().field().get()
                     .firstOperation().get()
                     .serializable().equalsToArray(expectedBytes));

    WkSzInputPacket<
          String,
          WkStringWithDynamicBytesSrlzStructNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode,?,?,? extends WkSignedBigEndianIntegerSrlzStructNode>,
          WkStringWithDynamicBytesSrlzInputNode<Integer, WkSignedBigEndianIntegerSrlzStructNode, WkSignedBigEndianIntegerSrlzInputNode>>
      dynstrReading = DYNAMIC_STR_STRUCT.newInputPacket(WkSettingsSrlzPacketOperationData.EMPTY, outputstream.inputStream());

    while(dynstrReading.isInProgress()) {
      dynstrReading.processBytestream();
      if (dynstrReading.previousProcessingSteapResult().isPresent()) break;
    }

    while(dynstrReading.isInProgress()) {
      dynstrReading.processBytestream();
      if (dynstrReading.previousProcessingSteapResult().isPresent()) break;
    }

    while(dynstrReading.isInProgress()) {
      dynstrReading.processBytestream();
    }

    assertEquals(originalStr,
                 dynstrReading.firstOperation().get().result().get().deserialized().get());
  }

}
