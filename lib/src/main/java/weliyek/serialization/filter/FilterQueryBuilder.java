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

import weliyek.serialization.WkSrlzStructDefinitionFrameNode;
import weliyek.serialization.filter.FilterPredicateRule.ExclusiveOption;

public class FilterQueryBuilder
{

    private WkSrlzStructDefinitionFrameNode<?,?> searchedField;
    private FilterPredicateRuleBase rule;
    private String desc;

    public FilterQueryBuilder() {
        reset();
    }

    public void reset() {
        this.searchedField = null;
        this.rule = null;
        this.desc = "";
    }

    public FilterQueryBuilder search(WkSrlzStructDefinitionFrameNode<?,?> searchedField) {
        this.searchedField = searchedField;
        return this;
    }

    public FilterQueryBuilder where(FilterPredicateRuleBase rule) {
        this.rule = rule;
        return this;
    }

    public <V> FilterQueryBuilder whereAny(FieldTester<?,?> matcher) {
        this.rule = new FilterPredicateRuleField(matcher, ExclusiveOption.ANY);
        return this;
    }

    public <V> FilterQueryBuilder whereAll(FieldTester<?,?> matcher) {
        this.rule = new FilterPredicateRuleField(matcher, ExclusiveOption.ALL);
        return this;
    }

    public FilterQueryBuilder withDescription(String desc) {
        this.desc = desc;
        return this;
    }

    public FilterQuery build() {
        final FilterQuery q = new FilterQuery(searchedField, desc, rule);
        reset();
        return q;
    }

}
