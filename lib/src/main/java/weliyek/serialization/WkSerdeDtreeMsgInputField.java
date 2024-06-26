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

import weliyek.serialization.filter.WkSerdeDtreeMsgFilterable;

public interface WkSerdeDtreeMsgInputField<
                        T,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XO extends WkSerdeDtreeMsgReader<T,?,?,?,XD>>
    extends WkSerdeDtreeMsgField<T, XO, XD>,
            WkSerdeDtreeMsgFilterable,
            WkSerdeDtreeMsgInput
{

  /**
   * Indicates if the filter passed when creating the reading object requires
   * the deserialized value. <b>Note</b> that deserialization will
   * only be performed if the field is enabled.
   * @return True if deserialized will be created. False otherwise.
   */
  boolean deserializedRequiredByFilter();

  /* *
   * A deserialized value will be created. <b>Note</b> that deserialization will
   * only be performed if the field is enabled.
   * @return True if deserialized will be created. False otherwise.

   */
  boolean deserializedRequired();

}
