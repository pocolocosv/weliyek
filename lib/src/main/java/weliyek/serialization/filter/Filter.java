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

import weliyek.serialization.WkSzDefinition;

public class Filter
{

    public static Filter buildFilter(Set<FilterQuery> queries) {
        if (queries.isEmpty())
            return EMPTY_FILTER;
        else
            return new Filter(queries);
    }

    private static final Filter EMPTY_FILTER = new Filter(Collections.emptySet());

    public static Filter getEmptyFilter() { return EMPTY_FILTER; }

    private static final FilterResults EMPTY_RESULTS = new FilterResults(EMPTY_FILTER);

    private final Set<FilterQuery> queries;

    private final Set<WkSzDefinition<?,?>> fieldsToBeMatched;

    private final Set<FieldTester<?, ?>> matchers;

    private Filter(Set<FilterQuery> queries) {
        this.queries = Collections.unmodifiableSet(new LinkedHashSet<>(queries));
        Set<WkSzDefinition<?,?>> toBeMatched = new LinkedHashSet<>();
        Set<FieldTester<?, ?>> matchersSet = new LinkedHashSet<>();
        for (FilterQuery query : queries) {
            toBeMatched.addAll(query.rule.matchTargets());
            matchersSet.addAll(query.rule.testers());
        }
        fieldsToBeMatched = Collections.unmodifiableSet(toBeMatched);
        matchers = Collections.unmodifiableSet(matchersSet);
    }

    public final FilterResults buildNewResults() {
        if (this.queries.isEmpty())
            return EMPTY_RESULTS;
        else
            return new FilterResults(this);
    }

    public boolean hasToBeDeserialized(WkSzDefinition<?,?> definition) {
      if (isSearhed(definition)) {
        return true;
      }
      return hasToMatch(definition);
    }

    public boolean isSearhed(WkSzDefinition<?,?> definition) {
      for (FilterQuery query : queries) {
        if (query.searchedField().equals(definition)) {
          return true;
        } else if (query.searchedField().isASubfield(definition)) {
          return true;
        }
      }
      return false;
    }

    public final boolean hasToMatch(WkSzDefinition<?,?> protocolField) {
        return fieldsToBeMatched.contains(protocolField);
    }

    final Stream<FieldTester<?, ?>> valueTests() {
        return matchers.stream();
    }

    public final Set<FilterQuery> queries() {
        return this.queries;
    }

    public final boolean isEmpty() {
      return queries().isEmpty();
    }

}
