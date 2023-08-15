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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import weliyek.amat2.protocol.field.BytestreamBuilder;
import weliyek.amat2.protocol.field.DeserializedBuilder;
import weliyek.amat2.protocol.field.Field;
import weliyek.amat2.protocol.field.MessageContext;
import weliyek.amat2.protocol.field.ReadDataBuildManager;
import weliyek.amat2.protocol.field.WriteDataBuildManager;
import weliyek.amat2.protocol.field.aggregator.GroupAggregratorField;
import weliyek.amat2.protocol.field.basic.SimpleSubfield;
import weliyek.amat2.protocol.field.basic.UnsignedByteField;
import weliyek.amat2.protocol.field.data.BasicDataBuildersSupplier;
import weliyek.amat2.protocol.field.data.ReadData;
import weliyek.amat2.protocol.field.data.ReadDataBuilder;
import weliyek.amat2.protocol.field.data.SizingReadData;
import weliyek.amat2.protocol.field.data.SizingWriteData;
import weliyek.amat2.protocol.field.data.VariableSizingReadDataBuilder;
import weliyek.amat2.protocol.field.data.VariableSizingWriteDataBuilder;
import weliyek.amat2.protocol.field.data.WriteData;
import weliyek.amat2.protocol.field.data.WriteDataBuilder;

