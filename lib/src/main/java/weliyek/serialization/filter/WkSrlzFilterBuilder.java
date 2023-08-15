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

import java.util.LinkedHashSet;
import java.util.Set;

import weliyek.serialization.WkSrlzStruct;
import weliyek.serialization.WkSrlzStructComponentFrameNodeRootCore;

public class WkSrlzFilterBuilder
{

    private final Set<WkSrlzFilterQuery> builderContainer = new LinkedHashSet<>();

    public WkSrlzFilterBuilder() {
    }

    WkSrlzFilterBuilder setProtocol(WkSrlzStruct<?, ?, ?, ?, ?, ?, ?, ?, ?, ?> packetStructure) {
      // TODO: Set structure to be used to verify that all added queries do apply for
      // said structure.
        return this;
    }

    WkSrlzFilterBuilder addQuery(WkSrlzFilterQuery query) {
        this.builderContainer.add(query);
        return this;
    }

    public void reset() {
        this.builderContainer.clear();
    }

    public WkSrlzFilter build() {
        final WkSrlzFilter filter = WkSrlzFilter.buildFilter(builderContainer);
        reset();
        return filter;
    }

}
