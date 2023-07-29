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
package weliyek.ketza.util.array;

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.base.output.SerializingSubfieldHandlerCore;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.amat.basic.aggregator.AggregatorWritingCore;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceDefinition;
import weliyek.amat.basic.dynamic.sequence.VariableSizeSequenceWriting;
import weliyek.amat.basic.number.NumberDefinition;
import weliyek.amat.basic.number.NumberSerializing;

public abstract class DynamicSequenceSerializingCore<
                        T,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQ extends SerializingRuntime<YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,YQ>,
                        YR extends SerializingResult,
                        YO extends DynamicSequenceSerializing<
                                        T,YS,YQ,YR,YD,ZT,ZYO,ZYD,VYO,VYD>,
                        YOC extends DynamicSequenceSerializingCore<
                                        T,YS,YB,YBC,YQ,YQC,YR,YO,?,YD,AYBC,
                                        ZT,ZYS,ZYO,ZYD,
                                        VYS,VYO,VYD,
                                        DC>,
                        YD extends DynamicSequenceDefinition<T,?,YO,?,?>,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        ZT extends Number,
                        ZYS extends OperationSettings,
                        ZYO extends NumberSerializing<ZT,ZYS,?,?,ZYD>,
                        ZYD extends NumberDefinition<ZT,?>,
                        VYS extends OperationSettings,
                        VYO extends VariableSizeSequenceWriting<T,VYS,?,?,VYD>,
                        VYD extends VariableSizeSequenceDefinition<T,?>,
                        DC extends DynamicSequenceDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YO,YD,AYBC,
                                        ZT,?,?,?,ZYS,ZYO,ZYD,
                                        ?,?,?,?,VYS,VYO,VYD,
                                        ?,?,DC>>
    extends AggregatorWritingCore<T, YS, YB, YBC, YQ, YQC, YR, YD, YO, YOC, AYBC, DC>
    implements DynamicSequenceSerializing<T, YS, YQ, YR, YD, ZT, ZYO, ZYD, VYO, VYD>
{

  private SerializingSubfieldHandlerCore<ZT,ZYS,ZYD,ZYO,T,?,YD,YO> sizeWriteField;
  private SerializingSubfieldHandlerCore<T,VYS,VYD,VYO,T,?,YD,YO> varseqWriteField;

  protected DynamicSequenceSerializingCore(
    int index,
    T serializable,
    YS settings,
    AYBC parentBytestream,
    SerializingFieldCore<T,?,YD,?,?,?> packetHandlerCore,
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
  public SerializingSubfieldHandler<ZT, ZYD, ZYO> size() {
    return this.sizeWriteField.asSubfield();
  }

  @Override
  public SerializingSubfieldHandler<T, VYD, VYO> variableSequence() {
    return this.varseqWriteField.asSubfield();
  }

}
