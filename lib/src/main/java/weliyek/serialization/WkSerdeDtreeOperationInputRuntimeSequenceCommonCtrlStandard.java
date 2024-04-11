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

import java.io.IOException;
import java.util.function.Function;

import weliyek.serialization.sequence.WkSerdeDtreeOperationRuntimeSequenceCtrlModule;
import weliyek.serialization.util.DoesNothingRunnable;

public class WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlStandard<
                        AB extends WkSerdeDtreeBytestreamInputBase<?>,
                        B extends WkSerdeDtreeBytestreamInput,
                        BC extends WkSerdeDtreeBytestreamInputBase<B>>
    implements WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<
                        B, BC,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<B>>
{

  private final WkSerdeDtreeOperationInputRuntimeCtrlModule<AB, B, BC, WkSerdeDtreeOperationInputRuntimeSequenceCommon<B>> ctrlCore;
  private final WkSerdeDtreeOperationInputRuntimeSequenceCommon<B> publicRuntime;
  private final WkSerdeDtreeOperationRuntimeSequenceCtrlModule sequenceCtrl = new WkSerdeDtreeOperationRuntimeSequenceCtrlModule();

  public WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlStandard(
    AB parentBytestream,
    Function<AB, BC> bytestreamFactory) {
    this.ctrlCore = new WkSerdeDtreeOperationInputRuntimeCtrlModule<AB,B,BC,WkSerdeDtreeOperationInputRuntimeSequenceCommon<B>>(
                              this,
                              parentBytestream,
                              bytestreamFactory,
                              DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSerdeDtreeOperationInputRuntimeSequenceCommon<B>() {

      @Override
      public long nextElementIndex() {
        return WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlStandard.this.nextElementIndex();
      }

      @Override
      public B bytestream() {
        return WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlStandard.this.bytestream();
      }

    };
  }

  @Override
  public B bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntimeSequenceCommon<B> asRuntime() {
    return this.publicRuntime;
  }

  @Override
  public void disableRuntime() {
    this.ctrlCore.disableRuntime();
  }

  @Override
  public int readByte() throws IOException {
    return this.ctrlCore.readByte();
  }

  @Override
  public long skipBytes(long num) throws IOException {
    return this.ctrlCore.skipBytes(num);
  }

  @Override
  public BC bytestreamCore() {
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
