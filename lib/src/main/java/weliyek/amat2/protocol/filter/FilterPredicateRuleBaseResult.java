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

import java.util.Collection;

import weliyek.amat.base.WkSzDefinition;

public abstract class FilterPredicateRuleBaseResult
        implements FilterTesterResult,
                   FilterPredicateRule
{

    private final FilterPredicateRuleBase rule;
    private final String desc;

    FilterPredicateRuleBaseResult(FilterPredicateRuleBase rule) {
        this.rule = rule;
        this.desc = "RESULT:" + rule.name();
    }

    final boolean test(WkSzFilterableSegment info) {
        //if (this.isEvaluationCompleted())
        //    return result();
        return onTest(info);
    }

    void process(WkSzFilterableSegment segment, FilterQuery query) {
      if (isPremiseFound()) {
        return;
      }
      WkSzDefinition<?,?> definitionUnderTest = FieldTester.extractProtocolDefinitionFrom(segment);
      if (query.rule.matchTargets().contains(definitionUnderTest)) {
        onTest(segment);
      }
    }

    abstract boolean onTest(WkSzFilterableSegment segment);

    @Override
    public final String name() {
        return this.desc;
    }

    @Override
    public final String toString() {
        return name();
    }

    @Override
    public FilterPredicateRuleBase rule() {
        return rule;
    }

    abstract Collection<FilterPredicateRuleBaseResult> subresults();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.rule.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof FilterPredicateRuleBaseResult))
            return false;
        FilterPredicateRuleBaseResult other = (FilterPredicateRuleBaseResult) obj;
        if ( ! rule.equals(other.rule))
            return false;
        return true;
    }

}
