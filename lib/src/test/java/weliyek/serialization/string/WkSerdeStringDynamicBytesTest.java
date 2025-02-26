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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.number.WkSerdeSignedBigEndianInteger;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedBigEndianIntegerWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkSerdeStringDynamicBytesTest
{

  private static final Logger logger = LoggerFactory.getLogger(WkSerdeStringDynamicBytesTest.class);

  private static Charset defaultCharset = StandardCharsets.UTF_8;

  private static WkSerdeDtreeStruct<
                          String,
                          WkSerdeDtreeOperationSettings,
                          WkSerdeStringDynamicBytes<Integer,
                                    WkSerdeSignedBigEndianInteger,
                                    WkSerdeSignedBigEndianIntegerReader,
                                    ?, ?,
                                    ? extends WkSerdeSignedBigEndianInteger>,
                          WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>,
                          WkSerdeDtreeBytestreamInputBase<?>,
                          WkSerdeDtreeOperationSettings,
                          WkSerdeStringDynamicBytes<
                                    Integer, ?, ?,
                                    WkSerdeSignedBigEndianInteger,
                                    WkSerdeSignedBigEndianIntegerWriter,
                                    ? extends WkSerdeSignedBigEndianInteger>,
                          WkSerdeStringDynamicBytesWriter<
                                    Integer,
                                    WkSerdeSignedBigEndianInteger,
                                    WkSerdeSignedBigEndianIntegerWriter>,
                          WkSerdeDtreeBytestreamOutputBase<?>,
                          WkSerdeStringDynamicBytes<
                                    Integer,
                                    WkSerdeSignedBigEndianInteger,
                                    WkSerdeSignedBigEndianIntegerReader,
                                    WkSerdeSignedBigEndianInteger,
                                    WkSerdeSignedBigEndianIntegerWriter,
                                    WkSerdeSignedBigEndianInteger>>
                              DYNAMIC_STR_STRUCT;

  static final String originalStr = "Test string";
  static final byte[] expectedBytes = originalStr.getBytes(defaultCharset);

  @BeforeAll
  public static void setUpBeforeClass() throws Exception {
    DYNAMIC_STR_STRUCT = WkSerdeStringDynamicBytes.<
                            Integer,
                            WkSerdeSignedBigEndianInteger,
                            WkSerdeSignedBigEndianIntegerReader,
                            WkSerdeSignedBigEndianInteger,
                            WkSerdeSignedBigEndianIntegerWriter,
                            WkSerdeSignedBigEndianInteger>newStruct(
                                "DYNAMICSTR",
                                "BYTES",
                                "ARRAYSIZE",
                                "BYTEARRAY",
                                0,
                                1024,
                                defaultCharset,
                                Integer::valueOf,
                                WkSerdeSignedBigEndianInteger::newCore);
  }

  @AfterAll
  public static void tearDownAfterClass() throws Exception {
  }

  @BeforeEach
  public void setUp() throws Exception {
  }

  @AfterEach
  public void tearDown() throws Exception {
  }

  @Test
  public void test() {
    KetzaByteOutputStream outputstream = new KetzaByteOutputStream();
    WkSerdeDtreeWriter<
                String,
                WkSerdeStringDynamicBytes<Integer, ?, ?, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter, ? extends WkSerdeSignedBigEndianInteger>,
                WkSerdeStringDynamicBytesWriter<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerWriter>>
      dynstrWriting = DYNAMIC_STR_STRUCT.newOutputPacket(
                                              originalStr,
                                              WkSerdeDtreeOperationSettings.EMPTY,
                                              outputstream);
    logger.info(dynstrWriting.name() + " created");
    while(dynstrWriting.isInProgress()) {
      dynstrWriting.processBytestream();
      if (dynstrWriting.previousProcessingSteapResult().isPresent()) {
        break;
      }
    }
    logger.info("Checking if is "
                + DYNAMIC_STR_STRUCT.definition().bytes().definition().size().definition());
    assertEquals(DYNAMIC_STR_STRUCT.definition().bytes().definition().size().definition(),
                 dynstrWriting.previousProcessingSteapResult().get().definition());
    assertEquals(expectedBytes.length,
                 dynstrWriting.firstOperation().get()
                              .bytes().get()
                              .firstOperation().get()
                              .size().get()
                              .firstOperation().get()
                              .serializable().intValue());
    while(dynstrWriting.isInProgress()) {
      dynstrWriting.processBytestream();
      if (dynstrWriting.previousProcessingSteapResult().isPresent()) {
        break;
      }
    }
    logger.info("Checking if is "
                + DYNAMIC_STR_STRUCT.definition().bytes().definition().variableSequence().definition());
    assertEquals(DYNAMIC_STR_STRUCT.definition().bytes().definition().variableSequence().definition(),
                 dynstrWriting.previousProcessingSteapResult().get().definition());
    assertTrue(dynstrWriting.firstOperation().get()
                     .bytes().get()
                     .firstOperation().get()
                     .variableSequence().get()
                     .firstOperation().get()
                     .serializable().equalsToArray(expectedBytes));

    WkSerdeDtreeReader<
          String,
          WkSerdeStringDynamicBytes<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader,?,?,? extends WkSerdeSignedBigEndianInteger>,
          WkSerdeStringDynamicBytesReader<Integer, WkSerdeSignedBigEndianInteger, WkSerdeSignedBigEndianIntegerReader>>
      dynstrReading = DYNAMIC_STR_STRUCT.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());

    while(dynstrReading.isInProgress()) {
      dynstrReading.processBytestream();
      if (dynstrReading.previousProcessingSteapResult().isPresent()) {
        break;
      }
    }

    while(dynstrReading.isInProgress()) {
      dynstrReading.processBytestream();
      if (dynstrReading.previousProcessingSteapResult().isPresent()) {
        break;
      }
    }

    while(dynstrReading.isInProgress()) {
      dynstrReading.processBytestream();
    }

    assertEquals(originalStr,
                 dynstrReading.firstOperation().get().result().get().serializable().get());
  }

}
