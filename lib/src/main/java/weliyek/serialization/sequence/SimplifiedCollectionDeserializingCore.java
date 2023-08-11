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
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzPacketReaderFieldCore;
import weliyek.serialization.WkSzPacketReaderOperation;
import weliyek.serialization.WkSzReadingResult;
import weliyek.serialization.WkSzSequenceReadingRuntime;
import weliyek.serialization.WkSzSequenceReadingRuntimeControl;

public final class SimplifiedCollectionDeserializingCore<
                        T extends Collection<ET>,
                        XS extends WkSzOperationSettings,
                        XD extends WkSzCollectionAndElementsDefinition<T,XO,?,ET,?>,
                        XO extends CollectionAndElementsFieldDeserializer<
                                        T,
                                        XS,
                                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                                        WkSzReadingResult<T>,
                                        XD,
                                        ET,EXD,EXO>,
                        ET,
                        EXS extends WkSzOperationSettings,
                        EXD extends WkSzDefinition<ET,?>,
                        EXO extends WkSzPacketReaderOperation<ET,EXS,?,?,EXD>>
    extends CollectionAndElementsFieldDeserializerCore<
                        T,
                        XS,
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzSequenceReadingRuntime<WkSzInputBytestream>,
                        WkSzSequenceReadingRuntimeControl<
                          WkSzInputBytestream,
                          WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                          WkSzSequenceReadingRuntime<WkSzInputBytestream>>,
                        WkSzReadingResult<T>,
                        XD,
                        XO,
                        SimplifiedCollectionDeserializingCore<T,XS,XD,XO,ET,EXS,EXD,EXO>,
                        WkSzInputBytestreamBase<?>,
                        ET,
                        EXS,
                        EXD,
                        EXO,
                        SimplifiedCollectionDefinitionCore<T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,?>>
{

  SimplifiedCollectionDeserializingCore(
    int index,
    XS settings,
    WkSzInputBytestreamBase<?> parentBytestream,
    WkSzPacketReaderFieldCore<T,?,XD,?,?,?> packetfieldCore,
    SimplifiedCollectionDefinitionCore<
      T,XS,XD,XO,?,?,?,ET,EXS,EXD,EXO,?,?,?,?,?> definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
  }

  @Override
  protected void onCollectionReadingInitialization() {
    // Nothing to do.
  }

  @Override
  protected SimplifiedCollectionDeserializingCore<T,XS,XD,XO,ET,EXS,EXD,EXO> getThis() {
    return this;
  }

}