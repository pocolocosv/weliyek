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
package weliyek.amat.basic.aggregator;

import java.util.List;
import java.util.Optional;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.OutputBytestream;
import weliyek.amat.base.output.OutputBytestreamGeneralBase;
import weliyek.amat.base.output.SerializingFieldCore;
import weliyek.amat.base.output.SerializingOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.SerializingSubfieldHandler;
import weliyek.amat.base.output.SerializingSubfieldHandlerCore;
import weliyek.amat.base.output.WritingOperationCore;
import weliyek.amat.base.output.WritingRuntimeControl;

public abstract class AggregatorWritingCore<
                        T,
                        YS extends OperationSettings,
                        YB extends OutputBytestream,
                        YBC extends OutputBytestreamGeneralBase<? extends YB>,
                        YQ extends SerializingRuntime<YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,YQ>,
                        YR extends SerializingResult,
                        YD extends AggregatorDefinition<T,?>,
                        YO extends AggregatorWriting<T,YS,YQ,YR,YD>,
                        YOC extends AggregatorWritingCore<T,YS,YB,YBC,YQ,YQC,YR,YD,YO,?,AYB,DC>,
                        AYB extends OutputBytestreamGeneralBase<?>,
                        DC extends AggregatorDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,YO,AYB,?,DC>>
        extends WritingOperationCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
        implements AggregatorWriting<T, YS, YQ, YR, YD>
{

  private WritingHandlerList<T,YBC,YD,YO> writingSubfields;

  protected AggregatorWritingCore(
    int index,
    T serializable,
    YS settings,
    AYB parentBytestream,
    SerializingFieldCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.writingSubfields = definitionCore().subcomponentHandlers.newSerializingHandlers(this);
  }

  @Override
  public List<SerializingSubfieldHandler<?,?,?>> subfields() {
    return this.writingSubfields.asSubfieldList();
  }

  @Override
  protected void onStart() {
    onAggregatorInitialization();
  }

  protected abstract void onAggregatorInitialization();

  @Override
  protected Optional<OperationSegment<?,?,?,?,?>> onProcessingBytestream() {
    Optional<OperationSegment<?,?,?,?,?>> latestSubfield = this.writingSubfields.processBytestream();
    if (this.writingSubfields.isCompleted()) {
      this.completeOperation();
    }
    return latestSubfield;
  }

  @SuppressWarnings("unchecked")
  public <SY,
             SYS extends OperationSettings,
             SYD extends DefinitionSegment<SY,?>,
             SYO extends SerializingOperation<SY,SYS,?,?,SYD>>
  SerializingSubfieldHandlerCore<SY,SYS,SYD,SYO,T,YBC,YD,YO> getSubfieldpacketFor(
    SubcomponentHandlerCore<SY,?,?,?,T,?,?,?,SYS,SYD,SYO,?,YD,YO,?,?>
    protocolSubfieldCore) {
    return this.writingSubfields.findSubfieldpacket(
        (SubcomponentHandlerCore<SY,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>) protocolSubfieldCore);
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
