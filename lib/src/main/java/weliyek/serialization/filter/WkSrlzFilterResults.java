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

public class WkSrlzFilterResults
{

  private static final Logger logger = LoggerFactory.getLogger(WkSrlzFilterResults.class);

    private final WkSrlzFilter filter;

    private final Map<WkSrlzFilterQuery, WkSrlzFilterQueryResults> resultListsByQuery = new HashMap<>();

    WkSrlzFilterResults(WkSrlzFilter filter) {
        this.filter = filter;
        for (WkSrlzFilterQuery query : filter.queries()) {
            resultListsByQuery.put(query, new WkSrlzFilterQueryResults(query));
        }
    }

    public WkSrlzFilterQueryResults allResultsForQuery(WkSrlzFilterQuery query) {
        return resultListsByQuery.get(query);
    }

    public void runTestOnOpRes(WkSrlzPacketFilterableFrameNode opRes) {
        for (WkSrlzFilterQueryResults queryResults : resultListsByQuery.values()) {
          logger.debug("Testing \"" + opRes.toString() + "\" against query \"" + queryResults.query().toString() + "\"");
          queryResults.test(opRes);
        }
    }

    public boolean fieldMustBeMatched(WkSrlzStructDefinitionFrameNode<?,?> field) {
        for (WkSrlzFilterQueryResults queryResults : resultListsByQuery.values()) {
          if (queryResults.query().matchTargetFields().contains(field)) {
            return true;
          }
        }
        return false;
    }

    public WkSrlzFilter filter() {
      return this.filter;
    }

}
