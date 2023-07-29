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
import java.util.LinkedHashMap;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.input.DeserializingOperation;

public class FilterQueryResults
{

    private static final Logger logger = LoggerFactory.getLogger(FilterQueryResults.class);

    private final FilterQuery query;

    private LinkedHashMap<DeserializingOperation<?,?,?,?,?>, FilterPredicateRuleBaseResult>
                      matchedTargetAndTests = new LinkedHashMap<>();

    private FilterPredicateRuleBaseResult currentResult;

    FilterQueryResults(FilterQuery query) {
        this.query = Objects.requireNonNull(query);
        this.currentResult = query.rule.newResult();
    }

    public FilterQuery query() {
        return this.query;
    }

    public Collection<FilterPredicateRuleBaseResult> results() {
        return this.matchedTargetAndTests.values();
    }

    public void test(FilterableMessageSegment segmentUnderTest) {
      DefinitionSegment<?,?> definitionUnderTest = FieldTester.extractProtocolDefinitionFrom(segmentUnderTest);
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
          this.matchedTargetAndTests.put((DeserializingOperation<?,?,?,?,?>) segmentUnderTest, currentResult);
        }
        // Restart rules because the old one could contain true predicates.
        this.currentResult = null;
      }

    }

    FilterPredicateRuleBaseResult currentResult() {
        return this.currentResult;
    }

}
