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
package weliyek.amat.basic.sequence;

import java.io.IOException;
import java.util.function.Function;

import weliyek.amat.base.output.WkSzBasicWritingRuntimeModule;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.ketza.util.DoesNothingRunnable;

public class WkSzStandardSequenceWritingRuntimeControl<
                        AB extends WkSzOutputBytestreamBase<?>,
                        B extends WkSzOutputBytestream,
                        BC extends WkSzOutputBytestreamBase<B>>
    implements WkSzSequenceWritingRuntimeControl<
                        B, BC,
                        WkSzSequenceWritingRuntime<B>>
{

  private final WkSzBasicWritingRuntimeModule<
                    AB, B, BC, WkSzSequenceWritingRuntime<B>> ctrlCore;
  private final WkSzSequenceOnlyOperationRuntimeControlModule sequenceCtrl = new WkSzSequenceOnlyOperationRuntimeControlModule();
  private final WkSzSequenceWritingRuntime<B> publicRuntime;

  public WkSzStandardSequenceWritingRuntimeControl(
    AB parentBytestream,
    Function<AB, BC> serializerBytestreamFactory) {
    this.ctrlCore = new WkSzBasicWritingRuntimeModule<AB,B,BC,WkSzSequenceWritingRuntime<B>>(
                            this,
                            parentBytestream,
                            serializerBytestreamFactory,
                            DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSzSequenceWritingRuntime<B>() {

      @Override
      public long nextElementIndex() {
        return WkSzStandardSequenceWritingRuntimeControl.this.nextElementIndex();
      }

      @Override
      public B bytestream() {
        return WkSzStandardSequenceWritingRuntimeControl.this.bytestream();
      }
    };
  }

  @Override
  public B bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSzSequenceWritingRuntime<B> asRuntime() {
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
