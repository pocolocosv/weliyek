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

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import weliyek.amat.base.input.ProtocolReading;
import weliyek.amat.base.output.ProtocolWriting;
import weliyek.amat.newserialization.SerializationTester;
import weliyek.amat.newserialization.operation.BasicInputConfig;
import weliyek.amat.newserialization.operation.BasicOutputConfig;
import weliyek.amat.newserialization.operation.InputConfig;
import weliyek.amat.newserialization.operation.OutputConfig;
import weliyek.bitcoin.BitcoinCommand;
import weliyek.bitcoin.BitcoinCommandInputField;
import weliyek.bitcoin.BitcoinCommandOutputField;
import weliyek.bitcoin.BitcoinCommandReading;
import weliyek.bitcoin.BitcoinCommandWriting;
import weliyek.ketza.util.KetzaByteOutputStream;

class BitcoinCommandSerializationTest
{

    private static BitcoinCommandOutputField OUTPUT_FIELD;
    private static BitcoinCommandInputField INPUT_FIELD;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        OUTPUT_FIELD = new BitcoinCommandOutputField();
        INPUT_FIELD = new BitcoinCommandInputField();
    }

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testAddr() {
        byte[] buff = new byte[] {'a','d','d','r',0,0,0,0,0,0,0,0};
        readWriteRead(buff, BitcoinCommand.ADDR);
    }

    @Test
    void testVersion() {
        byte[] buff = new byte[] {'v','e','r','s','i','o','n',0,0,0,0,0};
        readWriteRead(buff, BitcoinCommand.VERSION);
    }

    private void readWriteRead(byte[] buff, BitcoinCommand target) {
        final ByteArrayInputStream stream = new ByteArrayInputStream(buff);

        ProtocolReading<
            BitcoinCommand,
            Object,
            InputConfig,
            BitcoinCommandReading,
            BitcoinCommandInputField> first_cmd_reading = INPUT_FIELD.newFullReadOperation(new Object(), BasicInputConfig.INSTANCE, stream);

        SerializationTester.assertNotStarted(first_cmd_reading);
        first_cmd_reading.processBytestream();
        SerializationTester.assertStarted(first_cmd_reading);
        first_cmd_reading.processBytestream();
        SerializationTester.assertHasExpectedDeserialized(target, first_cmd_reading);

        KetzaByteOutputStream outputBuffer = new KetzaByteOutputStream();

        ProtocolWriting<
            BitcoinCommand,
            Object,
            OutputConfig,
            BitcoinCommandWriting,
            BitcoinCommandOutputField> cmd_writing = OUTPUT_FIELD.startWriting(target, new Object(), BasicOutputConfig.INSTANCE, outputBuffer);

        SerializationTester.assertNotStarted(cmd_writing);
        cmd_writing.processSingleStepBytestream();
        SerializationTester.assertStarted(cmd_writing);
        cmd_writing.processSingleStepBytestream();
        SerializationTester.assertWritingHasCompleted(target, cmd_writing);

        ProtocolReading<
            BitcoinCommand,
            Object,
            InputConfig,
            BitcoinCommandReading,
            BitcoinCommandInputField> second_cmd_reading = INPUT_FIELD.newFullReadOperation(new Object(), BasicInputConfig.INSTANCE, outputBuffer.inputStream());

        SerializationTester.assertNotStarted(second_cmd_reading);
        second_cmd_reading.processBytestream();
        SerializationTester.assertStarted(second_cmd_reading);
        second_cmd_reading.processBytestream();
        SerializationTester.assertHasExpectedDeserialized(target, second_cmd_reading);
    }

}
