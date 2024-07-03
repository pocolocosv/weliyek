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

import weliyek.serialization.WkSerdeDtreeAggregatorMsgReader;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgReaderCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongReader;
import weliyek.serialization.number.WkSerdeUnsignedByte;
import weliyek.serialization.number.WkSerdeUnsignedByteReader;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianIntegerReader;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianShort;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianShortReader;

public class WkBtcCompactSizeSerdeReader
    implements WkSerdeDtreeAggregatorMsgReader<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<Long>,
                        WkBtcCompactSizeSerdeDef>
{

  final WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeReader> readerCore;

  WkBtcCompactSizeSerdeReader(
    int index,
    WkSerdeDtreeMsgInputFieldCore<?, WkSerdeDtreeOperationSettings, ?, ?, WkSerdeDtreeBytestreamInputBase<?>, ?, ?, ?> readerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader, ?, ?, ?, ? extends WkBtcCompactSizeSerdeDef>
      definitionCore) {
    this.readerCore = new WkSerdeDtreeAggregatorMsgReaderCoreSimplified<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeReader>(
                            index,
                            readerFieldCore,
                            definitionCore,
                            this);
  }

  public Optional<WkSerdeDtreeMsgInputField<Integer, WkSerdeUnsignedByte, WkSerdeUnsignedByteReader>>
  firstByte() {
    return Optional.ofNullable(readerCore.getSubfieldpacketFor(definition().firstByte).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<Integer, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortReader>>
  uint16le() {
    return Optional.ofNullable(readerCore.getSubfieldpacketFor(definition().uint16le).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<Long, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerReader>>
  uint32le() {
    return Optional.ofNullable(readerCore.getSubfieldpacketFor(definition().uint32le).asPacket());
  }

  public Optional<WkSerdeDtreeMsgInputField<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongReader>>
  int64le() {
    return Optional.ofNullable(readerCore.getSubfieldpacketFor(definition().int64le).asPacket());
  }

  @Override
  public WkBtcCompactSizeSerdeDef definition() {
    return this.readerCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.readerCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.readerCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<Long>> result() {
    return this.readerCore.result();
  }

  @Override
  public int index() {
    return this.readerCore.index();
  }

  @Override
  public String name() {
    return this.readerCore.name();
  }

  @Override
  public List<WkSerdeDtreeMsgInputField<?, ?, ?>> subfields() {
    return this.readerCore.subfields();
  }

  @Override
  public WkSerdeDtreeMsgInputField<?, ?, ?> parentField() {
    return this.readerCore.parentField();
  }

}
