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

public abstract class PayloadListCoreKernelAbstract
                        < E,
                          K extends PayloadListCoreKernelAbstract<E, K>>
        extends PayloadCoreKernelAbstract<K>
        implements List<E>,
                   PayloadCoreKernelRO<K>,
                   PayloadCoreKernelRW<K>
{

    protected abstract List<E> modifiableList();

    protected abstract List<E> publicList();

    protected abstract void onCommonPayloadListKernelCommissionSteps();

    protected abstract void onPayloadListKernelDecommission();

    @Override
    public void commissionPayloadKernelWithArgs(BitcoinMsgRWBody owner, MessageRWArgs args) {
        this.commissionWithOwnerCore(owner);
    }

    @Override
    public int size() {
        return publicList().size();
    }

    @Override
    public boolean isEmpty() {
        return publicList().isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return publicList().contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return publicList().iterator();
    }

    @Override
    public Object[] toArray() {
        return publicList().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return publicList().toArray(a);
    }

    @Override
    public boolean add(E e) {
        return publicList().add(e);
    }

    @Override
    public boolean remove(Object o) {
        return publicList().remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return publicList().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return publicList().addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return publicList().addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return publicList().removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return publicList().retainAll(c);
    }

    @Override
    public void clear() {
        publicList().clear();
    }

    @Override
    public E get(int index) {
        return publicList().get(index);
    }

    @Override
    public E set(int index, E element) {
        return publicList().set(index, element);
    }

    @Override
    public void add(int index, E element) {
        publicList().add(index, element);
    }

    @Override
    public E remove(int index) {
        return publicList().remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return publicList().indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return publicList().lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return publicList().listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return publicList().listIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return publicList().subList(fromIndex, toIndex);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + publicList().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if ( ! (obj instanceof PayloadListCoreKernelAbstract))
            return false;
        PayloadListCoreKernelAbstract<?,?> other = (PayloadListCoreKernelAbstract<?,?>) obj;
        if (! publicList().equals(other.publicList()))
            return false;
        return true;
    }

}
