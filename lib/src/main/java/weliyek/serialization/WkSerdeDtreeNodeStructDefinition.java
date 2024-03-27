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
package weliyek.serialization;

import java.util.List;
import java.util.function.Predicate;

import weliyek.serialization.tree.WkSerdeDtreeNodeStruct;

public interface WkSerdeDtreeNodeStructDefinition<T>
  extends WkSerdeDtreeNodeStruct,
          WkSerdeDtreeNodeType
{

  Class<T> serializableClass();

  List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> subfields();

  default boolean isASubfield(WkSerdeDtreeNodeStructDefinition<?> testedDefinition) {
    Predicate<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> test = new Predicate<WkSerdeDtreeNodeStructComponentHandler<?,?,?>>() {
      @Override
      public boolean test(WkSerdeDtreeNodeStructComponentHandler<?, ?, ?> t) {
        return testedDefinition.equals(t.field().definition());
      }
    };
    return visitSubfieldsBreadthFirst(test);
  }

  default boolean visitSubfieldsBreadthFirst(Predicate<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> handlerTester) {
    for (WkSerdeDtreeNodeStructComponentHandler<?,?,?> handler : subfields()) {
      if(handlerTester.test(handler)) {
        return true;
      }
    }
    for (WkSerdeDtreeNodeStructComponentHandler<?,?,?> handler : subfields()) {
      if (handler.field().definition().visitSubfieldsBreadthFirst(handlerTester)) {
        return true;
      }
    }
    return false;
  }

  default boolean visitSubfieldsDepthFirst(Predicate<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> handlerTester) {
    for (WkSerdeDtreeNodeStructComponentHandler<?,?,?> handler : subfields()) {
      if(handlerTester.test(handler)) {
        return true;
      }
      if (handler.field().definition().visitSubfieldsDepthFirst(handlerTester)) {
        return true;
      }
    }
    return false;
  }

}
