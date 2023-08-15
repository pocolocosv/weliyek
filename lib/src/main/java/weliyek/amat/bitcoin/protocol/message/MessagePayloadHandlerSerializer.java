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
package weliyek.amat.bitcoin.protocol.message;

import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.bitcoin.protocol.BitcoinProtocolMessageConfig;
import weliyek.ketza.base.composite.serializable.SerializableCoreKernelSerializer;

public class MessagePayloadHandlerSerializer
        implements SerializableCoreKernelSerializer<MessagePayloadHandlerRO,
                                                    MessagePayloadHandlerRW,
                                                    BitcoinProtocolMessageConfig>
{
    public static final Logger logger = LoggerFactory.getLogger(MessagePayloadHandlerSerializer.class);

    @Override
    public void deserializeKernel(MessagePayloadHandlerRO      roHandler,
                                  InputStream                  in,
                                  BitcoinProtocolMessageConfig config)
    {
        roHandler.commissionPayloadKernel(in, config);
    }

    @Override
    public void serializeKernel(MessagePayloadHandlerRW      rwHandler,
                                OutputStream                 out,
                                BitcoinProtocolMessageConfig config)
    {
        // TODO Auto-generated method stub

    }

    /*
    final BitcoinMsgCommissioners commissioners;

    final MessageDoubleDigest inDigest;

    final MessageDoubleDigest outDigest;

    MessagePayloadHandlerSerializer(BitcoinMsgCommissioners commissioners) {
        this.commissioners = commissioners;
        try {
            MessageDigest sha256Hasher = MessageDigest.getInstance("SHA-256");
            this.inDigest = new MessageDoubleDigest(sha256Hasher);
            this.outDigest = (MessageDoubleDigest) this.inDigest.clone();
        } catch (NoSuchAlgorithmException|CloneNotSupportedException e) {
            throw new AmatProtocolException(commissioners.messageNamespace, AmatProtocolOperation.NON_IO, e);
        }
    }

    // Write
    // =====

    public void serializeKernel(final MessagePayloadHandlerRW rwHandler,
                                final BitcoinConfig config,
                                final CountingOutputStream countingOut,
                                final AmatNamespace messageNamespace) {
        final AmatNamespace payloadNmspc = BitcoinProtocolName.MESSAGE_PAYLOAD.appendTo(messageNamespace);
        final KetzaByteOutputStream payloadBuffer = new KetzaByteOutputStream();
        outDigest.reset();
        final DigestOutputStream digestOut = new DigestOutputStream(payloadBuffer, outDigest);
        serializePayloadToBuffer(rwHandler, config, digestOut, payloadNmspc);
        writePayloadLen(countingOut, payloadNmspc, payloadBuffer);
        writeChecksum(countingOut, payloadNmspc, digestOut);
        writePayload(countingOut, payloadNmspc, payloadBuffer);
    }

    private void writePayloadLen(final CountingOutputStream countingOut,
                                 final AmatNamespace payloadNmspc,
                                 final KetzaByteOutputStream payloadBuffer)
    {
        final AmatNamespace lengthNmspc = BitcoinProtocolName.MESSAGE_PAYLOAD_LENGTH.appendTo(payloadNmspc);
        AmatProtocolUtil.writeIntAsLittleEndian(countingOut, payloadBuffer.size(), lengthNmspc);
    }

    private void writeChecksum(final CountingOutputStream countingOut,
                               final AmatNamespace payloadNmspc,
                               final DigestOutputStream digestOut)
    {
        final AmatNamespace checksumNmspc = BitcoinProtocolName.MESSAGE_PAYLOAD_CHECKSUM.appendTo(payloadNmspc);
                                      byte[] hash = digestOut.getMessageDigest().digest();
                                       int chksum = extractFirstFourBytes(hash);
                                                    AmatProtocolUtil.writeIntAsLittleEndian(countingOut, chksum, checksumNmspc);
    }

    private void serializePayloadToBuffer(final MessagePayloadHandlerRW rwHandler,
                              final BitcoinConfig config,
                              final OutputStream out,
                              final AmatNamespace payloadNmspc) {
        final Optional<BitcoinCommandName> commandName = rwHandler.asCommandName();
        if ( ! commandName.isPresent()) {
            throw new AmatProtocolException(payloadNmspc, AmatProtocolOperation.SERIALIZER_INVALID_VALUE);
        }
        final PayloadCoreKernelTools<?,?> tools = this.commissioners.tools(commandName.get());
        if (null == tools) {
            // Nothing to do for this message.
            return;
        }
        tools.serializeKernel(rwHandler.kernel(), config, out, payloadNmspc);
    }

    private void writePayload(final CountingOutputStream countingOut,
                              final AmatNamespace payloadNmspc,
                              final KetzaByteOutputStream payloadBuffer) {
        try {
            if (logger.isDebugEnabled())
                logger.debug("{} writing to OutputStream", payloadNmspc.toString());
            payloadBuffer.writeTo(countingOut);
        } catch (IOException e) {
            throw new AmatProtocolException(payloadNmspc, AmatProtocolOperation.IO_CTRL, e);
        }
    }


    // Read
    // ====

    public void deserializeKernel(final CountingInputStream countingIn,
                                  final BitcoinConfig config,
                                  final MessagePayloadHandlerRO roHandler,
                                  final AmatNamespace messageNmspc)
    {
        final AmatNamespace payloadNmspc = BitcoinProtocolName.MESSAGE_PAYLOAD.appendTo(messageNmspc);
                                  int payloadLen = readPayloadLength(countingIn, payloadNmspc);
                                                   readChecksum(countingIn, roHandler, payloadNmspc);
                                                   checkMessageSize(countingIn, payloadNmspc, payloadLen);
                final DigestInputStream digestIn = newDigestInputStream(countingIn, payloadLen);
                                                   roHandler.owner.payloadHandler.selectPayloadKernel();
                                                   readPayload(digestIn, config, roHandler, payloadNmspc);
                                                   computeChecksum(roHandler, digestIn.getMessageDigest(), payloadNmspc);
    }

    private int readPayloadLength(final CountingInputStream countingIn,
                                  final AmatNamespace messageNmspc)
    {
        final AmatNamespace lengthNmspc = BitcoinProtocolName.MESSAGE_PAYLOAD_LENGTH.appendTo(messageNmspc);
                                 int payloadLen = AmatProtocolUtil.readLittleEndianInt(countingIn, lengthNmspc);
        return payloadLen;
    }

    private void readChecksum(final CountingInputStream countingIn,
                              final MessagePayloadHandlerRO roHandler,
                              final AmatNamespace messageNmspc)
    {
        final AmatNamespace checksumNmspc = BitcoinProtocolName.MESSAGE_PAYLOAD_CHECKSUM.appendTo(messageNmspc);
                         roHandler.owner.checksum = AmatProtocolUtil.readLittleEndianInt(countingIn, checksumNmspc);
    }

    private DigestInputStream newDigestInputStream(final CountingInputStream countingIn,
                                                   int payloadLen)
    {
            BoundedInputStream boundedIn = new BoundedInputStream(countingIn, payloadLen);
                                           this.inDigest.reset();
        final DigestInputStream digestIn = new DigestInputStream(boundedIn, this.inDigest);
        return digestIn;
    }

    private void readPayload(final InputStream in,
                             final BitcoinConfig config,
                             final MessagePayloadHandlerRO roHandler,
                             final AmatNamespace messageNamespace) {
        final Optional<BitcoinCommandName> commandName = roHandler.asCommandName();
        if ( ! commandName.isPresent()) {
            // Stop deserializing if we don't know what is being deserialized.
            throw new AmatProtocolException(messageNamespace, AmatProtocolOperation.DESERIALIZER_INVALID_VALUE);
        }
        final PayloadCoreKernelTools<?,?> tools = this.commissioners.tools(commandName.get());
        if (null == tools) {
            // Nothing to do for this message.
            return;
        }
        tools.deserializeKernel(in, config, roHandler.kernel(), messageNamespace);
    }

    private void checkMessageSize(final CountingInputStream countingIn,
                                  final AmatNamespace messageNmspc,
                                  int payloadLen) {
        final long expectedMsgSize = countingIn.getCount() + payloadLen;
        if (expectedMsgSize > BitcoinMsgStream.MAX_SIZE) {
            throw new AmatProtocolException(messageNmspc, AmatProtocolOperation.DESERIALIZING_INVALID_SIZE);
        }
    }

    private void computeChecksum(final MessagePayloadHandlerRO roHandler,
                                 final MessageDigest hasher,
                                 final AmatNamespace payloadNmspc) {
        final byte[] hash = hasher.digest();
        roHandler.owner.computedChecksum = extractFirstFourBytes(hash);
        if (logger.isInfoEnabled()) {
            if (roHandler.owner.checksumIsValid())
                logger.info(String.format("%s VALID checksum ", payloadNmspc.toString()));
            else
                logger.info(String.format("%s INVALID checksum", payloadNmspc.toString()));
        }
    }

    static int extractFirstFourBytes(byte[] hash) {
        int b1 = 0xFF & hash[0];
        int b2 = 0xFF & hash[1];
        int b3 = 0xFF & hash[2];
        int b4 = 0xFF & hash[3];
        return (b4 << (8*3)) | (b3 << (8*2)) | (b2 << (8*1)) | b1;
    }
    */

}