public class BitcoinTxField<C extends MessageContext>
        extends GroupAggregratorField
                        <C,
                         BitcoinTx,
                         BitcoinTx,
                         DeserializedBuilder
                             <BitcoinTx,
                              ReadData<C, BitcoinTx>,
                              BitcoinTxField<C>>,
                         WriteData<C, BitcoinTx>,
                         ReadData<C, BitcoinTx>,
                         BitcoinTxField<C>>
{

    public final static int WITNESS_MARKER = 0;

    public final static int WITNESS_FLAG = 1;

    private final UnsignedByteField<C> segwitMarker;
    private final UnsignedByteField<C> segwitFlag;
    private final BitcoinTxInListField<C> txinList;
    private final BitcoinTxOutListField<C> txoutList;
    private final BitcoinTxSegwitField<C> witnesses;

    public BitcoinTxField(
            Supplier<DeserializedBuilder<BitcoinTx, ReadData<C, BitcoinTx>, BitcoinTxField<C>>> deserializedBuilderSupplier,
            Collection<Field<C, ?, ?>> requiredFields) {
        super(
                "TX",
                BitcoinTx.class,
                BitcoinTx.class,
                deserializedBuilderSupplier,
                new TxBufferedBytestream(),
                requiredFields);
        this.segwitMarker = buildSegwitMarkerField();
        this.segwitFlag = buildSegwitFlagField();
        this.txinList = buildTxInListField();
        this.txoutList = buildTxOutListField();
        this.witnesses = buildTxInSegwitListField();
    }

    public UnsignedByteField<C> segwitMarker() {
        return segwitMarker;
    }

    public UnsignedByteField<C> segwitFlag() {
        return segwitFlag;
    }

    public BitcoinTxInListField<C> txinList() {
        return txinList;
    }

    private UnsignedByteField<C> buildSegwitMarkerField() {
        WriteDataBuildManager<C,
                              WriteData<C, BitcoinTx>,
                              Byte,
                              WriteDataBuilder<C, Byte, WriteData<C, Byte>, UnsignedByteField<C>>,
                              BitcoinTxField<C>>
                onWriteDataBuild = (su,w,swb,f) -> {
                    BitcoinTx tx = w.serialized().get();
                    swb.setEnabledFlag(tx.hasSegregatedWitnesses());
                };
        ReadDataBuildManager<C,
                             ReadData<C, BitcoinTx>,
                             Number,
                             ReadDataBuilder<C, Number, ReadData<C, Number>, UnsignedByteField<C>>,
                             BitcoinTxField<C>>
                onReadDataBuild = (r,srb,f) -> {};
        UnsignedByteField<C> marker = new UnsignedByteField<>("SEGWIT_MARKER");
        addNonStandardSubfieldInfo(new SimpleSubfield<>(
                marker,
                (tx,c) -> Byte.valueOf((byte)0),    // <-- Marker value of 0
                onWriteDataBuild,
                onReadDataBuild,
                new BasicDataBuildersSupplier<>()));
        return marker;
    }

    private UnsignedByteField<C> buildSegwitFlagField() {
        WriteDataBuildManager<C,
                              WriteData<C, BitcoinTx>,
                              Byte,
                              WriteDataBuilder<C, Byte, WriteData<C, Byte>, UnsignedByteField<C>>,
                              BitcoinTxField<C>>
            onWriteDataBuild = (su,w,swb,f) -> {
                BitcoinTx tx = w.serialized().get();
                swb.setEnabledFlag(tx.hasSegregatedWitnesses());
            };
       ReadDataBuildManager<C,
                            ReadData<C, BitcoinTx>,
                            Number,
                            ReadDataBuilder<C, Number, ReadData<C, Number>, UnsignedByteField<C>>,
                            BitcoinTxField<C>>
           onReadDataBuild = (r,srb,f) -> {};
       UnsignedByteField<C> flag = new UnsignedByteField<>("SEGWIT_FLAG");
       addNonStandardSubfieldInfo(new SimpleSubfield<>(
               flag,
               (tx,c) -> Byte.valueOf((byte)1),     // <-- Flag value of 1
               onWriteDataBuild,
               onReadDataBuild,
               new BasicDataBuildersSupplier<>()));
       return flag;
    }

    private BitcoinTxInListField<C> buildTxInListField() {
        WriteDataBuildManager<C,
                              WriteData<C, BitcoinTx>,
                              List<BitcoinTxIn>,
                              WriteDataBuilder<C, List<BitcoinTxIn>, WriteData<C, List<BitcoinTxIn>>, BitcoinTxInListField<C>>,
                              BitcoinTxField<C>>
            onWriteDataBuild = (su,w,swb,f) -> {
                BitcoinTx tx = w.serialized().get();
                swb.setEnabledFlag(tx.hasSegregatedWitnesses());
            };
        BitcoinTxInListField<C> a = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                            new BitcoinTxInListField<>(),
                            (tx,c) -> tx.txInList())).subfield();
        return a;
    }

    private BitcoinTxOutListField<C> buildTxOutListField() {
        BitcoinTxOutListField<C> a = addNonStandardSubfieldInfo(SimpleSubfield.withStandardFieldData(
                            new BitcoinTxOutListField<>(),
                            (tx,c) -> tx.txOutList())).subfield();
        return a;
    }

    private BitcoinTxSegwitField<C> buildTxInSegwitListField() {
        BitcoinTxSegwitField<C> segwit = new BitcoinTxSegwitField<C>();

        WriteDataBuildManager
            <C,
             WriteData<C, BitcoinTx>,
             List<List<BitcoinScript>>,
             VariableSizingWriteDataBuilder
                 <C,
                  List<List<BitcoinScript>>,
                  SizingWriteData<C, List<List<BitcoinScript>>>,
                  BitcoinTxSegwitField<C>>,
             BitcoinTxField<C>>
        onWriteDataBuild = (su,w,swb,f) -> {
            BitcoinTx txOnWrite = w.serialized().get();
            if (txOnWrite.hasSegregatedWitnesses()) {
                swb.enableField();
                swb.setSize(txOnWrite.txInList().size());
            } else {
                swb.disableField();
            }
        };

        ReadDataBuildManager
                <C,
                 ReadData<C, BitcoinTx>,
                 List<List<BitcoinScript>>,
                 VariableSizingReadDataBuilder
                     <C,
                      List<List<BitcoinScript>>,
                      SizingReadData<C, List<List<BitcoinScript>>>,
                      BitcoinTxSegwitField<C>>,
                 BitcoinTxField<C>>
        onReadDataBuild = (r, srb, f) -> {
            if (withSegwitOnRead(r,f)) {
                srb.enableField();
                List<BitcoinTxIn> txInList = r.getLatestDeserializedOrThrow(f.txinList);
                srb.setSize(txInList.size());
            } else {
                srb.disableField();
            }
        };

        SimpleSubfield.withVariableSizingFieldData(
                segwit,
                (tx,c) -> witnesses(tx),
                onWriteDataBuild,
                onReadDataBuild);
        return null;
    }

    static List<List<BitcoinScript>> witnesses(BitcoinTx tx) {
        List<List<BitcoinScript>> listOfList = new LinkedList<>();
        for (BitcoinTxIn txIn : tx.txInList()) {
            listOfList.add(txIn.segregatedWitness());
        }
        return listOfList;
    }

    private static <C extends MessageContext>
    boolean withSegwitOnRead(
            ReadData<C, BitcoinTx> readData,
            BitcoinTxField<C> txField) {
        byte marker = readData.getLatestDeserializedOrThrow(txField.segwitMarker).byteValue();
        byte flag = readData.getLatestDeserializedOrThrow(txField.segwitFlag).byteValue();
        if ((WITNESS_MARKER == marker) && (WITNESS_FLAG == flag))
            return true;
        else
            return false;
    }

    @Override
    protected BitcoinTxField<C> getThis() {
        return this;
    }

    static class TxBufferedBytestream implements BytestreamBuilder
    {

        @Override
        public OutputStream newOutputBytestream(OutputStream output) {
            return output;
        }

        @Override
        public InputStream newInputBytestream(InputStream input) {
            if ( ! input.markSupported()) {
                input = new BufferedInputStream(input, 2);
            }
            return input;
        }

    }

    /*
    public static final Logger logger = LoggerFactory.getLogger(BitcoinProtocolFieldTx.class);

    public final static int WITNESS_MARKER = 0;

    public final static int WITNESS_FLAG = 1;

    public final AmatProtocolFieldUnsignedInt version;

    public final BitcoinProtocolFieldTxInList txInList;

    public final BitcoinProtocolFieldTxOutList txOutList;

    public final AmatProtocolFieldUnsignedInt lockTime;

    private final AmatProtocolMessageFieldByte tmpMarker;

    private final AmatProtocolMessageFieldByte tmpFlag;

    protected BitcoinProtocolFieldTx(AmatNameComposite namespace) {
        super(namespace.newDescendant(BitcoinProtocolName.TX));
        version = new AmatProtocolFieldUnsignedInt(namespace().newDescendant(BitcoinProtocolName.TX_VERSION), ENDIANNESS.LITTLE);
        txInList = new BitcoinProtocolFieldTxInList(namespace());
        txOutList = new BitcoinProtocolFieldTxOutList(namespace());
        lockTime = new AmatProtocolFieldUnsignedInt(namespace().newDescendant(BitcoinProtocolName.TX_LOCKTIME), ENDIANNESS.LITTLE);
        tmpMarker = new AmatProtocolMessageFieldByte(namespace().newDescendant(BitcoinProtocolName.TX_SEGWIT_INFO_MARKER));
        tmpFlag = new AmatProtocolMessageFieldByte(namespace().newDescendant(BitcoinProtocolName.TX_SEGWIT_INFO_FLAG));
    }

    @Override
    public void readFrom(InputStream in, BitcoinConfig config) {
        version.readFrom(in, config);
        final boolean containsWitness = areWitnessPresent(in, config);
        txInList.readFrom(in, config);
        txOutList.readFrom(in, config);
        if (containsWitness) {
            for (BitcoinTxInField txIn : txInList.container()) {
                txIn.witness.readFrom(in, config);
            }
        }
        lockTime.readFrom(in, config);
    }

    private boolean areWitnessPresent(InputStream in, BitcoinConfig config) {
        if ( ! in.markSupported()) {
            in = new BufferedInputStream(in, 2);
        }
        // https://bitcoincore.org/en/segwit_wallet_dev/
        in.mark(2);
        tmpMarker.readFrom(in, config);
        int marker = tmpMarker.value().get().basicByte();
        tmpMarker.reset();
        tmpFlag.readFrom(in, config);
        int flag = tmpFlag.value().get().basicByte();
        tmpFlag.reset();
        if ((WITNESS_MARKER == marker) && (WITNESS_FLAG == flag)) {
            if (logger.isDebugEnabled()) logger.debug("{} contains witness info", namespace());
            return true;
        }
        try {
            in.reset();
        } catch (IOException e) {
            throw new RuntimeException("Unexpected IOException while attempting to read witness info", e);
        }
        if (logger.isDebugEnabled()) logger.debug("{} does NOT contains witness info", namespace());
        return true;
    }

    @Override
    public void writeTo(OutputStream out, BitcoinConfig config) {
        version.writeTo(out, config);
        boolean containsWitness = writeMarkAndFlagIfWitnessArePresent(out, config);
        txInList.writeTo(out, config);
        txOutList.writeTo(out, config);
        if (containsWitness) {
            for (BitcoinTxInField txIn : txInList.container()) {
                txIn.witness.writeTo(out, config);
            }
        }
        lockTime.writeTo(out, config);
    }

    private boolean writeMarkAndFlagIfWitnessArePresent(OutputStream out, BitcoinConfig config) {
        for (BitcoinTxInField txIn : txInList.container()) {
            if ( ! txIn.witness.container().isEmpty()) {
                tmpMarker.setIntValue((byte) WITNESS_MARKER);
                tmpMarker.writeTo(out, config);
                tmpMarker.reset();
                tmpFlag.setIntValue((byte) WITNESS_FLAG);
                tmpFlag.writeTo(out, config);
                tmpFlag.reset();
                return true;
            }
        }
        return false;
    }

    @Override
    public void reset() {
        version.reset();
        txInList.reset();
        txOutList.reset();
        lockTime.reset();
    }
    */

}
