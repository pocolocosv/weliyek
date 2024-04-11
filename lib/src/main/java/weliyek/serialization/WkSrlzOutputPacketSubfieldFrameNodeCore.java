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
package weliyek.serialization;

import java.util.function.Predicate;

public class WkSrlzOutputPacketSubfieldFrameNodeCore<
                        ST,
                        SYS extends WkSerdeDtreeOperationSettings,
                        SYD extends WkSerdeDtreeNodeStructDefinition<ST>,
                        SYO extends WkSerdeDtreeNodeDataWriter<ST,SYS,?,?,SYD>,
                        T,
                        YBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        YD extends WkSerdeDtreeAggregatorDefinition<T>,
                        YO extends WkSerdeDtreeAggregatorWriter<T,?,? extends WkSerdeDtreeOperationOutputRuntime<?>,?,YD>>
    extends WkSrlzPacketSubfieldFrameNodeCore<
                        SYS, SYD,
                        WkSrlzStructSubcomponentFrameNodeCore<ST,?,?,?,T,?,?,?,SYS,SYD,SYO,YBC,YD,YO,? extends SYD,? extends YD>,
                        WkSerdeDtreeNodeDataOutputComponent<ST,SYD,SYO>,
                        WkSerdeDtreeNodeDataOutputComponentCore<ST,SYS,SYD,SYO,YBC,YO>,
                        WkSrlzOutputPacketSubfieldFrameNode<ST,SYD,SYO>,
                        WkSerdeDtreeAggregatorWriterCore<?,?,?,YBC,?,?,?,YD,YO,?,?,?>,
                        YD>
    implements WkSrlzOutputPacketSubfieldFrameNode<ST,SYD,SYO>
{

  public WkSrlzOutputPacketSubfieldFrameNodeCore() {
    super();
  }


  @Override
  protected WkSerdeDtreeNodeDataOutputComponentCoreNonroot<ST,SYS,SYD,SYO,T,YBC,YO> newPacket() {
    return new WkSerdeDtreeNodeDataOutputComponentCoreNonroot<>(1, this);
  }

  @Override
  public WkSrlzOutputPacketSubfieldFrameNode<ST, SYD, SYO> asSubfield() {
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
  public WkSerdeDtreeNodeStructComponentCore<ST,?,?,?,?,SYS,SYD,SYO,YBC,? extends SYD> protocolFieldCore() {
    return subcomponentHandlerCore().protocolFieldCore();
  }

  @Override
  protected SYD definition() {
    return protocolField().definition();
  }

  @Override
  protected WkSerdeDtreeNodeStructComponent<? extends SYD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
