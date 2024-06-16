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

import java.util.Collection;

import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgReader;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStructDefinition;

public interface WkSerdeElementCollectionReader<
                        T extends Collection<ET>,
                        XS extends WkSerdeDtreeOperationSettings,
                        XQ extends WkSerdeDtreeOperationInputRuntimeSequenceCommon<?>,
                        XR extends WkSerdeDtreeOperationResult<T>,
                        XD extends WkSerdeElementCollectionDefinition<T,?,?,ET,?>,
                        ET,
                        EXD extends WkSerdeDtreeStructDefinition<ET>,
                        EXO extends WkSerdeDtreeMsgReader<ET,?,?,?,EXD>>
        extends WkSerdeElementCollectionOperation<
                        XS, XQ, XR, XD,
                        WkSerdeDtreeMsgInputField<ET, EXD, EXO>>,
                WkSerdeDtreeCollectionReader<T, XS, XQ, XR, XD>
{

}
