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

import weliyek.serialization.WkSzBasicOutputBytestream;
import weliyek.serialization.WkEncodingRuntimeSrlzPacketOperationCtrlModule;
import weliyek.serialization.WkSzOutputBytestream;
import weliyek.serialization.WkSzOutputBytestreamBase;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkSequenceEncodingRuntimeSrlzPacketOperationCtrl;
import weliyek.serialization.util.DoesNothingRunnable;

public class WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl
    implements WkSequenceEncodingRuntimeSrlzPacketOperationCtrl<
                        WkSzOutputBytestream,
                        WkSzOutputBytestreamBase<? extends WkSzOutputBytestream>,
                        WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>>
{

  private final WkEncodingRuntimeSrlzPacketOperationCtrlModule<
                    WkSzOutputBytestreamBase<?>,
                    WkSzOutputBytestream,
                    WkSzOutputBytestreamBase<WkSzOutputBytestream>,
                    WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>> ctrlCore;
  private final WkSzSequenceOnlyOperationRuntimeControlModule sequenceCtrl = new WkSzSequenceOnlyOperationRuntimeControlModule();
  private final WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> publicRuntime;

  public WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl(
    WkSzOutputBytestreamBase<?> parentBytestream) {
    this.ctrlCore = new WkEncodingRuntimeSrlzPacketOperationCtrlModule<>(this, parentBytestream, WkSzBasicOutputBytestream::new, DoesNothingRunnable.INSTANCE);
    this.publicRuntime = new WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream>() {

      @Override
      public long nextElementIndex() {
        return WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl.this.nextElementIndex();
      }

      @Override
      public WkSzOutputBytestream bytestream() {
        return WkSimplifiedSequenceEncodingRuntimeSrlzPacketOperationCtrl.this.bytestream();
      }};
  }

  @Override
  public WkSzOutputBytestream bytestream() {
    return this.ctrlCore.bytestream();
  }

  @Override
  public WkSequenceEncodingRuntimeSrlzPacketOperationData<WkSzOutputBytestream> asRuntime() {
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
