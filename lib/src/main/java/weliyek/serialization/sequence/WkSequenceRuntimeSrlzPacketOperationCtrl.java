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

import weliyek.serialization.WkSzBytestream;
import weliyek.serialization.WkSzBytestreamBase;
import weliyek.serialization.WkCommonRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkRuntimeSrlzPacketOperationCtrl;

public interface WkSequenceRuntimeSrlzPacketOperationCtrl<
                        B extends WkSzBytestream,
                        BC extends WkSzBytestreamBase<?, ? extends B>,
                        Q extends WkCommonRuntimeSrlzPacketOperationData<B>>
    extends WkRuntimeSrlzPacketOperationCtrl<B, BC, Q>,
            WkCommonSequenceRuntimeSrlzPacketOperationData<B>,
            WkSequenceOnlySrlzPacketOperationCtrl
{

}
