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

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.base.AmatProtocolException;
import weliyek.amat.base.protocol.base.AmatProtocolOperation;
import weliyek.amat.base.protocol.base.AmatProtocolUtil;
import weliyek.amat.bitcoin.protocol.VarStr;
import weliyek.bitcoin.BitcoinMsgReject.CCodeName;
import weliyek.ketza.util.ByteSequenceWrapper;

public class BitcoinMsgRejectSerializer
        extends PayloadKernelSerializerAbstract<BitcoinMsgRejectROKernel,
                                                BitcoinMsgRejectRWKernel>
{

    public static final Logger logger = LoggerFactory.getLogger(BitcoinMsgRejectSerializer.class);


    // Serialization
    // =============

    @Override
    protected void serializePayloadKernel(final BitcoinMsgRejectRWKernel rwKernel,
                                          final BitcoinConfig config,
                                          final OutputStream out,
                                          final AmatNamespace payloadNamespace)
    {
        final AmatNamespace rejectNmspc = BitcoinProtocolName.REJECT.appendTo(payloadNamespace);
        writeMessageString(rwKernel, out, rejectNmspc);
        writeCCode(rwKernel, out, rejectNmspc);
        writeReason(rwKernel, out, rejectNmspc);
        writeData(rwKernel, out, rejectNmspc);
    }

    private void writeMessageString(final BitcoinMsgRejectRWKernel rwKernel, final OutputStream out,
            final AmatNamespace rejectNmspc) {
        final AmatNamespace messageNmspc = BitcoinProtocolName.REJECT_MESSAGE.appendTo(rejectNmspc);
        VarStr.write(rwKernel.message(), out, messageNmspc);
    }

    private void writeCCode(final BitcoinMsgRejectRWKernel rwKernel, final OutputStream out,
            final AmatNamespace rejectNmspc) {
        final AmatNamespace ccodeNmspc = BitcoinProtocolName.REJECT_CCODE.appendTo(rejectNmspc);
        AmatProtocolUtil.writeByte(out, rwKernel.ccode(), ccodeNmspc);
    }

    private void writeReason(final BitcoinMsgRejectRWKernel rwKernel, final OutputStream out,
            final AmatNamespace rejectNmspc) {
        final AmatNamespace reasonNmspc = BitcoinProtocolName.REJECT_REASON.appendTo(rejectNmspc);
        VarStr.write(rwKernel.reason(), out, reasonNmspc);
    }

    private void writeData(final BitcoinMsgRejectRWKernel rwKernel,
                           final OutputStream out,
                           final AmatNamespace rejectNmspc) {
        final AmatNamespace dataNmspc = BitcoinProtocolName.REJECT_DATA.appendTo(rejectNmspc);
        AmatProtocolUtil.writeByteArray(out, rwKernel.data(), dataNmspc);
    }


    // Deserialization
    // ===============

    @Override
    protected void deserializePayloadKernel(final InputStream in,
                                            final AmatNamespace payloadNamespace,
                                            final BitcoinConfig config,
                                            final BitcoinMsgRejectROKernel roKernel)
    {
        final AmatNamespace rejectNmspc = BitcoinProtocolName.REJECT.appendTo(payloadNamespace);
        readMessage(in, roKernel, rejectNmspc);
        readCCode(in, roKernel, rejectNmspc);
        readReason(in, roKernel, rejectNmspc);
        readData(in, roKernel, rejectNmspc);
    }

    private void readMessage(final InputStream in,
                             final BitcoinMsgRejectROKernel roKernel,
                             final AmatNamespace rejectNmspc) {
        final AmatNamespace messageNmspc = BitcoinProtocolName.REJECT_MESSAGE.appendTo(rejectNmspc);
        final String messageStr = VarStr.read(in, messageNmspc);
        roKernel.setMessageString(messageStr);
    }

    private void readCCode(final InputStream in,
                           final BitcoinMsgRejectROKernel roKernel,
                           final AmatNamespace rejectNmspc) {
        final AmatNamespace ccodeNmspc = BitcoinProtocolName.REJECT_CCODE.appendTo(rejectNmspc);
        final byte ccode = (byte) AmatProtocolUtil.readByte(in, ccodeNmspc);
        roKernel.setCCodeValue(ccode);
    }

    private void readReason(final InputStream in,
                                  final BitcoinMsgRejectROKernel roKernel,
                                  final AmatNamespace rejectNmspc) {
        final AmatNamespace reasonNmspc = BitcoinProtocolName.REJECT_REASON.appendTo(rejectNmspc);
        final String reasonStr = VarStr.read(in, reasonNmspc);
        roKernel.setReasonString(reasonStr);
    }

    private void readData(final InputStream in,
                          final BitcoinMsgRejectROKernel roKernel,
                          final AmatNamespace rejectNmspc)
    {
        final AmatNamespace dataNmspc = BitcoinProtocolName.REJECT_DATA.appendTo(rejectNmspc);
        final Optional<BitcoinCommandName> rejectedCmd = roKernel.messageAsCommand();
        if ( ! rejectedCmd.isPresent()) {
            // We don't have a BitcoinMessageCommand for the rejected command.
            // It's assumed that we know about all the commands being used.
            // Just throw and don't attempt to read data.
            throw new AmatProtocolException(dataNmspc, AmatProtocolOperation.NON_IO, "Don't know the command that was rejected");
        }
        final Optional<CCodeName> ccodeName = BitcoinMsgReject.CCodeName.from(roKernel.ccode());
        int expectedDataLenght = 0;
        if (ccodeName.isPresent()) {
            switch(ccodeName.get()) {
            case REJECT_MALFORMED:  // 0x01
                // Message could not be decoded. Be careful of reject message
                // feedback loops where two peers each don’t understand each
                // other’s reject messages and so keep sending them back and
                // forth forever.
                expectedDataLenght = 0;
                break;
            case REJECT_INVALID:    // 0x10
                switch(rejectedCmd.get()) {
                case BLOCK:
                    // Block is invalid for some reason (invalid proof-of-work,
                    // invalid signature, etc). Extra data may include the
                    // rejected block’s header hash.
                case TX:
                    // Transaction is invalid for some reason (invalid signature,
                    // output value greater than input, etc.). Extra data may
                    // include the rejected transaction’s TXID.
                    expectedDataLenght = 32;
                    break;
                default:
                    expectedDataLenght = 0;
                }
                break;
            case REJECT_OBSOLETE:   // 0x11
                switch(rejectedCmd.get()) {
                case BLOCK:
                    // The block uses a version that is no longer supported.
                    // Extra data may include the rejected block’s header hash.
                    expectedDataLenght = 32;
                    break;
                case VERSION:
                    // Connecting node is using a protocol version that the
                    // rejecting node considers obsolete and unsupported.
                default:
                    expectedDataLenght = 0;
                }
                break;
            case REJECT_DUPLICATE:  // 0x12
                switch(rejectedCmd.get()) {
                case TX:
                    // Duplicate input spend (double spend): the rejected
                    // transaction spends the same input as a previously
                    // -received transaction. Extra data may include the
                    // rejected transaction’s TXID.
                    expectedDataLenght = 32;
                    break;
                case VERSION:
                    //  More than one version message received in this connection.
                default:
                    expectedDataLenght = 0;
                }
                break;
            case REJECT_NONSTANDARD: // 0x40
                switch(rejectedCmd.get()) {
                case TX:
                    // The transaction will not be mined or relayed because
                    // the rejecting node considers it non-standard—a
                    // transaction type or version unknown by the server.
                    // Extra data may include the rejected transaction’s TXID.
                    expectedDataLenght = 32;
                    break;
                default:
                    expectedDataLenght = 0;
                }
                break;
            case REJECT_INSUFFICIENTFEE:    // 0x42
                switch(rejectedCmd.get()) {
                case TX:
                    // The transaction did not have a large enough fee or
                    // priority to be relayed or mined. Extra data may include
                    // the rejected transaction’s TXID.
                    expectedDataLenght = 32;
                    break;
                default:
                    expectedDataLenght = 0;
                }
                break;
            case REJECT_CHECKPOINT: // 0x43
                switch(rejectedCmd.get()) {
                case BLOCK:
                    // The block belongs to a block chain which is not the same
                    // block chain as provided by a compiled-in checkpoint.
                    // Extra data may include the rejected block’s header hash.
                    expectedDataLenght = 32;
                    break;
                default:
                    expectedDataLenght = 0;
                }
                break;
            default:
                break;
            }
        } else {
            // We don't have a CCodeName for the ccode received.
            switch(roKernel.ccode()) {
            case 0x41:  // REJECT_DUST
                // REJECT_DUST define is commented in bitcoin core:
                // https://github.com/bitcoin/bitcoin/blob/7b57bc998f334775b50ebc8ca5e78ca728db4c58/src/consensus/validation.h#L21
                switch(rejectedCmd.get()) {
                case TX:
                    // One or more output amounts are below the dust threshold.
                    // Extra data may include the rejected transaction’s TXID.
                    expectedDataLenght = 32;
                    break;
                default:
                    expectedDataLenght = 0;
                }
                break;
            default:
                expectedDataLenght = 0;
            }
        }
        byte[] dataBuf = new byte[expectedDataLenght];
        AmatProtocolUtil.readByteArray(in, dataBuf, dataNmspc);
        final ByteSequenceWrapper data = new ByteSequenceWrapper(dataBuf);
        roKernel.setDataBytes(data);
    }

}
