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
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerWriter;

public class WkBtcProtocolVersionSerdeWriter
    implements WkSerdeDtreeAggregatorMsgWriter<
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationOutputRuntime<WkSerdeDtreeBytestreamOutput>,
                        WkSerdeDtreeOperationResult<WkBtcProtocolVersion>,
                        WkBtcProtocolVersionSerdeDef>
{

  final WkSerdeDtreeAggregatorMsgWriterCoreSimplified<
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerdeDef,
                        WkBtcProtocolVersionSerdeWriter> writerCore;

  WkBtcProtocolVersionSerdeWriter(
    int index,
    WkSerdeDtreeMsgOutputFieldCore<WkBtcProtocolVersion,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamOutputBase<?>,?,?,?>
      writerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcProtocolVersion, ?, ?, ?, WkSerdeDtreeOperationSettings, WkBtcProtocolVersionSerdeDef, WkBtcProtocolVersionSerdeWriter, ? extends WkBtcProtocolVersionSerdeDef>
      definitionCore) {
    this.writerCore = new WkSerdeDtreeAggregatorMsgWriterCoreSimplified<>(
                              index, writerFieldCore, definitionCore, this);
  }

  public Optional<WkSerdeDtreeMsgOutputField<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerWriter>>
  int32le() {
    return Optional.ofNullable(writerCore.getSubfieldpacketFor(definition().int32).asPacket());
  }

  @Override
  public WkBtcProtocolVersionSerdeDef definition() {
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
  public Optional<WkSerdeDtreeOperationResult<WkBtcProtocolVersion>> result() {
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
  public WkBtcProtocolVersion serializable() {
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
