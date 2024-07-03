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

public class WkSerdeSignedByte
    implements WkSerdeDtreeNumberStructDefinition<Byte>
{

  public static <
  T,
  XO extends WkSerdeDtreeAggregatorMsgReader<T,?,?,?,?>,
  YO extends WkSerdeDtreeAggregatorMsgWriter<T,?,?,?,?>>
  WkSerdeDtreeStructSubfieldCore<Byte, T, WkSerdeDtreeOperationSettings, WkSerdeSignedByte,
    WkSerdeSignedByteReader, ? extends WkSerdeDtreeBytestreamInputBase<?>, XO,
    WkSerdeDtreeOperationSettings, WkSerdeSignedByte, WkSerdeSignedByteWriter,
    ? extends WkSerdeDtreeBytestreamOutputBase<?>, YO, WkSerdeSignedByte>
  addAsSingleOperationSubfield(
    String byteLabel,
    WkSerdeDtreeAggregatorStructDefinitionCore<
      T,?,?,? extends WkSerdeDtreeBytestreamInputBase<?>,?,?,?,?,XO,?,?,?,?,
      ? extends WkSerdeDtreeBytestreamOutputBase<?>,?,?,?,?,YO,?,?,?,?> aggregatorCore,
    Optional<Predicate<? super XO>> rxEnablingTest,
    Optional<Predicate<? super YO>> txEnablingTest,
    WkSzPacketWriteDisaggregator<Byte,WkSerdeSignedByte,T,YO>
      disaggregator,
    boolean readRequired) {
    return aggregatorCore.<Byte, WkSerdeDtreeOperationSettings, WkSerdeSignedByte,
             WkSerdeSignedByteReader, WkSerdeDtreeOperationSettings,
             WkSerdeSignedByte, WkSerdeSignedByteWriter, WkSerdeSignedByte>
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
                    WkSerdeSignedByte::newCore);
  }

  public static WkSerdeDtreeStruct<
                      Byte,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedByte,
                      WkSerdeSignedByteReader,
                      WkSerdeDtreeBytestreamInputBase<?>,
                      WkSerdeDtreeOperationSettings,
                      WkSerdeSignedByte,
                      WkSerdeSignedByteWriter,
                      WkSerdeDtreeBytestreamOutputBase<?>,
                      WkSerdeSignedByte>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeSignedByte::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                    Byte,
                    WkSerdeSignedByteReader,
                    WkSerdeSignedByteWriter,
                    WkSerdeSignedByte>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedByte(componentCore).definitionCore;
  }

  private WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Byte,
                        WkSerdeSignedByteReader,
                        WkSerdeSignedByteWriter,
                        WkSerdeSignedByte> definitionCore;

  private WkSerdeSignedByte(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                Byte,
                                WkSerdeSignedByteReader,
                                WkSerdeSignedByteWriter,
                                WkSerdeSignedByte>(
                                    componentCore,
                                    (i,xkc,xdc) -> new WkSerdeSignedByteReader(i,xkc,xdc).operationCore,
                                    WkSerdeDtreeByteSignedReaderDecoderEngine.FACTORY,
                                    (i,ykc,ydc) -> new WkSerdeSignedByteWriter(i,ykc,ydc).writingCore,
                                    WkSerdeDtreeByteSignedWriterEncoderEngine.FACTORY,
                                    this,
                                    Byte.class);
  }

  @Override
  public Class<Byte> serializableClass() {
    return Byte.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

}
