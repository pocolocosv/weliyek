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

public class WkSerdeDtreeMsgOutputSubfieldCore<
                        T,
                        YS extends WkSerdeDtreeOperationSettings,
                        YD extends WkSerdeDtreeStructDefinition<T>,
                        YO extends WkSerdeDtreeMsgWriter<T,YS,?,?,YD>,
                        AY,
                        AYBC extends WkSerdeDtreeBytestreamOutputBase<?>,
                        AYO extends WkSerdeDtreeAggregatorMsgWriter<AY,?,?,?,?>>
        extends WkSerdeDtreeMsgOutputFieldCore<
                        T, YS, YO, YD, AYBC,
                        WkSerdeDtreeAggregatorMsgWriterCore<AY,?,?,AYBC,?,?,?,?,?,AYO,?,?>,
                        WkSerdeDtreeMsgOutputField<T,YD,YO>,
                        WkSerdeDtreeStructSubfieldCore<
                          T,AY,?,?,?,?,?,YS,YD,YO,AYBC,AYO,? extends YD>>
{

  private WkSerdeDtreeAggregatorMsgWriterCore<AY,?,?,AYBC,?,?,?,?,?,AYO,?,?> parentOpCore;

  WkSerdeDtreeMsgOutputSubfieldCore(
    boolean enabled,
    WkSerdeDtreeStructSubfieldCore<T,AY,?,?,?,?,?,YS,YD,YO,AYBC,AYO,? extends YD> structFieldCore,
    WkSerdeDtreeAggregatorMsgWriterCore<AY,?,?,AYBC,?,?,?,?,?,AYO,?,?> parentOpCore) {
    super(structFieldCore, enabled);
    this.parentOpCore = Objects.requireNonNull(parentOpCore);
  }

  public AYO parentOperation() {
    return parentOperationCore().body();
  }

  @Override
  protected YS newSettings(int index) {
    return structFieldCore().newTxSettings(index, parentOperation());
  }

  @Override
  protected AYBC parentBytestream() {
    return parentOperationCore().getRuntimeControl().bytestreamCore();
  }

  @Override
  protected T serializable(int index) {
    return structFieldCore().disaggregate(index, parentOperation(), asPacket());
  }

  @Override
  protected int computeExpectedNumberOfOperations() {
    return structFieldCore().expectedNumberOfTxOps(parentOperation());
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
  public WkSerdeDtreeMsgOutputField<T, YD, YO> asPacket() {
    return this;
  }

  @Override
  protected WkSerdeDtreeAggregatorMsgWriterCore<AY,?,?,AYBC,?,?,?,?,?,AYO,?,?> parentOperationCore() {
    return this.parentOpCore;
  }

  @Override
  protected boolean isOptional() {
    return structFieldCore().isTxOptional();
  }

  @Override
  protected boolean testIfOptionalFieldIsToBeEnabled() {
    // TODO Auto-generated method stub
    return false;
  }

}
