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

import java.util.List;
import java.util.Set;

import weliyek.amat.base.WkSzDefinition;

public interface FilterPredicateRule
{

    public enum ExclusiveOption {
        ANY,
        ALL;

        public boolean isAny() {
            return this.equals(ANY);
        }

        public boolean isAll() {
            return this.equals(ALL);
        }

        public boolean accumulativeResult(boolean current, boolean accumulated) {
            if (isAny())
                return current | accumulated;
            else if (isAll())
                return current & accumulated;
            throw new IllegalStateException();
        }

    }

    String name();  // Optional name

    Set<? extends FilterPredicateRule> subrules();

    List<WkSzDefinition<?,?>> matchTargets();

}