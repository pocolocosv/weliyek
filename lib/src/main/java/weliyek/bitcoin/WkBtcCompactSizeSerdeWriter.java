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

import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriter;
import weliyek.serialization.WkSerdeDtreeAggregatorMsgWriterCoreSimplified;
import weliyek.serialization.WkSerdeDtreeAggregatorStructDefinitionCoreSimplified;
import weliyek.serialization.WkSerdeDtreeBytestreamOutput;
import weliyek.serialization.WkSerdeDtreeBytestreamOutputBase;
import weliyek.serialization.WkSerdeDtreeMsgOutputField;
import weliyek.serialization.WkSerdeDtreeMsgOutputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationOutputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLong;
import weliyek.serialization.number.WkSerdeSignedLittleEndianLongWriter;
import weliyek.serialization.number.WkSerdeUnsignedByte;
import weliyek.serialization.number.WkSerdeUnsignedByteWriter;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianIntegerWriter;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianShort;
import weliyek.serialization.number.WkSerdeUnsignedLittleEndianShortWriter;

public class WkBtcCompactSizeSerdeWriter
    implements WkSerdeDtreeAggregatorMsgWriter<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<Long>,
                        WkBtcCompactSizeSerdeDef>
{

  final WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
                        Long,
                        WkSerdeDtreeOperationSettings,
                        WkBtcCompactSizeSerdeDef,
                        WkBtcCompactSizeSerdeWriter> writerCore;

  WkBtcCompactSizeSerdeWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<Long, WkSerdeDtreeOperationSettings, ?, ?, WkSerdeDtreeBytestreamOutputBase<?>, ?, ?, ?>
      writerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<Long, ?, ?, ?, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter, ? extends WkBtcCompactSizeSerdeDef>
      definitionCore) {
    this.writerCore = new WkSerdeDtreeAggregatorMsgWriterCoreSimplified<Long, WkSerdeDtreeOperationSettings, WkBtcCompactSizeSerdeDef, WkBtcCompactSizeSerdeWriter>(
                            index, writerFieldCore, definitionCore, this);
  }

  public Optional<WkSerdeDtreeMsgOutputField<Integer, WkSerdeUnsignedByte, WkSerdeUnsignedByteWriter>>
  firstByte() {
    return Optional.ofNullable(writerCore.getSubfieldpacketFor(definition().firstByte).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<Integer, WkSerdeUnsignedLittleEndianShort, WkSerdeUnsignedLittleEndianShortWriter>>
  uint16le() {
    return Optional.ofNullable(writerCore.getSubfieldpacketFor(definition().uint16le).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<Long, WkSerdeUnsignedLittleEndianInteger, WkSerdeUnsignedLittleEndianIntegerWriter>>
  uint32le() {
    return Optional.ofNullable(writerCore.getSubfieldpacketFor(definition().uint32le).asPacket());
  }

  public Optional<WkSerdeDtreeMsgOutputField<Long, WkSerdeSignedLittleEndianLong, WkSerdeSignedLittleEndianLongWriter>>
  int64le() {
    return Optional.ofNullable(writerCore.getSubfieldpacketFor(definition().int64le).asPacket());
  }

  @Override
  public WkBtcCompactSizeSerdeDef definition() {
    return this.writerCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.writerCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput> dashboard() {
    return this.writerCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<Long>> result() {
    return this.writerCore.result();
  }

  @Override
  public int index() {
    return this.writerCore.index();
  }

  @Override
  public String name() {
    return this.writerCore.name();
  }

  @Override
  public Long serializable() {
    return this.writerCore.serializable();
  }

  @Override
  public List<WkSerdeDtreeMsgOutputField<?, ?, ?>> subfields() {
    return this.writerCore.subfields();
  }

  @Override
  public WkSerdeDtreeMsgOutputField<?, ?, ?> parentField() {
    return this.writerCore.parentField();
  }

}
