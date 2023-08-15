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

import java.util.Iterator;

public class CountingIterator<E> implements Iterator<E>
{

    private final Iterator<E> iter;
    private int counter = 0;

    public CountingIterator(Iterator<E> iter) {
        this.iter = iter;
    }

    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public E next() {
        final E e = iter.next();
        counter++;
        return e;
    }

    public int count() {
        return counter;
    }

}
