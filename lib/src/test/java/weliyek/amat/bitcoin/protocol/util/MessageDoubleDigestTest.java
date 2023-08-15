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
package weliyek.amat.bitcoin.protocol.util;

import static org.junit.Assert.assertTrue;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class MessageDoubleDigestTest
{

    byte[] data = new byte[] {
            (byte)0x62, (byte)0xea, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x11, (byte)0xb2, (byte)0xd0, (byte)0x50,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xff, (byte)0xff,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x01, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0xff, (byte)0xff, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0x00,
            (byte)0x3b, (byte)0x2e, (byte)0xb3, (byte)0x5d, (byte)0x8c, (byte)0xe6, (byte)0x17, (byte)0x65,
            (byte)0x0f, (byte)0x2f, (byte)0x53, (byte)0x61, (byte)0x74, (byte)0x6f, (byte)0x73, (byte)0x68,
            (byte)0x69, (byte)0x3a, (byte)0x30, (byte)0x2e, (byte)0x37, (byte)0x2e, (byte)0x32, (byte)0x2f,
            (byte)0xc0, (byte)0x3e, (byte)0x03, (byte)0x00
    };

    byte[] expectedDoubleSha256 = new byte[] {
            (byte)0x3b, (byte)0x64, (byte)0x8d, (byte)0x5a, (byte)0x32, (byte)0xc9, (byte)0x99, (byte)0x5b,
            (byte)0x5a, (byte)0xe1, (byte)0xd8, (byte)0x4b, (byte)0x37, (byte)0x35, (byte)0x68, (byte)0x9e,
            (byte)0x67, (byte)0x88, (byte)0x54, (byte)0xad, (byte)0x0c, (byte)0xb3, (byte)0xe2, (byte)0xf6,
            (byte)0x53, (byte)0x62, (byte)0x9d, (byte)0x74, (byte)0x11, (byte)0x27, (byte)0x2f, (byte)0xe1
    };

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() throws NoSuchAlgorithmException {
        final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        final MessageDoubleDigest doubleSha256 = new MessageDoubleDigest(sha256);
        final byte[] hash = doubleSha256.digest(data);
        assertTrue(Arrays.equals(expectedDoubleSha256, hash));
    }

}
