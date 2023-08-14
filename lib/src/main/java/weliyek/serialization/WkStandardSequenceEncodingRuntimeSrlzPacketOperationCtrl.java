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

public class WkStandardSequenceEncodingRuntimeSrlzPacketOperationCtrl<
                        AB extends WkSzOutputBytestreamBase<?>,
                        B extends WkSzOutputBytestream,
                        BC extends WkSzOutputBytestreamBase<B>>
    implements WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<
                        B, BC,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<B>>
{

  private final WkEncodingRuntimeSrlzPacketOperationCtrlModule<
                    AB, B, BC, WkSequenceEncodingRuntimeSrlzPacketOperationData<B>> ctrlCore;
  private final WkSzSequenceOnlyOperationRuntimeControlModule sequenceCtrl = new WkSzSequenceOnlyOperationRuntimeControlModule();
  private final WkSequenceEncodingRuntimeSrlzPacketOperationData<B> publicRuntime;

  public WkStandardSequenceEncodingRuntimeSrlzPacketOperationCtrl(
    AB parentBytestream,
    Function<AB, BC> serializerBytestreamFactory) {
    this.ctrlCore = new WkEncodingRuntimeSrlzPacketOperationCtrlModule<AB,B,BC,WkSequenceEncodingRuntimeSrlzPacketOperationData<B>>(
                            this,
                            parentBytestream,
                            serializerBytestreamFactory,
                            DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSequenceEncodingRuntimeSrlzPacketOperationData<B>() {

      @Override
      public long nextElementIndex() {
        return WkStandardSequenceEncodingRuntimeSrlzPacketOperationCtrl.this.nextElementIndex();
      }

      @Override
      public B bytestream() {
        return WkStandardSequenceEncodingRuntimeSrlzPacketOperationCtrl.this.bytestream();
      }
    };
  }

  @Override
  public B bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSequenceEncodingRuntimeSrlzPacketOperationData<B> asRuntime() {
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
