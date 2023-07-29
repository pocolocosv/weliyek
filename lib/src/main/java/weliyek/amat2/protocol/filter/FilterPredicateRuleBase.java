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

import java.util.AbstractSet;
import java.util.List;
import java.util.Set;

/**
 * Inherits from {@link AbstractSet} in order to have most helper logic
 * available to both derived classes.
 */
public abstract class FilterPredicateRuleBase
        implements FilterPredicateRule
{

    final String desc;

    protected FilterPredicateRuleBase(String desc) {
        if (null == desc || desc.isEmpty())
            this.desc = "Anonymous " + this.getClass().getSimpleName();
        else
            this.desc = desc;
    }

    @Override
    public String name() {
        return this.desc;
    }

    abstract List<FieldTester<?,?>> testers();

    @Override
    public String toString() {
        return this.desc;
    }

    @Override
    public abstract Set<? extends FilterPredicateRuleBase> subrules();

    abstract FilterPredicateRuleBaseResult newResult();

}
