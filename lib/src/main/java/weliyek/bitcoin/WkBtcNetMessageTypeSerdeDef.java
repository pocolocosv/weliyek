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

public class WkBtcNetMessageTypeSerdeDef
    implements WkSerdeDtreeAggregatorStructDefinition<WkBtcNetMessageType>
{

  final WkSerdeDtreeStructSubfieldCore<
                        WkByteArray,
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayReader,
                        ? extends WkSerdeDtreeBytestreamInputBase<?>,
                        WkBtcNetMessageTypeSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        ? extends WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcNetMessageTypeSerdeWriter,
                        WkSerdeDtreeFixedSizeByteArray> byteArray;

  final WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeDef,
                        WkBtcNetMessageTypeSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeDef,
                        WkBtcNetMessageTypeSerdeWriter,
                        WkBtcNetMessageTypeSerdeDef> definitionCore;

  static WkSerdeDtreeStruct<
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeDef,
                        WkBtcNetMessageTypeSerdeReader,
                        WkSerdeDtreeBytestreamInputBase<?>,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeDef,
                        WkBtcNetMessageTypeSerdeWriter,
                        WkSerdeDtreeBytestreamOutputBase<?>,
                        WkBtcNetMessageTypeSerdeDef>
  newStruct(String label) {
    return new WkSerdeDtreeStructCore<>(
                            label,
                            WkBtcNetMessageTypeSerdeDef::newCore,
                            WkSerdeDtreeBytestreamCountingInputStream::new,
                            WkSerdeDtreeBytestreamCountingOutputStream::new);
  }

  static public WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<
                        WkBtcNetMessageType,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeDef,
                        WkBtcNetMessageTypeSerdeReader,
                        WkSerdeDtreeOperationSettings,
                        WkBtcNetMessageTypeSerdeDef,
                        WkBtcNetMessageTypeSerdeWriter,
                        WkBtcNetMessageTypeSerdeDef>
  newCore(
      WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    return new WkBtcNetMessageTypeSerdeDef(fieldCore).definitionCore;
  }

  WkBtcNetMessageTypeSerdeDef(
    WkSerdeDtreeStructFieldCore<?,?,?,?,?,?,?,?> fieldCore) {
    this.definitionCore = new WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcNetMessageType, WkSerdeDtreeOperationSettings, WkBtcNetMessageTypeSerdeDef, WkBtcNetMessageTypeSerdeReader, WkSerdeDtreeOperationSettings, WkBtcNetMessageTypeSerdeDef, WkBtcNetMessageTypeSerdeWriter, WkBtcNetMessageTypeSerdeDef>(
                                  WkBtcNetMessageTypeSerdeReader::newReaderCore,
                                  WkBtcNetMessageTypeSerdeWriter::newWriter,
                                  fieldCore,
                                  WkBtcNetMessageTypeSerdeDef::onReadInit,
                                  WkBtcNetMessageTypeSerdeDef::onFullRead,
                                  WkBtcNetMessageTypeSerdeDef::onSkippedRead,
                                  WkBtcNetMessageTypeSerdeDef::onWriteInit,
                                  this,
                                  WkBtcNetMessageType.class);

    this.byteArray = WkSerdeDtreeFixedSizeByteArray.<
                        WkBtcNetMessageType,
                        WkBtcNetMessageTypeSerdeReader,
                        WkBtcNetMessageTypeSerdeWriter>
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
      WkBtcNetMessageTypeSerdeDef, WkBtcNetMessageTypeSerdeReader> readerCore) {
    // Nothing to do.
  }

  private static WkBtcNetMessageType onFullRead(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageType, WkSerdeDtreeOperationSettings,
      WkBtcNetMessageTypeSerdeDef, WkBtcNetMessageTypeSerdeReader> readerCore) {
    WkByteArray bytes = readerCore.body().byteArray().get().firstOperation().get().result().get().serializable().get();
    return WkBtcNetMessageType.newCommand(bytes);
  }

  private static void onSkippedRead(
    WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
      WkBtcNetMessageType, WkSerdeDtreeOperationSettings,
      WkBtcNetMessageTypeSerdeDef, WkBtcNetMessageTypeSerdeReader> readerCore) {
    // Nothing to do.
  }

  private static void onWriteInit(
    WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
      WkBtcNetMessageType, WkSerdeDtreeOperationSettings,
      WkBtcNetMessageTypeSerdeDef, WkBtcNetMessageTypeSerdeWriter> writerCore) {
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
