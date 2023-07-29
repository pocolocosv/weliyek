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

import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat2.protocol.filter.FieldTester;

public interface DefinitionSegment<
                        T,
                        XO extends DeserializingOperation<T,?,?,?,?>>
  extends StructureSegment,
          CustomSegment
{

  Class<T> rxClass();

  List<SubcomponentHandler<?,?,?>> subfields();

  default boolean isASubfield(DefinitionSegment<?,?> testedDefinition) {
    Predicate<SubcomponentHandler<?,?,?>> test = new Predicate<SubcomponentHandler<?,?,?>>() {
      @Override
      public boolean test(SubcomponentHandler<?, ?, ?> t) {
        return testedDefinition.equals(t.field().definition());
      }
    };
    return visitSubfieldsBreadthFirst(test);
  }

  default boolean visitSubfieldsBreadthFirst(Predicate<SubcomponentHandler<?,?,?>> handlerTester) {
    for (SubcomponentHandler<?,?,?> handler : subfields()) {
      if(handlerTester.test(handler)) {
        return true;
      }
    }
    for (SubcomponentHandler<?,?,?> handler : subfields()) {
      if (handler.field().definition().visitSubfieldsBreadthFirst(handlerTester)) {
        return true;
      }
    }
    return false;
  }

  default boolean visitSubfieldsDepthFirst(Predicate<SubcomponentHandler<?,?,?>> handlerTester) {
    for (SubcomponentHandler<?,?,?> handler : subfields()) {
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
    return SegmentType.PACKETSTRUCT;
  }

}
