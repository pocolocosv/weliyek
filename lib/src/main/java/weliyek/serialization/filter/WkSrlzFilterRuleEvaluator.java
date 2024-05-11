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
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSerdeDtreeStructDefinition;

public class WkSrlzFilterRuleEvaluator
        extends WkSrlzPacketNodePredicateEvaluatorBase
{

    private static final Logger logger = LoggerFactory.getLogger(WkSrlzFilterRuleEvaluator.class);

    private WkSerdeDtreeMsgFilterable fulfillingSegment;

    WkSrlzFilterRuleEvaluator(
        WkSrlzFilterRule rule) {
        super(rule);
    }

    @Override
    protected boolean onTest(WkSerdeDtreeMsgFilterable segment) {
      if (isPremiseFound()) {
        // Segment that fulfills the rule was found. Nothing else to do.
        return true;
      }

      if (! rule().onlyTester().canBeTestedAgainst(segment)) {
        return isPremiseFound();
      }

      final boolean testResult = rule().onlyTester().testIfProperSegmentOrFalseOtherwise(segment);

      if (testResult) {
        logger.debug(segment.toString() + ": Found match to rule \"" + rule().desc + "\"");
        this.fulfillingSegment = segment;
      }

      return testResult;
    }

    @Override
    public boolean isPremiseFound() {
      return null != this.fulfillingSegment;
    }

    @Override
    public List<WkSerdeDtreeStructDefinition<?>> matchTargets() {
        return rule().matchTargets();
    }

    @Override
    Collection<WkSrlzPacketNodePredicateEvaluatorBase> subresults() {
        return Collections.emptyList();
    }

    @Override
    public Set<? extends WkSrlzFilterPredicateRule> subrules() {
        return rule().subrules();
    }

    @Override
    public final WkSrlzFilterRule rule() {
        return (WkSrlzFilterRule) super.rule();
    }

}