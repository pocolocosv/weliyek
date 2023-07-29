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
import java.util.Optional;

import weliyek.ketza.base.Completable;
import weliyek.ketza.base.Enableable;

public interface FieldSegment<
                        T,
                        O extends OperationSegment<?,?,?,?,?>,
                        D extends DefinitionSegment<?,?>>
    extends DataSegment,
            FixedSegment,
            Completable,
            Enableable
{

  ComponentSegment<? extends D> protocolField();

  String name();

  @Override
  default SegmentType type() {
    return SegmentType.PACKET;
  }

  int expectedNumberOfOperations();

  // Operations access.

  List<O> operationList();

  List<T> collectAllOperationValues();

  default Optional<O> firstOperation() {
    return Optional.ofNullable(operationList().isEmpty()?null:operationList().get(0));
  }

}
