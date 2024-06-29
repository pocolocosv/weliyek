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
import weliyek.serialization.sequence.WkSerdeDtreeIntSignedLittleEndianWriterEncoderEngine;

public class WkSerdeSignedLittleEndianInteger
    implements WkSerdeDtreeNumberStructDefinition<Integer>
{

  public static <T,
  XO extends WkSerdeDtreeAggregatorMsgReader<T,?,?,?,?>,
  YO extends WkSerdeDtreeAggregatorMsgWriter<T,?,?,?,?>>
  WkSerdeDtreeStructSubfieldCore<
       Integer, T, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianInteger,
       WkSerdeSignedLittleEndianIntegerReader, ? extends WkSerdeDtreeBytestreamInputBase<?>,
       XO, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianInteger,
       WkSerdeSignedLittleEndianIntegerWriter, ? extends WkSerdeDtreeBytestreamOutputBase<?>,
       YO, WkSerdeSignedLittleEndianInteger>
  addAsSubfieldWithSingleOperation(
    String label,
    WkSzPacketWriteDisaggregator<Integer,WkSerdeSignedLittleEndianInteger,T,YO> disaggregator,
    WkSerdeDtreeAggregatorStructDefinitionCore<
    T,?,?,? extends WkSerdeDtreeBytestreamInputBase<?>,?,?,?,?,XO,?,?,?,?,
    ? extends WkSerdeDtreeBytestreamOutputBase<?>,?,?,?,?,YO,?,?,?,?> aggregatorCore) {
    return aggregatorCore.<Integer,
         WkSerdeDtreeOperationSettings,
         WkSerdeSignedLittleEndianInteger,
         WkSerdeSignedLittleEndianIntegerReader,
         WkSerdeDtreeOperationSettings,
         WkSerdeSignedLittleEndianInteger,
         WkSerdeSignedLittleEndianIntegerWriter,
         WkSerdeSignedLittleEndianInteger>
             addSubcomponent(
                 label,
                 Optional.empty(),
                 WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                 WkSerdeDtreeOperationSettings::none,
                 Optional.empty(),
                 WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                 WkSerdeDtreeOperationSettings::none,
                 disaggregator,
                 false,
                 WkSerdeSignedLittleEndianInteger::newCore);
  }

  public static <T,
                 XO extends WkSerdeDtreeAggregatorMsgReader<T,?,?,?,?>,
                 YO extends WkSerdeDtreeAggregatorMsgWriter<T,?,?,?,?>>
  WkSerdeDtreeStructSubfieldCore<
                    Integer, T, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianInteger,
                    WkSerdeSignedLittleEndianIntegerReader, ? extends WkSerdeDtreeBytestreamInputBase<?>,
                    XO, WkSerdeDtreeOperationSettings, WkSerdeSignedLittleEndianInteger,
                    WkSerdeSignedLittleEndianIntegerWriter, ? extends WkSerdeDtreeBytestreamOutputBase<?>,
                    YO, WkSerdeSignedLittleEndianInteger>
  addAsSubfieldWithSingleOperation(
    WkSerdeDtreeAggregatorStructDefinitionCore<
      T,?,?,? extends WkSerdeDtreeBytestreamInputBase<?>,?,?,?,?,XO,?,?,?,?,
      ? extends WkSerdeDtreeBytestreamOutputBase<?>,?,?,?,?,YO,?,?,?,?> aggregatorCore,
    String label,
    Optional<Predicate<? super XO>> rxEnablingTest,
    Optional<Predicate<? super YO>> txEnablingTest,
    WkSzPacketWriteDisaggregator<Integer,WkSerdeSignedLittleEndianInteger,T,YO> disaggregator,
    boolean readRequired) {
    return aggregatorCore.<Integer,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeSignedLittleEndianInteger,
                    WkSerdeSignedLittleEndianIntegerReader,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeSignedLittleEndianInteger,
                    WkSerdeSignedLittleEndianIntegerWriter,
                    WkSerdeSignedLittleEndianInteger>
                        addSubcomponent(
                            label,
                            rxEnablingTest,
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            txEnablingTest,
                            WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                            WkSerdeDtreeOperationSettings::none,
                            disaggregator,
                            readRequired,
                            WkSerdeSignedLittleEndianInteger::newCore);
  }


  public static WkSerdeDtreeStruct<
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkSerdeSignedLittleEndianInteger>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                      label,
                      WkSerdeSignedLittleEndianInteger::newCore,
                      WkSerdeDtreeBytestreamCountingInputStream::new,
                      WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger>
  newCore(WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    return new WkSerdeSignedLittleEndianInteger(componentCore).definitionCore;
  }

  private final WkSerdeDtreeNumberDefinitionCoreSimplified<
                        Integer,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeSignedLittleEndianInteger> definitionCore;

  public WkSerdeSignedLittleEndianInteger(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> componentCore) {
    this.definitionCore = new WkSerdeDtreeNumberDefinitionCoreSimplified<
                                  Integer,
                                  WkSerdeSignedLittleEndianIntegerReader,
                                  WkSerdeSignedLittleEndianIntegerWriter,
                                  WkSerdeSignedLittleEndianInteger>(
                                      componentCore,
                                      (i,xs,axb,xkc,dc) -> new WkSerdeSignedLittleEndianIntegerReader(i,xs,axb,xkc,dc).operationCore,
                                      WkSerdeDtreeIntegerSignedLittleEndianReaderDecoderEngine.FACTORY,
                                      (i,y,ys,ayb,ykc,dc) -> new WkSerdeSignedLittleEndianIntegerWriter(i,y,ys,ayb,ykc,dc).operationCore,
                                      WkSerdeDtreeIntSignedLittleEndianWriterEncoderEngine.FACTORY,
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
