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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.number.WkSzNumberDefinition;
import weliyek.amat.basic.number.WkSzNumberWriter;
import weliyek.ketza.util.array.DynamicSequenceSerializingCore;
import weliyek.serialization.WkSzDefinition;

public final class SimplifiedDynamicCollectionFieldSerializer<
                        T extends Collection<ET>,
                        YS extends OperationSettings,
                        YO extends DynamicCollectionFieldSerializer<
                                        T,YS,
                                        SerializingRuntime<WkSzOutputBytestream>,
                                        SerializingResult,
                                        YD,ZT,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD extends WkSzDynamicCollectionDefinition<
                                        T,?,YO,?,ET,?,?,?,EYS,?,EYO,?,?,VYS>,
                        ZT extends Number,
                        ZYS extends OperationSettings,
                        ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        ET,
                        EYS extends OperationSettings,
                        EYD extends WkSzDefinition<ET,?>,
                        EYO extends WkSzPacketWriterOperation<ET,EYS,?,?,EYD>,
                        VYS extends OperationSettings>
    extends DynamicSequenceSerializingCore<
                        T, YS,
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        SerializingRuntime<WkSzOutputBytestream>,
                        WritingRuntimeControl<
                          WkSzOutputBytestream,
                          WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                          SerializingRuntime<WkSzOutputBytestream>>,
                        SerializingResult,
                        YO,
                        SimplifiedDynamicCollectionFieldSerializer<
                          T,YS,YO,YD,ZT,ZYS,ZYO,ZYD,ET,EYS,EYD,EYO,VYS>,
                        YD,
                        WkSzOutputBytestreamBase<?>,
                        ZT, ZYS, ZYO, ZYD, VYS,
                        VariableSizeCollectionFieldSerializer<T,VYS,ET,EYS,EYD,EYO>, // VYO
                        VariableSizeCollectionField<T,?,VYS,ET,?,?,?,EYS,EYD,EYO,?>, // VYD
                        SimplifiedDynamicCollectionDefinitionCore<
                          T,?,?,?,YS,YO,YD,ZT,?,?,?,ZYS,ZYO,ZYD,?,ET,?,?,?,EYS,EYD,EYO,?,?,VYS,?>>
    implements DynamicCollectionFieldSerializer<
                        T,
                        YS,
                        SerializingRuntime<WkSzOutputBytestream>,
                        SerializingResult,
                        YD, ZT, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
{

  public SimplifiedDynamicCollectionFieldSerializer(
    int index,
    T serializable,
    YS settings,
    WkSzOutputBytestreamBase<?> parentBytestream,
    WkSzPacketWriterFieldCore<T, ?, YD, ?, ?, ?> packetHandlerCore,
    SimplifiedDynamicCollectionDefinitionCore<T, ?, ?, ?, YS, YO, YD, ZT, ?, ?, ?, ZYS, ZYO, ZYD, ?, ET, ?, ?, ?, EYS, EYD, EYO, ?, ?, VYS, ?> definitionCore,
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
  SimplifiedDynamicCollectionFieldSerializer<T, YS, YO, YD, ZT, ZYS, ZYO, ZYD, ET, EYS, EYD, EYO, VYS>
  getThis() {
    return this;
  }

}
