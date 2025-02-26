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
package weliyek.bitcoin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;

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
import weliyek.serialization.util.KetzaByteOutputStream;

public class WkBtcDifficultyTargetTest
{

    public static final Logger logger = LoggerFactory.getLogger(WkBtcDifficultyTargetTest.class);

    final static byte[] COMPACT1_BYTES = new byte[] {       0x56,       0x34,       0x12, 0x05};
    final static byte[] COMPACT2_BYTES = new byte[] { (byte)0xDE, (byte)0xC0,       0x00, 0x06};
    final static byte[] COMPACT3_BYTES = new byte[] {       0x56,       0x34, (byte)0x92, 0x04};
    final static byte[] COMPACT4_BYTES = new byte[] {       0x56,       0x34,       0x12, 0x04};
    final static byte[] COMPACT5_BYTES = new byte[] { (byte)0x00, (byte)0xDE, (byte)0xC0, 0x05};

    final static byte[] TARGET_HASH = new byte[] {
        0x00, 0x00, 0x00, 0x00, 0x00, 0x04, 0x04, (byte)0xCB,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
        0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
    };

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testBigIntConversion() {
        assertEquals(0x05123456, WkBtcDifficultyTarget.convertBigIntegerToCompact(new BigInteger("1234560000", 16)));
        assertEquals(0x0600C0DE, WkBtcDifficultyTarget.convertBigIntegerToCompact(new BigInteger("C0DE000000", 16)));
        assertEquals(0x04923456, WkBtcDifficultyTarget.convertBigIntegerToCompact(new BigInteger("12345600", 16).negate()));
        assertEquals(0x04123456, WkBtcDifficultyTarget.convertBigIntegerToCompact(new BigInteger("12345600", 16)));
        assertEquals(0x05C0DE00, WkBtcDifficultyTarget.convertBigIntegerToCompact(new BigInteger("40de000000", 16).negate()));

        assertEquals(new BigInteger("1234560000", 16),          WkBtcDifficultyTarget.convertCompactToBigInteger(0x05123456));
        assertEquals(new BigInteger("C0DE000000", 16),          WkBtcDifficultyTarget.convertCompactToBigInteger(0x0600C0DE));
        assertEquals(new BigInteger("12345600", 16).negate(),   WkBtcDifficultyTarget.convertCompactToBigInteger(0x04923456));
        assertEquals(new BigInteger("12345600", 16),            WkBtcDifficultyTarget.convertCompactToBigInteger(0x04123456));
        assertEquals(new BigInteger("40de000000", 16).negate(), WkBtcDifficultyTarget.convertCompactToBigInteger(0x05C0DE00));
    }

    @Test
    public void testHashToCompact() {
      WkBtcDifficultyTarget targetHash = new WkBtcDifficultyTarget(TARGET_HASH);
      int compactTarget = targetHash.toCompact();
      assertEquals(0x1b0404cb, compactTarget);
      WkBtcDifficultyTarget convResult = WkBtcDifficultyTarget.ofCompact(compactTarget);
      assertEquals(convResult, targetHash);
    }

    @Test
    public void testSerde() {
      KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

      WkSerdeDtreeStruct<WkBtcDifficultyTarget, WkSerdeDtreeOperationSettings, WkBtcDifficultyTargetSerdeDef, WkBtcDifficultyTargetSerdeReader, WkSerdeDtreeBytestreamInputBase<?>, WkSerdeDtreeOperationSettings, WkBtcDifficultyTargetSerdeDef, WkBtcDifficultyTargetSerdeWriter, WkSerdeDtreeBytestreamOutputBase<?>, WkBtcDifficultyTargetSerdeDef>
        targetMsgStruct = WkBtcDifficultyTargetSerdeDef.newStruct("DIFF_TARGET");

      WkBtcDifficultyTarget targetHash = new WkBtcDifficultyTarget(TARGET_HASH);
      int compactTarget = targetHash.toCompact();
      assertEquals(0x1b0404cb, compactTarget);

      WkSerdeDtreeWriter<WkBtcDifficultyTarget, WkBtcDifficultyTargetSerdeDef, WkBtcDifficultyTargetSerdeWriter>
        targetWriter = targetMsgStruct.newOutputPacket(targetHash, WkSerdeDtreeOperationSettings.EMPTY, outputBuffer);

      targetWriter.processBytestream();

      assertTrue(targetWriter.isCompleted());

      assertEquals(4, outputBuffer.size());

      WkSerdeDtreeReader<WkBtcDifficultyTarget, WkBtcDifficultyTargetSerdeDef, WkBtcDifficultyTargetSerdeReader>
        targetReader = targetMsgStruct.newInputPacket(WkSerdeDtreeOperationSettings.EMPTY, outputBuffer.inputStream());

      targetReader.processBytestream();

      assertTrue(targetReader.isCompleted());

      assertEquals(compactTarget, targetReader.firstOperation().get().int32().get().firstOperation().get().result().get().serializable().get().intValue());
      assertEquals(targetHash, targetReader.firstOperation().get().result().get().serializable().get());
    }

}
