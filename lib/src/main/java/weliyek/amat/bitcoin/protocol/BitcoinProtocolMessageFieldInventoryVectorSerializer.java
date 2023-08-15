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

import java.io.InputStream;
import java.io.OutputStream;

import weliyek.amat.base.protocol.message.field.AmatProtocolMessageFieldBaseSerializer;
import weliyek.bitcoin.BitcoinInventoryVector;
import weliyek.ketza.base.composite.serializable.SerializableConfig;

public class BitcoinProtocolMessageFieldInventoryVectorSerializer
        implements AmatProtocolMessageFieldBaseSerializer<BitcoinProtocolMessageFieldInventoryVector,
                                                          BitcoinProtocolMessageFieldInventoryVector,
                                                          SerializableConfig>
{

    @Override
    public void serializeKernel(BitcoinProtocolMessageFieldInventoryVector invVecField,
                                OutputStream out,
                                SerializableConfig config)
    {
        invVecField.value().writeTo(out, invVecField.typeNamespace, invVecField.hashNamespace);
    }

    @Override
    public void deserializeKernel(BitcoinProtocolMessageFieldInventoryVector invVecField,
                                  InputStream in,
                                  SerializableConfig config)
    {
        BitcoinInventoryVector invVec = BitcoinInventoryVector.readFrom(in, invVecField.typeNamespace,
                                                                        invVecField.hashNamespace);
        invVecField.commissionWithValue(invVec);
    }

}
