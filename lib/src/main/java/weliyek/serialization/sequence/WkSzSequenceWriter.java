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

import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketWriterField;
import weliyek.serialization.WkSzPacketWriterOperation;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;

public interface WkSzSequenceWriter<
                        T,
                        YS extends WkSzOperationSettings,
                        YQ extends WkSzWritingRuntime<?>,
                        YR extends WkSzWritingResult,
                        YD extends WkSzSequenceDefinition<T,?>>
        extends WkSzPacketWriterOperation<T, YS, YQ, YR, YD>,
                WkSzSequenceOperation<
                        YS, YQ, YR, YD,
                        WkSzPacketWriterField<T,YD,?>>
{

}
