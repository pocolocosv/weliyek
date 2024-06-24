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

public class WkBtcNetMessageStartSerdeField
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcNetMessageStart>
{

  static public WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeField,
                    WkBtcNetMessageStartSerdeFieldReader,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeField,
                    WkBtcNetMessageStartSerdeFieldWriter,
                    WkBtcNetMessageStartSerdeField>
  newCore(WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    return new WkBtcNetMessageStartSerdeField(fieldCore).definitionCore;
  }

  static WkSerdeDtreeStruct<
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeField,
                    WkBtcNetMessageStartSerdeFieldReader,
                    WkSerdeDtreeBytestreamInputBase<?>,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeField,
                    WkBtcNetMessageStartSerdeFieldWriter,
                    WkSerdeDtreeBytestreamOutputBase<?>,
                    WkBtcNetMessageStartSerdeField>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                    label,
                    WkBtcNetMessageStartSerdeField::newCore,
                    WkSerdeDtreeBytestreamCountingInputStream::new,
                    WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeField,
                    WkBtcNetMessageStartSerdeFieldReader,
                    WkSerdeDtreeOperationSettings,
                    WkBtcNetMessageStartSerdeField,
                    WkBtcNetMessageStartSerdeFieldWriter,
                    WkBtcNetMessageStartSerdeField> definitionCore;

  final WkSerdeDtreeStructSubfieldCore<
                    Long,
                    WkBtcNetMessageStart,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeUnsignedLittleEndianInteger,
                    WkSerdeUnsignedLittleEndianIntegerReader,
                    WkSerdeDtreeBytestreamInputBase<?>,
                    WkBtcNetMessageStartSerdeFieldReader,
                    WkSerdeDtreeOperationSettings,
                    WkSerdeUnsignedLittleEndianInteger,
                    WkSerdeUnsignedLittleEndianIntegerWriter,
                    WkSerdeDtreeBytestreamOutputBase<?>,
                    WkBtcNetMessageStartSerdeFieldWriter,
                    WkSerdeUnsignedLittleEndianInteger> intField;

  private WkBtcNetMessageStartSerdeField(
    WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                                WkBtcNetMessageStart,
                                WkSerdeDtreeOperationSettings,
                                WkBtcNetMessageStartSerdeField,
                                WkBtcNetMessageStartSerdeFieldReader,
                                WkSerdeDtreeOperationSettings,
                                WkBtcNetMessageStartSerdeField,
                                WkBtcNetMessageStartSerdeFieldWriter,
                                WkBtcNetMessageStartSerdeField>(
                                    WkBtcNetMessageStartSerdeFieldReader::newCore,
                                    WkBtcNetMessageStartSerdeFieldWriter::newCore,
                                    fieldCore,
                                    WkBtcNetMessageStartSerdeField::onReadInit,
                                    WkBtcNetMessageStartSerdeField::onReadFull,
                                    WkBtcNetMessageStartSerdeField::onReadSkipped,
                                    WkBtcNetMessageStartSerdeField::onWriteInit,
                                    this,
                                    WkBtcNetMessageStart.class);
    this.intField = definitionCore.<Long, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianInteger,
                      WkSerdeUnsignedLittleEndianIntegerReader, WkSerdeDtreeOperationSettings, WkSerdeUnsignedLittleEndianInteger,
                      WkSerdeUnsignedLittleEndianIntegerWriter, WkSerdeUnsignedLittleEndianInteger>
                        addSubcomponent(
                          "UINT32LE",
                          Optional.empty(),
                          WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
                          WkSerdeDtreeOperationSettings::none,
                          Optional.empty(),
                          WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
                          WkSerdeDtreeOperationSettings::none,
                          (k,ao,i) -> ao.serializable().value, //disaggregator,
                          false,
                          WkSerdeUnsignedLittleEndianInteger::newCore);
  }

  public WkSerdeDtreeStructSubfield<
            WkBtcNetMessageStartSerdeFieldReader,
            WkBtcNetMessageStartSerdeFieldWriter,
            WkSerdeUnsignedLittleEndianInteger>
  uint32le() {
    return this.intField.asProtocolField();
  }

  private static void onReadInit(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageStart, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeField,
      WkBtcNetMessageStartSerdeFieldReader> readerCore) {
  }

  private static WkBtcNetMessageStart onReadFull(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageStart, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeField,
      WkBtcNetMessageStartSerdeFieldReader> readerCore) {
    Long val = readerCore.body().uint32le().get().firstOperation().get().result().get().serializable().get();
    return WkBtcNetMessageStart.newMagic(val.intValue());
  }

  private static void onReadSkipped(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageStart, WkSerdeDtreeOperationSettings, WkBtcNetMessageStartSerdeField,
      WkBtcNetMessageStartSerdeFieldReader> readerCore) {
  }

  private static void onWriteInit(
    WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
      WkBtcNetMessageStart,
      WkSerdeDtreeOperationSettings,
      WkBtcNetMessageStartSerdeField,
      WkBtcNetMessageStartSerdeFieldWriter> writerCore) {
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
