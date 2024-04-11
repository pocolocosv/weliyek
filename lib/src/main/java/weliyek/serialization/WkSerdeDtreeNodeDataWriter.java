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

public interface WkSerdeDtreeNodeDataWriter<
                        T,
                        S extends WkSerdeDtreeOperationSettings,
                        Q extends WkSerdeDtreeOperationOutputRuntime<?>,
                        R extends WkSerdeDtreeOperationResult<T>,
                        D extends WkSerdeDtreeNodeStructDefinition<T>>
        extends WkSerdeDtreeNodeDataOperation<
                        S, Q, R, D,
                        WkSerdeDtreeNodeDataOutputComponent<T,D,?>>,
                WkSerdeDtreeNodeDataOutput
{

  T serializable();

  @Override
  List<WkSrlzOutputPacketSubfieldFrameNode<?,?,?>> subfields();

}
