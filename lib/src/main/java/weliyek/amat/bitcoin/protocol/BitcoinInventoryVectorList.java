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
import java.util.ArrayList;
import java.util.List;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.bitcoin.BitcoinInventoryVector;

public class BitcoinInventoryVectorList
        extends ArrayList<BitcoinInventoryVector>
{

    private static final long serialVersionUID = 2018_02_11_000L;

    BitcoinInventoryVectorList(List<BitcoinInventoryVector> l) {
        super(l);
    }

    BitcoinInventoryVectorList() {
        super();
    }

    public void writeTo(OutputStream out,
                        AmatNamespace sizeNamespace,
                        AmatNamespace typeNamespace,
                        AmatNamespace hashNamespace)
    {
        BitcoinProtocolIO.writeAsVarInt(this.size(), out, sizeNamespace);
        for (BitcoinInventoryVector invVec : this) {
            invVec.writeTo(out, typeNamespace, hashNamespace);
        }
    }

    public static BitcoinInventoryVectorList readFrom(InputStream in,
                                                      AmatNamespace sizeNamespace,
                                                      AmatNamespace typeNamespace,
                                                      AmatNamespace hashNamespace)
    {
        final long listSize = BitcoinProtocolIO.readVarInt(in, sizeNamespace);
        final BitcoinInventoryVectorList list = new BitcoinInventoryVectorList();
        for(long i = 0; i < listSize; i++) {
            final BitcoinInventoryVector invVec = BitcoinInventoryVector.readFrom(in, typeNamespace, hashNamespace);
            list.add(invVec);
        }
        return list;
    }

}
