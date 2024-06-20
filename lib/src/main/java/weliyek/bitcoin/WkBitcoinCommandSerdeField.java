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
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
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
                        WkSerdeDtreeBytestreamInputBase<? extends WkSerdeDtreeBytestreamInput>,
                        WkBitcoinCommandSerdeFieldReader,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayWriter,
                        WkSerdeDtreeBytestreamOutputBase<? extends WkSerdeDtreeBytestreamOutput>,
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

  public WkBitcoinCommandSerdeField(
    WkSerdeDtreeStructFieldCore<?, ?, ?, ?, ?> fieldCore,
    int expectedSize,
    String label) {
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

    this.byteArray = definitionCore.<WkByteArray, WkSerdeDtreeOperationSettings,
                                    WkSerdeDtreeFixedSizeByteArray, WkSerdeDtreeFixedSizeByteArrayReader,
                                    WkSerdeDtreeOperationSettings, WkSerdeDtreeFixedSizeByteArray,
                                    WkSerdeDtreeFixedSizeByteArrayWriter, WkSerdeDtreeFixedSizeByteArray>
                        addSubcomponent(
                                label,
                                Optional.empty(),
                                WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
                                WkSerdeDtreeOperationSettings::none,
                                Optional.empty(),
                                WkSerdeDtreeAggregatorStructDefinitionCore.singleOperation(),
                                WkSerdeDtreeOperationSettings::none,
                                (k,ao,i) -> ao.serializable().bytes,
                                false,
                                (pc) -> WkSerdeDtreeFixedSizeByteArray.newCore(expectedSize, pc));
    /*
    WkSerdeDtreeFixedSizeByteArray.addAsSubfield(
        expectedSize,
        definitionCore,
        label,
        Optional.empty(),
        Optional.empty(),
        (k,ao,i) -> ao.serializable().bytes,
        false);
        */
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
    return null; // TODO
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> subfields() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<WkSerdeDtreeStructField<?>> requiredSubfields() {
    // TODO Auto-generated method stub
    return null;
  }

}
