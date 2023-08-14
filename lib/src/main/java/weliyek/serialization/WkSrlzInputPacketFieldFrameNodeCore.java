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

import weliyek.serialization.filter.FilterResults;

public abstract class WkSrlzInputPacketFieldFrameNodeCore<
                        T,
                        XS extends WkSzOperationSettings,
                        XD extends WkSrlzStructDefinitionFrameNode<T,?>,
                        XO extends WkSrlzInputPacketDecoderFrameNode<
                                        T,XS,?,? extends WkSzReadingResult<T>,XD>,
                        AXBC extends WkSzInputBytestreamBase<?>,
                        AXO extends WkAggregatorSrlzInputPacketDecoderFrameNode<?,?,? extends WkSzReadingRuntime<?>,?,?>>
    extends WkSrlzPacketFieldFrameNodeCore<
                        T, XS, XD,
                        WkSrlzStructComponentFrameNodeCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD>,
                        XO,
                        WkSrlzInputPacketDecoderFrameNodeCore<?,?,?,?,?,XO,?,XD,?,?>,
                        WkSrlzInputPacketFieldFrameNode<T,XD,XO>,
                        AXBC,
                        WkAggregatorSrlzInputPacketDecoderFrameNodeCore<?,?,?,AXBC,?,?,?,?,AXO,?,?,?>>
    implements WkSrlzInputPacketFieldFrameNode<T,XD,XO>
{

  private boolean requiredByFilter;

  protected WkSrlzInputPacketFieldFrameNodeCore(
    int initialOperationListCapacity,
    WkSrlzStructComponentFrameNodeCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD> protocolHandler) {
    super(
        initialOperationListCapacity,
        protocolHandler,
        WkSrlzInputPacketFieldFrameNodeCore::getDeserializedFromReadingOp);
  }

  @Override
  protected WkSrlzInputPacketDecoderFrameNodeCore<?,?,?,?,?,XO,?,XD,?,?>
  newOperation(int index) {
    final XS settings = newSettings(index);
    final AXBC parentBytestream = parentBytestream();
    final WkSrlzStructComponentFrameNodeCore<T,XS,XD,XO,AXBC,?,?,?,?,? extends XD> componentCore = protocolFieldCore();
    WkSrlzStructDefinitionFrameNodeCore<T,XS,?,?,XD,XO,AXBC,?,?,?,?,?,?,? extends XD,?> definitionCore = componentCore.definitionCore();
    return definitionCore.newReadingOperationCore(index, settings, parentBytestream, this);
  }

  private static
  <XX,
   OO extends WkSrlzInputPacketDecoderFrameNode<?,?,?,? extends WkSzReadingResult<XX>,?>>
  XX getDeserializedFromReadingOp(OO readingOp) {
    final WkSzReadingResult<XX> result = readingOp.result().get();
    return result.deserialized().get();
  }

  protected abstract FilterResults filterResults();

  @Override
  public boolean deserializedRequiredByFilter() {
    return this.requiredByFilter;
  }

  @Override
  protected final void onPacketFieldInitialization() {
    setRequiredByFilterFlag();
    onPacketInputFieldHandlerInitialization();
  }

  protected abstract void onPacketInputFieldHandlerInitialization();

  private void setRequiredByFilterFlag() {
    if (this.filterResults().filter().hasToBeDeserialized(this.structComponent().definition())) {
      this.requiredByFilter = true;
    } else {
      this.requiredByFilter = false;
    }
  }

  @Override
  public boolean deserializedRequired() {
    return deserializedRequiredByFilter() || this.filterResults().filter().isEmpty();
  }

  @Override
  public final WkSrlzFrameNodeType type() {
    return WkSrlzFrameNodeType.PACKET;
  }

  @Override
  public final WkSzPacketDirection direction() {
    return WkSzPacketDirection.READ;
  }

  @Override
  public WkSrlzStructComponentFrameNode<? extends XD> structComponent() {
    return protocolFieldCore().asProtocolField();
  }

}
