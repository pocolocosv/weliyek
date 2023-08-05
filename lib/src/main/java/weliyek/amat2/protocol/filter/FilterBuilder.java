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

import java.util.LinkedHashSet;
import java.util.Set;

import weliyek.amat.base.WkSzStruct;

public class FilterBuilder
{

    private final Set<FilterQuery> builderContainer = new LinkedHashSet<>();

    public FilterBuilder() {
    }

    FilterBuilder setProtocol(WkSzStruct<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> packetStructure) {
      // TODO: Set structure to be used to verify that all added queries do apply for
      // said structure.
        return this;
    }

    FilterBuilder addQuery(FilterQuery query) {
        this.builderContainer.add(query);
        return this;
    }

    public void reset() {
        this.builderContainer.clear();
    }

    public Filter build() {
        final Filter filter = Filter.buildFilter(builderContainer);
        reset();
        return filter;
    }

}
