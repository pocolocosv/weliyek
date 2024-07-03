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

public class WkSerdeUnsignedByte
    implements WkSerdeDtreeNumberStructDefinition<Integer>
{

  public static <T,
                 XO extends WkSerdeDtreeAggregatorMsgReader<T,?,?,?,?>,
                 YO extends WkSerdeDtreeAggregatorMsgWriter<T,?,?,?,?>>
  WkSerdeDtreeStructSubfieldCore<Integer, T, WkSerdeDtreeOperationSettings, WkSerdeUnsignedByte,
  WkSerdeUnsignedByteReader, ? extends WkSerdeDtreeBytestreamInputBase<?>, XO,
    WkSerdeDtreeOperationSettings, WkSerdeUnsignedByte, WkSerdeUnsignedByteWriter,
    ? extends WkSerdeDtreeBytestreamOutputBase<?>, YO, WkSerdeUnsignedByte>
  addAsSingleOperationSubfield(
    String byteLabel,
    WkSerdeDtreeAggregatorStructDefinitionCore<
      T,?,?,? extends WkSerdeDtreeBytestreamInputBase<?>,?,?,?,?,XO,?,?,?,?,
      ? extends WkSerdeDtreeBytestreamOutputBase<?>,?,?,?,?,YO,?,?,?,?> aggregatorCore,
    Optional<Predicate<? super XO>> rxEnablingTest,
    Optional<Predicate<? super YO>> txEnablingTest,
    WkSzPacketWriteDisaggregator<Integer,WkSerdeUnsignedByte,T,YO>
      disaggregator,
    boolean readRequired) {
    return aggregatorCore.<Integer, WkSerdeDtreeOperationSettings, WkSerdeUnsignedByte,
             WkSerdeUnsignedByteReader, WkSerdeDtreeOperationSettings,
             WkSerdeUnsignedByte, WkSerdeUnsignedByteWriter, WkSerdeUnsignedByte>
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
                    WkSerdeUnsignedByte::newCore);
  }

  public static WkSerdeDtreeStruct<
                      Integer,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeUnsignedByte,
                      WkSerdeUnsignedByteReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeUnsignedByte,
                      WkSerdeUnsignedByteWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeUnsignedByte>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeUnsignedByte::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                      Integer,
                      WkSerdeUnsignedByteReader,
                      WkSerdeUnsignedByteWriter,
                      WkSerdeUnsignedByte>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeUnsignedByte(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeUnsignedByteReader,
                        WkSerdeUnsignedByteWriter,
                        WkSerdeUnsignedByte> definitionCore;

  private WkSerdeUnsignedByte(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<>(
                                  componentCore,
                                  (i,xkc,dc) -> new WkSerdeUnsignedByteReader(i,xkc,dc).operationCore,
                                  WkSerdeDtreeByteUnsignedReaderDecoderEngine.FACTORY,
                                  (i,ykc,dc) -> new WkSerdeUnsignedByteWriter(i,ykc,dc).writingCore,
                                  WkSerdeDtreeByteUnsignedWriterEncoderEngine.FACTORY,
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
