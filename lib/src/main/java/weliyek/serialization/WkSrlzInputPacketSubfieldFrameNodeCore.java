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

public class WkSrlzInputPacketSubfieldFrameNodeCore<
                        ST,
                        SXS extends WkSettingsSrlzPacketOperationData,
                        SXD extends WkSerdeDTreeNodeStructDefinition<ST>,
                        SXO extends WkSerdeDTreeNodeDataReader<ST,SXS,?,?,SXD>,
                        T,
                        XBC extends WkSzInputBytestreamBase<?>,
                        XD extends WkAggregatorSrlzStructDefinitionFrameNode<T>,
                        XO extends WkAggregatorSrlzInputPacketDecoderFrameNode<T,?,? extends WkDecodingRuntimeSrlzPacketOperationData<?>,?,XD>>
    extends WkSrlzPacketSubfieldFrameNodeCore<
                        SXS, SXD,
                        WkSrlzStructSubcomponentFrameNodeCore<ST,SXS,SXD,SXO,?,XBC,XD,XO,?,?,?,?,?,?,? extends SXD,? extends XD>,
                        WkSrlzInputPacketFieldFrameNode<ST,SXD,SXO>,
                        WkSrlzInputPacketFieldFrameNodeCore<ST,SXS,SXD,SXO,XBC,XO>,
                        WkSrlzInputPacketSubfieldFrameNode<ST,SXD,SXO>,
                        WkAggregatorSrlzInputPacketDecoderFrameNodeCore<?,?,?,XBC,?,?,?,XD,XO,?,?,?>,
                        XD>
    implements WkSrlzInputPacketSubfieldFrameNode<ST,SXD,SXO>
{

  private boolean isRequiredByProto = false;

  public WkSrlzInputPacketSubfieldFrameNodeCore() {
    super();
  }

  @Override
  protected void onInitialization() {
    final WkAggregatorSrlzInputPacketDecoderFrameNodeCore<?,?,?,?,?,?,?,XD,XO,?,?,?> parentOpCore = parentOperationCore();
    WkAggregatorSrlzStructDefinitionFrameNode<?> parentDef = parentOpCore.definition();
    List<WkSerdeDTreeNodeStructComponentHandler<?,?,?>> requiredSubfields = parentDef.requiredSubfields();
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
  protected WkSrlzInputPacketFieldFrameNodeNonrootCore<ST,SXS,SXD,SXO,XBC,XO> newPacket() {
    return new WkSrlzInputPacketFieldFrameNodeNonrootCore<>(1, this);
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
  public WkSrlzStructComponentFrameNodeCore<ST,SXS,SXD,SXO,XBC,?,?,?,?,? extends SXD> protocolFieldCore() {
    return subcomponentHandlerCore().protocolFieldCore();
  }

  @Override
  protected SXD definition() {
    return protocolField().definition();
  }

  @Override
  protected final WkSerdeDTreeNodeStructComponent<? extends SXD> protocolField() {
    return protocolFieldCore().asProtocolField();
  }

}
