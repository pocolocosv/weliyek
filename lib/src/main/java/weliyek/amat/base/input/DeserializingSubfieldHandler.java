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

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.SubfieldHandler;

public interface DeserializingSubfieldHandler<
                        T,
                        XD extends DefinitionSegment<T,?>,
                        XO extends DeserializingOperation<T,?,?,?,XD>>
    extends SubfieldHandler<DeserializingField<T, XD, XO>>,
            DeserializingSegment
{

  /**
   * Indicates if the protocol itself requires the deserialized value such as
   * the size of a field to be read later. <b>Note</b> that deserialization will
   * only be performed if the field is enabled.
   * @return True if deserialized will be created. False otherwise.
   */
  boolean deserializedRequiredByProtocol();

}
