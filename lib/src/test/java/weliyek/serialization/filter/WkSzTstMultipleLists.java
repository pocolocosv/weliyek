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
package weliyek.serialization.filter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class WkSzTstMultipleLists extends AbstractList<WkSzTstPrimitivesGroupList>
{

    public final static String SERIALIZER_LABEL = "MULTIPLEGROUPLISTS";

    final List<WkSzTstPrimitivesGroupList> container;

    WkSzTstMultipleLists(List<WkSzTstPrimitivesGroupList> container) {
        this.container = Objects.requireNonNull(container);
    }

    WkSzTstMultipleLists(Collection<WkSzTstPrimitivesGroupList> col) {
        this.container = new ArrayList<>(col);
    }

    @Override
    public WkSzTstPrimitivesGroupList get(int index) {
        return container.get(index);
    }

    @Override
    public int size() {
        return container.size();
    }

    public static class Builder extends AbstractList<WkSzTstPrimitivesGroupList>
    {

        private LinkedList<WkSzTstPrimitivesGroupList> builderContainer = new LinkedList<>();

        public WkSzTstMultipleLists build() {
            ArrayList<WkSzTstPrimitivesGroupList> listContainer = new ArrayList<>(builderContainer);
            builderContainer.clear();
            return new WkSzTstMultipleLists(listContainer);
        }

        public Builder addPrimitivesList(WkSzTstPrimitivesGroupList list) {
            this.add(list);
            return this;
        }

        @Override
        public void add(int index, WkSzTstPrimitivesGroupList element) {
            this.builderContainer.add(index, element);
        }

        @Override
        public WkSzTstPrimitivesGroupList set(int index, WkSzTstPrimitivesGroupList element) {
            return this.builderContainer.set(index, element);
        }

        @Override
        public WkSzTstPrimitivesGroupList get(int index) {
            return this.builderContainer.get(index);
        }

        @Override
        public int size() {
            return this.builderContainer.size();
        }

    }

}
