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

import weliyek.serialization.WkSzOperationResult;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketField;
import weliyek.serialization.WkSzPacketOperation;
import weliyek.serialization.WkSzPacketSubfield;

public interface WkSzCollectionAndElementsOperation<
                        S extends WkSzOperationSettings,
                        Q extends WkSzCommonSequenceOperationRuntime<?>,
                        R extends WkSzOperationResult,
                        D extends WkSzCollectionAndElementsDefinition<?,?,?,?,?>,
                        K extends WkSzPacketField<?,?,?>,
                        EO extends WkSzPacketOperation<?,?,?,?,?>,
                        EK extends WkSzPacketField<?,EO,?>,
                        EJ extends WkSzPacketSubfield<EK>>
        extends WkSzCollectionAndElementsSegment<EJ>,
                WkSzCollectionOperation<S, Q, R, D, K>
{

}