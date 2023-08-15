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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.input.BoundedInputStream;
import org.apache.commons.io.input.CountingInputStream;

import weliyek.amat.base.protocol.base.AmatProtocolException;
import weliyek.amat.base.protocol.base.AmatProtocolOperation;
import weliyek.ketza.util.KetzaByteOutputStream;

public abstract class BitcoinProtocolScriptAbstract
        extends AbstractList<BitcoinScriptOp>
        implements BitcoinProtocolScript
{

    protected final List<BitcoinScriptOp> list;

    protected BitcoinProtocolScriptAbstract() {
        list = new ArrayList<>();
    }

    protected BitcoinProtocolScriptAbstract(Collection<? extends BitcoinScriptOp> c) {
        list = new ArrayList<>(c);
    }

    protected void writeToOutputStream(OutputStream                    out,
                                       BitcoinProtocolScriptNamespaces scriptNamespace)
    {
        final KetzaByteOutputStream outBuf = new KetzaByteOutputStream();
        for (BitcoinScriptOp op : this) {
            op.writeTo(outBuf, scriptNamespace.op);
        }
        BitcoinProtocolIO.writeAsVarInt(outBuf.size(), out, scriptNamespace.size);
        try {
            outBuf.writeTo(out);
        } catch (IOException e) {
            throw new AmatProtocolException(scriptNamespace, AmatProtocolOperation.IO_WRITE, e);
        }
    }

    protected void readFromInputStream(InputStream                     in,
                                       BitcoinProtocolScriptNamespaces scriptNamespace)
    {
        this.list.clear();
        final long size = BitcoinProtocolIO.readVarInt(in, scriptNamespace.size);
        final BoundedInputStream boundedIn = new BoundedInputStream(in, size);
        final CountingInputStream countingIn = new CountingInputStream(boundedIn);
        while(countingIn.getByteCount() < size) {
            final BitcoinScriptOp op = BitcoinScriptOp.readFrom(countingIn, scriptNamespace.op);
            this.list.add(op);
        }
    }

    @Override
    public BitcoinScriptOp get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }

}
