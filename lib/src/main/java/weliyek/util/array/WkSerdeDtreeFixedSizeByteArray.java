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

import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentCoreRoot;
import weliyek.serialization.WkSerdeDtreeNodeStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeNodeStructComponentHandler;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.sequence.SequenceFixedSizeParameter;
import weliyek.serialization.sequence.WkSerdeUtilsPrimitiveArrayLengthGetter;

public class WkSerdeDtreeFixedSizeByteArray
    implements WkSerdeDtreeByteArrayDefinition,
               WkSerdeDtreeFixedSizePrimitiveArrayDefinition<
                        WkByteArray>
{

  public static WkSerdeDtreeStruct<
                      WkByteArray,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeDtreeFixedSizeByteArray>
  newStruct(
    String label,
    int expectedLength) {
    return new WkSerdeDtreeNodeStructComponentCoreRoot<>(
                      label,
                      (pc) -> WkSerdeDtreeFixedSizeByteArray.newCore(expectedLength, pc),
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNodeStructDefinitionCore<
                      WkByteArray,
                      WkSerdeDtreeOperationSettings,?,?,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,?,?,
                      WkSerdeDtreeFixedSizeByteArray,
                      WkSerdeDtreeFixedSizeByteArrayWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeDtreeFixedSizeByteArray,?>
  newCore(
    int expectedLength,
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeDtreeFixedSizeByteArray(expectedLength, componentCore).definitionCore;
  }

  private final WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        WkSerdeDtreeFixedSizeByteArray> definitionCore;
  private final SequenceFixedSizeParameter<WkByteArray> fixedSizeParameter;

  private WkSerdeDtreeFixedSizeByteArray(
    int expectedLength,
    WkSerdeDtreeNodeStructComponentCore<?,?,?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
        WkByteArray,
        WkSerdeDtreeOperationSettings,
        WkSerdeDtreeFixedSizeByteArrayReader,
        WkSerdeDtreeOperationSettings,
        WkSerdeDtreeFixedSizeByteArrayWriter,
        WkSerdeDtreeFixedSizeByteArray>(
                                    1024, // de/serialization step size
                                    componentCore,
                                    WkSerdeDtreeFixedSizeByteArray::getRxRequestedLengthFromDefinition,
                                    (i,xs,axb,xkc,dc) -> new WkSerdeDtreeFixedSizeByteArrayReader(i,xs,axb,xkc,dc).operationCore,
                                    WkSerdeDtreeByteArrayReaderDecoderEngine.FACTORY,
                                    (WkSerdeUtilsPrimitiveArrayLengthGetter<WkByteArray,WkSerdeDtreeOperationSettings,WkSerdeDtreeFixedSizeByteArray>)WkSerdeDtreeFixedSizeByteArray::getTxRequestedLengthFromDefinition,
                                    (i,y,ys,ayb,ykc,dc) -> new WkSerdeDtreeFixedSizeByteArrayWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                    WkSerdeDtreeByteArrayWriterEncoderEngine.FACTORY,
                                    this,
                                    WkByteArray.class);
    this.fixedSizeParameter = new SequenceFixedSizeParameter<WkByteArray>(expectedLength, this.definitionCore);
  }

  private static int getRxRequestedLengthFromDefinition(WkSerdeDtreeOperationSettings none, WkSerdeDtreeFixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  private static int getTxRequestedLengthFromDefinition(WkByteArray wrapper, WkSerdeDtreeOperationSettings none, WkSerdeDtreeFixedSizeByteArray definition) {
    return definition.getExpectedLength();
  }

  @Override
  public Class<WkByteArray> serializableClass() {
    return WkByteArray.class;
  }

  @Override
  public List<WkSerdeDtreeNodeStructComponentHandler<?, ?, ?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

}
