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

import java.util.List;
import java.util.Objects;

import weliyek.serialization.WkSerdeDtreeStructDefinition;

public class WkSrlzFilterQuery
{

    final WkSrlzFilterPredicateRuleBase rule;

    private final WkSerdeDtreeStructDefinition<?> mySearchTargetField;

    private final String description;

    WkSrlzFilterQuery(WkSerdeDtreeStructDefinition<?> searchedField,
                String desc,
                WkSrlzFilterPredicateRuleBase rule) {
        this.mySearchTargetField = Objects.requireNonNull(searchedField);
        this.rule = Objects.requireNonNull(rule);
        this.description = "Query:" + Objects.requireNonNull(desc);
    }

    public WkSerdeDtreeStructDefinition<?> searchedField() {
        return this.mySearchTargetField;
    }

    public List<WkSerdeDtreeStructDefinition<?>> matchTargetFields() {
        return rule.matchTargets();
    }

    @Override
    public String toString() {
        return description;
    }

}
