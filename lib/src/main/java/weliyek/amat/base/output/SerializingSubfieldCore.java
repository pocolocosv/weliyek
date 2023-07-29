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
package weliyek.amat.base.output;

import java.util.function.ToIntFunction;

import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.basic.aggregator.AggregatorWriting;
import weliyek.amat.basic.aggregator.AggregatorWritingCore;

public class SerializingSubfieldCore<
                        T,
                        YS extends OperationSettings,
                        YD extends DefinitionSegment<T,?>,
                        YO extends SerializingOperation<T,YS,?,?,YD>,
                        AY,
                        AYBC extends OutputBytestreamGeneralBase<?>,
                        AYO extends AggregatorWriting<AY,?,? extends SerializingRuntime<?>,?,?>>
        extends SerializingFieldCore<T, YS, YD, YO, AYBC, AYO>
{

  private final SerializingSubfieldHandlerCore<T,YS,YD,YO,AY,AYBC,?,AYO> myHandler;

  SerializingSubfieldCore(
    int initialOperationListCapacity,
    SerializingSubfieldHandlerCore<T,YS,YD,YO,AY,AYBC,?,AYO> subfieldHandler) {
    super(initialOperationListCapacity, subfieldHandler.protocolFieldCore());
    assert null != subfieldHandler;
    this.myHandler = subfieldHandler;
  }

  public AYO parentOperation() {
    return parentOperationCore().body();
  }

  @Override
  protected YS newSettings(int index) {
    return this.myHandler.subcomponentHandlerCore().newTxSettings(index, parentOperation());
  }

  @Override
  protected AYBC parentBytestream() {
    return parentOperationCore().getRuntimeControl().bytestreamCore();
  }

  @Override
  protected T serializable(int index) {
    Disaggregator<T,YD,AY,AYO> disaggregator = this.myHandler.subcomponentHandlerCore().disaggregator();
    return disaggregator.disaggregate(asPacket(), parentOperation(), index);
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    ToIntFunction<? super AYO> evaluator = this.myHandler.subcomponentHandlerCore().numberOfTxOperationsEvaluator();
    return evaluator.applyAsInt(parentOperation());
  }

  @Override
  protected void onPacketFieldInitialization() {
    // TODO Auto-generated method stub
  }

  @Override
  protected void onPacketFieldSucccessfullyEnabled() {
    // TODO Auto-generated method stub
  }

  @Override
  public SerializingField<T, YD, YO> asPacket() {
    return this;
  }

  @Override
  protected AggregatorWritingCore<?, ?, ?, AYBC, ?, ?, ?, ?, AYO, ?, ?, ?> parentOperationCore() {
    return this.myHandler.parentOperationCore();
  }

}
