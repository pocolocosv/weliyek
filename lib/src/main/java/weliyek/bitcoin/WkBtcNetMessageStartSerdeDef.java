/*
 * Copyright (C) 2024  Ricardo Villalobos - All Rights Reserved
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
package weliyek.bitcoin;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfield;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianIntegerWriter;

public class WkBtcNetMessageStartSerdeDef
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcNetMessageStart>
{

  static public WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeDef,
                    WkBtcNetMessageStartSerdeReader,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeDef,
                    WkBtcNetMessageStartSerdeWriter,
                    WkBtcNetMessageStartSerdeDef>
  newCore(WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    return new WkBtcNetMessageStartSerdeDef(fieldCore).definitionCore;
  }

  static WkSerdeDtreeStruct<
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeDef,
                    WkBtcNetMessageStartSerdeReader,
                    WkSerdeDtreeBytestreamInputBase<?>,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeDef,
                    WkBtcNetMessageStartSerdeWriter,
                    WkSerdeDtreeBytestreamOutputBase<?>,
                    WkBtcNetMessageStartSerdeDef>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                    label,
                    WkBtcNetMessageStartSerdeDef::newCore,
                    WkSerdeDtreeBytestreamCountingInputStream::new,
                    WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeDef,
                    WkBtcNetMessageStartSerdeReader,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeDef,
                    WkBtcNetMessageStartSerdeWriter,
                    WkBtcNetMessageStartSerdeDef> definitionCore;

  final WkSerdeDtreeStructSubfieldCore<
                    Long,
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeUnsignedLittleEndianInteger,
                    WkSerdeUnsignedLittleEndianIntegerReader,
                    WkSerdeDtreeBytestreamInputBase<?>,
                    WkBtcNetMessageStartSerdeReader,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeUnsignedLittleEndianInteger,
                    WkSerdeUnsignedLittleEndianIntegerWriter,
                    WkSerdeDtreeBytestreamOutputBase<?>,
                    WkBtcNetMessageStartSerdeWriter,
                    WkSerdeUnsignedLittleEndianInteger> intField;

  private WkBtcNetMessageStartSerdeDef(
    WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                                WkBtcNetMessageStart,
                                WkSerdeDtreeOperationSettings,
                                WkBtcNetMessageStartSerdeDef,
                                WkBtcNetMessageStartSerdeReader,
                                WkSerdeDtreeOperationSettings,
                                WkBtcNetMessageStartSerdeDef,
                                WkBtcNetMessageStartSerdeWriter,
                                WkBtcNetMessageStartSerdeDef>(
                                    (i,xkc,dc) -> new WkBtcNetMessageStartSerdeReader(i,xkc,dc).readerCore,
                                    (i,ykc,dc) -> new WkBtcNetMessageStartSerdeWriter(i,ykc,dc).writerCore,
                                    fieldCore,
                                    WkBtcNetMessageStartSerdeDef::onReadInit,
                                    WkBtcNetMessageStartSerdeDef::onReadFull,
                                    WkBtcNetMessageStartSerdeDef::onReadSkipped,
                                    WkBtcNetMessageStartSerdeDef::onWriteInit,
                                    this,
                                    WkBtcNetMessageStart.class);
    this.intField = definitionCore.<Long, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianInteger,
                      WkSerdeUnsignedLittleEndianIntegerReader, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianInteger,
                      WkSerdeUnsignedLittleEndianIntegerWriter, WkSerdeUnsignedLittleEndianInteger>
                        addSubcomponent(
                          "UINT32LE",
                          Optional.empty(),
                          WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                          WkSerdeDtreeOperationSettings::none,
                          Optional.empty(),
                          WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                          WkSerdeDtreeOperationSettings::none,
                          (k,ao,i) -> ao.serializable().value, //disaggregator,
                          false,
                          WkSerdeUnsignedLittleEndianInteger::newCore);
  }

  public WkSerdeDtreeStructSubfield<
            WkBtcNetMessageStartSerdeReader,
            WkBtcNetMessageStartSerdeWriter,
            WkSerdeUnsignedLittleEndianInteger>
  uint32le() {
    return this.intField.asProtocolField();
  }

  private static void onReadInit(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageStart, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeDef,
      WkBtcNetMessageStartSerdeReader> readerCore) {
  }

  private static WkBtcNetMessageStart onReadFull(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageStart, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeDef,
      WkBtcNetMessageStartSerdeReader> readerCore) {
    Long val = readerCore.body().uint32le().get().firstOperation().get().result().get().serializable().get();
    return WkBtcNetMessageStart.newMagic(val.intValue());
  }

  private static void onReadSkipped(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageStart, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeDef,
      WkBtcNetMessageStartSerdeReader> readerCore) {
  }

  private static void onWriteInit(
    WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
      WkBtcNetMessageStart,
      WkSerdeDtreeOperationSettings,
      WkBtcNetMessageStartSerdeDef,
      WkBtcNetMessageStartSerdeWriter> writerCore) {
  }

  @Override
  public final Class<WkBtcNetMessageStart> serializableClass() {
    return this.definitionCore.serializableClass();
  }

  @Override
  public final List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public final List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
