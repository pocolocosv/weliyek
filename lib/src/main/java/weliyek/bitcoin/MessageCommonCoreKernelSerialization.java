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

import weliyek.ketza.base.composite.serializable.SerializableCoreKernelSerializer;

public class MessageCommonCoreKernelSerialization
        implements SerializableCoreKernelSerializer<MessageCommonCoreKernelBase,
                                                    MessageCommonCoreKernelBase,
                                                    BitcoinProtocolMessageConfig>
{

    @Override
    public void deserializeKernel(MessageCommonCoreKernelBase commonKernel,
                                  InputStream                     in,
                                  BitcoinProtocolMessageConfig    config)
    {
        final BitcoinMessageMagic magic = BitcoinMessageMagic.readFrom(in, commonKernel.magicNamespace);
        final BitcoinCommand command = BitcoinCommand.readFrom(in, commonKernel.commandNamespace);
        commonKernel.commissionWithMagicAndCommand(magic, command);
    }

    @Override
    public void serializeKernel(MessageCommonCoreKernelBase commonKernel,
                                OutputStream                    out,
                                BitcoinProtocolMessageConfig    config)
    {
        commonKernel.magic().writeTo(out, commonKernel.magicNamespace);
        commonKernel.command().writeTo(out, commonKernel.commandNamespace);
    }

    /*
    // Serialization
    // =============

    public void serializeCommonKernel(final MessageCommonCoreKernelRW rwKernel,
                                      final BitcoinConfig          config,
                                      final OutputStream              out,
                                      final AmatNamespace     messageNmspc)
    {
        writeMagic(rwKernel, out, messageNmspc);
        writeCommand(rwKernel, out, messageNmspc);
    }

    private void writeMagic(final MessageCommonCoreKernelRW rwKernel,
                            final OutputStream              out,
                            final AmatNamespace     messageNmspc) {
        final AmatNamespace magicNmscp = BitcoinProtocolName.MESSAGE_MAGIC.appendTo(messageNmspc);
        if ( ! rwKernel.magic().isPresent())
            throw new AmatProtocolException(magicNmscp, AmatProtocolOperation.SERIALIZER_INVALID_VALUE);
        // Writing magic as is. No attempt to convert to know valid values as those in the enum.
        AmatProtocolUtil.writeIntAsLittleEndian(out, rwKernel.magic().get().intValue(), magicNmscp);
    }

    private void writeCommand(final MessageCommonCoreKernelRW rwKernel,
                              final OutputStream              out,
                              final AmatNamespace     messageNmspc) {
        final AmatNamespace commandNmspc = BitcoinProtocolName.MESSAGE_COMMAND.appendTo(messageNmspc);
        if ( ! rwKernel.command().isPresent())
            throw new AmatProtocolException(commandNmspc,
                                            AmatProtocolOperation.SERIALIZER_INVALID_VALUE);
        // Writing command as is. No attempt to convert to know valid values as those in the enum.
        AmatProtocolUtil.writeBytes(out,
                                    BitcoinCommandName.canonicalArrayFromString(rwKernel.command().get()),
                                    commandNmspc);
    }

    // Deserialization
    // ===============

    public void deserializeCommonKernel(final InputStream in,
                                        final BitcoinConfig config,
                                        final MessageCommonCoreKernelRO roKernel,
                                        final AmatNamespace messageNmspc)
    {
        readMagic(in, roKernel, messageNmspc);
        readCommand(in, roKernel, messageNmspc);
    }

    private void readMagic(final InputStream in,
                           final MessageCommonCoreKernelRO roKernel,
                           final AmatNamespace messageNmspc) {
        final AmatNamespace magicNmspc = BitcoinProtocolName.MESSAGE_MAGIC.appendTo(messageNmspc);
        int magicVal = AmatProtocolUtil.readLittleEndianInt(in, magicNmspc);
        roKernel.setMagic(magicVal);
    }

    private void readCommand(final InputStream in,
                             final MessageCommonCoreKernelRO roKernel,
                             final AmatNamespace messageNmspc) {
        final AmatNamespace commandNmspc = BitcoinProtocolName.MESSAGE_COMMAND.appendTo(messageNmspc);
        byte[] cmdBuffer = new byte[BitcoinCommandName.CANONICAL_LENGTH];
        AmatProtocolUtil.readByteArray(in, cmdBuffer, commandNmspc);
        final String commandStr = BitcoinCommandName.convertCanonicalBufferToString(cmdBuffer);
        roKernel.setCommand(commandStr);
    }
    */

}
