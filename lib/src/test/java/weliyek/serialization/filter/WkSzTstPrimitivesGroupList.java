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

public class WkSzTstPrimitivesGroupList extends AbstractList<WkSzTstPrimitivesGroup>
{

    final List<WkSzTstPrimitivesGroup> container;

    /**
     * Uses supplied list as a container. Any changes done to the container
     * will be mirrored by this class.
     */
    WkSzTstPrimitivesGroupList(List<WkSzTstPrimitivesGroup> container) {
        this.container = Objects.requireNonNull(container);
    }

    WkSzTstPrimitivesGroupList(Collection<WkSzTstPrimitivesGroup> collection) {
        this.container = new ArrayList<>(collection);
    }

    @Override
    public WkSzTstPrimitivesGroup get(int index) {
        return container.get(index);
    }

    @Override
    public int size() {
        return container.size();
    }

    static class Builder {

        private static LinkedList<WkSzTstPrimitivesGroup> builderContainer = new LinkedList<>();

        public WkSzTstPrimitivesGroupList build() {
            List<WkSzTstPrimitivesGroup> listContainer = new LinkedList<>(builderContainer);
            builderContainer.clear();
            return new WkSzTstPrimitivesGroupList(listContainer);
        }

        public Builder addPrimitives(WkSzTstPrimitivesGroup primitive) {
            builderContainer.add(primitive);
            return this;
        }

    }

}
