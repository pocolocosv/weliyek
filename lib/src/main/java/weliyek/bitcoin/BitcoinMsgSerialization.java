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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.ketza.bitstreamable.scion.duo.DuoScionSerializer;

public class BitcoinMsgSerialization
        implements DuoScionSerializer<BitcoinMsgROBody,
                                      BitcoinMsgRWBody,
                                      BitcoinProtocolMessageConfig>
{
    public static final Logger logger = LoggerFactory.getLogger(BitcoinMsgSerialization.class);

    final MessageCommonCoreKernelSerialization commonKernelSerialization;

    final MessagePayloadHandlerSerializer payloadHandlerSerializer;

    BitcoinMsgSerialization(BitcoinMsgCommissioners c) {
        assert null != c;
        this.commonKernelSerialization = new MessageCommonCoreKernelSerialization();
        this.payloadHandlerSerializer = new MessagePayloadHandlerSerializer();
    }

    @Override
    public void serializeCore(BitcoinMsgRWBody rw, BitcoinProtocolMessageConfig config, OutputStream out)
    {
        commonKernelSerialization.serializeKernel(rw.commonKernel, out, config);
        payloadHandlerSerializer.serializeKernel(rw.payloadHandler, out, config);
    }

    @Override
    public void deserializeCore(BitcoinMsgROBody ro, BitcoinProtocolMessageConfig config, InputStream in)
    {
        commonKernelSerialization.deserializeKernel(ro.commonKernel, in, config);
        payloadHandlerSerializer.deserializeKernel(ro.payloadHandler, in, config);
    }

}
