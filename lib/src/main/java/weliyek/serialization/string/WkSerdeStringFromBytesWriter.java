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
package weliyek.serialization.string;

import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataOutputComponent;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeByteArrayDefinition;
import weliyek.util.array.WkSerdeDtreeByteArrayWriter;

public interface WkSerdeStringFromBytesWriter<
                        YS extends WkSerdeDtreeOperationSettings,
                        YQ extends WkSerdeDtreeOperationOutputRuntime<?>,
                        YR extends WkSerdeDtreeOperationResult<String>,
                        YD extends WkSerdeStringFromBytesDefinition<?,?,? extends SD>,
                        SD extends WkSerdeDtreeByteArrayDefinition,
                        SYO extends WkSerdeDtreeByteArrayWriter<?,?,?,SD>>
    extends WkSerdeStringFromPrimitiveArrayWriter<
                        YS, YQ, YR, YD,
                        WkByteArray, SD, SYO>,
            WkSerdeStringFromBytesOperation<
                        YS, YQ, YR, YD,
                        WkSerdeDtreeNodeDataOutputComponent<String,YD,?>,
                        SYO,
                        WkSerdeDtreeNodeDataOutputComponent<WkByteArray,SD,SYO>>
{

}
