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
package weliyek.amat.base.input;

import java.util.Objects;
import java.util.function.ToIntFunction;

import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.basic.aggregator.WkSzAggregatorReader;
import weliyek.amat.basic.aggregator.WkSzAggregatorReaderCore;
import weliyek.amat2.protocol.filter.FilterResults;

public class DeserializingSubfieldCore<
                        T,
                        XS extends OperationSettings,
                        XD extends WkSzDefinition<T,?>,
                        XO extends WkSzPacketReaderOperation<T,XS,?,?,XD>,
                        AXBC extends InputBytestreamGeneralBase<?>,
                        AXO extends WkSzAggregatorReader<?,?,? extends DeserializingRuntime<?>,?,?>>
        extends WkSzPacketReaderFieldCore<T, XS, XD, XO, AXBC, AXO>
{

  private final WkSzPacketReaderSubfieldCore<T,XS,XD,XO,?,AXBC,?,AXO> myHandler;

  protected DeserializingSubfieldCore(
    int initialOperationListCapacity,
    WkSzPacketReaderSubfieldCore<T,XS,XD,XO,?,AXBC,?,AXO> subfieldHandler) {
    super(initialOperationListCapacity, subfieldHandler.protocolFieldCore());
    this.myHandler = Objects.requireNonNull(subfieldHandler);
  }

  @Override
  protected FilterResults filterResults() {
    return this.myHandler.parentOperationCore().packetFieldCore().filterResults();
  }

  @Override
  protected void onPacketInputFieldHandlerInitialization() {
    // Nothing to do?
  }

  @Override
  protected XS newSettings(int index) {
    return this.myHandler.subcomponentHandlerCore().newRxSettings(index, parentOperation());
  }

  @Override
  protected AXBC parentBytestream() {
    return parentOperationCore().getRuntimeControl().bytestreamCore();
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    ToIntFunction<? super AXO> evaluator = this.myHandler.subcomponentHandlerCore().numberOfRxOperationsEvaluator();
    return evaluator.applyAsInt(parentOperation());
  }

  @Override
  protected void onPacketFieldSucccessfullyEnabled() {
    // TODO Auto-generated method stub

  }

  @Override
  protected void onDoneProcessing() {
    // TODO Auto-generated method stub

  }

  @Override
  public WkSzPacketReaderField<T, XD, XO> asPacket() {
    return this;
  }

  public AXO parentOperation() {
    return parentOperationCore().body();
  }

  @Override
  protected WkSzAggregatorReaderCore<?, ?, ?, AXBC, ?, ?, ?, ?, AXO, ?, ?, ?> parentOperationCore() {
    return this.myHandler.parentOperationCore();
  }

  @Override
  public boolean deserializedRequired() {
    return    super.deserializedRequired()
           || myHandler.deserializedRequiredByProtocol();
  }

  /*

  @Override
  protected FilterResults filterResults() {
    return this.subfieldHandler.parentOperationCore().packetFieldCore().filterResults();
  }

  @Override
  protected void onPacketInputFieldHandlerInitialization() {
    // TODO Nothing to do?
  }

  @Override
  protected S newSettings(int index) {
    return this.subfieldHandler.protocolFieldCore().settingsFactory().newSettings(index, asPacket());
  }

  @Override
  protected AB parentBytestream() {
    ReadingRuntime<? extends AB> runtime = this.subfieldHandler.parentOperation().dashboard();
    return runtime.bytestream();
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    return this.subfieldHandler.protocolFieldCore().numberOfOperations(asPacket());
  }

  @Override
  public AO parentOperation() {
    return this.subfieldHandler.parentOperation();
  }

  @Override
  protected PacketReadingSegmentCore<?,?,?,?,?,?,?,?,?,? extends AO,?,?> parentOperationCore() {
    return this.subfieldHandler.parentOperationCore();
  }

  @Override
  protected void onPacketFieldSucccessfullyEnabled() {
    // TODO Nothing to do?
  }

  @Override
  protected void onDoneProcessing() {
    // TODO Nothing to do?
  }
  */

  /* OLD
  private final boolean deserializabletMustBeInstantiated;

  protected PacketInputSubfieldHandlerBase(
    ProtocolInputFieldHandlerBase<C, X, S, P, O, AB, AO, ?> subfield,
    boolean enabled,
    PacketInputFieldReadingCore<? extends C, ?, ?, ?, ?, ?, ?, ?, ? extends AO, ?> parentOperationCore) {
    super(subfield.protocolCore(), enabled, parentOperationCore);
    this.subfield = subfield;
    this.deserializabletMustBeInstantiated = subfield.checkIfSubfieldMustInstantiateDeserialized(parentOperationCore);
  }

  @Override
  public boolean isRequired() {
    return this.deserializabletMustBeInstantiated;
  }

  @Override
  public C getContext() {
    return parentOperationCore().packetField().getContext();
  }

  @Override
  protected S newSettings(final int index) {
    return this.subfield.newSettings(index, packetBody());
  }

  @Override
  protected AB parentBytestream() {
    final ReadingRuntime<? extends AB> dashboard = parentOperation().get().dashboard();
    return dashboard.bytestream();
  }

  @Override
  protected FilterResults filterResults() {
    return parentOperationCore().packetFieldCore().filterResults();
  }
  */

}
