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

import java.util.List;
import java.util.function.Predicate;

@Deprecated
public class WkSrlzInputPacketSubfieldFrameNodeCore<
                        ST,
                        SXS extends WkSerdeDtreeOperationSettings,
                        SXD extends WkSerdeDtreeStructDefinition<ST>,
                        SXO extends WkSerdeDtreeMsgReader<ST,SXS,?,?,SXD>,
                        T,
                        XBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        XD extends WkSerdeDtreeAggregatorStructDefinition<T>,
                        XO extends WkSerdeDtreeAggregatorMsgReader<T,?,? extends WkSerdeDtreeOperationInputRuntime<?>,?,XD>>
    extends WkSrlzPacketSubfieldFrameNodeCore<
                        SXS, SXD,
                        WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,?,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD>,
                        WkSerdeDtreeMsgInputField<ST,SXD,SXO>,
                        WkSerdeDtreeMsgInputFieldCore<ST,SXS,SXD,SXO,XBC,XO>,
                        WkSrlzInputPacketSubfieldFrameNode<ST,SXD,SXO>,
                        WkSerdeDtreeAggregatorMsgReaderCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>,
                        XD>
    implements WkSrlzInputPacketSubfieldFrameNode<ST,SXD,SXO>
{

  private boolean isRequiredByProto = false;

  public WkSrlzInputPacketSubfieldFrameNodeCore() {
    super();
  }

  @Override
  protected void onInitialization() {
    final WkSerdeDtreeAggregatorMsgReaderCore<?,?,?,?,?,?,?,XD,XO,?,?,?> parentOpCore = parentOperationCore();
    WkSerdeDtreeAggregatorStructDefinition<?> parentDef = parentOpCore.definition();
    List<WkSerdeDtreeNodeStructComponentHandler<?,?,?>> requiredSubfields = parentDef.requiredSubfields();
    if(-1 != requiredSubfields.indexOf(subcomponentHandlerCore().body())) {
      this.isRequiredByProto = true;
    } else {
      this.isRequiredByProto = false;
    }
  }

  @Override
  protected void onReset() {
    this.isRequiredByProto = false;
  }

  @Override
  protected WkSerdeDtreeMsgInputSubfieldCore<ST,SXS,SXD,SXO,XBC,XO> newPacket() {
    return new WkSerdeDtreeMsgInputSubfieldCore<>(1, this);
  }

  @Override
  public boolean deserializedRequiredByProtocol() {
    return this.isRequiredByProto;
  }

  @Override
  public WkSrlzInputPacketSubfieldFrameNode<ST, SXD, SXO> asSubfield() {
    return this;
  }

  @Override
  protected boolean isFieldOptional() {
    return subcomponentHandlerCore().isRxOptional();
  }

  @Override
  protected boolean testIfOptionalFieldIsToBeEnabled() {
    XO parentOp = parentOperationCore().body();
    Predicate<? super XO> tester = subcomponentHandlerCore().rxOptionalityTest().get();
    return tester.test(parentOp);
  }

  @Override
  public WkSerdeDtreeStructFieldCore<ST,SXS,SXD,SXO,XBC,?,?,?,?,? extends SXD> protocolFieldCore() {
    return subcomponentHandlerCore().protocolFieldCore();
  }

  @Override
  protected SXD definition() {
    return protocolField().definition();
  }

  @Override
  protected final WkSerdeDtreeStructField<? extends SXD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
