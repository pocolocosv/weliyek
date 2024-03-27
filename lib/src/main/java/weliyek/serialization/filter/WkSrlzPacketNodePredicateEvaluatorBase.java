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

import java.util.Collection;

import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;

public abstract class WkSrlzPacketNodePredicateEvaluatorBase
        implements WkSrlzPacketNodePredicateResult,
                   WkSrlzFilterPredicateRule
{

    private final WkSrlzFilterPredicateRuleBase rule;
    private final String desc;

    WkSrlzPacketNodePredicateEvaluatorBase(WkSrlzFilterPredicateRuleBase rule) {
        this.rule = rule;
        this.desc = "RESULT:" + rule.name();
    }

    final boolean test(WkSerdeDtreeNodeDataFilterable info) {
        //if (this.isEvaluationCompleted())
        //    return result();
        return onTest(info);
    }

    void process(WkSerdeDtreeNodeDataFilterable segment, WkSrlzFilterQuery query) {
      if (isPremiseFound()) {
        return;
      }
      WkSerdeDtreeNodeStructDefinition<?> definitionUnderTest = WkSrlzPacketNodePredicate.extractProtocolDefinitionFrom(segment);
      if (query.rule.matchTargets().contains(definitionUnderTest)) {
        onTest(segment);
      }
    }

    abstract boolean onTest(WkSerdeDtreeNodeDataFilterable segment);

    @Override
    public final String name() {
        return this.desc;
    }

    @Override
    public final String toString() {
        return name();
    }

    @Override
    public WkSrlzFilterPredicateRuleBase rule() {
        return rule;
    }

    abstract Collection<WkSrlzPacketNodePredicateEvaluatorBase> subresults();

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
        if (!(obj instanceof WkSrlzPacketNodePredicateEvaluatorBase))
            return false;
        WkSrlzPacketNodePredicateEvaluatorBase other = (WkSrlzPacketNodePredicateEvaluatorBase) obj;
        if ( ! rule.equals(other.rule))
            return false;
        return true;
    }

}
