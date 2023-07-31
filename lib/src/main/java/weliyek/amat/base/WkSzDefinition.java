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
package weliyek.amat.base;

import java.util.List;
import java.util.function.Predicate;

import weliyek.amat.base.input.WkSzPacketReaderOperation;
import weliyek.amat2.protocol.filter.FieldTester;

public interface WkSzDefinition<
                        T,
                        XO extends WkSzPacketReaderOperation<T,?,?,?,?>>
  extends WkSzStructSegment,
          WkSzDatatypeSegment
{

  Class<T> rxClass();

  List<WkSzStructSubcomponent<?,?,?>> subfields();

  default boolean isASubfield(WkSzDefinition<?,?> testedDefinition) {
    Predicate<WkSzStructSubcomponent<?,?,?>> test = new Predicate<WkSzStructSubcomponent<?,?,?>>() {
      @Override
      public boolean test(WkSzStructSubcomponent<?, ?, ?> t) {
        return testedDefinition.equals(t.field().definition());
      }
    };
    return visitSubfieldsBreadthFirst(test);
  }

  default boolean visitSubfieldsBreadthFirst(Predicate<WkSzStructSubcomponent<?,?,?>> handlerTester) {
    for (WkSzStructSubcomponent<?,?,?> handler : subfields()) {
      if(handlerTester.test(handler)) {
        return true;
      }
    }
    for (WkSzStructSubcomponent<?,?,?> handler : subfields()) {
      if (handler.field().definition().visitSubfieldsBreadthFirst(handlerTester)) {
        return true;
      }
    }
    return false;
  }

  default boolean visitSubfieldsDepthFirst(Predicate<WkSzStructSubcomponent<?,?,?>> handlerTester) {
    for (WkSzStructSubcomponent<?,?,?> handler : subfields()) {
      if(handlerTester.test(handler)) {
        return true;
      }
      if (handler.field().definition().visitSubfieldsDepthFirst(handlerTester)) {
        return true;
      }
    }
    return false;
  }


  FieldTester<?,?> makeTester(Predicate<? super XO> test, String description);

  @Override
  default SegmentType type() {
    return SegmentType.STRUCT;
  }

}
