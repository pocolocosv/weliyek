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
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ricvgx.builder.ByteInputStream;
import weliyek.amat.base.AmatNameComposite;
import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.TestAmatName;
import weliyek.amat.bitcoin.protocol.message.TestUtils;
import weliyek.ketza.util.KetzaByteOutputStream;

public class BitcoinProtocolFieldInvVecListTest
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinProtocolFieldInvVecListTest.class);

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

    static {
        ARRAY_INVENTORY_LIST = TestUtils.joinArrays(ARRAY_INVENTORY_LIST_SIZE,
                                                    ARRAY_INVENTORY_LIST_INV1_TYPE,
                                                    ARRAY_INVENTORY_LIST_INV1_HASH,
                                                    ARRAY_INVENTORY_LIST_INV2_TYPE,
                                                    ARRAY_INVENTORY_LIST_INV2_HASH);
    }

    public BitcoinConfig CONFIG = mock(BitcoinConfig.class);

    public final AmatNameComposite baseNamespace = new AmatNamespace(TestAmatName.BASE_AMAT);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        final AmatNameComposite invVecListName = baseNamespace.newDescendant(TestAmatName.FIELD_A);
        final BitcoinProtocolFieldInvVecList invVecList1 = new BitcoinProtocolFieldInvVecList(invVecListName);

        final ByteInputStream in = new ByteInputStream(ARRAY_INVENTORY_LIST, ARRAY_INVENTORY_LIST.length);
        invVecList1.readFrom(in, CONFIG);
        assertEquals(2, invVecList1.primitivesContainerField().size());

        final KetzaByteOutputStream out = new KetzaByteOutputStream();

        invVecList1.writeTo(out, CONFIG);

        final BitcoinProtocolFieldInvVecList invVecList2 = new BitcoinProtocolFieldInvVecList(baseNamespace.newDescendant(TestAmatName.FIELD_B));

        invVecList2.readFrom(out.inputStream(), CONFIG);

        assertEquals(invVecList1, invVecList2);
    }

}
