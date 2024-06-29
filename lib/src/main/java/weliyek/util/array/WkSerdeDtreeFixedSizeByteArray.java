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
import java.util.Optional;
import java.util.function.Predicate;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReader;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriter;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.WkSzPacketWriteDisaggregator;
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
    return new WkSerdeDtreeStructCore<>(
                      label,
                      (pc) -> WkSerdeDtreeFixedSizeByteArray.newCore(expectedLength, pc),
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static <
  T,
  XO extends WkSerdeDtreeAggregatorMsgReader<T,?,?,?,?>,
  YO extends WkSerdeDtreeAggregatorMsgWriter<T,?,?,?,?>>
  WkSerdeDtreeStructSubfieldCore<
    WkByteArray, T, WkSerdeDtreeOperationSettings, WkSerdeDtreeFixedSizeByteArray,
    WkSerdeDtreeFixedSizeByteArrayReader, ? extends WkSerdeDtreeBytestreamInputBase<?>,
    XO, WkSerdeDtreeOperationSettings, WkSerdeDtreeFixedSizeByteArray,
    WkSerdeDtreeFixedSizeByteArrayWriter, ? extends WkSerdeDtreeBytestreamOutputBase<?>,
    YO, WkSerdeDtreeFixedSizeByteArray>
  addAsSubfieldWithSingleOperation(
    int expectedSize,
    WkSerdeDtreeAggregatorStructDefinitionCore<
      T,?,?,? extends WkSerdeDtreeBytestreamInputBase<?>,?,?,?,?,XO,?,?,?,?,
      ? extends WkSerdeDtreeBytestreamOutputBase<?>,?,?,?,?,YO,?,?,?,?> aggregatorCore,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    Optional<Predicate<? super YO>> txEnablingTest,
    WkSzPacketWriteDisaggregator<WkByteArray,WkSerdeDtreeFixedSizeByteArray,T,YO> disaggregator,
    boolean readRequired) {
    return aggregatorCore.<
              WkByteArray,
              WkSerdeDtreeOperationSettings,
              WkSerdeDtreeFixedSizeByteArray,
              WkSerdeDtreeFixedSizeByteArrayReader,
              WkSerdeDtreeOperationSettings,
              WkSerdeDtreeFixedSizeByteArray,
              WkSerdeDtreeFixedSizeByteArrayWriter,
              WkSerdeDtreeFixedSizeByteArray>addSubcomponent(
        label,
        rxEnablingTest,
        WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
        WkSerdeDtreeOperationSettings::none,
        txEnablingTest,
        WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
        WkSerdeDtreeOperationSettings::none,
        disaggregator,
        readRequired,
        (pc) -> WkSerdeDtreeFixedSizeByteArray.newCore(expectedSize, pc));
  }

  public static WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        WkSerdeDtreeFixedSizeByteArray>
  newCore(
    int expectedLength,
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
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
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
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
                                    WkSerdeDtreeFixedSizeByteArrayReader::newCore,
                                    WkSerdeDtreeByteArrayReaderDecoderEngine.FACTORY,
                                    (WkSerdeUtilsPrimitiveArrayLengthGetter<WkByteArray,WkSerdeDtreeOperationSettings,WkSerdeDtreeFixedSizeByteArray>)WkSerdeDtreeFixedSizeByteArray::getTxRequestedLengthFromDefinition,
                                    WkSerdeDtreeFixedSizeByteArrayWriter::newCore,
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
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public int getExpectedLength() {
    return this.fixedSizeParameter.sequenceExpectedSize();
  }

}
