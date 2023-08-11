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

import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderField;
import weliyek.serialization.WkSzPacketReaderOperation;
import weliyek.serialization.WkSzPacketReaderSubfield;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzSequenceReadingRuntime;

public interface CollectionAndElementsFieldDeserializer<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        XQ extends WkSzSequenceReadingRuntime<?>,
                        XR extends WkSzReadingResult<T>,
                        XD extends WkSzCollectionAndElementsDefinition<T,?,?,ET,?>,
                        ET,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,?,?,?,EXD>>
        extends WkSzCollectionAndElementsOperation<
                        XS, XQ, XR, XD,
                        WkSzPacketReaderField<T,XD,?>,
                        EXO,
                        WkSzPacketReaderField<ET,EXD,EXO>,
                        WkSzPacketReaderSubfield<ET,EXD,EXO>>,
                WkSzCollectionReader<T, XS, XQ, XR, XD>
{

}
