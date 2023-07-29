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
package weliyek.amat.base.output;

import java.util.Optional;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSegment;

public interface OutputPacket<
                        T,
                        D extends DefinitionSegment<T,?>,
                        O extends SerializingOperation<T,?,?,?,D>>
    extends SerializingField<T, D, O>
{

  void processBytestream();

  Optional<OperationSegment<?,?,?,?,?>> previousProcessingSteapResult();

}
