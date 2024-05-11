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

import weliyek.serialization.tree.WkSerdeDtreeStruct;

public interface WkSerdeDtreeStructDefinition<T>
  extends WkSerdeDtreeStruct,
          WkSerdeDtreeDatatype
{

  Class<T> serializableClass();

  List<WkSerdeDtreeStructField<?>> subfields();

  default boolean isASubfield(WkSerdeDtreeStructDefinition<?> testedDefinition) {
    Predicate<WkSerdeDtreeStructField<?>> test = new Predicate<WkSerdeDtreeStructField<?>>() {
      @Override
      public boolean test(WkSerdeDtreeStructField<?> t) {
        return testedDefinition.equals(t.definition());
      }
    };
    return visitSubfieldsBreadthFirst(test);
  }

  default boolean visitSubfieldsBreadthFirst(Predicate<WkSerdeDtreeStructField<?>> handlerTester) {
    for (WkSerdeDtreeStructField<?> handler : subfields()) {
      if(handlerTester.test(handler)) {
        return true;
      }
    }
    for (WkSerdeDtreeStructField<?> handler : subfields()) {
      if (handler.definition().visitSubfieldsBreadthFirst(handlerTester)) {
        return true;
      }
    }
    return false;
  }

  default boolean visitSubfieldsDepthFirst(Predicate<WkSerdeDtreeStructField<?>> handlerTester) {
    for (WkSerdeDtreeStructField<?> handler : subfields()) {
      if(handlerTester.test(handler)) {
        return true;
      }
      if (handler.definition().visitSubfieldsDepthFirst(handlerTester)) {
        return true;
      }
    }
    return false;
  }

}
