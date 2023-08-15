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

public class BtcProtoFieldHashTest
{

    /*
    public static final Logger logger = LoggerFactory.getLogger(BtcProtoFieldHashTest.class);

    private static BtcProtoFieldHash<BtcProtoContext>  FIELD_HASH;

    private static Protocol<BtcProtoContext,
                            BtcProtoFieldHash<BtcProtoContext>,
                            BitcoinHash,
                            BitcoinHash,
                            WriteData<BtcProtoContext, BitcoinHash>,
                            ReadData<BtcProtoContext, BitcoinHash>,
                            WriteDataBuilder
                                <BtcProtoContext,
                                 BitcoinHash,
                                 WriteData<BtcProtoContext, BitcoinHash>,
                                 BtcProtoFieldHash<BtcProtoContext>>,
                            ReadDataBuilder
                                <BtcProtoContext,
                                 BitcoinHash,
                                 ReadData<BtcProtoContext, BitcoinHash>,
                                 BtcProtoFieldHash<BtcProtoContext>>> PROTO;


    public static final byte[] BIT_ARRAY_256 = new byte[] {
            (byte)0xde, (byte)0x55, (byte)0xff, (byte)0xd7, (byte)0x09, (byte)0xac, (byte)0x1f, (byte)0x5d,
            (byte)0xc5, (byte)0x09, (byte)0xa0, (byte)0x92, (byte)0x5d, (byte)0x0b, (byte)0x1f, (byte)0xc4,
            (byte)0x42, (byte)0xca, (byte)0x03, (byte)0x4f, (byte)0x22, (byte)0x47, (byte)0x32, (byte)0xe4,
            (byte)0x29, (byte)0x08, (byte)0x1d, (byte)0xa1, (byte)0xb6, (byte)0x21, (byte)0xf5, (byte)0x5a
    };

    public BitcoinConfig CONFIG = mock(BitcoinConfig.class);

    KetzaByteOutputStream buffer;
    LittleEndianDataOutputStream out;
    AmatNamespace namespace;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        FIELD_HASH = new BtcProtoFieldHash<>(Collections.emptyList());
        PROTO = Protocol.withMainField(FIELD_HASH, new BasicDataBuildersSupplier<>());
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void test() {
        logger.info("=== Reading 256 bits into " + BitcoinHash.class.getSimpleName() + " ===");
        ByteArrayInputStream input = new ByteArrayInputStream(BIT_ARRAY_256);
        ReadOperationDataStore<BtcProtoContext> readInfo = PROTO.read(mock(BtcProtoContext.class), input, (c,rb) -> {});
        assertNotNull(readInfo);
        ReadOperationSequence<BtcProtoContext, BitcoinHash> hashList = readInfo.getLastReadSequenceFor(FIELD_HASH);
        assertNotNull(hashList);
        Optional<ReadData<BtcProtoContext, BitcoinHash>> readHashInfo = hashList.getLatestOpResult();
        assertNotNull(readHashInfo);
        assertTrue(readHashInfo.isPresent());
        assertTrue(readHashInfo.get().deserialized().isPresent());
        BitcoinHash readHash1 = readHashInfo.get().deserialized().get();

        logger.info("=== Writing " + BitcoinHash.class.getSimpleName() + " to an OutputStream ===");
        KetzaByteOutputStream output = new KetzaByteOutputStream();
        PROTO.write(mock(BtcProtoContext.class), readHash1, output, (c,wb) -> {});
        assertEquals(32, output.size());

        logger.info("=== Reading " + BitcoinHash.class.getSimpleName() + " back from OutputStream ===");
        ReadOperationDataStore<BtcProtoContext> readInfo2 = PROTO.read(mock(BtcProtoContext.class), output.inputStream(), (c,rb) -> {});
        BitcoinHash readHash2 = readInfo2.getLatestDeserializedOrThrow(FIELD_HASH);
        assertEquals(readHash1, readHash2);
    }
    */

}
