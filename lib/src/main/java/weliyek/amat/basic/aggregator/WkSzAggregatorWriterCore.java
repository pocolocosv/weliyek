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

import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.output.WkSzPacketWriterFieldCore;
import weliyek.amat.base.output.WkSzPacketWriterOperation;
import weliyek.amat.base.output.SerializingResult;
import weliyek.amat.base.output.SerializingRuntime;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.amat.base.output.WkSzPacketWriterSubfield;
import weliyek.amat.base.output.WkSzPacketWriterSubfieldCore;
import weliyek.amat.base.output.WkSzPacketWriterOperationCore;
import weliyek.amat.base.output.WritingRuntimeControl;
import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzPacketOperation;

public abstract class WkSzAggregatorWriterCore<
                        T,
                        YS extends OperationSettings,
                        YB extends WkSzOutputBytestream,
                        YBC extends WkSzOutputBytestreamBase<? extends YB>,
                        YQ extends SerializingRuntime<YB>,
                        YQC extends WritingRuntimeControl<YB,YBC,YQ>,
                        YR extends SerializingResult,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<T,YS,YQ,YR,YD>,
                        YOC extends WkSzAggregatorWriterCore<T,YS,YB,YBC,YQ,YQC,YR,YD,YO,?,AYB,DC>,
                        AYB extends WkSzOutputBytestreamBase<?>,
                        DC extends WkSzAggregatorDefinitionCore<
                                        T,?,?,?,?,?,?,?,?,YS,YB,YBC,YQC,YR,YD,YO,AYB,?,DC>>
        extends WkSzPacketWriterOperationCore<T, YS, YQ, YQC, YR, YO, YOC, YD, AYB, DC>
        implements WkSzAggregatorWriter<T, YS, YQ, YR, YD>
{

  private WritingHandlerList<T,YBC,YD,YO> writingSubfields;

  protected WkSzAggregatorWriterCore(
    int index,
    T serializable,
    YS settings,
    AYB parentBytestream,
    WkSzPacketWriterFieldCore<T,?,YD,?,?,?> packetHandlerCore,
    DC definitionCore,
    YO operationBody) {
    super(index, serializable, settings, parentBytestream, packetHandlerCore, definitionCore, operationBody);
    this.writingSubfields = definitionCore().subcomponentHandlers.newSerializingHandlers(this);
  }

  @Override
  public List<WkSzPacketWriterSubfield<?,?,?>> subfields() {
    return this.writingSubfields.asSubfieldList();
  }

  @Override
  protected void onStart() {
    onAggregatorInitialization();
  }

  protected abstract void onAggregatorInitialization();

  @Override
  protected Optional<WkSzPacketOperation<?,?,?,?,?>> onProcessingBytestream() {
    Optional<WkSzPacketOperation<?,?,?,?,?>> latestSubfield = this.writingSubfields.processBytestream();
    if (this.writingSubfields.isCompleted()) {
      this.completeOperation();
    }
    return latestSubfield;
  }

  @SuppressWarnings("unchecked")
  public <SY,
             SYS extends OperationSettings,
             SYD extends WkSzDefinition<SY,?>,
             SYO extends WkSzPacketWriterOperation<SY,SYS,?,?,SYD>>
  WkSzPacketWriterSubfieldCore<SY,SYS,SYD,SYO,T,YBC,YD,YO> getSubfieldpacketFor(
    WkSzSubcomponentCore<SY,?,?,?,T,?,?,?,SYS,SYD,SYO,?,YD,YO,?,?>
    protocolSubfieldCore) {
    return this.writingSubfields.findSubfieldpacket(
        (WkSzSubcomponentCore<SY,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>) protocolSubfieldCore);
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
