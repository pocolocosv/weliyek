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

import weliyek.amat.base.WkSzStructComponent;
import weliyek.amat.base.WkSzStructComponentCoreBase;
import weliyek.amat.base.WkSzDefinition;
import weliyek.amat.base.OperationSettings;
import weliyek.amat.base.WkSzPacketSubfieldCore;
import weliyek.amat.basic.aggregator.WkSzAggregatorDefinition;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriter;
import weliyek.amat.basic.aggregator.WkSzAggregatorWriterCore;
import weliyek.amat.basic.aggregator.WkSzSubcomponentCore;

public class WkSzPacketWriterSubfieldCore<
                        ST,
                        SYS extends OperationSettings,
                        SYD extends WkSzDefinition<ST,?>,
                        SYO extends WkSzPacketWriterOperation<ST,SYS,?,?,SYD>,
                        T,
                        YBC extends WkSzOutputBytestreamBase<?>,
                        YD extends WkSzAggregatorDefinition<T,?>,
                        YO extends WkSzAggregatorWriter<T,?,? extends SerializingRuntime<?>,?,YD>>
    extends WkSzPacketSubfieldCore<
                        SYS, SYD,
                        WkSzSubcomponentCore<ST,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>,
                        WkSzPacketWriterField<ST,SYD,SYO>,
                        WkSzPacketWriterFieldCore<ST,SYS,SYD,SYO,YBC,YO>,
                        WkSzPacketWriterSubfield<ST,SYD,SYO>,
                        WkSzAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>,
                        YD>
    implements WkSzPacketWriterSubfield<ST,SYD,SYO>
{

  public WkSzPacketWriterSubfieldCore() {
    super();
  }


  @Override
  protected SerializingSubfieldCore<ST,SYS,SYD,SYO,T,YBC,YO> newPacket() {
    return new SerializingSubfieldCore<>(1, this);
  }

  @Override
  public WkSzPacketWriterSubfield<ST, SYD, SYO> asSubfield() {
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
  public WkSzStructComponentCoreBase<ST,?,?,?,?,SYS,SYD,SYO,YBC,? extends SYD> protocolFieldCore() {
    return subcomponentHandlerCore().protocolFieldCore();
  }

  @Override
  protected SYD definition() {
    return protocolField().definition();
  }

  @Override
  protected WkSzStructComponent<? extends SYD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
