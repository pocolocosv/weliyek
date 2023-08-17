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

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.EnumSet;

import com.google.common.io.LittleEndianDataInputStream;

import ricvgx.builder.ByteInputStream;
import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.bitcoin.protocol.BitcoinNodeServiceFlag;
import weliyek.amat.bitcoin.protocol.Complexity;
import weliyek.amat.bitcoin.protocol.VarStr;
import weliyek.bitcoin.WkBitcoinCommandName;
import weliyek.bitcoin.WkBitcoinHash;
import weliyek.bitcoin.BitcoinInventoryVector;
import weliyek.bitcoin.BitcoinMessageMagicName;
import weliyek.bitcoin.BitcoinNetAddr;
import weliyek.bitcoin.BitcoinProtocolIOVarInt;
import weliyek.bitcoin.BitcoinProtocolScriptAbstract;

public class TestUtils
{

    public static LittleEndianDataInputStream toLittleEndian(byte[] array) {
        ByteInputStream in = new ByteInputStream(array, array.length);
        LittleEndianDataInputStream littleEndianIn = new LittleEndianDataInputStream(in);
        return littleEndianIn;
    }

    public static int toSignedInt(byte[] array) {
        LittleEndianDataInputStream in = toLittleEndian(array);
        int val = 0;
        try {
            val = in.readInt();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return val;
    }

    public static long toUnsignedInt(byte[] array) {
        LittleEndianDataInputStream in = toLittleEndian(array);
        long val = 0;
        try {
            val = Integer.toUnsignedLong(in.readInt());
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return val;
    }

    public static long toLong(byte[] array) {
        LittleEndianDataInputStream in = toLittleEndian(array);
        long val = 0;
        try {
            val = in.readLong();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return val;
    }

    public static WkBitcoinHash toBitcoinHash(byte[] array, AmatNamespace namespace) {
        WkBitcoinHash hash = WkBitcoinHash.readFrom(TestUtils.toLittleEndian(array), namespace);
        return hash;
    }

    public static Complexity toComplexity(byte[] array, final AmatNamespace namespace) {
        Complexity c = Complexity.readFrom(TestUtils.toLittleEndian(array), namespace);
        return c;
    }

    public static BitcoinProtocolIOVarInt toVarInt(byte[] array, AmatNamespace namespace) {
        BitcoinProtocolIOVarInt var = BitcoinProtocolIOVarInt.readFrom(TestUtils.toLittleEndian(array), namespace);
        return var;
    }

    public static String toVarStr(byte[] array, AmatNamespace namespace) {
        String str = VarStr.read(TestUtils.toLittleEndian(array), namespace);
        return str;
    }

    public static boolean compareUpToShortestArrays(byte[] a1, byte[] a2) {
        int minLen = Math.min(a1.length, a2.length);
        for(int i = 0; i < minLen; i++) {
            if (a1[i] != a2[i]) {
                return false;
            }
        }
        return true;
    }

    public static BitcoinMessageMagicName toMagic(byte[] array) {
        int val;
        try {
            val = TestUtils.toLittleEndian(array).readInt();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        BitcoinMessageMagicName magic = BitcoinMessageMagicName.convertFromIntOrNull(val).get();
        return magic;
    }

    public static WkBitcoinCommandName toCommand(byte[] array) {
        final String str = WkBitcoinCommandName.convertCanonicalBufferToString(array);
        return WkBitcoinCommandName.fromString(str).get();
    }

    public static EnumSet<BitcoinNodeServiceFlag> toServices(byte[] array) {
        long l;
        try {
            l = TestUtils.toLittleEndian(array).readLong();
        } catch (IOException e) {
            throw new RuntimeException();
        }
        EnumSet<BitcoinNodeServiceFlag> services = BitcoinNodeServiceFlag.enumSetFromLong(l);
        return services;
    }

    public static BitcoinProtocolScriptAbstract toScript(byte[] scriptSize, byte[] script, AmatNamespace namespace) {
        byte[] array = new byte[ scriptSize.length + script.length];
        System.arraycopy(scriptSize, 0, array, 0, scriptSize.length);
        System.arraycopy(script, 0, array, scriptSize.length, script.length);
        return BitcoinProtocolScriptAbstract.readFrom(TestUtils.toLittleEndian(array), namespace);
    }

    public static BitcoinNetAddr toNetAddr(int version, WkBitcoinCommandName command, byte[] array, AmatNamespace namespace) {
        LittleEndianDataInputStream in = toLittleEndian(array);
        BitcoinNetAddr netAddr = BitcoinNetAddr.readFrom(version, command, in, namespace);
        return netAddr;
    }

    public static BitcoinInventoryVector toInventory(byte[] typeArray, byte[] hashArray, AmatNamespace namespace) {
        byte[] invArray = joinArrays(typeArray, hashArray);
        return toInventory(invArray, namespace);
    }

    public static BitcoinInventoryVector toInventory(byte[] array, AmatNamespace namespace) {
        LittleEndianDataInputStream in = toLittleEndian(array);
        BitcoinInventoryVector inv = BitcoinInventoryVector.readFrom(in, namespace);
        return inv;
    }

    public static byte[] joinArrays(byte[]... arrays) {
        int totalLen = 0;
        for (byte[] b : arrays) {
            totalLen += b.length;
        }
        byte[] newBuf = new byte[totalLen];
        int currPos = 0;
        for (byte[] b : arrays) {
            System.arraycopy(b, 0, newBuf, currPos, b.length);
            currPos += b.length;
        }
        return newBuf;
    }

    public static byte[] getDouble256Hash(byte[] array) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
        byte[] hash = digest.digest(array);
        hash = digest.digest(hash);
        return hash;
    }

    public static byte[] getChecksumArray(byte[] array) {
        byte[] hash = getDouble256Hash(array);
        return Arrays.copyOf(hash, 4);
    }

    public static int getChecksum(byte[] array) {
        byte[] chksumArray = getChecksumArray(array);
        int b1 = chksumArray[0];
        int b2 = chksumArray[1];
        int b3 = chksumArray[2];
        int b4 = chksumArray[3];
        int chksum = (b4 << 24) | (b3 << 16) | (b2 << 8) | b1;
        return chksum;
    }

    public static byte[] intToArray(int i) {
        byte b1 = (byte) (0xFF & i);
        byte b2 = (byte) (0xFF & (i >> 8));
        byte b3 = (byte) (0xFF & (i >> 16));
        byte b4 = (byte) (0xFF & (i >> 24));
        byte[] buf = new byte[] { b1, b2, b3, b4 };
        return buf;
    }

}
