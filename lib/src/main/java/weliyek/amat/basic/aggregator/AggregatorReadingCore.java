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
import weliyek.amat.base.input.DeserializingFieldCore;
import weliyek.amat.base.input.DeserializingOperation;
import weliyek.amat.base.input.DeserializingResult;
import weliyek.amat.base.input.DeserializingRuntime;
import weliyek.amat.base.input.DeserializingSubfieldHandler;
import weliyek.amat.base.input.DeserializingSubfieldHandlerCore;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.ReadingOperationCore;
import weliyek.amat.base.input.ReadingRuntimeControl;

public abstract class AggregatorReadingCore<
                        T,
                        XS extends OperationSettings,
                        XB extends InputBytestream,
                        XBC extends InputBytestreamGeneralBase<? extends XB>,
                        XQ extends DeserializingRuntime<XB>,
                        XQC extends ReadingRuntimeControl<XB,XBC,XQ>,
                        XR extends DeserializingResult<T>,
                        XD extends AggregatorDefinition<T,?>,
                        XO extends AggregatorReading<T,XS,XQ,XR,XD>,
                        XOC extends AggregatorReadingCore<T,XS,XB,XBC,XQ,XQC,XR,XD,XO,?,AXB,DC>,
                        AXB extends InputBytestreamGeneralBase<?>,
                        DC extends AggregatorDefinitionCore<T,XS,XB,XBC,XQC,XR,XD,XO,AXB,?,?,?,?,?,?,?,?,?,DC>>
    extends ReadingOperationCore<T, XS, XQ, XQC, XR, XO, XOC, XD, AXB, DC>
    implements AggregatorReading<T, XS, XQ, XR, XD>
{

  protected ReadingHandlerList<T,XBC,XD,XO> readinHandlerList;

  protected AggregatorReadingCore(
    int index,
    XS settings,
    AXB parentBytestream,
    DeserializingFieldCore<T,?,XD,?,?,?> packetfieldCore,
    DC definitionCore,
    XO operationBody) {
    super(index, settings, parentBytestream, packetfieldCore, definitionCore, operationBody);
    this.readinHandlerList = definitionCore().subcomponentHandlers.newDeserializingHandlers(this);
  }

  @SuppressWarnings("unchecked")
  public <ST,
          SXS extends OperationSettings,
          SXD extends DefinitionSegment<ST,?>,
          SXO extends DeserializingOperation<ST,SXS,?,?,SXD>>
  DeserializingSubfieldHandlerCore<ST,SXS,SXD,SXO,T,XBC,XD,XO> getSubfieldpacketFor(
    SubcomponentHandlerCore<ST,SXS,SXD,SXO,T,?,XD,XO,?,?,?,?,?,?,?,?> protocolSubfieldCore) {
    return this.readinHandlerList.findSubfieldpacket(
        (SubcomponentHandlerCore<ST,SXS,SXD,SXO,T,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD>) protocolSubfieldCore);
  }

  @Override
  public List<DeserializingSubfieldHandler<?, ?, ?>> subfields() {
    return this.readinHandlerList.asSubfieldList();
  }

  @Override
  protected void onStartReading() {
    onAggregatorReadingInitialization();
  }

  protected abstract void onAggregatorReadingInitialization();

  @Override
  protected Optional<OperationSegment<?, ?, ?, ?, ?>> onProcessingBytestream() {
    Optional<OperationSegment<?,?,?,?,?>> completedSubfield = this.readinHandlerList.processBytestream();
    if (this.readinHandlerList.isCompleted()) {
        completeOperation();
    }
    return completedSubfield;
  }

  @Override
  public long expectedBytes() {
    // TODO Auto-generated method stub
    return 0;
  }

}
