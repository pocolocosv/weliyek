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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzInputPacket;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzOutputPacket;
import weliyek.serialization.WkSzStruct;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytes;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytesReader;
import weliyek.serialization.string.WkSzStringWithFixedLengthBytesWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

public class StringWithFixedLengthBytesTest
{

    private static final Logger logger = LoggerFactory.getLogger(StringWithFixedLengthBytesTest.class);

    private int numBytesToReadWrite;

    private WkSzStruct<
                        String,
                        WkSzOperationSettings,
                        WkSzStringWithFixedLengthBytes,
                        WkSzStringWithFixedLengthBytesReader,
                        WkSzInputBytestreamBase<?>,
                        WkSzOperationSettings,
                        WkSzStringWithFixedLengthBytes,
                        WkSzStringWithFixedLengthBytesWriter,
                        WkSzOutputBytestreamBase<?>,
                        WkSzStringWithFixedLengthBytes> stringWithFixedSizeBytes;

    @Before
    public void setUp() throws Exception {
        numBytesToReadWrite = 10;
        stringWithFixedSizeBytes = WkSzStringWithFixedLengthBytes
                                .newPacketStructure(
                                    "FIXED_LEN_STRING_" + numBytesToReadWrite,
                                    "BYTES",
                                    numBytesToReadWrite,
                                    StandardCharsets.UTF_8);
        logger.info(stringWithFixedSizeBytes.name() + " created");
    }

    @Test
    public void testShortString() {
        String shortStr = "str";
        byte[] expectedByteArray = Arrays.copyOf(shortStr.getBytes(StandardCharsets.UTF_8), numBytesToReadWrite);

        KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

        WkSzOutputPacket<String, WkSzStringWithFixedLengthBytes, WkSzStringWithFixedLengthBytesWriter>
          stringWithFixedSizeSerializing = stringWithFixedSizeBytes.newOutputPacket(shortStr, WkSzOperationSettings.EMPTY, outputstream);
        logger.info(stringWithFixedSizeSerializing.name() + " created");

        assertFalse(stringWithFixedSizeSerializing.isCompleted());

        int i = 1;
        int writingSerializationStepSize = stringWithFixedSizeBytes.definition().bytes().field().definition().getSerializationStepSize();
        while(stringWithFixedSizeSerializing.isInProgress()) {
          stringWithFixedSizeSerializing.processBytestream();
          if (stringWithFixedSizeSerializing.isInProgress()) {
            assertEquals(
                Math.min(writingSerializationStepSize * i++, expectedByteArray.length),
                stringWithFixedSizeSerializing.firstOperation().get().bytes().field().get().firstOperation().get().dashboard().nextElementIndex());
          }
        }

        assertTrue(stringWithFixedSizeSerializing.isCompleted());

        assertTrue(outputstream.equals(expectedByteArray));

        WkSzInputPacket<String, WkSzStringWithFixedLengthBytes, WkSzStringWithFixedLengthBytesReader>
          stringWithFixedSizeBytesDeserializing = stringWithFixedSizeBytes.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
        logger.info(stringWithFixedSizeBytesDeserializing.name() + " created");

        i = 1;
        int readingSerializationStepSize = stringWithFixedSizeBytes.definition().bytes().field().definition().getSerializationStepSize();
        while(stringWithFixedSizeBytesDeserializing.isInProgress()) {
          stringWithFixedSizeBytesDeserializing.processBytestream();
          if (stringWithFixedSizeBytesDeserializing.isInProgress()) {
            assertEquals(
                readingSerializationStepSize * i++,
                stringWithFixedSizeSerializing.firstOperation().get().bytes().field().get().firstOperation().get().dashboard().nextElementIndex());
          }
        }

        assertTrue(stringWithFixedSizeSerializing.isCompleted());
        assertTrue(stringWithFixedSizeSerializing.firstOperation().get().result().isPresent());

        assertEquals(shortStr, stringWithFixedSizeBytesDeserializing.firstOperation().get().result().get().deserialized().get());
    }

    @Test
    public void testLongStr() {

        String longStr = "This is a very long string to be tested.";
        String expectedStr = longStr.substring(0, numBytesToReadWrite);
        byte[] expectedByteArray = expectedStr.getBytes(StandardCharsets.UTF_8);

        KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

        WkSzOutputPacket<String, WkSzStringWithFixedLengthBytes, WkSzStringWithFixedLengthBytesWriter>
        stringWithFixedSizeSerializing = stringWithFixedSizeBytes.newOutputPacket(longStr, WkSzOperationSettings.EMPTY, outputstream);

        logger.info(stringWithFixedSizeSerializing.name() + " created");

        assertFalse(stringWithFixedSizeSerializing.isCompleted());

        int i = 1;
        int writingSerializationStepSize = stringWithFixedSizeBytes.definition().bytes().field().definition().getSerializationStepSize();
        while(stringWithFixedSizeSerializing.isInProgress()) {
          stringWithFixedSizeSerializing.processBytestream();
          if (stringWithFixedSizeSerializing.isInProgress()) {
            assertEquals(
                Math.min(writingSerializationStepSize * i++, expectedByteArray.length),
                stringWithFixedSizeSerializing.firstOperation().get().bytes().field().get().firstOperation().get().dashboard().nextElementIndex());
          }
        }

        assertTrue(stringWithFixedSizeSerializing.isCompleted());

        assertTrue(outputstream.equals(expectedByteArray));

        WkSzInputPacket<String, WkSzStringWithFixedLengthBytes, WkSzStringWithFixedLengthBytesReader>
          stringWithFixedSizeBytesDeserializing = stringWithFixedSizeBytes.newInputPacket(WkSzOperationSettings.EMPTY, outputstream.inputStream());
        logger.info(stringWithFixedSizeBytesDeserializing.name() + " created");

        i = 1;
        int readingSerializationStepSize = stringWithFixedSizeBytes.definition().bytes().field().definition().getSerializationStepSize();
        while(stringWithFixedSizeBytesDeserializing.isInProgress()) {
          stringWithFixedSizeBytesDeserializing.processBytestream();
          if (stringWithFixedSizeBytesDeserializing.isInProgress()) {
            assertEquals(
                readingSerializationStepSize * i++,
                stringWithFixedSizeSerializing.firstOperation().get().bytes().field().get().firstOperation().get().dashboard().nextElementIndex());
          }
        }

        assertTrue(stringWithFixedSizeSerializing.isCompleted());
        assertTrue(stringWithFixedSizeSerializing.firstOperation().get().result().isPresent());

        assertEquals(expectedStr, stringWithFixedSizeBytesDeserializing.firstOperation().get().result().get().deserialized().get());
    }

}