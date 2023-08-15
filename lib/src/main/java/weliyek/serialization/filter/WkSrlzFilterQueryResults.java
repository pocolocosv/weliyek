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
import java.util.LinkedHashMap;
import java.util.Objects;

import weliyek.serialization.WkSrlzInputPacketDecoderFrameNode;
import weliyek.serialization.WkSrlzStructDefinitionFrameNode;

public class WkSrlzFilterQueryResults
{

    private final WkSrlzFilterQuery query;

    private LinkedHashMap<WkSrlzInputPacketDecoderFrameNode<?,?,?,?,?>, WkSrlzPacketNodePredicateEvaluatorBase>
                      matchedTargetAndTests = new LinkedHashMap<>();

    private WkSrlzPacketNodePredicateEvaluatorBase currentResult;

    WkSrlzFilterQueryResults(WkSrlzFilterQuery query) {
        this.query = Objects.requireNonNull(query);
        this.currentResult = query.rule.newResult();
    }

    public WkSrlzFilterQuery query() {
        return this.query;
    }

    public Collection<WkSrlzPacketNodePredicateEvaluatorBase> results() {
        return this.matchedTargetAndTests.values();
    }

    public void test(WkSrlzPacketFilterableFrameNode segmentUnderTest) {
      WkSrlzStructDefinitionFrameNode<?,?> definitionUnderTest = WkSrlzPacketNodePredicate.extractProtocolDefinitionFrom(segmentUnderTest);
      final boolean definitionUnderTestIsBeingSearched = query.searchedField().equals(definitionUnderTest);
      final boolean definitionUnderTestIsASubfield = query.searchedField().isASubfield(definitionUnderTest);
      final boolean isNeitherSearchedOrASubfield =    (! definitionUnderTestIsBeingSearched)
                                                   && (! definitionUnderTestIsASubfield);
      if (isNeitherSearchedOrASubfield) {
        this.currentResult = null;
        return;
      } else if (definitionUnderTestIsASubfield) { // is a subfield
        if (null == this.currentResult) {
          this.currentResult = query.rule.newResult();
        }
        this.currentResult.process(segmentUnderTest, this.query);
      } else if (definitionUnderTestIsBeingSearched) {
        if (this.currentResult.isPremiseFound()) {
          this.matchedTargetAndTests.put((WkSrlzInputPacketDecoderFrameNode<?,?,?,?,?>) segmentUnderTest, currentResult);
        }
        // Restart rules because the old one could contain true predicates.
        this.currentResult = null;
      }

    }

    WkSrlzPacketNodePredicateEvaluatorBase currentResult() {
        return this.currentResult;
    }

}
