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

public class WkBtcNetMessageTypeSerdeField
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcNetMessageType>
{

  final WkSerdeDtreeStructSubfieldCore<
                        WkByteArray,
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        ? extends WkSerdeDtreeBytestreamInputBase<?>,
                        WkBtcNetMessageTypeSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        ? extends WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcNetMessageTypeSerdeFieldWriter,
                        WkSerdeDtreeFixedSizeByteArray> byteArray;

  final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeField,
                        WkBtcNetMessageTypeSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeField,
                        WkBtcNetMessageTypeSerdeFieldWriter,
                        WkBtcNetMessageTypeSerdeField> definitionCore;

  static WkSerdeDtreeStruct<
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeField,
                        WkBtcNetMessageTypeSerdeFieldReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeField,
                        WkBtcNetMessageTypeSerdeFieldWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcNetMessageTypeSerdeField>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                            label,
                            WkBtcNetMessageTypeSerdeField::newCore,
                            WkSerdeDtreeBytestreamCountingInputStream::new,
                            WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  static public WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeField,
                        WkBtcNetMessageTypeSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeField,
                        WkBtcNetMessageTypeSerdeFieldWriter,
                        WkBtcNetMessageTypeSerdeField>
  newCore(
      WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    return new WkBtcNetMessageTypeSerdeField(fieldCore).definitionCore;
  }

  WkBtcNetMessageTypeSerdeField(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcNetMessageType, WkSerdeDtreeOperationSettings, WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldReader, WkSerdeDtreeOperationSettings, WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldWriter, WkBtcNetMessageTypeSerdeField>(
                                  WkBtcNetMessageTypeSerdeFieldReader::newReaderCore,
                                  WkBtcNetMessageTypeSerdeFieldWriter::newWriter,
                                  fieldCore,
                                  WkBtcNetMessageTypeSerdeField::onReadInit,
                                  WkBtcNetMessageTypeSerdeField::onFullRead,
                                  WkBtcNetMessageTypeSerdeField::onSkippedRead,
                                  WkBtcNetMessageTypeSerdeField::onWriteInit,
                                  this,
                                  WkBtcNetMessageType.class);

    this.byteArray = WkSerdeDtreeFixedSizeByteArray.<
                        WkBtcNetMessageType,
                        WkBtcNetMessageTypeSerdeFieldReader,
                        WkBtcNetMessageTypeSerdeFieldWriter>
                            addAsSubfieldWithSingleOperation(
                                WkBtcNetMessageType.CANONICAL_LENGTH,
                                definitionCore,
                                "BYTES",
                                Optional.empty(),
                                Optional.empty(),
                                (k,ao,i) -> ao.serializable().bytes,
                                false);
  }

  private static void onReadInit(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageType, WkSerdeDtreeOperationSettings,
      WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldReader> readerCore) {
    // Nothing to do.
  }

  private static WkBtcNetMessageType onFullRead(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageType, WkSerdeDtreeOperationSettings,
      WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldReader> readerCore) {
    WkByteArray bytes = readerCore.body().byteArray().get().firstOperation().get().result().get().serializable().get();
    return WkBtcNetMessageType.newCommand(bytes);
  }

  private static void onSkippedRead(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageType, WkSerdeDtreeOperationSettings,
      WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldReader> readerCore) {
    // Nothing to do.
  }

  private static void onWriteInit(
    WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
      WkBtcNetMessageType, WkSerdeDtreeOperationSettings,
      WkBtcNetMessageTypeSerdeField, WkBtcNetMessageTypeSerdeFieldWriter> writerCore) {
    // Nothing to do.
  }

  @Override
  public Class<WkBtcNetMessageType> serializableClass() {
    return WkBtcNetMessageType.class;
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
