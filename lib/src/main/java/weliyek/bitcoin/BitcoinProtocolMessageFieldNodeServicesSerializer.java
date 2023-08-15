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

public class BitcoinProtocolMessageFieldNodeServicesSerializer
        implements AmatProtocolMessageFieldBaseSerializer<BitcoinProtocolMessageFieldNodeServices,
                                                          BitcoinProtocolMessageFieldNodeServices,
                                                          SerializableConfig>
{

    @Override
    public void serializeKernel(BitcoinProtocolMessageFieldNodeServices servicesField,
                                OutputStream out,
                                SerializableConfig config)
    {
        BitcoinNodeServices services = servicesField.value();
        services.writeTo(out, servicesField.namespace());
    }

    @Override
    public void deserializeKernel(BitcoinProtocolMessageFieldNodeServices servicesField,
                                  InputStream in,
                                  SerializableConfig config)
    {
        BitcoinNodeServices services = BitcoinNodeServices.readFrom(in, servicesField.namespace());
        servicesField.commissionWithValue(services);
    }

}
