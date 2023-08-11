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
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterOperation;
import weliyek.serialization.WkSzSequenceWritingRuntime;
import weliyek.serialization.WkSzSequenceWritingRuntimeControl;
import weliyek.serialization.WkSzWritingResult;

public final class SimplifiedCollectionSerializingCore<
                        T extends Collection<ET>,
                        YS extends WkSzOperationSettings,
                        YD extends WkSzCollectionAndElementsDefinition<T,?,YO,ET,?>,
                        YO extends CollectionAndElementsFieldSerializer<
                                        T,
                                        YS,
                                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,
                                        ET,EYD,EYO>,
                        ET,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>>
    extends CollectionAndElementsFieldSerializerCore<
                        T,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzSequenceWritingRuntime<WkSzOutputBytestream>,
                        WkSzSequenceWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzSequenceWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YD,
                        YO,
                        SimplifiedCollectionSerializingCore<T,YS,YD,YO,ET,EYS,EYD,EYO>,
                        WkSzOutputBytestreamBase<?>,
                        ET,
                        EYS,
                        EYD,
                        EYO,
                        SimplifiedCollectionDefinitionCore<
                          T,?,?,?,YS,YD,YO,
                          ET,?,?,?,EYS,EYD,EYO,?,?>>
{

  SimplifiedCollectionSerializingCore(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
    SimplifiedCollectionDefinitionCore<
      T,?,?,?,YS,YD,YO,ET,?,?,?,EYS,EYD,EYO,?,?> definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
  }

  @Override
  protected void onCollectionWritingInitialization() {
    // Nothing to do.

  }

  @Override
  protected SimplifiedCollectionSerializingCore<T,YS,YD,YO,ET,EYS,EYD,EYO> getThis() {
    return this;
  }

}
