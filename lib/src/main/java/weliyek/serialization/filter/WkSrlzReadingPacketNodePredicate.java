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

import java.util.function.Predicate;

import weliyek.serialization.WkSerdeDtreeNodeStructDefinition;
import weliyek.serialization.WkSerdeDtreeNodeDataReader;

public class WkSrlzReadingPacketNodePredicate<
                        D extends WkSerdeDtreeNodeStructDefinition<?>,
                        O extends WkSerdeDtreeNodeDataReader<?,?,?,?,D>>
    extends WkSrlzPacketNodePredicate<D, O>
{

  public WkSrlzReadingPacketNodePredicate(
      D structDefinition, Predicate<? super O> readingPredicate, String description) {
    super(structDefinition, readingPredicate, description);
  }

  @Override
  public boolean deserializedIsRequired() {
    return true;
  }

  @Override
  public boolean canBeTestedAgainst(WkSerdeDtreeNodeDataFilterable node) {
    if (isAnInputPacketReadingNode(node)) {
      WkSerdeDtreeNodeStructDefinition<?> structDef = extractDefinition((WkSerdeDtreeNodeDataReader<?,?,?,?,?>)node);
      return targetProtocolField().equals(structDef);
    }
    return false;
  }

}
