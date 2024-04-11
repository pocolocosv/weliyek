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

import weliyek.serialization.sequence.WkSerdeDtreeOperationRuntimeSequenceCtrlModule;
import weliyek.serialization.util.DoesNothingRunnable;

public class WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified
    implements WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrl<
                        WkSerdeDtreeBytestreamInput,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>>
{

  private final WkSerdeDtreeOperationInputRuntimeCtrlModule<
                    WkSerdeDtreeBytestreamInputBase<?>,
                    WkSerdeDtreeBytestreamInput,
                    WkSerdeDtreeBytestreamInputBase<WkSerdeDtreeBytestreamInput>,
                    WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>> ctrlCore;
  private final WkSerdeDtreeOperationRuntimeSequenceCtrlModule sequenceCtrl = new WkSerdeDtreeOperationRuntimeSequenceCtrlModule();
  private final WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput> publicRuntime;

  public WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified(WkSerdeDtreeBytestreamInputBase<?> parentBytestream) {
    this.ctrlCore = new WkSerdeDtreeOperationInputRuntimeCtrlModule<>(this, parentBytestream, WkSerdeDtreeBytestreamInputBasic::new, DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>() {

      @Override
      public long nextElementIndex() {
        return WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified.this.nextElementIndex();
      }

      @Override
      public WkSerdeDtreeBytestreamInput bytestream() {
        return WkSerdeDtreeOperationInputRuntimeSequenceCommonCtrlSimplified.this.bytestream();
      }};
  }

  @Override
  public WkSerdeDtreeBytestreamInput bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput> asRuntime() {
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
  public WkSerdeDtreeBytestreamInputBase<WkSerdeDtreeBytestreamInput> bytestreamCore() {
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
