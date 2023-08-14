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

import java.util.List;
import java.util.Optional;

/**
 * Handles the serialization process of the associated {@link WkSrlzStructComponentFrameNode}.
 * 
 * @param <T> Serialization target type.
 * @param <O> Reading or writing operation type for the handled data type.
 * @param <D> The handled data type.
 */
public interface WkSrlzPacketFieldFrameNode<
                        T,
                        O extends WkSrlzPacketOperationFrameNode<?,?,?,?,?>,
                        D extends WkSrlzStructDefinitionFrameNode<?,?>>
    extends WkSrlzPacketFrameNode,
            WkSrlzCtrlFrameNode
{

  WkSrlzStructComponentFrameNode<? extends D> structComponent();

  String name();

  @Override
  default WkSrlzFrameNodeType type() {
    return WkSrlzFrameNodeType.PACKET;
  }

  int expectedNumberOfOperations();

  // Operations access.

  List<O> operationList();

  List<T> collectAllOperationValues();
  
  boolean isCompleted();
  
  default boolean isInProgress() {
    return ! isCompleted();
  }
  
  boolean isEnabled();

  default Optional<O> firstOperation() {
    return Optional.ofNullable(operationList().isEmpty()?null:operationList().get(0));
  }

}
