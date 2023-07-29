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

import weliyek.amat.base.input.BasicInputBytestream;
import weliyek.amat.base.input.BasicReadingRuntimeModule;
import weliyek.amat.base.input.InputBytestream;
import weliyek.amat.base.input.InputBytestreamGeneralBase;
import weliyek.amat.base.input.SequenceReadingRuntime;
import weliyek.ketza.util.DoesNothingRunnable;

public class BasicSequenceReadingRuntime
    implements SequenceReadingRuntimeControl<
                        InputBytestream,
                        InputBytestreamGeneralBase<? extends InputBytestream>,
                        SequenceReadingRuntime<InputBytestream>>
{

  private final BasicReadingRuntimeModule<
                    InputBytestreamGeneralBase<?>,
                    InputBytestream,
                    InputBytestreamGeneralBase<InputBytestream>,
                    SequenceReadingRuntime<InputBytestream>> ctrlCore;
  private final SequenceOnlyOperationRuntimeControlModule sequenceCtrl = new SequenceOnlyOperationRuntimeControlModule();
  private final SequenceReadingRuntime<InputBytestream> publicRuntime;

  public BasicSequenceReadingRuntime(InputBytestreamGeneralBase<?> parentBytestream) {
    this.ctrlCore = new BasicReadingRuntimeModule<>(this, parentBytestream, BasicInputBytestream::new, DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new SequenceReadingRuntime<InputBytestream>() {

      @Override
      public long nextElementIndex() {
        return BasicSequenceReadingRuntime.this.nextElementIndex();
      }

      @Override
      public InputBytestream bytestream() {
        return BasicSequenceReadingRuntime.this.bytestream();
      }};
  }

  @Override
  public InputBytestream bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public SequenceReadingRuntime<InputBytestream> asRuntime() {
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
  public InputBytestreamGeneralBase<InputBytestream> bytestreamCore() {
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
