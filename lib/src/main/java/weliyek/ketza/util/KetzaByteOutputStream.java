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
package weliyek.ketza.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import weliyek.ketza.util.array.WKArrayUtil;

public class KetzaByteOutputStream
        extends ByteArrayOutputStream
        implements Iterable<Integer>
{

    public KetzaByteOutputStream() {
    }

    public KetzaByteOutputStream(int size) {
        super(size);
    }

    public InputStream inputStream() {
        return new ByteArrayInputStream(buf, 0, count);
    }

    @Deprecated
    public byte[] backingArray() {
        return this.buf;
    }

    public byte[] arrayCopy() {
        return Arrays.copyOf(buf, count);
    }

    public boolean equals(byte[] array) {
      return equals(array, 0, array.length);
    }

    public boolean equals(byte[] array, int from, int to) {
      Objects.requireNonNull(array);
      WKArrayUtil.throwIfBoundsAreInvalid(array.length, from, to);
      return Arrays.equals(this.buf, 0, this.count, array, from, to);
    }

    public int compare(byte[] array, int from, int to) {
      Objects.requireNonNull(array);
      WKArrayUtil.throwIfBoundsAreInvalid(array.length, from, to);
      return Arrays.compare(this.buf, 0, this.count, array, from, to);
    }

    @Override
    public ListIterator<Integer> iterator() {
        return iterator(0);
    }

    public ListIterator<Integer> iterator(final int index) {
        if (index < 0 || this.count < index) {
            throw new IndexOutOfBoundsException();
        }
        return new ListIterator<Integer>() {

            int pos = index;

            @Override
            public boolean hasNext() {
                return pos < count;
            }

            @Override
            public Integer next() {
                if ( ! hasNext()) {
                    throw new NoSuchElementException();
                }
                byte b = buf[pos];
                pos++;
                return Integer.valueOf(b);
            }

            @Override
            public boolean hasPrevious() {
                return 0 < pos && pos <= count;
            }

            @Override
            public Integer previous() {
                if ( ! hasPrevious()) {
                    throw new NoSuchElementException();
                }
                pos--;
                return Integer.valueOf(buf[pos]);
            }

            @Override
            public int nextIndex() {
                return pos;
            }

            @Override
            public int previousIndex() {
                return pos - 1;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override
            public void set(Integer i) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void add(Integer i) {
                throw new UnsupportedOperationException();
            }
        };
    }

}
