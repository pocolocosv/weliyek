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
package weliyek.amat2.protocol.filter;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class PrimitivesGroupList extends AbstractList<PrimitivesGroup>
{

    final List<PrimitivesGroup> container;

    /**
     * Uses supplied list as a container. Any changes done to the container
     * will be mirrored by this class.
     */
    PrimitivesGroupList(List<PrimitivesGroup> container) {
        this.container = Objects.requireNonNull(container);
    }

    PrimitivesGroupList(Collection<PrimitivesGroup> collection) {
        this.container = new ArrayList<>(collection);
    }

    @Override
    public PrimitivesGroup get(int index) {
        return container.get(index);
    }

    @Override
    public int size() {
        return container.size();
    }

    static class Builder {

        private static LinkedList<PrimitivesGroup> builderContainer = new LinkedList<>();

        public PrimitivesGroupList build() {
            List<PrimitivesGroup> listContainer = new LinkedList<>(builderContainer);
            builderContainer.clear();
            return new PrimitivesGroupList(listContainer);
        }

        public Builder addPrimitives(PrimitivesGroup primitive) {
            builderContainer.add(primitive);
            return this;
        }

    }

}
