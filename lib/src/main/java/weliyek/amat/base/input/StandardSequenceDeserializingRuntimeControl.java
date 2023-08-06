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
package weliyek.amat.base.input;

import java.io.IOException;
import java.util.function.Function;

import weliyek.amat.basic.sequence.SequenceOnlyOperationRuntimeControlModule;
import weliyek.amat.basic.sequence.SequenceReadingRuntimeControl;
import weliyek.ketza.util.DoesNothingRunnable;

public class StandardSequenceDeserializingRuntimeControl<
                        AB extends WkSzInputBytestreamBase<?>,
                        B extends WkSzInputBytestream,
                        BC extends WkSzInputBytestreamBase<B>>
    implements SequenceReadingRuntimeControl<
                        B, BC,
                        SequenceReadingRuntime<B>>
{

  private final BasicReadingRuntimeModule<AB, B, BC, SequenceReadingRuntime<B>> ctrlCore;
  private final SequenceReadingRuntime<B> publicRuntime;
  private final SequenceOnlyOperationRuntimeControlModule sequenceCtrl = new SequenceOnlyOperationRuntimeControlModule();

  public StandardSequenceDeserializingRuntimeControl(
    AB parentBytestream,
    Function<AB, BC> bytestreamFactory) {
    this.ctrlCore = new BasicReadingRuntimeModule<AB,B,BC,SequenceReadingRuntime<B>>(
                              this,
                              parentBytestream,
                              bytestreamFactory,
                              DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new SequenceReadingRuntime<B>() {

      @Override
      public long nextElementIndex() {
        return StandardSequenceDeserializingRuntimeControl.this.nextElementIndex();
      }

      @Override
      public B bytestream() {
        return StandardSequenceDeserializingRuntimeControl.this.bytestream();
      }

    };
  }

  @Override
  public B bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public SequenceReadingRuntime<B> asRuntime() {
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
