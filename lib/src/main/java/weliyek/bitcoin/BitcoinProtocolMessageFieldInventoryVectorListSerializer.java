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

import weliyek.amat.base.protocol.message.field.AmatProtocolMessageFieldBaseSerializer;
import weliyek.ketza.base.composite.serializable.SerializableConfig;

public class BitcoinProtocolMessageFieldInventoryVectorListSerializer implements
        AmatProtocolMessageFieldBaseSerializer<BitcoinProtocolMessageFieldInventoryVectorList,
                                               BitcoinProtocolMessageFieldInventoryVectorList,
                                               SerializableConfig>
{

    @Override
    public void serializeKernel(BitcoinProtocolMessageFieldInventoryVectorList invVecListField,
                                OutputStream out,
                                SerializableConfig config)
    {
        invVecListField.value().writeTo(out,
                                        invVecListField.sizeNamespace,
                                        invVecListField.elementTypeNamespace,
                                        invVecListField.elementHashNamespace);
    }

    @Override
    public void deserializeKernel(BitcoinProtocolMessageFieldInventoryVectorList invVecListField,
                                  InputStream in,
                                  SerializableConfig config)
    {
        final BitcoinInventoryVectorList list = BitcoinInventoryVectorList.readFrom(in,
                                                        invVecListField.sizeNamespace,
                                                        invVecListField.elementTypeNamespace,
                                                        invVecListField.elementHashNamespace);
        invVecListField.commissionWithValue(list);
    }

}
