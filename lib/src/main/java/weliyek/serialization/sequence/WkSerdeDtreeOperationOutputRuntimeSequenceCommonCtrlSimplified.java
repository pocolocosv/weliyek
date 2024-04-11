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
package weliyek.serialization.sequence;

import java.io.IOException;

import weliyek.serialization.WkSerdeDtreeBytestreamOutputBasic;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeCtrlModule;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl;
import weliyek.serialization.util.DoesNothingRunnable;

public class WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrlSimplified
    implements WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrl<
                        WkSerdeDtreeBytestreamOutput,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>>
{

  private final WkSerdeDtreeOperationOutputRuntimeCtrlModule<
                    WkSerdeDtreeBytestreamOutputBase<?>,
                    WkSerdeDtreeBytestreamOutput,
                    WkSerdeDtreeBytestreamOutputBase<WkSerdeDtreeBytestreamOutput>,
                    WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>> ctrlCore;
  private final WkSerdeDtreeOperationRuntimeSequenceCtrlModule sequenceCtrl = new WkSerdeDtreeOperationRuntimeSequenceCtrlModule();
  private final WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput> publicRuntime;

  public WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrlSimplified(
    WkSerdeDtreeBytestreamOutputBase<?> parentBytestream) {
    this.ctrlCore = new WkSerdeDtreeOperationOutputRuntimeCtrlModule<>(this, parentBytestream, WkSerdeDtreeBytestreamOutputBasic::new, DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput>() {

      @Override
      public long nextElementIndex() {
        return WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrlSimplified.this.nextElementIndex();
      }

      @Override
      public WkSerdeDtreeBytestreamOutput bytestream() {
        return WkSerdeDtreeOperationOutputRuntimeSequenceCommonCtrlSimplified.this.bytestream();
      }};
  }

  @Override
  public WkSerdeDtreeBytestreamOutput bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntimeSequenceCommon<WkSerdeDtreeBytestreamOutput> asRuntime() {
    return this.publicRuntime;
  }

  @Override
  public void disableRuntime() {
    this.ctrlCore.disableRuntime();
  }

  @Override
  public void writeByte(int b) throws IOException {
    this.ctrlCore.writeByte(b);
  }

  @Override
  public WkSerdeDtreeBytestreamOutputBase<WkSerdeDtreeBytestreamOutput> bytestreamCore() {
    return this.ctrlCore.bytestreamCore();
  }

  @Override
  public long nextElementIndex() {
    return this.sequenceCtrl.nextElementIndex();
  }

  @Override
  public void setNextElementIndex(long startPos) {
    this.sequenceCtrl.setNextElementIndex(startPos);
  }

}
