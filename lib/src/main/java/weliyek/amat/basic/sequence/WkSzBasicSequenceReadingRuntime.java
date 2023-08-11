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

import weliyek.amat.base.input.WkSzBasicReadingRuntimeModule;
import weliyek.amat.base.input.WkSzSequenceReadingRuntime;
import weliyek.amat.base.input.WkSzBasicInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.ketza.util.DoesNothingRunnable;

public class WkSzBasicSequenceReadingRuntime
    implements WkSzSequenceReadingRuntimeControl<
                        WkSzInputBytestream,
                        WkSzInputBytestreamBase<? extends WkSzInputBytestream>,
                        WkSzSequenceReadingRuntime<WkSzInputBytestream>>
{

  private final WkSzBasicReadingRuntimeModule<
                    WkSzInputBytestreamBase<?>,
                    WkSzInputBytestream,
                    WkSzInputBytestreamBase<WkSzInputBytestream>,
                    WkSzSequenceReadingRuntime<WkSzInputBytestream>> ctrlCore;
  private final WkSzSequenceOnlyOperationRuntimeControlModule sequenceCtrl = new WkSzSequenceOnlyOperationRuntimeControlModule();
  private final WkSzSequenceReadingRuntime<WkSzInputBytestream> publicRuntime;

  public WkSzBasicSequenceReadingRuntime(WkSzInputBytestreamBase<?> parentBytestream) {
    this.ctrlCore = new WkSzBasicReadingRuntimeModule<>(this, parentBytestream, WkSzBasicInputBytestream::new, DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSzSequenceReadingRuntime<WkSzInputBytestream>() {

      @Override
      public long nextElementIndex() {
        return WkSzBasicSequenceReadingRuntime.this.nextElementIndex();
      }

      @Override
      public WkSzInputBytestream bytestream() {
        return WkSzBasicSequenceReadingRuntime.this.bytestream();
      }};
  }

  @Override
  public WkSzInputBytestream bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSzSequenceReadingRuntime<WkSzInputBytestream> asRuntime() {
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
  public WkSzInputBytestreamBase<WkSzInputBytestream> bytestreamCore() {
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
