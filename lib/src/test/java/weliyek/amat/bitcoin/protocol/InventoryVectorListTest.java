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
package weliyek.amat.bitcoin.protocol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.util.Arrays;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ricvgx.builder.ByteInputStream;
import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.namespace.AmatNamespaceFactoryWithSimpleCache;
import weliyek.amat.base.protocol.base.AmatProtocolOperation;
import weliyek.amat.base.protocol.base.AmatProtocolUnexpectedCountException;
import weliyek.amat.bitcoin.protocol.message.TestUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InventoryVectorListTest
{

    public static final Logger logger = LoggerFactory.getLogger(InventoryVectorListTest.class);

    public final static byte[] ARRAY_INVENTORY_LIST_SIZE = new byte[] {
            0x02
    };

    public final static byte[] ARRAY_INVENTORY_LIST_INV1_TYPE = new byte[] {
            0x01, 0x00, 0x00, 0x00
    };

    public final static byte[] ARRAY_INVENTORY_LIST_INV1_HASH = new byte[] {
            (byte)0xde, (byte)0x55, (byte)0xff, (byte)0xd7, (byte)0x09, (byte)0xac, (byte)0x1f, (byte)0x5d,
            (byte)0xc5, (byte)0x09, (byte)0xa0, (byte)0x92, (byte)0x5d, (byte)0x0b, (byte)0x1f, (byte)0xc4,
            (byte)0x42, (byte)0xca, (byte)0x03, (byte)0x4f, (byte)0x22, (byte)0x47, (byte)0x32, (byte)0xe4,
            (byte)0x29, (byte)0x08, (byte)0x1d, (byte)0xa1, (byte)0xb6, (byte)0x21, (byte)0xf5, (byte)0x5a
    };

    public final static byte[] ARRAY_INVENTORY_LIST_INV2_TYPE = new byte[] {
            0x01, 0x00, 0x00, 0x00
    };

    public final static byte[] ARRAY_INVENTORY_LIST_INV2_HASH = new byte[] {
            (byte)0x91, (byte)0xd3, (byte)0x6d, (byte)0x99, (byte)0x70, (byte)0x37, (byte)0xe0, (byte)0x80,
            (byte)0x18, (byte)0x26, (byte)0x29, (byte)0x78, (byte)0x76, (byte)0x6f, (byte)0x24, (byte)0xb8,
            (byte)0xa0, (byte)0x55, (byte)0xaa, (byte)0xf1, (byte)0xd8, (byte)0x72, (byte)0xe9, (byte)0x4a,
            (byte)0xe8, (byte)0x5e, (byte)0x98, (byte)0x17, (byte)0xb2, (byte)0xc6, (byte)0x8d, (byte)0xc7
    };

    public final static byte[] ARRAY_INVENTORY_LIST;

    /*

    public static final int                  INVENTORY_COUNT;
    public static final BitcoinInventoryVector.Type INVENTORY_INV1_TYPE;
    public static final BitcoinHash          INVENTORY_INV1_HASH;
    public static final BitcoinInventoryVector      INVENTORY_INV1;
    public static final BitcoinInventoryVector.Type INVENTORY_INV2_TYPE;
    public static final BitcoinHash          INVENTORY_INV2_HASH;
    public static final BitcoinInventoryVector      INVENTORY_INV2;
    */

    @Rule
    public ExpectedException exception = ExpectedException.none();

    static {
        ARRAY_INVENTORY_LIST = TestUtils.joinArrays(ARRAY_INVENTORY_LIST_SIZE,
                                                    ARRAY_INVENTORY_LIST_INV1_TYPE,
                                                    ARRAY_INVENTORY_LIST_INV1_HASH,
                                                    ARRAY_INVENTORY_LIST_INV2_TYPE,
                                                    ARRAY_INVENTORY_LIST_INV2_HASH);
        /*
        INVENTORY_COUNT     = TestUtils.toVarInt(ARRAY_INVENTORY_LIST_SIZE, TestAmatInfo.newNamespace()).intValue();
        INVENTORY_INV1_TYPE = BitcoinInventoryVector.Type.nullableFromInt(TestUtils.toSignedInt(ARRAY_INVENTORY_LIST_INV1_TYPE));
        INVENTORY_INV1_HASH = TestUtils.toBitcoinHash(ARRAY_INVENTORY_LIST_INV1_HASH, TestAmatInfo.newNamespace());
        INVENTORY_INV1      = TestUtils.toInventory(ARRAY_INVENTORY_LIST_INV1_TYPE, ARRAY_INVENTORY_LIST_INV1_HASH, TestAmatInfo.newNamespace());
        INVENTORY_INV2_TYPE = BitcoinInventoryVector.Type.nullableFromInt(TestUtils.toSignedInt(ARRAY_INVENTORY_LIST_INV2_TYPE));
        INVENTORY_INV2_HASH = TestUtils.toBitcoinHash(ARRAY_INVENTORY_LIST_INV2_HASH, TestAmatInfo.newNamespace());
        INVENTORY_INV2      = TestUtils.toInventory(ARRAY_INVENTORY_LIST_INV2_TYPE, ARRAY_INVENTORY_LIST_INV2_HASH, TestAmatInfo.newNamespace());
        */
    }

    public static AmatNamespaceFactoryWithSimpleCache NAMESPACE_FACTORY;

    public AmatNamespace sizeNamespace;
    public AmatNamespace elementNamespace;
    public AmatNamespace elementTypeNamespace;
    public AmatNamespace elementHashNamespace;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        NAMESPACE_FACTORY = new AmatNamespaceFactoryWithSimpleCache();
    }

    @Before
    public void setUp() throws Exception {
        final AmatNamespace listNamespace = NAMESPACE_FACTORY.newNamespace(BitcoinProtocolName.INV_VEC_LIST);
        sizeNamespace = listNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC_LIST_SIZE);
        elementNamespace = listNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC_LIST_ELEMENT);
        final AmatNamespace invVecElemNamespace = elementNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC);
        elementTypeNamespace = invVecElemNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC_TYPE);
        elementHashNamespace = invVecElemNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC_HASH);
    }

    @Test
    public void test01_EmptyArray() {
        logger.info("=== test01_EmptyArray ===");
        final InputStream emptyIn = new ByteInputStream(new byte[0], 0);
        try {
            BitcoinInventoryVectorList.readFrom(emptyIn,
                                                sizeNamespace,
                                                elementTypeNamespace,
                                                elementHashNamespace);
        } catch (AmatProtocolUnexpectedCountException exception) {
            assertNotNull(exception);
            assertNull(exception.getCause());
            assertEquals(1, exception.expected);
            assertEquals(0, exception.count);
            assertEquals(AmatProtocolOperation.IO_READ, exception.operation);
        }
    }

    @Test
    public void test02_IncompleteInv1TypeWith1ByteOutOf4() {
        logger.info("=== test02_IncompleteInv1TypeWith1ByteOutOf4 ===");
        final byte[] partialTypeByteArray = Arrays.copyOf(ARRAY_INVENTORY_LIST, 2);
        final InputStream partialIn = TestUtils.toLittleEndian(partialTypeByteArray);
        try {
            BitcoinInventoryVectorList.readFrom(partialIn,
                                                sizeNamespace,
                                                elementTypeNamespace,
                                                elementHashNamespace);
        } catch (AmatProtocolUnexpectedCountException exception) {
            assertNotNull(exception);
            assertNull(exception.getCause());
            assertEquals(4, exception.expected);
            assertEquals(1, exception.count);
            assertEquals(AmatProtocolOperation.IO_READ, exception.operation);
        }
    }

    @Test
    public void test03_IncompleteInv1TypeWith3ByteOutOf4() {
        logger.info("=== test03_IncompleteInv1TypeWith3ByteOutOf4 ===");
        final byte[] partialTypeByteArray = Arrays.copyOf(ARRAY_INVENTORY_LIST, 4);
        final InputStream partialIn = TestUtils.toLittleEndian(partialTypeByteArray);
        try {
            BitcoinInventoryVectorList.readFrom(partialIn,
                                                sizeNamespace,
                                                elementTypeNamespace,
                                                elementHashNamespace);
        } catch (AmatProtocolUnexpectedCountException exception) {
            assertNotNull(exception);
            assertNull(exception.getCause());
            assertEquals(4, exception.expected);
            assertEquals(3, exception.count);
        }
    }

    @Test
    public void test04_IncompleteInv1HashWith0Bytes() {
        logger.info("=== test04_IncompleteInv1HashWith0Bytes ===");
        final byte[] partialTypeByteArray = Arrays.copyOf(ARRAY_INVENTORY_LIST, 5);
        final InputStream partialIn = TestUtils.toLittleEndian(partialTypeByteArray);
        try {
            BitcoinInventoryVectorList.readFrom(partialIn,
                                                sizeNamespace,
                                                elementTypeNamespace,
                                                elementHashNamespace);
        } catch (AmatProtocolUnexpectedCountException exception) {
            assertNotNull(exception);
            assertNull(exception.getCause());
            assertEquals(32, exception.expected);
            assertEquals(0, exception.count);
        }
    }

    @Test
    public void test05_InventoryVector() {
        logger.info("=== test05_InventoryVector ===");
        InputStream invVecIn = TestUtils.toLittleEndian(ARRAY_INVENTORY_LIST);
        final BitcoinInventoryVectorList list = BitcoinInventoryVectorList.readFrom(invVecIn,
                                                                                    sizeNamespace,
                                                                                    elementTypeNamespace,
                                                                                    elementHashNamespace);
        assertEquals(2, list.size());
        /*
        BitcoinInventoryVector inv1 = invVec.get(0);
        assertEquals(INVENTORY_INV1, inv1);
        BitcoinInventoryVector inv2 = invVec.get(1);
        assertEquals(INVENTORY_INV2, inv2);
        */
    }

}
