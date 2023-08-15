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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import weliyek.amat.base.namespace.AmatNamespace;
import weliyek.amat.base.protocol.message.field.AmatProtocolMessageFieldValue;

public class BitcoinProtocolMessageFieldInventoryVectorList
        extends AmatProtocolMessageFieldValue<BitcoinInventoryVectorList,
                                              AmatNamespace>
        implements List<BitcoinInventoryVector>
{

    public final AmatNamespace sizeNamespace;
    public final AmatNamespace elementTypeNamespace;
    public final AmatNamespace elementHashNamespace;

    public BitcoinProtocolMessageFieldInventoryVectorList(AmatNamespace namespace) {
        super(namespace.newChildNamespace(BitcoinProtocolName.INV_VEC_LIST));
        sizeNamespace = namespace().newChildNamespace(BitcoinProtocolName.INV_VEC_LIST_SIZE);
        final AmatNamespace elementNamespace = namespace().newChildNamespace(BitcoinProtocolName.INV_VEC_LIST_ELEMENT);
        final AmatNamespace invVecElemNamespace = elementNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC);
        elementTypeNamespace = invVecElemNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC_TYPE);
        elementHashNamespace = invVecElemNamespace.newChildNamespace(BitcoinProtocolName.INV_VEC_HASH);
    }

    @Override
    public int size() {
        return value().size();
    }

    @Override
    public boolean isEmpty() {
        return value().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return value().contains(o);
    }

    @Override
    public Iterator<BitcoinInventoryVector> iterator() {
        return value().iterator();
    }

    @Override
    public Object[] toArray() {
        return value().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return value().toArray(a);
    }

    @Override
    public boolean add(BitcoinInventoryVector e) {
        return value().add(e);
    }

    @Override
    public boolean remove(Object o) {
        return value().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return value().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends BitcoinInventoryVector> c) {
        return value().addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends BitcoinInventoryVector> c) {
        return value().addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return value().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return value().retainAll(c);
    }

    @Override
    public void clear() {
        value().clear();
    }

    @Override
    public BitcoinInventoryVector get(int index) {
        return value().get(index);
    }

    @Override
    public BitcoinInventoryVector set(int index, BitcoinInventoryVector element) {
        return value().set(index, element);
    }

    @Override
    public void add(int index, BitcoinInventoryVector element) {
        value().add(index, element);
    }

    @Override
    public BitcoinInventoryVector remove(int index) {
        return value().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return value().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return value().lastIndexOf(o);
    }

    @Override
    public ListIterator<BitcoinInventoryVector> listIterator() {
        return value().listIterator();
    }

    @Override
    public ListIterator<BitcoinInventoryVector> listIterator(int index) {
        return value().listIterator(index);
    }

    @Override
    public List<BitcoinInventoryVector> subList(int fromIndex, int toIndex) {
        return value().subList(fromIndex, toIndex);
    }

}
