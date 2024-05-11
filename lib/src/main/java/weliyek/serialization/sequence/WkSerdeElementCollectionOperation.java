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

import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeMsgField;
import weliyek.serialization.WkSerdeDtreeMsgOperation;
import weliyek.serialization.WkSerdeDtreeNodeDataComponentHandler;

public interface WkSerdeElementCollectionOperation<
                        S extends WkSerdeDtreeOperationSettings,
                        Q extends WkSerdeDtreeOperationRuntimeSequenceCommon<?>,
                        R extends WkSerdeDtreeOperationResult<?>,
                        D extends WkSerdeElementCollectionDefinition<?,?,?,?,?>,
                        K extends WkSerdeDtreeMsgField<?,?,?>,
                        EO extends WkSerdeDtreeMsgOperation<?,?,?,?,?>,
                        EK extends WkSerdeDtreeMsgField<?,EO,?>,
                        EJ extends WkSerdeDtreeNodeDataComponentHandler<EK>>
        extends WkSerdeElementCollection<EJ>,
                WkSerdeDtreeCollectionOperation<S, Q, R, D, K>
{

}