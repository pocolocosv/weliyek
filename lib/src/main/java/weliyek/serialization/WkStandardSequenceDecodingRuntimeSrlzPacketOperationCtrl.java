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

import weliyek.serialization.sequence.WkSzSequenceOnlyOperationRuntimeControlModule;
import weliyek.serialization.util.DoesNothingRunnable;

public class WkStandardSequenceDecodingRuntimeSrlzPacketOperationCtrl<
                        AB extends WkSzInputBytestreamBase<?>,
                        B extends WkSzInputBytestream,
                        BC extends WkSzInputBytestreamBase<B>>
    implements WkSequenceDecodingRuntimeSrlzPacketOperationCtrl<
                        B, BC,
                        WkSequenceDecodingRuntimeSrlzPacketOperationData<B>>
{

  private final WkDecodingRuntimeSrlzPacketOperationCtrlModule<AB, B, BC, WkSequenceDecodingRuntimeSrlzPacketOperationData<B>> ctrlCore;
  private final WkSequenceDecodingRuntimeSrlzPacketOperationData<B> publicRuntime;
  private final WkSzSequenceOnlyOperationRuntimeControlModule sequenceCtrl = new WkSzSequenceOnlyOperationRuntimeControlModule();

  public WkStandardSequenceDecodingRuntimeSrlzPacketOperationCtrl(
    AB parentBytestream,
    Function<AB, BC> bytestreamFactory) {
    this.ctrlCore = new WkDecodingRuntimeSrlzPacketOperationCtrlModule<AB,B,BC,WkSequenceDecodingRuntimeSrlzPacketOperationData<B>>(
                              this,
                              parentBytestream,
                              bytestreamFactory,
                              DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSequenceDecodingRuntimeSrlzPacketOperationData<B>() {

      @Override
      public long nextElementIndex() {
        return WkStandardSequenceDecodingRuntimeSrlzPacketOperationCtrl.this.nextElementIndex();
      }

      @Override
      public B bytestream() {
        return WkStandardSequenceDecodingRuntimeSrlzPacketOperationCtrl.this.bytestream();
      }

    };
  }

  @Override
  public B bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSequenceDecodingRuntimeSrlzPacketOperationData<B> asRuntime() {
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
