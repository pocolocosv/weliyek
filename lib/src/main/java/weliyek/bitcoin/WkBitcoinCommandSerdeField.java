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
import weliyek.serialization.WkSerdeDtreeStructSubfieldCore;
import weliyek.util.array.WkByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArray;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayReader;
import weliyek.util.array.WkSerdeDtreeFixedSizeByteArrayWriter;

public class WkBitcoinCommandSerdeField
    implements WkSerdeDtreeAggregatorStructDefinition<WkBitcoinCommand>
{

  final WkSerdeDtreeStructSubfieldCore<
                        WkByteArray,
                        WkBitcoinCommand,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        ? extends WkSerdeDtreeBytestreamInputBase<?>,
                        WkBitcoinCommandSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        ? extends WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBitcoinCommandSerdeFieldWriter,
                        WkSerdeDtreeFixedSizeByteArray> byteArray;

  final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBitcoinCommand,
                        WkSerdeDtreeOperationSettings,
                        WkBitcoinCommandSerdeField,
                        WkBitcoinCommandSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkBitcoinCommandSerdeField,
                        WkBitcoinCommandSerdeFieldWriter,
                        WkBitcoinCommandSerdeField> definitionCore;

  static public WkSerdeDtreeStruct<
                        WkBitcoinCommand,
                        WkSerdeDtreeOperationSettings,
                        WkBitcoinCommandSerdeField,
                        WkBitcoinCommandSerdeFieldReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkBitcoinCommandSerdeField,
                        WkBitcoinCommandSerdeFieldWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBitcoinCommandSerdeField>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                            label,
                            WkBitcoinCommandSerdeField::newCore,
                            WkSerdeDtreeBytestreamCountingInputStream::new,
                            WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  static public WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBitcoinCommand,
                        WkSerdeDtreeOperationSettings,
                        WkBitcoinCommandSerdeField,
                        WkBitcoinCommandSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkBitcoinCommandSerdeField,
                        WkBitcoinCommandSerdeFieldWriter,
                        WkBitcoinCommandSerdeField>
  newCore(
      WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    return new WkBitcoinCommandSerdeField(fieldCore).definitionCore;
  }

  WkBitcoinCommandSerdeField(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBitcoinCommand, WkSerdeDtreeOperationSettings, WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldReader, WkSerdeDtreeOperationSettings, WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldWriter, WkBitcoinCommandSerdeField>(
                                  WkBitcoinCommandSerdeFieldReader::newReaderCore,
                                  WkBitcoinCommandSerdeFieldWriter::newWriter,
                                  fieldCore,
                                  WkBitcoinCommandSerdeField::onReadInit,
                                  WkBitcoinCommandSerdeField::onFullRead,
                                  WkBitcoinCommandSerdeField::onSkippedRead,
                                  WkBitcoinCommandSerdeField::onWriteInit,
                                  this,
                                  WkBitcoinCommand.class);

    this.byteArray = WkSerdeDtreeFixedSizeByteArray.<
                        WkBitcoinCommand,
                        WkBitcoinCommandSerdeFieldReader,
                        WkBitcoinCommandSerdeFieldWriter>
                            addAsSubfieldWithSingleOperation(
                                WkBitcoinCommand.CANONICAL_LENGTH,
                                definitionCore,
                                "BYTES",
                                Optional.empty(),
                                Optional.empty(),
                                (k,ao,i) -> ao.serializable().bytes,
                                false);
  }

  private static void onReadInit(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBitcoinCommand, WkSerdeDtreeOperationSettings,
      WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldReader> readerCore) {
    // Nothing to do.
  }

  private static WkBitcoinCommand onFullRead(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBitcoinCommand, WkSerdeDtreeOperationSettings,
      WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldReader> readerCore) {
    WkByteArray bytes = readerCore.body().byteArray().get().firstOperation().get().result().get().serializable().get();
    return WkBitcoinCommand.newCommand(bytes);
  }

  private static void onSkippedRead(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBitcoinCommand, WkSerdeDtreeOperationSettings,
      WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldReader> readerCore) {
    // Nothing to do.
  }

  private static void onWriteInit(
    WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
      WkBitcoinCommand, WkSerdeDtreeOperationSettings,
      WkBitcoinCommandSerdeField, WkBitcoinCommandSerdeFieldWriter> writerCore) {
    // Nothing to do.
  }

  @Override
  public Class<WkBitcoinCommand> serializableClass() {
    return WkBitcoinCommand.class;
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
