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
package weliyek.amat.basic.aggregator.sequence;

import java.util.Collection;

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.basic.sequence.SequenceWritingRuntime;
import weliyek.amat.basic.sequence.SequenceWritingRuntimeControl;

public final class SimplifiedCollectionSerializingCore<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YD extends WkSzCollectionAndElementsDefinition<T,?,YO,ET,?>,
                        YO extends CollectionAndElementsFieldSerializer<
                                        T,
                                        YS,
                                        SequenceWritingRuntime<WkSzOutputBytestream>,
                                        SerializingResult,
                                        YD,
                                        ET,EYD,EYO>,
                        ET,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>>
    extends CollectionAndElementsFieldSerializerCore<
                        T,
                        YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        SequenceWritingRuntime<WkSzOutputBytestream>,
                        SequenceWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          SequenceWritingRuntime<WkSzOutputBytestream>>,
                        SerializingResult,
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
