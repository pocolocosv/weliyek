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

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weliyek.serialization.WkSrlzStructDefinitionFrameNode;

public class FilterResults
{

  private static final Logger logger = LoggerFactory.getLogger(FilterResults.class);

    private final Filter filter;

    private final Map<FilterQuery, FilterQueryResults> resultListsByQuery = new HashMap<>();

    FilterResults(Filter filter) {
        this.filter = filter;
        for (FilterQuery query : filter.queries()) {
            resultListsByQuery.put(query, new FilterQueryResults(query));
        }
    }

    public FilterQueryResults allResultsForQuery(FilterQuery query) {
        return resultListsByQuery.get(query);
    }

    public void runTestOnOpRes(WkSrlzPacketFilterableFrameNode opRes) {
        for (FilterQueryResults queryResults : resultListsByQuery.values()) {
          logger.debug("Testing \"" + opRes.toString() + "\" against query \"" + queryResults.query().toString() + "\"");
          queryResults.test(opRes);
        }
    }

    public boolean fieldMustBeMatched(WkSrlzStructDefinitionFrameNode<?,?> field) {
        for (FilterQueryResults queryResults : resultListsByQuery.values()) {
          if (queryResults.query().matchTargetFields().contains(field)) {
            return true;
          }
        }
        return false;
    }

    public Filter filter() {
      return this.filter;
    }

}
