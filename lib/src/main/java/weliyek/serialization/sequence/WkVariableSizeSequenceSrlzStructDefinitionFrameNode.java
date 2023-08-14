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
package weliyek.serialization.sequence;

public interface WkVariableSizeSequenceSrlzStructDefinitionFrameNode<
                        T,
                        XO extends WkVariableSizeSequenceSrlzInputPacketDecoderFrameNode<T,?,?,?,?>>
        extends WkVariableSizeSequenceSrlzFrameNode,
                WkSequenceSrlzStructDefinitionFrameNode<T,XO>
{

  /**
   * Any sequence handled by this field must be higher or equal to the returned value if
   * this one is non-negative. If negative, no checking is done against the sequence for a
   * minimal size.
   *
   * @return A non-negative value if a minimal size checking is performed. Negative if no
   *         checking is done.
   */
  int minimalSize();

  /**
   * Any sequence handled by this field must be smaller or equal to the returned value if
   * this one is non-negative. If negative, no checking is done against the sequence for a
   * maximal size.
   *
   * @return A non-negative value if a maximal size checking is performed. Negative if no
   *         checking is done.
   */
  int maximalSize();

}
