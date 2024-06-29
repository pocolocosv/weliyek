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
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinition;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCore;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingInputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamCountingOutputStream;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeStruct;
import weliyek.serialization.WkSerdeDtreeStructCore;
import weliyek.serialization.WkSerdeDtreeStructField;
import weliyek.serialization.WkSerdeDtreeStructFieldCore;
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;

public class WkBtcProtocolVersionSerde
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcProtocolVersion>
{

  public static WkSerdeDtreeStruct<
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerde,
                        WkBtcProtocolVersionSerdeReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerde,
                        WkBtcProtocolVersionSerdeWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcProtocolVersionSerde>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                        label,
                        WkBtcProtocolVersionSerde::newCore,
                        WkSerdeDtreeBytestreamCountingInputStream::new,
                        WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  public static WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerde,
                        WkBtcProtocolVersionSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerde,
                        WkBtcProtocolVersionSerdeWriter,
                        WkBtcProtocolVersionSerde>
  newCore(WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    return new WkBtcProtocolVersionSerde(fieldCore).definitionCore;
  }

  private final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerde,
                        WkBtcProtocolVersionSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerde,
                        WkBtcProtocolVersionSerdeWriter,
                        WkBtcProtocolVersionSerde> definitionCore;

  final WkSerdeDtreeStructSubfieldCore<
                        Integer,
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianIntegerReader,
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkBtcProtocolVersionSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeSignedLittleEndianInteger,
                        WkSerdeSignedLittleEndianIntegerWriter,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
                        WkBtcProtocolVersionSerdeWriter,
                        WkSerdeSignedLittleEndianInteger> int32;

  private WkBtcProtocolVersionSerde(
    WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?, ?, ?, ?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcProtocolVersion, WkSerdeDtreeOperationSettings, WkBtcProtocolVersionSerde, WkBtcProtocolVersionSerdeReader, WkSerdeDtreeOperationSettings, WkBtcProtocolVersionSerde, WkBtcProtocolVersionSerdeWriter, WkBtcProtocolVersionSerde>(
                                  WkBtcProtocolVersionSerdeReader::newCore,
                                  WkBtcProtocolVersionSerdeWriter::newCore,
                                  fieldCore,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                  WkBtcProtocolVersionSerde::onReadFull,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnReadInitOrSkipped,
                                  WkSerdeDtreeAggregatorStructDefinitionCoreSimplified::doNothingOnWriteInit,
                                  this,
                                  WkBtcProtocolVersion.class);
    this.int32 = definitionCore.<Integer, WkSerdeDtreeOperationSettings,WkSerdeSignedLittleEndianInteger,WkSerdeSignedLittleEndianIntegerReader,WkSerdeDtreeOperationSettings,WkSerdeSignedLittleEndianInteger,WkSerdeSignedLittleEndianIntegerWriter,WkSerdeSignedLittleEndianInteger>addSubcomponent(
                                  "INT32",
                                  Optional.empty(),
                                  WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                                  WkSerdeDtreeOperationSettings::none,
                                  Optional.empty(),
                                  WkSerdeDtreeAggregatorStructDefinitionCore::opWithSingleResult,
                                  WkSerdeDtreeOperationSettings::none,
                                  (k,ao,i) -> ao.serializable().value,
                                  false,
                                  WkSerdeSignedLittleEndianInteger::newCore);
  }

  private static WkBtcProtocolVersion onReadFull(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcProtocolVersion, WkSerdeDtreeOperationSettings, WkBtcProtocolVersionSerde,
      WkBtcProtocolVersionSerdeReader> readerCore) {
    int intValue = readerCore.body().int32le().get().firstOperation().get().result().get().serializable().get().intValue();
    return WkBtcProtocolVersion.newVersion(intValue);
  }

  @Override
  public Class<WkBtcProtocolVersion> serializableClass() {
    return WkBtcProtocolVersion.class;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    return this.definitionCore.subfields();
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    return this.definitionCore.requiredSubfields();
  }

}
