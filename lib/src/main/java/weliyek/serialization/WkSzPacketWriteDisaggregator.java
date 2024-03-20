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

@FunctionalInterface
public interface WkSzPacketWriteDisaggregator<
                        T,
                        D extends WkSerdeDTreeNodeStructDefinition<T>,
                        AT,
                        AO extends WkSerdeDTreeNodeDataWriter<? extends AT,?,?,?,?>>
{

  /**
   * Extracts serializable value from the parent operation.
   *
   * @param packetField The current writing field that owns the writing operation being created.
   * @param parentOperation The parent operation used to extract the disaggregated value from.
   * @param i The index on the current writing operation in the writing field.
   * @return The disaggregated value that will be used by the writing operation.
   */
  T disaggregate(
    WkSrlzOutputPacketFieldFrameNode<T,D,?> packetField,
    AO parentOperation,
    int i);

}
