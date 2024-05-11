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
package weliyek.util.array;

import java.util.List;

import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeOperationSettingsVariableLength;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.sequence.SequenceSizeParameters;
import weliyek.serialization.sequence.WkSerdeUtilsPrimitiveArrayLengthGetter;

public class WkSerdeDtreeVariableSizeByteArray
    implements WkSerdeDtreeByteArrayDefinition,
               WkSerdeDtreeVariableSizePrimitiveArrayDefinition<
                        WkByteArray>
{

  public static WkSerdeDtreeStruct<
                      WkByteArray,
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeDtreeVariableSizeByteArray,
                      WkSerdeDtreeVariableSizeByteArrayReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDtreeVariableSizeByteArray,
                      WkSerdeDtreeVariableSizeByteArrayWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeDtreeVariableSizeByteArray>
  newStruct(String label, int minSize, int maxSize) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      (pc) -> WkSerdeDtreeVariableSizeByteArray.newCore(minSize, maxSize, pc),
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
                      WkByteArray,
                      WkSerdeDtreeOperationSettingsVariableLength,
                      WkSerdeDtreeVariableSizeByteArrayReader,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDtreeVariableSizeByteArrayWriter,
                      WkSerdeDtreeVariableSizeByteArray>
  newCore(
    int minSize,
    int maxSize,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore) {
    return new WkSerdeDtreeVariableSizeByteArray(minSize, maxSize, componentCore).definitionCore;
  }

  private final WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
                        WkByteArray,
                        WkSerdeDtreeOperationSettingsVariableLength,
                        WkSerdeDtreeVariableSizeByteArrayReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeVariableSizeByteArrayWriter,
                        WkSerdeDtreeVariableSizeByteArray> definitionCore;
  private final SequenceSizeParameters<WkByteArray> sizeLimits;

  private WkSerdeDtreeVariableSizeByteArray(
    int minSize,
    int maxSize,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
        WkByteArray,
        WkSerdeDtreeOperationSettingsVariableLength,
        WkSerdeDtreeVariableSizeByteArrayReader,
        WkSerdeDtreeOperationSettings,
        WkSerdeDtreeVariableSizeByteArrayWriter,
        WkSerdeDtreeVariableSizeByteArray>(
                                  1024, // de/serialization step size
                                  componentCore,
                                  WkSerdeDtreeVariableSizeByteArray::getRxRequestedLength,
                                  (i,xs,axb,xkc,dc) -> new WkSerdeDtreeVariableSizeByteArrayReader(i,xs,axb,xkc,dc).operationCore,
                                  WkSerdeDtreeByteArrayReaderDecoderEngine.FACTORY,
                                  (WkSerdeUtilsPrimitiveArrayLengthGetter<WkByteArray,WkSerdeDtreeOperationSettings,WkSerdeDtreeVariableSizeByteArray>)WkSerdeDtreeVariableSizeByteArray::getTxRequestedLength,
                                  (i,y,ys,ayb,ykc,dc) -> new WkSerdeDtreeVariableSizeByteArrayWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                  WkSerdeDtreeByteArrayWriterEncoderEngine.FACTORY,
                                  this,
                                  WkByteArray.class);
    this.sizeLimits = new SequenceSizeParameters<>(minSize, maxSize, definitionCore);
  }

  private static int getRxRequestedLength(WkSerdeDtreeOperationSettingsVariableLength settings, WkSerdeDtreeVariableSizeByteArray definition) {
    return settings.getRequestedLength();
  }

  private static int getTxRequestedLength(
    WkByteArray wrapper,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeVariableSizeByteArray definition) {
    return wrapper.getLength();
  }

  @Override
  public Class<WkByteArray> serializableClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int minimalSize() {
    return this.sizeLimits.minimalSize();
  }

  @Override
  public int maximalSize() {
    return this.sizeLimits.maximalSize();
  }

  @Override
  public String toString() {
    return this.definitionCore.name();
  }

}
