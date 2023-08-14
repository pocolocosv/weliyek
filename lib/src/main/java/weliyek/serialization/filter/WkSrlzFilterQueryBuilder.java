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
import weliyek.serialization.filter.WkSrlzFilterPredicateRule.ExclusiveOption;

public class WkSrlzFilterQueryBuilder
{

    private WkSrlzStructDefinitionFrameNode<?,?> searchedField;
    private WkSrlzFilterPredicateRuleBase rule;
    private String desc;

    public WkSrlzFilterQueryBuilder() {
        reset();
    }

    public void reset() {
        this.searchedField = null;
        this.rule = null;
        this.desc = "";
    }

    public WkSrlzFilterQueryBuilder search(WkSrlzStructDefinitionFrameNode<?,?> searchedField) {
        this.searchedField = searchedField;
        return this;
    }

    public WkSrlzFilterQueryBuilder where(WkSrlzFilterPredicateRuleBase rule) {
        this.rule = rule;
        return this;
    }

    public <V> WkSrlzFilterQueryBuilder whereAny(WkSrlzPacketNodePredicate<?,?> matcher) {
        this.rule = new WkSrlzFilterRule(matcher, ExclusiveOption.ANY);
        return this;
    }

    public <V> WkSrlzFilterQueryBuilder whereAll(WkSrlzPacketNodePredicate<?,?> matcher) {
        this.rule = new WkSrlzFilterRule(matcher, ExclusiveOption.ALL);
        return this;
    }

    public WkSrlzFilterQueryBuilder withDescription(String desc) {
        this.desc = desc;
        return this;
    }

    public WkSrlzFilterQuery build() {
        final WkSrlzFilterQuery q = new WkSrlzFilterQuery(searchedField, desc, rule);
        reset();
        return q;
    }

}
