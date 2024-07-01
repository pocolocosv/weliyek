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

import weliyek.serialization.filter.WkSrlzFilterResults;

public abstract class WkSerdeDtreeMsgInputFieldCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XO extends WkSerdeDtreeMsgReader<T,XS,?,? extends WkSerdeDtreeOperationResult<T>,XD>,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        AXOC extends WkSerdeDtreeMsgReaderCore<?,?,?,?,?,?,?,?,?,?>,
                        XK extends WkSerdeDtreeMsgInputField<T,XD,XO>,
                        XPC extends WkSerdeDtreeStructFieldCore<
                                        T,XS,XO,AXBC,?,?,?,? extends XD>>
    extends WkSerdeDtreeMsgFieldCore<
                        T, XS, XD, XO,
                        WkSerdeDtreeMsgReaderCore<?,?,?,?,?,XO,?,?,?,?>,
                        XK, AXBC, AXOC, XPC>
    implements WkSerdeDtreeMsgInputField<T,XD,XO>
{

  private boolean requiredByFilter;

  protected WkSerdeDtreeMsgInputFieldCore(
    XPC structFieldCore,
    boolean enabled) {
    super(
        structFieldCore,
        WkSerdeDtreeMsgInputFieldCore::getDeserializedFromReadingOp,
        enabled);
  }

  @Override
  protected WkSerdeDtreeMsgReaderCore<?,?,?,?,?,XO,?,?,?,?>
  newOperation(int index) {
    return structFieldCore()
           .definitionCore()
           .newReadingOperationCore(index, this);
  }

  private static
  <XX,
   OO extends WkSerdeDtreeMsgReader<?,?,?,? extends WkSerdeDtreeOperationResult<XX>,?>>
  XX getDeserializedFromReadingOp(OO readingOp) {
    final WkSerdeDtreeOperationResult<XX> result = readingOp.result().get();
    return result.serializable().get();
  }

  protected abstract WkSrlzFilterResults filterResults();

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
  public WkSerdeDtreeStructField<? extends XD> structComponent() {
    return structFieldCore().asProtocolField();
  }

}
