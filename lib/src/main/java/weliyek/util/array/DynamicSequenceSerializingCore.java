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
package weliyek.util.array;

import weliyek.serialization.WkSzAggregatorWriterCore;
import weliyek.serialization.WkSzOperationSettings;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSzPacketWriterFieldCore;
import weliyek.serialization.WkSzPacketWriterSubfield;
import weliyek.serialization.WkSzPacketWriterSubfieldCore;
import weliyek.serialization.WkSzWritingResult;
import weliyek.serialization.WkSzWritingRuntime;
import weliyek.serialization.WkSzWritingRuntimeControl;
import weliyek.serialization.number.WkSzNumberDefinition;
import weliyek.serialization.number.WkSzNumberWriter;
import weliyek.serialization.sequence.VariableSizeSequenceWriting;
import weliyek.serialization.sequence.WkSzVariableSizeSequenceDefinition;

public abstract class DynamicSequenceSerializingCore<
                        T,
                        YS extends WkSzOperationSettings,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends WkSzWritingRuntime<YB>,
                        YQC extends WkSzWritingRuntimeControl<YB,YBC,YQ>,
                        YR extends WkSzWritingResult,
                        YO extends DynamicSequenceSerializing<
                                        T,YS,YQ,YR,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YOC extends DynamicSequenceSerializingCore<
                                        T,YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYBC,
                                        ZT,ZYS,ZYO,ZYD,
                                        VYS,VYO,VYD,
                                        DC>,
                        YD extends WkSzDynamicSequenceDefinition<T,?,YO,?,?>,
                        AYBC extends WkSzOutputBytestreamBase<?>,
                        ZT extends Number,
                        ZYS extends WkSzOperationSettings,
                        ZYO extends WkSzNumberWriter<ZT,ZYS,?,?,ZYD>,
                        ZYD extends WkSzNumberDefinition<ZT,?>,
                        VYS extends WkSzOperationSettings,
                        VYO extends VariableSizeSequenceWriting<T,VYS,?,?,VYD>,
                        VYD extends WkSzVariableSizeSequenceDefinition<T,?>,
                        DC extends DynamicSequenceDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYBC,
                                        ZT,?,?,?,ZYS,ZYO,ZYD,
                                        ?,?,?,?,VYS,VYO,VYD,
                                        ?,?,DC>>
    extends WkSzAggregatorWriterCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYBC, DC>
    implements DynamicSequenceSerializing<T, YS, YQ, YR, YD, ZT, ZYO, ZYD, VYO, VYD>
{

  private WkSzPacketWriterSubfieldCore<ZT,ZYS,ZYD,ZYO,T,?,YD,YO> sizeWriteField;
  private WkSzPacketWriterSubfieldCore<T,VYS,VYD,VYO,T,?,YD,YO> varseqWriteField;

  protected DynamicSequenceSerializingCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.sizeWriteField = getSubfieldpacketFor(definitionCore().sizeComponent);
    this.varseqWriteField = getSubfieldpacketFor(definitionCore().varseqComponent);
  }

  @Override
  protected void onAggregatorInitialization() {
  }

  @Override
  public WkSzPacketWriterSubfield<ZT, ZYD, ZYO> size() {
    return this.sizeWriteField.asSubfield();
  }

  @Override
  public WkSzPacketWriterSubfield<T, VYD, VYO> variableSequence() {
    return this.varseqWriteField.asSubfield();
  }

}