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
package weliyek.util.array;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeReader;
import weliyek.serialization.WkSerdeDtreeWriter;
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkByteArrayTest
{

    private static final Logger logger = LoggerFactory.getLogger(WkByteArrayTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test01_CtorWithSubarrayElements() {
        byte[] buf = new byte[] { 1, 2 ,3, 4, 5};
        int from = 1;
        int to = 4;
        byte[] expected = Arrays.copyOfRange(buf, from, to);
        final WkByteArray wrapper = new WkByteArray(buf, from, to);
        final WkPrimitiveArray.ArrayEqualComparator arrayComparator = new WkPrimitiveArray.ArrayEqualComparator(expected, 0, expected.length);

        assertTrue(wrapper.equalsToArray(expected, 0));

        wrapper.iterateAsIntsWhileTrue(arrayComparator);
        assertEquals(3, arrayComparator.getCounter());
    }

    @Test
    public void test02_FixedSizeByteArray() {
      int writingSerializationStepSize = 2;
      int readingSerializationStepSize = 4;
      byte[] originalArray = new byte[] { (byte)0xFF, 0x09, 0x0A, 0x0B, 0x0C ,0x0D, 0x0E, 0x0F, (byte)0xFF };
      int sequenceStartIndex = 1;
      int sequenceEndIndex = 7;
      int sequenceLenght = sequenceEndIndex - sequenceStartIndex;
      WkByteArray outputWrapper = new WkByteArray(originalArray, sequenceStartIndex, sequenceEndIndex);
      assertTrue(outputWrapper.equalsToArray(originalArray, 1));
      KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

      WkSerdeDtreeStruct<WkByteArray, WkSerdeDtreeOperationSettings, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeDtreeFixedSizeByteArray>
        fixeSizeByteArray = WkSerdeDtreeFixedSizeByteArray.newStruct("FIXED_BYTEARRAY", sequenceLenght);
      logger.info(fixeSizeByteArray + " output protocol created");

      WkSerdeDtreeWriter<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayWriter>
        byteArrayWriting = fixeSizeByteArray.newOutputPacket(outputWrapper, WkSerdeDtreeOperationSettings.EMPTY, outputstream);

      logger.info(byteArrayWriting.toString());

      assertFalse(byteArrayWriting.isCompleted());
      assertEquals(0, byteArrayWriting.firstOperation().get().dashboard().nextElementIndex());

      int i = 1;
      while(byteArrayWriting.isInProgress()) {
        byteArrayWriting.processBytestream();
        if (byteArrayWriting.isInProgress()) {
          assertEquals(
              writingSerializationStepSize * i++,
              byteArrayWriting.firstOperation().get().dashboard().nextElementIndex());
        }
      }

      assertTrue(byteArrayWriting.isCompleted());
      assertEquals(writingSerializationStepSize*3, byteArrayWriting.firstOperation().get().dashboard().nextElementIndex());

      assertTrue(outputstream.equals(originalArray, sequenceStartIndex, sequenceEndIndex));

      WkSerdeDtreeReader<WkByteArray, WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader>
        byteArrayReading = fixeSizeByteArray.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputstream.inputStream());

      logger.info(byteArrayReading + " input op created");

      i = 1;
      while(byteArrayReading.isInProgress()) {
        byteArrayReading.processBytestream();
        if (byteArrayReading.isInProgress()) {
          assertEquals(
              readingSerializationStepSize * i++,
              byteArrayReading.firstOperation().get().dashboard().nextElementIndex());
        }
      }

      assertTrue(byteArrayReading.isCompleted());
      assertTrue(byteArrayReading.firstOperation().get().result().isPresent());

      assertEquals(outputWrapper, byteArrayReading.firstOperation().get().result().get().serializable().get());
    }

    @Test
    public void test03_VariableSizeByteArray() {
      int serializationStepSize = 2;
      byte[] originalArray = new byte[] { (byte)0xFF, 0x09, 0x0A, 0x0B, 0x0C ,0x0D, 0x0E, 0x0F, (byte)0xFF };
      int sequenceStartIndex = 1;
      int sequenceEndIndex = 7;
      int sequenceLenght = sequenceEndIndex - sequenceStartIndex;
      WkByteArray outputWrapper = new WkByteArray(originalArray, sequenceStartIndex, sequenceEndIndex);
      assertTrue(outputWrapper.equalsToArray(originalArray, 1));
      KetzaByteOutputStream outputstream = new KetzaByteOutputStream();

      WkSerdeDtreeStruct<WkByteArray, WkSerdeDtreeOperationSettingsVariableLength, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkSerdeDtreeVariableSizeByteArray>
        outputProtocol = WkSerdeDtreeVariableSizeByteArray.newStruct("DYNAMIC_BYTEARRAY", 0, 100);

      WkSerdeDtreeWriter<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayWriter>
        wrapperWriting = outputProtocol.newOutputPacket(outputWrapper, WkSerdeDtreeOperationSettings.EMPTY, outputstream);

      assertFalse(wrapperWriting.isCompleted());
      int i = 1;
      while(wrapperWriting.isInProgress()) {
        wrapperWriting.processBytestream();
      }

      assertTrue(outputstream.equals(originalArray, sequenceStartIndex, sequenceEndIndex));

      WkSerdeDtreeReader<WkByteArray, WkSerdeDtreeVariableSizeByteArray, WkSerdeDtreeVariableSizeByteArrayReader>
        wrapperReading = outputProtocol.newInputPacket(WkSerdeDtreeOperationSettingsVariableLength.withLength(sequenceLenght), outputstream.inputStream());

      logger.info(wrapperReading + " input op created");

      i = 1;
      while(wrapperReading.isInProgress()) {
        wrapperReading.processBytestream();
        if (wrapperReading.isInProgress()) {
          assertEquals(
              serializationStepSize * i++,
              wrapperReading.firstOperation().get().dashboard().nextElementIndex());
        }
      }

      assertTrue(wrapperReading.isCompleted());
      assertTrue(wrapperReading.firstOperation().get().result().isPresent());

      assertEquals(outputWrapper, wrapperReading.firstOperation().get().result().get().serializable().get());
    }

    /*
    @Test
    public void test04_VariableSizeByteArray_InvalidLowerBound() {
      assertThrows(
          WkSzDefinitionCoreException.class,
          () -> WkSerdeDtreeVariableSizeByteArray.newStruct("INVALID", -1, 10));
    }

    @Test
    public void test05_VariableSizeByteArray_InvalidHigherBound() {
      assertThrows(
          WkSzDefinitionCoreException.class,
          () -> WkSerdeDtreeVariableSizeByteArray.newStruct("INVALID", 1, -1));
    }

    @Test
    public void test06_VariableSizeByteArray_InvalidBounds() {
      assertThrows(
          WkSzDefinitionCoreException.class,
          () -> WkSerdeDtreeVariableSizeByteArray.newStruct("INVALID", 22, 11));
    }
    */


    /* =======
    private static WritingParameters<Object, ByteArrayWrapper, OutputBytestream<?>>
    buildWritingSettings(
        ByteArrayWrapper wrapper,
        KetzaByteOutputStream outputBuffer) {
      CountingOutputBytestream countingBytestream = new CountingOutputBytestream(outputBuffer);
      BasicWritingParametersBuilder<Object, ByteArrayWrapper> writingSettingsBuilder =
          new BasicWritingParametersBuilder<Object, ByteArrayWrapper>(wrapper, new Object(), countingBytestream);
      WritingParameters<Object, ByteArrayWrapper, OutputBytestream<?>> settings =
          writingSettingsBuilder.build();
      return settings;
    }

    private static void assertOpNotStarted(ProtocolOperation<?> operation) {
      assertNotNull(operation);
      assertFalse(operation.isCompleted());
      assertFalse(operation.topOperation().hasStarted());
      assertFalse(operation.topOperation().settings().isPresent());

      if (operation instanceof ProtocolWriting) {
        assertFalse(
            ((ProtocolWriting<?, ?, ?, ?>) operation).topOperation().encoded().isPresent());
      } else {
        assertFalse(
            ((ProtocolReading<?, ?, ?, ?, ?>) operation).topOperation().decoded().isPresent());
      }
    }

    private static void assertOpStartedButNotCompleted(ProtocolOperation<?> operation) {
      assertTrue(operation.topOperation().hasStarted());
      assertFalse(operation.topOperation().isCompleted());
      assertTrue(operation.topOperation().settings().isPresent());
    }
    */

    /*
    @Test
    public void testSerialization() {
        byte[] buf = new byte[] { -2, -1, 0, 1, 2, -1, -2};
        ByteArrayWrapper writeWrapper = new ByteArrayWrapper(buf, 2, 3);
        BasicByteArrayWrapperOutputField<Object> wrapperOutputField = new BasicByteArrayWrapperOutputField<>(Optional.empty(), 0, 0);
        KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();
        ProtocolWriting<
            ByteArrayWrapper,
            Object,
            SizeProviderAndPaddingElementWritingConfig<Byte>,
            BasicByteArrayWrapperWriting<Object>,
            BasicByteArrayWrapperOutputField<Object>>
        wrapperWriting = wrapperOutputField.startWriting(
                                writeWrapper,
                                new Object(),
                                new BasicSequenceWritingConfig<>(writeWrapper.getLength(), Optional.empty()),
                                outputBuffer);
        SerializationTester.assertNotStarted(wrapperWriting);
        wrapperWriting.processBytestream();
        SerializationTester.assertStarted(wrapperWriting);
        wrapperWriting.processBytestream();
        SerializationTester.assertWritingHasCompleted(writeWrapper, wrapperWriting);

        assertEquals(3, outputBuffer.size());

        BasicByteArrayWrapperInputField wrapperInputField = new BasicByteArrayWrapperInputField(-1, -1);
        ProtocolReading<
            ByteArrayWrapper,
            Object,
            SizeProviderReadingConfig,
            BasicByteArrayWrapperReading,
            BasicByteArrayWrapperInputField>
                wrapperReading = wrapperInputField.newFullReadOperation(
                                                    new Object(),
                                                    new BasicSequenceReadingConfig(writeWrapper.getLength()),
                                                    outputBuffer.inputStream());

        SerializationTester.assertNotStarted(wrapperReading);
        wrapperReading.processBytestream();
        SerializationTester.assertStarted(wrapperReading);
        wrapperReading.processBytestream();
        SerializationTester.assertHasExpectedDeserialized(writeWrapper, wrapperReading);
    }
    */

}
