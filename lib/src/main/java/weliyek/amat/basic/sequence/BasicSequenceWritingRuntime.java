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

import weliyek.amat.base.output.BasicWritingRuntimeModule;
import weliyek.amat.base.output.WkSzBasicOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestream;
import weliyek.amat.base.output.WkSzOutputBytestreamBase;
import weliyek.ketza.util.DoesNothingRunnable;

public class BasicSequenceWritingRuntime
    implements SequenceWritingRuntimeControl<
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        SequenceWritingRuntime<WkSzOutputBytestream>>
{

  private final BasicWritingRuntimeModule<
                    WkSzOutputBytestreamBase<?>,
                    WkSzOutputBytestream,
                    WkSzOutputBytestreamBase<WkSzOutputBytestream>,
                    SequenceWritingRuntime<WkSzOutputBytestream>> ctrlCore;
  private final SequenceOnlyOperationRuntimeControlModule sequenceCtrl = new SequenceOnlyOperationRuntimeControlModule();
  private final SequenceWritingRuntime<WkSzOutputBytestream> publicRuntime;

  public BasicSequenceWritingRuntime(
    WkSzOutputBytestreamBase<?> parentBytestream) {
    this.ctrlCore = new BasicWritingRuntimeModule<>(this, parentBytestream, WkSzBasicOutputBytestream::new, DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new SequenceWritingRuntime<WkSzOutputBytestream>() {

      @Override
      public long nextElementIndex() {
        return BasicSequenceWritingRuntime.this.nextElementIndex();
      }

      @Override
      public WkSzOutputBytestream bytestream() {
        return BasicSequenceWritingRuntime.this.bytestream();
      }};
  }

  @Override
  public WkSzOutputBytestream bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public SequenceWritingRuntime<WkSzOutputBytestream> asRuntime() {
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
  public WkSzOutputBytestreamBase<WkSzOutputBytestream> bytestreamCore() {
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
