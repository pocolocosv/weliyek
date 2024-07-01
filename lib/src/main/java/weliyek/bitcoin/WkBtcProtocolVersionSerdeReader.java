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
import weliyek.serialization.number.WkSerdeSignedLittleEndianInteger;
import weliyek.serialization.number.WkSerdeSignedLittleEndianIntegerReader;

public class WkBtcProtocolVersionSerdeReader
    implements WkSerdeDtreeAggregatorMsgReader<
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkBtcProtocolVersion>,
                        WkBtcProtocolVersionSerdeDef>
{

  final WkSerdeDtreeAggregatorMsgReaderCoreSimplified<
                        WkBtcProtocolVersion,
                        WkSerdeDtreeOperationSettings,
                        WkBtcProtocolVersionSerdeDef,
                        WkBtcProtocolVersionSerdeReader> readerCore;

  WkBtcProtocolVersionSerdeReader(
    int index,
    WkSerdeDtreeMsgInputFieldCore<?,WkSerdeDtreeOperationSettings,?,?,WkSerdeDtreeBytestreamInputBase<?>,?,?,?>
      readerFieldCore,
    WkSerdeDtreeAggregatorStructDefinitionCoreSimplified<WkBtcProtocolVersion, WkSerdeDtreeOperationSettings, WkBtcProtocolVersionSerdeDef, WkBtcProtocolVersionSerdeReader, ?, ?, ?, ? extends WkBtcProtocolVersionSerdeDef>
      definitionCore) {
    this.readerCore = new WkSerdeDtreeAggregatorMsgReaderCoreSimplified<>(
                              index, readerFieldCore, definitionCore, this);
  }

  public Optional<WkSerdeDtreeMsgInputField<Integer, WkSerdeSignedLittleEndianInteger, WkSerdeSignedLittleEndianIntegerReader>>
  int32le() {
    return Optional.ofNullable(readerCore.getSubfieldpacketFor(definition().int32).asPacket());
  }

  @Override
  public WkBtcProtocolVersionSerdeDef definition() {
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
  public Optional<WkSerdeDtreeOperationResult<WkBtcProtocolVersion>> result() {
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
