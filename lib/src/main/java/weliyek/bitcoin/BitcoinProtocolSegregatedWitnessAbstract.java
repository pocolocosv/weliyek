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
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BitcoinProtocolSegregatedWitnessAbstract
                            <S extends BitcoinProtocolScript>
        extends AbstractList<S>
        implements BitcoinProtocolSegregatedWitness<S>
{

    protected final List<S> list;

    protected BitcoinProtocolSegregatedWitnessAbstract() {
        super();
        list = new ArrayList<>();
    }

    protected BitcoinProtocolSegregatedWitnessAbstract(Collection<? extends S> c) {
        super();
        list = new ArrayList<>(c);
    }

    protected void writeToOutputStream(OutputStream                              out,
                                       BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces)
    {
        BitcoinProtocolIO.writeAsVarInt(this.size(), out, segwitNamespaces.size);
        for (S script : this) {
            writeScript(script, out, segwitNamespaces.script);
        }
    }

    protected abstract void writeScript(S                               script,
                                        OutputStream                    out,
                                        BitcoinProtocolScriptNamespaces scriptNamespaces);

    protected void readFromInputStream(InputStream   in,
                                       BitcoinProtocolSegregatedWitnessNamespace segwitNamespaces)
    {
        this.list.clear();
        long listSize = BitcoinProtocolIO.readVarInt(in, segwitNamespaces.size);
        for(int i = 0; i < listSize; i++) {
            S script = newScriptFromInputStream(in, segwitNamespaces.script);
            this.list.add(script);
        }
    }

    protected abstract S newScriptFromInputStream(InputStream   in,
                                                  BitcoinProtocolScriptNamespaces scriptNamespaces);

    @Override
    public S get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

}
