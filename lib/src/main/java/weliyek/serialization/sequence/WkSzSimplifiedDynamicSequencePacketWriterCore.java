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
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;
import weliyek.util.array.WkSzDynamicSequencePacketWriterOperationCore;

public final class WkSzSimplifiedDynamicSequencePacketWriterCore<
                        T extends Collection<ET>,
                        YS extends WkSzOperationSettings,
                        YO extends WkSzDynamicCollectionPacketWriterOperation<
                                        T,YS,
                                        WkSzWritingRuntime<WkSzOutputBytestream>,
                                        WkSzWritingResult,
                                        YD,ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD extends WkSzDynamicCollectionDefinition<
                                        T,?,YO,?,ET,?,?,?,EYS,?,EYO,?,?,VYS>,
                        ZT extends Number,
                        ZYS extends WkSzOperationSettings,
                        ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EYS extends WkSzOperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        VYS extends WkSzOperationSettings>
    extends WkSzDynamicSequencePacketWriterOperationCore<
                        T, YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          WkSzWritingRuntime<WkSzOutputBytestream>>,
                        WkSzWritingResult,
                        YO,
                        WkSzSimplifiedDynamicSequencePacketWriterCore<
                          T,YS,YO,YD,ZT,ZYS,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT, ZYS, ZYO, ZYD, VYS,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>, // VYD
                        WkSzSimplifiedDynamicCollectionStructDefinition<
                          T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS,?>>
    implements WkSzDynamicCollectionPacketWriterOperation<
                        T,
                        YS,
                        WkSzWritingRuntime<WkSzOutputBytestream>,
                        WkSzWritingResult,
                        YD, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
{

  public WkSzSimplifiedDynamicSequencePacketWriterCore(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<T, ?, YD, ?, ?, ?> packetHandlerCore,
    WkSzSimplifiedDynamicCollectionStructDefinition<T, ?, ?, ?, YS, YO, YD, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS, ?> definitionCore,
    YO operationBody) {
    super(
          index,
          serializable,
          settings,
          parentBytestream,
          packetHandlerCore,
          definitionCore,
          operationBody);
  }

  @Override
  protected
  WkSzSimplifiedDynamicSequencePacketWriterCore<T, YS, YO, YD, ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
  getThis() {
    return this;
  }

}
