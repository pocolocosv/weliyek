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

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.output.SerializingOperation;

public interface SubcomponentHandler<
                        AXO extends DeserializingOperation<?,?,?,?,?>,
                        AYO extends SerializingOperation<?,?,?,?,?>,
                        D extends DefinitionSegment<?,?>>
    extends StructureSegment,
            FixedSegment
{

  default boolean isRxRequired() { return rxOptionalityTest().isEmpty(); }

  default boolean isRxOptional() { return ! isRxRequired(); }

  default boolean isTxRequired() { return txOptionalityTest().isEmpty(); }

  default boolean isTxOptional() { return ! isTxRequired(); }

  /**
   * The presence of a test indicates that the test must be performed with the parent
   * operation to discover if the subfield is to be activated or not. If a test is not
   * present then the subfield is required and it will always be processed.
   * @return The optionality test.
   */
  Optional<Predicate<? super AXO>> rxOptionalityTest();

  Optional<Predicate<? super AYO>> txOptionalityTest();

  ToIntFunction<? super AXO> numberOfRxOperationsEvaluator();

  ToIntFunction<? super AYO> numberOfTxOperationsEvaluator();

  ComponentSegment<D> field();

  /**
   * Subcomponents are executed in the order as chosen when adding them to the
   * aggregator parent definition.
   * @return
   */
  int executionOrder();

}
