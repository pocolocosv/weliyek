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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.protocol.TestAmatInfo;
import weliyek.bitcoin.BitcoinCommandName;
import weliyek.bitcoin.BitcoinConfig;
import weliyek.bitcoin.BitcoinInventoryVector;
import weliyek.bitcoin.BitcoinMessageMagicName;
import weliyek.bitcoin.BitcoinMsg;
import weliyek.bitcoin.BitcoinMsgGetDataRO;
import weliyek.bitcoin.BitcoinMsgGetDataRW;
import weliyek.bitcoin.BitcoinMsgRO;
import weliyek.bitcoin.BitcoinMsgRW;
import weliyek.bitcoin.BitcoinMsgStream;
import weliyek.ketza.base.composite.lineage.Prospective;

/**
 * This test uses data from other test classes in order to create the
 * different arrays needed.
 *
 * @author Ricardo Villalobos Guevara
 *
 */
public class BitcoinMsgGetDataTest
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinMsgGetDataTest.class);

    public final static byte[] ARRAY_MSG_MAGIC = BitcoinMsgVersionTest.ARRAY_MSG_MAGIC;

    public final static byte[] ARRAY_MSG_COMMAND = BitcoinCommandName.GETDATA.toMsgArray();



    public final static byte[] ARRAY_MSG_GETDATA_COUNT = InventoryVectorListTest.ARRAY_INVENTORY_LIST_SIZE;

    public final static byte[] ARRAY_MSG_GETDATA_INV1_TYPE = InventoryVectorListTest.ARRAY_INVENTORY_LIST_INV1_TYPE;

    public final static byte[] ARRAY_MSG_GETDATA_INV1_HASH = InventoryVectorListTest.ARRAY_INVENTORY_LIST_INV1_HASH;

    public final static byte[] ARRAY_MSG_GETDATA_INV2_TYPE = InventoryVectorListTest.ARRAY_INVENTORY_LIST_INV2_TYPE;

    public final static byte[] ARRAY_MSG_GETDATA_INV2_HASH = InventoryVectorListTest.ARRAY_INVENTORY_LIST_INV2_HASH;

    public final static byte[] ARRAY_MSG_GETDATA_PAYLOAD = TestUtils.joinArrays(ARRAY_MSG_GETDATA_COUNT,
                                                                                ARRAY_MSG_GETDATA_INV1_TYPE,
                                                                                ARRAY_MSG_GETDATA_INV1_HASH,
                                                                                ARRAY_MSG_GETDATA_INV2_TYPE,
                                                                                ARRAY_MSG_GETDATA_INV2_HASH);

    public final static byte[] ARRAY_MSG_PAYLOAD_LENGTH = TestUtils.intToArray(ARRAY_MSG_GETDATA_PAYLOAD.length);

    public final static byte[] ARRAY_MSG_PAYLOAD_CHECKSUM = TestUtils.getChecksumArray(ARRAY_MSG_GETDATA_PAYLOAD);

    public final static byte[] ARRAY_MSG = TestUtils.joinArrays(ARRAY_MSG_MAGIC,
                                                                ARRAY_MSG_COMMAND,
                                                                ARRAY_MSG_PAYLOAD_LENGTH,
                                                                ARRAY_MSG_PAYLOAD_CHECKSUM,
                                                                ARRAY_MSG_GETDATA_PAYLOAD);

    public final static   BitcoinMessageMagicName MSG_MAGIC = TestUtils.toMagic(ARRAY_MSG_MAGIC);
    public final static BitcoinCommandName MSG_COMMAND = TestUtils.toCommand(ARRAY_MSG_COMMAND);
    public final static                   int MSG_PAYLOAD_CHECKSUM = TestUtils.toSignedInt(ARRAY_MSG_PAYLOAD_CHECKSUM);
    public final static                   int MSG_GETDATA_COUNT = TestUtils.toVarInt(ARRAY_MSG_GETDATA_COUNT, TestAmatInfo.newNamespace()).intValue();
    public final static BitcoinInventoryVector MSG_GETDATA_INV1 = TestUtils.toInventory(ARRAY_MSG_GETDATA_INV1_TYPE, ARRAY_MSG_GETDATA_INV1_HASH, TestAmatInfo.newNamespace());
    public final static BitcoinInventoryVector MSG_GETDATA_INV2 = TestUtils.toInventory(ARRAY_MSG_GETDATA_INV2_TYPE, ARRAY_MSG_GETDATA_INV2_HASH, TestAmatInfo.newNamespace());

    InputStream msgInputStream;

    @Before
    public void setUp() throws Exception {
        msgInputStream = TestUtils.toLittleEndian(ARRAY_MSG);
    }

    @Test
    public void test() throws NoSuchAlgorithmException {
        BitcoinMsg creator = new BitcoinMsg(TestAmatInfo.newNamespace());

        // BitcoinMsgStream
        logger.debug("InputStream -> BitcoinMsgStream");
        Prospective<BitcoinMsgStream> msgStream = creator.newSealed(msgInputStream, BitcoinConfig.newConfigWithUnknonwVersion());
        assertTrue(msgStream.isPresent());

        // BitcoinMsgRO
        logger.debug("BitcoinMsgStream -> BitcoinMsgRO");
        Prospective<BitcoinMsgRO> msgRO = msgStream.get().msgRO();
        assertTrue(msgRO.isPresent());
        assertEquals(MSG_MAGIC, msgRO.get().asMagicName().get());
        assertEquals(MSG_COMMAND, msgRO.get().asCommandName().get());
        assertTrue(msgRO.get().isGetData());

        Optional<BitcoinMsgGetDataRO> msgGetDataRO = msgRO.get().asGetData();
        assertTrue(msgGetDataRO.isPresent());
        assertEquals(MSG_GETDATA_COUNT, msgGetDataRO.get().size());
        assertEquals(MSG_GETDATA_INV1, msgGetDataRO.get().get(0));
        assertEquals(MSG_GETDATA_INV2, msgGetDataRO.get().get(1));

        logger.debug("BitcoinMsgRO -> BitcoinMsgRW");
        Prospective<BitcoinMsgRW> msgRW = msgRO.get().msgRW();
        assertTrue(msgRW.isPresent());
        assertEquals(msgRO.get().asMagicName(), msgRW.get().asMagicName());
        assertEquals(msgRO.get().asCommandName(), msgRW.get().asCommandName());
        assertTrue(msgRW.get().isGetData());

        Optional<BitcoinMsgGetDataRW> msgGetDataRW = msgRW.get().asGetData();
        assertTrue(msgGetDataRW.isPresent());
        assertEquals(msgGetDataRO.get(), msgGetDataRW.get());

        logger.debug("BitcoinMsgRW -> BitcoinMsgStream");
        Prospective<BitcoinMsgStream> msgStream2 = msgRW.get().msgStream();
        assertTrue(msgStream2.isPresent());
        assertEquals(msgStream.get(), msgStream2.get());
    }

}
