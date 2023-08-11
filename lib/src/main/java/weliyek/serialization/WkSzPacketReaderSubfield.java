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

public interface WkSzPacketReaderSubfield<
                        T,
                        XD extends WkSzDefinition<T,?>,
                        XO extends WkSzPacketReaderOperation<T,?,?,?,XD>>
    extends WkSzPacketSubfield<WkSzPacketReaderField<T, XD, XO>>,
            WkSzPacketReaderSegment
{

  /**
   * Indicates if the protocol itself requires the deserialized value such as
   * the size of a field to be read later. <b>Note</b> that deserialization will
   * only be performed if the field is enabled.
   * @return True if deserialized will be created. False otherwise.
   */
  boolean deserializedRequiredByProtocol();

}
