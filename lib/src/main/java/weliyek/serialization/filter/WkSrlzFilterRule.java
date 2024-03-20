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

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import weliyek.serialization.WkSerdeDTreeNodeStructDefinition;

public class WkSrlzFilterRule
        extends WkSrlzFilterPredicateRuleBase
{

    private final WkSrlzPacketNodePredicate<?,?> nodePredicate;
    private final List<WkSerdeDTreeNodeStructDefinition<?>> structDefinitionTargetList;
    private final List<WkSrlzPacketNodePredicate<?,?>> nodePredicateList;
    final ExclusiveOption option;

    public WkSrlzFilterRule(
      WkSrlzPacketNodePredicate<?,?> nodePredicate,
      ExclusiveOption option) {
        super(nodePredicate.toString());
        this.nodePredicate = Objects.requireNonNull(nodePredicate);
        this.option = Objects.requireNonNull(option);

        this.structDefinitionTargetList = Collections.singletonList(nodePredicate.targetProtocolField());
        this.nodePredicateList = Collections.singletonList(nodePredicate);
    }

    @Override
    public List<WkSerdeDTreeNodeStructDefinition<?>> matchTargets() {
        return this.structDefinitionTargetList;
    }

    @Override
    List<WkSrlzPacketNodePredicate<?,?>> testers() {
        return nodePredicateList;
    }

    WkSrlzPacketNodePredicate<?,?> onlyTester() {
        return this.nodePredicate;
    }

    @Override
    public Set<? extends WkSrlzFilterPredicateRuleBase> subrules() {
        return Collections.emptySet();
    }

    @Override
    WkSrlzPacketNodePredicateEvaluatorBase newResult() {
        return new WkSrlzFilterRuleEvaluator(this);
    }

}
