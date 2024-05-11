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
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

import weliyek.serialization.WkSerdeDtreeStructDefinition;

public class WkSrlzFilter
{

    public static WkSrlzFilter buildFilter(Set<WkSrlzFilterQuery> queries) {
        if (queries.isEmpty())
            return EMPTY_FILTER;
        else
            return new WkSrlzFilter(queries);
    }

    private static final WkSrlzFilter EMPTY_FILTER = new WkSrlzFilter(Collections.emptySet());

    public static WkSrlzFilter getEmptyFilter() { return EMPTY_FILTER; }

    private static final WkSrlzFilterResults EMPTY_RESULTS = new WkSrlzFilterResults(EMPTY_FILTER);

    private final Set<WkSrlzFilterQuery> queries;

    private final Set<WkSerdeDtreeStructDefinition<?>> fieldsToBeMatched;

    private final Set<WkSrlzPacketNodePredicate<?, ?>> matchers;

    private WkSrlzFilter(Set<WkSrlzFilterQuery> queries) {
        this.queries = Collections.unmodifiableSet(new LinkedHashSet<>(queries));
        Set<WkSerdeDtreeStructDefinition<?>> toBeMatched = new LinkedHashSet<>();
        Set<WkSrlzPacketNodePredicate<?, ?>> matchersSet = new LinkedHashSet<>();
        for (WkSrlzFilterQuery query : queries) {
            toBeMatched.addAll(query.rule.matchTargets());
            matchersSet.addAll(query.rule.testers());
        }
        fieldsToBeMatched = Collections.unmodifiableSet(toBeMatched);
        matchers = Collections.unmodifiableSet(matchersSet);
    }

    public final WkSrlzFilterResults buildNewResults() {
        if (this.queries.isEmpty())
            return EMPTY_RESULTS;
        else
            return new WkSrlzFilterResults(this);
    }

    public boolean hasToBeDeserialized(WkSerdeDtreeStructDefinition<?> definition) {
      if (isSearhed(definition)) {
        return true;
      }
      return hasToMatch(definition);
    }

    public boolean isSearhed(WkSerdeDtreeStructDefinition<?> definition) {
      for (WkSrlzFilterQuery query : queries) {
        if (query.searchedField().equals(definition)) {
          return true;
        } else if (query.searchedField().isASubfield(definition)) {
          return true;
        }
      }
      return false;
    }

    public final boolean hasToMatch(WkSerdeDtreeStructDefinition<?> protocolField) {
        return fieldsToBeMatched.contains(protocolField);
    }

    final Stream<WkSrlzPacketNodePredicate<?, ?>> valueTests() {
        return matchers.stream();
    }

    public final Set<WkSrlzFilterQuery> queries() {
        return this.queries;
    }

    public final boolean isEmpty() {
      return queries().isEmpty();
    }

}
