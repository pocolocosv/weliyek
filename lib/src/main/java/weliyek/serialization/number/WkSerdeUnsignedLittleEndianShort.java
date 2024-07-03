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
package weliyek.serialization.number;

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

public class WkSerdeUnsignedLittleEndianShort
    implements WkSerdeDtreeNumberStructDefinition<Integer>
{

  public static <T,
                 XO extends WkSerdeDtreeAggregatorMsgReader<T,?,?,?,?>,
                 YO extends WkSerdeDtreeAggregatorMsgWriter<T,?,?,?,?>>
  WkSerdeDtreeStructSubfieldCore<Integer, T, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianShort,
    WkSerdeUnsignedLittleEndianShortReader, ? extends WkSerdeDtreeBytestreamInputBase<?>, XO,
    WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter,
    ? extends WkSerdeDtreeBytestreamOutputBase<?>, YO, WkSerdeUnsignedLittleEndianShort>
  addAsSingleOperationSubfield(
    String byteLabel,
    WkSerdeDtreeAggregatorStructDefinitionCore<
      T,?,?,? extends WkSerdeDtreeBytestreamInputBase<?>,?,?,?,?,XO,?,?,?,?,
      ? extends WkSerdeDtreeBytestreamOutputBase<?>,?,?,?,?,YO,?,?,?,?> aggregatorCore,
    Optional<Predicate<? super XO>> rxEnablingTest,
    Optional<Predicate<? super YO>> txEnablingTest,
    WkSzPacketWriteDisaggregator<Integer,WkSerdeUnsignedLittleEndianShort,T,YO>
      disaggregator,
    boolean readRequired) {
    return aggregatorCore.<Integer, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianShort,
             WkSerdeUnsignedLittleEndianShortReader, WkSerdeDtreeOperationSettings,
             WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter, WkSerdeUnsignedLittleEndianShort>
                addSubcomponent(
                    byteLabel,
                    rxEnablingTest,
                    WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                    WkSerdeDtreeOperationSettings::none,
                    txEnablingTest,
                    WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                    WkSerdeDtreeOperationSettings::none,
                    disaggregator,
                    readRequired,
                    WkSerdeUnsignedLittleEndianShort::newCore);
  }

  public static WkSerdeDtreeStruct<
                      Integer,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeUnsignedLittleEndianShort,
                      WkSerdeUnsignedLittleEndianShortReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeUnsignedLittleEndianShort,
                      WkSerdeUnsignedLittleEndianShortWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeUnsignedLittleEndianShort>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeUnsignedLittleEndianShort::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeUnsignedLittleEndianShortReader,
                        WkSerdeUnsignedLittleEndianShortWriter,
                        WkSerdeUnsignedLittleEndianShort>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeUnsignedLittleEndianShort(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeUnsignedLittleEndianShortReader,
                        WkSerdeUnsignedLittleEndianShortWriter,
                        WkSerdeUnsignedLittleEndianShort> definitionCore;

  private WkSerdeUnsignedLittleEndianShort(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Integer,
                                  WkSerdeUnsignedLittleEndianShortReader,
                                  WkSerdeUnsignedLittleEndianShortWriter,
                                  WkSerdeUnsignedLittleEndianShort>(
                                      componentCore,
                                      (i,xkc,dc) -> new WkSerdeUnsignedLittleEndianShortReader(i,xkc,dc).operationCore,
                                      WkSerdeDtreeShortUnsignedLittleEndianReaderDecoderEngine.FACTORY,
                                      (i,ykc,dc) -> new WkSerdeUnsignedLittleEndianShortWriter(i,ykc,dc).operationCore,
                                      WkSerdeDtreeShortUnsignedLittleEndianWriterEncoderEngine.FACTORY,
                                      this,
                                      Integer.class);
  }

  @Override
  public Class<Integer> serializableClass() {
    return Integer.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

}
