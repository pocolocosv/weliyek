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

import java.util.function.Predicate;

import weliyek.amat.base.ComponentSegment;
import weliyek.amat.base.ComponentSegmentCore;
import weliyek.amat.base.DefinitionSegment;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.SubfieldHandlerCore;
import weliyek.amat.basic.aggregator.AggregatorDefinition;
import weliyek.amat.basic.aggregator.AggregatorWriting;
import weliyek.amat.basic.aggregator.AggregatorWritingCore;
import weliyek.amat.basic.aggregator.SubcomponentHandlerCore;

public class SerializingSubfieldHandlerCore<
                        ST,
                        SYS extends OperationSettings,
                        SYD extends DefinitionSegment<ST,?>,
                        SYO extends SerializingOperation<ST,SYS,?,?,SYD>,
                        T,
                        YBC extends OutputBytestreamGeneralBase<?>,
                        YD extends AggregatorDefinition<T,?>,
                        YO extends AggregatorWriting<T,?,? extends SerializingRuntime<?>,?,YD>>
    extends SubfieldHandlerCore<
                        SYS, SYD,
                        SubcomponentHandlerCore<ST,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>,
                        SerializingField<ST,SYD,SYO>,
                        SerializingFieldCore<ST,SYS,SYD,SYO,YBC,YO>,
                        SerializingSubfieldHandler<ST,SYD,SYO>,
                        AggregatorWritingCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>,
                        YD>
    implements SerializingSubfieldHandler<ST,SYD,SYO>
{

  public SerializingSubfieldHandlerCore() {
    super();
  }


  @Override
  protected SerializingSubfieldCore<ST,SYS,SYD,SYO,T,YBC,YO> newPacket() {
    return new SerializingSubfieldCore<>(1, this);
  }

  @Override
  public SerializingSubfieldHandler<ST, SYD, SYO> asSubfield() {
    return this;
  }

  @Override
  protected void onInitialization() {
    // Nothing to do.
  }

  @Override
  protected void onReset() {
    // Nothing to do.
  }

  @Override
  protected boolean isFieldOptional() {
    return subcomponentHandlerCore().isTxOptional();
  }

  @Override
  protected boolean testIfOptionalFieldIsToBeEnabled() {
    YO parentOp = parentOperationCore().body();
    Predicate<? super YO> tester = subcomponentHandlerCore().txOptionalityTest().get();
    return tester.test(parentOp);
  }

  @Override
  public ComponentSegmentCore<ST,?,?,?,?,SYS,SYD,SYO,YBC,? extends SYD> protocolFieldCore() {
    return subcomponentHandlerCore().protocolFieldCore();
  }

  @Override
  protected SYD definition() {
    return protocolField().definition();
  }

  @Override
  protected ComponentSegment<? extends SYD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
