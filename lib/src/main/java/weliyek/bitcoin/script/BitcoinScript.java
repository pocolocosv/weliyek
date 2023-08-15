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
package weliyek.bitcoin.script;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public class BitcoinScript
        extends AbstractList<BitcoinScriptOp>
{

    private final List<BitcoinScriptOp> container;

    BitcoinScript(List<BitcoinScriptOp> container) {
        this.container = Objects.requireNonNull(container);
    }

    public long sizeInBytes() {
        long size = 0;
        for (BitcoinScriptOp op : container) {
            size += op.sizeInBytes();
        }
        return size;
    }

    @Override
    public BitcoinScriptOp get(int index) {
        return container.get(index);
    }

    @Override
    public int size() {
        return container.size();
    }

    /*
    public static class DeserializedScriptBuilder<C extends MessageContext>
            implements DeserializedBuilder<C, BitcoinScript, BitcoinScriptField<C,?>>
    {

        @Override
        public BitcoinScript buildDeserialized(
                BitcoinScriptField<C, ?> field,
                ReadProtocolData<C> records) {
            // TODO Auto-generated method stub
            return null;
        }

    }
    */

}
