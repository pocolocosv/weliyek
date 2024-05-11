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

import java.util.Objects;

import weliyek.serialization.filter.WkSrlzFilterResults;

public class WkSerdeDtreeMsgInputSubfieldCore<
                        T,
                        XS extends WkSerdeDtreeOperationSettings,
                        XD extends WkSerdeDtreeStructDefinition<T>,
                        XO extends WkSerdeDtreeMsgReader<T,XS,?,?,XD>,
                        AX,
                        AXBC extends WkSerdeDtreeBytestreamInputBase<?>,
                        AXO extends WkSerdeDtreeAggregatorMsgReader<AX,?,?,?,?>>
        extends WkSerdeDtreeMsgInputFieldCore<
                        T, XS, XO, XD, AXBC,
                        WkSerdeDtreeAggregatorMsgReaderCore<AX,?,?,AXBC,?,?,?,?,?,AXO,?,?>,
                        WkSerdeDtreeMsgInputField<T,XD,XO>,
                        WkSerdeDtreeStructSubfieldCore<
                          T,AX,XS,XD,XO,AXBC,AXO,?,?,?,?,?,? extends XD>>
{

  private WkSerdeDtreeAggregatorMsgReaderCore<AX,?,?,AXBC,?,?,?,?,?,AXO,?,?> parentOpCore;

  protected WkSerdeDtreeMsgInputSubfieldCore(
    boolean enabled,
    WkSerdeDtreeStructSubfieldCore<
      T,AX,XS,XD,XO,AXBC,AXO,?,?,?,?,?,? extends XD> structFieldCore,
    WkSerdeDtreeAggregatorMsgReaderCore<AX,?,?,AXBC,?,?,?,?,?,AXO,?,?> parentOpCore) {
    super(structFieldCore, enabled);
    this.parentOpCore = Objects.requireNonNull(parentOpCore);
  }

  @Override
  protected WkSrlzFilterResults filterResults() {
    return parentOperationCore().packetFieldCore().filterResults();
  }

  @Override
  protected void onPacketInputFieldHandlerInitialization() {
    // Nothing to do?
  }

  @Override
  protected XS newSettings(int index) {
    return structFieldCore().newRxSettings(index, parentOperationCore().body());
  }

  @Override
  protected AXBC parentBytestream() {
    return parentOperationCore().getRuntimeControl().bytestreamCore();
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    return structFieldCore().expectedNumberOfRxOps(parentOperationCore().body());
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
  public WkSerdeDtreeMsgInputField<T, XD, XO> asPacket() {
    return this;
  }

  @Override
  protected WkSerdeDtreeAggregatorMsgReaderCore<AX,?,?,AXBC,?,?,?,?,?,AXO,?,?> parentOperationCore() {
    return this.parentOpCore;
  }

  @Override
  public boolean deserializedRequired() {
    return    super.deserializedRequired()
           || structFieldCore().isDeserializedRequiredByAggregator();
  }

  @Override
  protected boolean isOptional() {
    return structFieldCore().isRxOptional();
  }

  @Override
  protected boolean testIfOptionalFieldIsToBeEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

}
