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
package weliyek.amat.base.input;

import java.util.List;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat2.protocol.filter.FilterableMessageSegment;

public interface DeserializingOperation<
                        T,
                        XS extends OperationSettings,
                        XQ extends DeserializingRuntime<?>,
                        XR extends DeserializingResult<T>,
                        XD extends DefinitionSegment<T,?>>
        extends OperationSegment<
                        XS, XQ, XR, XD,
                        DeserializingField<T,XD,?>>,
                FilterableMessageSegment,
                DeserializingSegment
{

  @Override
  List<DeserializingSubfieldHandler<?,?,?>> subfields();

}
